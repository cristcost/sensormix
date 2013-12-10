/*
 * Copyright 2013, Cristiano Costantini, Giuseppe Gerla, Michele Ficarra, Sergio Ciampi, Stefano
 * Cigheri.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
package com.google.developers.gdgfirenze.serializer;

import com.google.developers.gdgfirenze.model.AbstractSample;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.util.ClassUtils;
import org.springframework.util.SystemPropertyUtils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The Class Serializer. This class wrap the kryo instance to allow
 * auto-registration of serializable class derived by a type.
 */
public class Serializer {
  /**
   * The class logger.
   */
  private static Logger logger = Logger.getLogger(Serializer.class.getName());

  /**
   * The kryo instance.
   */
  private Kryo k;

  /**
   * Instantiates a new serializer.
   */
  public Serializer() {
    k = new Kryo();
    k.setClassLoader(Serializer.class.getClassLoader());
    k.setRegistrationRequired(true);
  }

  /**
   * Serialize an abstract class.
   * 
   * @param orig abstract class instance to serialize
   * @return byte array with serialized data
   */
  public byte[] serialize(AbstractSample orig) {
    final ByteArrayOutputStream bos = new ByteArrayOutputStream();
    final Output buffer = new Output(bos);
    k.writeClassAndObject(buffer, orig);
    buffer.close();
    return bos.toByteArray();
  }

  /**
   * Deserialize byte data.
   * 
   * @param buffer the buffer to deserialize
   * @return the abstract sample
   */
  public AbstractSample deserialize(byte[] buffer) {
    final Input iBuff = new Input(buffer);

    return (AbstractSample) k.readClassAndObject(iBuff);
  }

  /**
   * Register all derived class.
   * 
   * @param ancestorClass the ancestor class
   */
  public void registerAllDerivedClass(Class<?> ancestorClass) {
    List<Class<?>> classesDerived = new ArrayList<Class<?>>();

    try {
      classesDerived.addAll(findDerivedClassInPackage(ancestorClass.getPackage().getName(),
          ancestorClass));
    } catch (IOException e) {
      logger.log(Level.SEVERE, "Error during class searching", e);
    } catch (ClassNotFoundException e) {
      logger.log(Level.SEVERE, "Error during class searching", e);
    }
    classesDerived = new ArrayList<Class<?>>(new LinkedHashSet<Class<?>>(classesDerived));
    for (Class<?> cl : classesDerived) {
      logger.log(Level.INFO, "Register class " + cl + " with id " + cl.hashCode());
      k.register(cl);
    }
  }

  /**
   * Find all derived class by anchestorType in a package.
   * 
   * @param basePackage package in which search derived class
   * @param ancestorClass the ancestor class
   * @return list of all derived class
   * @throws IOException if package is invalid or not found
   * @throws ClassNotFoundException if class is not in the classpath
   */
  private List<Class<?>> findDerivedClassInPackage(String basePackage, Class<?> ancestorClass)
    throws IOException, ClassNotFoundException {
    final ResourcePatternResolver resourcePatternResolver =
        new PathMatchingResourcePatternResolver();
    final MetadataReaderFactory metadataReaderFactory =
        new CachingMetadataReaderFactory(resourcePatternResolver);

    final List<Class<?>> candidates = new ArrayList<Class<?>>();
    final String packageSearchPath =
        ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX + resolveBasePackage(basePackage) + "/"
            + "**/*.class";
    final Resource[] resources = resourcePatternResolver.getResources(packageSearchPath);
    for (Resource resource : resources) {
      if (resource.isReadable()) {
        final MetadataReader metadataReader = metadataReaderFactory.getMetadataReader(resource);

        final Class<?> cl = Class.forName(metadataReader.getClassMetadata().getClassName());
        if (isCandidate(cl, ancestorClass)) {
          candidates.add(cl);
          candidates.addAll(findFieldClass(cl, ancestorClass));
        }
      }
    }
    return candidates;
  }

  /**
   * Find all fields types of a class.
   * 
   * @param classToCheck class to inspect
   * @param ancestorClass the ancestor class
   * @return list of all fields classes
   */
  private List<Class<?>> findFieldClass(Class<?> classToCheck, Class<?> ancestorClass) {
    final List<Class<?>> candidates = new ArrayList<Class<?>>();
    final List<Field> fields = new ArrayList<Field>();
    Class<?> currClassToCheck = classToCheck;
    do {
      fields.addAll(Arrays.asList(currClassToCheck.getDeclaredFields()));
      fields.addAll(Arrays.asList(currClassToCheck.getFields()));
      currClassToCheck = currClassToCheck.getSuperclass();
    } while (currClassToCheck != ancestorClass.getSuperclass());
    for (Field f : fields) {
      try {
        k.getRegistration(f.getType());
      } catch (IllegalArgumentException e) {
        logger.log(Level.WARNING, "Class not registered in Kryo. Automatic registration");
        candidates.add(f.getType());
      }
    }

    return candidates;
  }

  /**
   * resolve a base package name in a resource path.
   * 
   * @param basePackage package name to resolve
   * @return resource path
   */
  private String resolveBasePackage(String basePackage) {
    String className = SystemPropertyUtils.resolvePlaceholders(basePackage);
    return ClassUtils.convertClassNameToResourcePath(className);
  }

  /**
   * Check if a class is not already registered and is derived from anchestor
   * class.
   * 
   * @param currClassToCheck class to check
   * @param ancestorClass the ancestor class
   * @return true if class verifies conditions
   * @throws ClassNotFoundException if class is not in the classpath
   */
  private boolean isCandidate(Class<?> classToCheck, Class<?> ancestorClass)
    throws ClassNotFoundException {
    Class<?> currClassToCheck = classToCheck;
    try {
      k.getRegistration(currClassToCheck);
    } catch (IllegalArgumentException e) {
      logger.log(Level.WARNING, "Class not registered in Kryo. Automatic registration");
      while (currClassToCheck != null && currClassToCheck.getSuperclass() != Object.class) {
        if (currClassToCheck.getSuperclass() == ancestorClass) {
          return true;
        }
        currClassToCheck = currClassToCheck.getSuperclass();
      }
    }
    return false;
  }
}

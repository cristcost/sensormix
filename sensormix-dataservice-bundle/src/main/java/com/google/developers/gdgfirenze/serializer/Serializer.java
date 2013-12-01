package com.google.developers.gdgfirenze.serializer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.util.ClassUtils;
import org.springframework.util.SystemPropertyUtils;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.google.developers.gdgfirenze.model.AbstractSample;

public class Serializer {
	private static Logger logger = Logger.getLogger(Serializer.class.getName());

	Kryo k;

	public Serializer() {
		k = new Kryo();
		k.setClassLoader(Serializer.class.getClassLoader());
		k.setRegistrationRequired(true);
	}

	public byte[] serialize(AbstractSample orig) {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		Output buffer = new Output(bos);
		k.writeClassAndObject(buffer, orig);
		buffer.close();
		return bos.toByteArray();
	}

	public AbstractSample deserialize(byte[] buffer) {
		Input iBuff = new Input(buffer);

		return (AbstractSample) k.readClassAndObject(iBuff);
	}

	public void registerAllDerivedClass(Class<?> ancestorClass) {
		List<Class<?>> classesDerived = new ArrayList<Class<?>>();

		try {
			classesDerived.addAll(findDerivedClassInPackage(ancestorClass
					.getPackage().getName(), ancestorClass));
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Error during class searching", e);
		}
		classesDerived = new ArrayList<Class<?>>(new LinkedHashSet<Class<?>>(classesDerived));
		for (Class<?> cl : classesDerived) {
			logger.log(Level.INFO, "Register class " + cl + " with id " + cl.hashCode());
			k.register(cl);
		}
	}

	private List<Class<?>> findDerivedClassInPackage(String basePackage,
			Class<?> ancestorClass) throws IOException, ClassNotFoundException {
		ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
		MetadataReaderFactory metadataReaderFactory = new CachingMetadataReaderFactory(
				resourcePatternResolver);

		List<Class<?>> candidates = new ArrayList<Class<?>>();
		String packageSearchPath = ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX
				+ resolveBasePackage(basePackage) + "/" + "**/*.class";
		Resource[] resources = resourcePatternResolver
				.getResources(packageSearchPath);
		for (Resource resource : resources) {
			if (resource.isReadable()) {
				MetadataReader metadataReader = metadataReaderFactory
						.getMetadataReader(resource);

				Class<?> cl = Class.forName(metadataReader.getClassMetadata()
						.getClassName());
				if (isCandidate(cl, ancestorClass)) {
					candidates.add(cl);
					candidates.addAll(findFieldClass(cl, ancestorClass));
				}
			}
		}
		return candidates;
	}

	private List<Class<?>> findFieldClass(Class<?> classToCheck, Class<?> ancestorClass) {
		List<Class<?>> candidates = new ArrayList<Class<?>>();
		List<Field> fields = new ArrayList<Field>();
		do  {
			fields.addAll(Arrays.asList(classToCheck.getDeclaredFields()));
			fields.addAll(Arrays.asList(classToCheck.getFields()));
			classToCheck = classToCheck.getSuperclass();
		} while (classToCheck != ancestorClass.getSuperclass());
		for (Field f : fields) {
			try {
				k.getRegistration(f.getType());
			} catch (Exception e) {
				logger.log(Level.WARNING, "Class not registered in Kryo. Automatic registration");
				candidates.add(f.getType());
			}
		}

		return candidates;
	}

	private String resolveBasePackage(String basePackage) {
		return ClassUtils.convertClassNameToResourcePath(SystemPropertyUtils
				.resolvePlaceholders(basePackage));
	}

	private boolean isCandidate(Class<?> classToCheck, Class<?> ancestorClass)
			throws ClassNotFoundException {
		try {
			k.getRegistration(classToCheck);
		} catch (Exception e) {
			logger.log(Level.WARNING, "Class not registered in Kryo. Automatic registration");
			while (classToCheck != null
					&& classToCheck.getSuperclass() != Object.class) {
				if (classToCheck.getSuperclass() == ancestorClass)
					return true;
				classToCheck = classToCheck.getSuperclass();
			}
		}
		return false;
	}
}

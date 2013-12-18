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
import com.google.developers.gdgfirenze.model.NumericValueSample;
import com.google.developers.gdgfirenze.model.PositionSample;
import com.google.developers.gdgfirenze.model.StringValueSample;
import com.google.developers.gdgfirenze.model.WifiSignalSample;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

import java.io.ByteArrayOutputStream;
import java.util.Date;

/**
 * The Class Serializer. This class wrap the kryo instance to allow
 * auto-registration of serializable class derived by a type.
 */
public class Serializer {
  
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

    k.register(Date.class, 100);
    k.register(WifiSignalSample.class, 200);
    k.register(NumericValueSample.class, 201);
    k.register(PositionSample.class, 202);
    k.register(StringValueSample.class, 203);
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
}

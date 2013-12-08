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
package com.google.developers.gdgfirenze.integration;

import com.google.developers.gdgfirenze.model.NumericValueSample;
import com.google.developers.gdgfirenze.protobuf.SensormixProtos.SampleMessage;
import com.google.developers.gdgfirenze.service.SamplesPayload;

import java.util.Date;

/**
 * The Class SampleAdapter.
 * 
 * Is used within camel routes to transform java messages in Protobuf format to the canonical
 * SamplePayload format.
 * 
 */
public class SampleAdapter {

  public SamplesPayload transform(SampleMessage message) {
    SamplesPayload ret = new SamplesPayload();

    NumericValueSample sample = new NumericValueSample();
    sample.setType("urn:rixf:net.sensormix/sample_types/faces_detected");
    sample.setSensorId(message.getDeviceId());
    sample.setTime(new Date(message.getTime() * 1000L));
    sample.setValue((double) message.getFaces());

    ret.getSamples().add(sample);

    return ret;
  }
}

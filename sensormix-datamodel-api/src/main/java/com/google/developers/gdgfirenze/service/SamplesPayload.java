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
package com.google.developers.gdgfirenze.service;

import com.google.developers.gdgfirenze.model.AbstractSample;
import com.google.developers.gdgfirenze.model.NumericValueSample;
import com.google.developers.gdgfirenze.model.PositionSample;
import com.google.developers.gdgfirenze.model.Sensor;
import com.google.developers.gdgfirenze.model.StringValueSample;
import com.google.developers.gdgfirenze.model.WifiSignalSample;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * The Class SamplesPayload.
 * 
 * This class is used to aggregate multiple sensors and transport between two endpoints. It belongs
 * to the 'service' package as its purpose is not for 'representation' of information, but it is for
 * supporting 'exchange' of information.
 */
@SuppressWarnings("serial")
@XmlRootElement(name = "data")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SamplesPayload")
public class SamplesPayload implements Serializable {

  /** The samples. */
  @XmlElementWrapper(name = "samples")
  @XmlElements({
      @XmlElement(name = "numericValueSample", type = NumericValueSample.class),
      @XmlElement(name = "stringValueSample", type = StringValueSample.class),
      @XmlElement(name = "positionSample", type = PositionSample.class),
      @XmlElement(name = "wifiSignalSample", type = WifiSignalSample.class)
  })
  private final List<AbstractSample> samples = new ArrayList<AbstractSample>();

  /** The sensor. */
  @XmlElement(name = "sensor", required = false)
  private Sensor sensor;

  /**
   * Gets the samples.
   * 
   * @return the samples
   */
  public List<AbstractSample> getSamples() {
    return samples;
  }

  /**
   * Gets the sensor.
   * 
   * @return the sensor
   */
  public Sensor getSensor() {
    return sensor;
  }

  /**
   * Sets the sensor.
   * 
   * @param sensor the new sensor
   */
  public void setSensor(Sensor sensor) {
    this.sensor = sensor;
  }

}

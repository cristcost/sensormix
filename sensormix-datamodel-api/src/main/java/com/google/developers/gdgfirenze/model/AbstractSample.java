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
package com.google.developers.gdgfirenze.model;

import java.io.Serializable;
import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;

/**
 * The Class AbstractSample.
 * 
 * It represents the base class for every sample generated by a sensor.
 * 
 * It is annotated with JaxB annotations in order to allow easy marshalling/unmarshalling to XML.
 * The class is part of a the Sensormix.gwt.xml module and implements Serializable in order to be
 * used within a GWT application.
 * 
 */
@SuppressWarnings("serial")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AbstractSample")
@XmlSeeAlso({
    NumericValueSample.class, PositionSample.class, WifiSignalSample.class
})
public abstract class AbstractSample implements Serializable {

  @XmlAttribute(required = true, name = "sensorId")
  @XmlSchemaType(name = "anyURI")
  private String sensorId;

  @XmlAttribute(required = false, name = "time")
  @XmlSchemaType(name = "dateTime")
  private Date time;

  @XmlAttribute(required = false, name = "type")
  @XmlSchemaType(name = "anyURI")
  private String type;

  public String getSensorId() {
    return sensorId;
  }

  public void setSensorId(String sensorId) {
    this.sensorId = sensorId;
  }

  public Date getTime() {
    return time;
  }

  public void setTime(Date time) {
    this.time = time;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

}
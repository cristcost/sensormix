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
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;

/**
 * The Class SampleReport.
 * 
 * It represents statistic of samples collected from a sensors.
 * 
 * It is annotated with JaxB annotations in order to allow easy marshalling/unmarshalling to XML.
 * The class is part of a the Sensormix.gwt.xml module and implements Serializable in order to be
 * used within a GWT application.
 */
@SuppressWarnings("serial")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SampleReport")
public class SampleReport implements Serializable {

  /** The sensor id. */
  @XmlAttribute(required = true, name = "sensorId")
  @XmlSchemaType(name = "anyURI")
  private String sensorId;

  /** The sample type. */
  @XmlAttribute(required = false, name = "sampleType")
  private String sampleType;

  /** The daily sample reports. */
  @XmlElement(required = false, name = "dailySampleReport")
  private List<DailySampleReport> dailySampleReports;

  /**
   * Gets the sensor id.
   * 
   * @return the sensor id
   */
  public String getSensorId() {
    return sensorId;
  }

  /**
   * Sets the sensor id.
   * 
   * @param sensorId the new sensor id
   */
  public void setSensorId(String sensorId) {
    this.sensorId = sensorId;
  }

  /**
   * Gets the sample type.
   * 
   * @return the sample type
   */
  public String getSampleType() {
    return sampleType;
  }

  /**
   * Sets the sample type.
   * 
   * @param sampleType the new sample type
   */
  public void setSampleType(String sampleType) {
    this.sampleType = sampleType;
  }

  /**
   * Gets the daily sample reports.
   * 
   * @return the daily sample reports
   */
  public List<DailySampleReport> getDailySampleReports() {
    return dailySampleReports;
  }

  /**
   * Sets the daily sample reports.
   * 
   * @param dailySampleReports the new daily sample reports
   */
  public void setDailySampleReports(List<DailySampleReport> dailySampleReports) {
    this.dailySampleReports = dailySampleReports;
  }

}

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
import javax.xml.bind.annotation.XmlType;

/**
 * The Class DailySampleReport.
 * 
 * It represents daily statistic of number of samples collected.
 * 
 * It is annotated with JaxB annotations in order to allow easy marshalling/unmarshalling to XML.
 * The class is part of a the Sensormix.gwt.xml module and implements Serializable in order to be
 * used within a GWT application.
 */
@SuppressWarnings("serial")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DailySampleReport")
public class DailySampleReport implements Serializable {

  /** The sample count. */
  @XmlAttribute(required = false, name = "sampleCount")
  private long sampleCount;

  /** The date. */
  @XmlAttribute(required = false, name = "date")
  @XmlSchemaType(name = "dateTime")
  private Date date;

  /**
   * Gets the sample count.
   *
   * @return the sample count
   */
  public long getSampleCount() {
    return sampleCount;
  }

  /**
   * Sets the sample count.
   *
   * @param sampleCount the new sample count
   */
  public void setSampleCount(long sampleCount) {
    this.sampleCount = sampleCount;
  }

  /**
   * Gets the date.
   *
   * @return the date
   */
  public Date getDate() {
    return date;
  }

  /**
   * Sets the date.
   *
   * @param date the new date
   */
  public void setDate(Date date) {
    this.date = date;
  }

}

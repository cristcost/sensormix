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

package com.google.developers.gdgfirenze.datamodeljpa;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The Class JpaAbstractSample.
 */
@Entity
@Table(name = "abstractsample")
@Access(AccessType.FIELD)
public class JpaAbstractSample {

  @Id
  @GeneratedValue
  private Long id;

  @Column(name = "sensorId", nullable = false)
  private String sensorId;

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "time", nullable = false)
  private Date time;

  @Column(name = "type", nullable = false)
  private String type;

  @Lob
  @Column(name = "sample", nullable = false)
  private byte[] value;

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
   * Gets the time.
   * 
   * @return the time
   */
  public Date getTime() {
    return time;
  }

  /**
   * Sets the time.
   * 
   * @param time the new time
   */
  public void setTime(Date time) {
    this.time = time;
  }

  /**
   * Gets the type.
   * 
   * @return the type
   */
  public String getType() {
    return type;
  }

  /**
   * Sets the type.
   * 
   * @param type the new type
   */
  public void setType(String type) {
    this.type = type;
  }

  /**
   * Gets the value.
   * 
   * @return the value
   */
  public byte[] getValue() {
    return value;
  }

  /**
   * Sets the value.
   * 
   * @param value the new value
   */
  public void setValue(byte[] value) {
    this.value = value;
  }

}

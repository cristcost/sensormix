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
import java.util.List;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The Class JpaSensor.
 */
@Entity
@Table(name = "sensor")
@Access(AccessType.FIELD)
public class JpaSensor {

  @Id
  @Column(name = "id")
  private String id;

  @Column(name = "name", nullable = false)
  private String name;

  @Column(name = "type")
  private String type;

  @Column(name = "description", nullable = false)
  private String description;

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "lastSeen")
  private Date lastSeen;

  @Column(name = "lat")
  private Double lat;

  @Column(name = "lng")
  private Double lng;

  @OneToMany
  @JoinColumn(name = "sensorId")
  private List<JpaAbstractSample> samples;

  /**
   * Gets the samples.
   * 
   * @return the samples
   */
  public List<JpaAbstractSample> getSamples() {
    return samples;
  }

  /**
   * Sets the samples.
   * 
   * @param samples the new samples
   */
  public void setSamples(List<JpaAbstractSample> samples) {
    this.samples = samples;
  }

  /**
   * Gets the id.
   * 
   * @return the id
   */
  public String getId() {
    return id;
  }

  /**
   * Sets the id.
   * 
   * @param id the new id
   */
  public void setId(String id) {
    this.id = id;
  }

  /**
   * Gets the name.
   * 
   * @return the name
   */
  public String getName() {
    return name;
  }

  /**
   * Sets the name.
   * 
   * @param name the new name
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Gets the description.
   * 
   * @return the description
   */
  public String getDescription() {
    return description;
  }

  /**
   * Sets the description.
   * 
   * @param description the new description
   */
  public void setDescription(String description) {
    this.description = description;
  }

  /**
   * Gets the last seen.
   * 
   * @return the last seen
   */
  public Date getLastSeen() {
    return lastSeen;
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
   * Sets the last seen.
   * 
   * @param lastSeen the new last seen
   */
  public void setLastSeen(Date lastSeen) {
    this.lastSeen = lastSeen;
  }

  /**
   * Gets the lat.
   * 
   * @return the lat
   */
  public Double getLat() {
    return lat;
  }

  /**
   * Sets the lat.
   * 
   * @param lat the new lat
   */
  public void setLat(Double lat) {
    this.lat = lat;
  }

  /**
   * Gets the lng.
   * 
   * @return the lng
   */
  public Double getLng() {
    return lng;
  }

  /**
   * Sets the lng.
   * 
   * @param lng the new lng
   */
  public void setLng(Double lng) {
    this.lng = lng;
  }
}

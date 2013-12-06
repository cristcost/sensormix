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
 * The Class Sensor.
 * 
 * It represents a sensor and contains information such as name, description and position for
 * implementing a GUI for browsing sensors defined in the systems.
 * 
 * It is annotated with JaxB annotations in order to allow easy marshalling/unmarshalling to XML.
 * The class is part of a the Sensormix.gwt.xml module and implements Serializable in order to be
 * used within a GWT application.
 */
@SuppressWarnings("serial")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Sensor")
public class Sensor implements Serializable {

  /** The id. */
  @XmlAttribute(required = true, name = "id")
  @XmlSchemaType(name = "anyURI")
  private String id;

  /** The type. */
  @XmlAttribute(required = false, name = "type")
  @XmlSchemaType(name = "anyURI")
  private String type;

  /** The name. */
  @XmlAttribute(required = false, name = "name")
  private String name;

  /** The description. */
  @XmlAttribute(required = false, name = "description")
  private String description;

  /** The last seen. */
  @XmlAttribute(required = false, name = "lastSeen")
  @XmlSchemaType(name = "dateTime")
  private Date lastSeen;

  /** The lat. */
  @XmlAttribute(required = false, name = "lat")
  @XmlSchemaType(name = "double")
  private Double lat;

  /** The lng. */
  @XmlAttribute(required = false, name = "lng")
  @XmlSchemaType(name = "double")
  private Double lng;

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
   * Gets the last seen.
   * 
   * @return the last seen
   */
  public Date getLastSeen() {
    return lastSeen;
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

}

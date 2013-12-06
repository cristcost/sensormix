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

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;

/**
 * The Class PositionSample.
 * 
 * It implements a sample with a position information, such as GPS data collected from a smartphone.
 * 
 * It is annotated with JaxB annotations in order to allow easy marshalling/unmarshalling to XML.
 * The class is part of a the Sensormix.gwt.xml module and implements Serializable in order to be
 * used within a GWT application.
 */
@SuppressWarnings("serial")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PositionSample")
public class PositionSample extends AbstractSample implements Serializable {

  /** The accuracy. */
  @XmlAttribute(required = false, name = "accuracy")
  @XmlSchemaType(name = "double")
  private Double accuracy; // 5,

  /** The alt. */
  @XmlAttribute(required = false, name = "alt")
  @XmlSchemaType(name = "double")
  private Double alt; // 100.0999755859375,

  /** The bearing. */
  @XmlAttribute(required = false, name = "bearing")
  @XmlSchemaType(name = "double")
  private Double bearing; // 4,

  /** The lat. */
  @XmlAttribute(required = false, name = "lat")
  @XmlSchemaType(name = "double")
  private Double lat; // 43.905801717191935,

  /** The lng. */
  @XmlAttribute(required = false, name = "lng")
  @XmlSchemaType(name = "double")
  private Double lng; // 11.031828951090574,

  /** The speed. */
  @XmlAttribute(required = false, name = "speed")
  @XmlSchemaType(name = "double")
  private Double speed; // 10.5

  /**
   * Gets the accuracy.
   * 
   * @return the accuracy
   */
  public Double getAccuracy() {
    return accuracy;
  }

  /**
   * Sets the accuracy.
   * 
   * @param accuracy the new accuracy
   */
  public void setAccuracy(Double accuracy) {
    this.accuracy = accuracy;
  }

  /**
   * Gets the alt.
   * 
   * @return the alt
   */
  public Double getAlt() {
    return alt;
  }

  /**
   * Sets the alt.
   * 
   * @param alt the new alt
   */
  public void setAlt(Double alt) {
    this.alt = alt;
  }

  /**
   * Gets the bearing.
   * 
   * @return the bearing
   */
  public Double getBearing() {
    return bearing;
  }

  /**
   * Sets the bearing.
   * 
   * @param bearing the new bearing
   */
  public void setBearing(Double bearing) {
    this.bearing = bearing;
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
   * Gets the speed.
   * 
   * @return the speed
   */
  public Double getSpeed() {
    return speed;
  }

  /**
   * Sets the speed.
   * 
   * @param speed the new speed
   */
  public void setSpeed(Double speed) {
    this.speed = speed;
  }

}

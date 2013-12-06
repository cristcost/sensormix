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
 * The Class WifiSignalSample.
 * 
 * It implements a sample with wifi access point identification information (BSSID, SSID) and
 * strength of the signal detected.
 * 
 * It is annotated with JaxB annotations in order to allow easy marshalling/unmarshalling to XML.
 * The class is part of a the Sensormix.gwt.xml module and implements Serializable in order to be
 * used within a GWT application.
 */
@SuppressWarnings("serial")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "WifiSignalSample")
public class WifiSignalSample extends AbstractSample implements Serializable {

  /** The bssid. */
  @XmlAttribute(required = false, name = "bssid")
  private String bssid; // "00:22:3f:56:38:6a",

  /** The capabilities. */
  @XmlAttribute(required = false, name = "capabilities")
  private String capabilities; // "[WPA2-PSK-CCMP][ESS]",

  /** The frequency. */
  @XmlAttribute(required = false, name = "frequency")
  @XmlSchemaType(name = "double")
  private Double frequency; // 2437,

  /** The level. */
  @XmlAttribute(required = false, name = "level")
  @XmlSchemaType(name = "double")
  private Double level; // -50,

  /** The ssid. */
  @XmlAttribute(required = false, name = "ssid")
  private String ssid; // "Silence"

  /**
   * Gets the bssid.
   * 
   * @return the bssid
   */
  public String getBssid() {
    return bssid;
  }

  /**
   * Sets the bssid.
   * 
   * @param bssid the new bssid
   */
  public void setBssid(String bssid) {
    this.bssid = bssid;
  }

  /**
   * Gets the capabilities.
   * 
   * @return the capabilities
   */
  public String getCapabilities() {
    return capabilities;
  }

  /**
   * Sets the capabilities.
   * 
   * @param capabilities the new capabilities
   */
  public void setCapabilities(String capabilities) {
    this.capabilities = capabilities;
  }

  /**
   * Gets the frequency.
   * 
   * @return the frequency
   */
  public Double getFrequency() {
    return frequency;
  }

  /**
   * Sets the frequency.
   * 
   * @param frequency the new frequency
   */
  public void setFrequency(Double frequency) {
    this.frequency = frequency;
  }

  /**
   * Gets the level.
   * 
   * @return the level
   */
  public Double getLevel() {
    return level;
  }

  /**
   * Sets the level.
   * 
   * @param level the new level
   */
  public void setLevel(Double level) {
    this.level = level;
  }

  /**
   * Gets the ssid.
   * 
   * @return the ssid
   */
  public String getSsid() {
    return ssid;
  }

  /**
   * Sets the ssid.
   * 
   * @param ssid the new ssid
   */
  public void setSsid(String ssid) {
    this.ssid = ssid;
  }

}

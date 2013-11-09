package com.google.developers.gdgfirenze.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlSchemaType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "WifiSignalSample")
public class WifiSignalSample extends AbstractSample implements Serializable {

  @XmlAttribute(required = false, name = "bssid")
  private String bssid; // "00:22:3f:56:38:6a",

  @XmlAttribute(required = false, name = "capabilities")
  private String capabilities; // "[WPA2-PSK-CCMP][ESS]",

  @XmlAttribute(required = false, name = "frequency")
  @XmlSchemaType(name = "double")
  private Double frequency; // 2437,

  @XmlAttribute(required = false, name = "level")
  @XmlSchemaType(name = "double")
  private Double level; // -50,

  @XmlAttribute(required = false, name = "ssid")
  private String ssid; // "Silence"

  public String getBssid() {
    return bssid;
  }

  public void setBssid(String bssid) {
    this.bssid = bssid;
  }

  public String getCapabilities() {
    return capabilities;
  }

  public void setCapabilities(String capabilities) {
    this.capabilities = capabilities;
  }

  public Double getFrequency() {
    return frequency;
  }

  public void setFrequency(Double frequency) {
    this.frequency = frequency;
  }

  public Double getLevel() {
    return level;
  }

  public void setLevel(Double level) {
    this.level = level;
  }

  public String getSsid() {
    return ssid;
  }

  public void setSsid(String ssid) {
    this.ssid = ssid;
  }

}

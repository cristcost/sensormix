package com.google.developers.gdgfirenze.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlSchemaType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PositionSample")
public class PositionSample extends AbstractSample implements Serializable {

  @XmlAttribute(required = false, name = "accuracy")
  @XmlSchemaType(name = "double")
  private Double accuracy; // 5,

  @XmlAttribute(required = false, name = "alt")
  @XmlSchemaType(name = "double")
  private Double alt; // 100.0999755859375,

  @XmlAttribute(required = false, name = "bearing")
  @XmlSchemaType(name = "double")
  private Double bearing; // 4,

  @XmlAttribute(required = false, name = "lat")
  @XmlSchemaType(name = "double")
  private Double lat; // 43.905801717191935,

  @XmlAttribute(required = false, name = "lng")
  @XmlSchemaType(name = "double")
  private Double lng; // 11.031828951090574,

  @XmlAttribute(required = false, name = "speed")
  @XmlSchemaType(name = "double")
  private Double speed; // 10.5

  public Double getAccuracy() {
    return accuracy;
  }

  public void setAccuracy(Double accuracy) {
    this.accuracy = accuracy;
  }

  public Double getAlt() {
    return alt;
  }

  public void setAlt(Double alt) {
    this.alt = alt;
  }

  public Double getBearing() {
    return bearing;
  }

  public void setBearing(Double bearing) {
    this.bearing = bearing;
  }

  public Double getLat() {
    return lat;
  }

  public void setLat(Double lat) {
    this.lat = lat;
  }

  public Double getLng() {
    return lng;
  }

  public void setLng(Double lng) {
    this.lng = lng;
  }

  public Double getSpeed() {
    return speed;
  }

  public void setSpeed(Double speed) {
    this.speed = speed;
  }

}

package com.google.developers.gdgfirenze.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlSchemaType;

import java.io.Serializable;
import java.util.Date;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Sensor")
public class Sensor implements Serializable {

  @XmlAttribute(required = true, name = "id")
  @XmlSchemaType(name = "anyURI")
  private String id;

  @XmlAttribute(required = false, name = "name")
  private String name;

  @XmlAttribute(required = false, name = "description")
  private String description;

  @XmlAttribute(required = false, name = "lastSeen")
  @XmlSchemaType(name = "dateTime")
  private Date lastSeen;

  @XmlAttribute(required = false, name = "lat")
  @XmlSchemaType(name = "double")
  private Double lat;

  @XmlAttribute(required = false, name = "lng")
  @XmlSchemaType(name = "double")
  private Double lng;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public Date getLastSeen() {
    return lastSeen;
  }

  public void setLastSeen(Date lastSeen) {
    this.lastSeen = lastSeen;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
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

}

package com.google.developers.gdgfirenze.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlSchemaType;

import java.util.Date;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Sensor")
public class Sensor {

	@XmlAttribute(required = true, name = "id")
	@XmlSchemaType(name = "anyURI")
	public String id;

	@XmlAttribute(required = false, name = "lastSeen")
	@XmlSchemaType(name = "dateTime")
	public Date lastSeen;

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

}

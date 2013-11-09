package com.google.developers.gdgfirenze.model;

import java.io.Serializable;
import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlSchemaType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AbstractSample")
@XmlSeeAlso({
	NumericValueSample.class, PositionSample.class, WifiSignalSample.class
})
public abstract class AbstractSample implements Serializable {

	@XmlAttribute(required = true, name = "sensorId")
	@XmlSchemaType(name = "anyURI")
	private String sensorId;

	@XmlAttribute(required = false, name = "time")
	@XmlSchemaType(name = "dateTime")
	private Date time;

	@XmlAttribute(required = false, name = "type")
	@XmlSchemaType(name = "anyURI")
	private String type;
	
	public String getSensorId() {
		return sensorId;
	}

	public void setSensorId(String sensorId) {
		this.sensorId = sensorId;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
}
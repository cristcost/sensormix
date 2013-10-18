package com.google.developers.gdgfirenze.service;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DailySampleReport")
public class DailySampleReport {

	@XmlAttribute(required = false, name = "sampleCount")
	private long sampleCount;

	@XmlAttribute(required = false, name = "date")
	@XmlSchemaType(name = "dateTime")
	private Date date;

	public long getSampleCount() {
		return sampleCount;
	}

	public void setSampleCount(long sampleCount) {
		this.sampleCount = sampleCount;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

}

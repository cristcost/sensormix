package com.google.developers.gdgfirenze.model;

import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;

public class SampleReport {

	@XmlAttribute(required = true, name = "sensorId")
	@XmlSchemaType(name = "anyURI")
	private String sensorId;

	@XmlAttribute(required = false, name = "sampleType")
	private String sampleType;

	@XmlElement(required = false, name = "dailySampleReport")
	private List<DailySampleReport> dailySampleReports;

	public String getSensorId() {
		return sensorId;
	}

	public void setSensorId(String sensorId) {
		this.sensorId = sensorId;
	}

	public String getSampleType() {
		return sampleType;
	}

	public void setSampleType(String sampleType) {
		this.sampleType = sampleType;
	}

	public List<DailySampleReport> getDailySampleReports() {
		return dailySampleReports;
	}

	public void setDailySampleReports(List<DailySampleReport> dailySampleReports) {
		this.dailySampleReports = dailySampleReports;
	}

}

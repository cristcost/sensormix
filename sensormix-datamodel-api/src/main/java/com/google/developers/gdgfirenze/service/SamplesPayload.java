package com.google.developers.gdgfirenze.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.google.developers.gdgfirenze.model.AbstractSample;
import com.google.developers.gdgfirenze.model.NumericValueSample;
import com.google.developers.gdgfirenze.model.PositionSample;
import com.google.developers.gdgfirenze.model.Sensor;
import com.google.developers.gdgfirenze.model.StringValueSample;
import com.google.developers.gdgfirenze.model.WifiSignalSample;

@XmlRootElement(name = "data")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SamplesPayload")
public class SamplesPayload implements Serializable {

	@XmlElementWrapper(name = "samples")
	@XmlElements({
			@XmlElement(name = "numericValueSample", type = NumericValueSample.class),
			@XmlElement(name = "stringValueSample", type = StringValueSample.class),
			@XmlElement(name = "positionSample", type = PositionSample.class),
			@XmlElement(name = "wifiSignalSample", type = WifiSignalSample.class) })
	private final List<AbstractSample> samples = new ArrayList<AbstractSample>();

	@XmlElement(name = "sensor", required = false)
	private Sensor sensor;

	public List<AbstractSample> getSamples() {
		return samples;
	}

	public Sensor getSensor() {
		return sensor;
	}

	public void setSensor(Sensor sensor) {
		this.sensor = sensor;
	}

}

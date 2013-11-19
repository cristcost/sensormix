package com.google.developers.gdgfirenze.admin.client.model;

import java.io.Serializable;
import java.util.List;

import com.google.developers.gdgfirenze.model.AbstractSample;

public class SamplesRange implements Serializable{

	private static final long serialVersionUID = 5702957096600361000L;

	private long samplesCount;

	private List<AbstractSample> samples;

	public long getSamplesCount() {
		return samplesCount;
	}

	public void setSamplesCount(long samplesCount) {
		this.samplesCount = samplesCount;
	}

	public List<AbstractSample> getSamples() {
		return samples;
	}

	public void setSamples(List<AbstractSample> samples) {
		this.samples = samples;
	}

}

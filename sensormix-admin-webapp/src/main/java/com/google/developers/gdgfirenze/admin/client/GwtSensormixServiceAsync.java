package com.google.developers.gdgfirenze.admin.client;

import java.util.Date;
import java.util.List;

import com.google.developers.gdgfirenze.model.AbstractSample;
import com.google.developers.gdgfirenze.model.SampleReport;
import com.google.developers.gdgfirenze.model.Sensor;
import com.google.gwt.user.client.rpc.AsyncCallback;

public interface GwtSensormixServiceAsync {

	void countSamples(String sensorId, String sampleType, Date from, Date to,
			AsyncCallback<Long> callback);

	void getSampleReport(String sensorId, String sampleType, Date from,
			Date to, AsyncCallback<SampleReport> callback);

	void getSamples(String sensorId, String sampleType, Date from, Date to,
			AsyncCallback<List<AbstractSample>> callback);

	void getSensors(List<String> sensorIds, Date from, Date to,
			AsyncCallback<List<Sensor>> callback);

	void listSamplesTypes(AsyncCallback<List<String>> callback);

	void listSensorsIds(AsyncCallback<List<String>> callback);

	void recordSamples(List<AbstractSample> samples,
			AsyncCallback<Void> callback);

	void registerSensor(Sensor sensor, AsyncCallback<Void> callback);

}

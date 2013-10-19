package com.google.developers.gdgfirenze.dataservice;

import java.util.Date;
import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.persistence.EntityManagerFactory;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

import com.google.developers.gdgfirenze.model.AbstractSample;
import com.google.developers.gdgfirenze.model.Sensor;
import com.google.developers.gdgfirenze.service.SampleReport;
import com.google.developers.gdgfirenze.service.SensormixService;

public class SensormixServiceJpaImpl implements SensormixService {

	private EntityManagerFactory entityManagerFactory;
	
	public void setEntityManagerFactory(EntityManagerFactory entityManagerFactory) {
		this.entityManagerFactory = entityManagerFactory;
	}

	@Override
	@WebMethod(action = "urn:#listSensorsIds")
	@RequestWrapper(localName = "listSensorsIdsIn", targetNamespace = "http://developers.google.com/gdgfirenze/ns/service")
	@ResponseWrapper(localName = "listSensorsIdsOut", targetNamespace = "http://developers.google.com/gdgfirenze/ns/service")
	@WebResult(name = "sensorId")
	public
	List<String> listSensorsIds() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@WebMethod(action = "urn:#getSamples")
	@RequestWrapper(localName = "getSamplesIn", targetNamespace = "http://developers.google.com/gdgfirenze/ns/service")
	@ResponseWrapper(localName = "getSamplesOut", targetNamespace = "http://developers.google.com/gdgfirenze/ns/service")
	@WebResult(name = "sample")
	public
	List<AbstractSample> getSamples(
			@WebParam(name = "sensorId") String sensorId,
			@WebParam(name = "sampleType") String sampleType,
			@WebParam(name = "from") Date from, @WebParam(name = "to") Date to) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@WebMethod(action = "urn:#countSamples")
	@RequestWrapper(localName = "countSamplesIn", targetNamespace = "http://developers.google.com/gdgfirenze/ns/service")
	@ResponseWrapper(localName = "countSamplesOut", targetNamespace = "http://developers.google.com/gdgfirenze/ns/service")
	@WebResult(name = "sampleCount")
	public
	long countSamples(@WebParam(name = "sensorId") String sensorId,
			@WebParam(name = "sampleType") String sampleType,
			@WebParam(name = "from") Date from, @WebParam(name = "to") Date to) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	@WebMethod(action = "urn:#getSampleReport")
	@RequestWrapper(localName = "getSampleReportIn", targetNamespace = "http://developers.google.com/gdgfirenze/ns/service")
	@ResponseWrapper(localName = "getSampleReportOut", targetNamespace = "http://developers.google.com/gdgfirenze/ns/service")
	@WebResult(name = "sampleReport")
	public
	SampleReport getSampleReport(@WebParam(name = "sensorId") String sensorId,
			@WebParam(name = "sampleType") String sampleType,
			@WebParam(name = "from") Date from, @WebParam(name = "to") Date to) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@WebMethod(action = "urn:#getSensors")
	@RequestWrapper(localName = "getSensorsIn", targetNamespace = "http://developers.google.com/gdgfirenze/ns/service")
	@ResponseWrapper(localName = "getSensorsOut", targetNamespace = "http://developers.google.com/gdgfirenze/ns/service")
	@WebResult(name = "sensor")
	public
	List<Sensor> getSensors(@WebParam(name = "sensorId") List<String> sensorIds) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@WebMethod(action = "urn:#registerSensor")
	@RequestWrapper(localName = "registerSensorIn", targetNamespace = "http://developers.google.com/gdgfirenze/ns/service")
	@ResponseWrapper(localName = "registerSensorOut", targetNamespace = "http://developers.google.com/gdgfirenze/ns/service")
	public
	void registerSensor(@WebParam(name = "sensor") Sensor sensor) {
		// TODO Auto-generated method stub

	}

	@Override
	@WebMethod(action = "urn:#recordSamples")
	@RequestWrapper(localName = "recordSamplesIn", targetNamespace = "http://developers.google.com/gdgfirenze/ns/service")
	@ResponseWrapper(localName = "recordSamplesOut", targetNamespace = "http://developers.google.com/gdgfirenze/ns/service")
	public
	void recordSamples(@WebParam(name = "sample") List<AbstractSample> samples) {
		// TODO Auto-generated method stub

	}

}

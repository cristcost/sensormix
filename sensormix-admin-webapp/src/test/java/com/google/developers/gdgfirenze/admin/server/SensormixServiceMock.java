package com.google.developers.gdgfirenze.admin.server;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;

import com.google.developers.gdgfirenze.admin.client.service.GwtSensormixService;
import com.google.developers.gdgfirenze.dataservice.SensormixServiceJpaImpl;
import com.google.developers.gdgfirenze.model.AbstractSample;
import com.google.developers.gdgfirenze.model.SampleReport;
import com.google.developers.gdgfirenze.model.Sensor;
import com.google.developers.gdgfirenze.service.SensormixService;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class SensormixServiceMock extends RemoteServiceServlet implements
		GwtSensormixService {

	private EntityManagerFactory emf;
	private String jpaPersistenceUnitName = "sm_mysql_db_test";
	private SensormixServiceJpaImpl sensormixServiceJpaImpl;

	@Override
	public long countSamples(String sensorId, String sampleType, Date from,
			Date to) {
		return getService().countSamples(sensorId, sampleType, from, to);
	}

	@Override
	public void destroy() {
		super.destroy();
		emf.close();
	}

	@Override
	public SampleReport getSampleReport(String sensorId, String sampleType,
			Date from, Date to) {
		return getService().getSampleReport(sensorId, sampleType, from, to);
	}

	@Override
	public List<AbstractSample> getSamples(String sensorId, String sampleType,
			Date from, Date to, Long limitFrom, Long limitCount) {
		return getService().getSamples(sensorId, sampleType, from, to,
				limitFrom, limitCount);
	}

	@Override
	public List<Sensor> getSensors(List<String> sensorIds, Date from, Date to) {
		return getService().getSensors(sensorIds, from, to);
	}

	private SensormixService getService() {
		return sensormixServiceJpaImpl;
	}

	@Override
	public void init() throws ServletException {
		super.init();
		emf = Persistence.createEntityManagerFactory(jpaPersistenceUnitName);
		sensormixServiceJpaImpl = new SensormixServiceJpaImpl();
		sensormixServiceJpaImpl.setEntityManagerFactory(emf);
	}

	@Override
	public List<String> listSamplesTypes() {
		return getService().listSamplesTypes();
	}

	@Override
	public List<String> listSensorsIds() {
		return getService().listSensorsIds();
	}

	@Override
	public void recordSamples(List<AbstractSample> samples) {
		getService().recordSamples(samples);
	}

	@Override
	public void registerSensor(Sensor sensor) {
		getService().registerSensor(sensor);
	}

}

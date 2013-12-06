package com.google.developers.gdgfirenze.admin.server;

import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.ServletException;

import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.osgi.util.tracker.ServiceTracker;

import com.google.developers.gdgfirenze.admin.client.service.GwtSensormixService;
import com.google.developers.gdgfirenze.model.AbstractSample;
import com.google.developers.gdgfirenze.model.SampleReport;
import com.google.developers.gdgfirenze.model.Sensor;
import com.google.developers.gdgfirenze.service.SensormixService;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class SensormixServiceProxy extends RemoteServiceServlet implements
		GwtSensormixService {

	private static final Logger logger = Logger
			.getLogger(SensormixServiceProxy.class.getName());

	private ServiceTracker tracker = null;

	@Override
	public long countSamples(String sensorId, String sampleType, Date from,
			Date to) {
		return getService().countSamples(sensorId, sampleType, from, to);
	}

	@Override
	public void destroy() {
		super.destroy();
		if (tracker != null) {
			tracker.close();
		}
	}

	@Override
	public SampleReport getSampleReport(String sensorId, String sampleType,
			Date from, Date to) {
		return getService().getSampleReport(sensorId, sampleType, from, to);
	}

	@Override
	public List<AbstractSample> getSamples(String sensorId, String sampleType,
			Date from, Date to, Long limitFrom, Long limitCount) {
		List<AbstractSample> samples = getService().getSamples(sensorId,
				sampleType, from, to, limitFrom, limitCount);
		if (samples != null) {
			logger.info("Sending " + samples.size() + " samples to GWT");
		} else {
			logger.warning("Samples are null!");
		}
		return samples;
	}

	@Override
	public List<Sensor> getSensors(List<String> sensorIds, Date from, Date to) {
		List<Sensor> sensors = getService().getSensors(sensorIds, from, to);
		if (sensors != null) {
			logger.info("Sending " + sensors.size() + " sensor to GWT");
		} else {
			logger.warning("Sensors are null!");
		}
		return sensors;
	}

	private SensormixService getService() {
		try {
			return (SensormixService) tracker.waitForService(10000);
		} catch (InterruptedException e) {
			throw new RuntimeException(
					"Error accessing OSGi service for SensormixService", e);
		}
	}

	@Override
	public void init() throws ServletException {
		super.init();

		BundleContext context = FrameworkUtil.getBundle(this.getClass())
				.getBundleContext();
		tracker = new ServiceTracker(context, SensormixService.class.getName(),
				null);
		tracker.open();
	}

	@Override
	public List<String> listSamplesTypes() {
		return getService().listSamplesTypes();
	}

	@Override
	public List<String> listSensorsIds() {
		List<String> listSensorsIds = getService().listSensorsIds();
		if (listSensorsIds != null) {
			logger.info("Sending " + listSensorsIds.size()
					+ " sensor ids to GWT");
		} else {
			logger.warning("Sensor IDs are null!");
		}
		return listSensorsIds;
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

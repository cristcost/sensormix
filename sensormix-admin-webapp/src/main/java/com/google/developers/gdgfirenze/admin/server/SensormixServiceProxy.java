package com.google.developers.gdgfirenze.admin.server;

import com.google.developers.gdgfirenze.model.AbstractSample;
import com.google.developers.gdgfirenze.model.SampleReport;
import com.google.developers.gdgfirenze.model.Sensor;
import com.google.developers.gdgfirenze.service.SensormixService;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.osgi.util.tracker.ServiceTracker;

import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class SensormixServiceProxy extends RemoteServiceServlet implements SensormixService {

  private ServiceTracker tracker = null;

  @Override
  public void init() throws ServletException {
    super.init();

    BundleContext context = FrameworkUtil.getBundle(this.getClass()).getBundleContext();
    tracker = new ServiceTracker(context, SensormixService.class.getName(), null);
    tracker.open();
  }

  @Override
  public void destroy() {
    super.destroy();
    if (tracker != null) {
      tracker.close();
    }
  }

  private SensormixService getService() {
    try {
      return (SensormixService) tracker.waitForService(10000);
    } catch (InterruptedException e) {
      throw new RuntimeException("Error accessing OSGi service for SensormixService", e);
    }
  }

  @Override
  public List<String> listSensorsIds() {
    return getService().listSensorsIds();
  }

  @Override
  public List<String> listSamplesTypes() {
    return getService().listSamplesTypes();
  }

  @Override
  public List<AbstractSample> getSamples(String sensorId, String sampleType, Date from, Date to) {
    return getService().getSamples(sensorId, sampleType, from, to);
  }

  @Override
  public long countSamples(String sensorId, String sampleType, Date from, Date to) {
    return getService().countSamples(sensorId, sampleType, from, to);
  }

  @Override
  public SampleReport getSampleReport(String sensorId, String sampleType, Date from, Date to) {
    return getService().getSampleReport(sensorId, sampleType, from, to);
  }

  @Override
  public List<Sensor> getSensors(List<String> sensorIds, Date from, Date to) {
    return getService().getSensors(sensorIds, from, to);
  }

  @Override
  public void registerSensor(Sensor sensor) {
    getService().registerSensor(sensor);
  }

  @Override
  public void recordSamples(List<AbstractSample> samples) {
    getService().recordSamples(samples);
  }

}

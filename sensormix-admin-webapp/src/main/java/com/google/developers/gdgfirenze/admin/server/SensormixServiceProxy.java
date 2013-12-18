/*
 * Copyright 2013, Cristiano Costantini, Giuseppe Gerla, Michele Ficarra, Sergio Ciampi, Stefano
 * Cigheri.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
package com.google.developers.gdgfirenze.admin.server;

import com.google.developers.gdgfirenze.admin.client.service.GwtSensormixService;
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
import java.util.logging.Logger;

import javax.servlet.ServletException;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class SensormixServiceProxy extends RemoteServiceServlet implements GwtSensormixService {

  /** The Constant logger. */
  private static final Logger logger = Logger.getLogger(SensormixServiceProxy.class.getName());

  /** The tracker. */
  private ServiceTracker tracker = null;

  /*
   * (non-Javadoc)
   * 
   * @see com.google.developers.gdgfirenze.service.SensormixService#countSamples(java.lang.String,
   * java.lang.String, java.util.Date, java.util.Date)
   */
  @Override
  public long countSamples(String sensorId, String sampleType, Date from, Date to) {
    return getService().countSamples(sensorId, sampleType, from, to);
  }

  /*
   * (non-Javadoc)
   * 
   * @see javax.servlet.GenericServlet#destroy()
   */
  @Override
  public void destroy() {
    super.destroy();
    if (tracker != null) {
      tracker.close();
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * com.google.developers.gdgfirenze.service.SensormixService#getSampleReport(java.lang.String,
   * java.lang.String, java.util.Date, java.util.Date)
   */
  @Override
  public SampleReport getSampleReport(String sensorId, String sampleType, Date from, Date to) {
    return getService().getSampleReport(sensorId, sampleType, from, to);
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.google.developers.gdgfirenze.service.SensormixService#getSamples(java.lang.String,
   * java.lang.String, java.util.Date, java.util.Date, java.lang.Long, java.lang.Long)
   */
  @Override
  public List<AbstractSample> getSamples(String sensorId, String sampleType, Date from, Date to,
      Long limitFrom, Long limitCount) {
    final List<AbstractSample> samples = getService().getSamples(sensorId, sampleType, from, to,
        limitFrom, limitCount);
    if (samples != null) {
      logger.info("Sending " + samples.size() + " samples to GWT");
    } else {
      logger.warning("Samples are null!");
    }
    return samples;
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.google.developers.gdgfirenze.service.SensormixService#getSensors(java.util.List,
   * java.util.Date, java.util.Date)
   */
  @Override
  public List<Sensor> getSensors(List<String> sensorIds, Date from, Date to) {
    final List<Sensor> sensors = getService().getSensors(sensorIds, from, to);
    if (sensors != null) {
      logger.info("Sending " + sensors.size() + " sensor to GWT");
    } else {
      logger.warning("Sensors are null!");
    }
    return sensors;
  }

  /*
   * (non-Javadoc)
   * 
   * @see javax.servlet.GenericServlet#init()
   */
  @Override
  public void init() throws ServletException {
    super.init();

    final BundleContext context = FrameworkUtil.getBundle(this.getClass()).getBundleContext();
    tracker = new ServiceTracker(context, SensormixService.class.getName(), null);
    tracker.open();
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.google.developers.gdgfirenze.service.SensormixService#listSamplesTypes()
   */
  @Override
  public List<String> listSamplesTypes() {
    return getService().listSamplesTypes();
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.google.developers.gdgfirenze.service.SensormixService#listSensorsIds()
   */
  @Override
  public List<String> listSensorsIds() {
    final List<String> listSensorsIds = getService().listSensorsIds();
    if (listSensorsIds != null) {
      logger.info("Sending " + listSensorsIds.size() + " sensor ids to GWT");
    } else {
      logger.warning("Sensor IDs are null!");
    }
    return listSensorsIds;
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.google.developers.gdgfirenze.service.SensormixService#recordSamples(java.util.List)
   */
  @Override
  public void recordSamples(List<AbstractSample> samples) {
    getService().recordSamples(samples);
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * com.google.developers.gdgfirenze.service.SensormixService#registerSensor(com.google.developers
   * .gdgfirenze.model.Sensor)
   */
  @Override
  public void registerSensor(Sensor sensor) {
    getService().registerSensor(sensor);
  }

  /**
   * Gets the service.
   * 
   * @return the service
   */
  private SensormixService getService() {
    try {
      return (SensormixService) tracker.waitForService(10000);
    } catch (InterruptedException e) {
      throw new RuntimeException("Error accessing OSGi service for SensormixService", e);
    }
  }

}

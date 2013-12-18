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
package com.google.developers.gdgfirenze.admin.client.service;

import com.google.developers.gdgfirenze.model.AbstractSample;
import com.google.developers.gdgfirenze.model.SampleReport;
import com.google.developers.gdgfirenze.model.Sensor;

import com.google.gwt.user.client.rpc.AsyncCallback;

import java.util.Date;
import java.util.List;

/**
 * The Interface GwtSensormixServiceAsync.
 */
public interface GwtSensormixServiceAsync {

  /**
   * Count samples.
   * 
   * @param sensorId
   *          the sensor id
   * @param sampleType
   *          the sample type
   * @param from
   *          the from
   * @param to
   *          the to
   * @param callback
   *          the callback
   */
  void countSamples(String sensorId, String sampleType, Date from, Date to,
      AsyncCallback<Long> callback);

  /**
   * Gets the sample report.
   * 
   * @param sensorId
   *          the sensor id
   * @param sampleType
   *          the sample type
   * @param from
   *          the from
   * @param to
   *          the to
   * @param callback
   *          the callback
   * @return the sample report
   */
  void getSampleReport(String sensorId, String sampleType, Date from, Date to,
      AsyncCallback<SampleReport> callback);

  /**
   * Gets the samples.
   * 
   * @param sensorId
   *          the sensor id
   * @param sampleType
   *          the sample type
   * @param from
   *          the from
   * @param to
   *          the to
   * @param limitFrom
   *          the limit from
   * @param limitCount
   *          the limit count
   * @param callback
   *          the callback
   * @return the samples
   */
  void getSamples(String sensorId, String sampleType, Date from, Date to, Long limitFrom,
      Long limitCount, AsyncCallback<List<AbstractSample>> callback);

  /**
   * Gets the sensors.
   * 
   * @param sensorIds
   *          the sensor ids
   * @param from
   *          the from
   * @param to
   *          the to
   * @param callback
   *          the callback
   * @return the sensors
   */
  void getSensors(List<String> sensorIds, Date from, Date to, AsyncCallback<List<Sensor>> callback);

  /**
   * List samples types.
   * 
   * @param callback
   *          the callback
   */
  void listSamplesTypes(AsyncCallback<List<String>> callback);

  /**
   * List sensors ids.
   * 
   * @param callback
   *          the callback
   */
  void listSensorsIds(AsyncCallback<List<String>> callback);

  /**
   * Record samples.
   * 
   * @param samples
   *          the samples
   * @param callback
   *          the callback
   */
  void recordSamples(List<AbstractSample> samples, AsyncCallback<Void> callback);

  /**
   * Register sensor.
   * 
   * @param sensor
   *          the sensor
   * @param callback
   *          the callback
   */
  void registerSensor(Sensor sensor, AsyncCallback<Void> callback);

}

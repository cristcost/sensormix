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
package com.google.developers.gdgfirenze.service;

import com.google.developers.gdgfirenze.model.AbstractSample;
import com.google.developers.gdgfirenze.model.SampleReport;
import com.google.developers.gdgfirenze.model.Sensor;

import java.util.Date;
import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.ParameterStyle;
import javax.jws.soap.SOAPBinding.Style;
import javax.jws.soap.SOAPBinding.Use;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * The Interface SensormixService.
 * 
 * This is the main class that represents the service managing the samples and sensors data. It is
 * designed as a SOA service (oriented on the business functionalities and coarse grained) and it is
 * annotated with JaxWS annotations so to be easily exposed as a Web Service.
 * 
 * It abstracts access to data, so that no low level detail of the implementation (such as database
 * details) is visible by service consumers: no java imports except of JaxB and JaxWS annotations
 * used.
 */
@WebService(name = "SensormixService",
    targetNamespace = "http://developers.google.com/gdgfirenze/ns/service")
@SOAPBinding(parameterStyle = ParameterStyle.WRAPPED, style = Style.DOCUMENT, use = Use.LITERAL)
public interface SensormixService {

  /**
   * List sensors ids.
   * 
   * @return the list
   */
  @WebMethod(action = "urn:#listSensorsIds")
  @RequestWrapper(localName = "listSensorsIdsIn",
      targetNamespace = "http://developers.google.com/gdgfirenze/ns/service")
  @ResponseWrapper(localName = "listSensorsIdsOut",
      targetNamespace = "http://developers.google.com/gdgfirenze/ns/service")
  @WebResult(name = "sensorId")
  List<String> listSensorsIds();

  /**
   * List samples types.
   * 
   * @return the list
   */
  @WebMethod(action = "urn:#listSamplesTypes")
  @RequestWrapper(localName = "listSamplesTypesIn",
      targetNamespace = "http://developers.google.com/gdgfirenze/ns/service")
  @ResponseWrapper(localName = "listSamplesTypesOut",
      targetNamespace = "http://developers.google.com/gdgfirenze/ns/service")
  @WebResult(name = "sampleType")
  List<String> listSamplesTypes();

  /**
   * Gets the samples.
   * 
   * @param sensorId the sensor id
   * @param sampleType the sample type
   * @param from the from
   * @param to the to
   * @param limitFrom the limit from
   * @param limitCount the limit count
   * @return the samples
   */
  @WebMethod(action = "urn:#getSamples")
  @RequestWrapper(localName = "getSamplesIn",
      targetNamespace = "http://developers.google.com/gdgfirenze/ns/service")
  @ResponseWrapper(localName = "getSamplesOut",
      targetNamespace = "http://developers.google.com/gdgfirenze/ns/service")
  @WebResult(name = "sample")
  List<AbstractSample> getSamples(@WebParam(name = "sensorId") String sensorId, @WebParam(
      name = "sampleType") String sampleType, @WebParam(name = "from") Date from, @WebParam(
      name = "to") Date to, @WebParam(name = "limitFrom") Long limitFrom, @WebParam(
      name = "limitCount") Long limitCount);

  /**
   * Count samples.
   * 
   * @param sensorId the sensor id
   * @param sampleType the sample type
   * @param from the from
   * @param to the to
   * @return the long
   */
  @WebMethod(action = "urn:#countSamples")
  @RequestWrapper(localName = "countSamplesIn",
      targetNamespace = "http://developers.google.com/gdgfirenze/ns/service")
  @ResponseWrapper(localName = "countSamplesOut",
      targetNamespace = "http://developers.google.com/gdgfirenze/ns/service")
  @WebResult(name = "sampleCount")
  long countSamples(@WebParam(name = "sensorId") String sensorId,
      @WebParam(name = "sampleType") String sampleType, @WebParam(name = "from") Date from,
      @WebParam(name = "to") Date to);

  /**
   * Gets the sample report.
   * 
   * @param sensorId the sensor id
   * @param sampleType the sample type
   * @param from the from
   * @param to the to
   * @return the sample report
   */
  @WebMethod(action = "urn:#getSampleReport")
  @RequestWrapper(localName = "getSampleReportIn",
      targetNamespace = "http://developers.google.com/gdgfirenze/ns/service")
  @ResponseWrapper(localName = "getSampleReportOut",
      targetNamespace = "http://developers.google.com/gdgfirenze/ns/service")
  @WebResult(name = "sampleReport")
  SampleReport getSampleReport(@WebParam(name = "sensorId") String sensorId, @WebParam(
      name = "sampleType") String sampleType, @WebParam(name = "from") Date from, @WebParam(
      name = "to") Date to);

  /**
   * Gets the sensors.
   * 
   * @param sensorIds the sensor ids
   * @param from the from
   * @param to the to
   * @return the sensors
   */
  @WebMethod(action = "urn:#getSensors")
  @RequestWrapper(localName = "getSensorsIn",
      targetNamespace = "http://developers.google.com/gdgfirenze/ns/service")
  @ResponseWrapper(localName = "getSensorsOut",
      targetNamespace = "http://developers.google.com/gdgfirenze/ns/service")
  @WebResult(name = "sensor")
  List<Sensor> getSensors(@WebParam(name = "sensorId") List<String> sensorIds, @WebParam(
      name = "from") Date from, @WebParam(name = "to") Date to);

  /**
   * Register sensor.
   * 
   * @param sensor the sensor
   */
  @WebMethod(action = "urn:#registerSensor")
  @RequestWrapper(localName = "registerSensorIn",
      targetNamespace = "http://developers.google.com/gdgfirenze/ns/service")
  @ResponseWrapper(localName = "registerSensorOut",
      targetNamespace = "http://developers.google.com/gdgfirenze/ns/service")
  void registerSensor(@WebParam(name = "sensor") Sensor sensor);

  /**
   * Record samples.
   * 
   * @param samples the samples
   */
  @WebMethod(action = "urn:#recordSamples")
  @RequestWrapper(localName = "recordSamplesIn",
      targetNamespace = "http://developers.google.com/gdgfirenze/ns/service")
  @ResponseWrapper(localName = "recordSamplesOut",
      targetNamespace = "http://developers.google.com/gdgfirenze/ns/service")
  void recordSamples(@WebParam(name = "sample") List<AbstractSample> samples);

}

package com.google.developers.gdgfirenze.service;

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

import com.google.developers.gdgfirenze.model.AbstractSample;
import com.google.developers.gdgfirenze.model.Sensor;

@WebService(name = "SensormixService",
    targetNamespace = "http://developers.google.com/gdgfirenze/ns/service")
@SOAPBinding(parameterStyle = ParameterStyle.WRAPPED, style = Style.DOCUMENT, use = Use.LITERAL)
public interface SensormixService {

  @WebMethod(action = "urn:#listSensorsIds")
  @RequestWrapper(localName = "listSensorsIdsIn",
      targetNamespace = "http://developers.google.com/gdgfirenze/ns/service")
  @ResponseWrapper(localName = "listSensorsIdsOut",
      targetNamespace = "http://developers.google.com/gdgfirenze/ns/service")
  @WebResult(name = "sensorId")
  List<String> listSensorsIds();
  
  @WebMethod(action = "urn:#listSamplesTypes")
  @RequestWrapper(localName = "listSamplesTypesIn", targetNamespace = "http://developers.google.com/gdgfirenze/ns/service")
  @ResponseWrapper(localName = "listSamplesTypesOut", targetNamespace = "http://developers.google.com/gdgfirenze/ns/service")
  @WebResult(name = "sampleType")
  List<String> listSamplesTypes();

  @WebMethod(action = "urn:#getSamples")
  @RequestWrapper(localName = "getSamplesIn",
      targetNamespace = "http://developers.google.com/gdgfirenze/ns/service")
  @ResponseWrapper(localName = "getSamplesOut",
      targetNamespace = "http://developers.google.com/gdgfirenze/ns/service")
  @WebResult(name = "sample")
  List<AbstractSample> getSamples(@WebParam(name = "sensorId") String sensorId, @WebParam(
      name = "sampleType") String sampleType, @WebParam(name = "from") Date from, @WebParam(
      name = "to") Date to);

  @WebMethod(action = "urn:#countSamples")
  @RequestWrapper(localName = "countSamplesIn",
      targetNamespace = "http://developers.google.com/gdgfirenze/ns/service")
  @ResponseWrapper(localName = "countSamplesOut",
      targetNamespace = "http://developers.google.com/gdgfirenze/ns/service")
  @WebResult(name = "sampleCount")
  long countSamples(@WebParam(name = "sensorId") String sensorId,
      @WebParam(name = "sampleType") String sampleType, @WebParam(name = "from") Date from,
      @WebParam(name = "to") Date to);

  @WebMethod(action = "urn:#getSampleReport")
  @RequestWrapper(localName = "getSampleReportIn",
      targetNamespace = "http://developers.google.com/gdgfirenze/ns/service")
  @ResponseWrapper(localName = "getSampleReportOut",
      targetNamespace = "http://developers.google.com/gdgfirenze/ns/service")
  @WebResult(name = "sampleReport")
  SampleReport getSampleReport(@WebParam(name = "sensorId") String sensorId,
      @WebParam(name = "sampleType") String sampleType, @WebParam(name = "from") Date from,
      @WebParam(name = "to") Date to);
    
  @WebMethod(action = "urn:#getSensors")
  @RequestWrapper(localName = "getSensorsIn", targetNamespace = "http://developers.google.com/gdgfirenze/ns/service")
  @ResponseWrapper(localName = "getSensorsOut", targetNamespace = "http://developers.google.com/gdgfirenze/ns/service")
  @WebResult(name = "sensor")
  List<Sensor> getSensors(
		  @WebParam(name = "sensorId") List<String> sensorIds,
		  @WebParam(name = "from") Date from,
	      @WebParam(name = "to") Date to);

  @WebMethod(action = "urn:#registerSensor")
  @RequestWrapper(localName = "registerSensorIn",
      targetNamespace = "http://developers.google.com/gdgfirenze/ns/service")
  @ResponseWrapper(localName = "registerSensorOut",
      targetNamespace = "http://developers.google.com/gdgfirenze/ns/service")
  void registerSensor(@WebParam(name = "sensor") Sensor sensor);

  @WebMethod(action = "urn:#recordSamples")
  @RequestWrapper(localName = "recordSamplesIn",
      targetNamespace = "http://developers.google.com/gdgfirenze/ns/service")
  @ResponseWrapper(localName = "recordSamplesOut",
      targetNamespace = "http://developers.google.com/gdgfirenze/ns/service")
  void recordSamples(@WebParam(name = "sample") List<AbstractSample> samples);

}

package com.google.developers.gdgfirenze.model;

import java.util.Date;
import java.util.List;

public interface SensormixService {

  List<AbstractSample> getSamples(String sensorId, String sampleType, Date from, Date to);

  List<String> listSensorsIds();

  List<Sensor> getSensors(List<String> sensorIds);

  void registerSensor(Sensor sensor);

  void recordSamples(List<AbstractSample> samples);

}

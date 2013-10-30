package com.google.developers.gdgfirenze.integration;

import com.google.developers.gdgfirenze.model.AbstractSample;
import com.google.developers.gdgfirenze.model.Sensor;
import com.google.developers.gdgfirenze.service.SampleReport;
import com.google.developers.gdgfirenze.service.SensormixService;

import java.util.Date;
import java.util.List;

public class SensormixServiceMock implements SensormixService {

  @Override
  public List<String> listSensorsIds() {
    throw new UnsupportedOperationException();
  }

  @Override
  public List<String> listSamplesTypes() {
    throw new UnsupportedOperationException();
  }

  @Override
  public List<AbstractSample> getSamples(String sensorId, String sampleType, Date from, Date to) {
    throw new UnsupportedOperationException();
  }

  @Override
  public long countSamples(String sensorId, String sampleType, Date from, Date to) {
    throw new UnsupportedOperationException();
  }

  @Override
  public SampleReport getSampleReport(String sensorId, String sampleType, Date from, Date to) {
    throw new UnsupportedOperationException();
  }

  @Override
  public List<Sensor> getSensors(List<String> sensorIds, Date from, Date to) {
    throw new UnsupportedOperationException();
  }

  @Override
  public void registerSensor(Sensor sensor) {
    throw new UnsupportedOperationException();
  }

  @Override
  public void recordSamples(List<AbstractSample> samples) {
    if (samples != null) {
      System.out.println("recordSamples: Mock Service has received " + samples.size() + " samples");
    } else {
      System.out.println("recordSamples: Mock Service has received null");
    }
  }

}

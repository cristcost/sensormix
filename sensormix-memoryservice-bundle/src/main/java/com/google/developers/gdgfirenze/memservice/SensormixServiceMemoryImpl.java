package com.google.developers.gdgfirenze.memservice;

import com.google.developers.gdgfirenze.model.AbstractSample;
import com.google.developers.gdgfirenze.model.DailySampleReport;
import com.google.developers.gdgfirenze.model.SampleReport;
import com.google.developers.gdgfirenze.model.Sensor;
import com.google.developers.gdgfirenze.service.SensormixService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

public class SensormixServiceMemoryImpl implements SensormixService {

  private static Logger logger = Logger.getLogger(SensormixServiceMemoryImpl.class.getName());

  // TODO: implement these data structures
  List<AbstractSample> samples; // straight list of samples, change if required
  Map<String, Sensor> sensors; // map of sensor with id as key, change if required

  @Override
  public List<String> listSensorsIds() {
    List<String> ret = new ArrayList<String>();
    // TODO: cycle all sensors, take the id and return
    return ret;
  }

  @Override
  public List<AbstractSample> getSamples(String sensorId, String sampleType, Date from, Date to,
      Long limitFrom, Long limitCount) {

    List<AbstractSample> ret = new ArrayList<>();

    // TODO: cycle all samples and add to ret if correspond do conditions
    // NB:
    // null sensorId => any sensor
    // null sampleType => any sample type
    // limitCount != null => stop when ret.size() >= limitCount
    // limitFrom != null => skip the first limitFrom items
    return ret;
  }

  @Override
  public long countSamples(String sensorId, String sampleType, Date from, Date to) {

    long ret = 0;

    // TODO: Cycle over samples and increment ret when condition is met
    // NB. if all params are null, return samples.size()

    return ret;
  }

  @Override
  public SampleReport getSampleReport(String sensorId, String sampleType, Date from, Date to) {

    SampleReport sr = new SampleReport();
    sr.setSensorId(sensorId);
    sr.setSampleType(sampleType);
    sr.setDailySampleReports(new ArrayList<DailySampleReport>());

    // TODO: Ignore for the moment

    return sr;
  }

  @Override
  public List<Sensor> getSensors(List<String> sensorIds, Date from, Date to) {
    List<Sensor> ret = new ArrayList<Sensor>();

    // TODO: cycle all sensors and add to ret if correspond do conditions
    // NB: why this method has a from and a to ????
    return ret;
  }

  @Override
  public void registerSensor(Sensor sensor) {
    // TODO: add the sensor to sensors
  }

  @Override
  public void recordSamples(List<AbstractSample> samples) {
    // TODO: add samples to this.samples
  }

  @Override
  public List<String> listSamplesTypes() {
    List<String> ret = new ArrayList<String>();
    // TODO: ignore for now
    return ret;
  }
}

package com.google.developers.gdgfirenze.integration;

import com.google.developers.gdgfirenze.model.AbstractSample;
import com.google.developers.gdgfirenze.model.NumericValueSample;
import com.google.developers.gdgfirenze.model.PositionSample;
import com.google.developers.gdgfirenze.model.SampleReport;
import com.google.developers.gdgfirenze.model.Sensor;
import com.google.developers.gdgfirenze.model.WifiSignalSample;
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
      for (AbstractSample currentSample : samples) {
    	  if (currentSample instanceof NumericValueSample) {
    		  System.out.println(currentSample.getSensorId());
    		  System.out.println(currentSample.getTime());
    		  System.out.println(currentSample.getType());
    		  System.out.println(((NumericValueSample) currentSample).getValue());
    	  } else if (currentSample instanceof PositionSample) {
    		  System.out.println(currentSample.getSensorId());
    		  System.out.println(currentSample.getTime());
    		  System.out.println(currentSample.getType());
    		  System.out.println(((PositionSample) currentSample).getAccuracy());
    		  System.out.println(((PositionSample) currentSample).getAlt());
    		  System.out.println(((PositionSample) currentSample).getBearing());
    		  System.out.println(((PositionSample) currentSample).getLat());
    		  System.out.println(((PositionSample) currentSample).getLng());
    		  System.out.println(((PositionSample) currentSample).getSpeed());
    	  } else if (currentSample instanceof WifiSignalSample) {
    		  System.out.println(currentSample.getSensorId());
    		  System.out.println(currentSample.getTime());
    		  System.out.println(currentSample.getType());
    		  System.out.println(((WifiSignalSample) currentSample).getBssid());
    		  System.out.println(((WifiSignalSample) currentSample).getCapabilities());
    		  System.out.println(((WifiSignalSample) currentSample).getLevel());
    		  System.out.println(((WifiSignalSample) currentSample).getFrequency());
    		  System.out.println(((WifiSignalSample) currentSample).getSsid());
    	  } else {
    		  
    	  }
      }
      
    } else {
      System.out.println("recordSamples: Mock Service has received null");
    }
  }

@Override
public List<AbstractSample> getSamples(String sensorId, String sampleType,
		Date from, Date to, Long limitFrom, Long limitCount) {
    throw new UnsupportedOperationException();
}

}

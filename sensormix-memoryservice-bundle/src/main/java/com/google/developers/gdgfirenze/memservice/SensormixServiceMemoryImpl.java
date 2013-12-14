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
package com.google.developers.gdgfirenze.memservice;

import com.google.developers.gdgfirenze.model.AbstractSample;
import com.google.developers.gdgfirenze.model.DailySampleReport;
import com.google.developers.gdgfirenze.model.SampleReport;
import com.google.developers.gdgfirenze.model.Sensor;
import com.google.developers.gdgfirenze.osgi.SensormixAdminInterface;
import com.google.developers.gdgfirenze.service.SensormixService;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The Class SensormixServiceMemoryImpl. This class implements the methods from
 * the SensormixService interface in order to execute CRUD operation on main
 * data structures from the data model project: samples and sensors. This
 * implementation stores all the data in the volatile memory instead of physical
 * memory. When the service is stopped, all the data are lost.
 */
public class SensormixServiceMemoryImpl implements SensormixService, SensormixAdminInterface {

  /**
   * The class logger.
   */
  private static Logger logger = Logger.getLogger(SensormixServiceMemoryImpl.class.getName());

  /**
   * Data structure to save the samples.
   */
  private List<AbstractSample> samples = new ArrayList<AbstractSample>();

  /**
   * Data structure to save the sensors.
   */
  private Map<String, Sensor> sensors = new HashMap<String, Sensor>();

  /**
   * Data structure to save the samples based on their type.
   */
  private Map<String, List<AbstractSample>> samplesForType =
      new HashMap<String, List<AbstractSample>>();

  /**
   * Data structure to save the samples based on their date.
   */
  private Map<String, List<AbstractSample>> samplesForDate =
      new HashMap<String, List<AbstractSample>>();

  /**
   * Data structure to save the samples based on their sensorId.
   */
  private Map<String, List<AbstractSample>> samplesForSensorId =
      new HashMap<String, List<AbstractSample>>();

  /**
   * Date format.
   */
  private DateFormat df = new SimpleDateFormat("MM-dd-yyyy");

  private boolean inMaintenace;

  /**
   * Count the samples based on filter criteria.
   * 
   * @param sensorId the sensor id
   * @param sampleType the sample type
   * @param from date from
   * @param to date to
   * @return the long number of samples
   */
  @Override
  public long countSamples(String sensorId, String sampleType, Date from, Date to) {
    long ret = 0;
    if (sensorId == null && sampleType == null && from == null && to == null) {
      ret = samples.size();
    } else {
      for (AbstractSample currentSample : samples) {
        if (!isSampleToFilter(currentSample, sensorId, sampleType, from, to)) {
          ret++;
        }
      }
    }
    return ret;
  }

  /**
   * Get the sample report based on filter criteria.
   * 
   * @param sensorId the sensor id
   * @param sampleType the sample type
   * @param from date from
   * @param to date to
   * @return the sample report
   */
  @Override
  public SampleReport getSampleReport(String sensorId, String sampleType, Date from, Date to) {

    SampleReport sr = new SampleReport();
    sr.setSensorId(sensorId);
    sr.setSampleType(sampleType);
    sr.setDailySampleReports(new ArrayList<DailySampleReport>());

    List<AbstractSample> filtered = new ArrayList<AbstractSample>();
    filtered.addAll(samples);
    if (sensorId != null) {
      List<AbstractSample> filteredById = samplesForSensorId.get(sensorId);
      if (filteredById != null) {
        filtered.retainAll(filteredById);
      }
    }
    if (sampleType != null) {
      List<AbstractSample> filteredByType = samplesForType.get(sampleType);
      if (filteredByType != null) {
        filtered.retainAll(filteredByType);
      }
    }
    if (from != null && to != null) {
      if (!from.before(to)) {
        logger.log(Level.WARNING, "Error from date must be before to date");
      } else {
        Calendar start = Calendar.getInstance();
        Calendar end = Calendar.getInstance();
        start.setTime(from);
        end.setTime(to);

        while (start.before(end)) {
          Date internalStart = start.getTime();
          start.add(Calendar.DAY_OF_MONTH, 1);
          List<AbstractSample> appoList = new ArrayList<AbstractSample>();
          appoList.addAll(filtered);

          appoList.retainAll(samplesForDate.get(df.format(internalStart)));

          DailySampleReport dsr = new DailySampleReport();
          dsr.setDate(internalStart);
          dsr.setSampleCount(appoList.size());
          sr.getDailySampleReports().add(dsr);
        }
      }
    }

    return sr;
  }

  /**
   * Get the samples based on filter criteria. Search criteria: null sensorId =>
   * any sensor null sampleType => any sample type limitCount != null => stop
   * when ret.size() >= limitCount limitFrom != null => skip the first limitFrom
   * items
   * 
   * @param sensorId the sensor id
   * @param sampleType the sample type
   * @param from date from
   * @param to date to
   * @param limitFrom the limit from
   * @param limitCount the limit count
   * @return the samples corresponding to the filter parameters
   */
  @Override
  public List<AbstractSample> getSamples(String sensorId, String sampleType, Date from, Date to,
      Long limitFrom, Long limitCount) {

    final List<AbstractSample> ret = new ArrayList<>();
    if (sensorId == null && sampleType == null && from == null && to == null && limitFrom == null
        && limitCount == null) {
      for (AbstractSample currentSample : samples) {
        ret.add(currentSample);
      }
    } else {
      for (AbstractSample currentSample : samples) {
        if (!isSampleToFilter(currentSample, sensorId, sampleType, from, to)) {
          if (limitCount != null && ret.size() >= limitCount) {
          } else {
            ret.add(currentSample);
          }
          // if (limitCount == null) {
          // ret.add(currentSample);
          // } else if (limitCount != null && ret.size() <=
          // limitCount) {
          // ret.add(currentSample);
          // }
        }
      }
    }
    return ret;
  }

  /**
   * Get the sensors based on filter criteria.
   * 
   * @param sensorIds the sensor ids
   * @param from date from
   * @param to date to
   * @return the sensors corresponding to the filter parameters
   */
  @Override
  public List<Sensor> getSensors(List<String> sensorIds, Date from, Date to) {
    final List<Sensor> ret = new ArrayList<Sensor>();
    if (sensorIds == null && from == null && to == null) {
      for (Sensor currentSensor : sensors.values()) {
        ret.add(currentSensor);
      }
    } else {
      if (sensorIds != null && sensorIds.size() > 0) {
        for (String currentSensorId : sensorIds) {
          if (sensors.containsKey(currentSensorId)) {
            Sensor currentSensor = sensors.get(currentSensorId);
            if (from != null && to != null) {
              if (currentSensor.getLastSeen().compareTo(from) >= 0
                  && currentSensor.getLastSeen().compareTo(to) <= 0) {
                ret.add(currentSensor);
              }
            } else if (from == null && to != null) {
              if (currentSensor.getLastSeen().compareTo(to) <= 0) {
                ret.add(currentSensor);
              }
            } else if (from != null && to == null) {
              if (currentSensor.getLastSeen().compareTo(from) >= 0) {
                ret.add(currentSensor);
              }
            } else {
              ret.add(currentSensor);
            }
          }
        }
      }
    }
    return ret;
  }

  /**
   * check if is in mainteinance.
   * 
   * @return true, if is in maintenance
   */
  @Override
  public boolean isInMaintenance() {
    return inMaintenace;
  }

  /**
   * List the samples based on type filtering.
   * 
   * @return the list of types of samples
   */
  @Override
  public List<String> listSamplesTypes() {
    final List<String> ret = new ArrayList<String>();
    ret.addAll(samplesForType.keySet());
    return ret;
  }

  /**
   * Lists all the sensor identifiers.
   * 
   * @return the list of sensor identifiers
   */
  @Override
  public List<String> listSensorsIds() {
    final List<String> ret = new ArrayList<String>();
    for (Sensor sensor : sensors.values()) {
      if (sensor.getId() != null) {
        ret.add(sensor.getId());
      }
    }
    return ret;
  }

  /**
   * Record the samples.
   * 
   * @param samplesToAdd the samples to add to the samples data structure
   */
  @Override
  public void recordSamples(List<AbstractSample> samplesToAdd) {
    if (!inMaintenace) {
      if (samplesToAdd != null && samplesToAdd.size() > 0) {
        final List<String> checkList = new ArrayList<String>();
        checkList.addAll(listSensorsIds());
        for (AbstractSample sample : samplesToAdd) {
          if (!checkList.contains(sample.getSensorId())) {
            Sensor s = new Sensor();
            s.setId(sample.getSensorId());
            s.setName("Unknown");
            s.setDescription("Unknown");
            s.setLastSeen(sample.getTime());

            registerSensor(s);
            checkList.add(new String(sample.getSensorId()));
          } else {
            List<String> sensorList = new ArrayList<String>();
            sensorList.add(sample.getSensorId());
            Sensor s = getSensors(sensorList, null, null).get(0);
            s.setLastSeen(sample.getTime());
          }

          samples.add(sample);

          // index for type
          List<AbstractSample> partialSamples = samplesForType.get(sample.getType());
          if (partialSamples == null) {
            partialSamples = new ArrayList<AbstractSample>();
            samplesForType.put(sample.getType(), partialSamples);
          }
          partialSamples.add(sample);

          // index for sensor
          partialSamples = samplesForSensorId.get(sample.getSensorId());
          if (partialSamples == null) {
            partialSamples = new ArrayList<AbstractSample>();
            samplesForSensorId.put(sample.getSensorId(), partialSamples);
          }
          partialSamples.add(sample);

          // index for date
          final String dateFormatted = df.format(sample.getTime());
          partialSamples = samplesForDate.get(dateFormatted);
          if (partialSamples == null) {
            partialSamples = new ArrayList<AbstractSample>();
            samplesForDate.put(dateFormatted, partialSamples);
          }
          partialSamples.add(sample);
        }
      }
    } else {
      logger.log(Level.INFO,
          "Cannot register samples because DataServiceMemoryImpl is in maintenace");
      throw new RuntimeException(
          "Cannot register samples because DataServiceMemoryImpl is in maintenace");
    }
  }

  /**
   * Register the sensors.
   * 
   * @param sensor the sensor that shall be added to the sensor data structure.
   */
  @Override
  public void registerSensor(Sensor sensor) {
    if (!inMaintenace) {
      boolean found = false;
      if (sensors != null && sensors.values() != null && sensors.values().size() > 0) {
        for (Sensor currentSensor : sensors.values()) {
          if (currentSensor.getId().equalsIgnoreCase(sensor.getId())) {
            found = true;
          }
        }
      }
      if (!found) {
        sensors.put(sensor.getId(), sensor);
      } else {
        final Sensor s = sensors.get(sensor.getId());
        s.setLastSeen(sensor.getLastSeen());
      }
    } else {
      logger.log(Level.INFO,
          "Cannot register sensor because DataServiceMemoryImpl is in maintenace");
      throw new RuntimeException(
          "Cannot register sensor because DataServiceMemoryImpl is in maintenace");
    }
  }

  /**
   * setter of the maintenance property.
   */
  @Override
  public void setInMaintenace(boolean value) {
    inMaintenace = value;
  }

  /**************************************/
  /* PRIVATE METHODS: for internal use. */
  /**************************************/

  /**
   * Method used internally to this class to manage filter parameters.
   * 
   * @param currentSample the current sample
   * @param sensorId the sensor id
   * @param sampleType the sample type
   * @param from the from
   * @param to the to
   * @return true, if is sample to filter
   */
  private boolean isSampleToFilter(AbstractSample currentSample, String sensorId,
      String sampleType, Date from, Date to) {
    boolean isToFilter = false;
    if (sensorId != null) {
      if (!currentSample.getSensorId().equalsIgnoreCase(sensorId)) {
        isToFilter = true;
      }
    }
    if (isToFilter == false && sampleType != null) {
      if (!currentSample.getType().equalsIgnoreCase(sampleType)) {
        isToFilter = true;
      }
    }
    if (isToFilter == false && from != null && to != null) {
      if (currentSample.getTime().compareTo(from) < 0 || currentSample.getTime().compareTo(to) > 0) {
        isToFilter = true;
      }
    } else if (isToFilter == false && from != null && to == null) {
      if (currentSample.getTime().compareTo(from) < 0) {
        isToFilter = true;
      }
    } else if (isToFilter == false && from == null && to != null) {
      if (currentSample.getTime().compareTo(to) > 0) {
        isToFilter = true;
      }
    }
    return isToFilter;
  }

}
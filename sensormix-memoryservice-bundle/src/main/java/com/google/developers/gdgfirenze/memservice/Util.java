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
import com.google.developers.gdgfirenze.model.NumericValueSample;
import com.google.developers.gdgfirenze.model.PositionSample;
import com.google.developers.gdgfirenze.model.Sensor;
import com.google.developers.gdgfirenze.model.StringValueSample;
import com.google.developers.gdgfirenze.model.WifiSignalSample;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * ObjectsSetter class.
 */
public class Util {

  /**
   * dateTimeFormat.
   */
  private static final DateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

  /**
   * dateFormat.
   */
  private static final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

  /**
   * timeFormat.
   */
  private static final DateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");

  /**
   * New date time.
   *
   * @param dateTimeString the date time string
   * @return the date
   * @throws ParseException the parse exception
   */
  public static Date newDateTime(String dateTimeString) throws ParseException {
    if (dateTimeString == null) {
      return null;
    }
    return dateTimeFormat.parse(dateTimeString);
  }

  /**
   * New date.
   *
   * @param dateString the date string
   * @return the date
   * @throws ParseException the parse exception
   */
  public static Date newDate(String dateString) throws ParseException {
    if (dateString == null) {
      return null;
    }
    return dateFormat.parse(dateString);
  }
  
  /**
   * New time.
   *
   * @param timeString the time string
   * @return the long
   * @throws ParseException the parse exception
   */
  public static Long newTime(String timeString) throws ParseException {
    if (timeString == null) {
      return null;
    }
    final Date parsedDate = timeFormat.parse(timeString);
    return parsedDate.getTime();
  }
  
  /**
   * New sensor.
   *
   * @param id the id
   * @param description the description
   * @param name the name
   * @param type the type
   * @param lat the lat
   * @param lng the lng
   * @param lastSeen the last seen
   * @return the sensor
   */
  public static Sensor newSensor(String id, String description, String name, String type, Double lat, Double lng, Date lastSeen) {
    final Sensor sensor = new Sensor();
    sensor.setId(id);
    sensor.setDescription(description);
    sensor.setName(name);
    sensor.setType(type);
    sensor.setLat(lat);
    sensor.setLng(lng);
    sensor.setLastSeen(lastSeen);
    return sensor;
  }
  
  /**
   * New samples.
   *
   * @param samples the samples
   * @return the list
   */
  public static List<AbstractSample> newSamples(AbstractSample... samples) {
    return Arrays.asList(samples);
  }

  /**
   * New numeric value sample.
   *
   * @param sensorId the sensor id
   * @param type the type
   * @param time the time
   * @param value the value
   * @return the numeric value sample
   */
  public static NumericValueSample newNumericValueSample(String sensorId, String type, Date time, Double value) {
    final NumericValueSample numericValueSample = new NumericValueSample();
    numericValueSample.setSensorId(sensorId);
    numericValueSample.setType(type);
    numericValueSample.setTime(time);
    numericValueSample.setValue(value);
    return numericValueSample;
  }
  
  /**
   * New position sample.
   *
   * @param sensorId the sensor id
   * @param type the type
   * @param time the time
   * @param lat the lat
   * @param lng the lng
   * @param alt the alt
   * @param accuracy the accuracy
   * @param bearing the bearing
   * @param speed the speed
   * @return the position sample
   */
  public static PositionSample newPositionSample(String sensorId, String type, Date time, Double lat, 
      Double lng, Double alt, Double accuracy, Double bearing, Double speed) {
    final PositionSample positionSample = new PositionSample();
    positionSample.setSensorId(sensorId);
    positionSample.setType(type);
    positionSample.setTime(time);
    positionSample.setLat(lat);
    positionSample.setLng(lng);
    positionSample.setAlt(alt);
    positionSample.setAccuracy(accuracy);
    positionSample.setBearing(bearing);
    positionSample.setSpeed(speed);
    return positionSample;
  }

  /**
   * New string value sample.
   *
   * @param sensorId the sensor id
   * @param type the type
   * @param time the time
   * @param value the value
   * @return the string value sample
   */
  public static StringValueSample newStringValueSample(String sensorId, String type, Date time, String value) {
    final StringValueSample stringValueSample = new StringValueSample();
    stringValueSample.setSensorId(sensorId);
    stringValueSample.setType(type);
    stringValueSample.setTime(time);
    stringValueSample.setValue(value);
    return stringValueSample;
  }

  /**
   * New wifi signal sample.
   *
   * @param sensorId the sensor id
   * @param type the type
   * @param time the time
   * @param ssid the ssid
   * @param bssid the bssid
   * @param capabilities the capabilities
   * @param frequency the frequency
   * @param level the level
   * @return the wifi signal sample
   */
  public static WifiSignalSample newWifiSignalSample(String sensorId, String type, Date time, 
      String ssid, String bssid, String capabilities, Double frequency, Double level) {
    final WifiSignalSample wifiSignalSample = new WifiSignalSample();
    wifiSignalSample.setSensorId(sensorId);
    wifiSignalSample.setType(type);
    wifiSignalSample.setTime(time);
    wifiSignalSample.setSsid(ssid);
    wifiSignalSample.setBssid(bssid);
    wifiSignalSample.setCapabilities(capabilities);
    wifiSignalSample.setFrequency(frequency);
    wifiSignalSample.setLevel(level);
    return wifiSignalSample;
  }

}
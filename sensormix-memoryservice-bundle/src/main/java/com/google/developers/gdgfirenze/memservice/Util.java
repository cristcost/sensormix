package com.google.developers.gdgfirenze.memservice;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.google.developers.gdgfirenze.model.AbstractSample;
import com.google.developers.gdgfirenze.model.NumericValueSample;
import com.google.developers.gdgfirenze.model.PositionSample;
import com.google.developers.gdgfirenze.model.Sensor;
import com.google.developers.gdgfirenze.model.StringValueSample;
import com.google.developers.gdgfirenze.model.WifiSignalSample;

/**
 * ObjectsSetter class.
 */
public class Util {

	private static final DateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
	private static final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	private static final DateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");

	public static Date newDateTime(String dateTimeString) throws ParseException {
		if (dateTimeString == null) {
			return null;
		}
		return dateTimeFormat.parse(dateTimeString);
	}

	public static Date newDate(String dateString) throws ParseException {
		if (dateString == null) {
			return null;
		}
		return dateFormat.parse(dateString);
	}

	public static Long newTime(String timeString) throws ParseException {
		if (timeString == null) {
			return null;
		}
		Date parsedDate = timeFormat.parse(timeString);
		return parsedDate.getTime();
	}

	public static Sensor newSensor(String id, String description, String name, String type, Double lat, Double lng, Date lastSeen) {
		Sensor sensor = new Sensor();
  	sensor.setId(id);
  	sensor.setDescription(description);
  	sensor.setName(name);
  	sensor.setType(type);
  	sensor.setLat(lat);
  	sensor.setLng(lng);
  	sensor.setLastSeen(lastSeen);
		return sensor;
	}
	
  public static List<AbstractSample> newSamples(AbstractSample... samples) {
    return Arrays.asList(samples);
  }
	
	public static NumericValueSample newNumericValueSample(String sensorId, String type, Date time, Double value) {
		NumericValueSample numericValueSample = new NumericValueSample();
		numericValueSample.setSensorId(sensorId);
		numericValueSample.setType(type);
		numericValueSample.setTime(time);
		numericValueSample.setValue(value);
		return numericValueSample;
	}
	
	public static PositionSample newPositionSample(String sensorId, String type, Date time, Double lat, 
			Double lng, Double alt, Double accuracy, Double bearing, Double speed) {
		PositionSample positionSample = new PositionSample();
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
	
	public static StringValueSample newStringValueSample(String sensorId, String type, Date time, String value) {
		StringValueSample stringValueSample = new StringValueSample();
		stringValueSample.setSensorId(sensorId);
		stringValueSample.setType(type);
		stringValueSample.setTime(time);
		stringValueSample.setValue(value);
		return stringValueSample;
	}
	
	public static WifiSignalSample newWifiSignalSample(String sensorId, String type, Date time, 
			String ssid, String bssid, String capabilities, Double frequency, Double level) {
		WifiSignalSample wifiSignalSample = new WifiSignalSample();
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
package com.google.developers.gdgfirenze.memservice;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.text.ParseException;
import java.util.Arrays;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.google.developers.gdgfirenze.model.Sensor;

public class SensormixMemIntegrationTest {

	private static SensormixServiceMemoryImpl serviceMemoryImpl;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		serviceMemoryImpl = new SensormixServiceMemoryImpl();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * I add 4 different types of sensors using the following method:
	 * - registerSensor
	 * Then i test the following service methods:
	 * - sensorList
	 * - getSensors
	 */
	@Test
	public void sensorsTest() throws ParseException {
		serviceMemoryImpl.registerSensor(Util.newSensor(
				"Galaxy-Nexus-Battery", "Battery level detector sensor", "Battery Galaxy", "BATTERY", 43.768301, 11.253394, Util.newDateTime("2013-11-30 09:00:00.000")));
		serviceMemoryImpl.registerSensor(Util.newSensor(
				"Galaxy-Nexus-GPS_1A23", "GPS antenna", "GPS Galaxy", "GPS", 43.768301, 11.253394, Util.newDateTime("2013-11-30 09:00:10.000")));
		serviceMemoryImpl.registerSensor(Util.newSensor(
				"Arduino-FHR_MC", "Arduino sensor for temperature measurements", "Arduino-FiharrahEnterprise_MegaConductor", "ARDUINO", 43.768301, 11.253394, Util.newDateTime("2013-11-30 09:00:20.000")));
		serviceMemoryImpl.registerSensor(Util.newSensor(
				"Galaxy-Nexus-WiFi_AP", "WiFi antenna", "WIFI Galaxy", "WIFI", 43.768301, 11.253394, Util.newDateTime("2013-11-30 09:00:30.000")));
		
		List<String> sensorIdList = serviceMemoryImpl.listSensorsIds();
		assertEquals(4, sensorIdList.size());
		assertTrue(sensorIdList.contains("Galaxy-Nexus-Battery"));
		assertTrue(sensorIdList.contains("Galaxy-Nexus-GPS_1A23"));
		assertTrue(sensorIdList.contains("Arduino-FHR_MC"));
		assertTrue(sensorIdList.contains("Galaxy-Nexus-WiFi_AP"));
		assertFalse(sensorIdList.contains("Fake"));
		
		List<Sensor> sensorList = serviceMemoryImpl.getSensors(null, null, null);
		assertEquals(4, sensorList.size());
		
		sensorList.clear();
		sensorList = serviceMemoryImpl.getSensors(sensorIdList, null, null);
		assertEquals(4, sensorList.size());
		
		sensorList.clear();
		sensorList = serviceMemoryImpl.getSensors(Arrays.asList("Galaxy-Nexus-Battery", "Arduino-FHR_MC", "fake"), null, null);
		assertEquals(2, sensorList.size());
		
		sensorList.clear();
		sensorList = serviceMemoryImpl.getSensors(sensorIdList, Util.newDateTime("2013-11-30 08:50:00.000"), null);
		assertEquals(4, sensorList.size());
		
		sensorList.clear();
		sensorList = serviceMemoryImpl.getSensors(sensorIdList, Util.newDateTime("2013-11-30 09:50:00.000"), null);
		assertEquals(0, sensorList.size());
		
		sensorList.clear();
		sensorList = serviceMemoryImpl.getSensors(sensorIdList, Util.newDateTime("2013-11-30 09:00:15.000"), null);
		assertEquals(2, sensorList.size());
		
		sensorList.clear();
		sensorList = serviceMemoryImpl.getSensors(sensorIdList, null, Util.newDateTime("2013-11-30 08:50:00.000"));
		assertEquals(0, sensorList.size());
		
		sensorList.clear();
		sensorList = serviceMemoryImpl.getSensors(sensorIdList, null, Util.newDateTime("2013-11-30 09:00:15.000"));
		assertEquals(2, sensorList.size());
		
		sensorList.clear();
		sensorList = serviceMemoryImpl.getSensors(sensorIdList, null, Util.newDateTime("2013-11-30 09:50:00.000"));
		assertEquals(4, sensorList.size());
		
		sensorList.clear();
		sensorList = serviceMemoryImpl.getSensors(sensorIdList, Util.newDateTime("2013-11-30 08:50:00.000"), Util.newDateTime("2013-11-30 09:50:00.000"));
		assertEquals(4, sensorList.size());
		
		sensorList.clear();
		sensorList = serviceMemoryImpl.getSensors(sensorIdList, Util.newDateTime("2013-11-30 09:00:05.000"), Util.newDateTime("2013-11-30 09:00:15.000"));
		assertEquals(1, sensorList.size());
	}

	/**
	 * I add 4 different types of sensors several types of samples using the following methods:
	 * - registerSensor
	 * - recordSamples
	 * Then i test the following service methods:
	 * - listSamplesTypes
	 * - countSamples
	 * - getSamples
	 * - getSampleReport
	 */
	@Test
	public void samplesTest() throws ParseException {
		serviceMemoryImpl.registerSensor(Util.newSensor(
				"Galaxy-Nexus-Battery", "Battery level detector sensor", "Battery Galaxy", "BATTERY", 43.768301, 11.253394, Util.newDateTime("2013-11-30 09:00:00.000")));
		serviceMemoryImpl.registerSensor(Util.newSensor(
				"Galaxy-Nexus-GPS_1A23", "GPS antenna", "GPS Galaxy", "GPS", 43.768301, 11.253394, Util.newDateTime("2013-11-30 09:00:10.000")));
		serviceMemoryImpl.registerSensor(Util.newSensor(
				"Arduino-FHR_MC", "Arduino sensor for temperature measurements", "Arduino-FiharrahEnterprise_MegaConductor", "ARDUINO", 43.768301, 11.253394, Util.newDateTime("2013-11-30 09:00:20.000")));
		serviceMemoryImpl.registerSensor(Util.newSensor(
				"Galaxy-Nexus-WiFi_AP", "WiFi antenna", "WIFI Galaxy", "WIFI", 43.768301, 11.253394, Util.newDateTime("2013-11-30 09:00:30.000")));
		
		serviceMemoryImpl.recordSamples(Util.newSamples(
				Util.newNumericValueSample("Galaxy-Nexus-Battery", "NUMERIC", Util.newDateTime("2013-01-01 09:01:00.000"), 0.68),
				Util.newPositionSample("Galaxy-Nexus-GPS_1A23", "POSITION", Util.newDateTime("2013-01-01 09:01:10.000"), 43.768301, 11.253394, 15.0, 0.85, 0.45, 40.0),
				Util.newStringValueSample("Arduino-FHR_MC", "STRING", Util.newDateTime("2013-01-01 09:01:20.000"), "25°"),
				Util.newWifiSignalSample("Galaxy-Nexus-WiFi_AP", "WIFI", Util.newDateTime("2013-01-01 09:01:30.000"), "Silence", 
						"00:22:3f:56:38:6a", "[WPA2-PSK-CCMP][ESS]", 2437.0, -50.0)));
		
		
		
	//List<String> listSamplesTypes(); IGNORED - Miss implementation
	//long countSamples(String sensorId, String sampleType, Date from, Date to);
	//List<AbstractSample> getSamples(String sensorId, String sampleType, Date from, Date to, Long limitFrom, Long limitCount );
	//SampleReport getSampleReport(String sensorId, String sampleType, Date from, Date to); IGNORED - Miss implementation
	}
	
}
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

import com.google.developers.gdgfirenze.model.AbstractSample;
import com.google.developers.gdgfirenze.model.SampleReport;
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
	 * - getSensors.
	 *
	 * @throws ParseException the parse exception
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
	 * - getSampleReport.
	 *
	 * @throws ParseException the parse exception
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
				Util.newNumericValueSample("Galaxy-Nexus-Battery", "NUMERIC", Util.newDateTime("2013-01-01 09:02:00.000"), 0.58),
				Util.newNumericValueSample("Galaxy-Nexus-Battery", "NUMERIC", Util.newDateTime("2013-01-01 09:03:00.000"), 0.51),
				Util.newPositionSample("Galaxy-Nexus-GPS_1A23", "POSITION", Util.newDateTime("2013-01-01 09:02:10.000"), 43.768300, 11.253394, 15.0, 0.85, 0.45, 40.0),
				Util.newPositionSample("Galaxy-Nexus-GPS_1A23", "POSITION", Util.newDateTime("2013-01-01 09:02:12.000"), 43.768301, 11.253395, 15.0, 0.85, 0.45, 40.0),
				Util.newPositionSample("Galaxy-Nexus-GPS_1A23", "POSITION", Util.newDateTime("2013-01-01 09:02:14.000"), 43.768302, 11.253396, 15.0, 0.85, 0.45, 40.0),
				Util.newPositionSample("Galaxy-Nexus-GPS_1A23", "POSITION", Util.newDateTime("2013-01-01 09:02:16.000"), 43.768303, 11.253397, 15.0, 0.85, 0.45, 40.0),
				Util.newPositionSample("Galaxy-Nexus-GPS_1A23", "POSITION", Util.newDateTime("2013-01-01 09:02:18.000"), 43.768304, 11.253398, 15.0, 0.85, 0.45, 40.0),
				Util.newStringValueSample("Arduino-FHR_MC", "STRING", Util.newDateTime("2013-01-01 09:00:00.000"), "25°"),
				Util.newStringValueSample("Arduino-FHR_MC", "STRING", Util.newDateTime("2013-01-01 10:00:00.000"), "25.2°"),
				Util.newStringValueSample("Arduino-FHR_MC", "STRING", Util.newDateTime("2013-01-01 11:00:00.000"), "25.3°"),
				Util.newStringValueSample("Arduino-FHR_MC", "STRING", Util.newDateTime("2013-01-01 12:00:00.000"), "25.5°"),
				Util.newWifiSignalSample("Galaxy-Nexus-WiFi_AP", "WIFI", Util.newDateTime("2013-01-01 09:05:00.000"), "Silence", 
						"00:22:3f:56:38:6a", "[WPA2-PSK-CCMP][ESS]", 2437.0, -50.0),
				Util.newWifiSignalSample("Galaxy-Nexus-WiFi_AP", "WIFI", Util.newDateTime("2013-01-01 09:05:00.000"), "BaffoWiNet", 
						"00:22:3f:56:38:6a", "[WPA2-PSK-CCMP][ESS]", 2437.0, -45.0),
				Util.newWifiSignalSample("Galaxy-Nexus-WiFi_AP", "WIFI", Util.newDateTime("2013-01-01 09:15:00.000"), "AliceCristiano", 
						"00:22:3f:56:38:6a", "[WPA2-PSK-CCMP][ESS]", 2437.0, -48.0)));
		
		List<String> sampleTypeList = serviceMemoryImpl.listSamplesTypes();
		assertEquals(4, sampleTypeList.size());
		
		long sampleCount = serviceMemoryImpl.countSamples(null, null, null, null);
		assertEquals(15, sampleCount);
		sampleCount = serviceMemoryImpl.countSamples("Galaxy-Nexus-Battery", null, null, null);
		assertEquals(3, sampleCount);
		sampleCount = serviceMemoryImpl.countSamples("Galaxy-Nexus-GPS_1A23", null, null, null);
		assertEquals(5, sampleCount);
		sampleCount = serviceMemoryImpl.countSamples("Arduino-FHR_MC", null, null, null);
		assertEquals(4, sampleCount);
		sampleCount = serviceMemoryImpl.countSamples("Galaxy-Nexus-WiFi_AP", null, null, null);
		assertEquals(3, sampleCount);
		sampleCount = serviceMemoryImpl.countSamples("fake", null, null, null);
		assertEquals(0, sampleCount);
		sampleCount = serviceMemoryImpl.countSamples(null, "NUMERIC", null, null);
		assertEquals(3, sampleCount);
		sampleCount = serviceMemoryImpl.countSamples(null, null, Util.newDateTime("2013-01-01 08:50:00.000"), null);
		assertEquals(15, sampleCount);
		sampleCount = serviceMemoryImpl.countSamples(null, null, Util.newDateTime("2013-01-01 09:20:00.000"), null);
		assertEquals(3, sampleCount);
		sampleCount = serviceMemoryImpl.countSamples(null, null, null, Util.newDateTime("2013-01-01 13:00:00.000"));
		assertEquals(15, sampleCount);
		sampleCount = serviceMemoryImpl.countSamples(null, null, null, Util.newDateTime("2013-01-01 09:21:00.000"));
		assertEquals(12, sampleCount);
		sampleCount = serviceMemoryImpl.countSamples(null, null, Util.newDateTime("2013-01-01 09:11:00.000"), Util.newDateTime("2013-01-01 10:15:00.000"));
		assertEquals(2, sampleCount);
		sampleCount = serviceMemoryImpl.countSamples("Galaxy-Nexus-GPS_1A23", "POSITION", Util.newDateTime("2013-01-01 09:02:11.000"), Util.newDateTime("2013-01-01 09:02:17.000"));
		assertEquals(3, sampleCount);
		sampleCount = serviceMemoryImpl.countSamples("Galaxy-Nexus-GPS_1A23", "fake", Util.newDateTime("2013-01-01 09:02:11.000"), Util.newDateTime("2013-01-01 09:02:17.000"));
		assertEquals(0, sampleCount);
		
		List<AbstractSample> abstractSamples = serviceMemoryImpl.getSamples(null, null, null, null, null, null);
		assertEquals(15, abstractSamples.size());
		abstractSamples.clear();
		abstractSamples = serviceMemoryImpl.getSamples("Galaxy-Nexus-Battery", null, null, null, null, null);
		assertEquals(3, abstractSamples.size());
		abstractSamples.clear();
		abstractSamples = serviceMemoryImpl.getSamples("Galaxy-Nexus-GPS_1A23", null, null, null, null, null);
		assertEquals(5, abstractSamples.size());
		abstractSamples.clear();
		abstractSamples = serviceMemoryImpl.getSamples(null, "STRING", null, null, null, null);
		assertEquals(4, abstractSamples.size());
		abstractSamples.clear();
		abstractSamples = serviceMemoryImpl.getSamples(null, null, Util.newDateTime("2013-01-01 08:50:00.000"), null, null, null);
		assertEquals(15, abstractSamples.size());
		abstractSamples.clear();
		abstractSamples = serviceMemoryImpl.getSamples(null, null, Util.newDateTime("2013-01-01 09:20:00.000"), null, null, null);
		assertEquals(3, abstractSamples.size());
		abstractSamples.clear();
		abstractSamples = serviceMemoryImpl.getSamples(null, null, null, Util.newDateTime("2013-01-01 13:00:00.000"), null, null);
		assertEquals(15, abstractSamples.size());
		abstractSamples.clear();
		abstractSamples = serviceMemoryImpl.getSamples(null, null, null, Util.newDateTime("2013-01-01 09:21:00.000"), null, null);
		assertEquals(12, abstractSamples.size());
		abstractSamples.clear();
		abstractSamples = serviceMemoryImpl.getSamples(null, null, Util.newDateTime("2013-01-01 09:11:00.000"), Util.newDateTime("2013-01-01 10:15:00.000"), null, null);
		assertEquals(2, abstractSamples.size());
		abstractSamples.clear();
		abstractSamples = serviceMemoryImpl.getSamples(null, null, null, null, null, (long) 10);
		assertEquals(10, abstractSamples.size());
		abstractSamples.clear();
		abstractSamples = serviceMemoryImpl.getSamples("Galaxy-Nexus-GPS_1A23", "POSITION", Util.newDateTime("2013-01-01 09:02:11.000"), Util.newDateTime("2013-01-01 09:02:17.000"), null, (long) 10);
		assertEquals(3, abstractSamples.size());
		abstractSamples.clear();
		abstractSamples = serviceMemoryImpl.getSamples("Galaxy-Nexus-GPS_1A23", "POSITION", Util.newDateTime("2013-01-01 09:02:11.000"), Util.newDateTime("2013-01-01 09:02:17.000"), null, (long) 2);
		assertEquals(2, abstractSamples.size());
		abstractSamples.clear();
		abstractSamples = serviceMemoryImpl.getSamples("fake", "POSITION", Util.newDateTime("2013-01-01 09:02:11.000"), Util.newDateTime("2013-01-01 09:02:17.000"), null, null);
		assertEquals(0, abstractSamples.size());

		 SampleReport sampleReport = serviceMemoryImpl.getSampleReport("Galaxy-Nexus-GPS_1A23", "POSITION", Util.newDateTime("2013-01-01 08:50:00.000"), Util.newDateTime("2013-01-01 18:00:00.000"));
		 assertEquals(1, sampleReport.getDailySampleReports().size());
		 assertEquals(5, sampleReport.getDailySampleReports().get(0).getSampleCount());
	}
	
}
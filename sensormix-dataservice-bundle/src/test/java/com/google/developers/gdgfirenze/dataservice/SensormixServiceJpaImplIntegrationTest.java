package com.google.developers.gdgfirenze.dataservice;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.google.developers.gdgfirenze.model.AbstractSample;
import com.google.developers.gdgfirenze.model.NumericValueSample;
import com.google.developers.gdgfirenze.model.PositionSample;
import com.google.developers.gdgfirenze.model.Sensor;
import com.google.developers.gdgfirenze.model.WifiSignalSample;

public class SensormixServiceJpaImplIntegrationTest {

	private SensormixServiceJpaImpl sensormixServiceJpaImpl;
	private EntityManagerFactory emf;

	@Before
	public void initialize() {
		emf = Persistence
				.createEntityManagerFactory("sensormix_db_test");
		sensormixServiceJpaImpl = new SensormixServiceJpaImpl();
		sensormixServiceJpaImpl.setEntityManagerFactory(emf);
	}

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Test
	public void test1()  {
		Sensor s=new Sensor();
		s.setId("uri:giuseppe:android:77551144");
		s.setName("Samsung Galaxy W");
		s.setDescription("booooooo");
		sensormixServiceJpaImpl.registerSensor(s);
		List<String> ids = sensormixServiceJpaImpl.listSensorsIds();
		assertEquals(1, ids.size());
	}

	@Test
	public void test2()  {
		int count=1000;
		List<AbstractSample> firstTypeOfSample = new ArrayList<AbstractSample>();
		for(int i=0;i<count;i++) {
			NumericValueSample s=new NumericValueSample();
			s.setSensorId("uri:giuseppe:android:77551144");
			s.setType("uri:giuseppe:android:numericType");
			s.setTime(new Date(System.currentTimeMillis()));
			s.setValue((double)i*152);
			firstTypeOfSample.add(s);
		}
		for(int i=0;i<count;i++) {
			PositionSample s=new PositionSample();
			s.setSensorId("uri:giuseppe:android:77551145");
			s.setType("uri:giuseppe:android:positionType");
			s.setTime(new Date(System.currentTimeMillis()));
			s.setLat(15.00);
			s.setLng((double)(45+i/100));
			firstTypeOfSample.add(s);
		}
		for(int i=0;i<count;i++) {
			WifiSignalSample s=new WifiSignalSample();
			s.setSensorId("uri:giuseppe:android:77551146");
			s.setType("uri:giuseppe:android:WifiType");
			s.setTime(new Date(System.currentTimeMillis()));
			s.setFrequency(5000000000.0);
			firstTypeOfSample.add(s);
		}
		
		long checkTime=System.currentTimeMillis();
		sensormixServiceJpaImpl.recordSamples(firstTypeOfSample);
		checkTime=System.currentTimeMillis()-checkTime;
		System.out.println("recorded " + firstTypeOfSample.size() + " in " + checkTime + " milliseconds");
		checkTime=System.currentTimeMillis();
		List<AbstractSample> ids = sensormixServiceJpaImpl.getSamples("uri:giuseppe:android:77551145", null, null, null);
		checkTime=System.currentTimeMillis()-checkTime;
		System.out.println("readed " + ids.size() + " in " + checkTime+ " milliseconds");
		assertEquals(count, ids.size());
	}
	
	@Test
	public void test3()  {
		int count=1000;
		List<AbstractSample> firstTypeOfSample = new ArrayList<AbstractSample>();
		Calendar d=Calendar.getInstance();
		d.set(2013,10,22);
		for(int i=0;i<count;i++) {
			NumericValueSample s=new NumericValueSample();
			s.setSensorId("uri:giuseppe:android:77551144");
			s.setType("uri:giuseppe:android:numericType");
			s.setTime(d.getTime());
			s.setValue((double)i*152);
			firstTypeOfSample.add(s);
		}
		d.set(2013,9,22);
		for(int i=0;i<count;i++) {
			PositionSample s=new PositionSample();
			s.setSensorId("uri:giuseppe:android:77551145");
			s.setType("uri:giuseppe:android:positionType");
			s.setTime(d.getTime());
			s.setLat(15.00);
			s.setLng((double)(45+i/100));
			firstTypeOfSample.add(s);
		}
		d.set(2013,8,22);
		for(int i=0;i<count;i++) {
			WifiSignalSample s=new WifiSignalSample();
			s.setSensorId("uri:giuseppe:android:77551146");
			s.setType("uri:giuseppe:android:WifiType");
			s.setTime(d.getTime());
			s.setFrequency(5000000000.0);
			firstTypeOfSample.add(s);
		}
		
		long checkTime=System.currentTimeMillis();
		sensormixServiceJpaImpl.recordSamples(firstTypeOfSample);
		checkTime=System.currentTimeMillis()-checkTime;
		System.out.println("recorded " + firstTypeOfSample.size() + " in " + checkTime + " milliseconds");
		d.set(2013,9,22);
		checkTime=System.currentTimeMillis();
		List<AbstractSample> ids = sensormixServiceJpaImpl.getSamples(null, null, d.getTime(), null);
		checkTime=System.currentTimeMillis()-checkTime;
		System.out.println("readed " + ids.size() + " in " + checkTime+ " milliseconds");
		assertEquals(2*count, ids.size());
		
		d.set(2013,10,21);
		checkTime=System.currentTimeMillis();
		ids = sensormixServiceJpaImpl.getSamples(null, null, null, d.getTime());
		checkTime=System.currentTimeMillis()-checkTime;
		System.out.println("readed " + ids.size() + " in " + checkTime+ " milliseconds");
		assertEquals(2*count, ids.size());
		
		d.set(2013,9,21);
		Date to=d.getTime();
		d.set(2013,8,21);
		Date from=d.getTime();
		checkTime=System.currentTimeMillis();
		ids = sensormixServiceJpaImpl.getSamples(null, null, from, to);
		checkTime=System.currentTimeMillis()-checkTime;
		System.out.println("readed " + ids.size() + " in " + checkTime+ " milliseconds");
		assertEquals(count, ids.size());
	}
	
	@Test
	public void test4()  {
		int count=1000;
		List<AbstractSample> firstTypeOfSample = new ArrayList<AbstractSample>();
		Calendar d=Calendar.getInstance();
		d.set(2013,10,22);
		for(int i=0;i<count;i++) {
			NumericValueSample s=new NumericValueSample();
			s.setSensorId("uri:giuseppe:android:77551144");
			s.setType("uri:giuseppe:android:numericType");
			s.setTime(d.getTime());
			s.setValue((double)i*152);
			firstTypeOfSample.add(s);
		}
		d.set(2013,9,22);
		for(int i=0;i<count;i++) {
			PositionSample s=new PositionSample();
			s.setSensorId("uri:giuseppe:android:77551145");
			s.setType("uri:giuseppe:android:positionType");
			s.setTime(d.getTime());
			s.setLat(15.00);
			s.setLng((double)(45+i/100));
			firstTypeOfSample.add(s);
		}
		d.set(2013,8,22);
		for(int i=0;i<count;i++) {
			WifiSignalSample s=new WifiSignalSample();
			s.setSensorId("uri:giuseppe:android:77551146");
			s.setType("uri:giuseppe:android:WifiType");
			s.setTime(d.getTime());
			s.setFrequency(5000000000.0);
			firstTypeOfSample.add(s);
		}
		
		long checkTime=System.currentTimeMillis();
		sensormixServiceJpaImpl.recordSamples(firstTypeOfSample);
		checkTime=System.currentTimeMillis()-checkTime;
		System.out.println("recorded " + firstTypeOfSample.size() + " in " + checkTime + " milliseconds");
		checkTime=System.currentTimeMillis();
		List<AbstractSample> ids = sensormixServiceJpaImpl.getSamples(null, "uri:giuseppe:android:positionType", null, null);
		checkTime=System.currentTimeMillis()-checkTime;
		System.out.println("readed " + ids.size() + " in " + checkTime+ " milliseconds");
		assertEquals(count, ids.size());
	}
}
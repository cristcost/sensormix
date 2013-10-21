package com.google.developers.gdgfirenze.dataservice;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
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
			s.setType("uri:giuseppe:android:numeriType");
			s.setTime(new Date(System.currentTimeMillis()));
			s.setValue((double)i*152);
			firstTypeOfSample.add(s);
		}
		for(int i=0;i<count;i++) {
			PositionSample s=new PositionSample();
			s.setSensorId("uri:giuseppe:android:77551145");
			s.setType("uri:giuseppe:android:numeriType");
			s.setTime(new Date(System.currentTimeMillis()));
			s.setLat(15.00);
			s.setLng((double)(45+i/100));
			firstTypeOfSample.add(s);
		}
		for(int i=0;i<count;i++) {
			WifiSignalSample s=new WifiSignalSample();
			s.setSensorId("uri:giuseppe:android:77551146");
			s.setType("uri:giuseppe:android:numeriType");
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
}
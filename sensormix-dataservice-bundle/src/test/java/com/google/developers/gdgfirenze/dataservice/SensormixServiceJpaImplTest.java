package com.google.developers.gdgfirenze.dataservice;

import static org.junit.Assert.assertEquals;

import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.google.developers.gdgfirenze.model.Sensor;

public class SensormixServiceJpaImplTest {

	private SensormixServiceJpaImpl sensormixServiceJpaImpl;
	private EntityManagerFactory emf;

	@Before
	public void initialize() {
		emf = Persistence
				.createEntityManagerFactory("sensormix_db");
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

}
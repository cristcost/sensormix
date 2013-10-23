package com.google.developers.gdgfirenze.dataservice;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

import com.google.developers.gdgfirenze.datamodeljpa.JpaAbstractSample;
import com.google.developers.gdgfirenze.datamodeljpa.JpaSensor;
import com.google.developers.gdgfirenze.model.AbstractSample;
import com.google.developers.gdgfirenze.model.Sensor;
import com.google.developers.gdgfirenze.serializer.Serializer;
import com.google.developers.gdgfirenze.service.DailySampleReport;
import com.google.developers.gdgfirenze.service.SampleReport;
import com.google.developers.gdgfirenze.service.SensormixService;

public class SensormixServiceJpaImpl implements SensormixService {

	private EntityManagerFactory entityManagerFactory;

	public void setEntityManagerFactory(
			EntityManagerFactory entityManagerFactory) {
		this.entityManagerFactory = entityManagerFactory;
	}

	@Override
	@WebMethod(action = "urn:#listSensorsIds")
	@RequestWrapper(localName = "listSensorsIdsIn", targetNamespace = "http://developers.google.com/gdgfirenze/ns/service")
	@ResponseWrapper(localName = "listSensorsIdsOut", targetNamespace = "http://developers.google.com/gdgfirenze/ns/service")
	@WebResult(name = "sensorId")
	public List<String> listSensorsIds() {
		EntityManager em = entityManagerFactory.createEntityManager();
		TypedQuery<String> q = em.createQuery("SELECT s.id FROM JpaSensor s",
				String.class);

		List<String> result = q.getResultList();

		em.close();

		return result;
	}

	@Override
	@WebMethod(action = "urn:#getSamples")
	@RequestWrapper(localName = "getSamplesIn", targetNamespace = "http://developers.google.com/gdgfirenze/ns/service")
	@ResponseWrapper(localName = "getSamplesOut", targetNamespace = "http://developers.google.com/gdgfirenze/ns/service")
	@WebResult(name = "sample")
	public List<AbstractSample> getSamples(
			@WebParam(name = "sensorId") String sensorId,
			@WebParam(name = "sampleType") String sampleType,
			@WebParam(name = "from") Date from, @WebParam(name = "to") Date to) {

		List<AbstractSample> samples = new ArrayList<>();
		try {
			if (from != null && to != null && !from.before(to)) {
				// TODO
			} else {
				EntityManager em = entityManagerFactory.createEntityManager();
				CriteriaBuilder cb = em.getCriteriaBuilder();

				CriteriaQuery<JpaAbstractSample> cq = cb
						.createQuery(JpaAbstractSample.class);
				Root<JpaAbstractSample> jas = cq.from(JpaAbstractSample.class);
				cq.select(jas);
				List<Predicate> criteria = new ArrayList<Predicate>();
				if (sensorId != null && !"".equals(sensorId)) {
					ParameterExpression<String> p = cb.parameter(String.class,
							"sensorId");
					criteria.add(cb.equal(jas.get("sensorId"), p));
				}
				if (sampleType != null && !"".equals(sampleType)) {
					ParameterExpression<String> p = cb.parameter(String.class,
							"type");
					criteria.add(cb.equal(jas.get("type"), p));
				}
				if (from != null) {
					ParameterExpression<Date> p = cb.parameter(Date.class,
							"from");
					Path<Date> datePath = jas.get("time");
					criteria.add(cb.greaterThanOrEqualTo(datePath, p));
				}
				if (to != null) {
					ParameterExpression<Date> p = cb.parameter(Date.class,
							"to");
					Path<Date> datePath = jas.get("time");
					criteria.add(cb.lessThanOrEqualTo(datePath, p));
				}
				if (criteria.size() == 0) {
					throw new RuntimeException("no criteria");
				} else if (criteria.size() == 1) {
					cq.where(criteria.get(0));
				} else {
					cq.where(cb.and(criteria.toArray(new Predicate[0])));
				}
				TypedQuery<JpaAbstractSample> q = em.createQuery(cq);
				if (sensorId != null && !"".equals(sensorId)) {
					q.setParameter("sensorId", sensorId);
				}
				if (sampleType != null && !"".equals(sampleType)) {
					q.setParameter("type", sampleType);
				}
				if (from != null) {
					q.setParameter("from", from);
				}
				if (to != null) {
					q.setParameter("to", to);
				}
				List<JpaAbstractSample> jass = q.getResultList();
				for (Iterator<?> i = jass.iterator(); i.hasNext();) {
					JpaAbstractSample u = (JpaAbstractSample) i.next();
					samples.add(Serializer.deserialize(u.getValue()));
				}
				em.close();
			}
		} catch (Exception e) {
			// TODO
		}
		return samples;
	}

	@Override
	@WebMethod(action = "urn:#countSamples")
	@RequestWrapper(localName = "countSamplesIn", targetNamespace = "http://developers.google.com/gdgfirenze/ns/service")
	@ResponseWrapper(localName = "countSamplesOut", targetNamespace = "http://developers.google.com/gdgfirenze/ns/service")
	@WebResult(name = "sampleCount")
	public long countSamples(@WebParam(name = "sensorId") String sensorId,
			@WebParam(name = "sampleType") String sampleType,
			@WebParam(name = "from") Date from, @WebParam(name = "to") Date to) {

		try {
			if (from.before(to)) {
				EntityManager em = entityManagerFactory.createEntityManager();
				CriteriaBuilder cb = em.getCriteriaBuilder();

				CriteriaQuery<JpaAbstractSample> cq = cb
						.createQuery(JpaAbstractSample.class);
				Root<JpaAbstractSample> jas = cq.from(JpaAbstractSample.class);
				cq.multiselect(cb.count(jas));
				List<Predicate> criteria = new ArrayList<Predicate>();
				if (sensorId != null && !"".equals(sensorId)) {
					ParameterExpression<String> p = cb.parameter(String.class,
							"sensorId");
					criteria.add(cb.equal(jas.get("sensorId"), p));
				}
				if (sampleType != null && !"".equals(sampleType)) {
					ParameterExpression<String> p = cb.parameter(String.class,
							"type");
					criteria.add(cb.equal(jas.get("type"), p));
				}
				if (from != null) {
					ParameterExpression<Date> p = cb.parameter(Date.class,
							"time");
					Path<Date> datePath = jas.get("time");
					criteria.add(cb.greaterThanOrEqualTo(datePath, p));
				}
				if (to != null) {
					ParameterExpression<Date> p = cb.parameter(Date.class,
							"time");
					Path<Date> datePath = jas.get("time");
					criteria.add(cb.lessThanOrEqualTo(datePath, p));
				}
				if (criteria.size() == 0) {
					throw new RuntimeException("no criteria");
				} else if (criteria.size() == 1) {
					cq.where(criteria.get(0));
				} else {
					cq.where(cb.and(criteria.toArray(new Predicate[0])));
				}
				TypedQuery<JpaAbstractSample> q = em.createQuery(cq);
				if (sensorId != null && !"".equals(sensorId)) {
					q.setParameter("sensorId", sensorId);
				}
				if (sampleType != null && !"".equals(sampleType)) {
					q.setParameter("type", sampleType);
				}
				if (from != null) {
					q.setParameter("time", from);
				}
				if (to != null) {
					q.setParameter("time", to);
				}
				Object jass = q.getSingleResult();
				em.close();
			} else {
				// TODO
			}
		} catch (Exception e) {
			// TODO
		}

		return 0;
	}

	@Override
	@WebMethod(action = "urn:#getSampleReport")
	@RequestWrapper(localName = "getSampleReportIn", targetNamespace = "http://developers.google.com/gdgfirenze/ns/service")
	@ResponseWrapper(localName = "getSampleReportOut", targetNamespace = "http://developers.google.com/gdgfirenze/ns/service")
	@WebResult(name = "sampleReport")
	public SampleReport getSampleReport(
			@WebParam(name = "sensorId") String sensorId,
			@WebParam(name = "sampleType") String sampleType,
			@WebParam(name = "from") Date from, @WebParam(name = "to") Date to) {

		SampleReport sr = new SampleReport();
		sr.setSensorId(sensorId);
		sr.setSampleType(sampleType);
		sr.setDailySampleReports(new ArrayList<DailySampleReport>());

		try {
			if (from != null && to != null && from.before(to)) {
				EntityManager em = entityManagerFactory.createEntityManager();

				Calendar start = Calendar.getInstance();
				Calendar end = Calendar.getInstance();
				start.set(from.getYear(), from.getMonth(), from.getDay());
				end.set(to.getYear(), to.getMonth(), to.getDay());

				while (start.before(end)) {
					Date internalStart = start.getTime();
					start.add(Calendar.DAY_OF_MONTH, 1);
					Date internalEnd = start.getTime();

					CriteriaBuilder cb = em.getCriteriaBuilder();

					CriteriaQuery<JpaAbstractSample> cq = cb
							.createQuery(JpaAbstractSample.class);
					Root<JpaAbstractSample> jas = cq
							.from(JpaAbstractSample.class);
					cq.multiselect(cb.count(jas));
					List<Predicate> criteria = new ArrayList<Predicate>();
					if (sensorId != null && !"".equals(sensorId)) {
						ParameterExpression<String> p = cb.parameter(
								String.class, "sensorId");
						criteria.add(cb.equal(jas.get("sensorId"), p));
					}
					if (sampleType != null && !"".equals(sampleType)) {
						ParameterExpression<String> p = cb.parameter(
								String.class, "type");
						criteria.add(cb.equal(jas.get("type"), p));
					}
					if (internalStart != null) {
						ParameterExpression<Date> p = cb.parameter(Date.class,
								"time");
						Path<Date> datePath = jas.get("time");
						criteria.add(cb.greaterThanOrEqualTo(datePath, p));
					}
					if (internalEnd != null) {
						ParameterExpression<Date> p = cb.parameter(Date.class,
								"time");
						Path<Date> datePath = jas.get("time");
						criteria.add(cb.lessThanOrEqualTo(datePath, p));
					}
					if (criteria.size() == 0) {
						throw new RuntimeException("no criteria");
					} else if (criteria.size() == 1) {
						cq.where(criteria.get(0));
					} else {
						cq.where(cb.and(criteria.toArray(new Predicate[0])));
					}
					TypedQuery<JpaAbstractSample> q = em.createQuery(cq);
					if (sensorId != null && !"".equals(sensorId)) {
						q.setParameter("sensorId", sensorId);
					}
					if (sampleType != null && !"".equals(sampleType)) {
						q.setParameter("type", sampleType);
					}
					if (from != null) {
						q.setParameter("time", internalStart);
					}
					if (to != null) {
						q.setParameter("time", internalEnd);
					}
					Object jass = q.getSingleResult();

					DailySampleReport dsr = new DailySampleReport();
					dsr.setDate(internalStart);
					dsr.setSampleCount(0);
				}
				em.close();
			} else {
				// TODO
			}
		} catch (Exception e) {
			// TODO
		}
		return sr;
	}

	@Override
	@WebMethod(action = "urn:#getSensors")
	@RequestWrapper(localName = "getSensorsIn", targetNamespace = "http://developers.google.com/gdgfirenze/ns/service")
	@ResponseWrapper(localName = "getSensorsOut", targetNamespace = "http://developers.google.com/gdgfirenze/ns/service")
	@WebResult(name = "sensor")
	public List<Sensor> getSensors(
			@WebParam(name = "sensorId") List<String> sensorIds, Date from, Date to) {
		List<Sensor> sensors = new ArrayList<Sensor>();
		try {
			EntityManager em = entityManagerFactory.createEntityManager();
			CriteriaBuilder cb = em.getCriteriaBuilder();

			CriteriaQuery<JpaSensor> cq = cb.createQuery(JpaSensor.class);
			Root<JpaSensor> js = cq.from(JpaSensor.class);
			cq.select(js);
			Expression<String> p = js.get("id");
			Predicate criteria = p.in(sensorIds);
			cq.where(criteria);
			TypedQuery<JpaSensor> q = em.createQuery(cq);
			List<JpaSensor> ss = q.getResultList();
			for (Iterator<JpaSensor> i = ss.iterator(); i.hasNext();) {
				JpaSensor u = i.next();
				Sensor s = new Sensor();
				s.setId(u.getId());
				s.setName(u.getName());
				s.setDescription(u.getDescription());
				s.setLastSeen(u.getLastSeen());
				s.setLat(u.getLat());
				s.setLng(u.getLng());
				sensors.add(s);
			}
			em.close();
		} catch (Exception e) {
			// TODO
			System.out.println("error");
		}
		return sensors;
	}

	@Override
	@WebMethod(action = "urn:#registerSensor")
	@RequestWrapper(localName = "registerSensorIn", targetNamespace = "http://developers.google.com/gdgfirenze/ns/service")
	@ResponseWrapper(localName = "registerSensorOut", targetNamespace = "http://developers.google.com/gdgfirenze/ns/service")
	public void registerSensor(@WebParam(name = "sensor") Sensor sensor) {
		if (sensor != null) {
			JpaSensor s = new JpaSensor();
			s.setId(sensor.getId());
			s.setName(sensor.getName());
			s.setDescription(sensor.getDescription());
			s.setLastSeen(sensor.getLastSeen());
			s.setLat(sensor.getLat());
			s.setLng(sensor.getLng());

			EntityManager em = entityManagerFactory.createEntityManager();
			EntityTransaction tx = em.getTransaction();
			tx.begin();
			em.merge(s);
			tx.commit();
			em.close();
		} else {
			// TODO
		}
	}

	@Override
	@WebMethod(action = "urn:#recordSamples")
	@RequestWrapper(localName = "recordSamplesIn", targetNamespace = "http://developers.google.com/gdgfirenze/ns/service")
	@ResponseWrapper(localName = "recordSamplesOut", targetNamespace = "http://developers.google.com/gdgfirenze/ns/service")
	public void recordSamples(
			@WebParam(name = "sample") List<AbstractSample> samples) {
		if (samples != null) {
			List<String> checkList = listSensorsIds();

			EntityManager em = entityManagerFactory.createEntityManager();
			EntityTransaction transaction = em.getTransaction();
			transaction.begin();
			for (AbstractSample sample : samples) {
				if (!checkList.contains(sample.getSensorId())) {
					Sensor s = new Sensor();
					s.setId(sample.getSensorId());
					s.setName("Unknown");
					s.setDescription("Unknown");

					registerSensor(s);
					checkList.add(sample.getSensorId());
				} else {
					List<String> sensorList = new ArrayList<String>();
					sensorList.add(sample.getSensorId());
					Sensor s = getSensors(sensorList, null, null).get(0);
					s.setLastSeen(sample.getTime());

					registerSensor(s);
				}
				JpaAbstractSample s = new JpaAbstractSample();
				s.setSensorId(sample.getSensorId());
				s.setTime(sample.getTime());
				s.setType(sample.getType());
				s.setValue(Serializer.serialize(sample));

				em.persist(s);
			}
			transaction.commit();
			em.close();
		} else {
			// TODO
		}
	}

	@Override
	public List<String> listSamplesTypes() {
		// Sergio FROCIO!
		return null;
	}

}

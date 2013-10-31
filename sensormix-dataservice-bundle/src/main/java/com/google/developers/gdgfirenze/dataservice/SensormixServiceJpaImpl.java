package com.google.developers.gdgfirenze.dataservice;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.google.developers.gdgfirenze.datamodeljpa.JpaAbstractSample;
import com.google.developers.gdgfirenze.datamodeljpa.JpaSensor;
import com.google.developers.gdgfirenze.model.AbstractSample;
import com.google.developers.gdgfirenze.model.DailySampleReport;
import com.google.developers.gdgfirenze.model.SampleReport;
import com.google.developers.gdgfirenze.model.Sensor;
import com.google.developers.gdgfirenze.serializer.Serializer;
import com.google.developers.gdgfirenze.service.SensormixService;

public class SensormixServiceJpaImpl implements SensormixService {

	private static Logger logger = Logger
			.getLogger(SensormixServiceJpaImpl.class.getName());

	private static final ThreadLocal<Serializer> localSerializer = new ThreadLocal<Serializer>() {
		@Override
		protected Serializer initialValue() {
			logger.log(Level.INFO, "Initializing a new Kryo instance");
			Serializer serializer = new Serializer();

			return serializer;
		}
	};

	private EntityManagerFactory entityManagerFactory;

	public void setEntityManagerFactory(
			EntityManagerFactory entityManagerFactory) {
		this.entityManagerFactory = entityManagerFactory;
	}

	@Override
	public List<String> listSensorsIds() {
		List<String> result = new ArrayList<String>();

		try {
			EntityManager em = entityManagerFactory.createEntityManager();
			TypedQuery<String> q = em.createQuery(
					"SELECT s.id FROM JpaSensor s", String.class);

			result.addAll(q.getResultList());

			em.close();
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Error during sensors list retrieving", e);
		}

		return result;
	}

	@Override
	public List<AbstractSample> getSamples(String sensorId, String sampleType,
			Date from, Date to) {

		List<AbstractSample> samples = new ArrayList<>();
		try {
			if (from != null && to != null && !from.before(to)) {
				logger.log(Level.WARNING,
						"Error from date must be before to date");
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
							"sampleType");
					criteria.add(cb.equal(jas.get("type"), p));
				}
				if (from != null) {
					ParameterExpression<Date> p = cb.parameter(Date.class,
							"fromDate");
					Path<Date> datePath = jas.get("time");
					criteria.add(cb.greaterThanOrEqualTo(datePath, p));
				}
				if (to != null) {
					ParameterExpression<Date> p = cb.parameter(Date.class,
							"toDate");
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
					q.setParameter("sampleType", sampleType);
				}
				if (from != null) {
					q.setParameter("fromDate", from);
				}
				if (to != null) {
					q.setParameter("toDate", to);
				}
				List<JpaAbstractSample> jass = q.getResultList();
				for (Iterator<?> i = jass.iterator(); i.hasNext();) {
					JpaAbstractSample u = (JpaAbstractSample) i.next();
					samples.add(localSerializer.get().deserialize(u.getValue()));
				}
				em.close();
			}
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Error during samples list retrieving", e);
		}
		return samples;
	}

	@Override
	public long countSamples(String sensorId, String sampleType, Date from,
			Date to) {

		Long retVal = 0L;
		try {
			if (from != null && to != null && !from.before(to)) {
				logger.log(Level.WARNING,
						"Error from date must be before to date");
			} else {
				EntityManager em = entityManagerFactory.createEntityManager();
				CriteriaBuilder cb = em.getCriteriaBuilder();

				CriteriaQuery<Object> cq = cb.createQuery();
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
							"sampleType");
					criteria.add(cb.equal(jas.get("type"), p));
				}
				if (from != null) {
					ParameterExpression<Date> p = cb.parameter(Date.class,
							"fromDate");
					Path<Date> datePath = jas.get("time");
					criteria.add(cb.greaterThanOrEqualTo(datePath, p));
				}
				if (to != null) {
					ParameterExpression<Date> p = cb.parameter(Date.class,
							"toDate");
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
				Query q = em.createQuery(cq);
				if (sensorId != null && !"".equals(sensorId)) {
					q.setParameter("sensorId", sensorId);
				}
				if (sampleType != null && !"".equals(sampleType)) {
					q.setParameter("sampleType", sampleType);
				}
				if (from != null) {
					q.setParameter("fromDate", from);
				}
				if (to != null) {
					q.setParameter("toDate", to);
				}
				retVal = (Long) q.getSingleResult();
				em.close();
			}
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Error during samples counting", e);
		}

		return retVal;
	}

	@Override
	public SampleReport getSampleReport(String sensorId, String sampleType,
			Date from, Date to) {

		SampleReport sr = new SampleReport();
		sr.setSensorId(sensorId);
		sr.setSampleType(sampleType);
		sr.setDailySampleReports(new ArrayList<DailySampleReport>());

		try {
			if (from != null && to != null && !from.before(to)) {
				logger.log(Level.WARNING,
						"Error from date must be before to date");
			} else {
				EntityManager em = entityManagerFactory.createEntityManager();

				Calendar start = Calendar.getInstance();
				Calendar end = Calendar.getInstance();
				start.setTime(from);
				end.setTime(to);

				while (start.before(end)) {
					Date internalStart = start.getTime();
					start.add(Calendar.DAY_OF_MONTH, 1);
					Date internalEnd = start.getTime();

					CriteriaBuilder cb = em.getCriteriaBuilder();

					CriteriaQuery<Object> cq = cb.createQuery();
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
								String.class, "sampleType");
						criteria.add(cb.equal(jas.get("type"), p));
					}
					if (internalStart != null) {
						ParameterExpression<Date> p = cb.parameter(Date.class,
								"fromDate");
						Path<Date> datePath = jas.get("time");
						criteria.add(cb.greaterThanOrEqualTo(datePath, p));
					}
					if (internalEnd != null) {
						ParameterExpression<Date> p = cb.parameter(Date.class,
								"toDate");
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
					Query q = em.createQuery(cq);
					if (sensorId != null && !"".equals(sensorId)) {
						q.setParameter("sensorId", sensorId);
					}
					if (sampleType != null && !"".equals(sampleType)) {
						q.setParameter("sampleType", sampleType);
					}
					if (from != null) {
						q.setParameter("fromDate", internalStart);
					}
					if (to != null) {
						q.setParameter("toDate", internalEnd);
					}
					Long jass = (Long) q.getSingleResult();

					DailySampleReport dsr = new DailySampleReport();
					dsr.setDate(internalStart);
					dsr.setSampleCount(jass);
					sr.getDailySampleReports().add(dsr);
				}
				em.close();
			}
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Error during samples report creation", e);
		}
		return sr;
	}

	@Override
	public List<Sensor> getSensors(List<String> sensorIds, Date from, Date to) {
		List<Sensor> sensors = new ArrayList<Sensor>();
		try {
			EntityManager em = entityManagerFactory.createEntityManager();
			CriteriaBuilder cb = em.getCriteriaBuilder();

			CriteriaQuery<JpaSensor> cq = cb.createQuery(JpaSensor.class);
			Root<JpaSensor> js = cq.from(JpaSensor.class);
			cq.select(js);
			List<Predicate> criteria = new ArrayList<Predicate>();
			if (sensorIds != null && sensorIds.size() > 0) {
				Expression<String> p = js.get("id");
				criteria.add(p.in(sensorIds));
			}
			if (from != null) {
				ParameterExpression<Date> p = cb.parameter(Date.class,
						"fromDate");
				Path<Date> datePath = js.get("lastSeen");
				criteria.add(cb.greaterThanOrEqualTo(datePath, p));
			}
			if (to != null) {
				ParameterExpression<Date> p = cb
						.parameter(Date.class, "toDate");
				Path<Date> datePath = js.get("lastSeen");
				criteria.add(cb.lessThanOrEqualTo(datePath, p));
			}
			if (criteria.size() == 0) {
				throw new RuntimeException("no criteria");
			} else if (criteria.size() == 1) {
				cq.where(criteria.get(0));
			} else {
				cq.where(cb.and(criteria.toArray(new Predicate[0])));
			}
			TypedQuery<JpaSensor> q = em.createQuery(cq);
			if (from != null) {
				q.setParameter("fromDate", from);
			}
			if (to != null) {
				q.setParameter("toDate", to);
			}
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
			logger.log(Level.SEVERE, "Error during sensors list retrieving", e);
		}
		return sensors;
	}

	@Override
	public void registerSensor(Sensor sensor) {
		if (sensor != null) {
			JpaSensor s = new JpaSensor();
			s.setId(sensor.getId());
			s.setName(sensor.getName());
			s.setDescription(sensor.getDescription());
			s.setLastSeen(sensor.getLastSeen());
			s.setLat(sensor.getLat());
			s.setLng(sensor.getLng());
			EntityManager em = null;
			EntityTransaction tx =null;
			
			try {
				em = entityManagerFactory.createEntityManager();
				tx = em.getTransaction();
				tx.begin();
				em.merge(s);
				tx.commit();
				
			} catch (Exception e) {
				logger.log(Level.SEVERE, "Error during sensor registration", e);
				if(tx.isActive())
					tx.rollback();
			}
			finally {
				if(em.isOpen())
					em.close();
			}
		} else {
			logger.log(Level.WARNING, "sensor must be not null to register it");
		}
	}

	@Override
	public void recordSamples(List<AbstractSample> samples) {
		if (samples != null) {
			List<String> checkList = listSensorsIds();
			EntityManager em = null;
			EntityTransaction transaction =null;
			
			try {
				em = entityManagerFactory.createEntityManager();
				transaction = em.getTransaction();
				
				transaction.begin();
				for (AbstractSample sample : samples) {
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

						registerSensor(s);
					}
					JpaAbstractSample s = new JpaAbstractSample();
					s.setSensorId(sample.getSensorId());
					s.setTime(sample.getTime());
					s.setType(sample.getType());
					s.setValue(localSerializer.get().serialize(sample));

					em.persist(s);
				}
				transaction.commit();
			} catch (Exception e) {
				logger.log(Level.SEVERE, "Error during samples registration", e);
				if(transaction.isActive())
					transaction.rollback();
			}
			finally {
				if(em.isOpen())
					em.close();
			}
		} else {
			logger.log(Level.WARNING,
					"samples must be not null to register them");
		}
	}

	@Override
	public List<String> listSamplesTypes() {
		List<String> result = new ArrayList<String>();

		try {
			EntityManager em = entityManagerFactory.createEntityManager();
			CriteriaBuilder cb = em.getCriteriaBuilder();

			CriteriaQuery<String> cq = cb.createQuery(String.class);
			Root<JpaAbstractSample> js = cq.from(JpaAbstractSample.class);
			cq.multiselect(js.get("type"));
			cq.distinct(true);
			TypedQuery<String> q = em.createQuery(cq);

			result = q.getResultList();

			em.close();
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Error during samples types retrieving", e);
		}

		return result;
	}
}

package com.google.developers.gdgfirenze.memservice;

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

import com.google.developers.gdgfirenze.model.AbstractSample;
import com.google.developers.gdgfirenze.model.DailySampleReport;
import com.google.developers.gdgfirenze.model.SampleReport;
import com.google.developers.gdgfirenze.model.Sensor;
import com.google.developers.gdgfirenze.service.SensormixAdminInterface;
import com.google.developers.gdgfirenze.service.SensormixService;

public class SensormixServiceMemoryImpl implements SensormixService,
		SensormixAdminInterface {

	private static Logger logger = Logger
			.getLogger(SensormixServiceMemoryImpl.class.getName());

	private List<AbstractSample> samples = new ArrayList<AbstractSample>();
	private Map<String, Sensor> sensors = new HashMap<String, Sensor>();

	private Map<String, List<AbstractSample>> samplesForType = new HashMap<String, List<AbstractSample>>();
	private Map<String, List<AbstractSample>> samplesForDate = new HashMap<String, List<AbstractSample>>();
	private Map<String, List<AbstractSample>> samplesForSensorId = new HashMap<String, List<AbstractSample>>();
	private DateFormat df = new SimpleDateFormat("MM-dd-yyyy");

	private boolean inMaintenace;

	@Override
	public List<String> listSensorsIds() {
		List<String> ret = new ArrayList<String>();
		for (Sensor sensor : sensors.values()) {
			if (sensor.getId() != null) {
				ret.add(sensor.getId());
			}
		}
		return ret;
	}

	/**
	 * Search criteria: null sensorId => any sensor null sampleType => any
	 * sample type limitCount != null => stop when ret.size() >= limitCount
	 * limitFrom != null => skip the first limitFrom items
	 */
	@Override
	public List<AbstractSample> getSamples(String sensorId, String sampleType,
			Date from, Date to, Long limitFrom, Long limitCount) {

		List<AbstractSample> ret = new ArrayList<>();
		if (sensorId == null && sampleType == null && from == null
				&& to == null && limitFrom == null && limitCount == null) {
			for (AbstractSample currentSample : samples) {
				ret.add(currentSample);
			}
		} else {
			for (AbstractSample currentSample : samples) {
				if (!isSampleToFilter(currentSample, sensorId, sampleType,
						from, to)) {
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

	@Override
	public long countSamples(String sensorId, String sampleType, Date from,
			Date to) {
		long ret = 0;
		if (sensorId == null && sampleType == null && from == null
				&& to == null) {
			ret = samples.size();
		} else {
			for (AbstractSample currentSample : samples) {
				if (!isSampleToFilter(currentSample, sensorId, sampleType,
						from, to)) {
					ret++;
				}
			}
		}
		return ret;
	}

	/**
	 * TODO: Add implementation: ignore for the moment
	 */
	@Override
	public SampleReport getSampleReport(String sensorId, String sampleType,
			Date from, Date to) {

		SampleReport sr = new SampleReport();
		sr.setSensorId(sensorId);
		sr.setSampleType(sampleType);
		sr.setDailySampleReports(new ArrayList<DailySampleReport>());

		List<AbstractSample> filtered = new ArrayList<AbstractSample>();
		filtered.addAll(samples);
		if (sensorId != null) {
			List<AbstractSample> filteredById = samplesForSensorId
					.get(sensorId);
			if (filteredById != null) {
				filtered.retainAll(filteredById);
			}
		}
		if (sampleType != null) {
			List<AbstractSample> filteredByType = samplesForType
					.get(sampleType);
			if (filteredByType != null) {
				filtered.retainAll(filteredByType);
			}
		}
		if (from != null && to != null) {
			if (!from.before(to)) {
				logger.log(Level.WARNING,
						"Error from date must be before to date");
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

					appoList.retainAll(samplesForDate.get(df
							.format(internalStart)));

					DailySampleReport dsr = new DailySampleReport();
					dsr.setDate(internalStart);
					dsr.setSampleCount(appoList.size());
					sr.getDailySampleReports().add(dsr);
				}
			}
		}

		return sr;
	}

	@Override
	public List<Sensor> getSensors(List<String> sensorIds, Date from, Date to) {
		List<Sensor> ret = new ArrayList<Sensor>();
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
									&& currentSensor.getLastSeen()
											.compareTo(to) <= 0) {
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

	@Override
	public void registerSensor(Sensor sensor) {
		if (!inMaintenace) {
			boolean found = false;
			if (sensors != null && sensors.values() != null
					&& sensors.values().size() > 0) {
				for (Sensor currentSensor : sensors.values()) {
					if (currentSensor.getId().equalsIgnoreCase(sensor.getId())) {
						found = true;
					}
				}
			}
			if (!found) {
				sensors.put(sensor.getId(), sensor);
			}
		} else {
			logger.log(Level.INFO,
					"Cannot register sensor because DataServiceMemoryImpl is in maintenace");
			throw new RuntimeException(
					"Cannot register sensor because DataServiceMemoryImpl is in maintenace");
		}
	}

	@Override
	public void recordSamples(List<AbstractSample> samplesToAdd) {
		if (!inMaintenace) {
			if (samplesToAdd != null && samplesToAdd.size() > 0) {
				for (AbstractSample s : samplesToAdd) {
					samples.add(s);

					// index for type
					List<AbstractSample> partialSamples = samplesForType.get(s
							.getType());
					if (partialSamples == null) {
						partialSamples = new ArrayList<AbstractSample>();
						samplesForType.put(s.getType(), partialSamples);
					}
					partialSamples.add(s);

					// index for sensor
					partialSamples = samplesForSensorId.get(s.getSensorId());
					if (partialSamples == null) {
						partialSamples = new ArrayList<AbstractSample>();
						samplesForSensorId.put(s.getSensorId(), partialSamples);
					}
					partialSamples.add(s);

					// index for date
					String dateFormatted = df.format(s.getTime());
					partialSamples = samplesForDate.get(dateFormatted);
					if (partialSamples == null) {
						partialSamples = new ArrayList<AbstractSample>();
						samplesForDate.put(dateFormatted, partialSamples);
					}
					partialSamples.add(s);
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
	 * TODO: Add implementation: ignore for the moment
	 */
	@Override
	public List<String> listSamplesTypes() {
		List<String> ret = new ArrayList<String>();
		ret.addAll(samplesForType.keySet());
		return ret;
	}

	/**************************************
	 * PRIVATE METHODS: for internal use. *
	 **************************************/

	private boolean isSampleToFilter(AbstractSample currentSample,
			String sensorId, String sampleType, Date from, Date to) {
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
			if (currentSample.getTime().compareTo(from) < 0
					|| currentSample.getTime().compareTo(to) > 0) {
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

	@Override
	public void setInMaintenace(boolean value) {
		inMaintenace = value;
	}

	@Override
	public boolean isInMaintenance() {
		return inMaintenace;
	}

}
package com.google.developers.gdgfirenze.admin.client.tree;

import java.util.List;

import com.google.developers.gdgfirenze.admin.client.cell.SampleCell;
import com.google.developers.gdgfirenze.admin.client.cell.SensorCell;
import com.google.developers.gdgfirenze.admin.client.event.NumberDetectedEvent;
import com.google.developers.gdgfirenze.admin.client.event.NumberDetectedEvent.TypeOfNumberDetected;
import com.google.developers.gdgfirenze.admin.client.service.GwtSensormixService;
import com.google.developers.gdgfirenze.admin.client.service.GwtSensormixServiceAsync;
import com.google.developers.gdgfirenze.model.AbstractSample;
import com.google.developers.gdgfirenze.model.Sensor;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.view.client.AsyncDataProvider;
import com.google.gwt.view.client.HasData;
import com.google.gwt.view.client.Range;
import com.google.gwt.view.client.TreeViewModel;
import com.google.web.bindery.event.shared.EventBus;

public class SensorTreeModel implements TreeViewModel {

	private final class SampleDataProvider extends
			AsyncDataProvider<AbstractSample> {

		private String sensorId;

		public SampleDataProvider(String sensorId) {
			super();
			this.sensorId = sensorId;
		}

		@Override
		protected void onRangeChanged(HasData<AbstractSample> display) {
			final Range range = display.getVisibleRange();
			final int start = range.getStart();
			sensormixService.countSamples(sensorId, null, null, null,
					new AsyncCallback<Long>() {

						@Override
						public void onFailure(Throwable caught) {
							// TODO Error handling
							caught.printStackTrace();
						}

						@Override
						public void onSuccess(Long result) {
							if (result != null) {
								updateRowCount(result.intValue(), true);
							}
						}
					});
			sensormixService.getSamples(sensorId, null, null, null,
					(long) start, (long) range.getLength(),
					new AsyncCallback<List<AbstractSample>>() {

						@Override
						public void onFailure(Throwable caught) {
							// TODO Error handling
							caught.printStackTrace();
						}

						@Override
						public void onSuccess(List<AbstractSample> result) {
							if (result != null) {
								updateRowData(start, result);
							}
						}
					});
		}
	}

	private final class SensorDataProvider extends AsyncDataProvider<Sensor> {

		@Override
		protected void onRangeChanged(HasData<Sensor> display) {
			final Range range = display.getVisibleRange();

			sensormixService.getSensors(null, null, null,
					new AsyncCallback<List<Sensor>>() {

						@Override
						public void onFailure(Throwable caught) {
							// TODO Error handling
							caught.printStackTrace();
						}

						@Override
						public void onSuccess(List<Sensor> result) {
							if (result != null) {
								int start = range.getStart();
								int end = start + range.getLength();
								if (end > result.size()) {
									end = result.size();
								}
								List<Sensor> dataInRange = result.subList(
										start, end);
								updateRowCount(result.size(), true);
								updateRowData(start, dataInRange);
								refreshHeaderCounter(result);
							}
						}

						private void refreshHeaderCounter(
								List<Sensor> sensorCount) {
							eventBus.fireEventFromSource(new NumberDetectedEvent(TypeOfNumberDetected.SENSOR, sensorCount.size()), this);
							
							sensormixService.countSamples(null, null, null, null,
									new AsyncCallback<Long>() {

										@Override
										public void onFailure(Throwable caught) {
											// TODO Error handling
											caught.printStackTrace();
										}

										@Override
										public void onSuccess(Long result) {
											if (result != null) {
												eventBus.fireEventFromSource(new NumberDetectedEvent(TypeOfNumberDetected.SAMPLE, result), this);
											}
										}
									});
						}
					});
		}
	}

	private final GwtSensormixServiceAsync sensormixService = GWT
			.create(GwtSensormixService.class);
	private EventBus eventBus;

	public SensorTreeModel(EventBus eventBus) {
		this.eventBus = eventBus;
	}

	// Get the NodeInfo that provides the children of the specified value.
	public <T> NodeInfo<?> getNodeInfo(final T value) {

		if (value == null) {
			SensorDataProvider dataProvider = new SensorDataProvider();
			// Return a node info that pairs the data with a cell.
			return new DefaultNodeInfo<Sensor>(dataProvider, new SensorCell());
		} else if (value instanceof Sensor) {
			Sensor sensor = (Sensor) value;
			SampleDataProvider sampleDataProvider = new SampleDataProvider(
					sensor.getId());
			return new DefaultNodeInfo<AbstractSample>(sampleDataProvider,
					new SampleCell());
		} else {
			return null;
		}

	}

	// Check if the specified value represents a leaf node. Leaf nodes cannot be
	// opened.
	public boolean isLeaf(Object value) {
		if (value instanceof AbstractSample) {
			return true;
		}
		return false;
	}
}

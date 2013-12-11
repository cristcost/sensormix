/*
 * Copyright 2013, Cristiano Costantini, Giuseppe Gerla, Michele Ficarra, Sergio Ciampi, Stefano
 * Cigheri.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
package com.google.developers.gdgfirenze.admin.client.tree;

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

import java.util.List;

/**
 * The Class SensorTreeModel.
 */
public class SensorTreeModel implements TreeViewModel {

  /**
   * The Class SampleDataProvider.
   */
  private final class SampleDataProvider extends AsyncDataProvider<AbstractSample> {

    /** The sensor id. */
    private String sensorId;

    /**
     * Instantiates a new sample data provider.
     * 
     * @param sensorId
     *          the sensor id
     */
    public SampleDataProvider(String sensorId) {
      super();
      this.sensorId = sensorId;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.google.gwt.view.client.AbstractDataProvider#onRangeChanged(com.google.gwt.view.client
     * .HasData)
     */
    @Override
    protected void onRangeChanged(HasData<AbstractSample> display) {
      final Range range = display.getVisibleRange();
      final int start = range.getStart();
      sensormixService.countSamples(sensorId, null, null, null, new AsyncCallback<Long>() {

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
      sensormixService.getSamples(sensorId, null, null, null, (long) start,
          (long) range.getLength(), new AsyncCallback<List<AbstractSample>>() {

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

  /**
   * The Class SensorDataProvider.
   */
  private final class SensorDataProvider extends AsyncDataProvider<Sensor> {

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.google.gwt.view.client.AbstractDataProvider#onRangeChanged(com.google.gwt.view.client
     * .HasData)
     */
    @Override
    protected void onRangeChanged(HasData<Sensor> display) {
      final Range range = display.getVisibleRange();

      sensormixService.getSensors(null, null, null, new AsyncCallback<List<Sensor>>() {

        @Override
        public void onFailure(Throwable caught) {
          // TODO Error handling
          caught.printStackTrace();
        }

        @Override
        public void onSuccess(List<Sensor> result) {
          if (result != null) {
            final int start = range.getStart();
            int end = start + range.getLength();
            if (end > result.size()) {
              end = result.size();
            }
            final List<Sensor> dataInRange = result.subList(start, end);
            updateRowCount(result.size(), true);
            updateRowData(start, dataInRange);
            refreshHeaderCounter(result);
          }
        }

        private void refreshHeaderCounter(List<Sensor> sensorCount) {
          eventBus.fireEventFromSource(new NumberDetectedEvent(TypeOfNumberDetected.SENSOR,
              sensorCount.size()), this);

          sensormixService.countSamples(null, null, null, null, new AsyncCallback<Long>() {

            @Override
            public void onFailure(Throwable caught) {
              // TODO Error handling
              caught.printStackTrace();
            }

            @Override
            public void onSuccess(Long result) {
              if (result != null) {
                eventBus.fireEventFromSource(new NumberDetectedEvent(TypeOfNumberDetected.SAMPLE,
                    result), this);
              }
            }
          });
        }
      });
    }
  }

  /** The event bus. */
  private EventBus eventBus;

  /** The sensormix service. */
  private final GwtSensormixServiceAsync sensormixService = GWT.create(GwtSensormixService.class);

  /**
   * Instantiates a new sensor tree model.
   * 
   * @param eventBus
   *          the event bus
   */
  public SensorTreeModel(EventBus eventBus) {
    this.eventBus = eventBus;
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.google.gwt.view.client.TreeViewModel#getNodeInfo(java.lang.Object)
   */
  @Override
  public <T> NodeInfo<?> getNodeInfo(final T value) {
    // Get the NodeInfo that provides the children of the specified value.
    if (value == null) {
      final SensorDataProvider dataProvider = new SensorDataProvider();
      // Return a node info that pairs the data with a cell.
      return new DefaultNodeInfo<Sensor>(dataProvider, new SensorCell());
    } else if (value instanceof Sensor) {
      final Sensor sensor = (Sensor) value;
      final SampleDataProvider sampleDataProvider = new SampleDataProvider(sensor.getId());
      return new DefaultNodeInfo<AbstractSample>(sampleDataProvider, new SampleCell());
    } else {
      return null;
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.google.gwt.view.client.TreeViewModel#isLeaf(java.lang.Object)
   */
  @Override
  public boolean isLeaf(Object value) {
    // Check if the specified value represents a leaf node. Leaf nodes cannot be opened.
    if (value instanceof AbstractSample) {
      return true;
    }
    return false;
  }
}

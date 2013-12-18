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
package com.google.developers.gdgfirenze.admin.client;

import com.google.developers.gdgfirenze.admin.client.event.NumberDetectedEvent;
import com.google.developers.gdgfirenze.admin.client.event.NumberDetectedEvent.TypeOfNumberDetected;
import com.google.developers.gdgfirenze.admin.client.service.GwtSensormixServiceAsync;
import com.google.developers.gdgfirenze.admin.client.tree.SensorTreeModel;
import com.google.developers.gdgfirenze.model.Sensor;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.cellview.client.CellBrowser;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.TreeViewModel;
import com.google.web.bindery.event.shared.EventBus;

import java.util.List;

/**
 * The Class SensormixAdminUi.
 */
public class SensormixAdminUi extends Composite {

  /**
   * The Interface SensormixAdminUiUiBinder.
   */
  interface SensormixAdminUiUiBinder extends UiBinder<Widget, SensormixAdminUi> {
  }

  private static final int PERIODREFRESHING = 5000;

  /** The ui binder. */
  private static SensormixAdminUiUiBinder uiBinder = GWT.create(SensormixAdminUiUiBinder.class);

  /** The navigator. */
  @UiField(provided = true)
  CellBrowser navigator;

  /** The num of samples. */
  @UiField
  Label numOfSamples;

  /** The num of sensors. */
  @UiField
  Label numOfSensors;

  private GwtSensormixServiceAsync sensormixService;

  private EventBus eventBus;
  private static boolean init = true;

  /**
   * Instantiates a new sensormix admin ui.
   * 
   * @param eventBus
   *          the event bus
   * @param sensormixService
   */
  public SensormixAdminUi(EventBus eventBus, GwtSensormixServiceAsync sensormixService) {
    this.sensormixService = sensormixService;
    this.eventBus = eventBus;
    final TreeViewModel model = new SensorTreeModel(eventBus, sensormixService);
    navigator = new CellBrowser.Builder<Object>(model, null).build();
    navigator.setDefaultColumnWidth(600);
    initWidget(uiBinder.createAndBindUi(this));
    final Timer timer = new Timer() {
      @Override
      public void run() {
        refreshHeaderCounters();
      }
    };
    timer.scheduleRepeating(PERIODREFRESHING);
  }

  private void refreshHeaderCounters() {
    sensormixService.getSensors(null, null, null, new AsyncCallback<List<Sensor>>() {

      @Override
      public void onFailure(Throwable caught) {
        // TODO Error handling
        caught.printStackTrace();
      }

      @Override
      public void onSuccess(List<Sensor> result) {
        if (result != null) {
          if (!init) {
            if (numOfSensors.getText().equals("" + result.size())) {
              eventBus.fireEventFromSource(new NumberDetectedEvent(TypeOfNumberDetected.SENSOR,
                  result.size()), this);
            }
          }
          init = false;
          numOfSensors.setText("" + result.size());
        }
      }
    });

    sensormixService.countSamples(null, null, null, null, new AsyncCallback<Long>() {

      @Override
      public void onFailure(Throwable caught) {
        // TODO Error handling
        caught.printStackTrace();
      }

      @Override
      public void onSuccess(Long result) {
        if (result != null) {
          numOfSamples.setText("" + result);
        }
      }
    });
  }

}

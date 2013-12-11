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
import com.google.developers.gdgfirenze.admin.client.event.NumberDetectedEventHandler;
import com.google.developers.gdgfirenze.admin.client.tree.SensorTreeModel;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.cellview.client.CellBrowser;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.TreeViewModel;
import com.google.web.bindery.event.shared.EventBus;

/**
 * The Class SensormixAdminUi.
 */
public class SensormixAdminUi extends Composite {

  /**
   * The Interface SensormixAdminUiUiBinder.
   */
  interface SensormixAdminUiUiBinder extends UiBinder<Widget, SensormixAdminUi> {
  }

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

  /**
   * Instantiates a new sensormix admin ui.
   * 
   * @param eventBus
   *          the event bus
   */
  public SensormixAdminUi(EventBus eventBus) {
    final TreeViewModel model = new SensorTreeModel(eventBus);
    navigator = new CellBrowser.Builder<Object>(model, null).build();
    navigator.setDefaultColumnWidth(600);
    initWidget(uiBinder.createAndBindUi(this));
    eventBus.addHandler(NumberDetectedEvent.TYPE, new NumberDetectedEventHandler() {

      @Override
      public void onNotificationEvent(NumberDetectedEvent event) {
        if (this != event.getSource() && event.getTypeOfNumberDetected() != null) {
          switch (event.getTypeOfNumberDetected()) {
            case SAMPLE:
              numOfSamples.setText("" + event.getDetectedNumber());
              break;
            case SENSOR:
              numOfSensors.setText("" + event.getDetectedNumber());
              break;
            default:
              break;
          }
        }
      }
    });
  }

}

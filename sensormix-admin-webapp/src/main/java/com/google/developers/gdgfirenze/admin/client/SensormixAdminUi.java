package com.google.developers.gdgfirenze.admin.client;

import com.google.developers.gdgfirenze.model.Sensor;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiFactory;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.cellview.client.CellList;
import com.google.gwt.user.cellview.client.HasKeyboardPagingPolicy.KeyboardPagingPolicy;
import com.google.gwt.user.cellview.client.HasKeyboardSelectionPolicy.KeyboardSelectionPolicy;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.ProvidesKey;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;

import java.util.List;

public class SensormixAdminUi extends Composite {

  private final GwtSensormixServiceAsync sensormixService = GWT.create(GwtSensormixService.class);

  private static SensormixAdminUiUiBinder uiBinder = GWT.create(SensormixAdminUiUiBinder.class);

  @UiField
  CellList<Sensor> sensorList;

  interface SensormixAdminUiUiBinder extends UiBinder<Widget, SensormixAdminUi> {
  }

  public SensormixAdminUi() {
    initWidget(uiBinder.createAndBindUi(this));
  }

  public SensormixAdminUi(String firstName) {
    initWidget(uiBinder.createAndBindUi(this));
  }

  public static final ProvidesKey<Sensor> KEY_PROVIDER = new ProvidesKey<Sensor>() {
    @Override
    public Object getKey(Sensor sensor) {
      return sensor == null ? null : sensor.getId();
    }
  };

  @UiFactory
  CellList<Sensor> makeSensorList() { // method name is insignificant

    SensorCell cell = new SensorCell();
    final CellList<Sensor> ret = new CellList<Sensor>(cell, KEY_PROVIDER);

    ret.setPageSize(30);
    ret.setKeyboardPagingPolicy(KeyboardPagingPolicy.INCREASE_RANGE);
    ret.setKeyboardSelectionPolicy(KeyboardSelectionPolicy.BOUND_TO_SELECTION);

    // Add a selection model so we can select cells.
    final SingleSelectionModel<Sensor> selectionModel =
        new SingleSelectionModel<Sensor>(KEY_PROVIDER);
    ret.setSelectionModel(selectionModel);
    selectionModel.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
      public void onSelectionChange(SelectionChangeEvent event) {
        // contactForm.setContact(selectionModel.getSelectedObject());
      }
    });

    sensormixService.listSensorsIds(new AsyncCallback<List>() {

      @Override
      public void onSuccess(List result) {
        // TODO Auto-generated method stub
        List<String> ids = result;
        sensormixService.getSensors(ids, null, null, new AsyncCallback<List>() {

          @Override
          public void onSuccess(List result) {
            ret.setRowData(result);
          }

          @Override
          public void onFailure(Throwable caught) {
            // TODO Auto-generated method stub
          }
        });
      }

      @Override
      public void onFailure(Throwable caught) {
        // TODO Auto-generated method stub

      }
    });

    return ret;
  }

}

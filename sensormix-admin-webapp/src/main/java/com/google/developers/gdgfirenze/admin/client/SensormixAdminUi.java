package com.google.developers.gdgfirenze.admin.client;

import com.google.developers.gdgfirenze.model.AbstractSample;
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
import com.google.gwt.user.client.ui.DateLabel;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.NumberLabel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.ProvidesKey;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;

import java.util.List;

public class SensormixAdminUi extends Composite {

  interface SensormixAdminUiUiBinder extends UiBinder<Widget, SensormixAdminUi> {
  }

  private final class SensorListUpdateHandler implements AsyncCallback<List> {

    @Override
    public void onFailure(Throwable caught) {
      infoMessage.setText("Error while loading Sensor list!");
    }

    @Override
    public void onSuccess(List result) {
      infoMessage.setText("Loading Sensors...");
      sensormixService.getSensors(result, null, null, new SensorUpdateHandler());
    }
  }

  private final class SensorSelectionHandler implements SelectionChangeEvent.Handler {
    public void onSelectionChange(SelectionChangeEvent event) {
      if (sensorSelectionModel.getSelectedObject() != null) {
        Sensor sensor = sensorSelectionModel.getSelectedObject();

        sensorDetailPanel.setVisible(true);

        sensorDescription.setText(sensor.getDescription());
        sensorName.setText(sensor.getName());
        sensorId.setText(sensor.getId());
        sensorLastSeen.setValue(sensor.getLastSeen());
        sensorLat.setValue(sensor.getLat());
        sensorLng.setValue(sensor.getLng());

      } else {
        sensorDetailPanel.setVisible(false);
      }
    }
  }

  private final class SensorUpdateHandler implements AsyncCallback<List> {
    @Override
    public void onFailure(Throwable caught) {
      infoMessage.setText("Error while loading Sensors!");
    }

    @Override
    public void onSuccess(List result) {
      infoMessage.setText("");
      sensorList.setRowData(result);
    }
  }

  public static final ProvidesKey<Sensor> KEY_PROVIDER = new ProvidesKey<Sensor>() {
    @Override
    public Object getKey(Sensor sensor) {
      return sensor == null ? null : sensor.getId();
    }
  };

  private static SensormixAdminUiUiBinder uiBinder = GWT.create(SensormixAdminUiUiBinder.class);

  @UiField
  Label infoMessage;

  @UiField
  CellList<AbstractSample> sampleList;
  @UiField
  Label sensorDescription;
  @UiField
  HTMLPanel sensorDetailPanel;

  @UiField
  Label sensorId;

  @UiField
  DateLabel sensorLastSeen;
  @UiField
  NumberLabel<Double> sensorLat;

  @UiField
  CellList<Sensor> sensorList;

  @UiField
  NumberLabel<Double> sensorLng;

  @UiField
  Label sensorName;

  private final GwtSensormixServiceAsync sensormixService = GWT.create(GwtSensormixService.class);

  private SingleSelectionModel<Sensor> sensorSelectionModel;

  public SensormixAdminUi() {
    initWidget(uiBinder.createAndBindUi(this));
  }

  public SensormixAdminUi(String firstName) {
    initWidget(uiBinder.createAndBindUi(this));
  }

  @Override
  protected void onLoad() {
    reloadSensors();
    sensorDetailPanel.setVisible(false);
  }

  @UiFactory
  CellList<Sensor> makeSensorList() { // method name is insignificant

    SensorCell cell = new SensorCell();
    final CellList<Sensor> ret = new CellList<Sensor>(cell, KEY_PROVIDER);

    ret.setPageSize(10);
    ret.setKeyboardPagingPolicy(KeyboardPagingPolicy.INCREASE_RANGE);
    ret.setKeyboardSelectionPolicy(KeyboardSelectionPolicy.BOUND_TO_SELECTION);

    sensorSelectionModel = new SingleSelectionModel<Sensor>(KEY_PROVIDER);
    ret.setSelectionModel(sensorSelectionModel);
    sensorSelectionModel.addSelectionChangeHandler(new SensorSelectionHandler());

    return ret;
  }

  private void reloadSensors() {
    // update sensors
    infoMessage.setText("Loading Sensor list...");
    sensormixService.listSensorsIds(new SensorListUpdateHandler());
  }

}

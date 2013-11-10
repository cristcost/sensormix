package com.google.developers.gdgfirenze.admin.client;

import com.google.developers.gdgfirenze.model.Sensor;
import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;

public class SensorCell extends AbstractCell<Sensor> {

  @Override
  public void render(Context context, Sensor sensor, SafeHtmlBuilder sb) {

    // Value can be null, so do a null check..
    if (sensor == null) {
      return;
    }

    sb.appendHtmlConstant("<span title=\"+sensor.getId()+\">");
    sb.appendEscaped(sensor.getName());
    sb.appendHtmlConstant("</span>");
    sb.appendHtmlConstant("<span>");
    sb.appendEscaped(sensor.getDescription());
    sb.appendHtmlConstant("</span>");

  }

}

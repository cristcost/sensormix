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
package com.google.developers.gdgfirenze.admin.client.cell;

import com.google.developers.gdgfirenze.admin.client.icon.IconBundle;
import com.google.developers.gdgfirenze.model.AbstractSample;
import com.google.developers.gdgfirenze.model.NumericValueSample;
import com.google.developers.gdgfirenze.model.PositionSample;
import com.google.developers.gdgfirenze.model.StringValueSample;
import com.google.developers.gdgfirenze.model.WifiSignalSample;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.resources.client.ResourcePrototype;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.user.client.ui.Image;

/**
 * The Class SampleCell.
 */
public class SampleCell extends AbstractCell<AbstractSample> {

  /** The Constant ICON_BUNDLE. */
  public static final IconBundle ICON_BUNDLE = GWT.create(IconBundle.class);

  /*
   * (non-Javadoc)
   * 
   * @see com.google.gwt.cell.client.AbstractCell#render(com.google.gwt.cell.client.Cell.Context,
   * java.lang.Object, com.google.gwt.safehtml.shared.SafeHtmlBuilder)
   */
  @Override
  public void render(Context context, AbstractSample sample, SafeHtmlBuilder sb) {
    // Value can be null, so do a null check..
    if (sample == null) {
      return;
    }
    sb.appendHtmlConstant("<table><tr><td colspan=\"2\">");
    if (sample.getType() != null) {
      final ResourcePrototype icon = ICON_BUNDLE.getResource(findIconName(sample.getType()));
      if (icon != null) {
        final Image image = new Image((ImageResource) icon);
        image.setTitle(sample.getType());
        sb.appendHtmlConstant(image.toString());
      } else {
        sb.appendEscaped(sample.getType());
      }
    }
    sb.appendEscaped(sample.getTime().toString());
    sb.appendHtmlConstant("</td></tr>");
    if (sample instanceof NumericValueSample) {
      final NumericValueSample numericValueSample = (NumericValueSample) sample;
      sb.appendHtmlConstant(getTableRow("Value", numericValueSample.getValue()));
    } else if (sample instanceof StringValueSample) {
      final StringValueSample stringValueSample = (StringValueSample) sample;
      sb.appendHtmlConstant(getTableRow("Value", stringValueSample.getValue()));
    } else if (sample instanceof StringValueSample) {
      final StringValueSample stringValueSample = (StringValueSample) sample;
      sb.appendHtmlConstant("<tr><td>Value: </td><td>");
      sb.appendEscaped(stringValueSample.getValue());
      sb.appendHtmlConstant("</td><td>");
    } else if (sample instanceof PositionSample) {
      final PositionSample positionSample = (PositionSample) sample;
      sb.appendHtmlConstant(getTableRow(
          "Latitude,Longitude",
          fromDoubleToString(positionSample.getLat()) + ","
              + fromDoubleToString(positionSample.getLng())));
      sb.appendHtmlConstant(getTableRow("Altitude", positionSample.getAlt()));
      sb.appendHtmlConstant(getTableRow("Bearing", positionSample.getBearing()));
      sb.appendHtmlConstant(getTableRow("Speed", positionSample.getSpeed()));
      sb.appendHtmlConstant(getTableRow("Accuracy", positionSample.getAccuracy()));
    } else if (sample instanceof WifiSignalSample) {
      final WifiSignalSample wifiSignalSample = (WifiSignalSample) sample;
      sb.appendHtmlConstant(getTableRow("Frequency", wifiSignalSample.getFrequency()));
      sb.appendHtmlConstant(getTableRow("Level", wifiSignalSample.getLevel()));
      sb.appendHtmlConstant(getTableRow("BSSID", wifiSignalSample.getBssid()));
      sb.appendHtmlConstant(getTableRow("SSID", wifiSignalSample.getSsid()));
      sb.appendHtmlConstant(getTableRow("Capabilities", wifiSignalSample.getCapabilities()));
    }
    sb.appendHtmlConstant("</table>");
  }

  /**
   * Find icon name.
   * 
   * @param type
   *          the type
   * @return the string
   */
  private String findIconName(String type) {
    try {
      String[] split = type.split("/");
      return split[split.length - 1];
    } catch (Exception e) {
      return null;
    }
  }

  /**
   * From double to string.
   * 
   * @param value
   *          the value
   * @return the string
   */
  private String fromDoubleToString(Double value) {
    String ret = "0";
    if (value != null) {
      ret = value.toString();
    }
    return ret;
  }

  /**
   * Gets the table row.
   * 
   * @param firstColumn
   *          the first column
   * @param secondColumn
   *          the second column
   * @return the table row
   */
  private String getTableRow(String firstColumn, Object secondColumn) {
    String secondColAsString = secondColumn.toString();
    if (secondColumn instanceof Double) {
      secondColAsString = fromDoubleToString((Double) secondColumn);
    }
    return "<tr><td>" + firstColumn + ": </td><td>" + secondColAsString + "</td></tr>";
  }

}

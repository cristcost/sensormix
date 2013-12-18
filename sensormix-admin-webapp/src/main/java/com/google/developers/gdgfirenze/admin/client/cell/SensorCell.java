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
import com.google.developers.gdgfirenze.model.Sensor;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.shared.DateTimeFormat;
import com.google.gwt.i18n.shared.DateTimeFormat.PredefinedFormat;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.resources.client.ResourcePrototype;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.user.client.ui.Image;

/**
 * The Class SensorCell.
 */
public class SensorCell extends AbstractCell<Sensor> {

  /** The Constant df. */
  private static final DateTimeFormat df = 
      DateTimeFormat.getFormat(PredefinedFormat.DATE_TIME_SHORT);

  /** The Constant ICON_BUNDLE. */
  public static final IconBundle ICON_BUNDLE = GWT.create(IconBundle.class);

  /**
   * Find icon name.
   * 
   * @param type
   *          the type
   * @return the string
   */
  private String findIconName(String type) {
    try {
      final String[] split = type.split("/");
      return split[split.length - 1];
    } catch (Exception e) {
      return null;
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.google.gwt.cell.client.AbstractCell#render(com.google.gwt.cell.client.Cell.Context,
   * java.lang.Object, com.google.gwt.safehtml.shared.SafeHtmlBuilder)
   */
  @Override
  public void render(Context context, Sensor sensor, SafeHtmlBuilder sb) {

    // Value can be null, so do a null check..
    if (sensor == null) {
      return;
    }

    sb.appendHtmlConstant("<table><tr><td title=\"" + sensor.getId() + "\"><strong>");
    sb.appendEscaped(sensor.getName());
    sb.appendHtmlConstant("</strong>");
    if (sensor.getType() != null) {
      sb.appendHtmlConstant("</td><td>");
      final ResourcePrototype icon = ICON_BUNDLE.getResource(findIconName(sensor.getType()));
      if (icon != null) {
        final Image image = new Image((ImageResource) icon);
        image.setTitle(sensor.getType());
        sb.appendHtmlConstant(image.toString());
      } else {
        sb.appendEscaped(sensor.getType());
      }
    }
    sb.appendHtmlConstant("</td></tr><tr><td style=\"font-size:0.9em;\">");
    sb.appendEscaped(sensor.getDescription());
    if (sensor.getLastSeen() != null || sensor.getLat() != null) {
      sb.appendHtmlConstant("</td></tr><tr><td style=\"font-size:0.9em;\"><dl>");
      if (sensor.getLastSeen() != null) {
        sb.appendHtmlConstant("<dt>Last Seen:</dt><dd>");
        sb.appendEscaped(df.format(sensor.getLastSeen()));
        sb.appendHtmlConstant("</dd>");
      }

      if (sensor.getLat() != null) {
        sb.appendHtmlConstant("<dt>Position:</dt><dd>");
        sb.appendEscaped(sensor.getLat() + "," + sensor.getLng());
        sb.appendHtmlConstant("</dd>");
      }
      sb.appendHtmlConstant("</dl>");
    }

    sb.appendHtmlConstant("</td></td></table>");
  }
}

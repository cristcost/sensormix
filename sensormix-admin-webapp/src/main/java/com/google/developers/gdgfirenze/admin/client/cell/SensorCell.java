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

public class SensorCell extends AbstractCell<Sensor> {

	private static final DateTimeFormat df = DateTimeFormat
			.getFormat(PredefinedFormat.DATE_TIME_SHORT);
	
	public static final IconBundle ICON_BUNDLE = GWT.create(IconBundle.class);


	private String findIconName(String type) {
		try {
			String[] split = type.split("/");
			return split[split.length - 1];
		} catch (Exception e) {
			return null;
		}
	}
	
	@Override
	public void render(Context context, Sensor sensor, SafeHtmlBuilder sb) {

		// Value can be null, so do a null check..
		if (sensor == null) {
			return;
		}

		sb.appendHtmlConstant("<table><tr><td title=\"" + sensor.getId()
				+ "\">");
		sb.appendEscaped(sensor.getName());
		if (sensor.getType() != null) {
			sb.appendHtmlConstant("</td><td>");
			ResourcePrototype icon = ICON_BUNDLE
					.getResource(findIconName(sensor.getType()));
			if (icon != null) {
				 Image image = new Image((ImageResource) icon);
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

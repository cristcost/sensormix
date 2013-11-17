package com.google.developers.gdgfirenze.admin.client.cell;

import com.google.developers.gdgfirenze.model.Sensor;
import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.i18n.shared.DateTimeFormat;
import com.google.gwt.i18n.shared.DateTimeFormat.PredefinedFormat;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;

public class SensorCell extends AbstractCell<Sensor> {

	private static final DateTimeFormat df = DateTimeFormat
			.getFormat(PredefinedFormat.DATE_TIME_SHORT);

	@Override
	public void render(Context context, Sensor sensor, SafeHtmlBuilder sb) {

		// Value can be null, so do a null check..
		if (sensor == null) {
			return;
		}

		sb.appendHtmlConstant("<table><tr><td title=\"" + sensor.getId()
				+ "\">");
		sb.appendEscaped(sensor.getName());
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

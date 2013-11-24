package com.google.developers.gdgfirenze.admin.client.cell;

import com.google.developers.gdgfirenze.admin.client.icon.IconBundle;
import com.google.developers.gdgfirenze.model.AbstractSample;
import com.google.developers.gdgfirenze.model.NumericValueSample;
import com.google.developers.gdgfirenze.model.PositionSample;
import com.google.developers.gdgfirenze.model.WifiSignalSample;
import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.resources.client.ResourcePrototype;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.user.client.ui.Image;

public class SampleCell extends AbstractCell<AbstractSample> {

	public static final IconBundle ICON_BUNDLE = GWT.create(IconBundle.class);

	@Override
	public void render(Context context, AbstractSample sample,
			SafeHtmlBuilder sb) {
		// Value can be null, so do a null check..
		if (sample == null) {
			return;
		}
		sb.appendHtmlConstant("<table><tr><td>");
		sb.appendEscaped(sample.getTime().toString());
		if (sample.getType() != null) {
			sb.appendHtmlConstant("</td><td>");
			ResourcePrototype icon = ICON_BUNDLE
					.getResource(findIconName(sample.getType()));
			if (icon != null) {
				// String img = AbstractImagePrototype
				// .create((ImageResource) icon).getHTML();
				 Image image = new Image((ImageResource) icon);
				 image.setTitle(sample.getType());
				sb.appendHtmlConstant(image.toString());
			} else {
				sb.appendEscaped(sample.getType());
			}
		}
		sb.appendHtmlConstant("</td></tr>");
		if (sample instanceof NumericValueSample) {
			NumericValueSample numericValueSample = (NumericValueSample) sample;
			sb.appendHtmlConstant("<tr><td>Value: </td><td>");
			sb.appendEscaped(fromDoubleToString(numericValueSample.getValue()));
			sb.appendHtmlConstant("</td><td>");
		} else if (sample instanceof PositionSample) {
			PositionSample positionSample = (PositionSample) sample;
			sb.appendHtmlConstant("<tr><td>Latitude,Longitude: </td><td>");
			sb.appendEscaped(fromDoubleToString(positionSample.getLat()) + "," + fromDoubleToString(positionSample.getLng()));
			sb.appendHtmlConstant("</td><td>");
			sb.appendHtmlConstant("<tr><td>Altitude: </td><td>");
			sb.appendEscaped(fromDoubleToString(positionSample.getAlt()));
			sb.appendHtmlConstant("</td><td>");
			sb.appendHtmlConstant("<tr><td>Bearing: </td><td>");
			sb.appendEscaped(fromDoubleToString(positionSample.getBearing()));
			sb.appendHtmlConstant("</td><td>");
			sb.appendHtmlConstant("<tr><td>Speed: </td><td>");
			sb.appendEscaped(fromDoubleToString(positionSample.getSpeed()));
			sb.appendHtmlConstant("</td><td>");
			sb.appendHtmlConstant("<tr><td>Accuracy: </td><td>");
			sb.appendEscaped(fromDoubleToString(positionSample.getAccuracy()));
			sb.appendHtmlConstant("</td><td>");
		} else if (sample instanceof WifiSignalSample) {
			WifiSignalSample wifiSignalSample = (WifiSignalSample) sample;
			sb.appendHtmlConstant("<tr><td>Frequency: </td><td>");
			sb.appendEscaped(fromDoubleToString(wifiSignalSample.getFrequency()));
			sb.appendHtmlConstant("</td><td>");
			sb.appendHtmlConstant("<tr><td>Level: </td><td>");
			sb.appendEscaped(fromDoubleToString(wifiSignalSample.getLevel()));
			sb.appendHtmlConstant("</td><td>");
			sb.appendHtmlConstant("<tr><td>BSSID: </td><td>");
			sb.appendEscaped(wifiSignalSample.getBssid());
			sb.appendHtmlConstant("</td><td>");
			sb.appendHtmlConstant("<tr><td>SSID: </td><td>");
			sb.appendEscaped(wifiSignalSample.getSsid());
			sb.appendHtmlConstant("</td><td>");
			sb.appendHtmlConstant("<tr><td>Capabilities: </td><td>");
			sb.appendEscaped(wifiSignalSample.getCapabilities());
			sb.appendHtmlConstant("</td><td>");			
		}
		sb.appendHtmlConstant("</table>");
	}

	private String findIconName(String type) {
		try {
			String[] split = type.split("/");
			return split[split.length - 1];
		} catch (Exception e) {
			return null;
		}
	}
	private String fromDoubleToString(Double value) {
		String ret = "0";
		if(value != null) {
			ret = value.toString();
		}
		return ret;			
	}

}

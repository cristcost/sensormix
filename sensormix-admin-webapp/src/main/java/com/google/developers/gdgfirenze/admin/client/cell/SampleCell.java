package com.google.developers.gdgfirenze.admin.client.cell;

import com.google.developers.gdgfirenze.admin.client.icon.IconBundle;
import com.google.developers.gdgfirenze.model.AbstractSample;
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
		sb.appendHtmlConstant("</td></td></table>");
	}

	private String findIconName(String type) {
		try {
			String[] split = type.split("/");
			return split[split.length - 1];
		} catch (Exception e) {
			return null;
		}
	}

}

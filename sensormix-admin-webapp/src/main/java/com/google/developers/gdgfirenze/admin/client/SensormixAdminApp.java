package com.google.developers.gdgfirenze.admin.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.SimpleEventBus;

/**
 * GWT Web App entry point.
 */
public class SensormixAdminApp implements EntryPoint {
	public void onModuleLoad() {
		EventBus eventBus = GWT.create(SimpleEventBus.class);
		SensormixAdminUi ui = new SensormixAdminUi(eventBus);
		RootLayoutPanel.get().add(ui);
	}
}

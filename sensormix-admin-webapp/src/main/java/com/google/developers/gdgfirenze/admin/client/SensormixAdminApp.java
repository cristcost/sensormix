package com.google.developers.gdgfirenze.admin.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootLayoutPanel;

/**
 * GWT Web App entry point.
 */
public class SensormixAdminApp implements EntryPoint {
	public void onModuleLoad() {
		SensormixAdminUi ui = new SensormixAdminUi();
		RootLayoutPanel.get().add(ui);
	}
}

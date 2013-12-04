package com.google.developers.gdgfirenze.admin.client;

import com.google.developers.gdgfirenze.admin.client.tree.SensorTreeModel;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.cellview.client.CellBrowser;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.TreeViewModel;

public class SensormixAdminUi extends Composite {

	interface SensormixAdminUiUiBinder extends
			UiBinder<Widget, SensormixAdminUi> {
	}

	private static SensormixAdminUiUiBinder uiBinder = GWT
			.create(SensormixAdminUiUiBinder.class);


	@UiField(provided = true)
	CellBrowser navigator;

	public SensormixAdminUi() {
		TreeViewModel model = new SensorTreeModel();
		navigator = new CellBrowser.Builder<Object>(model, null).build();
		navigator.setDefaultColumnWidth(600);
		initWidget(uiBinder.createAndBindUi(this));
	}

}

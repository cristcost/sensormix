package com.google.developers.gdgfirenze.admin.client;

import com.google.developers.gdgfirenze.admin.client.event.NumberDetectedEvent;
import com.google.developers.gdgfirenze.admin.client.event.NumberDetectedEventHandler;
import com.google.developers.gdgfirenze.admin.client.tree.SensorTreeModel;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.cellview.client.CellBrowser;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.TreeViewModel;
import com.google.web.bindery.event.shared.EventBus;

public class SensormixAdminUi extends Composite {

	interface SensormixAdminUiUiBinder extends
			UiBinder<Widget, SensormixAdminUi> {
	}

	private static SensormixAdminUiUiBinder uiBinder = GWT
			.create(SensormixAdminUiUiBinder.class);


	@UiField(provided = true)
	CellBrowser navigator;
	@UiField 
	Label numOfSamples;
	@UiField 
	Label numOfSensors;

	public SensormixAdminUi(EventBus eventBus) {
		TreeViewModel model = new SensorTreeModel(eventBus);
		navigator = new CellBrowser.Builder<Object>(model, null).build();
		navigator.setDefaultColumnWidth(600);
		initWidget(uiBinder.createAndBindUi(this));
		eventBus.addHandler(NumberDetectedEvent.TYPE, new NumberDetectedEventHandler() {
			
			@Override
			public void onNotificationEvent(NumberDetectedEvent event) {
				if(this!= event.getSource() && event.getTypeOfNumberDetected() != null) {
					switch (event.getTypeOfNumberDetected()) {
					case SAMPLE:
						numOfSamples.setText("" + event.getDetectedNumber());
						break;
					case SENSOR:
						numOfSensors.setText("" + event.getDetectedNumber());
						break;
					default:
						break;
					}
				}
			}
		});
	}

}

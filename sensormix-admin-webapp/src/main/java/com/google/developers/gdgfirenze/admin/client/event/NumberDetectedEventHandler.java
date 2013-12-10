package com.google.developers.gdgfirenze.admin.client.event;

import com.google.gwt.event.shared.EventHandler;

public interface NumberDetectedEventHandler extends EventHandler {
	void onNotificationEvent(NumberDetectedEvent event);
}

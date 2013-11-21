package com.google.developers.gdgfirenze.admin.client.icon;

import com.google.gwt.resources.client.ClientBundleWithLookup;
import com.google.gwt.resources.client.ImageResource;

public interface IconBundle extends ClientBundleWithLookup {
	
	  @Source("wifi.png")
	  ImageResource wifi_signal();

	  @Source("battery.png")
	  ImageResource battery_level();

}

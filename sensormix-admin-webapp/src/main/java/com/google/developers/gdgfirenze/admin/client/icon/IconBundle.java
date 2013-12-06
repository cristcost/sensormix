package com.google.developers.gdgfirenze.admin.client.icon;

import com.google.gwt.resources.client.ClientBundleWithLookup;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.resources.client.ImageResource.ImageOptions;

public interface IconBundle extends ClientBundleWithLookup {

	@Source("android.png")
	@ImageOptions(height = 32, width = 32)
	ImageResource android();

	@Source("arduino.png")
	@ImageOptions(height = 32, width = 32)
	ImageResource arduino();

	@Source("battery.png")
	@ImageOptions(height = 32, width = 32)
	ImageResource battery_level();

	@Source("light.png")
	@ImageOptions(height = 32, width = 32)
	ImageResource light();

	@Source("nfc.png")
	@ImageOptions(height = 32, width = 32)
	ImageResource nfc();

	@Source("position.png")
	@ImageOptions(height = 32, width = 32)
	ImageResource phone_gps();

	@Source("temperature.png")
	@ImageOptions(height = 32, width = 32)
	ImageResource temp();

	@Source("wifi.png")
	@ImageOptions(height = 32, width = 32)
	ImageResource wifi_signal();

}

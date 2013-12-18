/*
 * Copyright 2013, Cristiano Costantini, Giuseppe Gerla, Michele Ficarra, Sergio Ciampi, Stefano
 * Cigheri.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
package com.google.developers.gdgfirenze.admin.client.icon;
// CHECKSTYLE:OFF
import com.google.gwt.resources.client.ClientBundleWithLookup;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.resources.client.ImageResource.ImageOptions;

/**
 * The Interface IconBundle.
 */
public interface IconBundle extends ClientBundleWithLookup {

  /**
   * Android.
   *
   * @return the image resource
   */
  @Source("android.png")
  @ImageOptions(height = 32, width = 32)
  ImageResource android();

  /**
   * Arduino.
   *
   * @return the image resource
   */
  @Source("arduino.png")
  @ImageOptions(height = 32, width = 32)
  ImageResource arduino();

  /**
   * Battery_level.
   *
   * @return the image resource
   */
  @Source("battery.png")
  @ImageOptions(height = 32, width = 32)
  ImageResource battery_level();

  /**
   * Light.
   *
   * @return the image resource
   */
  @Source("light.png")
  @ImageOptions(height = 32, width = 32)
  ImageResource light();

  /**
   * Nfc.
   *
   * @return the image resource
   */
  @Source("nfc.png")
  @ImageOptions(height = 32, width = 32)
  ImageResource nfc();

  /**
   * Phone_gps.
   *
   * @return the image resource
   */
  @Source("position.png")
  @ImageOptions(height = 32, width = 32)
  ImageResource phone_gps();
  /**
   * Temp.
   *
   * @return the image resource
   */
  @Source("temperature.png")
  @ImageOptions(height = 32, width = 32)
  ImageResource temp();

  /**
   * Wifi_signal.
   *
   * @return the image resource
   */
  @Source("wifi.png")
  @ImageOptions(height = 32, width = 32)
  ImageResource wifi_signal();
}

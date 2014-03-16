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
package com.google.developers.gdgfirenze.mockep;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * The Class ArduinoSimulator.
 * 
 * It simulates the messages sent by the arduino sensormix project.
 */
public class AndroidSimulator {

  private static final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

  public static void main(String[] args) throws InterruptedException {

    for (int i = 0; i < 10000; i++) {
      try {
        Thread.sleep(5000);
        JSONObject data = createJsonUpdatePacket();
        postData("http://localhost:10080/sensormixSamplesEndpoint", data);
      } catch (JSONException | IOException e) {
        System.out.println("Error on sending sample: " + e.getMessage());
      }
    }
  }

  private static void postData(String url, JSONObject jsonSamplePacket)
    throws ClientProtocolException, IOException {

    HttpParams myParams = new BasicHttpParams();
    HttpConnectionParams.setConnectionTimeout(myParams, 10000);
    HttpConnectionParams.setSoTimeout(myParams, 10000);
    HttpClient httpclient = new DefaultHttpClient(myParams);

    HttpPost httppost = new HttpPost(url.toString());
    httppost.setHeader("Content-type", "application/json");

    StringEntity se = new StringEntity(jsonSamplePacket.toString());
    se.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
    httppost.setEntity(se);

    HttpResponse response = httpclient.execute(httppost);

    String temp = EntityUtils.toString(response.getEntity());
    System.out.println("JSON post response: " + temp);
  }

  private static JSONObject createJsonUpdatePacket() throws JSONException {

    Random rand = new Random();

    JSONObject root = new JSONObject();

    JSONObject obj = new JSONObject();
    root.put("sample", obj);

    obj.put("device_id", "urn:rixf:org.android/sensor_id");
    obj.put("time", dateFormat.format(new Date()));
    obj.put("battery_level", 10.0 + rand.nextDouble() * 20.0);

    JSONObject positionObj = new JSONObject();

    positionObj.put("lat", 42.5 + rand.nextDouble());
    positionObj.put("lng", 10.5 + rand.nextDouble());
    positionObj.put("alt", 100.0);
    positionObj.put("time", dateFormat.format(new Date()));
    positionObj.put("accuracy", 10.0);
    positionObj.put("bearing", 360.0 * rand.nextDouble());
    positionObj.put("speed", 0.0);

    obj.put("position", positionObj);

    JSONArray scanresultsObj = new JSONArray();
    JSONObject scanObj = new JSONObject();

    scanObj.put("frequency", 2400.0);
    scanObj.put("level", -10.0 - 20.0 * rand.nextDouble());
    scanObj.put("bssid", "BSSID");
    scanObj.put("capabilities", "[]");
    scanObj.put("ssid", "SSID");

    scanresultsObj.put(scanObj);

    obj.put("wifi_scans", scanresultsObj);

    return root;
  }

}

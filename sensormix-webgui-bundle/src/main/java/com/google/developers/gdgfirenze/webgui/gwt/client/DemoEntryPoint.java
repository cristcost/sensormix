/*
 * Copyright 2013 Thales Italia spa.
 * 
 * This program is not yet licensed and this file may not be used under any
 * circumstance.
 */
package com.google.developers.gdgfirenze.webgui.gwt.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class DemoEntryPoint implements EntryPoint {

  public void onModuleLoad() {
    
    final Label helloWorldLabel = new Label();
    
    helloWorldLabel.setText("Hello world on a GWT app!");
    
    RootPanel.get("gwtApp").add(helloWorldLabel);
  }
}

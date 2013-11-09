package com.google.developers.gdgfirenze.admin.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;

public class SensormixAdminApp implements EntryPoint {

  private final GwtSensormixServiceAsync sensormixService = GWT.create(GwtSensormixService.class);

  public void onModuleLoad() {
    final Label helloWorldLabel = new Label();

    helloWorldLabel.setText("Hello world on a GWT app!");

    RootPanel.get().add(helloWorldLabel);

  }
  //
  // greetingService.greetServer(textToServer, new AsyncCallback<String>() {
  // public void onFailure(Throwable caught) {
  // // Show the RPC error message to the user
  // dialogBox.setText("Remote Procedure Call - Failure");
  // serverResponseLabel.addStyleName("serverResponseLabelError");
  // serverResponseLabel.setHTML(SERVER_ERROR);
  // dialogBox.center();
  // closeButton.setFocus(true);
  // }
  //
  // public void onSuccess(String result) {
  // dialogBox.setText("Remote Procedure Call");
  // serverResponseLabel.removeStyleName("serverResponseLabelError");
  // serverResponseLabel.setHTML(result);
  // dialogBox.center();
  // closeButton.setFocus(true);
  // }
  // });
}

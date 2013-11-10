package com.google.developers.gdgfirenze.admin.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;

public class SensormixAdminApp implements EntryPoint {

  public void onModuleLoad() {

    SensormixAdminUi ui = new SensormixAdminUi();
    RootPanel.get().add(ui);

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

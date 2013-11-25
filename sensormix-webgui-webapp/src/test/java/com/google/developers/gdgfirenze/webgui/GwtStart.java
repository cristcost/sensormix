package com.google.developers.gdgfirenze.webgui;

import com.google.gwt.dev.DevMode;
import com.google.gwt.dev.javac.JdtCompiler;
import com.google.gwt.dev.shell.BrowserChannelServer;
import com.google.gwt.dev.shell.BrowserListener;

@Deprecated
public class GwtStart extends DevMode {

  public static void main(String[] args) {
    System.out.println("Starting");

    String[] newArgs =
        ("-noserver -war target/webapp -startupUrl gwt.html"
            + " -logLevel INFO -codeServerPort 9997 "
            + "com.google.developers.gdgfirenze.webgui.gwt.DemoApp").split(" ");

    DevMode.main(newArgs);

    /**
     * TODO: separa i problemi:
     * 
     * prima prova a lanciare il BrowserListener con il codice compilato in
     * precedenza dalla esecuzione di DevMode.main(newArgs);
     * 
     * dopodichè prova a capire come compilare il codice trovando le classi
     * necessarie.
     * 
     */

    // GwtDev dev = new GwtDev();
    // dev.startUp();
    // try {
    // System.in.read();
    // System.out.println(">>> STOPPING EMBEDDED JETTY SERVER");
    // } catch (IOException e) {
    // e.printStackTrace();
    // }

    // Interesting classes
    BrowserListener browserListener = null;
    BrowserChannelServer browserChannelServer = null;
    // com.google.gwt.dev.javac.
    JdtCompiler compiler = null;

    System.out.println("Stopping");
  }

}

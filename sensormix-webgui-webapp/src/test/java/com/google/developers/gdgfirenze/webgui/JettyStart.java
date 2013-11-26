package com.google.developers.gdgfirenze.webgui;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.bio.SocketConnector;
import org.eclipse.jetty.server.ssl.SslSocketConnector;
import org.eclipse.jetty.util.resource.Resource;
import org.eclipse.jetty.util.resource.ResourceCollection;
import org.eclipse.jetty.util.ssl.SslContextFactory;
import org.eclipse.jetty.webapp.WebAppContext;

public class JettyStart {
  public static void main(String[] args) throws Exception {

    Server server = new Server();
    SocketConnector connector = new SocketConnector();

    // Set some timeout options to make debugging easier.
    connector.setMaxIdleTime(3600000);
    connector.setSoLingerTime(-1);
    connector.setPort(8080);
    server.addConnector(connector);

    Resource keystore = Resource.newClassPathResource("/keystore");
    if (keystore != null && keystore.exists()) {
      // if a keystore for a SSL certificate is available, start a SSL
      // connector on port 8443.
      // By default, the quickstart comes with a Apache Wicket Quickstart
      // Certificate that expires about half way september 2021. Do not
      // use this certificate anywhere important as the passwords are
      // available in the source.

      connector.setConfidentialPort(8443);

      SslContextFactory factory = new SslContextFactory();
      factory.setKeyStoreResource(keystore);
      factory.setKeyStorePassword("jettyjetty");
      factory.setTrustStoreResource(keystore);
      factory.setKeyManagerPassword("jettyjetty");
      SslSocketConnector sslConnector = new SslSocketConnector(factory);
      sslConnector.setMaxIdleTime(3600000);
      sslConnector.setPort(8443);
      sslConnector.setAcceptors(4);
      server.addConnector(sslConnector);

      System.out.println("SSL access to the quickstart has been enabled on port 8443");
      System.out.println("You can access the application using SSL on https://localhost:8443");
      System.out.println();
    }

    String includeJarPattern =
        ".*/.*jsp-api-[^/]*\\.jar$|.*/.*jsp-[^/]*\\.jar$|.*/.*taglibs[^/]*\\.jar$";
    // includeJarPattern =
    // ".*/org\\.apache\\.taglibs\\.standard\\.glassfish-1\\.2\\.0\\.v201112081803\\.jar";

    ResourceCollection resources = new ResourceCollection(new String[]{
      "src/main/webapp",
    // "target/webapp",
        });

    WebAppContext webCtx = new WebAppContext();
    webCtx.setServer(server);
    webCtx.setContextPath("/");

    webCtx.setDescriptor("src/test/resources/web/test-web.xml");
    webCtx.setBaseResource(resources);

    webCtx.setAttribute("org.eclipse.jetty.server.webapp.ContainerIncludeJarPattern",
        includeJarPattern);

    server.setHandler(webCtx);

    // BrowserListener gwtBrowserListener = new BrowserListener(null, "0.0.0.0",
    // 9997, null);

    try {
      System.out.println(">>> STARTING EMBEDDED JETTY SERVER, PRESS ANY KEY TO STOP");
      server.start();
      System.in.read();
      System.out.println(">>> STOPPING EMBEDDED JETTY SERVER");
      server.stop();
      server.join();
    } catch (Exception e) {
      e.printStackTrace();
      System.exit(1);
    }
  }
}

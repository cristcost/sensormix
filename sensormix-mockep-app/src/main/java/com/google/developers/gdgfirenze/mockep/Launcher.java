package com.google.developers.gdgfirenze.mockep;

import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;

/**
 * Hello world!
 * 
 */
public class Launcher {

  public static void main(String[] args) throws Exception {
    CamelContext context = new DefaultCamelContext();
    context.addRoutes(new RouteBuilder() {
      public void configure() {
        from("jetty:http://0.0.0.0:8080/test")
        .to("log:dump?showAll=true")
        .choice().when(header(Exchange.HTTP_METHOD).in("POST","PUT"))
        .to("file:target/incoming?fileName=msg-${date:now:yyyyMMdd_HHmmss_SSS}.js")
        .end()
        .setHeader("Content-Type", constant("application/json"))
        .to("velocity:response_template.vm");
      }
    });

    context.start();
    System.out.println("Press ENTER to exit");
    System.in.read();

    System.out.println("exit");
    context.stop();
  }
}

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

import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;

/**
 * The Class for Launching the test endpoints.
 */
public class Launcher {

  public static void main(String[] args) throws Exception {
    CamelContext context = new DefaultCamelContext();
    context.addRoutes(new RouteBuilder() {
      public void configure() {
        from("jetty:http://0.0.0.0:8080/test").to("log:dump?showAll=true").choice().when(
            header(Exchange.HTTP_METHOD).in("POST", "PUT")).to(
            "file:target/incoming?fileName=msg-${date:now:yyyyMMdd_HHmmss_SSS}.js").end().setHeader(
            "Content-Type", constant("application/json")).to("velocity:response_template.vm");

        // Arduino route
        from("mina2:udp://0.0.0.0:10081").to("log:dump?showAll=true").to(
            "file:target/incoming?fileName=msg-${date:now:yyyyMMdd_HHmmss_SSS}.js");
      }
    });

    context.start();
    System.out.println("Press ENTER to exit");
    System.in.read();

    System.out.println("exit");
    context.stop();
  }
}

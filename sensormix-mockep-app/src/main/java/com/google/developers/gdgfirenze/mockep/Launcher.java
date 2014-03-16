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

import com.google.developers.gdgfirenze.protobuf.SensormixProtos.SampleMessage;

import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.netty.ChannelHandlerFactories;
import org.apache.camel.component.netty.ChannelHandlerFactory;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.impl.SimpleRegistry;

import java.util.Date;

/**
 * The Class for Launching the test endpoints.
 */
public class Launcher {

  /**
   * The Class SampleAdapter.
   * 
   * It implements a transformer made with Java.
   */
  public static class SampleAdapter {
    public String transform(SampleMessage message) {
      StringBuilder sb = new StringBuilder();
      sb.append("Device Id: ");
      sb.append(message.getDeviceId());
      sb.append(System.lineSeparator());

      sb.append("Time: ");
      Date date = new Date(message.getTime() * 1000L);
      sb.append(date);
      sb.append(System.lineSeparator());

      sb.append("Faces: ");
      sb.append(message.getFaces());
      sb.append(System.lineSeparator());

      return sb.toString();
    }
  }

  public static void main(String[] args) throws Exception {

    ChannelHandlerFactory decoder =
        ChannelHandlerFactories.newLengthFieldBasedFrameDecoder(2048, 0, 4, 0, 4);

    SimpleRegistry reg = new SimpleRegistry();
    reg.put("decoder", decoder);
    CamelContext context = new DefaultCamelContext(reg);

    context.addRoutes(new RouteBuilder() {
      @Override
      public void configure() {
        from("jetty:http://0.0.0.0:10080/sensormixSamplesEndpoint").to("log:dump?showAll=true").choice().when(
            header(Exchange.HTTP_METHOD).in("POST", "PUT")).to(
            "file:target/incoming?fileName=msg-http-${date:now:yyyyMMdd_HHmmss_SSS}.js").end().setHeader(
            "Content-Type", constant("application/json")).to("velocity:response_template.vm");

        // Arduino route
        from("mina2:udp://0.0.0.0:10081").to("log:dump?showAll=true").to(
            "file:target/incoming?fileName=msg-udp-${date:now:yyyyMMdd_HHmmss_SSS}.js");

        // Protocol buffer route
        from("netty:tcp://0.0.0.0:10082/?decoder=#decoder&sync=false").unmarshal().protobuf(
            SampleMessage.getDefaultInstance()).to("log:dump?showAll=true").bean(
            new SampleAdapter()).to(
            "file:target/incoming?fileName=msg-tcp-${date:now:yyyyMMdd_HHmmss_SSS}.txt");
      }
    });

    context.start();
    System.out.println("Press ENTER to exit");
    System.in.read();

    System.out.println("exit");
    context.stop();
  }

}

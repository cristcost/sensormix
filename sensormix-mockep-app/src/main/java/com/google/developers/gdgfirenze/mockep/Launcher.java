package com.google.developers.gdgfirenze.mockep;

import com.google.developers.gdgfirenze.protobuf.SensormixProtos.SampleMessage;

import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.netty.ChannelHandlerFactories;
import org.apache.camel.component.netty.ChannelHandlerFactory;
import org.apache.camel.component.netty.ShareableChannelHandlerFactory;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.impl.SimpleRegistry;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.handler.codec.oneone.OneToOneDecoder;

import java.util.Date;

/**
 * Hello world!
 * 
 */
public class Launcher {

  public static class SampleAdapter {
    public String transform(SampleMessage message) {
      StringBuilder sb = new StringBuilder();
      sb.append("Device Id: ");
      sb.append(message.getDeviceId());
      sb.append(System.lineSeparator());

      sb.append("Time: ");
      sb.append(new Date(message.getTime()));
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
      public void configure() {
        from("jetty:http://0.0.0.0:8080/test").to("log:dump?showAll=true").choice().when(
            header(Exchange.HTTP_METHOD).in("POST", "PUT")).to(
            "file:target/incoming?fileName=msg-http-${date:now:yyyyMMdd_HHmmss_SSS}.js").end().setHeader(
            "Content-Type", constant("application/json")).to("velocity:response_template.vm");

        // Arduino route
        from("mina2:udp://0.0.0.0:10081").to("log:dump?showAll=true").to(
            "file:target/incoming?fileName=msg-udp-${date:now:yyyyMMdd_HHmmss_SSS}.js");

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

/*
 * Copyright 2013 Thales Italia spa.
 * 
 * This program is not yet licensed and this file may not be used under any
 * circumstance.
 */
package com.google.developers.gdgfirenze.integration;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.handler.codec.oneone.OneToOneDecoder;

/**
 * The Class NeutralDecoder.
 */
public class ByteArrayDecoder extends OneToOneDecoder {

  @Override
  protected Object decode(ChannelHandlerContext ctx, Channel channel, Object msg) throws Exception {
    return ((ChannelBuffer) msg).array();
  }
}

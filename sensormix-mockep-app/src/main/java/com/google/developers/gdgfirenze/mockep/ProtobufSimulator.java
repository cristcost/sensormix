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

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Random;

/**
 * The Class ProtobufSimulator.
 * 
 * It simulates the messages sent using protocol buffer and netty with TCP.
 */
public class ProtobufSimulator {

  public static void main(String[] args) throws InterruptedException, SocketException,
    UnknownHostException, IOException {
    SampleMessage message = null;

    Socket socket = new Socket("localhost", 10082);
    Random rand = new Random();
    for (int i = 0; i < 10; i++) {
      message =
          SampleMessage.newBuilder().setDeviceId("urn:rixf:org.example/protobuf/sensor_id").
          setFaces(rand.nextLong() % 10).setTime(System.currentTimeMillis()).build();

      sendData(socket, message);

      Thread.sleep(5000);
    }
    socket.close();
  }

  private static void sendData(Socket socket, SampleMessage data) throws SocketException,
    UnknownHostException, IOException {

    OutputStream out = socket.getOutputStream();
    DataOutputStream dos = new DataOutputStream(out);

    byte[] bytes = data.toByteArray();
    dos.writeInt(bytes.length);
    dos.write(data.toByteArray());
  }
}

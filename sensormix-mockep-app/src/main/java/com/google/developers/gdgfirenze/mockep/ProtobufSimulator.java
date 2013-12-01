package com.google.developers.gdgfirenze.mockep;

import com.google.developers.gdgfirenze.protobuf.SensormixProtos.SampleMessage;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Random;

public class ProtobufSimulator {

  public static void main(String[] args) throws InterruptedException, SocketException,
    UnknownHostException, IOException {
    SampleMessage message = null;

    Socket socket = new Socket("localhost", 10082);
    Random rand = new Random();
    for (int i = 0; i < 10; i++) {

      message =
          SampleMessage.newBuilder().setDeviceId("urn:rixf:org.example/protobuf/sensor_id").setFaces(
              rand.nextLong() % 10).setTime(System.currentTimeMillis()).build();

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

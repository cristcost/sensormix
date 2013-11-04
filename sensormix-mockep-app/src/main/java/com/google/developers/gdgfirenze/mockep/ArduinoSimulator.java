package com.google.developers.gdgfirenze.mockep;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Random;

public class ArduinoSimulator {

	public static void main(String[] args) throws IOException,
			InterruptedException {

		Random rand = new Random();
		while (true) {
			DatagramSocket clientSocket = new DatagramSocket();
			InetAddress IPAddress = InetAddress.getByName("localhost");
			String data = "{'device_id':'urn:rixf:org.arduino/sensor_id', 'temp': "
					+ rand.nextDouble() + ", 'lux': " + rand.nextDouble() + "}";
			byte[] sendData = data.getBytes();
			DatagramPacket sendPacket = new DatagramPacket(sendData,
					sendData.length, IPAddress, 8888);
			clientSocket.send(sendPacket);
			clientSocket.close();
			Thread.sleep(10000);
		}
	}

}

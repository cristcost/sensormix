package com.google.developers.gdgfirenze.mockep;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Random;

public class ArduinoSimulator {

	public static void main(String[] args) throws IOException,
			InterruptedException {

		Random rand = new Random();
		sendData("{ 'sensor' : {'id':'urn:rixf:org.arduino/sensor_id', type : 'urn:rixf:net.sensormix/device_types/arduino', name: 'Arduino', description : 'Rev. 1.03, Temp and Light sensors'}}");
		while (true) {
			String data = "{ 'sample' : {'device_id':'urn:rixf:org.arduino/sensor_id', 'temp': '"
					+ rand.nextDouble() + "', 'lux': '" + rand.nextDouble() + "'} }";
			sendData(data);
			Thread.sleep(10000);
		}
	}

	private static void sendData(String data) throws SocketException,
			UnknownHostException, IOException {
		DatagramSocket clientSocket = new DatagramSocket();
		InetAddress ipAddress = InetAddress.getByName("localhost");

		byte[] sendData = data.getBytes();
		DatagramPacket sendPacket = new DatagramPacket(sendData,
				sendData.length, ipAddress, 10081);
		clientSocket.send(sendPacket);
		clientSocket.close();
	}

}

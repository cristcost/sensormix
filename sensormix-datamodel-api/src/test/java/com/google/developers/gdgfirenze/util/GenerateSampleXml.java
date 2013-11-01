package com.google.developers.gdgfirenze.util;

import com.google.developers.gdgfirenze.model.NumericValueSample;
import com.google.developers.gdgfirenze.model.PositionSample;
import com.google.developers.gdgfirenze.model.WifiSignalSample;
import com.google.developers.gdgfirenze.service.SamplesPayload;

import java.util.Date;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

public class GenerateSampleXml {

  public static void main(String[] args) throws JAXBException {

    NumericValueSample numericValueSample = new NumericValueSample();
    numericValueSample.setSensorId("#sensorId");
    numericValueSample.setTime(new Date());
    numericValueSample.setType("#sampleType");
    numericValueSample.setValue(123.4);

    WifiSignalSample wifiSignalSample = new WifiSignalSample();
    wifiSignalSample.setSensorId("#sensorId");
    wifiSignalSample.setTime(new Date());
    wifiSignalSample.setType("#sampleType");
    wifiSignalSample.setBssid("00:00:00:00:00:00");
    wifiSignalSample.setCapabilities("[C1][C2][C3]");
    wifiSignalSample.setFrequency(2400.0);
    wifiSignalSample.setLevel(-40.0);
    wifiSignalSample.setSsid("WLAN Name");

    PositionSample positionSample = new PositionSample();
    positionSample.setSensorId("#sensorId");
    positionSample.setTime(new Date());
    positionSample.setType("#sampleType");
    positionSample.setAccuracy(10.0);
    positionSample.setAlt(100.0);
    positionSample.setBearing(180.0);
    positionSample.setLat(43.0);
    positionSample.setLng(11.0);
    positionSample.setSpeed(0.0);

    SamplesPayload payload = new SamplesPayload();
    payload.getSamples().add(numericValueSample);
    payload.getSamples().add(wifiSignalSample);
    payload.getSamples().add(positionSample);

    JAXBContext jc = JAXBContext.newInstance(SamplesPayload.class);
    Marshaller marshaller = jc.createMarshaller();
    marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
    marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
    marshaller.setProperty(Marshaller.JAXB_FRAGMENT, true);
    marshaller.marshal(payload, System.out);
  }
}

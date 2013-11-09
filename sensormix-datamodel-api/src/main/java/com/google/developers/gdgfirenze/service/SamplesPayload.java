package com.google.developers.gdgfirenze.service;

import com.google.developers.gdgfirenze.model.AbstractSample;
import com.google.developers.gdgfirenze.model.NumericValueSample;
import com.google.developers.gdgfirenze.model.PositionSample;
import com.google.developers.gdgfirenze.model.WifiSignalSample;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "samples")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SamplesPayload")
public class SamplesPayload implements Serializable {
  @XmlElements({
      @XmlElement(name = "numericValueSample", type = NumericValueSample.class),
      @XmlElement(name = "positionSample", type = PositionSample.class),
      @XmlElement(name = "wifiSignalSample", type = WifiSignalSample.class)
  })
  private final List<AbstractSample> samples = new ArrayList<AbstractSample>();

  public List<AbstractSample> getSamples() {
    return samples;
  }
}

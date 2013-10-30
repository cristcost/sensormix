package com.google.developers.gdgfirenze.service;

import com.google.developers.gdgfirenze.model.AbstractSample;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@SuppressWarnings("serial")
@XmlRootElement(name = "samples")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SamplesPayload")
public class SamplesPayload extends ArrayList<AbstractSample> {

}

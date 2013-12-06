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
package com.google.developers.gdgfirenze.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;

/**
 * The Class NumericValueSample.
 * 
 * It implements a sample with a simple numeric value, such as temperature or light.
 * 
 * It is annotated with JaxB annotations in order to allow easy marshalling/unmarshalling to XML.
 * The class is part of a the Sensormix.gwt.xml module and implements Serializable in order to be
 * used within a GWT application.
 */
@SuppressWarnings("serial")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "NumericValueSample")
public class NumericValueSample extends AbstractSample implements Serializable {

  /** The value. */
  @XmlAttribute(required = false, name = "value")
  @XmlSchemaType(name = "double")
  private Double value;

  /**
   * Gets the value.
   * 
   * @return the value
   */
  public Double getValue() {
    return value;
  }

  /**
   * Sets the value.
   * 
   * @param value the new value
   */
  public void setValue(Double value) {
    this.value = value;
  }

}

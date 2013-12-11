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
package com.google.developers.gdgfirenze.admin.client.event;

import com.google.gwt.event.shared.GwtEvent;

/**
 * The Class NumberDetectedEvent.
 */
public class NumberDetectedEvent extends GwtEvent<NumberDetectedEventHandler> {

  /**
   * The Enum TypeOfNumberDetected.
   */
  public enum TypeOfNumberDetected {

    /** The sample. */
    SAMPLE,
    /** The sensor. */
    SENSOR
  }

  /** The Constant TYPE. */
  public static final Type<NumberDetectedEventHandler> TYPE = new Type<NumberDetectedEventHandler>();

  /** The detected number. */
  private long detectedNumber;

  /** The type of number detected. */
  private TypeOfNumberDetected typeOfNumberDetected;

  /**
   * Instantiates a new number detected event.
   * 
   * @param typeOfNumberDetected
   *          the type of number detected
   * @param detectedNumber
   *          the detected number
   */
  public NumberDetectedEvent(TypeOfNumberDetected typeOfNumberDetected, long detectedNumber) {
    this.typeOfNumberDetected = typeOfNumberDetected;
    this.detectedNumber = detectedNumber;
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.google.gwt.event.shared.GwtEvent#getAssociatedType()
   */
  @Override
  public Type<NumberDetectedEventHandler> getAssociatedType() {
    return TYPE;
  }

  /**
   * Gets the detected number.
   * 
   * @return the detected number
   */
  public long getDetectedNumber() {
    return detectedNumber;
  }

  /**
   * Gets the type of number detected.
   * 
   * @return the type of number detected
   */
  public TypeOfNumberDetected getTypeOfNumberDetected() {
    return typeOfNumberDetected;
  }

  /**
   * Sets the detected number.
   * 
   * @param detectedNumber
   *          the new detected number
   */
  public void setDetectedNumber(long detectedNumber) {
    this.detectedNumber = detectedNumber;
  }

  /**
   * Sets the type of number detected.
   * 
   * @param typeOfNumberDetected
   *          the new type of number detected
   */
  public void setTypeOfNumberDetected(TypeOfNumberDetected typeOfNumberDetected) {
    this.typeOfNumberDetected = typeOfNumberDetected;
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.google.gwt.event.shared.GwtEvent#dispatch(com.google.gwt.event.shared.EventHandler)
   */
  @Override
  protected void dispatch(NumberDetectedEventHandler handler) {
    handler.onNotificationEvent(this);
  }
}

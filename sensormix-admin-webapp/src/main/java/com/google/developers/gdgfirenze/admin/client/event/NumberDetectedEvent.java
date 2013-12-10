package com.google.developers.gdgfirenze.admin.client.event;

import com.google.gwt.event.shared.GwtEvent;

public class NumberDetectedEvent extends GwtEvent<NumberDetectedEventHandler>{
	public enum TypeOfNumberDetected {
		SAMPLE,SENSOR
	}
	public static final Type<NumberDetectedEventHandler> TYPE = new Type<NumberDetectedEventHandler>();
	private long detectedNumber;
	private TypeOfNumberDetected typeOfNumberDetected;
	public NumberDetectedEvent(TypeOfNumberDetected typeOfNumberDetected, long detectedNumber) {
		this.typeOfNumberDetected = typeOfNumberDetected;
		this.detectedNumber = detectedNumber;
	}

	@Override
	protected void dispatch(NumberDetectedEventHandler handler) {
		handler.onNotificationEvent(this);		
	}

	@Override
	public Type<NumberDetectedEventHandler> getAssociatedType() {
		return TYPE;
	}

	public long getDetectedNumber() {
		return detectedNumber;
	}

	public TypeOfNumberDetected getTypeOfNumberDetected() {
		return typeOfNumberDetected;
	}

	public void setDetectedNumber(long detectedNumber) {
		this.detectedNumber = detectedNumber;
	}

	public void setTypeOfNumberDetected(TypeOfNumberDetected typeOfNumberDetected) {
		this.typeOfNumberDetected = typeOfNumberDetected;
	}
}

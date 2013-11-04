package com.google.developers.gdgfirenze.serializer;

import java.io.ByteArrayOutputStream;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.google.developers.gdgfirenze.model.AbstractSample;
import com.google.developers.gdgfirenze.model.NumericValueSample;
import com.google.developers.gdgfirenze.model.PositionSample;
import com.google.developers.gdgfirenze.model.WifiSignalSample;

public class Serializer {
	Kryo k;

	public Serializer() {
		k = new Kryo();
		k.setClassLoader(Serializer.class.getClassLoader());
		k.register(WifiSignalSample.class, 1000);
		k.register(NumericValueSample.class, 1001);
		k.register(PositionSample.class, 1002);
	}

	public byte[] serialize(AbstractSample orig) {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		Output buffer = new Output(bos);
		k.writeClassAndObject(buffer, orig);
		buffer.close();
		return bos.toByteArray();
	}

	public AbstractSample deserialize(byte[] buffer) {
		Input iBuff = new Input(buffer);

		return (AbstractSample) k.readClassAndObject(iBuff);
	}
}

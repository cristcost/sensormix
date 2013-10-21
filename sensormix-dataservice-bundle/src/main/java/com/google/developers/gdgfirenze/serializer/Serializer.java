package com.google.developers.gdgfirenze.serializer;

import java.io.ByteArrayOutputStream;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.google.developers.gdgfirenze.model.AbstractSample;

public class Serializer {

	public static byte[] serialize(AbstractSample orig) {
        Kryo k=new Kryo();
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        Output buffer = new Output(bos);
        k.writeClassAndObject(buffer, orig);
        return buffer.getBuffer();
    }
	
	public static AbstractSample deserialize(byte[] buffer) {
        Kryo k=new Kryo();
        Input iBuff = new Input(buffer);
        
        return (AbstractSample)k.readClassAndObject(iBuff);
    }
}

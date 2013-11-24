package com.google.developers.gdgfirenze.integration;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class TestProcessor implements Processor{

	@Override
	public void process(Exchange exc) throws Exception {
		System.out.println(exc.toString());
	}

}

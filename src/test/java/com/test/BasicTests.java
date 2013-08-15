package com.test;

import org.junit.Test;

public class BasicTests {

	@Test
	public void testUrlExtraction(){
		String unsubscribeUrl = "http://localhost:8080/sample/unsbscribe?token=sometoken";
		System.out.println("Substr: "+unsubscribeUrl.substring(0,unsubscribeUrl.indexOf("/unsbscribe")+1));
	}
}

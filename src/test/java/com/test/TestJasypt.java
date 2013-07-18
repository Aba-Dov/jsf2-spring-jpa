package com.test;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import org.jasypt.util.text.BasicTextEncryptor;
import org.jasypt.util.text.StrongTextEncryptor;
import org.junit.Test;

public class TestJasypt {

	
	public void test(){
		System.out.println("In encryption method");
		BasicTextEncryptor textEncryptor = new BasicTextEncryptor();
		textEncryptor.setPassword("myEncryptionPassword");
		String myEncryptedText = textEncryptor.encrypt("raissi");
		System.out.println("Encrypted: "+myEncryptedText);
	}
	public void tesDecrypt(){
		System.out.println("In decryption method");
		BasicTextEncryptor textEncryptor = new BasicTextEncryptor();
		textEncryptor.setPassword("myEncryptionPassword");
		String myEncryptedText = textEncryptor.decrypt("wL5cHKtixvAli08w+1bYow==");
		System.out.println("Decrypted: "+myEncryptedText);
	}
	
	@Test
	public void testUrl(){
		try {
			StrongTextEncryptor encryptor = new StrongTextEncryptor();
			encryptor.setPassword("raissi");
			System.out.println("Encrypted: "+encryptor.encrypt("raissi"));
			System.out.println("Decrypted: "+encryptor.decrypt(encryptor.encrypt("raissi")));
			String encoded = URLEncoder.encode("/raissi?test=","UTF-8");
			System.out.println("Encoded: "+encoded);
			System.out.println("Decoded: "+URLDecoder.decode(encoded, "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pdf.ex.util;

import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;
import org.springframework.stereotype.Component;

/**
 *
 * @author giuseppina.mele
 */

@Component
public class Base64Util {
    
    
    	public static String encode(String in) {
		Encoder enc = Base64.getEncoder();
		return enc.encodeToString(in.getBytes());
	}
	
	public static String decode(String in) {
		Decoder dec = Base64.getDecoder();
		return new String(dec.decode(in.getBytes()));
	}
    
    
}

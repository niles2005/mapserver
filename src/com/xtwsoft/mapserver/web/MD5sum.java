package com.xtwsoft.mapserver.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5sum {
	public static char[] hexChar = { '0', '1', '2', '3', '4', '5', '6', '7','8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };  
	
	public static String getHash(String fileName, String hashType)  {   
        MessageDigest md5  = null;
		try {
			InputStream fis;  
			fis = new FileInputStream(fileName);  
			byte[] buffer = new byte[1024];  
			md5 = MessageDigest.getInstance(hashType);  
			int numRead = 0;  
			while ((numRead = fis.read(buffer)) > 0) {  
			    md5.update(buffer, 0, numRead);  
			}  
			fis.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}  
        return toHexString(md5.digest());  
    }  
  
    public static String toHexString(byte[] b) {  
        StringBuilder sb = new StringBuilder(b.length * 2);  
        for (int i = 0; i < b.length; i++) {  
            sb.append(hexChar[(b[i] & 0xf0) >>> 4]);  
            sb.append(hexChar[b[i] & 0x0f]);  
        }  
        return sb.toString();  
    }  
		

//	public static void main(String[] args) {
//		String filePath = "E:\\教程\\51CTO下载-Javascript 函数快速查询手册.pdf";
//		String hashType = "MD5"; 
//		
//		 try {
//			System.out.println(hashType + " == " + getHash(filePath, hashType));
//		} catch (Exception e) {
//			e.printStackTrace();
//		}  
//		
//
//	}

}

package com.example.MilanorTool.utils;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import javax.imageio.ImageIO;


public class Zip_tools {
	
	public static byte[] Zip_tools_byte() throws IOException {
		ZipFile zip = new ZipFile("C:/Users/pluie/Desktop/milanorToolSidefile/manhwa/의룡 -Team Medical Dragon-/medical_dragon01.zip");
		byte[] image = null;
		for (Enumeration e = zip.entries(); e.hasMoreElements(); ) {
	        ZipEntry entry = (ZipEntry) e.nextElement();
	        
	        if (!entry.isDirectory()) {
	        		image = getImage(zip.getInputStream(entry));
	        }
		}
		return image;
	}
	
//	public static String Zip_tools_text () throws IOException {
//		ZipFile zip = new ZipFile("C:/Users/pluie/Desktop/milanorToolSidefile/manhwa/의룡 -Team Medical Dragon-/medical_dragon01.zip");
//	
//		for (Enumeration e = zip.entries(); e.hasMoreElements(); ) {
//	        ZipEntry entry = (ZipEntry) e.nextElement();
//	        
//	        if (!entry.isDirectory()) {
//	        	
//	            if (FilenameUtils.getExtension(entry.getName()).equals("jpg")) {
//	            	
//	                byte[] image = getImage(zip.getInputStream(entry));
//
//	                	return image;
//	                
//	            } else if (FilenameUtils.getExtension(entry.getName()).equals("txt")) {
//	                StringBuilder out = getTxtFiles(zip.getInputStream(entry));
//	                
//	                return out;
//	            }
//	        }
//	    }
//	}
	
	//image 파일 컨트롤
	private static byte[] getImage(InputStream in)  {
	    try {
	        BufferedImage image = ImageIO.read(in); //just checking if the InputStream belongs in fact to an image
	        ByteArrayOutputStream baos = new ByteArrayOutputStream();
	        ImageIO.write(image, "jpg", baos);
	        return baos.toByteArray();
	    } catch (IOException e) {
	        // do something, it is not a image
	        e.printStackTrace();
	    }
	    return null;
	}
	
	//text 파일 컨트롤
	private  static StringBuilder getTxtFiles(InputStream in)  {
	    StringBuilder out = new StringBuilder();
	    BufferedReader reader = new BufferedReader(new InputStreamReader(in));
	    String line;
	    try {
	        while ((line = reader.readLine()) != null) {
	            out.append(line);
	        }
	    } catch (IOException e) {
	        // do something, probably not a text file
	        e.printStackTrace();
	    }
	    return out;
	}
}



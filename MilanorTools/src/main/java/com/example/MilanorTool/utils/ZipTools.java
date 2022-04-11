package com.example.MilanorTool.utils;

import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

import javax.imageio.ImageIO;

import com.example.MilanorTool.model.Contents;


public class ZipTools {
	// 이미지 컨텐츠 다건
	public static List<Contents> Zip_tools_bytes(String path) throws IOException {
		List<Contents> allContents = new ArrayList<Contents>();
		byte[] image = null;
		ZipEntry ze = null;
		
		try (InputStream input = new FileInputStream(path)) {
			ZipInputStream zipInputStream = new ZipInputStream(new BufferedInputStream(input), Charset.forName("UTF-8"));
			while ((ze = zipInputStream.getNextEntry()) != null) {
				Contents content = new Contents();
				
				if (!ze.isDirectory()) {
		    		image = getImage(zipInputStream);
			    }
				content.setContentName(ze.getName());
				content.setContentSource(Base64.getEncoder().encodeToString(image));
	    			allContents.add(content);
			}
			
			zipInputStream.closeEntry();
		    input.close();
		}
		Collections.sort(allContents); // 파일이름 순으로 정렬
		
		return allContents;
	}
	
	// 이미지 컨텐츠 단건
	public static byte[] Zip_tools_byte(String path) throws IOException {
		try (
				ZipFile zip = new ZipFile(path)) {
					byte[] image = null;
					for (Enumeration<?> e = zip.entries(); e.hasMoreElements(); ) {
					    ZipEntry entry = (ZipEntry) e.nextElement();
					    if (!entry.isDirectory()) {
					    		image = getImage(zip.getInputStream(entry));
					    }
				}
			return image;
		}
	}
	
	// 텍스트 와 이미지 컨텐츠 함께 쓸 때 예제
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
	public static byte[] getImage(InputStream in)  {
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
//	private  static StringBuilder getTxtFiles(InputStream in)  {
//	    StringBuilder out = new StringBuilder();
//	    BufferedReader reader = new BufferedReader(new InputStreamReader(in));
//	    String line;
//	    try {
//	        while ((line = reader.readLine()) != null) {
//	            out.append(line);
//	        }
//	    } catch (IOException e) {
//	        // do something, probably not a text file
//	        e.printStackTrace();
//	    }
//	    return out;
//	}
}



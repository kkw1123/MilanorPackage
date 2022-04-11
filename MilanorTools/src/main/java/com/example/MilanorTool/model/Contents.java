package com.example.MilanorTool.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Contents implements Comparable<Contents>{
	
	private String contentName;
	private String contentSource;
	
	@Override
	public int compareTo(Contents o) {
		return contentName.compareTo(o.contentName);
	}
}

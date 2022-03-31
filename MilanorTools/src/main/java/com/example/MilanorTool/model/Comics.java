package com.example.MilanorTool.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Data
@Getter
@Setter
public class Comics {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String id;
	private String title;
	private int volumes;
	private String title_image;
	private String content_path;
	private Date start_yr_mon;
	private Date end_yr_mon;
	private Date cre_date;
	private Date up_date;
}

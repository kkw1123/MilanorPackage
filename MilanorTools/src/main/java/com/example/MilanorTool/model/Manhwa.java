package com.example.MilanorTool.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Manhwa {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String id;
	private String title;
	private int volumes;
	private String title_image;
	private Date start_yr_mon;
	private Date end_yr_mon;
	private Date cre_date;
	private Date up_date;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getVolumes() {
		return volumes;
	}
	public void setVolumes(int volumes) {
		this.volumes = volumes;
	}
	public String getTitle_image() {
		return title_image;
	}
	public void setTitle_image(String Title_image) {
		this.title_image = Title_image;
	}
	public Date getStart_yr_mon() {
		return start_yr_mon;
	}
	public void setStart_yr_mon(Date start_yr_mon) {
		this.start_yr_mon = start_yr_mon;
	}
	public Date getEnd_yr_mon() {
		return end_yr_mon;
	}
	public void setEnd_yr_mon(Date end_yr_mon) {
		this.end_yr_mon = end_yr_mon;
	}
	public Date getCre_date() {
		return cre_date;
	}
	public void setCre_date(Date cre_date) {
		this.cre_date = cre_date;
	}
	public Date getUp_date() {
		return up_date;
	}
	public void setUp_date(Date up_date) {
		this.up_date = up_date;
	}
}

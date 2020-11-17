package com.guliany.socialzilla.dto;

import java.util.Date;

public class ErrorDTO {

	private Date timeStamp;
	private String title;
	private String description;

	public ErrorDTO() {
	}

	public ErrorDTO(String title, String description) {
		super();
		this.timeStamp = new Date();
		this.title = title;
		this.description = description;
	}

	public Date getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(Date timeStamp) {
		this.timeStamp = timeStamp;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "ErrorDTO [timeStamp=" + timeStamp + ", title=" + title + ", description=" + description + "]";
	}

}

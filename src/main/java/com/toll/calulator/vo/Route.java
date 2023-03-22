package com.toll.calulator.vo;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties
public  class Route implements Serializable{
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public int getToId() {
		return toId;
	}
	public void setToId(int toId) {
		this.toId = toId;
	}
	public double getDistance() {
		return distance;
	}
	public void setDistance(double distance) {
		this.distance = distance;
	}
	public boolean isExit() {
		return exit;
	}
	public void setExit(boolean exit) {
		this.exit = exit;
	}
	public boolean isEnter() {
		return enter;
	}
	public void setEnter(boolean enter) {
		this.enter = enter;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public int toId;
	    public double distance;
	    public boolean exit;
	    public boolean enter;
	    public String startDate;
}
package com.toll.calulator.vo;

import lombok.Data;

@Data
public class TollCalculatorResponse {

	String entryPostiton;
	String exitPosition;
	Double cost;
	String description;

	public Double getCost() {
		return cost;
	}

	public void setCost(Double cost) {
		this.cost = cost;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getEntryPostiton() {
		return entryPostiton;
	}

	public void setEntryPostiton(String entryPostiton) {
		this.entryPostiton = entryPostiton;
	}

	public String getExitPosition() {
		return exitPosition;
	}

	public void setExitPosition(String exitPosition) {
		this.exitPosition = exitPosition;
	}

}

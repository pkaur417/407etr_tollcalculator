package com.toll.calulator.vo;

import lombok.Data;

@Data
public class TollCalculatorRequest {
	
	String entryPostiton;
	
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

	String exitPosition;
	

}

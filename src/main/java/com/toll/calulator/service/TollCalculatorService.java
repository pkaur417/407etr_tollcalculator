package com.toll.calulator.service;

import com.toll.calulator.vo.TollCalculatorRequest;
import com.toll.calulator.vo.TollCalculatorResponse;


public interface TollCalculatorService {
	
	public TollCalculatorResponse getTollTicketAmount(TollCalculatorRequest req);

}

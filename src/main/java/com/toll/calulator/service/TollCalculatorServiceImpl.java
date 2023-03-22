package com.toll.calulator.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.toll.calulator.utility.ApplicationUtilty;
import com.toll.calulator.vo.TollCalculatorRequest;
import com.toll.calulator.vo.TollCalculatorResponse;

@Service
public class TollCalculatorServiceImpl implements TollCalculatorService {

	private static final Logger LOGGER = LogManager.getLogger(TollCalculatorServiceImpl.class);

	@Override
	public TollCalculatorResponse getTollTicketAmount(TollCalculatorRequest request) {
		boolean isValidReq = ApplicationUtilty.validateRequest(request.getEntryPostiton(), request.getExitPosition());
		if (isValidReq) {

			Double cost = ApplicationUtilty.calculateTollCost(request.getEntryPostiton(), request.getExitPosition());
			TollCalculatorResponse response = ApplicationUtilty.createResponse(request, cost, null);
			LOGGER.info("Toll Cost Calculated from "+ request.getEntryPostiton()+ " to ",
					request.getExitPosition() + cost);
			return response;

		}
		return null;

	}

}

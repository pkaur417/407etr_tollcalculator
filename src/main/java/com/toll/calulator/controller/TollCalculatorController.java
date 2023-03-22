package com.toll.calulator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.toll.calulator.service.TollCalculatorService;
import com.toll.calulator.utility.ApplicationUtilty;
import com.toll.calulator.vo.TollCalculatorRequest;
import com.toll.calulator.vo.TollCalculatorResponse;

@RestController
public class TollCalculatorController {

	private static final Logger LOGGER = LogManager.getLogger(TollCalculatorController.class);

	@Autowired
	private TollCalculatorService tollCalculatorService;

	@PostMapping("/tollCalculator")
	public ResponseEntity<TollCalculatorResponse> getTollTicketAmount(@RequestBody TollCalculatorRequest request) {

		try {
			TollCalculatorResponse response = tollCalculatorService.getTollTicketAmount(request);
			ResponseEntity<TollCalculatorResponse> serverResonse = handleResponse(request, response);
			return serverResonse;
		} catch (Exception e) {
			LOGGER.error("Exception occured : "+ e.getMessage());
			TollCalculatorResponse response = ApplicationUtilty.createResponse(request, 0.0d, e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
	}

	private ResponseEntity<TollCalculatorResponse> handleResponse(TollCalculatorRequest request,TollCalculatorResponse response) {
		ResponseEntity<TollCalculatorResponse> serverResonse;
		if (response != null) {
			serverResonse = ResponseEntity.status(HttpStatus.OK).body(response);
		} else {
			TollCalculatorResponse res = new TollCalculatorResponse();
			res.setEntryPostiton(request.getEntryPostiton());
			res.setExitPosition(request.getExitPosition());
			res.setCost(0.0d);
			res.setDescription("Invalid Request");
			serverResonse = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(res);
		}
		return serverResonse;
	}

}

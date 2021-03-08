package com.solactive.demo.application.controller;

import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.solactive.demo.application.exception.DataNotFoundException;
import com.solactive.demo.application.model.IndexInformation;
import com.solactive.demo.application.model.StatisticsData;
import com.solactive.demo.application.service.FinancialService;
import com.solactive.demo.application.util.Constants;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api")
public class FinancialController {

	@Autowired
	FinancialService service;

	@PostMapping("/ticks")
	@ApiOperation("Stores the Index Information for a Instrument name")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "OK"),
			@ApiResponse(code = 204, message = "Request Exceeded Time Limit") })
	public ResponseEntity<?> createIndexInformation(@Valid @RequestBody IndexInformation indexInformation)
			throws MethodArgumentNotValidException {
		long currentTimeinMilli = new Date().getTime();
		long calculatedTime = currentTimeinMilli - indexInformation.getTimestamp();

		if (calculatedTime < Constants.TIME_LIMIT) {
			service.setIndexInformation(indexInformation);
			return new ResponseEntity<>(HttpStatus.CREATED);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);

	}

	@GetMapping("/statistics")
	@ApiOperation("Retrieves aggregated statistics Data.")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK") })
	public StatisticsData getStatistics() throws DataNotFoundException {
		return service.getIndexInformation();
	}

	@GetMapping("/statistics/{instrumentIdentifier}")
	@ApiOperation("Retrieves statistics data for a given Instrument Name.")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK") })
	public StatisticsData getTicks(@PathVariable String instrumentIdentifier) throws DataNotFoundException {
		return service.getIndexInformation(instrumentIdentifier);
	}

}

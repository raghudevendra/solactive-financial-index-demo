package com.solactive.demo.application.service;

import org.springframework.stereotype.Service;

import com.solactive.demo.application.model.IndexInformation;
import com.solactive.demo.application.model.StatisticsData;

@Service
public interface FinancialService {

	/**
	 * This method used to store the index information for particular Instrument.
	 * which has Instrument name Price and Timestamp
	 * 
	 * @param IndexInformation.
	 * @return Nothing.
	 */
	public void setIndexInformation(IndexInformation indexInformation);

	/**
	 * This method used to retrieve statistics data for a particular Instrument
	 * 
	 * @param IndexInformation.
	 * @return StatisticsData.
	 */
	public StatisticsData getIndexInformation(String instrumentName);

	/**
	 * This method used to retrieve aggregated statistics data across all the
	 * Instrument
	 * 
	 * @param no @param.
	 * @return StatisticsData.
	 */
	public StatisticsData getIndexInformation();

}

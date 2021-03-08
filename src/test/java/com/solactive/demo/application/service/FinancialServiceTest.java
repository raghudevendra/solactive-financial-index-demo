package com.solactive.demo.application.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;

import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.solactive.demo.application.exception.DataNotFoundException;
import com.solactive.demo.application.model.IndexInformation;
import com.solactive.demo.application.model.StatisticsData;
import com.solactive.demo.application.serviceimpl.FinancialServiceImpl;
import com.solactive.demo.application.util.Constants;

@ExtendWith(MockitoExtension.class)
public class FinancialServiceTest {

	@InjectMocks
	private FinancialServiceImpl service;

	@Mock
	private StatisticsData statisticsData;

	@Mock
	private static ConcurrentHashMap<String, ArrayList<IndexInformation>> mockIndexInfoMap;

	private IndexInformation indexInfo;

	private static ConcurrentHashMap<String, ArrayList<IndexInformation>> indexInfoMap;

	@BeforeEach
	void init() {
		indexInfoMap = new ConcurrentHashMap<String, ArrayList<IndexInformation>>();
		indexInfo = new IndexInformation();
		indexInfo.setInstrument("IBM");
		indexInfo.setPrice(100.4);
		indexInfo.setTimestamp(new Date().getTime());

	}

	@Test
	public void setIndexInformation() {
		service.setIndexInformation(indexInfo);
	}

}
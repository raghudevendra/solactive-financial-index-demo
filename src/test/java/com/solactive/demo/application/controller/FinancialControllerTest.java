package com.solactive.demo.application.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;

import com.solactive.demo.application.model.IndexInformation;
import com.solactive.demo.application.model.StatisticsData;
import com.solactive.demo.application.service.FinancialService;

/*@RunWith(SpringRunner.class)
@SpringBootTest*/
@WebMvcTest(FinancialController.class)
public class FinancialControllerTest {

	@Autowired
	FinancialController financialController;

	@MockBean
	FinancialService service;

	private IndexInformation indexInfo;

	@BeforeEach
	void init() {
		indexInfo = new IndexInformation();
		indexInfo.setInstrument("IBM.N");
		indexInfo.setPrice(200);
		indexInfo.setTimestamp(1614950413801L);
	}

	@Test
	void testCreateIndexInformationForNoContent() throws MethodArgumentNotValidException {

		ResponseEntity<?> responseEntity = financialController.createIndexInformation(indexInfo);
		assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
	}

	@Test
	void testCreateIndexInformationFor() throws MethodArgumentNotValidException {

		long currentTimeinMilli = new Date().getTime();
		indexInfo.setTimestamp(currentTimeinMilli - 1000L);
		FinancialService mockFinancial = mock(FinancialService.class);
		doNothing().when(mockFinancial).setIndexInformation(indexInfo);
		ResponseEntity<?> responseEntity = financialController.createIndexInformation(indexInfo);
		assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
	}

	@Test
	void testGetIndexInformationFor() {
		StatisticsData statistics = new StatisticsData();
		statistics.setAvgerage(100);
		statistics.setCount(2);
		statistics.setMaximum(200);
		statistics.setMinimum(0);

		when(service.getIndexInformation()).thenReturn(statistics);
		StatisticsData statisticResponse = financialController.getStatistics();
		assertEquals(statisticResponse, statistics);
	}

	@Test
	void testGetIndexInformationForInstrument() {
		StatisticsData statistics = new StatisticsData();
		statistics.setAvgerage(100);
		statistics.setCount(2);
		statistics.setMaximum(200);
		statistics.setMinimum(0);

		when(service.getIndexInformation("IBM")).thenReturn(statistics);
		StatisticsData statisticResponse = financialController.getTicks("IBM");
		assertEquals(statisticResponse, statistics);
	}

}

package com.solactive.demo.application.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.solactive.demo.application.model.IndexInformation;
import com.solactive.demo.application.serviceimpl.FinancialServiceImpl;

@Component
public class ScheduledTasks {

	double refreshedTotalValue = 0;
	long count = 0;
	double maximum = Integer.MIN_VALUE;
	double minimum = Integer.MAX_VALUE;

	/**
	 * This method is scheduled to call every sec to recalculate the maximum and minimum value
	 * after the 60secs of time
	 * @param No Param.
	 * @return Nothing.
	 */
	@Scheduled(fixedRate = 1000)
	public void reportCurrentTime() {
		boolean flag = false;
		ConcurrentHashMap<String, ArrayList<IndexInformation>> map = FinancialServiceImpl.getIndexInfoMap();
		for (Map.Entry<String, ArrayList<IndexInformation>> entry : map.entrySet()) {
			ArrayList<IndexInformation> listOfIndexInfo = entry.getValue();
			Iterator<IndexInformation> iterationOnIndexData = listOfIndexInfo.iterator();
			long currentTime = new Date().getTime();
			while (iterationOnIndexData.hasNext()) {
				IndexInformation indexData = (IndexInformation) iterationOnIndexData.next();
				long diffInTime = currentTime - indexData.getTimestamp();
				long diffInSeconds = TimeUnit.MILLISECONDS.toSeconds(diffInTime);
				if (diffInSeconds > 60) {
					flag = true;
					iterationOnIndexData.remove();
				}

			}
		}
		// recalculate max min and total
		if (flag) {
			count = 0;

			for (Map.Entry<String, ArrayList<IndexInformation>> entry : map.entrySet()) {
				ArrayList<IndexInformation> listToRecalculate = entry.getValue();
				Iterator<IndexInformation> itrToRecalculate = listToRecalculate.iterator();
				while (itrToRecalculate.hasNext()) {
					IndexInformation recalculatedIndexInfo = (IndexInformation) itrToRecalculate.next();
					if (maximum < recalculatedIndexInfo.getPrice()) {
						maximum = recalculatedIndexInfo.getPrice();
					}
					if (minimum > recalculatedIndexInfo.getPrice()) {
						minimum = recalculatedIndexInfo.getPrice();
					}
					refreshedTotalValue = refreshedTotalValue + recalculatedIndexInfo.getPrice();
					count++;
				}
			}
			FinancialServiceImpl.setCount(count);
			FinancialServiceImpl.setIndexInfoMap(map);
			FinancialServiceImpl.setTotalPriceValue(refreshedTotalValue);
		}

	}
}
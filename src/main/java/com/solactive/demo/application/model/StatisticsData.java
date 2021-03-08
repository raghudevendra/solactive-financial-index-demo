package com.solactive.demo.application.model;

import org.springframework.stereotype.Component;

@Component
public class StatisticsData {
	private double avgerage;
	private double maximum;
	private double minimum;
	private long count;

	public StatisticsData() {
		super();
	}

	public StatisticsData(double avgerage, double maximum, double minimum) {
		this.avgerage = avgerage;
		this.maximum = maximum;
		this.minimum = minimum;
	}

	public double getAvgerage() {
		return avgerage;
	}

	public void setAvgerage(double avgerage) {
		this.avgerage = avgerage;
	}

	public double getMaximum() {
		return maximum;
	}

	public void setMaximum(double maximum) {
		this.maximum = maximum;
	}

	public double getMinimum() {
		return minimum;
	}

	public void setMinimum(double minimum) {
		this.minimum = minimum;
	}

	public long getCount() {
		return count;
	}

	public void setCount(long count) {
		this.count = count;
	}

}

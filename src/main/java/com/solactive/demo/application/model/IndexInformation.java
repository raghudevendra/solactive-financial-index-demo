package com.solactive.demo.application.model;

import javax.validation.constraints.NotNull;

public class IndexInformation {

	@NotNull(message =  "Instrument Name is required")
	private String instrument;

	@NotNull(message =  "Price is required")
	private double price;

	@NotNull(message =  "Timestamp is required")
	private long timestamp;

	public String getInstrument() {
		return instrument;
	}

	public void setInstrument(String instrument) {
		this.instrument = instrument;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

}

package com.sourav.fruitpricing;

public class Purchase {

	private String commodityName;
	private double pricePerTon;
	private double tradeVolume;

	public Purchase() {
	}

	public Purchase(String commodityName, double pricePerTon, double tradeVolume) {
		super();
		this.commodityName = commodityName;
		this.pricePerTon = pricePerTon;
		this.tradeVolume = tradeVolume;
	}

	public String getCommodityName() {
		return commodityName;
	}

	public void setCommodityName(String commodityName) {
		this.commodityName = commodityName;
	}

	public double getPricePerTon() {
		return pricePerTon;
	}

	public void setPricePerTon(double pricePerTon) {
		this.pricePerTon = pricePerTon;
	}

	public double getTradeVolume() {
		return tradeVolume;
	}

	public void setTradeVolume(double tradeVolume) {
		this.tradeVolume = tradeVolume;
	}
	
}

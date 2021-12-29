package com.sourav.fruitpricing;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ThirdPartyData {

	private String commodity;
	private String countryCode;
	private double fixedOverhead;// ($ per trade)
	private double variableOverhead;// ($ per ton)

	public ThirdPartyData() {
	}

	public ThirdPartyData(String commodity, String countryCode, double fixedOverhead, double variableOverhead) {
		super();
		this.commodity = commodity;
		this.countryCode = countryCode;
		this.fixedOverhead = fixedOverhead;
		this.variableOverhead = variableOverhead;
	}

	@JsonProperty("COMMODITY_NAME")
	public String getCommodity() {
		return commodity;
	}

	public void setCommodity(String commodity) {
		this.commodity = commodity;
	}

	@JsonProperty("COUNTRY")
	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	@JsonProperty("FIXED_OVERHEAD")
	public double getFixedOverhead() {
		return fixedOverhead;
	}

	public void setFixedOverhead(double fixedOverhead) {
		this.fixedOverhead = fixedOverhead;
	}

	@JsonProperty("VAR_OVERHEAD")
	public double getVariableOverhead() {
		return variableOverhead;
	}

	public void setVariableOverhead(double variableOverhead) {
		this.variableOverhead = variableOverhead;
	}

	@Override
	public String toString() {
		return "ThirdPartyData [commodity=" + commodity + ", countryCode=" + countryCode + ", fixedOverhead="
				+ fixedOverhead + ", variableOverhead=" + variableOverhead + "]";
	}
}

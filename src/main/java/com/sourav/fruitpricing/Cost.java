package com.sourav.fruitpricing;

public class Cost {

	private String countryCode;
	private double totalCost;
	private CostBreakdown costBreakdown;

	public Cost() {
	}

	public Cost(String countryCode, double totalCost, CostBreakdown costBreakdown) {
		super();
		this.countryCode = countryCode;
		this.totalCost = totalCost;
		this.costBreakdown = costBreakdown;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public double getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(double totalCost) {
		this.totalCost = totalCost;
	}

	public CostBreakdown getCostBreakdown() {
		return costBreakdown;
	}

	public void setCostBreakdown(CostBreakdown costBreakdown) {
		this.costBreakdown = costBreakdown;
	}

	static class CostBreakdown {
        private double effectivePricePerTon;
        private double tons;
        private double fixedOverhead;

        public CostBreakdown() {
		}

		public CostBreakdown(double effectivePricePerTon, double tons, double fixedOverhead) {
			super();
			this.effectivePricePerTon = effectivePricePerTon;
			this.tons = tons;
			this.fixedOverhead = fixedOverhead;
		}

		public double getEffectivePricePerTon() {
			return effectivePricePerTon;
		}

		public void setEffectivePricePerTon(double effectivePricePerTon) {
			this.effectivePricePerTon = effectivePricePerTon;
		}

		public double getTons() {
			return tons;
		}

		public void setTons(double tons) {
			this.tons = tons;
		}

		public double getFixedOverhead() {
			return fixedOverhead;
		}

		public void setFixedOverhead(double fixedOverhead) {
			this.fixedOverhead = fixedOverhead;
		}

	}
}

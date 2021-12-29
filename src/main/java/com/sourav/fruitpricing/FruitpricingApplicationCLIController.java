package com.sourav.fruitpricing;

import java.util.List;
import org.springframework.stereotype.Component;

import org.springframework.beans.factory.annotation.Autowired;

@Component
public class FruitpricingApplicationCLIController {

	@Autowired
	private FruitPricingService fruitPricingService;

	String calculatePrice(Purchase purchase) {
		List<Cost> costs = fruitPricingService.calculatePrice(purchase);
		StringBuilder sb = new StringBuilder();
		for (Cost cost : costs) {
			// e.g, IN 22060.10 | (54.42*405) +20
			sb.append(cost.getCountryCode()+" ");
			sb.append(cost.getTotalCost()+" | ");
			sb.append("("+cost.getCostBreakdown().getEffectivePricePerTon()+"*");
			sb.append(cost.getCostBreakdown().getTons()+") ");
			sb.append(cost.getCostBreakdown().getFixedOverhead());
			sb.append("\n");
		}
		return sb.toString();
	}
}

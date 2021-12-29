package com.sourav.fruitpricing;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class FruitpricingApplicationRestController {

	@Autowired
	private FruitPricingService fruitPricingService;

	@GetMapping("sourav/fruitpricing/name/{name}")
	ResponseEntity<String> welcome(@PathVariable String name) {
		return new ResponseEntity<String>(String.format("Welcome to Sourav's Fruit Pricing Application : %s", name), HttpStatus.OK);
	}

	@PostMapping("sourav/fruitpricing/priceme")
	ResponseEntity<List<Cost>> calculatePrice(@RequestBody Purchase purchase) {
		List<Cost> costs = fruitPricingService.calculatePrice(purchase);
		return new ResponseEntity<List<Cost>>(costs, HttpStatus.OK);
	}
}

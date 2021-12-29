package com.sourav.fruitpricing;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

@Component
@Service
public class FruitPricingService {

	//commodity -> List[details...]
	private Map<String, List<ThirdPartyData>> data = new HashMap<>();

	public FruitPricingService() {
		getThirdPartyData();
	}

	public List<Cost> calculatePrice(Purchase purchase) {

        if (data.containsKey(purchase.getCommodityName())) {
        	List<Cost> costs = new ArrayList<>();
        	for (ThirdPartyData thirdPartyData : data.get(purchase.getCommodityName())) {
        		Cost cost = new Cost();
        		double fixedCost = thirdPartyData.getFixedOverhead();
        		double variableCost = getVariableCost(purchase, thirdPartyData);
        		cost.setCountryCode(thirdPartyData.getCountryCode().toUpperCase());
        		double totalCost = fixedCost+variableCost;
        		String totalCostStr = String.format("%.2f", totalCost);
        		cost.setTotalCost(Double.valueOf(totalCostStr));
        		cost.setCostBreakdown(new Cost.CostBreakdown(
        				purchase.getPricePerTon()+thirdPartyData.getVariableOverhead(),
        				purchase.getTradeVolume(),
        				fixedCost));
        		costs.add(cost);
        	}
        	Collections.sort(costs, new Comparator<Cost>() {
        		@Override
        	    public int compare(Cost a, Cost b) {
        			if (b.getTotalCost() - a.getTotalCost() > 0) {
        				return 1;
        			} else if (b.getTotalCost() - a.getTotalCost() < 0) {
        				return -1;
        			} else {
        				return 0;
        			}
        		}
        	});
        	return costs;
		} else {
			return null;
		}
	}

	private double getVariableCost(Purchase purchase, ThirdPartyData thirdPartyData) {
	    return (purchase.getPricePerTon()+thirdPartyData.getVariableOverhead())*purchase.getTradeVolume();
	}

	private void getThirdPartyData() {
		if ((new File("C://Aboltabol//Equip//fruitpricing//ThirdPartyData//commodity.txt")).exists()) {
			getThirdPartyDataFromTxtFile();
		} else if ((new File("C://Aboltabol//Equip//fruitpricing//ThirdPartyData//commodity.json")).exists()) {
			getThirdPartyDataFromJsonFile();
		}
	}

	private void getThirdPartyDataFromJsonFile() {
		
	    JSONParser jsonParser = new JSONParser();
	    try (FileReader reader =
	            new FileReader("C://Aboltabol//Equip//fruitpricing//ThirdPartyData//commodity.json")) {
	         //Read JSON file
	         Object obj = jsonParser.parse(reader);
	         JSONArray thirdPartDataJsonList = (JSONArray) obj;
	         Iterator iterator = thirdPartDataJsonList.iterator();
	         while (iterator.hasNext()) {
	        	 Object object = iterator.next();
	        	 JSONObject jsonObject = (JSONObject) JSONValue.parse(new ObjectMapper().writeValueAsString(object));
	        	 ObjectMapper objectMapper = new ObjectMapper();
	        	 ThirdPartyData thirdPartyData = objectMapper.readerFor(ThirdPartyData.class).readValue(jsonObject.toString());
	        	 thirdPartyData.setCommodity(thirdPartyData.getCommodity().toLowerCase());
	        	 thirdPartyData.setCountryCode(thirdPartyData.getCountryCode().toLowerCase());
	        	 String comodity = thirdPartyData.getCommodity();
				 List<ThirdPartyData> listOfThirdPartyData = null;
				 if (data.containsKey(comodity)) {
					listOfThirdPartyData = data.get(comodity);
				 } else {
					listOfThirdPartyData = new ArrayList<>();
				 }
				 listOfThirdPartyData.add(thirdPartyData);
				 data.put(comodity, listOfThirdPartyData);
	             System.out.println(thirdPartDataJsonList);
	         }
	    } catch (FileNotFoundException e) {
	        e.printStackTrace();
	    } catch (IOException e) {
	        e.printStackTrace();
	    } catch (ParseException e) {
	        e.printStackTrace();
	    }
	}

	private void getThirdPartyDataFromTxtFile() {
		//the known folder for this data is C:\Aboltabol\Equip\fruitpricing\ThirdPartyData
		try {
			BufferedReader bufferedReader = new BufferedReader(new FileReader(
					new File("C://Aboltabol//Equip//fruitpricing//ThirdPartyData//commodity.txt")));
			String line = null;
			while (true) {
				line = bufferedReader.readLine();
				if (line == null) {
					break;
				}
				String[] info = line.split(" ");
				String comodity = info[0].toLowerCase();
				List<ThirdPartyData> listOfThirdPartyData = null;
				if (data.containsKey(comodity)) {
					listOfThirdPartyData = data.get(comodity);
				} else {
					listOfThirdPartyData = new ArrayList<>();
				}
				ThirdPartyData thirdPartyData = new ThirdPartyData(info[0].toLowerCase(), info[1].toLowerCase(),
						Double.valueOf(info[2]), Double.valueOf(info[3]));
				listOfThirdPartyData.add(thirdPartyData);
				data.put(comodity, listOfThirdPartyData);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

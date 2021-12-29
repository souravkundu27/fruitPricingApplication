package com.sourav.fruitpricing;

import java.util.Scanner;

import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FruitpricingApplication implements CommandLineRunner{

	private static final org.jboss.logging.Logger logger = LoggerFactory.logger(FruitpricingApplication.class);
	
	@Autowired
	private FruitpricingApplicationCLIController fruitpricingApplicationCLIController;
	
	public static void main(String[] args) {
		SpringApplication.run(FruitpricingApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		logger.info("EXECUTING : command line runner");
		 
        for (int i = 0; i < args.length; ++i) {
        	logger.info(String.format("args[%d]: %s"+ i+ args[i]));
        }
        Scanner scanner = new Scanner(System.in);
		while (true) {
			System.out.println("Enter the command, if you want to Exit, then enter 'Y'");
			String command = scanner.nextLine();
			if (command.equals("Y")) {
				System.exit(0);
			}
			System.out.println(processCli(command));
		}
	}

	private String processCli(String cmd) {
		String[] input = cmd.split(" ");
		if (input[0].equals("priceme")) {
			Purchase purchase = new Purchase();
			purchase.setCommodityName(input[1]);
			purchase.setTradeVolume(Double.valueOf(input[2]));
			purchase.setPricePerTon(Double.valueOf(input[3]));
			return fruitpricingApplicationCLIController.calculatePrice(purchase);
		} else {
			return "wrong command, Usage : priceme <comodity_name> <amount in ton> <price per ton>";
		}
	}
}

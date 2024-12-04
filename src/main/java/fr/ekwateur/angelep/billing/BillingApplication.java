package fr.ekwateur.angelep.billing;

import fr.ekwateur.angelep.billing.model.CorporateClient;
import fr.ekwateur.angelep.billing.model.Gender;
import fr.ekwateur.angelep.billing.model.IndividualClient;
import fr.ekwateur.angelep.billing.model.MonthlyBill;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;

@SpringBootApplication
public class BillingApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(BillingApplication.class, args);
	}

	private IndividualClient individualClient = new IndividualClient(
			"EKW000000001",
			100,
			250,
			Gender.FEMALE,
			"Angele",
			"Petitjean"
	);

	private CorporateClient corporateClientUpperTurnover = new CorporateClient(
			"EKW000000020",
			3000,
			6500,
			"SIRET",
			"myCompany",
			BigDecimal.valueOf(2000000)
	);

	private CorporateClient corporateClientLowerTurnover = new CorporateClient(
			"EKW000000021",
			3000,
			6500,
			"SIRET",
			"myCompany",
			BigDecimal.valueOf(2300)
	);

	private CorporateClient corporateClient1000000Turnover = new CorporateClient(
			"EKW000000022",
			3000,
			6500,
			"SIRET",
			"myCompany",
			BigDecimal.valueOf(1000000)
	);


	@Override
	public void run(String... args) throws Exception {
		System.out.println(new MonthlyBill(individualClient).printBill());
		System.out.println(new MonthlyBill(corporateClient1000000Turnover).printBill());
		System.out.println(new MonthlyBill(corporateClientLowerTurnover).printBill());
		System.out.println(new MonthlyBill(corporateClientUpperTurnover).printBill());
	}
}

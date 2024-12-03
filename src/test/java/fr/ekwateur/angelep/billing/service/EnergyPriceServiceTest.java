package fr.ekwateur.angelep.billing.service;

import fr.ekwateur.angelep.billing.model.CorporateClient;
import fr.ekwateur.angelep.billing.model.Energy;
import fr.ekwateur.angelep.billing.model.Gender;
import fr.ekwateur.angelep.billing.model.IndividualClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class EnergyPriceServiceTest {

    @Autowired
    private EnergyPriceService energyPriceService;

    private IndividualClient individualClient = new IndividualClient(
            "EKW000000000",
            100,
            250,
            Gender.FEMALE,
            "Angele",
            "Petitjean"
    );

    private CorporateClient corporateClientUpperTurnover = new CorporateClient(
            "EKW000000000",
            3000,
            6500,
            "SIRET",
            "myCompany",
            BigDecimal.valueOf(2000000)
    );

    private CorporateClient corporateClientLowerTurnover = new CorporateClient(
            "EKW000000000",
            3000,
            6500,
            "SIRET",
            "myCompany",
            BigDecimal.valueOf(2300)
    );

    @Test
    void whenIndividualClient_shouldReturnCorrectGasPrice() {
        BigDecimal price = energyPriceService.getPrice(Energy.GAS, individualClient);
        assertEquals(BigDecimal.valueOf(0.108), price);
    }

    @Test
    void whenIndividualClient_shouldReturnCorrectElectricityPrice() {
        BigDecimal price = energyPriceService.getPrice(Energy.ELECTRICITY, individualClient);
        assertEquals(BigDecimal.valueOf(0.133), price);
    }

    @Test
    void whenCorporateClientWithTurnoverBiggerThan1000000_shouldReturnCorrectGasPrice() {
        BigDecimal price = energyPriceService.getPrice(Energy.GAS, corporateClientUpperTurnover);
        assertEquals(BigDecimal.valueOf(0.123), price);
    }

    @Test
    void whenCorporateClientWithTurnoverBiggerThan1000000_shouldReturnCorrectElectricityPrice() {
        BigDecimal price = energyPriceService.getPrice(Energy.ELECTRICITY, corporateClientUpperTurnover);
        assertEquals(BigDecimal.valueOf(0.110), price);
    }

    @Test
    void whenCorporateClientWithTurnoverLowerThan1000000_shouldReturnCorrectGasPrice() {
        BigDecimal price = energyPriceService.getPrice(Energy.GAS, corporateClientLowerTurnover);
        assertEquals(BigDecimal.valueOf(0.117), price);
    }

    @Test
    void whenCorporateClientWithTurnoverLowerThan1000000_shouldReturnCorrectElectricityPrice() {
        BigDecimal price = energyPriceService.getPrice(Energy.ELECTRICITY, corporateClientLowerTurnover);
        assertEquals(BigDecimal.valueOf(0.112), price);
    }

}
package fr.ekwateur.angelep.billing.helper;

import fr.ekwateur.angelep.billing.model.CorporateClient;
import fr.ekwateur.angelep.billing.model.Energy;
import fr.ekwateur.angelep.billing.model.Gender;
import fr.ekwateur.angelep.billing.model.IndividualClient;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EnergyPriceHelperTest {

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

    private CorporateClient corporateClient1000000Turnover = new CorporateClient(
            "EKW000000000",
            3000,
            6500,
            "SIRET",
            "myCompany",
            BigDecimal.valueOf(1000000)
    );

    @Test
    void whenIndividualClient_shouldReturnCorrectGasPrice() {
        BigDecimal price = EnergyPriceHelper.getPrice(Energy.GAS, individualClient);
        assertEquals(BigDecimal.valueOf(0.108), price);
    }

    @Test
    void whenIndividualClient_shouldReturnCorrectElectricityPrice() {
        BigDecimal price = EnergyPriceHelper.getPrice(Energy.ELECTRICITY, individualClient);
        assertEquals(BigDecimal.valueOf(0.133), price);
    }

    @Test
    void whenCorporateClientWithTurnoverBiggerThan1000000_shouldReturnCorrectGasPrice() {
        BigDecimal price = EnergyPriceHelper.getPrice(Energy.GAS, corporateClientUpperTurnover);
        assertEquals(BigDecimal.valueOf(0.123), price);
    }

    @Test
    void whenCorporateClientWithTurnoverBiggerThan1000000_shouldReturnCorrectElectricityPrice() {
        BigDecimal price = EnergyPriceHelper.getPrice(Energy.ELECTRICITY, corporateClientUpperTurnover);
        assertEquals(BigDecimal.valueOf(0.110), price);
    }

    @Test
    void whenCorporateClientWithTurnoverLowerThan1000000_shouldReturnCorrectGasPrice() {
        BigDecimal price = EnergyPriceHelper.getPrice(Energy.GAS, corporateClientLowerTurnover);
        assertEquals(BigDecimal.valueOf(0.117), price);
    }

    @Test
    void whenCorporateClientWithTurnoverLowerThan1000000_shouldReturnCorrectElectricityPrice() {
        BigDecimal price = EnergyPriceHelper.getPrice(Energy.ELECTRICITY, corporateClientLowerTurnover);
        assertEquals(BigDecimal.valueOf(0.112), price);
    }

    @Test
    void whenCorporateClientWithTurnoverEqualTo1000000_shouldReturnSameGasPriceAsLower() {
        BigDecimal price = EnergyPriceHelper.getPrice(Energy.GAS, corporateClient1000000Turnover);
        assertEquals(BigDecimal.valueOf(0.117), price);
    }

    @Test
    void whenCorporateClientWithTurnoverEqualTo1000000_shouldReturnSameElectricityPriceAsLower() {
        BigDecimal price = EnergyPriceHelper.getPrice(Energy.ELECTRICITY, corporateClient1000000Turnover);
        assertEquals(BigDecimal.valueOf(0.112), price);
    }

}
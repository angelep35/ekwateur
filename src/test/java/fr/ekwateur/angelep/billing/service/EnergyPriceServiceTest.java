package fr.ekwateur.angelep.billing.service;

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

    @Test
    void whenIndividualClient_shouldReturnCorrectGasPrice() {
        IndividualClient individualClient = new IndividualClient(
                "EKW000000000",
                100,
                250,
                Gender.FEMALE,
                "Angele",
                "Petitjean"
        );
        BigDecimal price = energyPriceService.getPrice(Energy.GAS, individualClient);
        assertEquals(BigDecimal.valueOf(0.108), price);
    }

    @Test
    void whenIndividualClient_shouldReturnCorrectElectricityPrice() {
        IndividualClient individualClient = new IndividualClient(
                "EKW000000000",
                100,
                250,
                Gender.FEMALE,
                "Angele",
                "Petitjean"
        );
        BigDecimal price = energyPriceService.getPrice(Energy.ELECTRICITY, individualClient);
        assertEquals(BigDecimal.valueOf(0.133), price);
    }

}
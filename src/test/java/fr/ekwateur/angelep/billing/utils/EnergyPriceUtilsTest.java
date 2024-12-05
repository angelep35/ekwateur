package fr.ekwateur.angelep.billing.utils;

import fr.ekwateur.angelep.billing.model.CorporateClient;
import fr.ekwateur.angelep.billing.model.Energy;
import fr.ekwateur.angelep.billing.model.IndividualClient;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EnergyPriceUtilsTest {

    @Mock
    private IndividualClient individualClient;

    @Mock
    private CorporateClient corporateClient;

    @Test
    void whenIndividualClient_shouldReturnCorrectGasPrice() {
        BigDecimal price = EnergyPriceUtils.getPrice(Energy.GAS, individualClient);
        assertEquals(BigDecimal.valueOf(0.108), price);
    }

    @Test
    void whenIndividualClient_shouldReturnCorrectElectricityPrice() {
        BigDecimal price = EnergyPriceUtils.getPrice(Energy.ELECTRICITY, individualClient);
        assertEquals(BigDecimal.valueOf(0.133), price);
    }

    @Test
    void whenCorporateClientWithTurnoverBiggerThan1000000_shouldReturnCorrectGasPrice() {
        when(corporateClient.getTurnover()).thenReturn(BigDecimal.valueOf(2000000));
        BigDecimal price = EnergyPriceUtils.getPrice(Energy.GAS, corporateClient);
        assertEquals(BigDecimal.valueOf(0.123), price);
    }

    @Test
    void whenCorporateClientWithTurnoverBiggerThan1000000_shouldReturnCorrectElectricityPrice() {
        when(corporateClient.getTurnover()).thenReturn(BigDecimal.valueOf(2000000));
        BigDecimal price = EnergyPriceUtils.getPrice(Energy.ELECTRICITY, corporateClient);
        assertEquals(BigDecimal.valueOf(0.110), price);
    }

    @Test
    void whenCorporateClientWithTurnoverLowerThan1000000_shouldReturnCorrectGasPrice() {
        when(corporateClient.getTurnover()).thenReturn(BigDecimal.valueOf(6000));
        BigDecimal price = EnergyPriceUtils.getPrice(Energy.GAS, corporateClient);
        assertEquals(BigDecimal.valueOf(0.117), price);
    }

    @Test
    void whenCorporateClientWithTurnoverLowerThan1000000_shouldReturnCorrectElectricityPrice() {
        when(corporateClient.getTurnover()).thenReturn(BigDecimal.valueOf(6000));
        BigDecimal price = EnergyPriceUtils.getPrice(Energy.ELECTRICITY, corporateClient);
        assertEquals(BigDecimal.valueOf(0.112), price);
    }

    @Test
    void whenCorporateClientWithTurnoverEqualTo1000000_shouldReturnSameGasPriceAsLower() {
        when(corporateClient.getTurnover()).thenReturn(BigDecimal.valueOf(1000000));
        BigDecimal price = EnergyPriceUtils.getPrice(Energy.GAS, corporateClient);
        assertEquals(BigDecimal.valueOf(0.117), price);
    }

    @Test
    void whenCorporateClientWithTurnoverEqualTo1000000_shouldReturnSameElectricityPriceAsLower() {
        when(corporateClient.getTurnover()).thenReturn(BigDecimal.valueOf(1000000));
        BigDecimal price = EnergyPriceUtils.getPrice(Energy.ELECTRICITY, corporateClient);
        assertEquals(BigDecimal.valueOf(0.112), price);
    }

}
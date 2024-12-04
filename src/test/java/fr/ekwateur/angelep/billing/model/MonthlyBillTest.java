package fr.ekwateur.angelep.billing.model;

import fr.ekwateur.angelep.billing.utils.EnergyPriceUtils;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import java.math.BigDecimal;

import static fr.ekwateur.angelep.billing.model.Energy.ELECTRICITY;
import static fr.ekwateur.angelep.billing.model.Energy.GAS;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mockStatic;

class MonthlyBillTest {

    private IndividualClient individualClient;

    @Test
    void whenElectricityAndGasConsumptionNot0_ShouldCorrectlyCalculateAmountsDue() {
        individualClient = new IndividualClient(
                "EKW000000000",
                100,
                250,
                Gender.FEMALE,
                "Angele",
                "Petitjean"
        );

        try (MockedStatic<EnergyPriceUtils> mockedStatic = mockStatic(EnergyPriceUtils.class)) {
            mockedStatic.when(() -> EnergyPriceUtils.getPrice(ELECTRICITY, individualClient)).thenReturn(BigDecimal.valueOf(0.133));
            mockedStatic.when(() -> EnergyPriceUtils.getPrice(GAS, individualClient)).thenReturn(BigDecimal.valueOf(0.108));

            MonthlyBill bill = new MonthlyBill(individualClient);

            assertEquals(0, BigDecimal.valueOf(13.3).compareTo(bill.getElectricityAmountDue()));
            assertEquals(0, BigDecimal.valueOf(27).compareTo(bill.getGasAmountDue()));
            assertEquals(0, BigDecimal.valueOf(40.3).compareTo(bill.getTotalAmountDue()));

        }
    }

    @Test
    void whenElectricityConsumptionIs0_ShouldCorrectlyCalculateAmountsDue() {
        individualClient = new IndividualClient(
                "EKW000000000",
                0,
                250,
                Gender.FEMALE,
                "Angele",
                "Petitjean"
        );

        try (MockedStatic<EnergyPriceUtils> mockedStatic = mockStatic(EnergyPriceUtils.class)) {
            mockedStatic.when(() -> EnergyPriceUtils.getPrice(ELECTRICITY, individualClient)).thenReturn(BigDecimal.valueOf(0.133));
            mockedStatic.when(() -> EnergyPriceUtils.getPrice(GAS, individualClient)).thenReturn(BigDecimal.valueOf(0.108));

            MonthlyBill bill = new MonthlyBill(individualClient);

            assertEquals(0, BigDecimal.valueOf(0).compareTo(bill.getElectricityAmountDue()));
            assertEquals(0, BigDecimal.valueOf(27).compareTo(bill.getGasAmountDue()));
            assertEquals(0, BigDecimal.valueOf(27).compareTo(bill.getTotalAmountDue()));

        }
    }

}
package fr.ekwateur.angelep.billing.model;

import fr.ekwateur.angelep.billing.helper.EnergyPriceHelper;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import java.math.BigDecimal;

import static fr.ekwateur.angelep.billing.model.Energy.ELECTRICITY;
import static fr.ekwateur.angelep.billing.model.Energy.GAS;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mockStatic;

class MonthlyBillTest {

    private final IndividualClient individualClient = new IndividualClient(
            "EKW000000000",
            100,
            250,
            Gender.FEMALE,
            "Angele",
            "Petitjean"
    );

    @Test
    void whenElectricityAndGasConsumptionNotNull_ShouldCorrectlyCalculateAmountsDue() {
        try (MockedStatic<EnergyPriceHelper> mockedStatic = mockStatic(EnergyPriceHelper.class)) {
            mockedStatic.when(() -> EnergyPriceHelper.getPrice(ELECTRICITY, individualClient)).thenReturn(BigDecimal.valueOf(0.133));
            mockedStatic.when(() -> EnergyPriceHelper.getPrice(GAS, individualClient)).thenReturn(BigDecimal.valueOf(0.108));

            MonthlyBill bill = new MonthlyBill(individualClient);

            assertEquals(0, BigDecimal.valueOf(13.3).compareTo(bill.getElectricityAmountDue()));
            assertEquals(0, BigDecimal.valueOf(27).compareTo(bill.getGasAmountDue()));
            assertEquals(0, BigDecimal.valueOf(40.3).compareTo(bill.getTotalAmountDue()));

        }

    }

}
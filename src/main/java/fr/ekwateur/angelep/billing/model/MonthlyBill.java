package fr.ekwateur.angelep.billing.model;

import fr.ekwateur.angelep.billing.utils.EnergyPriceUtils;
import lombok.Getter;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static fr.ekwateur.angelep.billing.model.Energy.ELECTRICITY;
import static fr.ekwateur.angelep.billing.model.Energy.GAS;

@Getter
public class MonthlyBill {
    private final Client client;
    private final BigDecimal gasPrice;
    private final BigDecimal electricityPrice;
    private final BigDecimal gasAmountDue;
    private final BigDecimal electricityAmountDue;
    private final BigDecimal totalAmountDue;

    public MonthlyBill(Client client) {
        this.client = client;
        this.gasPrice = EnergyPriceUtils.getPrice(GAS, client);
        this.electricityPrice = EnergyPriceUtils.getPrice(ELECTRICITY, client);
        this.gasAmountDue = calculateEnergyAmountDue(client.getMonthlyGasConsumption(), gasPrice);
        this.electricityAmountDue = calculateEnergyAmountDue(client.getMonthlyElectricityConsumption(), electricityPrice);
        this.totalAmountDue = gasAmountDue.add(electricityAmountDue);
    }

    private BigDecimal calculateEnergyAmountDue(int consumption, BigDecimal energyPrice) {
        return BigDecimal.valueOf(consumption).multiply(energyPrice);
    }

    public String printBill() {
        return String.format(
                "Monthly Bill for Client: %s\n" +
                        "---------------------------------------------\n" +
                        "Gas Consumption:               %d kWh\n" +
                        "Gas Price:                     %.3f €/kWh\n" +
                        "Gas Amount Due:                %.2f €\n\n" +

                        "Electricity Consumption:       %d kWh\n" +
                        "Electricity Price:             %.3f €/kWh\n" +
                        "Electricity Amount Due:        %.2f €\n" +
                        "---------------------------------------------\n" +
                        "Total Amount Due:              %.2f €\n",
                client.getClientNumber(),
                client.getMonthlyGasConsumption(),
                gasPrice.setScale(3, RoundingMode.HALF_DOWN),
                gasAmountDue.setScale(2, RoundingMode.HALF_DOWN),
                client.getMonthlyElectricityConsumption(),
                electricityPrice.setScale(3, RoundingMode.HALF_DOWN),
                electricityAmountDue.setScale(2, RoundingMode.HALF_DOWN),
                totalAmountDue.setScale(2, RoundingMode.HALF_DOWN)
        );
    }
}

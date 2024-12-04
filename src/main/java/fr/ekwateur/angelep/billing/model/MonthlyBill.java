package fr.ekwateur.angelep.billing.model;

import fr.ekwateur.angelep.billing.utils.EnergyPriceUtils;
import lombok.Getter;

import java.math.BigDecimal;

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
}

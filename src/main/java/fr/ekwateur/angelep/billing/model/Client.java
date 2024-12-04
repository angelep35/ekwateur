package fr.ekwateur.angelep.billing.model;

import lombok.Getter;

@Getter
public abstract class Client {
    private final String clientNumber;
    private int monthlyElectricityConsumption;
    private int monthlyGasConsumption;

    public Client(String clientNumber, int monthlyElectricityConsumption, int monthlyGasConsumption) {
        if (!isClientNumberValid(clientNumber)) {
            throw new IllegalArgumentException("Invalid clientNumber format");
        }
        this.clientNumber = clientNumber;
        this.monthlyElectricityConsumption = monthlyElectricityConsumption;
        this.monthlyGasConsumption = monthlyGasConsumption;
    }

    private boolean isClientNumberValid(String clientNumber) {
        String regex = "EKW\\d{9}";
        return clientNumber != null && clientNumber.matches(regex);
    }
}

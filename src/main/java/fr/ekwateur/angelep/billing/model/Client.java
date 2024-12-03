package fr.ekwateur.angelep.billing.model;

import lombok.Getter;

@Getter
public abstract class Client {
    private final String clientNumber;
    private int electricityConsumption;
    private int gasConsumption;

    public Client(String clientNumber, int electricityConsumption, int gasConsumption) {
        if (!isClientNumberValid(clientNumber)) {
            throw new IllegalArgumentException("Invalid clientNumber format");
        }
        this.clientNumber = clientNumber;
        this.electricityConsumption = electricityConsumption;
        this.gasConsumption = gasConsumption;
    }

    private boolean isClientNumberValid(String clientNumber) {
        String regex = "EKW\\d{9}";
        return clientNumber != null && clientNumber.matches(regex);
    }
}

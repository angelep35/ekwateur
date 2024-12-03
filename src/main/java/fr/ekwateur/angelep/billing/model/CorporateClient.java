package fr.ekwateur.angelep.billing.model;


import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class CorporateClient extends Client {
    private String siretNumber;
    private String name;
    private BigDecimal turnover;

    public CorporateClient(String clientNumber, int electricityConsumption, int gasConsumption, String siretNumber, String name, BigDecimal turnover) {
        super(clientNumber, electricityConsumption, gasConsumption);
        this.siretNumber = siretNumber;
        this.name = name;
        this.turnover = turnover;
    }

    public CorporateClient(String clientNumber, int electricityConsumption, int gasConsumption) {
        super(clientNumber, electricityConsumption, gasConsumption);
    }
}

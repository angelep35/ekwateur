package fr.ekwateur.angelep.billing.model;

import java.math.BigDecimal;

public enum Energy {
    GAS(BigDecimal.valueOf(0.108), BigDecimal.valueOf(0.117), BigDecimal.valueOf(0.123)),
    ELECTRICITY(BigDecimal.valueOf(0.133), BigDecimal.valueOf(0.112), BigDecimal.valueOf(0.110));

    private final BigDecimal individualClientPrice;
    private final BigDecimal corporateClientLowerTurnoverPrice;
    private final BigDecimal corporateClientUpperTurnoverPrice;


    Energy(BigDecimal individualClientPrice, BigDecimal corporateClientLowerTurnoverPrice, BigDecimal corporateClientUpperTurnoverPrice) {
        this.individualClientPrice = individualClientPrice;
        this.corporateClientLowerTurnoverPrice = corporateClientLowerTurnoverPrice;
        this.corporateClientUpperTurnoverPrice = corporateClientUpperTurnoverPrice;
    }
}

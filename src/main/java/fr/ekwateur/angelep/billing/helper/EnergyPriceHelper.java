package fr.ekwateur.angelep.billing.helper;

import fr.ekwateur.angelep.billing.model.Client;
import fr.ekwateur.angelep.billing.model.CorporateClient;
import fr.ekwateur.angelep.billing.model.Energy;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class EnergyPriceHelper {

    public static final int THRESHOLD = 1000000;

    public BigDecimal getPrice(Energy energy, Client client) {
        if (client instanceof CorporateClient) {
            return getCorporateClientPrice(energy, (CorporateClient) client);
        }
        return energy.getIndividualClientPrice();
    }

    private BigDecimal getCorporateClientPrice(Energy energy, CorporateClient corporateClient) {
        if (turnoverGreaterThanThreshold(corporateClient)) {
            return energy.getCorporateClientUpperTurnoverPrice();
        }
        return energy.getCorporateClientLowerTurnoverPrice();
    }

    private static boolean turnoverGreaterThanThreshold(CorporateClient corporateClient) {
        return corporateClient.getTurnover().compareTo(BigDecimal.valueOf(THRESHOLD)) > 0;
    }
}

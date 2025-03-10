package fr.ekwateur.angelep.billing.utils;

import fr.ekwateur.angelep.billing.model.Client;
import fr.ekwateur.angelep.billing.model.CorporateClient;
import fr.ekwateur.angelep.billing.model.Energy;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class EnergyPriceUtils {

    public static final int THRESHOLD = 1000000;

    public static BigDecimal getPrice(Energy energy, Client client) {
        if (client instanceof CorporateClient) {
            return getCorporateClientPrice(energy, (CorporateClient) client);
        }
        return energy.getIndividualClientPrice();
    }

    private static BigDecimal getCorporateClientPrice(Energy energy, CorporateClient corporateClient) {
        if (turnoverGreaterThanThreshold(corporateClient)) {
            return energy.getCorporateClientUpperTurnoverPrice();
        }
        return energy.getCorporateClientLowerTurnoverPrice();
    }

    private static boolean turnoverGreaterThanThreshold(CorporateClient corporateClient) {
        return corporateClient.getTurnover().compareTo(BigDecimal.valueOf(THRESHOLD)) > 0;
    }
}

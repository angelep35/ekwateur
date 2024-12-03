package fr.ekwateur.angelep.billing.service;

import fr.ekwateur.angelep.billing.model.Client;
import fr.ekwateur.angelep.billing.model.CorporateClient;
import fr.ekwateur.angelep.billing.model.Energy;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class EnergyPriceService {

    public BigDecimal getPrice(Energy energy, Client client) {
        if (client instanceof CorporateClient) {
            return getCorporateClientPrice(energy, (CorporateClient) client);
        }
        return energy.getIndividualClientPrice();
    }

    private BigDecimal getCorporateClientPrice(Energy energy, CorporateClient corporateClient) {
        if (corporateClient.getTurnover().compareTo(BigDecimal.valueOf(1000000)) > 0) {
            return energy.getCorporateClientUpperTurnoverPrice();
        }
        return energy.getCorporateClientLowerTurnoverPrice();
    }
}

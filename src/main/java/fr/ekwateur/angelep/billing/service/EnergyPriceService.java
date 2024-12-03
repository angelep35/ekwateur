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
            return energy.getCorporateClientUpperTurnoverPrice();
        }
        return energy.getIndividualClientPrice();
    }
}

package fr.ekwateur.angelep.billing.service;

import fr.ekwateur.angelep.billing.model.Energy;
import fr.ekwateur.angelep.billing.model.IndividualClient;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class EnergyPriceService {
    public BigDecimal getPrice(Energy energy, IndividualClient individualClient) {
        return energy.getIndividualClientPrice();
    }
}

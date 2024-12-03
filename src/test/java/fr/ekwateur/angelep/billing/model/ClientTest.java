package fr.ekwateur.angelep.billing.model;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class ClientTest {

    @Test
    void whenClientNumberIsValid_ShouldCreateObject() {
        CorporateClient corporateClient = new CorporateClient(
                "EKW000000000",
                230,
                450,
                "SIRET",
                "myCompany",
                BigDecimal.valueOf(649741.56)
        );

        assertNotNull(corporateClient);
    }

    @Test
    void whenClientNumberIsInvalid_ShouldThrowIllegalArgumentException() {
        String invalidClientNumber = "INVALID";

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new CorporateClient(
                        invalidClientNumber,
                        230,
                        450,
                        "SIRET",
                        "myCompany",
                        BigDecimal.valueOf(649741.56)
                ));

        assertEquals("Invalid clientNumber format", exception.getMessage());
    }
}

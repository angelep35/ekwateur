package fr.ekwateur.angelep.billing.model;

import lombok.Getter;

@Getter
public class IndividualClient extends Client {

    private final Gender gender;
    private final String firstName;
    private final String lastName;

    public IndividualClient(String clientNumber, int electricityConsumption, int gasConsumption, Gender gender, String firstName, String lastName) {
        super(clientNumber, electricityConsumption, gasConsumption);
        this.gender = gender;
        this.firstName = firstName;
        this.lastName = lastName;
    }
}

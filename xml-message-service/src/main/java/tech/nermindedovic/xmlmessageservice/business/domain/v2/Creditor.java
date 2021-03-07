package tech.nermindedovic.xmlmessageservice.business.domain.v2;

import lombok.Data;

@Data
public class Creditor extends TagTracker {
    private String name;
    private String address;
    private String accountNumber;

    public String getName() {
        return "Creditor";
    }
}

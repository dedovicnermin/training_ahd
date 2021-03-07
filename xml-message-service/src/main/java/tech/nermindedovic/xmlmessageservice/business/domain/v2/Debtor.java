package tech.nermindedovic.xmlmessageservice.business.domain.v2;

import lombok.Data;


@Data
public class Debtor extends TagTracker {
    private String name;
    private String address;
    private String accountNumber;
    private String amount;

    public String getName() {
        return "Debtor";
    }



}

package tech.nermindedovic.xmlmessageservice.business.domain.v2;

import lombok.Data;

@Data
public class AccountNumber extends TagTracker {
    String accountNumber;

    public String getName() {
        return "accountNumber";
    }
}

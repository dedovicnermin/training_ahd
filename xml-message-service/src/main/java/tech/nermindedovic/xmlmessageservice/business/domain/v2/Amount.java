package tech.nermindedovic.xmlmessageservice.business.domain.v2;

import lombok.Data;

@Data
public class Amount extends TagTracker {
    String amount;

    public String getName() {
        return "amount";
    }
}

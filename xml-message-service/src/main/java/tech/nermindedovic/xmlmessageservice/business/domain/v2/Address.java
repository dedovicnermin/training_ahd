package tech.nermindedovic.xmlmessageservice.business.domain.v2;

import lombok.Data;

@Data
public class Address extends TagTracker {
    String address;

    public String getName() {
        return "address";
    }
}

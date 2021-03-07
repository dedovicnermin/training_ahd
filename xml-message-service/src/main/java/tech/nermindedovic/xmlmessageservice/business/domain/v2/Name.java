package tech.nermindedovic.xmlmessageservice.business.domain.v2;

import lombok.Data;

@Data
public class Name extends TagTracker {
    String name;



    public String getName() {
        return "name";
    }
}

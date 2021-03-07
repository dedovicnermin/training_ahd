package tech.nermindedovic.xmlmessageservice.business.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
public class Debtor implements IMessage {
    private String name;
    private String address;
    private String accountNumber;
    private String amount;



}

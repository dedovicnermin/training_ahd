package tech.nermindedovic.xmlmessageservice;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
public class Debtor {
    private String name;
    private String address;
    private long accountNumber;
    private String amount;
}

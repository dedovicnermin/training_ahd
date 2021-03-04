package tech.nermindedovic.xmlmessageservice;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

@Data
//@JsonIgnoreProperties(ignoreUnknown = true)
public class PaymentMessage {

    @JacksonXmlProperty(localName = "Debtor")
    @JacksonXmlElementWrapper
    private Debtor debtor;

    @JacksonXmlProperty(localName = "Creditor")
    @JacksonXmlElementWrapper
    private Creditor creditor;
}

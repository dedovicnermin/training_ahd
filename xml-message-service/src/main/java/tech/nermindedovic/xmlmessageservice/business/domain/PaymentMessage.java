package tech.nermindedovic.xmlmessageservice.business.domain;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Data;
import tech.nermindedovic.xmlmessageservice.business.domain.Creditor;
import tech.nermindedovic.xmlmessageservice.business.domain.Debtor;

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

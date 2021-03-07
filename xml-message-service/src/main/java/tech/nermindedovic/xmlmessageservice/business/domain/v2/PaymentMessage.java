package tech.nermindedovic.xmlmessageservice.business.domain.v2;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Data;

@Data
public class PaymentMessage extends TagTracker {

//@JsonIgnoreProperties(ignoreUnknown = true)


        @JacksonXmlProperty(localName = "Debtor")
        @JacksonXmlElementWrapper
        private Debtor debtor;

        @JacksonXmlProperty(localName = "Creditor")
        @JacksonXmlElementWrapper
        private Creditor creditor;

        public String getName() {
                return "PaymentMessage";
        }

}

package tech.nermindedovic.xmlmessageservice.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.nermindedovic.xmlmessageservice.Creditor;
import tech.nermindedovic.xmlmessageservice.Debtor;
import tech.nermindedovic.xmlmessageservice.PaymentMessage;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.EnumMap;

@Service
public class PaymentMessageParser {

    private EnumMap<PaymentEnum, String> map = PaymentEnum.getEnumMap();

    public static PaymentMessage parsePaymentMessage(String msg) throws XMLStreamException {

        Stack<>

        XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
        XMLEventReader reader = xmlInputFactory.createXMLEventReader(new ByteArrayInputStream(msg.getBytes(StandardCharsets.UTF_8)));

        while (reader.hasNext()) {

            XmlEvent

        }



    }

    private void configureDebtor(Debtor debtor, String tag) {
        switch (tag) {
            case "name":
                debtor.setName(tag);
                break;

            case "address":
                debtor.setAddress(tag);
                break;
            case "accountNumber":
                debtor.setAccountNumber(Long.valueOf(tag));
                break;
            case "amount":
                debtor.setAmount(tag);

        }
    }

    private void configureCreditor(Creditor creditor ,String tag) {

    }

}

package tech.nermindedovic.xmlmessageservice.business;

import ch.qos.logback.core.joran.event.StartEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tech.nermindedovic.xmlmessageservice.business.domain.Creditor;
import tech.nermindedovic.xmlmessageservice.business.domain.Debtor;
import tech.nermindedovic.xmlmessageservice.business.domain.IMessage;
import tech.nermindedovic.xmlmessageservice.business.domain.PaymentMessage;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import java.util.*;

@Service
@Slf4j
public class PaymentMessageParser {



    public static PaymentMessage parsePaymentMessage(final String msg) throws XMLStreamException {

        XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
        XMLStreamReader eventReader = xmlInputFactory.createXMLStreamReader(new ByteArrayInputStream(msg.getBytes(StandardCharsets.UTF_8)));

        IMessage debtor = new Debtor();
        IMessage creditor = new Creditor();


//        LinkedHashMap<IMessage, List<String>> mapList = new LinkedHashMap<>();


        Stack<String> stack = new Stack<>();
        Map<String, IMessage> map = new HashMap<>();
        map.put("Debtor", debtor);
        map.put("Creditor", creditor);




        while (eventReader.hasNext()) {

            int event = eventReader.getEventType();

            switch (event) {
                case 1:
                    startTag(eventReader, stack);
                    break;
                case 2:
                    endTag(eventReader, stack, map);
                    break;
                case 3:
                case 4:
                    gatherData(eventReader, stack);
                    break;
            }

            eventReader.next();

        }

        PaymentMessage paymentMessage = new PaymentMessage();
        paymentMessage.setDebtor((Debtor) debtor);
        paymentMessage.setCreditor((Creditor) creditor);


        return paymentMessage;






    }

    private static void startTag(final XMLStreamReader eventReader, final Stack<String> stack) {
        if (eventReader.hasName()) {
            String tagName = eventReader.getLocalName();
            switch (tagName) {
                case "Debtor":
                case "Creditor":
                    stack.push(tagName);
                    break;
            }
        }
    }

//    private static void startTag(XMLStreamReader eventReader, LinkedHashMap<IMessage, List<String>> map, IMessage debtor, IMessage creditor) {
//        if (eventReader.hasName()) {
//            String tagName = eventReader.getLocalName();
//            switch (tagName) {
//                case "Debtor":
//
//                    map.put(debtor, new ArrayList<String>());
//                    break;
//                case "Creditor":
//                    creditor = new Creditor();
//                    map.put(creditor, new ArrayList<String>());
//                    break;
//            }
//        }
//    }

    private static void endTag(XMLStreamReader eventReader, Stack<String> stack, Map<String, IMessage> map) {
        if (eventReader.hasName()) {
            String tagName = eventReader.getLocalName();

            switch (tagName) {
                case "Debtor":
                    Debtor debtor = (Debtor) map.get(tagName);
                    String debtorData;
                    while (!stack.isEmpty() && stack.peek() != tagName) {
                        debtorData = stack.pop();

                        if (debtor.getAmount() == null) debtor.setAmount(debtorData);
                        else if (debtor.getAccountNumber() == null) debtor.setAccountNumber(debtorData);
                        else if (debtor.getAddress() == null) debtor.setAddress(debtorData);
                        else if (debtor.getName() == null) debtor.setName(debtorData);

                    }
                    stack.pop();
                    break;

                case "Creditor":
                    Creditor creditor = (Creditor) map.get(tagName);
                    String creditorData;
                    while (!stack.isEmpty() && stack.peek() != tagName) {
                        creditorData = stack.pop();

                        if (creditor.getAccountNumber() == null) creditor.setAccountNumber(creditorData);
                        else if (creditor.getAddress() == null) creditor.setAddress(creditorData);
                        else if (creditor.getName() == null) creditor.setName(creditorData);
                    }
                    stack.pop();
                    break;

            }

        }
    }

    private static void gatherData(XMLStreamReader eventReader, Stack<String> stack) {
        int start = eventReader.getTextStart();
        int length = eventReader.getTextLength();
        String str = new String(eventReader.getTextCharacters(), start, length).trim();
        if (!str.isEmpty()) {
            stack.push(str);
        }

    }



  





}

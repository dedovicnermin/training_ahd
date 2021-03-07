package tech.nermindedovic.xmlmessageservice.business.domain.v2;

import tech.nermindedovic.xmlmessageservice.business.domain.v2.*;
import tech.nermindedovic.xmlmessageservice.business.domain.v2.TagTracker;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Stack;

public class XMLParser2 {

    public static PaymentMessage parsePaymentMessage(final String msg) throws XMLStreamException {
        XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
        XMLStreamReader eventReader = xmlInputFactory.createXMLStreamReader(new ByteArrayInputStream(msg.getBytes(StandardCharsets.UTF_8)));



        TagTracker top;

        Stack<TagTracker> tagTrackerStack = new Stack<>();

        //Payment Message
        TagTracker root = new PaymentMessage();
        // Debtor
        TagTracker debtor = new Debtor();
        // Creditor
        TagTracker creditor = new Creditor();

        //Payment Msg will track:
        root.trackTag("Debtor", debtor);
        root.trackTag("Creditor", creditor);


        //debtor msg will track:
        debtor.trackTag("name", new Name());
        debtor.trackTag("address", new Address());
        debtor.trackTag("accountNumber", new AccountNumber());
        debtor.trackTag("amount", new Amount());


        //creditor msg will track:
        creditor.trackTag("name", new Name());
        creditor.trackTag("address", new Address());
        creditor.trackTag("accountNumber", new AccountNumber());



        while (eventReader.hasNext()) {

            int event = eventReader.getEventType();


            switch (event) {
                case 1:
                    String startTagName = eventReader.getLocalName();
                    switch (startTagName) {
                        case "PaymentMessage":
                            tagTrackerStack.push(root);
                            break;
                        case "Debtor":
                        case "Creditor":
                        case "name":
                        case "address":
                        case "accountNumber":
                        case "amount":
                            if (!tagTrackerStack.isEmpty()) {
                                top = tagTrackerStack.peek();
                                top.onStartTag(startTagName, tagTrackerStack);
                            }

                            break;
                        default:
                            break;
                    }
                    break;
                case 2:
                    String localName = eventReader.getLocalName();
                    if (!tagTrackerStack.isEmpty()) {
                        top = tagTrackerStack.peek();
                    } else {
                        break;
                    }
                    if (localName == "Debtor" || localName == "Creditor" ) {
                        for (String key : top.trackingMap.keySet()) {

                            Map<String, String> childData = top.getData();




                            top.data.put(key, top.trackingMap.get(key).getData().get(key));
                        }
                        tagTrackerStack.pop();
                    } else {
                        top.onEndTag(tagTrackerStack);
                    }

                    break;
                case 3:
                case 4:
                    int start = eventReader.getTextStart();
                    int length = eventReader.getTextLength();
                    String str = new String(eventReader.getTextCharacters(), start, length).trim();

                    if (!str.isEmpty() && !tagTrackerStack.isEmpty()) {
                        top = tagTrackerStack.peek();
                        top.onChars(str);
                    }
                    break;
            }
            eventReader.next();
        }
        return (PaymentMessage) root;
    }


}

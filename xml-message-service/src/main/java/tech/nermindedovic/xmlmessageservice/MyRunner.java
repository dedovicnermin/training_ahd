package tech.nermindedovic.xmlmessageservice;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import tech.nermindedovic.xmlmessageservice.onepassparser.XMLParser;
import tech.nermindedovic.xmlmessageservice.twopassparser.XMLParser2P;

@Component
public class MyRunner implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        String xml = "<PaymentMessage><Debtor><name>Jiraiya</name><address>japan</address><accountNumber>1234565</accountNumber><amount>100.00</amount></Debtor>"
                + "<Creditor><name>Tsunade</name><address>tokyo</address><accountNumber>535345345</accountNumber></Creditor></PaymentMessage>";

//        System.out.println(XMLParser.parsePaymentMessage(xml).toString());



        String xml2 = "<NetworkBatch><Network><source><ip>0.0.0.1</ip></source><name>network1</name><connections><connection><name>connection1</name><duration>06:04:34:00</duration><type>connectionType1</type><source><ip>IP for connection1 of network1</ip>" +
                "</source></connection><connection><name>connection2</name><duration>06:04:01:01</duration><type>connectionType2</type><source><ip>IP for connection2 of network 1</ip></source></connection></connections></Network>" +

                "<Network><source><ip>0.0.0.2</ip></source><name>network2</name><connections><connection><name>connection2</name><duration>09:00:14:00</duration><type>connectionType1</type><source><ip>IP for connection1 of network2</ip>" +
                "</source></connection><connection><name>connection2</name><duration>12:04:01:01</duration><type>connectionType2</type><source><ip>IP for connection2 of network 2</ip></source></connection></connections></Network>" +
                "</NetworkBatch>";



        System.out.println(XMLParser2P.parseXML(xml2).toString());




    }
}

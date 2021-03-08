package tech.nermindedovic.xmlmessageservice.xml;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class MyRunner implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        String xml = "<PaymentMessage><Debtor><name>Jiraiya</name><address>japan</address><accountNumber>1234565</accountNumber><amount>100.00</amount></Debtor>"
                + "<Creditor><name>Tsunade</name><address>tokyo</address><accountNumber>535345345</accountNumber></Creditor></PaymentMessage>";

        System.out.println(XMLParser.parsePaymentMessage(xml).toString());




    }
}

package tech.nermindedovic.xmlmessageservice;

import lombok.SneakyThrows;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tech.nermindedovic.xmlmessageservice.business.PaymentMessageParser;
import tech.nermindedovic.xmlmessageservice.business.domain.v2.XMLParser2;


@SpringBootApplication
public class SpringBootWebServiceApplication implements CommandLineRunner {

	@SneakyThrows
	public static void main(String[] args) {
		SpringApplication.run(SpringBootWebServiceApplication.class, args);


	}


	@Override
	public void run(String... args) throws Exception {
		String xml = "<PaymentMessage>\n" +
				"    <Debtor>\n" +
				"        <name>Jiraya</name>\n" +
				"        <address>Village Hidden in the Leaves</address>\n" +
				"        <accountNumber>03151999</accountNumber>\n" +
				"        <amount>100.00</amount>\n" +
				"    </Debtor>\n" +
				"    <Creditor>\n" +
				"        <name>Tsunade</name>\n" +
				"        <address>Japan</address>\n" +
				"        <accountNumber>239595445</accountNumber>\n" +
				"    </Creditor>\n" +
				"</PaymentMessage>";

		System.out.println(XMLParser2.parsePaymentMessage(xml).toString());
	}
}

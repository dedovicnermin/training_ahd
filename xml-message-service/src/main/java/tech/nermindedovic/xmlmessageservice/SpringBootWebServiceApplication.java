package tech.nermindedovic.xmlmessageservice;

import lombok.SneakyThrows;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tech.nermindedovic.xmlmessageservice.business.PaymentMessageParser;

@SpringBootApplication
public class SpringBootWebServiceApplication {

	@SneakyThrows
	public static void main(String[] args) {
		SpringApplication.run(SpringBootWebServiceApplication.class, args);

	}


}

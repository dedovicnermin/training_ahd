package tech.nermindedovic.xmlmessageservice;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.stream.XMLStreamReader;

import static org.springframework.http.MediaType.*;

@RestController
@RequestMapping("/api")
@Slf4j
public class RestControllerXML {
    /*
    * http://localhost:8080/api/get-string
    * */
    @PostMapping(path = "get-string",
            consumes = APPLICATION_XML_VALUE,
            produces = APPLICATION_XML_VALUE
    )
    private String getXMLString(@RequestBody final String xml) {
        return xml;
    }


    /*
    * http://localhost:8080/api/get-paymentmessage
    * - will return XML (JSON if produces wasnt defined)
    * */
    @PostMapping(path = "get-paymentmessage",
            consumes = APPLICATION_XML_VALUE,
            produces = APPLICATION_XML_VALUE
    )
    private PaymentMessage getPaymentMessage(@RequestBody final PaymentMessage msg) throws Exception {
        return msg;
    }






}

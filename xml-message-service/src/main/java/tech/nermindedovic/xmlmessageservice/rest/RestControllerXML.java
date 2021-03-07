package tech.nermindedovic.xmlmessageservice.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tech.nermindedovic.xmlmessageservice.business.PaymentMessageParser;
import tech.nermindedovic.xmlmessageservice.business.domain.v2.PaymentMessage;
import tech.nermindedovic.xmlmessageservice.business.domain.v2.XMLParser2;

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
//    @PostMapping(path = "get-paymentmessage",
//            consumes = APPLICATION_XML_VALUE,
//            produces = APPLICATION_XML_VALUE
//    )
//    private PaymentMessage getPaymentMessage(@RequestBody final PaymentMessage msg) throws Exception {
//        return msg;
//    }
//
//
//
//    @PostMapping(path = "parse-xml-string",
//        consumes = APPLICATION_XML_VALUE,
//        produces = APPLICATION_XML_VALUE
//    )
//    private PaymentMessage parseXMLMessage(@RequestBody final String msg) throws Exception {
//        PaymentMessage paymentMessage = PaymentMessageParser.parsePaymentMessage(msg);
//        log.info(paymentMessage.toString());
//        return paymentMessage;
//    }



    @PostMapping(path = "parse-xml-string2",
            consumes = APPLICATION_XML_VALUE,
            produces = APPLICATION_XML_VALUE
    )
    private tech.nermindedovic.xmlmessageservice.business.domain.v2.PaymentMessage parseXMLMessage(@RequestBody final String msg) throws Exception {
        PaymentMessage paymentMessage = XMLParser2.parsePaymentMessage(msg);
        return  paymentMessage;
    }






}

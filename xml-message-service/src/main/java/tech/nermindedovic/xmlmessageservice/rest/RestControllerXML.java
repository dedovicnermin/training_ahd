package tech.nermindedovic.xmlmessageservice.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.nermindedovic.xmlmessageservice.onepassparser.XMLParser;
import tech.nermindedovic.xmlmessageservice.onepassparser.trackers.PaymentMessage;


import static org.springframework.http.MediaType.*;

@RestController
@RequestMapping("/api")
@Slf4j
public class RestControllerXML {

    /*
    * IN    : XML as String
    * OUT   : PaymentMessage as Object
    * */

    @PostMapping(path = "parse-xml-toobject",
            consumes = APPLICATION_XML_VALUE
    )
    private PaymentMessage parseXMLMessageToObject(@RequestBody final String msg) throws Exception {
        PaymentMessage paymentMessage = XMLParser.parsePaymentMessage(msg);
        return  paymentMessage;
    }


    /*
    * IN    : XML as String
    * OUT   : PaymentMessage as String
    * */
    @PostMapping(path = "parse-xml-tostring",
            consumes = APPLICATION_XML_VALUE
    )
    private String parseXMLMessageToString(@RequestBody final String msg) throws Exception {
        PaymentMessage paymentMessage = XMLParser.parsePaymentMessage(msg);
        return  paymentMessage.toString();
    }








}

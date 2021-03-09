package tech.nermindedovic.xmlmessageservice.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.nermindedovic.xmlmessageservice.onepassparser.XMLParser;
import tech.nermindedovic.xmlmessageservice.onepassparser.trackers.PaymentMessage;
import tech.nermindedovic.xmlmessageservice.twopassparser.XMLParser2P;
import tech.nermindedovic.xmlmessageservice.twopassparser.data.NetworkBatch;


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
        return XMLParser.parsePaymentMessage(msg);
    }


    /*
    * IN    : XML as String
    * OUT   : PaymentMessage as String
    * */
    @PostMapping(path = "parse-xml-tostring",
            consumes = APPLICATION_XML_VALUE
    )
    private String parseXMLMessageToString(@RequestBody final String msg) throws Exception {
        return XMLParser.parsePaymentMessage(msg).toString();
    }




    @PostMapping(path = "parsetp-xml-toobject",
            consumes = APPLICATION_XML_VALUE
    )
    private NetworkBatch twoParseXMLMessageToObject(@RequestBody final String msg) throws Exception {
        NetworkBatch networkBatch = XMLParser2P.parseXML(msg);
        return networkBatch;
    }



    @PostMapping(path = "parsetp-xml-tostring",
            consumes = APPLICATION_XML_VALUE
    )
    private String twoParseXMLMessageToString(@RequestBody final String msg) throws Exception {
        return XMLParser2P.parseXML(msg).toString();
    }








}

package tech.nermindedovic.xmlmessageservice;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import tech.nermindedovic.xmlmessageservice.business.domain.Creditor;
import tech.nermindedovic.xmlmessageservice.business.domain.Debtor;
import tech.nermindedovic.xmlmessageservice.business.domain.PaymentMessage;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.MediaType.APPLICATION_XML_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@AutoConfigureMockMvc
@WebMvcTest
@Slf4j
class RestControllerXMLTest {

    @Autowired
    private MockMvc mockMvc;

    private String xml = "<PaymentMessage>" +
            "    <Debtor>" +
            "        <name>Jiraya</name>\n" +
            "        <address>Village Hidden in the Leaves</address>\n" +
            "        <accountNumber>03151999</accountNumber>\n" +
            "        <amount>100.00</amount>\n" +
            "    </Debtor>\n" +
            "    <Creditor>\n" +
            "        <name>Naruto</name>\n" +
            "        <address>Village Hidden in the Leaves</address>\n" +
            "        <accountNumber>04281999</accountNumber>\n" +
            "    </Creditor>\n" +
            "</PaymentMessage>";

    @Test
    @DisplayName("XML String -> String Test")
    public void from_xml_to_string_test() throws Exception {
        String url = "/api/get-string";


        String result = mockMvc.perform(post(url)
            .contentType(APPLICATION_XML_VALUE)
            .content(xml))
            .andExpect(status().isOk())
            .andReturn().getResponse().getContentAsString();

        assertEquals(xml,result);

    }

    @Test
    @DisplayName("JSON string -> string  throws test")
    public void given_wrong_content_type_test() throws Exception {
        String url = "/api/get-string";
        String json = "{\n" +
                "    \"debtor\": {\n" +
                "        \"name\": \"Jiraya\",\n" +
                "        \"address\": \"Village Hidden in the Leaves\",\n" +
                "        \"accountNumber\": 3151999,\n" +
                "        \"amount\": \"100.00\"\n" +
                "    },\n" +
                "    \"creditor\": {\n" +
                "        \"name\": \"Naruto\",\n" +
                "        \"address\": \"Village Hidden in the Leaves\",\n" +
                "        \"accountNumber\": 4281999\n" +
                "    }\n" +
                "}";



        mockMvc.perform(post(url)
                .contentType(APPLICATION_JSON_VALUE)
                .content(json))
                .andExpect(status().isUnsupportedMediaType())
                ;
    }



    @Test
    @DisplayName("Payment Message XML -> Payment Message XML")
    public void given_payment_message_returns_payment_message() throws Exception {
        String url = "/api/get-paymentmessage";

        PaymentMessage expected = new PaymentMessage();
        expected.setDebtor(new Debtor());
        expected.setCreditor(new Creditor());

        expected.getDebtor().setName("Jiraya");
        expected.getDebtor().setAddress("Village Hidden in the Leaves");
        expected.getDebtor().setAccountNumber("3151999");
        expected.getDebtor().setAmount("100");

        expected.getCreditor().setName("Naruto");
        expected.getCreditor().setAddress("Village Hidden in the Leaves");
        expected.getCreditor().setAccountNumber("4281999");

        mockMvc.perform(post(url)
            .contentType(APPLICATION_XML_VALUE)
            .content(xml))
            .andExpect(status().isOk())
            .andReturn().getResponse().equals(expected);


    }


    @Test
    public void xml_string_returns_parsed_paymentMessage() throws Exception {
        String url = "/api/parse-xml-string";

        PaymentMessage expected = new PaymentMessage();
        expected.setDebtor(new Debtor());
        expected.setCreditor(new Creditor());

        expected.getDebtor().setName("Jiraya");
        expected.getDebtor().setAddress("Village Hidden in the Leaves");
        expected.getDebtor().setAccountNumber("3151999");
        expected.getDebtor().setAmount("100");

        expected.getCreditor().setName("Naruto");
        expected.getCreditor().setAddress("Village Hidden in the Leaves");
        expected.getCreditor().setAccountNumber("4281999");

        mockMvc.perform(post(url)
                .contentType(APPLICATION_XML_VALUE)
                .content(xml))
                .andExpect(status().isOk())
                .andReturn().getResponse().equals(expected);


    }








}
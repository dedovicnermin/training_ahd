package tech.nermindedovic.xmlmessageservice.xml.trackers;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TrackerFactory {

    public static TagTracker getInstance(String tag) {
        switch (tag) {
            case "PaymentMessage":
                return new PaymentMessage();
            case "Debtor":
                return new Debtor();
            case "Creditor":
                return new Creditor();
            case "name":
                return new Name();
            case "address":
                return new Address();
            case "accountNumber":
                return new AccountNumber();
            case "amount":
                return new Amount();
            default:
                log.debug(tag);
                return null;
        }
    }




}

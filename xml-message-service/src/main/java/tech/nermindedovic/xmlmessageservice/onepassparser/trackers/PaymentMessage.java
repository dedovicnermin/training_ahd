package tech.nermindedovic.xmlmessageservice.onepassparser.trackers;


import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ToString
public class PaymentMessage extends TagTracker {

    private Debtor debtor;
    private Creditor creditor;

    public PaymentMessage() {super("PaymentMessage");}





    @Override
    public void addToSelf(String identity, String data, TagTracker instance) {
        if (instance == null) return;

        switch (identity) {
            case "Debtor":
                debtor = (Debtor) instance;
                break;
            case "Creditor":
                creditor = (Creditor) instance;
        }
    }

}

package tech.nermindedovic.xmlmessageservice.onepassparser.trackers;



public class Root extends TagTracker {
    private PaymentMessage paymentMessage;

    public Root() {
        super("root");
    }


    // dummy

    @Override
    public void addToSelf(String identity, String data, TagTracker instance) {
        return;
    }
}

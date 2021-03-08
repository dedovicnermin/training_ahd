package tech.nermindedovic.xmlmessageservice.xml.trackers;



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

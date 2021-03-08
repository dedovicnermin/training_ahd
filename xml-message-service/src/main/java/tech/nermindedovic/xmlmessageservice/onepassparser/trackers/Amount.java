package tech.nermindedovic.xmlmessageservice.onepassparser.trackers;


public class Amount extends TagTracker {
    private String amount;

    public Amount() {super("amount");}

    public void addToSelf(String identifier, String data, TagTracker instance) {
        if (data == null) {
            instance.addToSelf(this.getIdentifier(), null, this);
        } else {
            if (identifier == "amount") this.amount = data;
        }

    }
    @Override
    public String toString() {
        return amount;
    }


}

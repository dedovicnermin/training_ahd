package tech.nermindedovic.xmlmessageservice.onepassparser.trackers;

public class AccountNumber extends TagTracker {

    private String accountNumber;

    public AccountNumber() {super("accountNumber");}

    @Override
    public void addToSelf(String identifier, String data, TagTracker instance) {
        if (data == null) {
            instance.addToSelf(this.getIdentifier(), null, this);
        } else {if (identifier == "accountNumber") accountNumber = data;}
    }

    @Override
    public String toString() {
        return accountNumber;
    }




}

package tech.nermindedovic.xmlmessageservice.onepassparser.trackers;


import lombok.ToString;

@ToString
public class Creditor extends TagTracker {
    private Name name;
    private Address address;
    private AccountNumber accountNumber;


    public Creditor() { super("Creditor");}




    @Override
    public void addToSelf(String identity, String data, TagTracker instance) {
        if (instance == null) return;

        switch (identity) {
            case "name":
                name = (Name) instance;
                return;
            case "address":
                address = (Address) instance;
                return;
            case "accountNumber":
                accountNumber = (AccountNumber) instance;
                return;
            case "Creditor":
                instance.addToSelf(this.getIdentifier(), null, this);
                break;
        }
    }
}

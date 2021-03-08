package tech.nermindedovic.xmlmessageservice.xml.trackers;


import lombok.ToString;

@ToString
public class Debtor extends TagTracker {

    private Name name;
    private Address address;
    private AccountNumber accountNumber;
    private Amount amount;



    public Debtor() {super("Debtor");}

    public void addToSelf(String identifier, String data, TagTracker instance) {
        if (instance == null) return;

        switch (identifier) {
            case "name":
                name = (Name) instance;
                break;
            case "address":
                address = (Address) instance;
                break;
            case "accountNumber":
                accountNumber = (AccountNumber) instance;
                break;
            case "amount":
                amount = (Amount) instance;
                break;
            case "Debtor":
                instance.addToSelf(this.getIdentifier(), null, this);
                break;
        }

    }
}

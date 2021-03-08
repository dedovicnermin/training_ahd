package tech.nermindedovic.xmlmessageservice.xml.trackers;


import lombok.ToString;


public class Address extends TagTracker {
    public String address;

    public Address() {super("address");}

    @Override
    public void addToSelf(String identity, String data, TagTracker instance) {
        if (data == null) {
            instance.addToSelf(this.getIdentifier(), null, this);
        } else {
            if (identity == "address") address = data;
        }

    }


    @Override
    public String toString() {
        return address;
    }
}

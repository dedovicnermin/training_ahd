package tech.nermindedovic.xmlmessageservice.xml.trackers;





public class Name extends TagTracker {

    private String name;


    public Name() { super("name");}

    @Override
    public void addToSelf(final String identity, final String data, final TagTracker instance) {
        if (data == null) {
            instance.addToSelf(this.getIdentifier(), null, this);
        } else {
            if (identity == "name") name = data;
        };

    }

    @Override
    public String toString() {
        return name;
    }



}

package tech.nermindedovic.xmlmessageservice.xml.trackers;

import lombok.extern.slf4j.Slf4j;


import java.util.Stack;

@Slf4j
public abstract class TagTracker {
    // == field (who am i) ==
    private String identifier;


    public TagTracker(String identifier) {
        this.identifier = identifier;
    }



    public String getIdentifier() {
        return identifier;
    }

    public void on_start_tag(final String tag, final Stack<TagTracker> stack) {
        log.info("\n<" + tag + ">");

        TagTracker tracker = TrackerFactory.getInstance(tag);
        if (tracker == null) return;
        stack.push(tracker);
    }



    public void on_chars(final String data) {
        log.info("CHARS: " + data);

        addToSelf(identifier, data, this);
    }

    public void on_end_tag(final String tag, final Stack<TagTracker> stack) {
        log.info("</" + tag + ">\n");

        TagTracker popped = stack.pop();
        addToSelf(identifier, null, stack.peek());
        stack.push(popped);
        if (tag != "PaymentMessage" && stack.peek().getIdentifier() == tag)
            stack.pop();
    }


    /**
     * This method is used for two things :
     *      1. binding characters/strings to the respective object field. (for Name, Address, AccountNumber, Amount fields)
     *      2. binding a tagTracker to parent object (for binding name to Debtor and Debtor to PaymentMessage on end tags)
     *
     * @param identity: identifies what field or object we're trying to add.
     * @param data: Characters/string. is null if this gets called on END_TAG event
     * @param instance: used to pass parent object. parent object then calls this method again using initial data
     */
    public abstract void addToSelf(final String identity,final String data,final TagTracker instance);























}

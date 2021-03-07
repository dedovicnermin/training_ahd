package tech.nermindedovic.xmlmessageservice.business.domain.v2;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public  abstract class TagTracker {

    public Map<String, TagTracker> trackingMap = new HashMap<>();

//    fieldKey : fieldValue
    public Map<String, String> data = new HashMap<>();







    public void trackTag(String tagName, TagTracker tagTracker) {
        trackingMap.put(tagName, tagTracker);
    }

    public void onStartTag(String tagName, Stack<TagTracker> tagStack) {
        TagTracker toTrack = trackingMap.get(tagName);
        if (toTrack != null) {
            tagStack.push(toTrack);
        }
    }

    public void onEndTag(Stack<TagTracker> tagStack) {
        tagStack.pop();
    }


    public void onChars(String str) {
        data.put(getName(), str);
    }

    public Map<String, String> getData() {
        return data;
    }

    public abstract String getName();





}

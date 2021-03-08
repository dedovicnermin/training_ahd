package tech.nermindedovic.xmlmessageservice.onepassparser;



import tech.nermindedovic.xmlmessageservice.onepassparser.trackers.PaymentMessage;
import tech.nermindedovic.xmlmessageservice.onepassparser.trackers.Root;
import tech.nermindedovic.xmlmessageservice.onepassparser.trackers.TagTracker;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import java.util.Stack;




public class XMLParser {
    public static PaymentMessage parsePaymentMessage(final String msg) throws XMLStreamException {

        XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
        XMLStreamReader streamReader = xmlInputFactory.createXMLStreamReader(new ByteArrayInputStream(msg.getBytes(StandardCharsets.UTF_8)));

        /**
         * top : keep track of the most recent tracker using stack.
         *       Root() is a dummy Tag Tracker, and is used to simplify leveraging TagTrackers
         *       Without it, conditionals would be needed and would need to override the PaymentMessage on_start_tag method.
         */
        TagTracker top = new Root();
        Stack<TagTracker> stack = new Stack<>();
        stack.push(top);





        while (streamReader.hasNext()) {
            int event = streamReader.getEventType();

            switch (event) {
                /**
                 * 1 : START_TAG
                 *   : delegate work to tag trackers. A new TagTracker will be placed onto the stack using the startTag variable.
                 *   : top gets updated.
                 *   : Trackers created using TrackerFactor
                 */
                case 1:
                    String startTag = streamReader.getLocalName();
                    top.on_start_tag(startTag, stack);
                    top = stack.peek();
                    break;


                /**
                 * 2    : END_TAG
                 *      : Should use stack to get parent element and append data to it.
                 *      : takes the latest element from the stack, and appends itself to the parent tagTracker (via Stack ordering)
                 *      : tag tracker is looking out for PaymentMessage ending, and will not pop itself off in this case only.
                 *      : tag tracker is also making sure the top obj on stack == local tag name we pass here
                 */
                case 2:
                    String endTag = streamReader.getLocalName();
                    top.on_end_tag(endTag, stack);
                    top = stack.peek();
                    break;



                /**
                 * 3    : SPACES
                 */
                case 3:
                    break;




                /**
                 * 4    : CHARACTERS
                 *      : Gets characters, and builds string. If no data, doesnt get passed onto tracker
                 *      : Upon completion, data gets tied to the top tracker on the stack (should be itself,
                 *                                                  i.e. name data gets binded with Name object).
                 */
                case 4:
                    int start = streamReader.getTextStart();
                    int length = streamReader.getTextLength();
                    String data = new String(streamReader.getTextCharacters(), start, length).trim();

                    if (data.isEmpty()) break;

                    top.on_chars(data);
                    break;
            }

            streamReader.next();
        }

        /**
         * Tracker makes sure PaymentMessage doesnt get popped off.
         * This design choice was made based on the idea this parse strictly parses PaymentMessage objects.
         * So long as we're looking for paymentMessage, reformating for new properties to consider shouldnt be tedious/troublesome.
         */
        if (stack.isEmpty()) return null;
        PaymentMessage paymentMessage = (PaymentMessage) stack.pop();
        return paymentMessage;


    }
}

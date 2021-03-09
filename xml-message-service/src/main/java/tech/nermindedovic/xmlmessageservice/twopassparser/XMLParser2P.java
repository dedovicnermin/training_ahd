package tech.nermindedovic.xmlmessageservice.twopassparser;


import lombok.extern.slf4j.Slf4j;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import tech.nermindedovic.xmlmessageservice.twopassparser.data.Connection;
import tech.nermindedovic.xmlmessageservice.twopassparser.data.Network;
import tech.nermindedovic.xmlmessageservice.twopassparser.data.NetworkBatch;
import tech.nermindedovic.xmlmessageservice.twopassparser.data.Source;


import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Slf4j
public class XMLParser2P {

    private static final String NETWORK_BATCH_TAG = "NetworkBatch";
    private static final String NETWORK_TAG = "Network";
    private static final String SOURCE_TAG = "source";
    private static final String CONNECTIONS_TAG = "connections";
    private static final String CONNECTION_TAG = "connection";
    private static final String NAME_TAG = "name";
    private static final String DURATION_TAG = "duration";
    private static final String TYPE_TAG = "type";
    private static final String IP_TAG = "ip";

    public static NetworkBatch parseXML(String msg) throws JDOMException, IOException {
        SAXBuilder saxBuilder = new SAXBuilder();
        Document document = saxBuilder.build(new ByteArrayInputStream(msg.getBytes(StandardCharsets.UTF_8)));

        Element root = document.getRootElement();               // NetworkBatch
        if (root.getName() != NETWORK_BATCH_TAG) return null;
        return parseNetworkBatch(root);
    }


    private static NetworkBatch parseNetworkBatch(Element element) {
        NetworkBatch batch = new NetworkBatch();
        List<Element> networkChildren = element.getChildren(NETWORK_TAG);

        networkChildren.forEach(c -> parseNetwork(c, batch));
        return batch;
    }

    private static void parseNetwork(Element element, NetworkBatch parent) {
        Network network = new Network();

        Element sourceElem = element.getChild(SOURCE_TAG);
        Source  source = parseSource(sourceElem);
        network.setSource(source);

        Element nameElem = element.getChild(NAME_TAG);
        network.setNetworkName(getContent(nameElem));

        Element connectionsElem = element.getChild(CONNECTIONS_TAG);
        if (connectionsElem != null) {
            connectionsElem.getChildren(CONNECTION_TAG).forEach(connection -> parseConnection(connection, network));
        }

        parent.getNetworkList().add(network);

    }

    private static void parseConnection(Element connectionChild, Network parent) {
        if (connectionChild.getChildren().isEmpty()) return;
        Connection connection = new Connection();

        Element nameElem = connectionChild.getChild(NAME_TAG);
        connection.setConnectionName(getContent(nameElem));

        Element durationElem = connectionChild.getChild(DURATION_TAG);
        connection.setConnectionDuration(getContent(durationElem));

        Element cTypeElem = connectionChild.getChild(TYPE_TAG);
        connection.setConnectionType(getContent(cTypeElem));

        Element sourceElem = connectionChild.getChild(SOURCE_TAG);
        connection.setConnectionSource(parseSource(sourceElem));

        parent.getConnectionList().add(connection);

    }

    private static Source parseSource(Element element) {
        if (element == null) return null;

        Element ipElem = element.getChild(IP_TAG);
        if (ipElem == null) return null;
        return new Source(getContent(ipElem));


    }


    /**
     * used for tags that dont have nested classes within them.
     * @param child
     * @return
     */
    private static String getContent(Element child) {
        if (child == null || child.getTextTrim().isEmpty()) return null;
        return child.getTextTrim();
    }







}

package tech.nermindedovic.xmlmessageservice.twopassparser.data;


import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Network {
    private Source source = null;
    private String networkName;
    private List<Connection> connectionList = new ArrayList<>();

    public Network() {}



}

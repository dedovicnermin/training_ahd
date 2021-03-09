package tech.nermindedovic.xmlmessageservice.twopassparser.data;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import tech.nermindedovic.xmlmessageservice.twopassparser.data.Network;

@Data
public class NetworkBatch {
    private final List<Network> networkList = new ArrayList<>();

    public NetworkBatch() { }

    public List<Network> getNetworkList() {
        return networkList;
    }


}

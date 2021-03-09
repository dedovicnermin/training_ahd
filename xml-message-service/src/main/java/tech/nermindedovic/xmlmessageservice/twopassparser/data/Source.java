package tech.nermindedovic.xmlmessageservice.twopassparser.data;


import lombok.Data;

@Data
public class Source {
    private String IP;

    public Source(String ip) {
        this.IP = ip;
    }




}



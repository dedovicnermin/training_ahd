package tech.nermindedovic.xmlmessageservice.twopassparser.data;


import lombok.Data;

@Data
public class Connection {
     private String connectionName;
     private String connectionDuration;
     private String connectionType;
     private Source connectionSource;



}

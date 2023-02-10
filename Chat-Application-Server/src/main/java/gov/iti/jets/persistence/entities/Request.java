package gov.iti.jets.persistence.entities;

import java.sql.Date;

public class Request {
    private String sender;
    private String receiver;
    private Date requestDate;


    public Request(){}

    public Request(String sender, String receiver, Date requestDate) {
        this.sender = sender;
        this.receiver = receiver;
        this.requestDate = requestDate;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public Date getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(Date requestDate) {
        this.requestDate = requestDate;
    }

}
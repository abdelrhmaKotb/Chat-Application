package gov.iti.jets.persistence.entities;

import java.sql.Date;

public class Group {
    private int id;
    private String name;
    private Date dateOfCreation;
    private String adminPhoneNumber;

    public Group(int id, String name, Date dateOfCreation, String adminPhoneNumber) {
        this.id = id;
        this.name = name;
        this.dateOfCreation = dateOfCreation;
        this.adminPhoneNumber = adminPhoneNumber;
    }

   

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDateOfCreation() {
        return dateOfCreation;
    }

    public void setDateOfCreation(Date dateOfCreation) {
        this.dateOfCreation = dateOfCreation;
    }

    public String getAdminPhoneNumber() {
        return adminPhoneNumber;
    }

    public void setAdminPhoneNumber(String adminPhoneNumber) {
        this.adminPhoneNumber = adminPhoneNumber;
    }
}

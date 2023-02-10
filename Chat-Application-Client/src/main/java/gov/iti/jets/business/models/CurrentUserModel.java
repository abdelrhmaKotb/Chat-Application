package gov.iti.jets.business.models;

import java.sql.Date;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class CurrentUserModel {

    private StringProperty phoneNumber = new SimpleStringProperty();
    private StringProperty name = new SimpleStringProperty();
    private StringProperty email =  new SimpleStringProperty();
    private StringProperty gender;
    private StringProperty country;
   // private  Date dateOfBirth;
    private StringProperty bio =  new SimpleStringProperty();
    private StringProperty status =  new SimpleStringProperty();

    


    public StringProperty phoneNumberProperty() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber.set(phoneNumber);
    }

    public String getPhoneNumber() {
        return phoneNumber.get();
    }




    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getName() {
        return name.get();
    }




    public StringProperty emailProperty() {
        return email;
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public String getEmail() {
        return email.get();
    }



    public StringProperty statusProperty() {
        return status;
    }

    public void setStatus(String status) {
        this.status.set(status);
    }

    public String getStatus() {
        return status.get();
    }
   
    public void setBio(String b) {
        this.bio.set(b);
    }

    public String getBio() {
        return bio.get();
    }
    public StringProperty bProperty() {
        return bio;
    }
    
}

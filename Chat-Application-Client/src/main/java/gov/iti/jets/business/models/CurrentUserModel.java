package gov.iti.jets.business.models;

import javafx.beans.property.StringProperty;

public class CurrentUserModel {

    private StringProperty phoneNumber;
    private StringProperty name;
    private StringProperty email;
    // private StringProperty gender;
    // private StringProperty country;
    // // private   dateOfBirth;
    // private StringProperty bio;
    private StringProperty status;


    public StringProperty phoneNumberProperty() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber.setValue(phoneNumber);
    }

    public String getPhoneNumber() {
        return phoneNumber.getValue();
    }




    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.setValue(name);
    }

    public String getName() {
        return phoneNumber.getValue();
    }




    public StringProperty emailProperty() {
        return email;
    }

    public void setEmail(String email) {
        this.email.setValue(email);
    }

    public String getEmail() {
        return email.getValue();
    }



    public StringProperty statusProperty() {
        return status;
    }

    public void setStatus(String status) {
        this.status.setValue(status);
    }

    public String getStatus() {
        return status.getValue();
    }

    
}

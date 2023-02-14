package gov.iti.jets.business.models;

import java.sql.Date;
import java.time.LocalDate;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class CurrentUserModel {

    private StringProperty phoneNumber = new SimpleStringProperty();
    private StringProperty name = new SimpleStringProperty();
    private StringProperty email =  new SimpleStringProperty();
    private StringProperty gender=new SimpleStringProperty();
    private IntegerProperty country=new SimpleIntegerProperty();
    private  StringProperty dateOfBirth=new SimpleStringProperty();
    private StringProperty bio =  new SimpleStringProperty();
    private IntegerProperty status =  new SimpleIntegerProperty();
    private LocalDate date;

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    private byte[] image;
    
    


    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

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



    public IntegerProperty statusProperty() {
        return status;
    }

    public void setStatus(int status) {
        this.status.set(status);
    }

    public int getStatus() {
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
    

    public IntegerProperty getCountryProperty() {
        return country;
    }



    public int getCountry() {
        return country.get();
    }

    public IntegerProperty countryProperty() {
        return country;
    }

    public String getGender() {
        return gender.get();
    }

    public StringProperty genderProperty() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender.set(gender);
    }

    public void setCountry(int country) {
        this.country.set(country);
    }

    public String getDateOfBirth() {
        return dateOfBirth.get();
    }

    public StringProperty dateOfBirthProperty() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth.set(dateOfBirth);
    }

    public StringProperty bioProperty() {
        return bio;
    }
}

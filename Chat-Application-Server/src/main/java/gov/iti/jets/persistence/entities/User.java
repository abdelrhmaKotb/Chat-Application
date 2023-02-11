package gov.iti.jets.persistence.entities;

import java.sql.Date;
import java.util.Arrays;

import gov.iti.jets.enums.Gender;
import gov.iti.jets.enums.Mood;

public class User {

    private String phoneNumber;
    private String name;
    private String email;
    private String password;
    private Gender gender;
    private int country;
    private Date dateOfBirth;
    private String bio;
    private boolean isAdmin;
    private boolean isDeleted;
    private Mood status;
    private byte[] image;

    public User(){}

    public User(String phoneNumber, String name, String email, String password, Gender gender, int country,
            Date dateOfBirth, String bio, boolean isAdmin, boolean isDeleted, Mood status,byte[] image) {
        this.phoneNumber = phoneNumber;
        this.name = name;
        this.email = email;
        this.password = password;
        this.gender = gender;
        this.country = country;
        this.dateOfBirth = dateOfBirth;
        this.bio = bio;
        this.isAdmin = isAdmin;
        this.isDeleted = isDeleted;
        this.status = status;
        this.image=image;
    }
   


    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public int getCountry() {
        return country;
    }

    public void setCountry(int country) {
        this.country = country;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date localDate) {
        this.dateOfBirth = localDate;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public Mood getStatus() {
        return status;
    }

    public void setStatus(Mood status) {
        this.status = status;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }
    public void setImage(byte[] image){

        this.image = image;
    }
    public byte[] getImage(){
        return image;
    }
    
    @Override
    public String toString() {
        return "User [phoneNumber=" + phoneNumber + ", name=" + name + ", email=" + email + ", password=" + password
                + ", gender=" + gender + ", country=" + country + ", dateOfBirth=" + dateOfBirth + ", bio=" + bio
                + ", isAdmin=" + isAdmin + ", isDeleted=" + isDeleted + ", status=" + status + ", image="
                + "]";
    }
}
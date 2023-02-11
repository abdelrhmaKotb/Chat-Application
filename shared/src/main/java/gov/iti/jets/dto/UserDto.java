package gov.iti.jets.dto;

import java.io.Serializable;
import java.sql.Date;

import gov.iti.jets.enums.Gender;
import gov.iti.jets.enums.Mood;

public class UserDto implements Serializable {

    private String phoneNumber;
    private String name;
    private String email;
    private Gender gender;
    private int country;
    private Date dateOfBirth;
    private String bio;  
    private Mood status;
    private boolean isAdmin;
    private byte[] image;

    public UserDto(String phoneNumber, String name, String email, Gender gender, int country, Date dateOfBirth,
            String bio, Mood status, boolean isAdmin,byte[] image) {
        this.phoneNumber = phoneNumber;
        this.name = name;
        this.email = email;
        this.gender = gender;
        this.country = country;
        this.dateOfBirth = dateOfBirth;
        this.bio = bio;
        this.status = status;
        this.isAdmin = isAdmin;
        this.image=image;
    }
    public UserDto(String phoneNumber, String name, String email, Gender gender, int country, Date dateOfBirth,
            String bio, Mood status, boolean isAdmin) {
        this.phoneNumber = phoneNumber;
        this.name = name;
        this.email = email;
        this.gender = gender;
        this.country = country;
        this.dateOfBirth = dateOfBirth;
        this.bio = bio;
        this.status = status;
        this.isAdmin = isAdmin;
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

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
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

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    public void setImage(byte[] image){
        this.image=image;
    }
    public byte[] getImage(){
        return image;
    }


    @Override
    public String toString() {
        return "name " + name + "phone " + phoneNumber;
    }
}

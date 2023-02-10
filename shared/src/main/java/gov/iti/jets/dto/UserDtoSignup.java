package gov.iti.jets.dto;

import java.io.Serializable;
import java.sql.Date;
import java.util.Arrays;

import gov.iti.jets.enums.Gender;
import gov.iti.jets.enums.Mood;

public class UserDtoSignup implements Serializable {

    private String phoneNumber;
    private String name;
    private String email;
    private String password;
    private Gender gender;
    private int country;
    private Date dateOfBirth;
    private String bio;
    private Mood status;
    private byte[] image;

    public UserDtoSignup() {
    }

    public UserDtoSignup(String phoneNumber, String name, String email, String password, Gender gender, int country,
            Date dateOfBirth, String bio, Mood status, byte[] image) {
        this.phoneNumber = phoneNumber;
        this.name = name;
        this.email = email;
        this.password = password;
        this.gender = gender;
        this.country = country;
        this.dateOfBirth = dateOfBirth;
        this.bio = bio;
        this.status = status;
        this.image = image;
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

    public void setImage(byte[] image) {

        this.image = image;
    }

    public byte[] getImage() {
        return image;
    }

    
    @Override
    public String toString() {
        return "UserDtoSignup [phoneNumber=" + phoneNumber + ", name=" + name + ", email=" + email + ", password="
                + password + ", gender=" + gender + ", country=" + country + ", dateOfBirth=" + dateOfBirth + ", bio="
                + bio + ", status=" + status + ", image=" + Arrays.toString(image) + "]";
    }

}
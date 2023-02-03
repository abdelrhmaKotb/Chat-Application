package gov.iti.jets.persistence.entities;

import java.io.File;
import java.sql.Date;;
public class User {
    private String phoneNumber;
    private String name;
    private String email;
    private String password;
    private String gender;
    private int country;
    private Date dateOfBirth;
    private String bio;
    private boolean isAdmin;
    private boolean isDeleted;
    private String status;
    private File file;

    public User(){}

    public User(String phoneNumber, String name, String email, String password, String gender, int country,
            Date dateOfBirth, String bio, boolean isAdmin, boolean isDeleted, String status,File file) {
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
        this.file=file;
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
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
    public void setFile(File file){

        this.file=file;
    }
    public File getFile(){
        return file;
    }
}
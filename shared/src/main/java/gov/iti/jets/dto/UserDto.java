package gov.iti.jets.dto;

import java.io.File;
import java.io.Serializable;
import java.sql.Date;

public class UserDto implements Serializable {
    private String phoneNumber;
    private String name;
    private String email;
    private String gender;
    private int country;
    private Date dateOfBirth;
    private String bio;  
    private String status;
    private boolean isAdmin;
    private File file;


    public UserDto(String phoneNumber, String name, String email, String gender, int country, Date dateOfBirth,
            String bio, String status, boolean isAdmin,File file) {
        this.phoneNumber = phoneNumber;
        this.name = name;
        this.email = email;
        this.gender = gender;
        this.country = country;
        this.dateOfBirth = dateOfBirth;
        this.bio = bio;
        this.status = status;
        this.isAdmin = isAdmin;
        this.file=file;
    }
    public UserDto(String phoneNumber, String name, String email, String gender, int country, Date dateOfBirth,
            String bio, String status, boolean isAdmin) {
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

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
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


    @Override
    public String toString() {
        return "name " + name + "phone " + phoneNumber;
    }
}

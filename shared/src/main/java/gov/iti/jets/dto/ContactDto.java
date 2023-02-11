package gov.iti.jets.dto;

import java.io.Serializable;

import gov.iti.jets.enums.Gender;
import gov.iti.jets.enums.Mood;

/**
 *
 * @author Esraa
 */
public class ContactDto implements Serializable {
    private String user;
    private String friendPhoneNumber;
    private String friendName;
    private String friendEmail;
    private Gender friendGender;
    private Mood frinMood;
    private String category;
    private boolean isBlocked;

    public ContactDto(String user, String friendPhoneNumber, String friendName, String friendEmail, Gender friendGender,
            Mood frinMood, String category, boolean isBlocked) {
        this.user = user;
        this.friendPhoneNumber = friendPhoneNumber;
        this.friendName = friendName;
        this.friendEmail = friendEmail;
        this.friendGender = friendGender;
        this.frinMood = frinMood;
        this.category = category;
        this.isBlocked = isBlocked;
    }

    public ContactDto() {
    }

    public ContactDto(String user, String friendPhoneNumber, String category, boolean isBlocked) {
        this.user = user;
        this.friendPhoneNumber = friendPhoneNumber;
        this.category = category;
        this.isBlocked = isBlocked;

    }

    public String getFriendEmail() {
        return friendEmail;
    }

    public void setFriendEmail(String friendEmail) {
        this.friendEmail = friendEmail;
    }

    public Gender getFriendGender() {
        return friendGender;
    }

    public void setFriendGender(Gender friendGender) {
        this.friendGender = friendGender;
    }

    public Mood getFrinMood() {
        return frinMood;
    }

    public void setFrinMood(Mood frinMood) {
        this.frinMood = frinMood;
    }

   

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getFriendName() {
        return friendName;
    }

    public void setFriendName(String friendName) {
        this.friendName = friendName;
    }

    public String getFriendPhoneNumber() {
        return friendPhoneNumber;
    }

    public void setFriendPhoneNumber(String friendPhoneNumber) {
        this.friendPhoneNumber = friendPhoneNumber;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public boolean getIsBlocked() {
        return isBlocked;
    }

    public void setIsBlocked(boolean isBlocked) {
        this.isBlocked = isBlocked;
    }

    
    @Override
    public String toString() {
        return "ContactDto [user=" + user + ", friendPhoneNumber=" + friendPhoneNumber + ", friendName=" + friendName
                + ", friendEmail=" + friendEmail + ", friendGender=" + friendGender + ", frinMood=" + frinMood
                + ", category=" + category + ", isBlocked=" + isBlocked + "]";
    }
}
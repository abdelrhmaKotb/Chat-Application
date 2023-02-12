package gov.iti.jets.dto;

import gov.iti.jets.enums.Gender;
import gov.iti.jets.enums.Mood;

import java.io.Serializable;

/**
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
    private int fontSize;
    private String fontColor;
    private String fontStyle;
    private String backgroundColor;
    private boolean isBold;
    private boolean isUnderlined;
    private boolean isItalic;

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

    public ContactDto(String user, String friendPhoneNumber, String friendName,
                      String friendEmail, Gender friendGender, Mood frinMood, String category,
                      boolean isBlocked, int fontSize, String fontStyle, String fontColor, String backgroundColor,
                      boolean isBold, boolean isUnderlined, boolean isItalic) {
        this.user = user;
        this.friendPhoneNumber = friendPhoneNumber;
        this.friendName = friendName;
        this.friendEmail = friendEmail;
        this.friendGender = friendGender;
        this.frinMood = frinMood;
        this.category = category;
        this.isBlocked = isBlocked;
        this.fontSize = fontSize;
        this.fontColor = fontColor;
        this.fontStyle = fontStyle;
        this.backgroundColor = backgroundColor;
        this.isBold = isBold;
        this.isUnderlined = isUnderlined;
        this.isItalic = isItalic;
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


    public int getFontSize() {
        return fontSize;
    }

    public void setFontSize(int fontSize) {
        this.fontSize = fontSize;
    }

    public String getFontColor() {
        return fontColor;
    }

    public void setFontColor(String fontColor) {
        this.fontColor = fontColor;
    }

    public String getFontStyle() {
        return fontStyle;
    }

    public void setFontStyle(String fontStyle) {
        this.fontStyle = fontStyle;
    }

    public String getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(String backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public boolean isBold() {
        return isBold;
    }

    public void setBold(boolean bold) {
        isBold = bold;
    }

    public boolean isUnderlined() {
        return isUnderlined;
    }

    public void setUnderlined(boolean underlined) {
        isUnderlined = underlined;
    }

    public boolean isItalic() {
        return isItalic;
    }

    public void setItalic(boolean italic) {
        isItalic = italic;
    }
}
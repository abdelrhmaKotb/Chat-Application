package gov.iti.jets.persistence.entities;

public class Contact {
    private String user;
    private String friendPhoneNumber;
    private String category;
    private boolean isBlocked;
    private int fontSize;
    private String fontColor;
    private String fontStyle;
    private String backgroundColor;
    private boolean isBold;
    private boolean isUnderlined;
    private boolean isItalic;

    public Contact() {
    }

    public Contact(String user, String friendPhoneNumber, String category, boolean isBlocked) {
        this.user = user;
        this.friendPhoneNumber = friendPhoneNumber;
        this.category = category;
        this.isBlocked = isBlocked;
    }

    public Contact(String user, String friendPhoneNumber) {
        this.user = user;
        this.friendPhoneNumber = friendPhoneNumber;
    }

    public Contact(String user, String friendPhoneNumber, String category, boolean isBlocked, int fontSize, String fontColor, String fontStyle, String backgroundColor, boolean isBold, boolean isUnderlined, boolean isItalic) {
        this.user = user;
        this.friendPhoneNumber = friendPhoneNumber;
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

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
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

    public boolean isBlocked() {
        return isBlocked;
    }

    public void setBlocked(boolean blocked) {
        isBlocked = blocked;
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
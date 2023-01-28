package gov.iti.jets.persistence.entities;

public class Contact {
    private String user;
    private String friendPhoneNumber;
    private String category;
    private boolean isBlocked;


    public Contact(){}

    public Contact(String user, String friendPhoneNumber, String category, boolean isBlocked) {
        this.user = user;
        this.friendPhoneNumber = friendPhoneNumber;
        this.category = category;
        this.isBlocked = isBlocked;
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

}
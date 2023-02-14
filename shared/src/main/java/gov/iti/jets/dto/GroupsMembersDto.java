package gov.iti.jets.dto;

import java.io.Serializable;
import java.sql.Date;

public class GroupsMembersDto implements Serializable {
    private int group_id;
    private String memberPhoneNumber;
    private Date join_date;

    private int fontSize;
    private String fontColor;
    private String fontStyle;
    private String backgroundColor;
    private boolean isBold;
    private boolean isUnderlined;
    private boolean isItalic;
    public GroupsMembersDto(int group_id, String memberPhoneNumber, Date join_date) {
        this.group_id = group_id;
        this.memberPhoneNumber = memberPhoneNumber;
        this.join_date = join_date;
    }

    public GroupsMembersDto(int group_id, String memberPhoneNumber, int fontSize, String fontStyle, String fontColor, String backgroundColor, boolean isBold, boolean isUnderlined, boolean isItalic) {
        this.group_id = group_id;
        this.memberPhoneNumber = memberPhoneNumber;
        this.fontSize = fontSize;
        this.fontColor = fontColor;
        this.fontStyle = fontStyle;
        this.backgroundColor = backgroundColor;
        this.isBold = isBold;
        this.isUnderlined = isUnderlined;
        this.isItalic = isItalic;
    }

    public int getGroup_id() {
        return group_id;
    }

    public void setGroup_id(int group_id) {
        this.group_id = group_id;
    }

    public String getMemberPhoneNumber() {
        return memberPhoneNumber;
    }

    public void setMemberPhoneNumber(String memberPhoneNumber) {
        this.memberPhoneNumber = memberPhoneNumber;
    }

    public Date getJoin_date() {
        return join_date;
    }

    public void setJoin_date(Date join_date) {
        this.join_date = join_date;
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

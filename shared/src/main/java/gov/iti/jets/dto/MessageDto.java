package gov.iti.jets.dto;

import java.io.Serializable;
import java.sql.Date;

public class MessageDto implements Serializable {
    private String sender;

    private String message;
    private int fontSize;
    private String fontStyle;
    private String fontColor;
    private String backgroundColor;
    private boolean isBold;
    private boolean isUnderlined;
    private boolean isItalic;

    private String reciver;

    private Date messageDate;

    // public MessageDto(String message, int fontSize, String fontStyle, String
    // fontColor, String backgroundColor,
    // boolean isBold, boolean isUnderlined, boolean isItalic) {
    // this.message = message;
    // this.fontSize = fontSize;
    // this.fontStyle = fontStyle;
    // this.fontColor = fontColor;
    // this.backgroundColor = backgroundColor;
    // this.isBold = isBold;
    // this.isUnderlined = isUnderlined;
    // this.isItalic = isItalic;
    // }

    public Date getMessageDate() {
        return messageDate;
    }

    public void setMessageDate(Date messageDate) {
        this.messageDate = messageDate;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getFontSize() {
        return fontSize;
    }

    public void setFontSize(int fontSize) {
        this.fontSize = fontSize;
    }

    public String getFontStyle() {
        return fontStyle;
    }

    public void setFontStyle(String fontStyle) {
        this.fontStyle = fontStyle;
    }

    public String getFontColor() {
        return fontColor;
    }

    public void setFontColor(String fontColor) {
        this.fontColor = fontColor;
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

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReciver() {
        return reciver;
    }

    public void setReciver(String reciver) {
        this.reciver = reciver;
    }


    @Override
    public String toString() {
        return "sender " + sender + " reciver " + reciver + " message " + message;
    }
}

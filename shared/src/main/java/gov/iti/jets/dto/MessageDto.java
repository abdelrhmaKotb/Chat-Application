package gov.iti.jets.dto;

import java.io.Serializable;

public class MessageDto implements Serializable {

    class MessageContent {
        private String message;
        private int fontSize;
        private String fontStyle;
        private String fontColor;
        private String backgroundColor;
        private boolean isBold;
        private boolean isUnderlined;
        private boolean isItalic;

        public MessageContent(String message, int fontSize, String fontStyle, String fontColor, String backgroundColor,
                boolean isBold, boolean isUnderlined, boolean isItalic) {
            this.message = message;
            this.fontSize = fontSize;
            this.fontStyle = fontStyle;
            this.fontColor = fontColor;
            this.backgroundColor = backgroundColor;
            this.isBold = isBold;
            this.isUnderlined = isUnderlined;
            this.isItalic = isItalic;
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
    }

}

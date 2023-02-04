package gov.iti.jets.presentation.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignUpValidation {
   public String validatePhoneNumber(String phoneNumber) {

      Pattern p = Pattern.compile(
            "^01[0125][0-9]{8}$");

      Matcher m = p.matcher(phoneNumber);
      boolean validPhone = m.matches();
      if (validPhone) {
         return "valid phone";

      } else {
         return "invalid phone";

      }
   }

   public String validateUserName(String userName) {

      Pattern pattern = Pattern.compile("^[a-zA-Z]{3,29}$");
      Matcher m = pattern.matcher(userName);
      boolean validName = m.matches();
      if (validName) {
         return "valid userName";
      } else {

         return "Name must only contains letters and its size must be between 3 to 30 letters";
      }
   }

   public String validateEmail(String email) {

      Pattern pattern = Pattern
            .compile("^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}");
      Matcher m = pattern.matcher(email);
      boolean validEmail = m.matches();
      if (validEmail) {
         return "valid email";
      } else {
         return "invalid email ";
      }
   }

   public static String validPassword(String password) {
      if (password.length() > 15 || password.length() < 8) {
         return "must be less than 20 and more than 8 characters.";
      }
      String upperCaseChars = "(.*[A-Z].*)";
      if (!password.matches(upperCaseChars)) {
         return "must have atleast one uppercase character";
      }
      String lowerCaseChars = "(.*[a-z].*)";
      if (!password.matches(lowerCaseChars)) {
         return "must have atleast one lowercase character";
      }
      String numbers = "(.*[0-9].*)";
      if (!password.matches(numbers)) {
         return "must have atleast one number";
      }
      String specialChars = "(.*[!,@,#,$,%].*$)";
      if (!password.matches(specialChars)) {
         return "must have atleast one special character among !@#$%";
      }
      return "valid password";
   }

}

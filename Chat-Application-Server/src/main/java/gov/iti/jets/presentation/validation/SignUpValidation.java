package gov.iti.jets.presentation.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignUpValidation {
   public void validatePhoneNumber(String phoneNumber) {

      Pattern p = Pattern.compile(
            "^(\\d{1,3}( )?)?((\\(\\d{3}\\))|\\d{3})[- .]?\\d{3}[- .]?\\d{4}$"
                  + "|^(\\d{1,3}( )?)?(\\d{3}[ ]?){2}\\d{3}$"
                  + "|^(\\d{1,3}( )?)?(\\d{3}[ ]?)(\\d{2}[ ]?){2}\\d{2}$");

      Matcher m = p.matcher(phoneNumber);
      boolean validPhone = m.matches();
      if (validPhone) {
         System.out.println("valid phone");

      } else {
         System.out.println("invalid phone");

      }
   }

   public void validateUserName(String userName) {

      Pattern pattern = Pattern.compile("^[a-zA-Z][A-Za-z0-9_]{6,29}$");
      Matcher m = pattern.matcher(userName);
      boolean validName = m.matches();
      if (validName) {
         System.out.println("valid userName");
      } else {
         System.out.println(
               "User name must contain at least 7 digits leter or number or underscore and starts with letter ");
      }
   }

   public void validateEmail(String email) {

      Pattern pattern = Pattern
            .compile("^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}");
      Matcher m = pattern.matcher(email);
      boolean validEmail = m.matches();
      if (validEmail) {
         System.out.println("valid email");
      } else {
         System.out.println("invalid email ");
      }
   }

   public void validatePassword(String password) {
      String regex = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{8,20}$";
      if (!isValidPass(password, regex)) {
         System.out.println("Try Again");
      } else {
         System.out.println(password);
      }

   }

   public boolean isValidPass(String password, String regex) {
      Pattern pattern = Pattern.compile(regex);
      Matcher matcher = pattern.matcher(password);
      return matcher.matches();
   }

   public void confirmPass(String password, String confirmPassword) {
      if (!password.equals(confirmPassword)) {
         System.out.println("Wrong pass" + confirmPassword);
      } else {
         System.out.println(confirmPassword);
      }
   }

}

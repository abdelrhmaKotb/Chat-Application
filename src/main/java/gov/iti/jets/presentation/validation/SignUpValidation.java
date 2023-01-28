package gov.iti.jets.presentation.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignUpValidation {
   public String validatePhoneNumber(String phoneNumber) {
        
        Pattern p = Pattern.compile(
         "^(\\d{1,3}( )?)?((\\(\\d{3}\\))|\\d{3})[- .]?\\d{3}[- .]?\\d{4}$" 
       + "|^(\\d{1,3}( )?)?(\\d{3}[ ]?){2}\\d{3}$" 
       + "|^(\\d{1,3}( )?)?(\\d{3}[ ]?)(\\d{2}[ ]?){2}\\d{2}$");
 
     
          Matcher m = p.matcher(phoneNumber);
          boolean validPhone=m.matches();
          if(validPhone){
            return "valid phone";
             
          }
          else {
             return "invalid phone";
             
          }
     }
    public String validateUserName(String userName){
         
         Pattern pattern=Pattern.compile("^[a-zA-Z][A-Za-z0-9_]{6,29}$");
         Matcher m = pattern.matcher(userName);
          boolean validName=m.matches();
          if(validName){
             return "valid userName";
          }
          else {
             return "at least 7 leter or number and starts with letter ";
          }
     }
    public String validateEmail(String email){
        
         Pattern pattern=Pattern.compile("^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}");
         Matcher m = pattern.matcher(email);
          boolean validEmail=m.matches();
          if(validEmail){
             return "valid email";
          }
          else {
             return "invalid email ";
          }
     }
 
    
}

package gov.iti.jets.presentation.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import java.util.ResourceBundle;
import java.net.URL;
import java.rmi.registry.RegistryHandler;
import java.util.regex.*;

import javax.lang.model.util.ElementScanner14;

import gov.iti.jets.presentation.validation.SignUpValidation;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.awt.Color;
import java.io.File;
import java.io.IOException;

public class SignupController implements Initializable {

    @FXML
    private TextField txtPhoneNumber;

    @FXML
    private Button btnSignup;

    @FXML
    private Button btnSignin;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private PasswordField txtConfirmPassword;

    @FXML
    private DatePicker datepickerDateOfBirth;

    @FXML
    private ChoiceBox<String> choiceboxGender;

    @FXML
    private ChoiceBox<String> choiceboxCountry;

    @FXML
    private TextField txtUserName;

    @FXML
    private TextField txtBio;

    @FXML
    private TextField txtEmail;

    @FXML
    private ImageView imageviewProfileImage;
    
    @FXML
    private Label errorPhoneNumber,
         errorUerName,errorEmail,errorPassword,
         errofConfirmPassword,errorBio,errorDateOfBirth,errorGender
         ,errorCountry,errorProfileImage,successMessage;
    @FXML
     private Circle mycircle;

     File file;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        choiceboxGender.getItems().add("Female");
        choiceboxGender.getItems().add("Male");
        choiceboxCountry.getItems().add("Egypt");
        choiceboxCountry.getItems().add("Saudi Arabi");
        choiceboxCountry.getItems().add("Emrates");
        Image img=new Image("/images/person.png",false);
        mycircle.setFill(new ImagePattern(img));
        
         
    } 

    @FXML
    void clickBtnSignin(ActionEvent event) throws IOException {
        Stage stage=(Stage) btnSignup.getScene().getWindow();
        stage.close();
        Stage primarystage=new Stage();
        primarystage.setTitle("Login");
        Parent root = FXMLLoader.load(getClass().getResource("/views/login.fxml"));
        primarystage.setScene(new Scene(root,850,500));
        primarystage.show();

    }

    @FXML
    void clickBtnSignup(ActionEvent event) {
        if( validatePassword()&&confirmPass()&&
        isValidGeneder()&&isValidCountry()&&
        isValidPhoneNumber()&&isValidUserName()&&
        isValidImage()&&isValidDateOfBirth()&&isValidBio()&&isValidEmail()
        )
        successMessage.setOpacity(1);
        else{
            validatePassword();
            confirmPass();   
            isValidGeneder();
            isValidCountry();
            isValidPhoneNumber();
            isValidUserName();
            isValidImage();
            isValidDateOfBirth();
            isValidBio();
            isValidEmail();
        }
    }

    @FXML
    private void clickImageviewProfileImage(MouseEvent event) {
        final FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Image files ", "*.PNG", "*.JPG",
                "*.JPEG", "*.GIF", "*.SVG");
        fileChooser.getExtensionFilters().addAll(extFilter);
         file = fileChooser.showOpenDialog(null);
        if (file != null) {
            mycircle.setStroke(null);
            Image img = new Image(file.toURI().toString());
            mycircle.setFill(new ImagePattern(img));
        }
    }

    public boolean validatePassword() {
        boolean flag=false;
        String regex = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{8,20}$";
        if (!isValidPass(txtPassword.getText().trim(), regex)) {
            showErrorMessageLabel(errorPassword,txtPassword,"Not Valid");
            flag= false;
        }
        else{
           resetFields(errorPassword,txtPassword);
           flag= true;
        }
        return flag;
    }

    public boolean isValidPass(String password, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }

    public boolean confirmPass() {
        boolean flag=false;
        if (!txtPassword.getText().trim().equals(txtConfirmPassword.getText().trim())) {
            showErrorMessageLabel(errofConfirmPassword,txtConfirmPassword,"Not Matched");
            flag=false;
        }
        else{
          resetFields(errofConfirmPassword,txtConfirmPassword);
          if(!txtPassword.getText().trim().equals("")&&!txtConfirmPassword.getText().trim().equals(""))
                flag=true;
          else
                flag=false;
        }
        return flag;
    }

    public boolean isValidPhoneNumber() {
        boolean flag=false;
        SignUpValidation validation=new SignUpValidation();
        if(validation.validatePhoneNumber(txtPhoneNumber.getText().trim()).equals("invalid phone")){
                showErrorMessageLabel(errorPhoneNumber,txtPhoneNumber,"invalid phone");
                flag=false;
        }
        else{
                resetFields(errorPhoneNumber,txtPhoneNumber);
                flag=true;
            }
       return flag;
    }

    public boolean isValidUserName(){

        boolean flag=false;
        SignUpValidation validation=new SignUpValidation();
        if(!validation.validateUserName(txtUserName.getText()).trim().equals("valid userName")){
            showErrorMessageLabel(errorUerName,txtUserName,validation.validateUserName(txtUserName.getText().trim()));
            flag=false;
        }
        else{
            resetFields(errorUerName,txtUserName);
            flag=true;
        }

        return flag;


    }
    public boolean isValidEmail(){

        boolean flag=false;
        SignUpValidation validation=new SignUpValidation();
        if(!validation.validateEmail(txtEmail.getText().trim()).equals("valid email")){
            showErrorMessageLabel(errorEmail,txtEmail,"invalid email");
            flag=false;
          }
         else{
            resetFields(errorEmail,txtEmail);
            flag=true;
        
          }

        return flag;
    }

    public boolean isValidBio(){

        boolean flag=false;
        if(txtBio.getText().trim().equals("")){
            showErrorMessageLabel(errorBio,txtBio,"Required");
            flag=false;
         }
        else{
             resetFields(errorBio,txtBio);
             flag=true;
            }

        return flag;
    }

    public boolean isValidDateOfBirth(){

        boolean flag=false;
        if(datepickerDateOfBirth.getValue()==null){
            showErrorMessageDatePicker(errorDateOfBirth,datepickerDateOfBirth,"Required");
            flag=false;
        }
        else{
            errorDateOfBirth.setOpacity(0);
            datepickerDateOfBirth.setStyle("-fx-border-color:derive(#2D75E8,80%)");
            flag=true;
        }

        return flag;
    }
    
    public boolean isValidImage(){ 

        boolean flag=false;
        if(file==null){
            flag=false;
            errorProfileImage.setOpacity(1);
            errorProfileImage.setStyle("-fx-border-color: RED; -fx-border-width: 2; -fx-border-radius: 5;");
         }

        else{
            flag=true;
           errorProfileImage.setOpacity(0);
        }

        return flag;

    }
    public boolean isValidGeneder(){

       boolean flag=false;

        if (choiceboxGender.getValue() ==null){
            showErrorMessageChoiceBox(errorGender, choiceboxGender,"Required");
            flag=false;
         }
         else{
             errorGender.setOpacity(0);
             choiceboxGender.setStyle("-fx-border-color:derive(#2D75E8,80%)");
             flag=true;
         }
         return flag;
        
    }
    public boolean isValidCountry(){
        boolean flag=false;

          
        if (choiceboxCountry.getValue() ==null){
            showErrorMessageChoiceBox(errorCountry, choiceboxCountry,"Required");
            flag=false;
         }
         else{
             errorCountry.setOpacity(0);
             choiceboxCountry.setStyle("-fx-border-color:derive(#2D75E8,80%)");
             flag=true;
         }


         return flag;

    }
    public void showErrorMessageChoiceBox(Label errorName,ChoiceBox errorStyel,String str){                String errorStyle = String.format("-fx-border-color: RED; -fx-border-width: 2; -fx-border-radius: 5;");
        errorName.setOpacity(1.0);
        errorStyel.setStyle(errorStyle);
        errorName.setText(str);

    }

    public void showErrorMessageDatePicker(Label errorName,DatePicker errorStyel,String str){
        String errorStyle = String.format("-fx-border-color: RED; -fx-border-width: 2; -fx-border-radius: 5;");
        errorName.setOpacity(1.0);
        errorStyel.setStyle(errorStyle);
        errorName.setText(str);

    }

    public void showErrorMessageLabel(Label errorName,TextField errorStyel,String str){
        String errorStyle = String.format("-fx-border-color: RED; -fx-border-width: 2; -fx-border-radius: 5;");
        errorName.setOpacity(1.0);
        errorStyel.setStyle(errorStyle);
        errorName.setText(str);

    }
    public void resetFields(Label errorName,TextField errorStyle){
        errorName.setOpacity(0);
        errorStyle.setStyle("-fx-border-color:derive(#2D75E8,80%)");
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import Uml.User;
import DBClasses.UserDBConnector;
import java.io.IOException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author 786 Computers
 */
public class loginController implements Initializable {
    

    
    @FXML
    private VBox loginTab;
    @FXML
    private VBox signupTab;
    @FXML
    private TextField LogInusernameTextField;
    @FXML
    private PasswordField LogInpasswordTextField;
    @FXML
    private Label LogInValidateLabel;
    @FXML
    private Label SignUpValidateLabel;
    @FXML
    private TextField SignUpFirstnameTextField;
    @FXML
    private TextField SignUpLastnameTextField;
    @FXML
    private TextField SignUpUsernameTextField;
    @FXML
    private TextField SignUpEmailTextField;
    @FXML
    private PasswordField SignUpPasswordField;
    @FXML
    private PasswordField SignUpConfirmPasswordField;
    @FXML
    private Label SignUpPasswordMatchLabel;
    @FXML
    private Button selectLogInBtnId;
    @FXML
    private Button selectSignUpBtnId;
    @FXML
    private Button logInBtnId;
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }     
//    Login Window - login button
    @FXML
    private void logInBtn(ActionEvent e){
        LogInValidateLabel.setText("Loading...");
        if(LogInusernameTextField.getText().isEmpty() == false && LogInpasswordTextField.getText().isEmpty() == false){
            UserDBConnector userDb =new UserDBConnector();
            System.out.println(LogInusernameTextField.getText()+" , " + LogInpasswordTextField.getText() );
            String pw = LogInpasswordTextField.getText();
            String userName = LogInusernameTextField.getText();
//            boolean check = userDb.verify( pw, userName);
            User user = userDb.fetchUser(pw, userName);
            
            if(user != null){
                
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("main.fxml"));
                    
                    Parent root = loader.load();
                    MainController mainController = loader.getController();
                    mainController.dataInit(user);

                    Scene scene = new Scene(root);
                    
                    Stage stage = new Stage();
                    
                    stage.setScene(scene);
                    
                    stage.show();
                    closeWindow();
                }catch(org.json.JSONException ex){
                    LogInValidateLabel.setText(ex.getMessage());
                    System.out.println("herer in org.json.JSONException");
                }
                catch (IOException ex) {
                    Logger.getLogger(loginController.class.getName()).log(Level.SEVERE, null, ex);
                }
                catch(Exception ex){
                    LogInValidateLabel.setText(ex.getMessage());
                }  
            }
            else{
                LogInValidateLabel.setText("Invalid logIn");
            }
        }
        else{
            LogInValidateLabel.setText("Please Enter Username and Password");
        }
    }
 
//    signup window - signup button
    @FXML
    private void signUpBtn(ActionEvent event){
        
        SignUpValidateLabel.setText("");
        SignUpPasswordMatchLabel.setText("");
//        Check all fields are non empty

        if(SignUpUsernameTextField.getText().isEmpty() == false && SignUpPasswordField.getText().isEmpty() == false
           && SignUpFirstnameTextField.getText().isEmpty() == false && SignUpLastnameTextField.getText().isEmpty() == false 
           && SignUpEmailTextField.getText().isEmpty() == false && SignUpConfirmPasswordField.getText().isEmpty() == false){
//            confirm password
            if(SignUpConfirmPasswordField.getText().equals(SignUpPasswordField.getText())){
                
                UserDBConnector userDb =new UserDBConnector();
                userDb.insert(SignUpFirstnameTextField.getText(), SignUpLastnameTextField.getText(), 
                        SignUpUsernameTextField.getText(), SignUpPasswordField.getText(), SignUpEmailTextField.getText());
                SignUpValidateLabel.setText("Account created successfully");
                
            }
            else{
                SignUpPasswordMatchLabel.setText("Password does not match!");
            }
        }
        else{
            SignUpValidateLabel.setText("Please Enter All Infromations");   
        }
    }

    @FXML
    private void selectLogInBtn(ActionEvent event) {
        String design = ";-fx-background-color:  #0d0c0d ;-fx-border-width: 0px 0px 3px 0px;-fx-border-radius: 3px";
        signupTab.setVisible(false);
        loginTab.setVisible(true);
        selectSignUpBtnId.setStyle("-fx-text-fill: #dad8d8;-fx-border-color:#dad8d8"+design);
        selectLogInBtnId.setStyle("-fx-text-fill: #a80014;-fx-border-color:#a80014"+design);
    }

    @FXML
    private void selectSignUpBtn(ActionEvent event) {
        String design = ";-fx-background-color:  #0d0c0d ;-fx-border-width: 0px 0px 3px 0px;-fx-border-radius: 3px";
        signupTab.setVisible(true);
        loginTab.setVisible(false);
        selectLogInBtnId.setStyle("-fx-text-fill: #dad8d8;-fx-border-color:#dad8d8"+design);
        selectSignUpBtnId.setStyle("-fx-text-fill: #a80014;-fx-border-color:#a80014"+design);
    }
    private void closeWindow(){
        Stage stage = (Stage)logInBtnId.getScene().getWindow();
        stage.close();
    }

}

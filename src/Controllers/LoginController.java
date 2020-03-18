/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Models.User;
import java.io.IOException;
import java.sql.SQLException;
import static javafx.application.ConditionalFeature.FXML;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import java.sql.*;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;

/**
 *
 * @author Chris

 */
public class LoginController {
    @FXML
    private Button loginButton;
    @FXML
    private TextField loginUsername;
    @FXML
    private TextField loginPassword;
    @FXML
    private TextField registerFirstname;
    @FXML
    private TextField registerLastname;
    @FXML
    private TextField registerUsername;
    @FXML
    private TextField registerPassword;
    
    Connection conn = null;
    PreparedStatement pst= null;
    ResultSet rs= null;
    
    
    @FXML
    private void login(ActionEvent event) throws SQLException, IOException {
        
         conn = SqlConnect.ConnectDB();
        String Sql = "Select * from logindatabase where username=? and password=?";
        try{
            pst= conn.prepareStatement(Sql);
            pst.setString(1, loginUsername.getText());
            pst.setString(2, loginPassword.getText());
            rs=pst.executeQuery();
            if(rs.next()) {
                Parent root = FXMLLoader.load(getClass().getResource("/Views/Question.fxml"));
                loginButton.getScene().setRoot(root);
            }
            else{
                Alert alert= new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Invalid");
                alert.setHeaderText(null);
                alert.setContentText("Invalid Username or Password");
                alert.showAndWait();
            }
            conn.close();
        } catch(Exception e) {
            
            
        }
        
        
        
        
        
        
        
       /* if (loginUsername.getText().equals("test") && loginPassword.getText().equals("test")) {
            //TODO: Link to database
            System.out.println("User logged in.");
            Parent root = FXMLLoader.load(getClass().getResource("/Views/Question.fxml"));
            loginButton.getScene().setRoot(root);
        } */
    }
    
    @FXML
    private void register(ActionEvent event) throws SQLException {
        String firstName = registerFirstname.getText();
        String lastName = registerLastname.getText();
        String username = registerUsername.getText();
        String password = registerPassword.getText();
        
        PreparedStatement ps;
        ResultSet rs;
        String registerUserQuery = "INSERT INTO `logindatabase`(`first_name`, `last_name`, `username`, `password`) VALUES (?,?,?,?)";
        
        ps = SqlConnect.ConnectDB().prepareStatement(registerUserQuery);
        ps.setString(1, firstName);
        ps.setString(2, lastName);
        ps.setString(3, username);
        ps.setString(4, password);
        
        if(ps.executeUpdate() !=0){
            Alert alert= new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Confirmed");
                alert.setHeaderText(null);
                alert.setContentText("Your account has been created");
                alert.showAndWait(); 
        }
        else {
            Alert alert= new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Error: Check your information");
                alert.showAndWait(); 
        }
            
        
        
        
        //TODO: Implement register
    }
    
    private void showLoginFailed() {
        //TODO: Show login failed message
    }
}

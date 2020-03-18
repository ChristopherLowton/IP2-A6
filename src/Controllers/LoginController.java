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
    private TextField registerLastNname;
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
        User user = new User();
        //TODO: Implement register
    }
    
    private void showLoginFailed() {
        //TODO: Show login failed message
    }
}

package Controllers;

import Models.User;
import java.sql.SQLException;
import java.util.Optional;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Chris
 */
public class QuestionController {
    @FXML
    private Button logout;
    @FXML
    private TextArea question;
    @FXML
    private CheckBox answerA;
    @FXML
    private CheckBox answerB;
    @FXML
    private CheckBox answerC;
    @FXML
    private CheckBox answerD;
    @FXML
    private Button next;
    
    @FXML
    private void logout(ActionEvent event) throws SQLException, Exception {
        
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm?");
        alert.setHeaderText(null);
        alert.setContentText("Are you ok with this?");
        Optional<ButtonType> result = alert.showAndWait();
         if (result.get() == ButtonType.OK){
    // ... user chose OK
    // (new FXMLLoader(getClass().getResource("/Views/Login.fxml"))).load();
    
        Parent loginParent = FXMLLoader.load(getClass().getResource("/Views/Login.fxml"));
         Scene loginScene = new Scene(loginParent);
         
         Stage loginPage = (Stage)((Node)event.getSource()).getScene().getWindow();
             
         loginPage.setScene(loginScene);
         loginPage.show();
         } 
         else {
    // ... user chose CANCEL or closed the dialog
              }
        
        
       
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

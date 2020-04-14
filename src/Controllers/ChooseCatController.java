/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author syed
 */
public class ChooseCatController implements Initializable {

    @FXML
    private Button logout;
    @FXML
    private QuestionController questionController;
    
    
    Connection conn = null;
    PreparedStatement pst= null;
    ResultSet rs= null;
    
    
    
    
    @FXML
    private void easyCatButton(ActionEvent event) throws SQLException, IOException{
         Parent QuestionParent = FXMLLoader.load(getClass().getResource("/Views/Question.fxml"));
         Scene QuestionScene = new Scene(QuestionParent);
         
         Stage QuestionPage = (Stage)((Node)event.getSource()).getScene().getWindow();
             
         QuestionPage.setScene(QuestionScene);
         QuestionPage.show();       
        
         //calling easycategory to create first question
       // QuestionController question = new QuestionController();
       // question.easyCat();
         
         
         
                
                }
        

    
    
    @FXML
    private void mediumCat() {
        
    }
    
    @FXML
    private void hardCat() {
        
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void logout(ActionEvent event) throws SQLException, Exception {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm?");
        alert.setHeaderText(null);
        alert.setContentText("Are you ok with this?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            // ... user chose OK
            // (new FXMLLoader(getClass().getResource("/Views/Login.fxml"))).load();

            Parent loginParent = FXMLLoader.load(getClass().getResource("/Views/Login.fxml"));
            Scene loginScene = new Scene(loginParent);

            Stage loginPage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            loginPage.setScene(loginScene);
            loginPage.show();
        } else {
            // ... user chose CANCEL or closed the dialog
        }

    }
    
}

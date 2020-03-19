package Controllers;

import Models.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
    private Button cancel;
    
    Connection conn = null;
    PreparedStatement pst= null;
    ResultSet rs= null;
    
    @FXML
    public void easyCat() throws SQLException {
        conn = SqlConnect.ConnectDB();
        //for(int i=1;i<11;i++) 
        String Sql = "Select * from easyCat_DB where question_id=2 "; 
        pst = conn.prepareStatement(Sql);
        //pst.setString(1, q.getText());
        rs=pst.executeQuery(); 
        
        if(rs.next()){
            String Question = rs.getString("question_desc");
            String ansA = rs.getString("answer_A");
            String ansB = rs.getString("answer_B");
            String ansC = rs.getString("answer_C");
            String ansD = rs.getString("answer_D");
            question.setText(Question);
            answerA.setText(ansA);
            answerB.setText(ansB);
            answerC.setText(ansC);
            answerD.setText(ansD);
            }
        
    }
    
    
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
}
    
    

  


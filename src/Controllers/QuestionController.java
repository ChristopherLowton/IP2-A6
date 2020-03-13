package Controllers;

import Models.User;
import java.sql.SQLException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
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
        (new FXMLLoader(getClass().getResource("/Login/Login.fxml"))).load();
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

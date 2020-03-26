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
import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;

/**
 *
 * @author Chris
 *
 */
public class LoginController {

    @FXML
    private Button loginButton;
    @FXML
    private TextField loginEmail;
    @FXML
    private TextField loginPassword;
    @FXML
    private TextField registerFirstName;
    @FXML
    private TextField registerLastName;
    @FXML
    private TextField registerEmail;
    @FXML
    private TextField registerPassword;

    Sql sql = new Sql();
    SceneManager scene = new SceneManager();

    @FXML
    private void login(ActionEvent event) throws SQLException, IOException {
        if (sql.validateLogin(loginEmail.getText(), loginPassword.getText())) {
            scene.switchScene("ChooseCategory");
        } else {
            showAlert(Alert.AlertType.ERROR, "Invalid", "Invalid email or password.");
        }
    }

    @FXML
    private void register(ActionEvent event) throws SQLException {
        if (sql.registerUser(registerFirstName.getText(), registerLastName.getText(), registerEmail.getText(), registerPassword.getText())) {
            registerFirstName.clear();
            registerLastName.clear();
            registerEmail.clear();
            registerPassword.clear();
            showAlert(Alert.AlertType.INFORMATION, "Success", "You have successfully registered.");
        } else {
            showAlert(Alert.AlertType.ERROR, "Error", "Error: Check your information.");
        }
    }

    private Optional<ButtonType> showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        return alert.showAndWait();
    }
}

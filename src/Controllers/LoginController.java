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
    private Button testManager;

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

    private int userId;

    @FXML
    private void login(ActionEvent event) throws SQLException, IOException {
        this.userId = (sql.validateLogin(loginEmail.getText(), loginPassword.getText()));
        if (this.userId > 0) {
            boolean admin = this.sql.getUserById(this.userId).isAdmin();
            if (admin) {
                scene.switchScene("ManagerLandingPage");
            } else {
                scene.switchScene("ChooseCategory");
                ((ChooseCategoryController) scene.getLoader().getController()).initialize(this.userId);
            }
        } else {
            showAlert(Alert.AlertType.ERROR, "Invalid", "Invalid email or password.");
        }
    }

    @FXML
    private void register(ActionEvent event) throws SQLException {
        if (registerFirstName.getText().isEmpty() == false && registerLastName.getText().isEmpty() == false && registerEmail.getText().isEmpty() == false && registerPassword.getText().isEmpty() == false) {
            if (this.sql.getUserByEmail(registerEmail.getText()) == null) {
                if (sql.registerUser(registerFirstName.getText(), registerLastName.getText(), registerEmail.getText(), registerPassword.getText())) {
                    registerFirstName.clear();
                    registerLastName.clear();
                    registerEmail.clear();
                    registerPassword.clear();
                    showAlert(Alert.AlertType.INFORMATION, "Success", "You have successfully registered.");
                } else {
                    showAlert(Alert.AlertType.ERROR, "Error", "Error: Check your information.");
                }
            } else {
                showAlert(Alert.AlertType.ERROR, "Error", "Error: A user already exists with this email.");
            }
        } else {
            showAlert(Alert.AlertType.ERROR, "Error", "Error: Please enter details for all fields in the registration form.");
        }
    }

    private Optional<ButtonType> showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        return alert.showAndWait();
    }

    @FXML
    private void testManage(ActionEvent event) throws IOException {
        scene.switchScene("ManagerLandingPage");
    }

}

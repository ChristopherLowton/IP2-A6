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
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author syed
 */
public class CreateAdminController implements Initializable {

    @FXML
    private TextField registerFirstName;
    @FXML
    private TextField registerLastName;
    @FXML
    private TextField registerEmail;
    @FXML
    private PasswordField registerPassword;

    Connection conn = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    Sql sql = new Sql();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void register(ActionEvent event) {
        if (validateFields()) {
            if (this.sql.getUserByEmail(registerEmail.getText()) == null) {
                try {
                    String fName = registerFirstName.getText();
                    String lName = registerLastName.getText();
                    String email = registerEmail.getText();
                    String password = registerPassword.getText();
                    int adminRights = 1;

                    String query = "INSERT INTO `users`(`FirstName`, `LastName`, `Email`, `Password`,AdminRights) VALUES (?,?,?,?,?)";
                    pst = sql.connectDb().prepareStatement(query);
                    pst.setString(1, fName);
                    pst.setString(2, lName);
                    pst.setString(3, email);
                    pst.setString(4, password);
                    pst.setInt(5, adminRights);
                    if (pst.executeUpdate() != 0) {
                    }
                    showAlert(Alert.AlertType.INFORMATION, "Success", "You have successfully registered.");
                    clearFields();
                } catch (SQLException ex) {
                    Logger.getLogger(CreateAdminController.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                showAlert(Alert.AlertType.INFORMATION, "Error", "A user already exists with this email.");
            }
        }
    }

    private void clearFields() {
        registerFirstName.clear();
        registerLastName.clear();
        registerEmail.clear();
        registerPassword.clear();
    }

    private Optional<ButtonType> showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        return alert.showAndWait();
    }

    private boolean validateFields() {
        if (registerFirstName.getText().isEmpty() || registerLastName.getText().isEmpty() || registerEmail.getText().isEmpty() || registerPassword.getText().isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "Check Fields", "Please ensure all fields are filled in.");
            return false;
        }
        return true;
    }

    @FXML
    private void back(ActionEvent event) throws IOException {
        SceneManager scene = new SceneManager();
        scene.switchScene("ManagerLandingPage");
    }

}

package Controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Crizzil
 */
public class ChooseCategoryController implements Initializable {

    SceneManager scene = new SceneManager();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }
    
    @FXML
    public void easyQuiz() throws IOException {
        scene.switchScene("Question");
        ((QuestionController) scene.getLoader().getController()).beginQuiz(1);
    }
    
    @FXML
    public void mediumQuiz() throws IOException {
        scene.switchScene("Question");
        ((QuestionController) scene.getLoader().getController()).beginQuiz(2);
    }
    
    @FXML
    public void hardQuiz() throws IOException {
        scene.switchScene("Question");
        ((QuestionController) scene.getLoader().getController()).beginQuiz(3);
    }
    
    @FXML
    private void logout(ActionEvent event) throws SQLException, Exception {
        Optional<ButtonType> result = showAlert(Alert.AlertType.CONFIRMATION, "Confirm logout", "Are you sure you want to logout?");
        if (result.get() == ButtonType.OK) {
            this.scene.switchScene("Login");
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

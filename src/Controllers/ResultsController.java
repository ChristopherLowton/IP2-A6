/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;

/**
 *
 * @author Crizzil
 */
public class ResultsController {
    
    @FXML
    private TextArea result;
    @FXML
    private TextArea rating;
    
    SceneManager scene = new SceneManager();
    
    private int categoryId;
    
    private int score;
    private String[][] results;
    
    public void showResults(String[][] results, int score, int categoryId) {
        this.score = score;
        this.results = results;
        this.categoryId = categoryId;
        this.result.setText(String.valueOf(this.score));
        this.rating.setText(String.valueOf(score * 20) + "%");
    }
    
    @FXML
    public void restartQuiz() throws IOException {
        scene.switchScene("Question");
        ((QuestionController) scene.getLoader().getController()).beginQuiz(this.categoryId);
    }
    
    @FXML
    public void reviewAnswers() throws IOException {
        scene.switchScene("ReviewAnswers");
        ((ReviewAnswersController) scene.getLoader().getController()).showAnswers(this.results, this.categoryId);
    }
    
    @FXML
    public void topScores() throws IOException {
        scene.switchScene("TopScores");
        ((TopScoresController) scene.getLoader().getController()).showScores(this.results, this.categoryId);
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

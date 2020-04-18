/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Models.Result;
import Models.ResultSet;
import Models.Score;
import Models.ScoreboardSet;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author syed
 */
public class TopScoresController {

    @FXML
    private TableView table;
    @FXML
    private TableColumn<ResultSet, String> positionColumn;
    @FXML
    private TableColumn<ResultSet, String> userColumn;
    @FXML
    private TableColumn<ResultSet, String> scoreColumn;
    @FXML
    private TableColumn<ResultSet, String> ratingColumn;

    SceneManager scene = new SceneManager();
    
    private ArrayList<Score> scores;

    private int categoryId;
    private String[][] results;
    
    Sql sql = new Sql();
    
    private int userId;
    
    public void showScores(int userId, String[][] results, int categoryId) {
        this.userId = userId;
        this.results = results;
        this.categoryId = categoryId;
        this.scores = this.sql.getTopScores();
        ArrayList<ScoreboardSet> scoreboardSet = new ArrayList<>();
        for (int i = 0; i < this.scores.size(); i++) {
            Result result = this.sql.getResultById(this.scores.get(i).getResultId());
            scoreboardSet.add(new ScoreboardSet(String.valueOf(this.scores.get(i).getPosition()), this.sql.getUserById(result.getUserId()).getFullName(), String.valueOf(result.getScore()) , String.valueOf(result.getScore() * 20) + "%"));
        }
        this.positionColumn.setCellValueFactory(new PropertyValueFactory("position"));
        this.userColumn.setCellValueFactory(new PropertyValueFactory("user"));
        this.scoreColumn.setCellValueFactory(new PropertyValueFactory("score"));
        this.ratingColumn.setCellValueFactory(new PropertyValueFactory("rating"));
        table.getItems().addAll(scoreboardSet);
    }
    
    @FXML
    public void reviewAnswers() throws IOException {
        scene.switchScene("ReviewAnswers");
        ((ReviewAnswersController) scene.getLoader().getController()).showAnswers(this.userId, this.results, this.categoryId);
    }
    
    @FXML
    public void restartQuiz() throws IOException {
        scene.switchScene("Question");
        ((QuestionController) scene.getLoader().getController()).beginQuiz(this.userId, this.categoryId);
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

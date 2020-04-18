/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Models.ResultSet;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author Crizzil
 */
public class ReviewAnswersController {

    @FXML
    private TableView table;
    @FXML
    private TableColumn<ResultSet, String> questionColumn;
    @FXML
    private TableColumn<ResultSet, String> answerColumn;
    @FXML
    private TableColumn<ResultSet, String> correctColumn;

    SceneManager scene = new SceneManager();
    
    private String[][] results;
    private int categoryId;

    private int userId;
    
    public void showAnswers(int userId, String[][] results, int categoryId) {
        this.userId = userId;
        this.results = results;
        this.categoryId = categoryId;

        ArrayList<ResultSet> resultSet = new ArrayList<ResultSet>();
        for (int i = 0; i < this.results.length; i++) {
            resultSet.add(new ResultSet(results[i][0], results[i][1], results[i][2]));
        }
        this.questionColumn.setCellValueFactory(new PropertyValueFactory("question"));
        this.answerColumn.setCellValueFactory(new PropertyValueFactory("answer"));
        this.correctColumn.setCellValueFactory(new PropertyValueFactory("correct"));
        table.getItems().addAll(resultSet);
    }
    
    @FXML
    public void restartQuiz() throws IOException {
        scene.switchScene("Question");
        ((QuestionController) scene.getLoader().getController()).beginQuiz(this.userId, this.categoryId);
    }
    
    @FXML
    public void topScores() throws IOException {
        scene.switchScene("TopScores");
        ((TopScoresController) scene.getLoader().getController()).showScores(this.userId, this.results, this.categoryId);
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

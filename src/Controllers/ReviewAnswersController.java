/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Models.ResultSet;
import java.io.IOException;
import java.util.ArrayList;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
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

    public void showAnswers(String[][] results, int categoryId) {
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
        ((QuestionController) scene.getLoader().getController()).beginQuiz(this.categoryId);
    }
}

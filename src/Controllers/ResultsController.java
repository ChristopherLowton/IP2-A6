/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import java.io.IOException;
import javafx.fxml.FXML;
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
}

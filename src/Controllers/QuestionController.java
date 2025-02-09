package Controllers;

import Models.Answer;
import Models.Question;
import Models.Result;
import Models.Score;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
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
public class QuestionController implements Initializable {

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
    private Button cancel;

    private Sql sql = new Sql();
    private SceneManager scene = new SceneManager();

    private int categoryId;

    private int currentQuestionIndex = 0;
    private ArrayList<Question> questions;

    private int score = 0;

    private String[][] results = new String[5][3];

    private int userId;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    public void beginQuiz(int userId, int categoryId) {
        this.userId = userId;
        getQuestions(categoryId);
        Collections.shuffle(this.questions);
        displayQuestion(this.questions.get(currentQuestionIndex));
    }

    public void getQuestions(int categoryId) {
        this.categoryId = categoryId;
        this.questions = sql.getQuestions(categoryId);
        for (int i = 0; i < this.questions.size(); i++) {
            this.questions.get(i).setAnswers(sql.getAnswers(this.questions.get(i).getId()));
        }
    }

    @FXML
    public void previous() {
        Question q = null;
        if (this.currentQuestionIndex > 0) {
            q = this.questions.get(this.currentQuestionIndex - 1);
        }
        if (q != null) {
            this.currentQuestionIndex--;
            displayQuestion(q);
        }
    }

    @FXML
    public void next() throws IOException {
        int selectedCount = 0;
        if (this.answerA.selectedProperty().get()) {
            selectedCount++;
        }
        if (this.answerB.selectedProperty().get()) {
            selectedCount++;
        }
        if (this.answerC.selectedProperty().get()) {
            selectedCount++;
        }
        if (this.answerD.selectedProperty().get()) {
            selectedCount++;
        }

        if (selectedCount == 1) {
            checkAnswer();

            Question q = null;
            if (this.currentQuestionIndex < (this.questions.size() - 1)) {
                q = this.questions.get(this.currentQuestionIndex + 1);
            }
            if (q != null) {
                this.currentQuestionIndex++;
                displayQuestion(q);
            } else {
                saveResult();
                scene.switchScene("Results");
                ((ResultsController) scene.getLoader().getController()).showResults(this.userId, this.results, this.score, this.categoryId);
            }
        } else if (selectedCount > 1) {
            showAlert(AlertType.ERROR, "Too many answers", "You can only select one answer.");
        } else if (selectedCount < 1) {
            showAlert(AlertType.ERROR, "No answer selected", "Please select an answer.");
        }
    }

    @FXML
    private void displayQuestion(Question q) {
        this.question.setText(q.getText());
        displayAnswers(q);
    }

    @FXML
    private void displayAnswers(Question q) {
        ArrayList<Answer> answers = q.getAnswers();
        if (answers.size() == 4) {
            this.answerA.setText(answers.get(0).getText());
            this.answerB.setText(answers.get(1).getText());
            this.answerC.setText(answers.get(2).getText());
            this.answerD.setText(answers.get(3).getText());
            if (this.results[currentQuestionIndex] != null) {
                if (this.results[currentQuestionIndex][1] != null && this.results[currentQuestionIndex][1].isEmpty() == false) {
                    if (this.results[currentQuestionIndex][1].equals("A")) {
                        this.answerA.selectedProperty().set(true);
                    } else {
                        this.answerA.selectedProperty().set(false);
                    }
                    if (this.results[currentQuestionIndex][1].equals("B")) {
                        this.answerB.selectedProperty().set(true);
                    } else {
                        this.answerB.selectedProperty().set(false);
                    }
                    if (this.results[currentQuestionIndex][1].equals("C")) {
                        this.answerC.selectedProperty().set(true);
                    } else {
                        this.answerC.selectedProperty().set(false);
                    }
                    if (this.results[currentQuestionIndex][1].equals("D")) {
                        this.answerD.selectedProperty().set(true);
                    } else {
                        this.answerD.selectedProperty().set(false);
                    }
                }
            }
        } else {
            System.out.println("Error displaying answers");
        }
    }

    @FXML
    private void checkAnswer() {
        ArrayList<Answer> answers = this.questions.get(currentQuestionIndex).getAnswers();
        this.results[currentQuestionIndex][0] = this.question.getText();
        if (this.answerA.selectedProperty().get()) {
            this.results[currentQuestionIndex][1] = "A";
            if (answers.get(0).isCorrect()) {
                score++;
                this.results[currentQuestionIndex][2] = "Correct";
            } else {
                this.results[currentQuestionIndex][2] = "Incorrect";
            }
        }
        if (this.answerB.selectedProperty().get()) {
            this.results[currentQuestionIndex][1] = "B";
            if (answers.get(0).isCorrect()) {
                score++;
                this.results[currentQuestionIndex][2] = "Correct";
            } else {
                this.results[currentQuestionIndex][2] = "Incorrect";
            }
        }
        if (this.answerC.selectedProperty().get()) {
            this.results[currentQuestionIndex][1] = "C";
            if (answers.get(0).isCorrect()) {
                score++;
                this.results[currentQuestionIndex][2] = "Correct";
            } else {
                this.results[currentQuestionIndex][2] = "Incorrect";
            }
        }
        if (this.answerD.selectedProperty().get()) {
            this.results[currentQuestionIndex][1] = "D";
            if (answers.get(0).isCorrect()) {
                score++;
                this.results[currentQuestionIndex][2] = "Correct";
            } else {
                this.results[currentQuestionIndex][2] = "Incorrect";
            }
        }
        this.answerA.selectedProperty().set(false);
        this.answerB.selectedProperty().set(false);
        this.answerC.selectedProperty().set(false);
        this.answerD.selectedProperty().set(false);
    }

    private void saveResult() {
        Result currentResult = this.sql.getResultByUserAndCat(this.categoryId, this.userId);
        if (currentResult != null) {
            if (this.score > currentResult.getScore()) {
                this.sql.removeResult(currentResult.getResultId());
                this.sql.addResult(this.categoryId, this.userId, this.score);
                Result newResult = this.sql.getResultByUserAndCat(this.categoryId, this.userId);
                handleTopScores(newResult.getResultId());
            }
        }
    }

    private void handleTopScores(int resultId) {
        ArrayList<Result> results = new ArrayList<>();
        ArrayList<Score> scores = this.sql.getTopScores();
        Collections.sort(scores);
        for (int i = 0; i < scores.size(); i++) {
            System.out.println(scores.get(i).getPosition());
            results.add(this.sql.getResultById(scores.get(i).getResultId()));
        }
        for (int i = 0; i < results.size(); i++) {
            if (this.score > results.get(i).getScore()) {
                //Replace score
                this.sql.removeScore(results.get(i).getResultId());
                this.sql.addScore(resultId, this.categoryId, this.score);
                break;
            }
        }
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

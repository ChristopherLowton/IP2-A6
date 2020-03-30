package Controllers;

import Models.Answer;
import Models.Question;
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    public void beginQuiz(int categoryId) {
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
                displayQuestion(q);
                this.currentQuestionIndex++;
            } else {
                scene.switchScene("Results");
                ((ResultsController) scene.getLoader().getController()).showResults(this.results, this.score, this.categoryId);
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

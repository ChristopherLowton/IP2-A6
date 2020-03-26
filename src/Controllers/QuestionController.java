package Controllers;

import Models.Question;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
    
    private int currentQuestionIndex = -1;
    private ArrayList<Question> questions;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
    
    public void beginQuiz(int categoryId) {
        getQuestions(categoryId);
        next();
    }
    
    public void getQuestions(int categoryId) {
        questions = sql.getQuestions(categoryId);
    }
    
    @FXML
    public void next() {
        Question q = null;
        if (this.currentQuestionIndex < 0) {
            if (this.questions == null && this.questions.isEmpty() == false) {
                System.out.println("No Questions");
            }
            q = this.questions.get(0);
        } else {
            if (this.currentQuestionIndex < (this.questions.size() - 1)) {
                q = this.questions.get(this.currentQuestionIndex + 1);
            }
        }
        if (q != null) {
            displayQuestion(q);
            this.currentQuestionIndex++;
        } else {
            System.out.println("End of quiz");
        }
    }

    @FXML
    private void displayQuestion(Question q) {
        this.question.setText(q.getText());
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

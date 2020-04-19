/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import static Controllers.Sql.conn;
import Models.Answer;
import Models.CategorySet;
import Models.Question;
import java.io.IOException;
import java.lang.reflect.Field;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author syed
 */
public class EditNewCatController implements Initializable {

    @FXML
    private ChoiceBox catChoice;
    @FXML
    private TextArea questionText;
    @FXML
    private TextField ansAText;
    @FXML
    private TextField ansBText;
    @FXML
    private TextField ansCText;
    @FXML
    private TextField ansDText;
    @FXML
    private TextField cAnswer;

    private int currentQuestionIndex = 0;
    private ArrayList<Question> questions;
    int answerId;

    Connection conn = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    Sql sql = new Sql();
    ObservableList catList;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        populateCat();
    }

    @FXML
    private void saveQuestionAnswer(ActionEvent event) throws IOException {
        if (validateFields()) {
            saveQuestion();
            saveAnswers();
            showAlert(Alert.AlertType.INFORMATION, "Success", "You have successfully saved question and answers.");
            clearFields();
        }

    }

    @FXML
    private void updateQuestionAnswer(ActionEvent event) {
        if (questionText.getText().isEmpty() | cAnswer.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Update Error", "No question or correct answer selected");
        } else {
            updateQuestion();
            updateCorrectAns();
        }
    }

    @FXML
    private void populateQuestionAns(ActionEvent event) {

        if (catChoice.getSelectionModel().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "No Selection", "Please Select a Category to load data");
        } else {
            String tempCat = catChoice.getValue().toString();
            int categoryId = fetchCatInfo(tempCat);
            getQuestions(categoryId);
            Collections.shuffle(this.questions);

            if (!this.questions.isEmpty()) {
                displayQuestion(this.questions.get(currentQuestionIndex));
            } else {
                showAlert(Alert.AlertType.WARNING, "NO data", "No questions available, Please add questions");
            }
        }

    }

    private void populateCat() {
        try {
            catList = FXCollections.observableArrayList();

            String query = "SELECT * FROM categories";
            conn = sql.connectDb();
            pst = conn.prepareStatement(query);
            rs = pst.executeQuery();

            while (rs.next()) {
                String categories = rs.getString("Title");
                catList.add(categories);
            }
            catChoice.setItems(catList);
        } catch (SQLException ex) {
            Logger.getLogger(EditNewCatController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void saveQuestion() {

        try {
            String tempCat = catChoice.getValue().toString();
            int catId = fetchCatInfo(tempCat);
            String text = questionText.getText();
            String query = "INSERT INTO `questions`(`CategoryID`, `Text`) VALUES (?,?)";
            pst = sql.connectDb().prepareStatement(query);
            pst.setInt(1, catId);
            pst.setString(2, text);

            if (pst.executeUpdate() != 0) {
            }

        } catch (SQLException ex) {
            Logger.getLogger(EditNewCatController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void saveAnswers() {
        try {
            String tempQ = questionText.getText();
            int qId = fetchQInfo(tempQ);

            String cAnsText = ansAText.getText();
            String wAnsBText = ansBText.getText();
            String wAnsCText = ansCText.getText();
            String wAnsDText = ansDText.getText();

            int correctInt;

            String query = "INSERT INTO `answers`(`QuestionID`, `Text`, `Correct`) VALUES (?,?,?)";
            pst = sql.connectDb().prepareStatement(query);
            pst.setInt(1, qId);
            pst.setString(2, cAnsText);
            if (ansAText.getText().equals(cAnswer.getText())) {
                correctInt = 1;
                pst.setInt(3, correctInt);
            } else {
                correctInt = 0;
                pst.setInt(3, correctInt);
            }
            if (pst.executeUpdate() != 0) {
            }

            String query1 = "INSERT INTO `answers`(`QuestionID`, `Text`, `Correct`) VALUES (?,?,?)";
            pst = sql.connectDb().prepareStatement(query1);
            pst.setInt(1, qId);
            pst.setString(2, wAnsBText);
            if (ansBText.getText().equals(cAnswer.getText())) {
                correctInt = 1;
                pst.setInt(3, correctInt);
            } else {
                correctInt = 0;
                pst.setInt(3, correctInt);
            }
            if (pst.executeUpdate() != 0) {
            }

            String query2 = "INSERT INTO `answers`(`QuestionID`, `Text`, `Correct`) VALUES (?,?,?)";
            pst = sql.connectDb().prepareStatement(query2);
            pst.setInt(1, qId);
            pst.setString(2, wAnsCText);
            if (ansCText.getText().equals(cAnswer.getText())) {
                correctInt = 1;
                pst.setInt(3, correctInt);
            } else {
                correctInt = 0;
                pst.setInt(3, correctInt);
            }
            if (pst.executeUpdate() != 0) {
            }

            String query3 = "INSERT INTO `answers`(`QuestionID`, `Text`, `Correct`) VALUES (?,?,?)";
            pst = sql.connectDb().prepareStatement(query3);
            pst.setInt(1, qId);
            pst.setString(2, wAnsDText);
            if (ansDText.getText().equals(cAnswer.getText())) {
                correctInt = 1;
                pst.setInt(3, correctInt);
            } else {
                correctInt = 0;
                pst.setInt(3, correctInt);
            }
            if (pst.executeUpdate() != 0) {
            }

        } catch (SQLException ex) {
            Logger.getLogger(EditNewCatController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void updateQuestion() {
        try {
            String text = questionText.getText();
            int qId = this.questions.get(currentQuestionIndex).getId();

            String query = "UPDATE `questions` SET `Text` =? WHERE `questions`.`QuestionID` =?";
            pst = sql.connectDb().prepareStatement(query);
            pst.setString(1, text);
            pst.setInt(2, qId);
            if (pst.executeUpdate() != 0) {

            }
        } catch (SQLException ex) {
            Logger.getLogger(EditNewCatController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void updateCorrectAns() {
        try {
            String text = cAnswer.getText();
            int ansId = fetchAnsId();

            String query = "UPDATE `answers` SET `Text`=? WHERE `answers`.`AnswerID` =?";
            pst = sql.connectDb().prepareStatement(query);
            pst.setString(1, text);
            pst.setInt(2, ansId);
            if (pst.executeUpdate() != 0) {
            }
        } catch (SQLException ex) {
            Logger.getLogger(EditNewCatController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    public void delQuestion() {
        if (questionText.getText().isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "Delete Error", "No Question Selected");
        } else {
            try {
                int qId = this.questions.get(currentQuestionIndex).getId();

                String query = "DELETE FROM `questions` WHERE QuestionID=?";
                pst = sql.connectDb().prepareStatement(query);
                pst.setInt(1, qId);
                if (pst.executeUpdate() != 0) {
                }
                this.questions.remove(currentQuestionIndex);
                next();

            } catch (SQLException ex) {
                Logger.getLogger(EditNewCatController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    private int fetchAnsId() {

        if (this.questions.get(currentQuestionIndex).getAnswers().get(0).isCorrect() == true) {
            answerId = this.questions.get(currentQuestionIndex).getAnswers().get(0).getId();
        }
        if (this.questions.get(currentQuestionIndex).getAnswers().get(1).isCorrect() == true) {
            answerId = this.questions.get(currentQuestionIndex).getAnswers().get(1).getId();
        }
        if (this.questions.get(currentQuestionIndex).getAnswers().get(2).isCorrect() == true) {
            answerId = this.questions.get(currentQuestionIndex).getAnswers().get(2).getId();
        }
        if (this.questions.get(currentQuestionIndex).getAnswers().get(3).isCorrect() == true) {
            answerId = this.questions.get(currentQuestionIndex).getAnswers().get(3).getId();
        }
        return answerId;
    }

    private int fetchCatInfo(String tempCat) {
        List tempCategoryId = sql.seacrhCatId(tempCat);

        int categoryId = (int) tempCategoryId.get(0);

        return categoryId;
    }

    private int fetchQInfo(String tempQText) {
        List tempQId = sql.searchQId(tempQText);
        int questionId = (int) tempQId.get(0);

        return questionId;
    }

    private boolean validateFields() {
        if (catChoice.getSelectionModel().isEmpty() || questionText.getText().isEmpty() || ansAText.getText().isEmpty() || ansBText.getText().isEmpty() || ansCText.getText().isEmpty() || ansDText.getText().isEmpty() || cAnswer.getText().isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "Check Fields", "Please ensure all fields are filled in.");
            return false;
        }
        return true;
    }

    private Optional<ButtonType> showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        return alert.showAndWait();

    }

    public void getQuestions(int categoryId) {
        this.questions = sql.getQuestions(categoryId);
        for (int i = 0; i < this.questions.size(); i++) {
            this.questions.get(i).setAnswers(sql.getAnswers(this.questions.get(i).getId()));
        }
    }

    private void displayQuestion(Question q) {

        {
            this.questionText.setText(q.getText());
            displayAnswers(q);
        }

    }

    private void displayAnswers(Question q) {
        ArrayList<Answer> answers = q.getAnswers();
        if (answers.size() == 4) {
            this.ansAText.setText(answers.get(0).getText());
            this.ansBText.setText(answers.get(1).getText());
            this.ansCText.setText(answers.get(2).getText());
            this.ansDText.setText(answers.get(3).getText());
        } else {

        }

        if (answers.get(0).isCorrect() == true) {
            this.cAnswer.setText(answers.get(0).getText());
        } else {
        }
        if (answers.get(1).isCorrect() == true) {
            this.cAnswer.setText(answers.get(1).getText());
        } else {
        }
        if (answers.get(2).isCorrect() == true) {
            this.cAnswer.setText(answers.get(2).getText());
        } else {
        }
        if (answers.get(3).isCorrect() == true) {
            this.cAnswer.setText(answers.get(3).getText());
        } else {
        }

    }

    @FXML
    private void next() {
        Question q = null;
        if (this.currentQuestionIndex < (this.questions.size() - 1)) {
            q = this.questions.get(this.currentQuestionIndex + 1);
        }
        if (q != null) {
            displayQuestion(q);
            this.currentQuestionIndex++;
        } else {

        }

    }

    @FXML
    private void catPage(ActionEvent event) throws IOException {
        SceneManager scene = new SceneManager();
        scene.switchScene("ManagerLandingPage");
    }

    public void clearFields() {
        this.questionText.clear();
        this.ansAText.clear();
        this.ansBText.clear();
        this.ansCText.clear();
        this.ansDText.clear();
        this.cAnswer.clear();
    }

    @FXML
    private void logout(ActionEvent event) throws SQLException, Exception {
        SceneManager scene = new SceneManager();
        Optional<ButtonType> result = showAlert(Alert.AlertType.CONFIRMATION, "Confirm logout", "Are you sure you want to logout?");
        if (result.get() == ButtonType.OK) {
            scene.switchScene("Login");
        }
    }
}

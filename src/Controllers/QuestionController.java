package Controllers;

import Models.Question;
import Models.User;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
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
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.annotation.Resources;

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

    private int currentQuestionIndex = -1;
    private ArrayList<Question> questions;

    Connection conn = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        easyCat();
    }

    @FXML
    public void easyCat() {
        try {
            conn = SqlConnect.ConnectDB();
            String Sql = "Select * from easyCat_DB";
            pst = conn.prepareStatement(Sql);
            rs = pst.executeQuery();

            questions = new ArrayList<Question>();
            while (rs.next()) {
                Question q = new Question(rs.getInt(1),
                        rs.getString("question_desc"),
                        rs.getString("answer_A"),
                        rs.getString("answer_B"),
                        rs.getString("answer_C"),
                        rs.getString("answer_D"),
                        rs.getString("correct_answer"));
                questions.add(q);
            }
            next();
        } catch (SQLException ex) {
            Logger.getLogger(QuestionController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    public void next() {
        Question q = null;
        if (this.currentQuestionIndex < 0) {
            if (this.questions == null) {
                this.easyCat();
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
        this.question.setText(q.getDescription());
        this.answerA.setText(q.getAnswerA());
        this.answerB.setText(q.getAnswerB());
        this.answerC.setText(q.getAnswerC());
        this.answerD.setText(q.getAnswerD());
    }

    @FXML
    private void logout(ActionEvent event) throws SQLException, Exception {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm?");
        alert.setHeaderText(null);
        alert.setContentText("Are you ok with this?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            // ... user chose OK
            // (new FXMLLoader(getClass().getResource("/Views/Login.fxml"))).load();

            Parent loginParent = FXMLLoader.load(getClass().getResource("/Views/Login.fxml"));
            Scene loginScene = new Scene(loginParent);

            Stage loginPage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            loginPage.setScene(loginScene);
            loginPage.show();
        } else {
            // ... user chose CANCEL or closed the dialog
        }

    }
}

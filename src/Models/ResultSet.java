/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Crizzil
 */
public class ResultSet {
    private SimpleStringProperty question;
    private SimpleStringProperty answer;
    private SimpleStringProperty correct;

    public ResultSet(String question, String answer, String correct) {
        this.question = new SimpleStringProperty(question);
        this.answer = new SimpleStringProperty(answer);
        this.correct = new SimpleStringProperty(correct);
    }

    public String getQuestion() {
        return this.question.get();
    }

    public String getAnswer() {
        return this.answer.get();
    }

    public String getCorrect() {
        return this.correct.get();
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

/**
 *
 * @author Crizzil
 */
public class Answer {
    private int id;
    private int questionId;
    private String text;
    private boolean correct;
    
    public Answer() {
        //Default constructor
    }

    public Answer(int id, int questionId, String text, boolean correct) {
        this.id = id;
        this.questionId = questionId;
        this.text = text;
        this.correct = correct;
    }

    public int getId() {
        return this.id;
    }
    
    public int getCategoryId() {
        return this.questionId;
    }
    
    public String getText() {
        return this.text;
    }
    
    public boolean isCorrect() {
        return this.correct;
    }
}

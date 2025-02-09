/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.util.ArrayList;

/**
 *
 * @author Chris
 */
public class Question {

    private int id;
    private int categoryId;
    private String text;
    
    private ArrayList<Answer> answers;

    public Question() {
        //Default constructor
    }

    public Question(int id, int categoryId, String text) {
        this.id = id;
        this.categoryId = categoryId;
        this.text = text;
    }

    public int getId() {
        return this.id;
    }
    
    public int getCategoryId() {
        return this.categoryId;
    }
    
    public String getText() {
        return this.text;
    }
    
    public ArrayList<Answer> getAnswers() {
        return this.answers;
    }
    
    public void setAnswers(ArrayList<Answer> answers) {
        this.answers = answers;
    }
}

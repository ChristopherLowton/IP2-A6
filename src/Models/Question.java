/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

/**
 *
 * @author Chris
 */
public class Question {

    private int id;
    private int categoryId;
    private String text;

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
}

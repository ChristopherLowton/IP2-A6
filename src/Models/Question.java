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
    private String description;
    private String answer_A;
    private String answer_B;
    private String answer_C;
    private String answer_D;
    private String correct_answer;

    public Question() {
        //Default constructor
    }

    public Question(int id, String description, String answer_A, String answer_B, String answer_C, String answer_D, String correct_answer) {
        this.id = id;
        this.description = description;
        this.answer_A = answer_A;
        this.answer_B = answer_B;
        this.answer_C = answer_C;
        this.answer_D = answer_D;
        this.correct_answer = correct_answer;
    }

    public int getId() {
        return this.id;
    }

    public String getDescription() {
        return this.description;
    }

    public String getAnswerA() {
        return this.answer_A;
    }
    
    public String getAnswerB() {
        return this.answer_B;
    }
    
    public String getAnswerC() {
        return this.answer_C;
    }
    
    public String getAnswerD() {
        return this.answer_D;
    }
    
    public String getCorrectAnswer() {
        return this.correct_answer;
    }
}

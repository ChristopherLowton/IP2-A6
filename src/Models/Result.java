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
public class Result {
    private int resultId;
    private int categoryId;
    private int userId;
    private int score;
    
    public Result(int resultId, int categoryId, int userId, int score) {
        this.resultId = resultId;
        this.categoryId = categoryId;
        this.userId = userId;
        this.score = score;
    }
    
    public int getResultId() {
        return this.resultId;
    }
    
    public int getCategoryId() {
        return this.categoryId;
    }
    
    public int getUserId() {
        return this.userId;
    }
    
    public int getScore() {
        return this.score;
    }
}

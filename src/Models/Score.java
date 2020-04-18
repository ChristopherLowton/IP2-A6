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
public class Score implements Comparable {
    private int resultId;
    private int categoryId;
    private int position;
    
    public Score() {
        //Default constructor
    }

    public Score(int resultId, int categoryId, int position) {
        this.resultId = resultId;
        this.categoryId = categoryId;
        this.position = position;
    }

    public int getResultId() {
        return this.resultId;
    }
    
    public int getCategoryId() {
        return this.categoryId;
    }
    
    public int getPosition() {
        return this.position;
    }

    @Override
    public int compareTo(Object s) {
        int comparePos=((Score) s).getPosition();
        /* Ascending */
        return this.position-comparePos;

        /* Descending */
        //return comparePos-this.position;
    }
}

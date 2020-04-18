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
public class ScoreboardSet {
    private SimpleStringProperty position;
    private SimpleStringProperty user;
    private SimpleStringProperty score;
    private SimpleStringProperty rating;

    public ScoreboardSet(String position, String user, String score, String rating) {
        this.position = new SimpleStringProperty(position);
        this.user = new SimpleStringProperty(user);
        this.score = new SimpleStringProperty(score);
        this.rating = new SimpleStringProperty(rating);
    }

    public String getPosition() {
        return this.position.get();
    }

    public String getUser() {
        return this.user.get();
    }

    public String getScore() {
        return this.score.get();
    }
    
    public String getRating() {
        return this.rating.get();
    }
}

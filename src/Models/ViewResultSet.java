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
public class ViewResultSet {
    private SimpleStringProperty category;
    private SimpleStringProperty rating;

    public ViewResultSet(String category, String rating) {
        this.category = new SimpleStringProperty(category);
        this.rating = new SimpleStringProperty(rating);
    }

    public String getCategory() {
        return this.category.get();
    }

    public String getRating() {
        return this.rating.get();
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author syed
 */
public class Category {

    private StringProperty title = new SimpleStringProperty();
    private IntegerProperty catId = new SimpleIntegerProperty();

    public Category() {
        // default constructor
    }
    
    public Category(String title, int catId) {
        this.title = new SimpleStringProperty(title);
        this.catId = new SimpleIntegerProperty(catId);
    }

    
    
    public String getTitle() {
        return title.get();
    }

    public void setTitle(String value) {
        title.set(value);
    }

    public StringProperty titleProperty() {
        return title;
    }
    
    public int getCatId() {
        return catId.get();
    }
    
    public void setCatId(int value) {
        catId.set(value);
    }
    
    public IntegerProperty catIdProperty() {
        return catId;
    }
    
    @Override
    public String toString() {
        String output = Integer.toString(this.getCatId()) +" "+ this.getTitle();
        System.out.println("Categories:  " +output);
        return output;
    }  
    
    
}

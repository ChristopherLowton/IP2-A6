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
public class Category {
    private int categoryId;
    private String title;
    
    public Category(int categoryId, String title) {
        this.categoryId = categoryId;
        this.title = title;
    }
    
    public int getCategoryId() {
        return this.categoryId;
    }
    
    public String getTitle() {
        return this.title;
    }
}

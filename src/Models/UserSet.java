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
public class UserSet {
    private SimpleStringProperty id;
    private SimpleStringProperty name;

    public UserSet(String id, String name) {
        this.id = new SimpleStringProperty(id);
        this.name = new SimpleStringProperty(name);
    }

    public String getId() {
        return this.id.get();
    }

    public String getName() {
        return this.name.get();
    }
}

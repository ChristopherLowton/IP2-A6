/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

/**
 *
 * @author Crizzil
 */
public class SceneManager {
    private static Scene scene;
    private static FXMLLoader loader;
    
    public void setScene(Scene scene) {
        SceneManager.scene = scene;
    }
    
    public FXMLLoader getLoader() {
        return loader;
    }
    
    public void switchScene(String view) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/" + view + ".fxml"));
        SceneManager.loader = loader;
        SceneManager.scene.setRoot(loader.load());
    }
}

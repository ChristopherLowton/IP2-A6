package Controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Crizzil
 */
public class ChooseCategoryController implements Initializable {

    SceneManager scene = new SceneManager();
    @FXML
    private ChoiceBox<String> catChoice;
    
    
    
    
   Connection conn = null;
   PreparedStatement pst= null;
   ResultSet rs= null; 
   Sql sql = new Sql();
   ObservableList catList;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        populateCat();
    }
    
   
   
    
    @FXML
    private void startQuiz() throws IOException {
        String tempCat = catChoice.getValue().toString();
        int catId =fetchCatInfo(tempCat);
        
        scene.switchScene("Question");
        ((QuestionController) scene.getLoader().getController()).beginQuiz(catId);
    }
    
    
    
    
   
   private void populateCat() {
        try {
           catList = FXCollections.observableArrayList();
          
            String query = "SELECT * FROM categories";
            conn = sql.connectDb();
            pst = conn.prepareStatement(query);
            rs = pst.executeQuery();
            
            while(rs.next()){
                String categories = rs.getString("Title");
                catList.add(categories);
            }
            catChoice.setItems(catList);
              } catch (SQLException ex) {
            Logger.getLogger(EditNewCatController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
    
    private int fetchCatInfo(String tempCat) {
        List tempCategoryId = sql.seacrhCatId(tempCat);
        
        int categoryId = (int) tempCategoryId.get(0);
        
        return categoryId;       
    }
    
    
    @FXML
    private void logout(ActionEvent event) throws SQLException, Exception {
        Optional<ButtonType> result = showAlert(Alert.AlertType.CONFIRMATION, "Confirm logout", "Are you sure you want to logout?");
        if (result.get() == ButtonType.OK) {
            this.scene.switchScene("Login");
        }
    }
    
    private Optional<ButtonType> showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        return alert.showAndWait();
    }
}

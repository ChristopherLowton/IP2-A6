/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Models.CategorySet;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author syed
 */
public class ManagerLandingPageController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private Button addButton;
    @FXML
    private Button editButton;
    @FXML
    private TableView<CategorySet> catTable;
    @FXML
    private TableColumn<CategorySet, Integer> idCol;
    @FXML
    private TableColumn<CategorySet, String> titleCol;
    @FXML
    private TextField idText;
    @FXML
    private TextField titleText;

    SceneManager scene = new SceneManager();

    private Sql sql = new Sql();
    
    ObservableList<CategorySet> catList;
    Connection conn = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        populateCatTable();
    }

    private void populateCatTable() {
        try {
            catList = FXCollections.observableArrayList();

            String query = "Select * from categories";
            conn = Sql.connectDb();
            pst = conn.prepareStatement(query);
            rs = pst.executeQuery();

            while (rs.next()) {
                CategorySet category = new CategorySet();
                category.setCatId(rs.getInt("CategoryID"));
                category.setTitle(rs.getString("Title"));

                catList.add(category);
            }

            idCol.setCellValueFactory(new PropertyValueFactory<>("catId"));
            titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));

            catTable.setItems(catList);

        } catch (SQLException ex) {
            Logger.getLogger(ManagerLandingPageController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void addCategory() {
        try {
            if (titleText.getText().isEmpty()) {
                showAlert(Alert.AlertType.WARNING, "Check Field", "Please Enter into the field title");
            } else {
                String title = titleText.getText();
                String query = "INSERT INTO `categories`(Title) VALUES (?)";
                pst = Sql.connectDb().prepareStatement(query);
                pst.setString(1, title);
                if (pst.executeUpdate() != 0) {
                }
                populateCatTable();
                clearFields();
            }
        } catch (SQLException ex) {
            Logger.getLogger(ManagerLandingPageController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void editCategory() {
        try {
            if (titleText.getText().isEmpty() | idText.getText().isEmpty()) {
                showAlert(Alert.AlertType.WARNING, "Check Fields", "Please Enter into the fields to update");
            } else {
                String tempId = idText.getText();
                int id = Integer.parseInt(tempId);
                String title = titleText.getText();

                String query = "UPDATE `categories` SET `Title` =? WHERE `categories`.`CategoryID` = ?;";
                pst = Sql.connectDb().prepareStatement(query);
                pst.setString(1, title);
                pst.setInt(2, id);
                if (pst.executeUpdate() != 0) {
                }
                populateCatTable();
                clearFields();
            }
        } catch (SQLException ex) {
            Logger.getLogger(ManagerLandingPageController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void delCategory() {
        try {
            if (titleText.getText().isEmpty() || idText.getText().isEmpty()) {
                showAlert(Alert.AlertType.WARNING, "Check Fields", "Please Enter into the fields to delete");
            } else {
                if (this.sql.getCategory(Integer.valueOf(idText.getText()), titleText.getText()) != null) {
                    int id = Integer.parseInt(idText.getText());

                    String query = "DELETE FROM `categories` WHERE CategoryID=?";
                    pst = Sql.connectDb().prepareStatement(query);
                    pst.setInt(1, id);
                    if (pst.executeUpdate() != 0) {
                        
                    }
                    populateCatTable();
                    clearFields();
                } else {
                    showAlert(Alert.AlertType.WARNING, "Check Fields", "This category does not exist.");
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(ManagerLandingPageController.class.getName()).log(Level.SEVERE, null, ex);
            showAlert(Alert.AlertType.WARNING, "Error", "Cannot delete a category that still has questions in.");
        }
    }

    @FXML
    private void questionPage(ActionEvent event) throws IOException {
        SceneManager scene = new SceneManager();
        scene.switchScene("editNewCat");
    }

    @FXML
    private void adminAccountPage(ActionEvent event) throws IOException {
        SceneManager scene = new SceneManager();
        scene.switchScene("CreateAdmin");
    }

    private Optional<ButtonType> showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        return alert.showAndWait();

    }

    private void clearFields() {
        idText.clear();
        titleText.clear();
    }

    @FXML
    private void logout(ActionEvent event) throws SQLException, Exception {
        SceneManager scene = new SceneManager();
        Optional<ButtonType> result = showAlert(Alert.AlertType.CONFIRMATION, "Confirm logout", "Are you sure you want to logout?");
        if (result.get() == ButtonType.OK) {
            scene.switchScene("Login");
        }
    }

    @FXML
    public void viewResults() throws IOException {
        scene.switchScene("ViewResults");
        //((QuestionController) scene.getLoader().getController()).beginQuiz(this.categoryId);
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Models.Result;
import Models.ResultSet;
import Models.ScoreboardSet;
import Models.User;
import Models.UserSet;
import Models.ViewResultSet;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author Crizzil
 */
public class ViewResultsController implements Initializable {
    
    @FXML
    private TableView usersTable;
    @FXML
    private TableColumn<ResultSet, String> idColumn;
    @FXML
    private TableColumn<ResultSet, String> userColumn;
    
    @FXML
    private TableView resultsTable;
    @FXML
    private TableColumn<ResultSet, String> categoryColumn;
    @FXML
    private TableColumn<ResultSet, String> ratingColumn;
    
    SceneManager scene = new SceneManager();
    
    Sql sql = new Sql();
    
    ArrayList<User> users;
    ArrayList<Result> results;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.showUsers();
    }
    
    public void showUsers() {
        this.users = this.sql.getUsers();
        ArrayList<UserSet> userSet = new ArrayList<>();
        for (int i = 0; i < this.users.size(); i++) {
            userSet.add(new UserSet(String.valueOf(this.users.get(i).getId()), this.users.get(i).getFullName()));
        }
        this.idColumn.setCellValueFactory(new PropertyValueFactory("id"));
        this.userColumn.setCellValueFactory(new PropertyValueFactory("name"));
        this.usersTable.getItems().addAll(userSet);
    }
    
    public void showResults() {
        this.resultsTable.getItems().clear();
        this.results = this.sql.getResultsByUserId(Integer.valueOf(((UserSet) this.usersTable.getSelectionModel().getSelectedItem()).getId()));
        ArrayList<ViewResultSet> viewResultSet = new ArrayList<>();
        for (int i = 0; i < this.results.size(); i++) {
            viewResultSet.add(new ViewResultSet(this.sql.getCategoryById(this.results.get(i).getCategoryId()).getTitle(), String.valueOf(this.results.get(i).getScore() * 20)));
        }
        this.categoryColumn.setCellValueFactory(new PropertyValueFactory("category"));
        this.ratingColumn.setCellValueFactory(new PropertyValueFactory("rating"));
        resultsTable.getItems().addAll(viewResultSet);
    }
    
    @FXML
    private void testManage(ActionEvent event) throws IOException {
        scene.switchScene("ManagerLandingPage");
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

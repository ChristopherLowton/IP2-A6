/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;
import Models.Question;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author syed
 */
public class Sql {
    public static Connection conn = connectDb();
    
    public static Connection connectDb() {
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ip2","root","");
            return conn;
        } catch(Exception e) {
           return null;
        }
    }
    
    public ArrayList<Question> getQuestions(int categoryId) {
        try {
            PreparedStatement pst = conn.prepareStatement("SELECT * FROM questions WHERE CategoryId=?");
            pst.setInt(1, categoryId);
            ResultSet rs = pst.executeQuery();
            
            ArrayList<Question> questions = new ArrayList<Question>();
            while (rs.next()) {
                Question q = new Question(rs.getInt(1),
                        rs.getInt(2),
                        rs.getString("Text"));
                questions.add(q);
            }
            return questions;
        } catch (SQLException ex) {
            Logger.getLogger(QuestionController.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public boolean validateLogin(String email, String password) {
        try {
            PreparedStatement pst = conn.prepareStatement("Select * from users where email=? and password=?");
            pst.setString(1, email);
            pst.setString(2, password);
            ResultSet rs = pst.executeQuery();
            
            if (rs.next()) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(QuestionController.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public boolean registerUser(String firstName, String lastName, String email, String password) {
        try {
            PreparedStatement pst = conn.prepareStatement("INSERT INTO `users`(`FirstName`, `LastName`, `Email`, `Password`) VALUES (?,?,?,?)");
            pst.setString(1, firstName);
            pst.setString(2, lastName);
            pst.setString(3, email);
            pst.setString(4, password);
            
            if (pst.executeUpdate() != 0) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(QuestionController.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
}

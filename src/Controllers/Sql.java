/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;
import Models.Answer;
import Models.Category;
import Models.Question;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
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
    
    public ArrayList<Answer> getAnswers(int questionId) {
        try {
            PreparedStatement pst = conn.prepareStatement("SELECT * FROM answers WHERE QuestionId=?");
            pst.setInt(1, questionId);
            ResultSet rs = pst.executeQuery();
            
            ArrayList<Answer> answers = new ArrayList<Answer>();
            while (rs.next()) {
                Answer q = new Answer(rs.getInt(1),
                        rs.getInt(2),
                        rs.getString("Text"),
                        rs.getBoolean("Correct"));
                answers.add(q);
            }
            return answers;
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
       
        public ArrayList<Integer> seacrhCatId(String tempCat) {
        try {
            PreparedStatement pst = conn.prepareStatement("SELECT * FROM categories where Title=?");
            pst.setString(1, tempCat);
            ResultSet rs = pst.executeQuery();
            ArrayList<Integer> idList = new ArrayList<>();
            while(rs.next()) {
                int tempId = rs.getInt("CategoryID");
                idList.add(tempId);
            }
            return idList;
        } catch (SQLException ex) {
            Logger.getLogger(Sql.class.getName()).log(Level.SEVERE, null, ex);
        } return null;
        }
        
        public ArrayList<Integer> searchQId(String tempQText) {
        try {
            PreparedStatement pst = conn.prepareStatement("Select * From questions where Text=?");
            pst.setString(1, tempQText);
            ResultSet rs = pst.executeQuery();
            ArrayList<Integer> qIdList = new ArrayList<>();
            while(rs.next()) {
                int tempQId = rs.getInt("QuestionID");
                qIdList.add(tempQId);
            }
            return qIdList;
        } catch (SQLException ex) {
            Logger.getLogger(Sql.class.getName()).log(Level.SEVERE, null, ex);
        }   return null;
                
                }
        
        
    }
    
    
    
    
    
    


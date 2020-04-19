/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Models.Answer;
import Models.Category;
import Models.CategorySet;
import Models.Question;
import Models.Result;
import Models.Score;
import Models.User;
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
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ip2", "root", "");
            return conn;
        } catch (SQLException e) {
            return null;
        }
    }

    public ArrayList<Question> getQuestions(int categoryId) {
        try {
            PreparedStatement pst = conn.prepareStatement("SELECT * FROM questions WHERE CategoryId=?");
            pst.setInt(1, categoryId);
            ResultSet rs = pst.executeQuery();

            ArrayList<Question> questions = new ArrayList<>();
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

            ArrayList<Answer> answers = new ArrayList<>();
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

    public int validateLogin(String email, String password) {
        try {
            PreparedStatement pst = conn.prepareStatement("Select * from users where email=? and password=?");
            pst.setString(1, email);
            pst.setString(2, password);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                return rs.getInt(1);
            } else {
                return -1;
            }
        } catch (SQLException ex) {
            Logger.getLogger(QuestionController.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
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
            while (rs.next()) {
                int tempId = rs.getInt("CategoryID");
                idList.add(tempId);
            }
            return idList;
        } catch (SQLException ex) {
            Logger.getLogger(Sql.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public ArrayList<Integer> searchQId(String tempQText) {
        try {
            PreparedStatement pst = conn.prepareStatement("Select * From questions where Text=?");
            pst.setString(1, tempQText);
            ResultSet rs = pst.executeQuery();
            ArrayList<Integer> qIdList = new ArrayList<>();
            while (rs.next()) {
                int tempQId = rs.getInt("QuestionID");
                qIdList.add(tempQId);
            }
            return qIdList;
        } catch (SQLException ex) {
            Logger.getLogger(Sql.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public ArrayList<Score> getTopScores() {
        try {
            PreparedStatement pst = conn.prepareStatement("SELECT * FROM scoreboards");
            ResultSet rs = pst.executeQuery();

            ArrayList<Score> scores = new ArrayList<>();
            while (rs.next()) {
                Score s = new Score(rs.getInt(1),
                        rs.getInt(2),
                        rs.getInt(3));
                scores.add(s);
            }
            return scores;
        } catch (SQLException ex) {
            Logger.getLogger(QuestionController.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public User getUserById(int Id) {
        try {
            PreparedStatement pst = conn.prepareStatement("SELECT * FROM users WHERE UserId=? LIMIT 1");
            pst.setString(1, String.valueOf(Id));
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                User user = new User(rs.getInt(1),
                        rs.getString("FirstName"),
                        rs.getString("LastName"),
                        rs.getString("Email"),
                        rs.getString("Password"),
                        rs.getBoolean("AdminRights"));
                return user;
            }
            return null;
        } catch (SQLException ex) {
            Logger.getLogger(QuestionController.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public Result getResultById(int Id) {
        try {
            PreparedStatement pst = conn.prepareStatement("SELECT * FROM results WHERE ResultID=? LIMIT 1");
            pst.setString(1, String.valueOf(Id));
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                Result result = new Result(rs.getInt(1),
                        rs.getInt(2),
                        rs.getInt(3),
                        rs.getInt(4));
                return result;
            }
            return null;
        } catch (SQLException ex) {
            Logger.getLogger(QuestionController.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public ArrayList<User> getUsers() {
        try {
            PreparedStatement pst = conn.prepareStatement("SELECT * FROM users");
            ResultSet rs = pst.executeQuery();

            ArrayList<User> users = new ArrayList<>();
            while (rs.next()) {
                User u = new User(rs.getInt(1),
                        rs.getString("FirstName"),
                        rs.getString("LastName"),
                        rs.getString("Email"),
                        rs.getString("Password"),
                        rs.getBoolean(6));
                users.add(u);
            }
            return users;
        } catch (SQLException ex) {
            Logger.getLogger(QuestionController.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public ArrayList<Result> getResultsByUserId(int Id) {
        try {
            PreparedStatement pst = conn.prepareStatement("SELECT * FROM results WHERE UserId=?");
            pst.setString(1, String.valueOf(Id));
            ResultSet rs = pst.executeQuery();

            ArrayList<Result> results = new ArrayList<>();
            while (rs.next()) {
                Result r = new Result(rs.getInt(1),
                        rs.getInt(2),
                        rs.getInt(3),
                        rs.getInt(4));
                results.add(r);
            }
            return results;
        } catch (SQLException ex) {
            Logger.getLogger(QuestionController.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public Category getCategoryById(int Id) {
        try {
            PreparedStatement pst = conn.prepareStatement("SELECT * FROM categories WHERE CategoryId=? LIMIT 1");
            pst.setString(1, String.valueOf(Id));
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                Category category = new Category(rs.getInt(1),
                        rs.getString("Title"));
                return category;
            }
            return null;
        } catch (SQLException ex) {
            Logger.getLogger(QuestionController.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public boolean removeScore(int resultId) {
        try {
            PreparedStatement pst = conn.prepareStatement("DELETE FROM scoreboards WHERE ResultID=?");
            pst.setInt(1, resultId);

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

    public boolean addScore(int resultId, int categoryId, int postion) {
        try {
            PreparedStatement pst = conn.prepareStatement("INSERT INTO scoreboards VALUES (?,?,?)");
            pst.setInt(1, resultId);
            pst.setInt(2, categoryId);
            pst.setInt(3, postion);

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

    public Result getResultByUserAndCat(int categoryId, int userId) {
        try {
            PreparedStatement pst = conn.prepareStatement("SELECT * FROM results WHERE CategoryId=? AND UserId=?");
            pst.setString(1, String.valueOf(categoryId));
            pst.setString(2, String.valueOf(userId));
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                Result result = new Result(rs.getInt(1),
                        rs.getInt(2),
                        rs.getInt(3),
                        rs.getInt(4));
                return result;
            }
            return null;
        } catch (SQLException ex) {
            Logger.getLogger(QuestionController.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public boolean addResult(int categoryId, int userId, int score) {
        try {
            PreparedStatement pst = conn.prepareStatement("INSERT INTO results (CategoryID, UserID, Score) VALUES (?,?,?)");
            pst.setInt(1, categoryId);
            pst.setInt(2, userId);
            pst.setInt(3, score);

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

    public boolean removeResult(int resultId) {
        try {
            PreparedStatement pst = conn.prepareStatement("DELETE FROM results WHERE ResultID=?");
            pst.setInt(1, resultId);

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

    public User getUserByEmail(String email) {
        try {
            PreparedStatement pst = conn.prepareStatement("SELECT * FROM users WHERE Email=? LIMIT 1");
            pst.setString(1, email);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                User user = new User(rs.getInt(1),
                        rs.getString("FirstName"),
                        rs.getString("LastName"),
                        rs.getString("Email"),
                        rs.getString("Password"),
                        rs.getBoolean(6));
                return user;
            }
            return null;
        } catch (SQLException ex) {
            Logger.getLogger(QuestionController.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public Category getCategory(int id, String title) {
        try {
            PreparedStatement pst = conn.prepareStatement("SELECT * FROM categories WHERE CategoryId=? AND Title=? LIMIT 1");
            pst.setInt(1, id);
            pst.setString(2, title);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                Category category = new Category(rs.getInt(1),
                        rs.getString("Title"));
                return category;
            }
            return null;
        } catch (SQLException ex) {
            Logger.getLogger(QuestionController.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}

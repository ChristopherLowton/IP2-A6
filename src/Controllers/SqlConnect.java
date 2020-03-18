/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;
import java.sql.*;
/**
 *
 * @author syed
 */
public class SqlConnect {
    Connection conn = null;
    public static Connection ConnectDB() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/testdb","root","");
            return conn;
        } catch(Exception e) {
           return null;
        }
    }
    
    
}

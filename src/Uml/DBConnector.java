/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Uml;

import java.sql.*;
/**
 *
 * @author 786 Computers
 */
public abstract class DBConnector {
    protected Connection con;
    protected Statement st;
    protected ResultSet rs;
    protected PreparedStatement ps;

    public DBConnector() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/trading_app_db","root","");
           
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();e.getCause();
        }
    }
//    public abstract void select();
//    public abstract void delete(int id);
    
}

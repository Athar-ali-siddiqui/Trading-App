/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DBClasses;

import Uml.User;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 786 Computers
 */
public class UserDBConnector extends DBConnector{

    public void insert( String firstname ,String lastname ,String username ,String password ,String email ){
        try {
            //        firstname , lastname , username , password , email
            String queryStr = "insert into user ( firstname , lastname , username , password , email ) values ";
            String valueStr = "('" + firstname + "','" + lastname + "','" + username + "','" + password + "','" + email + "')";
            st = con.createStatement();
            st.executeUpdate(queryStr + valueStr);
            System.out.println("st ==  "+ st.toString());
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserDBConnector.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public User fetchUser(String pw,String username){
        User u = null;
        String queryStr = "select * from user where BINARY username = ? and BINARY password = ?";
        try {
            ps = con.prepareStatement(queryStr);
            ps.setString(1, username);
            ps.setString(2, pw);

            rs = ps.executeQuery();
            
            while(rs.next()){
                u = new User(
                rs.getInt("userId"),rs.getString("firstName") +" "+ rs.getString("lastName"),
                        rs.getString("userName"),rs.getString("email"),rs.getString("password"));

            }
        con.close();
        } catch (SQLException ex) {
            Logger.getLogger(UserDBConnector.class.getName()).log(Level.SEVERE, null, ex);
        }return u;
    }

}

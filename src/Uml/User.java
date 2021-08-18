/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Uml;

/**
 *
 * @author 786 Computers
 */
public class User {
    private int userId;
    private String name;
    private String userName;
    private String email;
    private String password;
    User(int userId, String name,String userName,String email,String password ){
        this.userId = userId;
        this.name =name;
        this.userName=userName;        
        this.email = email;
        this.password = password;                
    }

    User() {
    }
    public int getId(){return userId;}
    public String getPassword(){return password;}
    public String getEmail(){return email;}
    public String getName(){return name;}
    public String getUserName(){return userName;}
        
}

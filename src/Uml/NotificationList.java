/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Uml;

import java.util.ArrayList;

/**
 *
 * @author 786 Computers
 */
public class NotificationList extends ArrayList<Notification>{
    

    public Notification getNotification(String name){
        for (Notification n : this) {
            if(name.equals(n.getUsername())) return n;
        }
        return null;
    }
     public Notification getNotification(int id ){
        for (Notification n : this) {
            if(id == n.getNotsId() ) return n;
        }
        return null;
    }
}

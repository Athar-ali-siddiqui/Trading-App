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
public class NotificationList {
    private ArrayList<Notification> list;
    NotificationList(){
        list = new ArrayList<>();
    }
    public void addNotification(Notification a){
        list.add(a);
    }
    public ArrayList<Notification> getList(){
        return list;
    }
    public Notification getNotification(String name){
        for (Notification n : list) {
            if(name.equals(n.getUsername())) return n;
        }
        return null;
    }
     public Notification getNotification(int id ){
        for (Notification n : list) {
            if(id == n.getNotsId() ) return n;
        }
        return null;
    }
}

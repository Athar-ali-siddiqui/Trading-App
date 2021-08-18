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
public class P2POrderList {
    private ArrayList<P2POrder> list ;
    P2POrderList(){
        list = new ArrayList();
    }
    public void addOrder(P2POrder o){
        list.add(o);
    }
    public ArrayList<P2POrder> getOrderList(){
        return list;
    }
}

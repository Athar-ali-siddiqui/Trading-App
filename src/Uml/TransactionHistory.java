/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Uml;

import java.util.*;

/**
 *
 * @author 786 Computers
 */
public class TransactionHistory {
    private int userId;
    private ArrayList<Transaction> transHistory;

    public TransactionHistory(int userId) {
        this.userId = userId;
        this.transHistory = new ArrayList<>();
    }

    
    public void addTransaction(Transaction t ){
        transHistory.add(t);
    }
    public ArrayList<Transaction> getTransHistroy(){
        return transHistory;
    }
    
}

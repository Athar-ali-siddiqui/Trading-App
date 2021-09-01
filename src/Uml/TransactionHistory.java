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
public class TransactionHistory extends ArrayList<Transaction>{
    private int userId;

    public TransactionHistory(int userId) {
        this.userId = userId;
    }
    
}

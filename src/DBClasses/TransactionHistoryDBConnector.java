/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DBClasses;

import Uml.Transaction;
import Uml.TransactionHistory;
import java.util.*;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 786 Computers
 */
public class TransactionHistoryDBConnector extends DBConnector {

    private int userId;

    public TransactionHistoryDBConnector(int userId) {
        this.userId = userId;
    }
    public int getTransactionId(Transaction trans){
        try {
            
            String queryStr = "select transId from transaction_history where date = ? and takenCurrencyId = ? and takenCurrencyAmount = ? and givenCurrencyId = ? and givenCurrencyAmount= ? ";
            ps = con.prepareStatement(queryStr);
            ps.setString(1, trans.getDate());
            ps.setInt(2,trans.getTakenCurrencyId());
            ps.setDouble(3,trans.getTakenCurrencyAmount() );
            ps.setInt(4,trans.getGivenCurrencyId());
            ps.setDouble(5,trans.getGivenCurrencyAmount() );
//            ps.setInt(6,this.userId);
             rs = ps.executeQuery();
             while(rs.next()){
                 return rs.getInt("transId");
             }
        } catch (SQLException ex) {
            Logger.getLogger(TransactionHistoryDBConnector.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -2389287;
    }
    public void addSpotTransaction(Transaction trans) {
        try {
            //        new Transaction
//(takenCurrencyId, takenCurrencyAmount, takenCurrencyName, givenCurrencyId, givenCurrencyAmount, givenCurrencyName, date, tradeType)
//            post a Transaction into table 
            
            String queryStr = "insert into transaction_history (date,takenCurrencyId,takenCurrencyAmount,givenCurrencyId,givenCurrencyAmount)"
                    +" values (?,?,?,?,?)";
            ps = con.prepareStatement(queryStr);
            ps.setString(1, trans.getDate());
            ps.setInt(2,trans.getTakenCurrencyId());
            ps.setDouble(3,trans.getTakenCurrencyAmount() );
            ps.setInt(4,trans.getGivenCurrencyId());
            ps.setDouble(5,trans.getGivenCurrencyAmount() );
//            ps.setInt(6,this.userId);
            ps.executeUpdate();
//            post userid in spot trade table 
            System.out.println("user id ==== "+ this.userId);
            queryStr = " insert into spot_trade (transId,userId) values (" + getTransactionId(trans) + "," + this.userId + ")";
            st.executeUpdate(queryStr);
        } catch (SQLException ex) {
            Logger.getLogger(TransactionHistoryDBConnector.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public TransactionHistory fetchSpotTransactionHistory() {
        TransactionHistory transHist = new TransactionHistory(this.userId);
        try {
            
            String queryStr ="select t.date as date ,t.takenCurrencyId as takenCurrencyId,c.currencySymbol as takenCurrencySymbol, t.takenCurrencyAmount as takenCurrencyAmount, " +
                "t.givenCurrencyId as givenCurrencyId,c1.currencySymbol as givenCurrencySymbol,t.givenCurrencyAmount as givenCurrencyAmount from transaction_history t " +
                "inner join spot_trade s on t.transId = s.transId " +"inner join currency c on c.currencyId = t.takenCurrencyId " +
                "inner join currency c1 on c1.currencyId = t.givenCurrencyId " +"where userId = "+this.userId + " order by t.transId asc";
            st = con.createStatement();
            rs = st.executeQuery(queryStr);
            
            while(rs.next()){
//(takenCurrencyId, takenCurrencyAmount, takenCurrencyName, givenCurrencyId, givenCurrencyAmount, givenCurrencyName, date, tradeType)
                transHist.add(new Transaction(rs.getInt("takenCurrencyId"),rs.getDouble("takenCurrencyAmount"),rs.getString("takenCurrencySymbol"),rs.getInt("givenCurrencyId"),rs.getDouble("givenCurrencyAmount"),rs.getString("givenCurrencySymbol"),rs.getString("date"),"spot" ));
//                System.out.println(rs.getInt("takenCurrencyId")+" "+rs.getDouble("takenCurrencyAmount")+" "+rs.getInt("givenCurrencyId")+" "+rs.getDouble("givenCurrencyAmount")+" "+rs.getDate("date")+" "+"spot" );
            }
            return transHist;
        } catch (SQLException ex) {
            Logger.getLogger(TransactionHistoryDBConnector.class.getName()).log(Level.SEVERE, null, ex);
        }
        return transHist;

    }
    public void addP2PTransaction(Transaction trans,int sellerId){
        try {
            //        new Transaction
//(takenCurrencyId, takenCurrencyAmount, takenCurrencyName, givenCurrencyId, givenCurrencyAmount, givenCurrencyName, date, tradeType)
//            post a Transaction into table 
            
            String queryStr = "insert into transaction_history (date,takenCurrencyId,takenCurrencyAmount,givenCurrencyId,givenCurrencyAmount)"
                    +" values (?,?,?,?,?)";
            ps = con.prepareStatement(queryStr);
            ps.setString(1, trans.getDate());
            ps.setInt(2,trans.getTakenCurrencyId());
            ps.setDouble(3,trans.getTakenCurrencyAmount() );
            ps.setInt(4,trans.getGivenCurrencyId());
            ps.setDouble(5,trans.getGivenCurrencyAmount() );
//            ps.setInt(6,this.userId);
            ps.executeUpdate();
//            post userid in spot trade table 
            System.out.println("user id ==== "+ this.userId);
            int transId = getTransactionId(trans);
            queryStr = " insert into p2p_trade (transId,sellerId,buyerId) values (" + transId +"," +sellerId +"," + this.userId + ")";
            st.executeUpdate(queryStr);
//            queryStr = " insert into notification (transId,userId,netIncome) values ("+transId +"," +trans.getTrader() +"," +  + ")";
        } catch (SQLException ex) {
            Logger.getLogger(TransactionHistoryDBConnector.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public TransactionHistory fetcP2PTransactionHistory(){
//        int takenCurrencyId, double takenCurrencyAmount, int givenCurrencyId, double givenCurrencyAmount, String date1,String tradeType
        TransactionHistory transHist = new TransactionHistory(this.userId);
        String queryStr = "select  t.date as date,t.takenCurrencyId as takenCurrencyId , t.takenCurrencyAmount as takenCurrencyAmount,c.currencySymbol as takenCurrencySymbol " +
                ", t.givenCurrencyId as givenCurrencyId, t.givenCurrencyAmount as givenCurrencyAmount,c1.currencySymbol as givenCurrencySymbol, p.sellerId as sellerId " +
                ",p.buyerId as buyerId, u1.userName as buyerName, u2.userName as sellerName from transaction_history t inner join " +
                "p2p_trade p on t.transId = p.transId inner join user u1 on u1.userId = p.buyerId " +
                "inner join user u2 on u2.userId = p.sellerId inner join currency c on c.currencyId = t.takenCurrencyId " +
                "inner join currency c1 on c1.currencyId = t.givenCurrencyId " +
                "where p.sellerId =1 or p.buyerId  =1 order by t.transId asc";
        try {
            st = con.createStatement();
            rs = st.executeQuery(queryStr);
            while(rs.next()){
                if(rs.getInt("sellerId") == this.userId){
                    transHist.add(new Transaction(rs.getInt("givenCurrencyId"), rs.getDouble("givenCurrencyAmount"),rs.getString("givenCurrencySymbol"), rs.getInt("takenCurrencyId"), rs.getDouble("takenCurrencyAmount"),rs.getString("takenCurrencySymbol"),rs.getString("date") ,rs.getString("buyerName") ) );
                }
                else if (rs.getInt("buyerId") == this.userId ){
                     transHist.add(new Transaction( rs.getInt("takenCurrencyId"), rs.getDouble("takenCurrencyAmount"),rs.getString("takenCurrencySymbol") ,rs.getInt("givenCurrencyId"), rs.getDouble("givenCurrencyAmount"),rs.getString("givenCurrencySymbol"),rs.getString("date"),rs.getString("sellerName") ) );
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(TransactionHistoryDBConnector.class.getName()).log(Level.SEVERE, null, ex);
        }
                
        return transHist;
    }
    public int countP2PTransactions(String username){
        int count = 0;
        try {
            
            rs =st.executeQuery("select userId from user where userName = '"+username+"'");
            rs.next(); int id = rs.getInt("userId");
            System.out.println("id for count == "+id);
            String querySTr = "select count(t.transId) as num from transaction_history t inner join p2p_trade p on t.transId =p.transId where p.sellerId = "+id+" or p.buyerId ="+id;
            rs =st.executeQuery(querySTr);
            rs.next(); count = rs.getInt("num");
            System.out.println("");
            
        } catch (SQLException ex) {
            Logger.getLogger(TransactionHistoryDBConnector.class.getName()).log(Level.SEVERE, null, ex);
            
        }return count;
    }
}

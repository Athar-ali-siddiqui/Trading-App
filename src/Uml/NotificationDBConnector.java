/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Uml;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 786 Computers
 */
public class NotificationDBConnector extends DBConnector{
    private int userId;

    public NotificationDBConnector(int userId) {
        this.userId = userId;
    }
    
    public void addNotification(int userId, int pnlId){
        
        try {
            System.out.println("herer in nots dbc == "+"("+userId +"," +pnlId  + ")" );
            String queryStr = " insert into notification (userId,pnlId) values ("+userId +"," +pnlId  + ")";
            st = con.createStatement();
            st.executeUpdate(queryStr);
        } catch (SQLException ex) {
            Logger.getLogger(NotificationDBConnector.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    public NotificationList fetchAllNotification(){
        NotificationList list = new NotificationList();
        String queryStr = "select n.notsId as notsId, t.date as date, t.takenCurrencyAmount as takenCurrencyAmount, " +
            " t.givenCurrencyAmount as givenCurrencyAmount, c.currencySymbol as givenCurrencySymbol, " +
            "u1.userName as buyerName,pnl.pnl as pnl " +
            "from profitnloss pnl inner join transaction_history t on pnl.transId = t.transId inner join p2p_trade p on t.transId = p.transId " +
            "inner join user u1 on u1.userId = p.buyerId " +
            "inner join user u2 on u2.userId = p.sellerId " +    
            "inner join notification n on n.pnlId = pnl.pnlId " +
            "inner join currency c on c.currencyId = t.takenCurrencyId "+
            "where p.sellerId ="+this.userId+" order by p.transId asc ";
        System.out.println("querySTr of nots == "+queryStr);
        try {
//String username, double takenCurrencyAmount, String takenCurrencySymbol, double givenCurrencyAmount, String givenCurrencySymbol + ")";
            st = con.createStatement();
            rs = st.executeQuery(queryStr);
            while(rs.next()){
                list.addNotification(new Notification(rs.getInt("notsId"), rs.getString("buyerName"),rs.getDouble("takenCurrencyAmount"),"USD",rs.getDouble("givenCurrencyAmount"),rs.getString("givenCurrencySymbol"),rs.getDouble("pnl"),rs.getString("date") ));
            }
        } catch (SQLException ex) {
            Logger.getLogger(NotificationDBConnector.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return list;
    }
    public void removeNotification(Notification n){
        String queryStr = "delete from notification where notsId = "+n.getNotsId();
        try {
            st = con.createStatement();
            st.executeUpdate(queryStr);
        } catch (SQLException ex) {
            Logger.getLogger(NotificationDBConnector.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

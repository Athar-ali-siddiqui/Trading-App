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
public class P2POrderDBConnetor extends DBConnector{
    private int userId;
    public P2POrderDBConnetor(int userId) {
        this.userId = userId;
    }
    public void addOrder(P2POrder o){
        String queryStr="insert into p2p_order (userId,currencyId,Date,amount,price) values (?,?,?,?,?)";
        try {
            ps = con.prepareStatement(queryStr);
            ps.setInt(1, this.userId);
            ps.setInt(2,o.getCurrencyId());
            ps.setString(3,o.getDate());
            ps.setDouble(4,o.getAmount());
            ps.setDouble(5,o.getPrice());
            
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(P2POrderDBConnetor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void deleteSellOrder(P2POrder o){
        String queryStr= "delete from p2p_order where userId = ? and currencyId = ? and date = ?";
        try {
            ps = con.prepareStatement(queryStr);
            ps.setInt(1, this.userId);
            ps.setInt(2, o.getCurrencyId());
            ps.setString(3,o.getDate());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(P2POrderDBConnetor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void updateOrder(P2POrder o,double amountBuy){
        try {
            String queryStr = "";
            String checkQueryStr = "select amount from p2p_order where userId="+o.getUserId()+" and Date = '"+o.getDate() +"' and currencyId = "+o.getCurrencyId();
//            System.out.println("in updateOrder() checkQueryStr: "+checkQueryStr);
            st = con.createStatement();
            rs = st.executeQuery(checkQueryStr);
            rs.next();
//            System.out.println("@@@@@ rs.getDouble(\"amount\") == " + rs.getDouble("amount"));
//            System.out.println("@@@@@ amountBuy == "+ amountBuy);
//            System.out.println("@@@@@ (rs.getDouble(\"amount\") - amountBuy) == "+ (rs.getDouble("amount") - amountBuy));
            if(amountBuy == rs.getDouble("amount")){
                queryStr = " delete from p2p_order where userId = "+o.getUserId() +" and currencyId = "+o.getCurrencyId()+" and Date = '"+o.getDate()+"'";
            }
            else{
                String updatedAmount = String.format("%.3f",rs.getDouble("amount") - amountBuy);
//                System.out.println("@@#@@@ updatedAmount: "+ updatedAmount);
                queryStr ="update p2p_order set amount =" + Double.parseDouble(updatedAmount) + 
                "where userId = "+o.getUserId() +" and currencyId = "+o.getCurrencyId()+" and Date = '"+o.getDate()+"'";
            }
//            System.out.println(" ***queryStr == "+queryStr);
            st.executeUpdate(queryStr);
        } catch (SQLException ex) {
            Logger.getLogger(P2POrderDBConnetor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public P2POrderList fetchSellOrder(){
//        int userId, int currencyId, double amount, double price, String currencySymbol, Date date
        P2POrderList orders = new P2POrderList();
        String queryStr="select p.currencyId as currencyId, p.amount as amount,"+
                "p.price as price,c.currencySymbol as currencySymbol,p.Date as date from p2p_order as p "+""
                + "inner join currency as c on p.currencyId = c.currencyId where p.userId = "+this.userId +" order by p.transId asc";
        
        try {
            st = con.createStatement();
            rs = st.executeQuery(queryStr);
            while(rs.next()){
                orders.addOrder(new P2POrder(this.userId, rs.getInt("currencyId"), rs.getDouble("amount"), rs.getDouble("price"), rs.getString("currencySymbol"), rs.getString("date") ));
            }
        } catch (SQLException ex) {
            Logger.getLogger(P2POrderDBConnetor.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return orders;
    }
    public P2POrderList fetchBuyOrder(){
// int userId, int currencyId, double amount, double price, String currencySymbol, String date, String userName
        P2POrderList orders = new P2POrderList();
        String queryStr = "select u.userId as userid ,u.userName as userName,c.currencySymbol as currencySymbol ,"
                +" p.currencyId as currencyId, p.amount as amount,p.price as price,c.currencySymbol as currencySymbol,p.date as date from p2p_order as p "
                + "inner join currency as c on p.currencyId = c.currencyId inner join user as u "+
                "on u.userId = p.userId where p.userId != " + this.userId;
        try {
            st = con.createStatement();
            rs = st.executeQuery(queryStr);
            while(rs.next()){
                P2POrder o = new P2POrder(rs.getInt("userid"), rs.getInt("currencyId"), rs.getDouble("amount"), rs.getDouble("price"), rs.getString("currencySymbol"), rs.getString("date"),rs.getString("userName") );
                System.out.println("order == "+o);
                orders.addOrder(o);
            }
        } catch (SQLException ex) {
            Logger.getLogger(P2POrderDBConnetor.class.getName()).log(Level.SEVERE, null, ex);
        }
        return orders;
    }
}

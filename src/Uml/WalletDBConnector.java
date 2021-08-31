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
public class WalletDBConnector extends DBConnector{
    private int userId;

    public WalletDBConnector(int userId) {
        this.userId = userId;
    }
    
    public Wallet fetchWallet(){
        Wallet wallet = new Wallet(userId);
        String queryStr = 
                "select wallet.currencyId as id, currency.currencyName as name, wallet.quantity as qtn "
                + "from wallet inner join currency on currency.currencyId = wallet.currencyId "
                + "inner join user on user.userId = wallet.userId where wallet.userid = "+userId +" order by wallet.currencyId asc";
        try {
            st = con.createStatement();
            rs = st.executeQuery(queryStr);
            
            while(rs.next()){
                Asset a = new Asset(rs.getInt("id") , rs.getString("name"),rs.getDouble("qtn"));
                wallet.add(a);
//                System.out.println(rs.getInt("id") +" "+ rs.getString("name") +" "+rs.getDouble("qtn"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(WalletDBConnector.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return wallet ;
    }
    
//        what if currency is bouth first time ????
//        Check first from select 
    public void updateAsset(int curId,double amount){
//        System.out.println("curId == "+ curId + "  amount == "+ amount);
        try {
            String checkQueryStr = "select count(1) from wallet where userId ="+this.userId+" and currencyId = "+curId;
            st = con.createStatement();
            rs = st.executeQuery(checkQueryStr);
            while(rs.next()){
//                System.out.println("rs.getInt(1)" + rs.getInt(1));
                if(rs.getInt(1) == 0 ){
                    amount = Math.abs(amount);
                    String queryStr = "insert into wallet (userId,currencyId,quantity)"+
                            " values ("+this.userId+","+curId+","+amount+")";
                    st.executeUpdate(queryStr);
                    
                }
                else{
                    try {
                        String olderValueQueryStr = "select quantity from wallet where userId ="+this.userId+" and currencyId = "+curId;
                        st = con.createStatement();
                        rs = st.executeQuery(olderValueQueryStr);
                        rs.next();
//                        System.out.println("rs.getDouble(1) : "+ rs.getDouble(1));
//                        System.out.println("rs.getDouble(\"quantity\") : "+ rs.getDouble("quantity"));
                        if(amount + rs.getDouble("quantity") == 0){
                            String queryStr = "delete from wallet where userId = "+this.userId +" and currencyId = "+curId;
                            st.executeUpdate(queryStr);
                        }
                        else{
                            String queryStr = "update Wallet set quantity = ? where userId = ? and currencyId = ?";
                            ps = con.prepareStatement(queryStr);
    //                        System.out.println("amount + rs.getDouble(\"quantity\") == " + (amount + rs.getDouble("quantity")));
                            String qtn = String.format("%.3f",( amount + rs.getDouble("quantity") ) );
                            ps.setDouble(1,Double.parseDouble(qtn));
                            ps.setInt(2, this.userId);
                            ps.setInt(3, curId);
                            ps.executeUpdate();

                        }
        //        fetchWallet().getWallet().get(1).setQuantity(amount)  ;
                    } catch (SQLException ex) {
                        Logger.getLogger(WalletDBConnector.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                break;
            }
        } catch (SQLException ex) {
            Logger.getLogger(WalletDBConnector.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

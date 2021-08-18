/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Uml;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 786 Computers
 */
public class BankDBConnector extends DBConnector{
    private final String accNo;
    private double amnt;
    
    public BankDBConnector(String accNo){
        super();
        this.accNo = accNo;
        
        
    }

    public boolean accountExits(){
        
        try {
            String queryStr = "select * from dummybank where accountNo = " + this.accNo ;
            st = con.createStatement();
            rs = st.executeQuery(queryStr);
            System.out.println("here in bdc");
            while(rs.next()){
                System.out.println("here in while loop bdc");
//                System.out.println("rs ==" + rs.getInt(1));
                if(!(rs.getString("accountNo").isEmpty())) {
                    this.amnt = rs.getInt("amount");
                    return true;
                }
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(BankDBConnector.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    public boolean canWithdraw(double amnt){
        
        try {
            String queryStr = "select amount from dummybank where accountNo = " + accNo ;
            st = con.createStatement();
            rs = st.executeQuery(queryStr);
            
            while(rs.next()){
                double amount = rs.getDouble(1);
                if(amnt <= amount){
//                    this.amnt =amount;
                    return true;
                }
            }   
        } catch (SQLException ex) {
            Logger.getLogger(BankDBConnector.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }    
    
    
    public double withdraw(double withAmnt){
//        System.out.println("in depsoit **** this.amnt =="+this.amnt + "  withamnt === "+withAmnt);
        try {
            String queryStr = "update dummybank set amount = ? where accountNo = ?";
            ps = con.prepareStatement(queryStr);
            ps.setDouble(1,this.amnt - withAmnt );
            ps.setString(2, accNo);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(BankDBConnector.class.getName()).log(Level.SEVERE, null, ex);
        }
        return this.amnt - withAmnt;
    }
    public void deposit(double depAmnt){
//        System.out.println("in depsoit **** this.amnt =="+this.amnt + "  withamnt === "+depAmnt);
        try {
            String queryStr = "update dummybank set amount = ? where accountNo = ?";
            ps = con.prepareStatement(queryStr);
            ps.setDouble(1,this.amnt + depAmnt );
            ps.setString(2, accNo);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(BankDBConnector.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

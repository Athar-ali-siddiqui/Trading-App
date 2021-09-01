/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DBClasses;

import Uml.Currency;
import Uml.CurrencyList;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 786 Computers
 */
public class CurrencyDBConnector extends DBConnector{
    public CurrencyList getAllCurrency(){
        CurrencyList list = new CurrencyList();
        try {
            
            String queryStr= " select * from currency";
            st = con.createStatement();
            rs = st.executeQuery(queryStr);
            while(rs.next()){
//                System.out.println(rs.getInt("currencyId")+" ,"+ rs.getString("currencyName")+" ,"+ rs.getString("currencySymbol"));
                Currency currency = new Currency(rs.getInt("currencyId"), rs.getString("currencyName"), rs.getString("currencySymbol"));
//                System.out.println("currency :-: "+currency);
                list.add(currency);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(CurrencyDBConnector.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Currency list = "+ list);
        return list;
        
    }
}

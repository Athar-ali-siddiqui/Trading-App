/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DBClasses;

import Uml.Pnl;
import Uml.PnlList;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 786 Computers
 */
public class PnlDBConnector extends DBConnector{
    private int userId;

    public PnlDBConnector(int userId) {
        this.userId = userId;
    }
    public PnlList fetchLast7DaysPnl(){
        PnlList list = new PnlList();

        String queryStr="select date,SUM(pnl) as pnl from profitnloss pnl inner join p2p_trade p on pnl.transId = p.transId where p.sellerId = "+this.userId+" group by date having";
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        Calendar cal = Calendar.getInstance();
        Date date = cal.getTime();
        String[] dates = new String[7];
        dates[0] = sdf.format(date);

        for(int i = 1; i < 7; i++){
            cal.add(Calendar.DAY_OF_MONTH, -1);
            date = cal.getTime();
            dates[i] = sdf.format(date);
        }

        for(int i = 0 ; i < dates.length -1 ; i++){
            String x = dates[i];
            queryStr +=(" date = '" + x + "' or"  );
        }
        queryStr +=(" date =  '" + dates[dates.length - 1] + "' Order by date asc"  );
        
        System.out.println(queryStr);
        System.out.println("dates == "+Arrays.toString(dates));

        HashMap<String, Double> items = new HashMap<String, Double>();
        try {
            st = con.createStatement();
            rs = st.executeQuery(queryStr);
            while( rs.next() ){
                items.put( rs.getString("date") ,Double.parseDouble(String.format( "%.3f",rs.getDouble("pnl") ) ));

            }
            
            for (int i = 0; i < 7; i++) {
                if( items.get( dates[i] ) == null ){
                    
                    list.add(new Pnl( dates[i] ) );
                }
                else {
                    
                     list.add( new Pnl( dates[i] , items.get( dates[i] ) ) );
                }
               
            }
        } catch (SQLException ex) {
            Logger.getLogger(PnlDBConnector.class.getName()).log(Level.SEVERE, null, ex);
        }

        System.out.println("list === "+list);
        return list;
    }
    public void addPnl(Pnl pnl){

        try {
            String queryStr = "insert into profitnloss (date,pnl,transId) values(?,?,?)";
            ps = con.prepareStatement(queryStr);
            ps.setString(1, pnl.getDate() );
            ps.setDouble(2, pnl.getPnl() );
            ps.setInt(3, pnl.getTransId() );
            ps.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(PnlDBConnector.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public int getPnlId(Pnl pnl){
        try {
            String queryStr = "select pnlId from profitnloss where transId = "+pnl.getTransId();
            st = con.createStatement();
            rs = st.executeQuery(queryStr);
            while( rs.next() ){
                return rs.getInt("pnlId");
            }
        } catch (SQLException ex) {
            Logger.getLogger(PnlDBConnector.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return 0;
    }
}

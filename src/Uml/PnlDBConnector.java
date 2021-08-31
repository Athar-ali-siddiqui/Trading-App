/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Uml;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
        int i = 0;
        try {
            st = con.createStatement();
            rs = st.executeQuery(queryStr);
            while( rs.next() ){

                    while(i<7){
                        if(dates[i].equals(rs.getString("date"))){
                            list.add( new Pnl( rs.getString("date") ,Double.parseDouble(String.format( "%.3f",rs.getDouble("pnl") ) ) ) );
                            i++;
                            break;
                        }else{
                            list.add( new Pnl( dates[i] ) );
                            i++;
                        }     
                    }   
            }
            System.out.println("i == "+i);
            for (int j = i; j < 7; j++) {
                list.add( new Pnl( dates[j] ) );
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

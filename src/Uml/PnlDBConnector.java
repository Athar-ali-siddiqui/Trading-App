/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Uml;

import java.sql.SQLException;
import java.util.ArrayList;
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
    public ArrayList<Pnl> fetchLast7DaysPnl(){
        ArrayList<Pnl> list = new ArrayList<>();

        String queryStr="select date,SUM(pnl) as pnl from profitnloss pnl inner join p2p_trade p on pnl.transId = p.transId where p.sellerId = "+this.userId+" group by date having date =  ";
        ArrayList<String> dates = new ArrayList<>();
        Date date = new Date();
        String monthYearStr = "-"+(date.getMonth()+1)+"-"+(date.getYear()+1900);
        for (int i = date.getDate()-6; i < date.getDate() ; i++) {
            dates.add(i+monthYearStr);
            queryStr +=("'"+i+monthYearStr+"' or date = " );
        }
        queryStr +=("'"+date.getDate()+monthYearStr+"' Order by date asc" );
         dates.add(date.getDate()+monthYearStr );
//        System.out.println(queryStr);
        System.out.println("dates == "+dates);
//        Pnl[] pnlList = new Pnl[7];
        int i = 0;
        try {
            st = con.createStatement();
            rs = st.executeQuery(queryStr);
            while( rs.next() ){

                    while(i<7){
                        if(dates.get(i).equals(rs.getString("date"))){
                            list.add( new Pnl( rs.getString("date") ,Double.parseDouble(String.format( "%.3f",rs.getDouble("pnl") ) ) ) );
                            i++;
                            break;
                        }else{
                            list.add( new Pnl( dates.get(i) ) );
                            i++;
                        }
                        
                    }
                    
                        
                    
                    
//                }
//                else{
//                    list.add( new Pnl(dates.get(i) ) );
//                }
                

            }
            System.out.println("i == "+i);
            for (int j = i; j < 7; j++) {
                list.add( new Pnl( dates.get(j) ) );
            }
        } catch (SQLException ex) {
            Logger.getLogger(PnlDBConnector.class.getName()).log(Level.SEVERE, null, ex);
        }
//        System.out.println("list === "+list);
        
//        int j = 0;
//        for (int i = 0; i < dates.size(); i++) {
//            System.out.println("i = "+ i+" j = "+j +"  list= "+list);
//            String date1 = dates.get(i);
//            if(pnlList[i] == null){
//                
//            }
//            if(!date1.equals( list.get(j).getDate() )){
//                list.add(j, new Pnl(date1));
//                
//            }else{
//                
//            }
//            j++;
//        }
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

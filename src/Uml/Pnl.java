/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Uml;

import java.util.Date;

/**
 *
 * @author 786 Computers
 */
public class Pnl {
    private String date;
    private double pnl;
    private int transId;

    public Pnl(Date date, double pnl, int transId) {
        this.date = date.getDate()+ "-" +(date.getMonth()+1) + "-"+ (date.getYear()+1900);
        this.pnl = pnl;
        this.transId = transId;
    }

    public Pnl(String date) {
        this.date = date;
    }

    public Pnl(String date, double pnl) {
        this.date = date;
        this.pnl = pnl;
    }

    public String getDate() {
        return date;
    }

    public double getPnl() {
        return pnl;
    }

    public int getTransId() {
        return transId;
    }

    @Override
    public String toString() {
        return "Pnl{" + "date=" + date + ", pnl=" + pnl + ", transId=" + transId + '}';
    }
    
    
}

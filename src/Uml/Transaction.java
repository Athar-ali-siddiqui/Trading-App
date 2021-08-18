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
public class Transaction {
    private int takenCurrencyId;
    private double takenCurrencyAmount;
    private String takenCurrencySymbol;
    private int givenCurrencyId;
    private double givenCurrencyAmount;
    private String givenCurrencySymbol;
    private String date;
    private String trader;
    
//    For database entry
    public Transaction(int takenCurrencyId, double takenCurrencyAmount, int givenCurrencyId, double givenCurrencyAmount, Date date1) {
        this.takenCurrencyId = takenCurrencyId;
        this.takenCurrencyAmount = takenCurrencyAmount;
        this.givenCurrencyId = givenCurrencyId;
        this.givenCurrencyAmount = givenCurrencyAmount;
        Date date = date1;
        this.date = date.getDate()+ "-" +(date.getMonth()+1) + "-"+ (date.getYear()+1900) +" "+ date.getHours()+":"+ date.getMinutes()+":"+date.getSeconds();
    }
//    For Database fetching
    public Transaction(int takenCurrencyId, double takenCurrencyAmount, String takenCurrencySymbol, int givenCurrencyId, double givenCurrencyAmount, String givenCurrencySymbol, String date, String trader) {
        this.takenCurrencyId = takenCurrencyId;
        this.takenCurrencyAmount = takenCurrencyAmount;
        this.takenCurrencySymbol = takenCurrencySymbol;
        this.givenCurrencyId = givenCurrencyId;
        this.givenCurrencyAmount = givenCurrencyAmount;
        this.givenCurrencySymbol = givenCurrencySymbol;
        
        this.date = date;
        this.trader = trader;
    }
    
    public int getTakenCurrencyId() {
        return takenCurrencyId;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getTakenCurrencyAmount() {
        return takenCurrencyAmount;
    }

    public String getTakenCurrencyName() {
        return takenCurrencySymbol;
    }
     public String getPair() {
        return givenCurrencySymbol + takenCurrencySymbol;
    }

    public int getGivenCurrencyId() {
        return givenCurrencyId;
    }

    public double getGivenCurrencyAmount() {
        return givenCurrencyAmount;
    }

    public String getGivenCurrencyName() {
        return givenCurrencySymbol;
    }

    public String getTrader() {
        return trader;
    }

    public String getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "Transaction{" + "takenCurrencyId=" + takenCurrencyId + ", takenCurrencyAmount=" + takenCurrencyAmount + ", Symbol=" + takenCurrencySymbol + ", givenCurrencyId=" + givenCurrencyId + ", givenCurrencyAmount=" + givenCurrencyAmount + ", givenCurrencySymbol=" + givenCurrencySymbol + ", date=" + date + ", tradeType=" + trader + ", pair=" + this.getPair() + '}';
    }
    

    
}

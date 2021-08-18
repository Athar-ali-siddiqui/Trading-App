/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Uml;

/**
 *
 * @author 786 Computers
 */
public class Notification {
    private int notsId;
    private String username;
    private double takenCurrencyAmount;
    private String takenCurrencySymbol;
    private double givenCurrencyAmount;
    private String givenCurrencySymbol;
    private double netIncome;
    private String date;

   
    public Notification(int notsId,String username, double takenCurrencyAmount, String takenCurrencySymbol, double givenCurrencyAmount, String givenCurrencySymbol, double netIncome, String date) {
        this.notsId = notsId;
        this.username = username;
        this.takenCurrencyAmount = takenCurrencyAmount;
        this.takenCurrencySymbol = takenCurrencySymbol;
        this.givenCurrencyAmount = givenCurrencyAmount;
        this.givenCurrencySymbol = givenCurrencySymbol;
        this.netIncome = netIncome;
        this.date = date;
    }

    public String getUsername() {
        return username;
    }

    public double getTakenCurrencyAmount() {
        return takenCurrencyAmount;
    }

    public String getTakenCurrencySymbol() {
        return takenCurrencySymbol;
    }

    public double getGivenCurrencyAmount() {
        return givenCurrencyAmount;
    }

    public String getGivenCurrencySymbol() {
        return givenCurrencySymbol;
    }

    public Double getNetIncome() {
        return netIncome;
    }
    public String getDate(){
        return date;
    }

    public int getNotsId() {
        return notsId;
    }

    @Override
    public String toString() {
        return "Notification{" + "notsId=" + notsId + ", username=" + username + ", takenCurrencyAmount=" + takenCurrencyAmount + ", takenCurrencySymbol=" + takenCurrencySymbol + ", givenCurrencyAmount=" + givenCurrencyAmount + ", givenCurrencySymbol=" + givenCurrencySymbol + ", netIncome=" + netIncome + ", date=" + date + '}';
    }

    

    
    
}

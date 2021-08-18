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
public class P2POrder {
    private int userId;
    private int currencyId;
    private double amount;
    private double price;
    private String currencySymbol;
    private String date;
     private String userName;

    public P2POrder(int userId, int currencyId, double amount, double price, String currencySymbol, String date, String userName) {
        this.userId = userId;
        this.currencyId = currencyId;
        this.amount = amount;
        this.price = price;
        this.currencySymbol = currencySymbol;
        this.date = date;
        this.userName = userName;
    }

    public P2POrder(int userId, int currencyId, double amount, double price, String currencySymbol, Date date) {
        this.userId = userId;
        this.currencyId = currencyId;
        this.amount = amount;
        this.price = price;
        this.currencySymbol = currencySymbol;
        this.date = date.getDate()+ "-" +(date.getMonth()+1) + "-"+ (date.getYear()+1900) +" "+ date.getHours()+":"+ date.getMinutes()+":"+date.getSeconds();
    }
    
    public P2POrder(int userId, int currencyId, double amount, double price, String currencySymbol, String date) {
        this.userId = userId;
        this.currencyId = currencyId;
        this.amount = amount;
        this.price = price;
        this.currencySymbol = currencySymbol;
        this.date = date;
    }
    public int getUserId() {
        return userId;
    }

    public int getCurrencyId() {
        return currencyId;
    }

    public double getAmount() {
        return amount;
    }

    public String getUserName() {
        return userName;
    }

    public double getPrice() {
        return price;
    }

    public String getCurrencySymbol() {
        return currencySymbol;
    }

    public String getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "P2POrder{" + "userId=" + userId + ", currencyId=" + currencyId + ", amount=" + amount + ", price=" + price + ", currencySymbol=" + currencySymbol + ", date=" + date + ", userName=" + userName + '}';
    }

}

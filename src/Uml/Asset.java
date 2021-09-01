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
public class Asset {
     private int currencyId;
    private String currencyName;

    
    private double quantity=0;
    public Asset(int currencyId,String currencyName, double quantity){
        this.currencyId = currencyId;
        this.currencyName =  currencyName.toUpperCase();
        this.quantity = quantity;
    }
    public int getCurrencyId(){return currencyId;}
    public String getCurrencyName(){return currencyName;}
    public double getQuantity(){return quantity;}
    public void setQuantity(double newQtn){
        this.quantity = newQtn;
    }
    @Override
    public String toString() {
        return "Asset{" + "currencyId=" + currencyId + ", currencyName=" + currencyName + ", quantity=" + quantity + '}';
    }
}

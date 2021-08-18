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
public class Currency {
    private int id;
    private String name;
    private String symbol;
    private double currentPriceInUSD = 1.0;

    public Currency(int id, String name, String symbol) {
        this.id = id;
        this.name = name;
        this.symbol = symbol;
    }

    public double getCurrentPriceInUSD() {
        return currentPriceInUSD;
    }

    public void setCurrentPriceInUSD(double currentPriceInUSD) {
        this.currentPriceInUSD = currentPriceInUSD;
    }
    
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSymbol() {
        return symbol;
    }

    @Override
    public String toString() {
        return "Currency{" + "id=" + id + ", name=" + name + ", symbol=" + symbol + ", currentPriceInUSD=" + currentPriceInUSD + '}';
    }

    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Uml;

import java.util.ArrayList;

/**
 *
 * @author 786 Computers
 */
public class CurrencyList {
    private ArrayList<Currency> currencies = new ArrayList<>();

    public CurrencyList() {}

    public CurrencyList(ArrayList<Currency> currencies) {
        this.currencies = currencies;
    }

    public Currency getCurrency(int i) {
        return currencies.get(i);
    }
    public Currency getCurrency(String name) {
        for (Currency cur : currencies) {
            if( cur.getName().equals( name.toLowerCase()) ) return cur;
        }
        return null;
    }
    public ArrayList<Currency> getAllCurrencies() {
        return currencies;
    }
    public void addCurrency(Currency c) {
//        System.out.println("currency in it's class == "+c);
        currencies.add(c);
    }
    @Override
    public String toString() {
        return "CurrencyList{" + "currencies=" + currencies + '}';
    }
}

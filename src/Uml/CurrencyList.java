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
public class CurrencyList  extends ArrayList<Currency>{
    

    public Currency get(String name) {
        for (Currency cur : this) {
            if( cur.getName().equals( name.toLowerCase()) ) return cur;
        }
        return null;
    }
}

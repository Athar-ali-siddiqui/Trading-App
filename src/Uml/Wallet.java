/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Uml;

import java.util.*;

/**
 *
 * @author 786 Computers
 */
public class Wallet extends ArrayList<Asset>{
    private int userId;

    public Wallet(int id) {
        this.userId = id;
    }
    public int getUserId(){return userId;}

    public Asset getAsset(int id ){
        for (Asset asset : this) {
            if(asset.getCurrencyId() == id) return asset;
        }
        return null;
        
    }
    public boolean haveAsset(String assetName){
//        System.out.println("havASSSET == " + assetName.toLowerCase());
        for (Asset asset : this) {
//            System.out.println("assetss ==" + asset.getCurrencyName());
            if(asset.getCurrencyName().equals( assetName.toUpperCase() )){
                return true;
            }
        }
        return false;
    }
  
}

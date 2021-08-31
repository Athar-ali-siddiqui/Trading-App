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
public class Wallet {
    private int userId;
    private ArrayList<Asset> wallet;

    public Wallet(int id) {
        wallet = new ArrayList<>();
        this.userId = id;
    }
    public int getUserId(){return userId;}
    public void addAsset(Asset a){
        wallet.add(a);
    }
    public void removeAsset(Asset a){
        wallet.remove(a);
    }

    public ArrayList<Asset> getWallet() {
        return wallet;
    }
    public Asset getAsset(int id ){
        for (Asset asset : wallet) {
            if(asset.getCurrencyId() == id) return asset;
        }
        return null;
        
    }
    public boolean haveAsset(String assetName){
//        System.out.println("havASSSET == " + assetName.toLowerCase());
        for (Asset asset : wallet) {
//            System.out.println("assetss ==" + asset.getCurrencyName());
            if(asset.getCurrencyName().equals( assetName.toUpperCase() )){
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "Wallet{" + "userId=" + userId + ", wallet=" + wallet + '}';
    }

    
    
    
}

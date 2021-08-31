/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Uml;

import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author 786 Computers
 */
public class ApiCaller{
    
    private static HttpURLConnection connection;
    
//    1 hour : 5 mint x 12 div
    public List<Double> getDataOfSelectedCoin(String name){
        name = name.toLowerCase();
        List<Double> data = new ArrayList<>();
        String urlStr = "https://api.coingecko.com/api/v3/simple/price?ids="+name+"&vs_currencies=usd&include_market_cap=true&include_24hr_vol=true&include_24hr_change=true";
        String content = callApi(urlStr);
        JSONObject apiContent = new JSONObject(content.toString());
        JSONObject object = apiContent.getJSONObject(name);
        data.add( object.getDouble("usd") );
        data.add( object.getDouble("usd_24h_change") );
        data.add( object.getDouble("usd_24h_vol") );
        
        urlStr =  "https://api.coingecko.com/api/v3/coins/"+ name.toLowerCase()+"/market_chart?vs_currency=usd&days=1&interval=5minute";
        content = callApi(urlStr);
        apiContent = new JSONObject(content.toString());
        JSONArray prices = apiContent.getJSONArray("prices");
        double min =Double.MAX_VALUE;
        double max = 0;
        for(int i =0; i< prices.length();i++){
             JSONArray price = (JSONArray) prices.get(i);
             if(min > Double.parseDouble(price.get(1).toString()) ){
                min = Double.parseDouble(price.get(1).toString());
            }
            else if(max < Double.parseDouble(price.get(1).toString()) ){
                max = Double.parseDouble(price.get(1).toString());
            }
        }
        data.add(2, max); data.add(3, min);
//        System.out.println("data == " + data);
        return data;
    }
    public List<Double> getCurrentPriceOfAllCoins(CurrencyList list){
        List<Double> prices = new ArrayList<>();
        ArrayList<Currency> currency = list;
//        bitcoin%2Cethereum%2Clitecoin%2Cdogecoin
        String urlStr = "https://api.coingecko.com/api/v3/simple/price?ids=";
        for (Currency cur : currency) {
            urlStr += (cur.getName() + "%2C");
        }
        String urlEndStr = "&vs_currencies=usd&include_market_cap=true&include_24hr_vol=true&include_24hr_change=true";
        urlStr += urlEndStr;
        String content = callApi(urlStr);
        JSONObject apiContent = new JSONObject(content.toString());
         for (int i = 1 ; i < currency.size(); i ++){
             Currency cur = currency.get(i);
             JSONObject object = apiContent.getJSONObject(cur.getName());
             prices.add(object.getDouble("usd"));
         }
//        System.out.println("Current prices of coins = "+prices);
        return prices;
    }
//    public double currentPrice(String name){
//        String urlStr =  "https://api.coingecko.com/api/v3/coins/"+ name.toLowerCase()+"/market_chart?vs_currency=usd&days=1&interval=5minute";
//        String content = callApi(urlStr);
//        
//        List< List<String> > arr = extractData(1,content,0);
////        System.out.println("arr == " + arr);
//        String priceStr = arr.get(0).get(1);
////        System.out.println("current price == "+ priceStr);
//        return Double.parseDouble( priceStr.substring(0, priceStr.indexOf(".")+6));
//    }
    public List< List<String> > oneDayFiveMintContent(String name){
        
        String urlStr =  "https://api.coingecko.com/api/v3/coins/"+name.toLowerCase()+"/market_chart?vs_currency=usd&days=1&interval=5minute";
        String content = callApi(urlStr);
        List< List<String> > arr = extractData(12,content,1);

//        System.out.println("arr == " + arr);
        return arr;
    }
    
     public List< List<String> > oneDayTenMintContent(String name) {

        String urlStr =  "https://api.coingecko.com/api/v3/coins/"+name.toLowerCase()+"/market_chart?vs_currency=usd&days=1&interval=5minute";
        String content = callApi(urlStr);
        List< List<String> > arr = extractData(30,content,2);
//        System.out.println("arr == " + arr);
   
        return arr;
     }
//     
    public List< List<String> > oneDayOneHourContent(String name) {

        String urlStr =  "https://api.coingecko.com/api/v3/coins/"+name.toLowerCase()+"/market_chart?vs_currency=usd&days=1&interval=hourly";
        String content = callApi(urlStr);
        List< List<String> > arr = extractData(24,content,1);
//        System.out.println("arr == " + arr);
   
        return arr;
     }
    public List< List<String> > threeDayThreeHourContent(String name) {

        String urlStr =  "https://api.coingecko.com/api/v3/coins/"+name.toLowerCase()+"/market_chart?vs_currency=usd&days=3&interval=hourly";
        String content = callApi(urlStr);
        List< List<String> > arr = extractData(24,content,3);
//        System.out.println("arr == " + arr);
   
        return arr;
     }
//    public List< List<String> > oneWeekTwelveHourContent() {
//
//        String urlStr =  "https://api.coingecko.com/api/v3/coins/bitcoin/market_chart?vs_currency=usd&days=7&interval=hourly";
//        String content = callApi(urlStr);
//        List< List<String> > arr = extractData(14,content,12);
//        System.out.println("arr == " + arr);
//   
//        return arr;
//     }
    public List< List<String> > oneMonthOneDayContent(String name) {

        String urlStr =  "https://api.coingecko.com/api/v3/coins/"+name.toLowerCase()+"/market_chart?vs_currency=usd&days=90&interval=daily";
        String content = callApi(urlStr);
        List< List<String> > arr = extractData(30,content,1);
//        System.out.println("arr == " + arr);
   
        return arr;
     }
    public List< List<String> > threeMonthThreeDayContent(String name) {

        String urlStr =  "https://api.coingecko.com/api/v3/coins/"+name.toLowerCase()+"/market_chart?vs_currency=usd&days=90&interval=daily";
        String content = callApi(urlStr);
        List< List<String> > arr = extractData(30,content,3);
//        System.out.println("arr == " + arr);
   
        return arr;
     }
//    Filter out the meaningfull data
     private List< List<String> > extractData(int div ,String responseContent, int mul){
        List< List<String> > arr = new ArrayList< List<String> >();
        List<String> subArr = new ArrayList<>();
        JSONObject apiContent = new JSONObject(responseContent.toString());
//         System.out.println("apiContent: "+apiContent);
//         System.out.println("responseContent: "+responseContent);
        JSONArray prices = apiContent.getJSONArray("prices");

        int i = prices.length()-1 -div*mul;
          for (int j = 0 ; j < div ; j++) {
            JSONArray price = (JSONArray) prices.get(i) ;
            i += 1*mul;
            
            Date date = new Date( (long) (price.get(0)) );
            String dateStr = (date.getDate())+"/"+(date.getMonth()+1) +" " +date.getHours() + ":" +date.getMinutes();
            subArr.add(dateStr);
            subArr.add(price.get(1).toString());
            arr.add(subArr);
            subArr = new ArrayList<>();

        }
        return arr;
     }
    
    
//    Call api and return apiresponse content
    private String callApi(String str) {
        BufferedReader reader;
        String line;
        StringBuffer responseContent = new StringBuffer();
        URL url;
      try {
            url = new URL(str);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(7000);
            connection.setReadTimeout(7000);
            
            int status = connection.getResponseCode();
            System.out.println("status: "+status);
            if(status > 299){
                reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
            }
            else{
                reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            }
            
            while((line = reader.readLine()) != null){
                responseContent.append(line);
            }
            reader.close();
//            System.out.println(responseContent.toString());
        } catch(java.net.UnknownHostException ex){
            
            System.out.println("Herere in java.net.UnknownHostException api caller");
            throw new org.json.JSONException("Network connectivity Issue");
        }
         catch (MalformedURLException ex) {
            Logger.getLogger(ApiCaller.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ApiCaller.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            connection.disconnect();
        }
      return responseContent.toString();
    }
    
    
}

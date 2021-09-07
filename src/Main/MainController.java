/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import DBClasses.*;
import Uml.*;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.layout.AnchorPane;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.*;
import javafx.collections.*;
import javafx.scene.*;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.*;
import javafx.scene.input.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author 786 Computers
 */
public class MainController implements Initializable {

    @FXML    private AnchorPane userTab,spotTradTab,p2pTradTab,bankTransactionTab,transferTab;
    @FXML    private Button userBtnId,bankTransactionBtnId,spotTradBtnId,p2pTradBtnId,transferBtnId;
    
    @FXML    private LineChart<?, ?> lineChart;
    @FXML    private NumberAxis yAxis;
    @FXML    private ComboBox apiTimeComboBox;
    
    @FXML    private TextField BTTamount,BTTbankAccNo;
    @FXML    private PasswordField BTTverifyPassword,BTTverifyConfirmPassword;
    @FXML    private Label BTTmatchVerifyPasswordLabel,BTTCorrectAmountLabel,BTTreciptTransTypeLabel,BTTreciptAmountLabel,
                           BTTreciptAppFeeLabel,BTTreciptFinalAmountLabel,BTTmatchAccNumLabel,BTTvalidateLabel,BTTreciptAccNum;
    @FXML    private RadioButton BTTdepositTransType,BTTwithdrawTransType;
    @FXML    private ToggleGroup selectTransactionType;
    @FXML    private AnchorPane BTTrecipt;
    
    @FXML    private Label UTusernameLabel,UTemailLabel;
    @FXML    private TableView<Asset> UTtableWallet;
    @FXML    private TableColumn<Asset, String> UTtableCurrencyName;
    @FXML    private TableColumn<Asset, Double> UTtableCurrencyQtn;
    @FXML    private PieChart UTpieChart;
    @FXML    private Label UTpieChartTotalAmnt;
    
    @FXML    private Button logoutBtn,SPTselectBuyBtnId,SPTselectSellBtnId,STTsellCoinBtnId,STTbuyCoinBtnId;
    
    @FXML    private Label STTcurrentPriceLabel,STTamountLabel,STTtotalLabel,STTvalidateLabel,STTsymbolLabel;

    @FXML    private Slider STTsellSlider,STTbuySlider;

    @FXML    private TableView<Transaction> STTtransactionHistory;
    @FXML    private TableColumn<Transaction, String> STTtransactionHistDate;
    @FXML    private TableColumn<Transaction, String> STTtransactionHistPair;
    @FXML    private TableColumn<Transaction, Double> STTtransactionHistAmount;
    @FXML    private TableColumn<Transaction, Double> STTtransactionHistPrice;
    
    @FXML    private ComboBox apiCoinComboBox;
    @FXML    private Text selectedCoinSymbolTag,selectedCoinNameTag,selectedCoinCurrentPriceTag,selectedCoin24HighTag,selectedCoin24LowTag,selectedCoin24VolumeTag;
    @FXML    private Label selectedCoin24ChangeTag;
    
    @FXML    private AnchorPane P2PbuyOrderTab;
    @FXML    private AnchorPane P2PsellOrderTab;
    @FXML    private AnchorPane P2PtradeHistoryTab;
    @FXML    private Label P2PScurrentPriceLabel;
    @FXML    private TextField P2PSpriceTextField;
    @FXML    private Slider P2PSamountSlider;
    @FXML    private Label P2PSamountLabel;
    @FXML    private TableView<P2POrder> P2PSellOrderTableView;
    @FXML    private TableColumn<P2POrder, String> P2PSellOrderTableViewDate;
    @FXML    private TableColumn<P2POrder, String> P2PSellOrderTableViewCoinSymbol;
    @FXML    private TableColumn<P2POrder, Double> P2PSellOrderTableViewPrice;
    @FXML    private TableColumn<P2POrder, Double> P2PSellOrderTableViewAmount;
    @FXML    private Label P2PSsymbolLabel;
    @FXML    private Label P2PSvalidateLabel;
    @FXML    private Label P2PScancelSellOrderValidateLabel;
    
    @FXML    private TableView<P2POrder> P2PBuyOrderTableView;
    @FXML    private TableColumn<P2POrder , String> P2PBuyOrderTableViewUsername;
    @FXML    private TableColumn<P2POrder , String> P2PBuyOrderTableViewCoinSymbol;
    @FXML    private TableColumn<P2POrder , Double> P2PBuyOrderTableViewPrice;
    @FXML    private TableColumn<P2POrder , Double> P2PBuyOrderTableViewAmount;
    @FXML    private AnchorPane P2PBuyOrder;
    @FXML    private Label P2PBusername;
    @FXML    private Label P2PBtotalTrades;
    @FXML    private Label P2PBprice;
    @FXML    private Slider P2PBamountSlider;
    @FXML    private TextField P2PBamountTextField;
    @FXML    private Label P2PBamountSymbol;
    @FXML    private Label P2PBtotalBillAmount;
    @FXML    private Label P2PBtotalBillSymbol;
    @FXML    private AnchorPane P2PtableTab;
    @FXML    private Label P2PBOrderValidateLabel;
    @FXML    private Button P2PBenterBtnId;
    @FXML    private TableView<Transaction> P2PTradeHistTableView;
    @FXML    private TableColumn<Transaction, String> P2PTradeHistUsername;
    @FXML    private TableColumn<Transaction, String> P2PTradeHistDate;
    @FXML    private TableColumn<Transaction, String> P2PTradeHistPair;
    @FXML    private TableColumn<Transaction, Double> P2PTradeHistPrice;
    @FXML    private TableColumn<Transaction, Double> P2PTradeHistAmount;
    @FXML    private Label newNotificationLabel;
    @FXML    private ListView<String> notificationListView;
    @FXML    private AnchorPane notificationTab,notificationDetail;
    @FXML    private Button notificationBtnId;
    @FXML
    private Label notificationDetailUsername,notificationDetailGivenCurrencySymbol,notificationDetailTakenCurrencySymbol,notificationDetailGivenCurrencyAmount;
    @FXML
    private Label notificationDetailTakenCurrencyAmount,notificationDetailNetIncomeLabel,notificationDetailNetIncomeAmount;
    @FXML
    private Label notificationDetailDate,notificationDetailGivenCurrencySymbol1,notificationDetailTakenCurrencySymbol1;
    @FXML
    private BarChart<String, Double> pnlChart;
    
    /**
     * Initializes the controller class.
     * @param user
     */
    private User user ;
    private final ApiCaller caller = new ApiCaller();
    private BankDBConnector bankDBC;private WalletDBConnector walletdbc;
    private Wallet wallet;
    private TransactionHistory spotTransHist;
    private TransactionHistoryDBConnector transdbc;
    private CurrencyList currencies;
    private P2POrderDBConnetor p2porderdbc;
    private P2POrderList p2pSellOrder;
    private P2POrderList p2pBuyOrder;
    private List<Double> currentPriceOfAllCoins;
    private TransactionHistory p2pTransHist;
    private double currentPriceofCoin;
    private Currency selectedCoin;
    private NotificationDBConnector notsdbc;
    private NotificationList notsList;
    private PnlDBConnector pnldbc;
    private PnlList pnlList;
    @FXML
    private Label notificationDetailsNetIncomeSymbol;
    @FXML
    private TableView<Pnl> PNLtableView;
    @FXML
    private TableColumn<Pnl, String> PNLtableViewDate;
    @FXML
    private TableColumn<Pnl, Double> PNLtableViewPnl;
    
//    -----------------------------------------------------
    
    public void dataInit(User user){
        
        this.user = user;
        int id = user.getId();
//        System.out.println("id == "+this.user.getId()+" user name == "+ user.getName() + " password == "+ this.user.getPassword());
        walletdbc = new WalletDBConnector(id);

        transdbc = new TransactionHistoryDBConnector(id); 
        p2porderdbc = new P2POrderDBConnetor(id);
        notsdbc = new NotificationDBConnector(id);
        pnldbc = new PnlDBConnector(id);
        
        currencies = new CurrencyDBConnector().getAllCurrency();
        currentPriceOfAllCoins = caller.getCurrentPriceOfAllCoins(currencies);

        for (int i = 1 ; i < currencies.size(); i ++){
            Currency cur = currencies.get(i);
            
            cur.setCurrentPriceInUSD(currentPriceOfAllCoins.get(i-1));
            
        }
        System.out.println("Currency in data init = "+ currencies);
        selectedCoin = currencies.get(apiCoinComboBox.getValue().toString());
        oneHour(selectedCoin.getName());
        
        dataRefresh();

        showNotification();
        showSTTtransactionHistTable();
        showP2PSellOrder();
        showP2PTradeHistory();
//        showPNLChart();
        
    }
    private void dataRefresh(){

        showUTWallet();
        showUTPieChartData();
        showDataOfSelectedCoin();
        
    }
//     --------------------------------------NOTIFICATION ------------------------------------- 
   
    private void showNotification(){
        notsList=notsdbc.fetchAllNotification();
        ObservableList<String> items = FXCollections.observableArrayList ();
        if(!notsList.isEmpty()){
            newNotificationLabel.setVisible(true);
            for(Notification n : notsList){
                items.add(n.getNotsId()+"- User "+n.getUsername() +" bought your order (click to see details)");
            }
            notificationListView.setItems(items);
        }
        else{
            newNotificationLabel.setVisible(false);
        }
    }
    @FXML
    private void notificationBtn(ActionEvent event) {
        notificationDetail.setVisible(false);
        newNotificationLabel.setVisible(false);
        
        if(notificationTab.isVisible()){
            notificationTab.setVisible(false);
        }else{
            notificationTab.setVisible(true);
        }
        
    }
    @FXML
    private void notificationClicked(MouseEvent event) {
        System.out.println("Notification : "+notificationListView.getSelectionModel().getSelectedItem());
        String str = notificationListView.getSelectionModel().getSelectedItem();
        if( str != null ){
            int id = Integer.parseInt( str.split("-")[0] );
            Notification n = notsList.getNotification(id);
            notificationDetailDate.setText( n.getDate() );
            notificationDetailUsername.setText( n.getUsername() );
            notificationDetailGivenCurrencyAmount.setText( Double.toString(n.getGivenCurrencyAmount()) );
            notificationDetailTakenCurrencyAmount.setText( Double.toString(n.getTakenCurrencyAmount()) );
            notificationDetailTakenCurrencySymbol.setText( n.getGivenCurrencySymbol() );
            notificationDetailGivenCurrencySymbol.setText( n.getTakenCurrencySymbol() );
            notificationDetailTakenCurrencySymbol1.setText( n.getTakenCurrencySymbol() );
            notificationDetailGivenCurrencySymbol1.setText( n.getGivenCurrencySymbol() );

            if(n.getNetIncome()>=0){

                notificationDetailNetIncomeLabel.setText("Profit");
                 notificationDetailNetIncomeAmount.setText( Double.toString(n.getNetIncome()) );
                 notificationDetailsNetIncomeSymbol.setStyle("-fx-text-fill: #0ead3b");
                 notificationDetailNetIncomeAmount.setStyle("-fx-text-fill: #0ead3b");
            }
            else{
                notificationDetailNetIncomeLabel.setText("Loss");
                notificationDetailsNetIncomeSymbol.setStyle("-fx-text-fill: #ff0d29");
                notificationDetailNetIncomeAmount.setStyle("-fx-text-fill: #ff0d29");
                notificationDetailNetIncomeAmount.setText( Double.toString(n.getNetIncome()) );

            }
            notificationDetail.setVisible(true);
        }
        
    }
    @FXML
    private void notificationDetailRemove(ActionEvent event) {

        notificationListView.getItems().remove(notificationListView.getSelectionModel().getSelectedIndex());
        notificationDetail.setVisible(false);
        String name = notificationDetailUsername.getText();
        
        Notification n = notsList.getNotification(name);
        System.out.println(n);
        notsdbc.removeNotification(n);
        
        
    }
//     --------------------------------------END  NOTIFICATION ------------------------------------- 
    
//   --------------------------------------User tab------------------------------------- 
     
    private void showUTWallet(){
        wallet = walletdbc.fetchWallet();
        UTtableCurrencyName.setCellValueFactory(new PropertyValueFactory<Asset, String>("currencyName"));
        UTtableCurrencyQtn.setCellValueFactory(new PropertyValueFactory<Asset, Double>("quantity"));
        
        ObservableList<Asset> assets =FXCollections.observableArrayList();

        for (Asset asset : wallet) {
            assets.add(asset);
        }
        UTtableWallet.setItems(assets);
        
    }
     private void showUTPieChartData(){
        UTpieChart.setLabelsVisible(false);
         ObservableList<PieChart.Data> data =FXCollections.observableArrayList();
         
         double amountInUSD = 0.0;
         for(Asset asset: wallet){
             Currency cur = currencies.get(asset.getCurrencyName());
             System.out.println("asset.getCurrencyName() == " + asset.getCurrencyName()+ " asset.getQuantity() == "+ asset.getQuantity() + "  cur.getCurrentPriceInUSD() == "+cur.getCurrentPriceInUSD());
             data.add(new PieChart.Data(asset.getCurrencyName(),asset.getQuantity()*cur.getCurrentPriceInUSD() ));
             amountInUSD += (asset.getQuantity()*cur.getCurrentPriceInUSD());
             
         }
         
         UTpieChartTotalAmnt.setText("$"+ String.format("%.3f",amountInUSD ) );
         UTpieChart.setData(data);
     }
//----------------------------------------End User tab -----------------------------
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        apiTimeComboBox.getItems().addAll("Five Mintues","Ten Mintues","One Hour","Three Hours","One Day","Three Days");
        apiCoinComboBox.getItems().addAll("Bitcoin","Ethereum","Litecoin","Dogecoin");
        apiCoinComboBox.setValue("Bitcoin");
        apiTimeComboBox.setValue("Five Mintues");
       
    } 
//    Logout button
    @FXML
    private void logout(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
            
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Stage stage = (Stage) logoutBtn.getScene().getWindow();
        stage.close();
    }
   
    

//-------------------------------tab  buttons----------------------
    @FXML
    private void userBtn(ActionEvent event) {
        userBtnId.setStyle("-fx-background-color:  #d0a703 ; -fx-border-width:  0px 0px 4px 0px ; -fx-border-color: #272527");
        spotTradBtnId.setStyle("-fx-background-color:  #272527 ; -fx-border-width:  0px 0px 4px 0px ; -fx-border-color: #d0a703");       
        p2pTradBtnId.setStyle("-fx-background-color:   #272527 ; -fx-border-width:  0px 0px 4px 0px ; -fx-border-color: #d0a703");
        bankTransactionBtnId.setStyle("-fx-background-color:   #272527 ; -fx-border-width:  0px 0px 4px 0px ; -fx-border-color: #d0a703");
        transferBtnId.setStyle("-fx-background-color:   #272527 ; -fx-border-width:  0px 0px 4px 0px ; -fx-border-color: #d0a703");
        spotTradTab.setVisible(false);userTab.setVisible(true);transferTab.setVisible(false);p2pTradTab.setVisible(false);bankTransactionTab.setVisible(false);
        UTemailLabel.setText(user.getEmail());UTusernameLabel.setText(user.getUserName());
        showNotification();showUTWallet();showUTPieChartData();
        
    }
    @FXML
    private void spotTradBtn(ActionEvent event) {
        spotTradBtnId.setStyle("-fx-background-color:  #d0a703 ; -fx-border-width:  0px 0px 4px 0px ; -fx-border-color: #272527");
        userBtnId.setStyle("-fx-background-color:  #272527 ; -fx-border-width:  0px 0px 4px 0px ; -fx-border-color: #d0a703");
        p2pTradBtnId.setStyle("-fx-background-color:   #272527 ; -fx-border-width:  0px 0px 4px 0px ; -fx-border-color: #d0a703");
        bankTransactionBtnId.setStyle("-fx-background-color:   #272527 ; -fx-border-width:  0px 0px 4px 0px ; -fx-border-color: #d0a703");
        transferBtnId.setStyle("-fx-background-color:   #272527 ; -fx-border-width:  0px 0px 4px 0px ; -fx-border-color: #d0a703");
        spotTradTab.setVisible(true);userTab.setVisible(false);transferTab.setVisible(false);p2pTradTab.setVisible(false);bankTransactionTab.setVisible(false);
//        STTcurrentPriceLabel.setText(Double.toString(currentPriceofCoin));
        SPTselectBuyBtn(event);
    }
    @FXML
    private void p2pTradBtn(ActionEvent event) {
        spotTradBtnId.setStyle("-fx-background-color:  #272527 ; -fx-border-width:  0px 0px 4px 0px ; -fx-border-color: #d0a703");
        userBtnId.setStyle("-fx-background-color:  #272527 ; -fx-border-width:  0px 0px 4px 0px ; -fx-border-color: #d0a703");
        bankTransactionBtnId.setStyle("-fx-background-color:   #272527 ; -fx-border-width:  0px 0px 4px 0px ; -fx-border-color: #d0a703");
        transferBtnId.setStyle("-fx-background-color:   #272527 ; -fx-border-width:  0px 0px 4px 0px ; -fx-border-color: #d0a703");
        p2pTradBtnId.setStyle("-fx-background-color:  #d0a703 ; -fx-border-width:  0px 0px 4px 0px ; -fx-border-color: #272527");
        spotTradTab.setVisible(false);userTab.setVisible(false);transferTab.setVisible(false);p2pTradTab.setVisible(true);bankTransactionTab.setVisible(false);
        P2PBuyOrder.setVisible(false);
        
        
        showP2PSellOrder();
        showP2PTradeHistory();
        
        ColorAdjust adj = new ColorAdjust(0, 0, 0, 0);
        GaussianBlur blur = new GaussianBlur(0); 
        adj.setInput(blur);
        P2PBuyOrder.setVisible(false);
        P2PtableTab.setEffect(adj);
        
        P2PselectBuyOrderBtn(event);
        
    }
    //-------------------------------END tab buttons----------------------
// ----------------------   Transfer TAb----------------------
    @FXML
    private void transferBtn(ActionEvent event) {

        transferBtnId.setStyle("-fx-background-color:  #d0a703 ; -fx-border-width:  0px 0px 4px 0px ; -fx-border-color: #272527");
        bankTransactionBtnId.setStyle("-fx-background-color:  #272527 ; -fx-border-width:  0px 0px 4px 0px ; -fx-border-color: #d0a703");
        spotTradBtnId.setStyle("-fx-background-color:  #272527 ; -fx-border-width:  0px 0px 4px 0px ; -fx-border-color: #d0a703");
        userBtnId.setStyle("-fx-background-color:  #272527 ; -fx-border-width:  0px 0px 4px 0px ; -fx-border-color: #d0a703");
        p2pTradBtnId.setStyle("-fx-background-color:   #272527 ; -fx-border-width:  0px 0px 4px 0px ; -fx-border-color: #d0a703");
        spotTradTab.setVisible(false);userTab.setVisible(false);transferTab.setVisible(true);p2pTradTab.setVisible(false);bankTransactionTab.setVisible(false); 
        pnlList = pnldbc.fetchLast7DaysPnl();
        showPNLChart();
        showPNLTable();
    }
    public void showPNLChart(){
        
        XYChart.Series series = new XYChart.Series();
        pnlChart.getData().clear();
        for (int i = pnlList.size()-1 ; i >= 0;i--) {
            Pnl pnl = pnlList.get(i);
            String[] s = pnl.getDate().split("-", 3);
            series.getData().add(new XYChart.Data( s[0]+"-"+s[1] ,pnl.getPnl() ) );
        }

         pnlChart.getData().add(series);
    }
    public void showPNLTable(){
       
//        PNLtableView
        PNLtableViewDate.setCellValueFactory(new PropertyValueFactory<Pnl, String>("date"));
        PNLtableViewPnl.setCellValueFactory(new PropertyValueFactory<Pnl, Double>("pnl"));
        
        ObservableList<Pnl> pnlValues =FXCollections.observableArrayList();
        for (int i = pnlList.size()-1 ; i >= 0;i--) {
            Pnl pnlValue = pnlList.get(i);
            pnlValues.add(pnlValue);
        }
        PNLtableView.setItems(pnlValues);
//
    }
//---------------------- End transfer tab----------------------

//    --------------------------API-----------------------------------

    private double getTickUnits(){
        double tickUnits = 1;
        if(selectedCoin.getName().equals("ethereum")){
            tickUnits = 2;
             
        }else if(selectedCoin.getName().equals("litecoin")){
            tickUnits = 0.2;
            
        }else if(selectedCoin.getName().equals("dogecoin")){
            tickUnits = 0.001;
        }
        else{
            tickUnits = 20;
        }       
        return tickUnits;
    }
    @FXML
    private void apiComboBoxUpdated(ActionEvent event) {
        String selectedCoinName = apiCoinComboBox.getValue().toString();
        selectedCoin = currencies.get(selectedCoinName);
        showDataOfSelectedCoin();
        if(spotTradTab.isVisible()){
            
            SPTselectBuyBtn(event);
        }
        else if (p2pTradTab.isVisible()){
            
            P2PselectSellOrderBtn(event);
        }
        if(apiTimeComboBox.getValue().toString().equals("Five Mintues")){
            oneHour(selectedCoin.getName());
        }
        else if(apiTimeComboBox.getValue().toString().equals("Ten Mintues")){
            FiveHour(selectedCoin.getName());
        }
        else if(apiTimeComboBox.getValue().toString().equals("One Hour")){
            oneDay(selectedCoin.getName());
        }
        else if(apiTimeComboBox.getValue().toString().equals("Three Hours")){
            threeDay(selectedCoin.getName());
        }
        else if(apiTimeComboBox.getValue().toString().equals("One Day")){
            oneMonth(selectedCoin.getName());
        }
        else if(apiTimeComboBox.getValue().toString().equals("Three Days")){
            threeMonth(selectedCoin.getName());
        }
        
    }
    private void showDataOfSelectedCoin(){
        List<Double> data = caller.getDataOfSelectedCoin(selectedCoin.getName());
        currentPriceofCoin = data.get(0);
        
        selectedCoinSymbolTag.setText(selectedCoin.getSymbol()+"/USD");
                
        selectedCoinNameTag.setText(selectedCoin.getName());
        selectedCoinCurrentPriceTag.setText( "$" + currentPriceofCoin );
        int lengthAfterDot = 2;
        if(currentPriceofCoin <10)lengthAfterDot = 6;
        
        
        String dataStr = data.get(1).toString();
        if(data.get(1)>0){
            selectedCoin24ChangeTag.setText( "$+" + dataStr.substring(0, dataStr.indexOf(".")+4) +"%");
            selectedCoin24ChangeTag.setStyle("-fx-text-fill: #0ead3b");
        }
        else{
            selectedCoin24ChangeTag.setText( "$" + dataStr.substring(0, dataStr.indexOf(".")+4) +"%");
            selectedCoin24ChangeTag.setStyle("-fx-text-fill: #ff0d29");
        }
        
        
        dataStr = data.get(2).toString();
        selectedCoin24HighTag.setText( "$" + dataStr.substring(0, dataStr.indexOf(".")+lengthAfterDot) );
        
        dataStr = data.get(3).toString();
        selectedCoin24LowTag.setText( "$" + dataStr.substring(0, dataStr.indexOf(".")+lengthAfterDot) );
        
        dataStr = new BigDecimal(data.get(4)).toString();
        selectedCoin24VolumeTag.setText( "$" + dataStr.substring(0, dataStr.indexOf(".")+2) );
        
       
        STTcurrentPriceLabel.setText(Double.toString(currentPriceofCoin));
        STTbuySlider.setValue(0);STTsellSlider.setValue(0);
        STTamountLabel.setText("0");
        STTtotalLabel.setText("0");
        STTsymbolLabel.setText(selectedCoin.getSymbol());
                
        
        P2PScurrentPriceLabel.setText(Double.toString(currentPriceofCoin));
        P2PSamountLabel.setText("0");P2PSamountSlider.setValue(0);
        P2PSsymbolLabel.setText(selectedCoin.getSymbol());
        
    }
    private void oneHour(String selectedCoinName){
        double tickUnit = getTickUnits();
//        "Five Mintues Graph",
        List< List<String> > oneHourApiContent = caller.oneDayFiveMintContent(selectedCoinName);
        showLineChart( oneHourApiContent, tickUnit);
    }
    private void FiveHour(String selectedCoinName){
//        "Ten Mintues Graph",
double tickUnit = getTickUnits();
        List< List<String> > ApiContent = caller.oneDayTenMintContent(selectedCoinName);
        showLineChart( ApiContent, tickUnit);
    }
    private void oneDay(String selectedCoinName){
//        "One Hours Graph",
        double tickUnit = getTickUnits();
        List< List<String> > ApiContent = caller.oneDayOneHourContent(selectedCoinName);
        showLineChart( ApiContent, tickUnit);
    }
    private void threeDay(String selectedCoinName){
//        "Three Hours Graph"
        double tickUnit = getTickUnits();
        List< List<String> > ApiContent = caller.threeDayThreeHourContent(selectedCoinName);
        showLineChart( ApiContent, tickUnit);
    }
    private void oneMonth(String selectedCoinName){
//        "One Day Graph",
        double tickUnit = getTickUnits();
        List< List<String> > ApiContent = caller.oneMonthOneDayContent(selectedCoinName);
        showLineChart( ApiContent, tickUnit*2);
    }
    private void threeMonth(String selectedCoinName){
//        "Three Days Graph",
        double tickUnit = getTickUnits();
        List< List<String> > ApiContent = caller.threeMonthThreeDayContent(selectedCoinName);
        showLineChart( ApiContent, tickUnit*2);
    }
    
    private void showLineChart( List<List<String>> apiContent , double  tickUnit){
        XYChart.Series series = new XYChart.Series();
        lineChart.getData().clear();
        

        double min = Double.parseDouble( apiContent.get(0).get(1));
        double max = 0;
        for (List<String> list : apiContent) {
        
            if(min > Double.parseDouble( list.get(1)) ){
                min = Double.parseDouble( list.get(1));
            }
            else if(max < Double.parseDouble( list.get(1)) ){
                max = Double.parseDouble( list.get(1));
            }
            series.getData().add(new XYChart.Data(list.get(0), Double.parseDouble( list.get(1)) ));
            
        }
        double lb = 0;
        double ub = 0;
        double boundValue = 50;
        if(tickUnit == 2){
            boundValue = 3;
            ub =Math.ceil(max)+boundValue;
            lb = Math.floor(min) -boundValue;
        }
        else if(tickUnit == 0.2){
            boundValue = 0.1;
            ub =Math.ceil(max)+boundValue;
            lb = Math.floor(min) -boundValue;
        }
        else if(tickUnit == 0.001 || tickUnit == 0.001*2){
            lb = min-0.007;
            ub = max + 0.007;
        }else{
            ub =Math.ceil(max)+boundValue;
            lb = Math.floor(min) -boundValue;
        }

        yAxis.setAutoRanging(false);
        yAxis.setLowerBound(lb);
        yAxis.setUpperBound(ub);
        yAxis.setTickUnit(tickUnit);
        
        lineChart.getData().add(series);
        
    }
//    --------------------------end  API-----------------------------------

//-------------------------------Bank Transaction TAb--------------------
    @FXML
    private void bankTransactionBtn(ActionEvent event) {
        bankTransactionBtnId.setStyle("-fx-background-color:  #d0a703 ; -fx-border-width:  0px 0px 4px 0px ; -fx-border-color: #272527");
        spotTradBtnId.setStyle("-fx-background-color:  #272527 ; -fx-border-width:  0px 0px 4px 0px ; -fx-border-color: #d0a703");
        userBtnId.setStyle("-fx-background-color:  #272527 ; -fx-border-width:  0px 0px 4px 0px ; -fx-border-color: #d0a703");
        p2pTradBtnId.setStyle("-fx-background-color:   #272527 ; -fx-border-width:  0px 0px 4px 0px ; -fx-border-color: #d0a703");
        transferBtnId.setStyle("-fx-background-color:   #272527 ; -fx-border-width:  0px 0px 4px 0px ; -fx-border-color: #d0a703");
        spotTradTab.setVisible(false);userTab.setVisible(false);transferTab.setVisible(false);p2pTradTab.setVisible(false);bankTransactionTab.setVisible(true);  
        BTTCorrectAmountLabel.setText("");BTTamount.setText("");BTTbankAccNo.setText("");BTTmatchAccNumLabel.setText("");BTTmatchVerifyPasswordLabel.setText("");BTTverifyConfirmPassword.setText("");
        BTTreciptAccNum.setText("");BTTreciptAmountLabel.setText("");BTTreciptFinalAmountLabel.setText("");BTTvalidateLabel.setText("");BTTverifyPassword.setText("");
        ColorAdjust adj = new ColorAdjust(0, 0, 0, 0);
        GaussianBlur blur = new GaussianBlur(0); 
        adj.setInput(blur);
        BTTrecipt.setEffect(adj);
    }
    @FXML
    private void BTTaccNumExit(MouseEvent event) {
        BTTreciptAccNum.setText(BTTbankAccNo.getText());
    }

    @FXML
    private void BTTTransTypeClicked(MouseEvent event) {
        if(BTTdepositTransType.isSelected()){
            BTTreciptTransTypeLabel.setText("Deposit to");
        }
        else if(BTTwithdrawTransType.isSelected()){
            BTTreciptTransTypeLabel.setText("Withdraw from");
        }
    }

    @FXML
    private void BTTamountOnChange(KeyEvent event) {
        double appFee = 0;
        String amount = BTTamount.getText();
        BTTreciptAmountLabel.setText("$"+amount);
        BTTreciptAppFeeLabel.setText("$"+ appFee );
        BTTreciptFinalAmountLabel.setText("$"+ amount );
    }
    @FXML
    private void BTTenterBtn(ActionEvent event) {
         BTTvalidateLabel.setText("");
        
        String accNo = BTTbankAccNo.getText();
        double amount = Double.parseDouble(BTTamount.getText());
        if(accNo.isEmpty() == false || BTTverifyPassword.getText().isEmpty() == false 
          || BTTverifyConfirmPassword.getText().isEmpty()==false || BTTdepositTransType.isSelected() || 
           BTTwithdrawTransType.isSelected() || BTTamount.getText().isEmpty() == false){
            
            bankDBC = new BankDBConnector(accNo);
//            Check account number
            BTTvalidateLabel.setText(""); BTTmatchVerifyPasswordLabel.setText("");
             BTTmatchAccNumLabel.setText("");BTTvalidateLabel.setText("");BTTCorrectAmountLabel.setText("");
            if( bankDBC.accountExits() ){
//                check user password
                if( BTTverifyPassword.getText().equals(BTTverifyConfirmPassword.getText()) &&   BTTverifyPassword.getText().equals(user.getPassword())){
//                  check transaction type
                    if(BTTwithdrawTransType.isSelected()){
//                    check withdraw is possible
                        if( bankDBC.canWithdraw(amount ) ){
                            ColorAdjust adj = new ColorAdjust(0, 0, 0, 0);
                            GaussianBlur blur = new GaussianBlur(0); 
                            adj.setInput(blur);
                            BTTrecipt.setEffect(adj);
    //                        withdraws the amount
                            double finalAmnt = bankDBC.withdraw(amount);
                            walletdbc.updateAsset(1, amount);
                            dataRefresh();
                            BTTvalidateLabel.setText("Transaction Occurs Successfully");
                            BTTvalidateLabel.setStyle("-fx-text-fill: #3b3bff");

                        }else{
                            BTTCorrectAmountLabel.setText("Please Enter Correct Amount");
                            ColorAdjust adj = new ColorAdjust(0, -0.9, -0.5, 0);
                            GaussianBlur blur = new GaussianBlur(7); 
                            adj.setInput(blur);
                            BTTrecipt.setEffect(adj);
                        } 
                    }
                    else if (BTTdepositTransType.isSelected()){
                        if(amount <= wallet.getAsset(0).getQuantity()){
                            bankDBC.deposit( amount);
                            walletdbc.updateAsset(1, (-1*amount) );
                            dataRefresh();
                            BTTvalidateLabel.setText("Transaction Occurs Successfully");
                            BTTvalidateLabel.setStyle("-fx-text-fill: #3b3bff");
                        }else{
                            BTTCorrectAmountLabel.setText("Your wallet contains "+ wallet.getAsset(0).getQuantity() +"USD");
                            ColorAdjust adj = new ColorAdjust(0, -0.9, -0.5, 0);
                            GaussianBlur blur = new GaussianBlur(7); 
                            adj.setInput(blur);
                            BTTrecipt.setEffect(adj);
                        }
                        
                    }
                }else{
                    BTTmatchVerifyPasswordLabel.setText("Password does not match");
                }
            }else{
                BTTmatchAccNumLabel.setText("Account no. does not match");
            }            
        }else{
            BTTvalidateLabel.setText("Please Enter All Informations");
        }
    }
//-------------------------------END Bank Transaction TAb--------------------
    
//----------------------------- Spot Trade tab-----------------------------
    private void showSTTtransactionHistTable(){
        spotTransHist = transdbc.fetchSpotTransactionHistory();
         STTtransactionHistDate.setCellValueFactory(new PropertyValueFactory<Transaction, String>("date"));
         STTtransactionHistPair.setCellValueFactory( new PropertyValueFactory<Transaction,String> ("pair"));
         STTtransactionHistAmount.setCellValueFactory( new PropertyValueFactory<Transaction,Double>("takenCurrencyAmount"));
         STTtransactionHistPrice.setCellValueFactory( new PropertyValueFactory<Transaction,Double>("givenCurrencyAmount"));
         
         ObservableList<Transaction> transactionHistory =FXCollections.observableArrayList();
         
         ArrayList<Transaction> trans = spotTransHist;
         Collections.reverse(trans);

         for(Transaction t : trans){
             transactionHistory.add(t);
         }
         STTtransactionHistory.setItems(transactionHistory);
         
     }
    @FXML
    private void SPTselectBuyBtn(ActionEvent event) {

        STTvalidateLabel.setText("");STTsellCoinBtnId.setVisible(false);STTsellSlider.setVisible(false);STTbuyCoinBtnId.setVisible(true);
        STTbuySlider.setVisible(true);STTamountLabel.setText("0");STTtotalLabel.setText("0");STTbuySlider.setValue(0);STTbuySlider.setMin(0);
        
        if(wallet.haveAsset("dollar")) {
            STTbuySlider.setMax(wallet.getAsset(currencies.get(0).getId()).getQuantity());
        }
        else {
            STTbuySlider.setMax(0);
        }
        
        STTbuySlider.valueProperty().addListener(new ChangeListener<Number>() {
            
        @Override
        public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) { 
            
            STTtotalLabel.setText( String.format( "%.3f", STTbuySlider.getValue() ) );
            STTamountLabel.setText( String.format( "%.3f", STTbuySlider.getValue()/currentPriceofCoin )  );
        }
        });
        
    }

    @FXML
    private void SPTselectSellBtn(ActionEvent event) {
        STTvalidateLabel.setText("");STTsellCoinBtnId.setVisible(true);STTsellSlider.setVisible(true);STTbuyCoinBtnId.setVisible(false);STTbuySlider.setVisible(false);
         STTamountLabel.setText("0");STTtotalLabel.setText("0");STTsellSlider.setValue(0);STTsellSlider.setMin(0);
         
         if(wallet.haveAsset(apiCoinComboBox.getValue().toString())) {
             
             STTsellSlider.setMax( wallet.getAsset( selectedCoin.getId()).getQuantity() );
         }
         else {
             STTsellSlider.setMax(0);
         }
         STTsellSlider.valueProperty().addListener(new ChangeListener<Number>() {
            
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
            
                STTamountLabel.setText( String.format( "%.3f" , STTsellSlider.getValue() ) );
                STTtotalLabel.setText( String.format( "%.3f" , currentPriceofCoin * STTsellSlider.getValue() ) );
            }
        });

    }

    @FXML
    private void STTsellCoinBtn(ActionEvent event) {
        
        if(STTsellSlider.getValue() != 0.0){
            double amount = Double.parseDouble(STTtotalLabel.getText());
            double price =  Double.parseDouble(STTamountLabel.getText());
            
            walletdbc.updateAsset( 1, amount );
            walletdbc.updateAsset( selectedCoin.getId() , price*-1 );
            
            Transaction transaction = new Transaction(1, amount, selectedCoin.getId(), price, new Date());
            transdbc.addSpotTransaction(transaction);
            
            showSTTtransactionHistTable();
            dataRefresh();
            STTvalidateLabel.setText("DONE");
        }
    }

    @FXML
    private void STTbuyCoinBtn(ActionEvent event) {
        
        if(STTbuySlider.getValue() != 0.0){
            
            double price = Double.parseDouble(STTtotalLabel.getText());
            double amount = Double.parseDouble(STTamountLabel.getText());
            walletdbc.updateAsset(1, price*-1);
            walletdbc.updateAsset( selectedCoin.getId() , amount );
            Transaction transaction = new Transaction( selectedCoin.getId() , amount ,1, price, new Date());
            transdbc.addSpotTransaction(transaction);
            
            showSTTtransactionHistTable();
            dataRefresh();
            STTvalidateLabel.setText("DONE");
        }
    }
//    -----------------------------END  Spot Trade tab-----------------------------

//    -----------------------------P2P Trade tab------------------------------------
    public void showP2PBuyOrder(){
//        P2PBuyOrderTableViewUsername
        P2PBuyOrderTableViewUsername.setCellValueFactory( new PropertyValueFactory<P2POrder,String>("userName") );
        P2PBuyOrderTableViewCoinSymbol.setCellValueFactory( new PropertyValueFactory<>("currencySymbol") );
        P2PBuyOrderTableViewPrice.setCellValueFactory( new PropertyValueFactory<>("price") );
        P2PBuyOrderTableViewAmount.setCellValueFactory( new PropertyValueFactory<>("amount") );
        
        ObservableList<P2POrder> orders =FXCollections.observableArrayList();
        ArrayList<P2POrder> arr = p2pBuyOrder;
        for (P2POrder p2POrder : arr) {
            orders.add(p2POrder);
        }
        P2PBuyOrderTableView.setItems(orders);
    }
    public void showP2PSellOrder(){
        p2pSellOrder = p2porderdbc.fetchSellOrder();
        P2PSellOrderTableViewDate.setCellValueFactory( new PropertyValueFactory<>("date") );
        P2PSellOrderTableViewCoinSymbol.setCellValueFactory( new PropertyValueFactory<>("currencySymbol") );
        P2PSellOrderTableViewPrice.setCellValueFactory( new PropertyValueFactory<>("price") );
        P2PSellOrderTableViewAmount.setCellValueFactory( new PropertyValueFactory<>("amount") );
        
        ObservableList<P2POrder> orders =FXCollections.observableArrayList();
        ArrayList<P2POrder> arr = p2pSellOrder;
        for (P2POrder p2POrder : arr) {
            orders.add(p2POrder);
        }
        P2PSellOrderTableView.setItems(orders);
    }
    public void showP2PTradeHistory(){
        p2pTransHist = transdbc.fetcP2PTransactionHistory();
        P2PTradeHistUsername.setCellValueFactory(new PropertyValueFactory<Transaction, String>("trader"));
        P2PTradeHistDate.setCellValueFactory(new PropertyValueFactory<Transaction, String>("date"));
         P2PTradeHistPair.setCellValueFactory( new PropertyValueFactory<Transaction,String> ("pair"));
         P2PTradeHistPrice.setCellValueFactory( new PropertyValueFactory<Transaction,Double>("givenCurrencyAmount"));
         P2PTradeHistAmount.setCellValueFactory( new PropertyValueFactory<Transaction,Double>("takenCurrencyAmount"));
         
         
         ObservableList<Transaction> transactionHistory =FXCollections.observableArrayList();
         
         ArrayList<Transaction> trans = p2pTransHist;
         Collections.reverse(trans);

         for(Transaction t : trans){
             t.setDate( t.getDate().split(" ",2)[0] );
             transactionHistory.add(t);
         }
         P2PTradeHistTableView.setItems(transactionHistory);
    }
    @FXML
    private void P2PselectTradeHistoryBtn(ActionEvent event) {
        P2PbuyOrderTab.setVisible(false);
        P2PsellOrderTab.setVisible(false);
        P2PtradeHistoryTab.setVisible(true);
    }

    @FXML
    private void P2PselectSellOrderBtn(ActionEvent event) {
        P2PbuyOrderTab.setVisible(false);
        P2PsellOrderTab.setVisible(true);
        P2PtradeHistoryTab.setVisible(false);
        P2PSvalidateLabel.setText("");
        P2PSpriceTextField.setText("");
        P2PSamountLabel.setText("0");P2PSamountSlider.setValue(0);P2PSamountSlider.setMin(0);
        
        if(wallet.haveAsset(apiCoinComboBox.getValue().toString())) {
             P2PSamountSlider.setMax( wallet.getAsset( selectedCoin.getId()).getQuantity() );
         }
         else {
             P2PSamountSlider.setMax(0);
         }
        
        P2PSamountSlider.valueProperty().addListener(new ChangeListener<Number>() {
            
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                    
                P2PSamountLabel.setText( String.format( "%.3f", P2PSamountSlider.getValue() ) );
                
            }
        });
    }

    @FXML
    private void P2PSenterBtn(ActionEvent event) {
        double price = 0.0;
        double amount = Double.parseDouble( String.format( "%.3f", Double.parseDouble( P2PSamountLabel.getText() ) ) );
        
        if( !P2PSpriceTextField.getText().isEmpty() ){
            price = Double.parseDouble(P2PSpriceTextField.getText());
            p2porderdbc.addOrder(new P2POrder(user.getId(), selectedCoin.getId(), amount, price, selectedCoin.getSymbol(), new Date() ));
        
            walletdbc.updateAsset( selectedCoin.getId() , amount*-1 );
            
            
            showP2PSellOrder();
            dataRefresh();
            P2PSvalidateLabel.setText("   DONE !!");
            
        }
        else{
            P2PSvalidateLabel.setText("Enter Correct Num");
            P2PSvalidateLabel.setStyle("-fx-text-fill: red");
        }
        
    }

    @FXML
    private void P2PScancelSellOrder(ActionEvent event) {
        P2POrder order = P2PSellOrderTableView.getSelectionModel().getSelectedItem();
        walletdbc.updateAsset(order.getCurrencyId(), order.getAmount());
        P2PSellOrderTableView.getItems().removeAll(order);
        P2PScancelSellOrderValidateLabel.setText("Sell Order is deleted Successfully !");
        p2porderdbc.deleteSellOrder(order);
        System.out.println("order :" + order);
        dataRefresh();   
    }

    @FXML
    private void P2PselectBuyOrderBtn(ActionEvent event) {
        P2PBuyOrder.setVisible(false);
        P2PbuyOrderTab.setVisible(true);P2PsellOrderTab.setVisible(false);P2PtradeHistoryTab.setVisible(false);
        p2pBuyOrder = p2porderdbc.fetchBuyOrder();
        showP2PBuyOrder();
    }

    
    @FXML
    private void P2PBuyOrderOpenOrderBtn(ActionEvent event) {
        P2PBOrderValidateLabel.setText(" ");
        if(P2PBuyOrderTableView.getSelectionModel().getSelectedItem() != null){
            ColorAdjust adj = new ColorAdjust(0, -0.9, -0.5, 0);
            GaussianBlur blur = new GaussianBlur(7); 
            adj.setInput(blur);
            P2PBuyOrder.setVisible(true);
            P2PtableTab.setEffect(adj);

            P2POrder order = P2PBuyOrderTableView.getSelectionModel().getSelectedItem();
            System.out.println("order == "+ order);
            P2PBusername.setText(order.getUserName());
            P2PBprice.setText(Double.toString(order.getPrice()));
            P2PBamountTextField.setPromptText("Enter Value");
            P2PBtotalTrades.setText("Total Trades: "+transdbc.countP2PTransactions(order.getUserName()));
            P2PBamountSymbol.setText(order.getCurrencySymbol());
            P2PBamountSlider.setMin(0);
            P2PBamountSlider.setValue(0);
            P2PBamountSlider.setMax(order.getAmount());
            P2PBamountSlider.valueProperty().addListener(new ChangeListener<Number>() {
                @Override
                public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
 
                    P2PBamountTextField.setText( String.format( "%.3f", P2PBamountSlider.getValue() ) );
   
                    P2PBtotalBillAmount.setText( String.format( "%.3f", P2PBamountSlider.getValue()*order.getPrice() ) );

                    Double totalBill =  Double.parseDouble(P2PBtotalBillAmount.getText());
                    if(wallet.getAsset(1).getQuantity() < totalBill){
                        P2PBtotalBillAmount.setStyle("-fx-text-fill: #ff0d29 ");

                        P2PBenterBtnId.setDisable(true);
                    }
                    else if (wallet.getAsset(1).getQuantity() >= totalBill ){
                        P2PBtotalBillAmount.setStyle("-fx-text-fill: white ");
                        P2PBenterBtnId.setDisable(false);
                    }
                }
            });
        }
    }
    @FXML
    private void P2PBuyOrderAmountChange(KeyEvent event) {
        P2PBamountSlider.setValue( Double.parseDouble(P2PBamountTextField.getText()) );
    }

    @FXML
    private void P2PBcancelBtn(ActionEvent event) {
        ColorAdjust adj = new ColorAdjust(0, 0, 0, 0);
        GaussianBlur blur = new GaussianBlur(0); 
        adj.setInput(blur);
        
        P2PBuyOrder.setVisible(false);
        P2PtableTab.setEffect(adj);
        
    }

    @FXML
    private void P2PBenterBtn(ActionEvent event) {
        P2PBenterBtnId.setDisable(false);
        if(P2PBamountSlider.getValue() == 0.0){
            
            P2PBOrderValidateLabel.setText("Please Enter Amount");
        }
        else{
            Double amount = Double.parseDouble(P2PBamountTextField.getText());
            Double totalBill =  Double.parseDouble(P2PBtotalBillAmount.getText());
            P2POrder o = P2PBuyOrderTableView.getSelectionModel().getSelectedItem();
            p2porderdbc.updateOrder(o, amount);
            walletdbc.updateAsset(o.getCurrencyId(), amount);

            walletdbc.updateAsset(1,totalBill*-1);
            
            new WalletDBConnector((o.getUserId())).updateAsset(1, totalBill);
            
            Transaction trans = new Transaction(o.getCurrencyId(), amount , 1 , totalBill,new Date());
            transdbc.addP2PTransaction(trans, o.getUserId());
            
            int transId = transdbc.getTransactionId(trans);
            
            P2PBOrderValidateLabel.setText("DONE !");
            
            showP2PTradeHistory();
            Pnl pnl = new Pnl( new Date(), calculatePNL( o, amount ), transId );
            pnldbc.addPnl(pnl);
            int pnlId = pnldbc.getPnlId(pnl);

            notsdbc.addNotification( o.getUserId(), pnlId);
            dataRefresh();
            ColorAdjust adj = new ColorAdjust(0, 0, 0, 0);
            GaussianBlur blur = new GaussianBlur(0); 
            adj.setInput(blur);

            P2PBuyOrder.setVisible(false);
            P2PtableTab.setEffect(adj);
            showP2PBuyOrder();
            
        }
    }


    private double calculatePNL(P2POrder o ,double amount){
        Double result = o.getPrice()*amount - currentPriceOfAllCoins.get(o.getCurrencyId()-2)*amount;
        
        return Double.parseDouble( String.format( "%.3f",result) );
    }

}
//    ---------------------------------END  P2P Trade tab------------------------------------

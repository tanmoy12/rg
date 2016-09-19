/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.ResourceBundle;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author Tanmoy
 */
public class FrontController implements Initializable {

    @FXML
    private TextField BdLp;
    @FXML
    private TextField BwLp;

    //FOR NEW CREATE STARTS
    public static String newCreateEntitiySetText = null;
    public static String bankName = null;
    public static String institute = null;
    public static String customer = null;
    public static String supplier = null;
    public static String product = null;
    public static String storage = null;
    public static boolean newSet = false;
    public static String newItem = null;
    public static String error;

    @FXML
    private Button BankButt;
    @FXML
    private Button CustomerButt;
    @FXML
    private Button SupplierButt;
    @FXML
    private Button StockButt;
    @FXML
    private Button ExpensesButt;
    @FXML
    private Button TruckButt;
    @FXML
    private VBox BdPartyV;
    @FXML
    private VBox BdInstituteV;
    @FXML
    private VBox BdBankV;
    @FXML
    private VBox BdAmountV;
    @FXML
    private VBox BwPartyV;
    @FXML
    private VBox BwInstituteV;
    @FXML
    private VBox BwBankV;
    @FXML
    private VBox BwAmountV;

    ////////////////////////imran  autosuggest textfield addition starts/////////////////////////////////////////////////////////////
    private AutoCompleteTextField BdParty;

    private AutoCompleteTextField BdInstitute;

    private AutoCompleteTextField BdBankCash;

    //for bankwithdrawl
    private AutoCompleteTextField BwBankCash;

    private AutoCompleteTextField BwInstitute;

    private AutoCompleteTextField BwParty;

    private AutoCompleteTextField EeBankCash;

    private AutoCompleteTextField EeExpense;

    private AutoCompleteTextField ReBankCash;
    private AutoCompleteTextField ReRevenue;

    ////////////////////////////////////////////////////////////Tanmoy er kaaj shesh//////////////////////////////////////////////////
    private IntegerTextField BdAmount;
    private IntegerTextField BwAmount;
    private IntegerTextField EeAmount;
    private IntegerTextField ReAmount;

    @FXML
    private TableColumn<BankTransactionDB, String> depPartyCol;
    @FXML
    private TableColumn<BankTransactionDB, String> depInstituteCol;
    @FXML
    private TableColumn<BankTransactionDB, String> depBankCashCol;
    @FXML
    private TableColumn<BankTransactionDB, String> depAmountCol;
    @FXML
    private TableColumn<BankTransactionDB, String> depLpCol;

    //BANK WITHDRAWAL TABLE
    @FXML
    private TableColumn<BankTransactionDB, String> withPartyCol;
    @FXML
    private TableColumn<BankTransactionDB, String> withInstituteCol;
    @FXML
    private TableColumn<BankTransactionDB, String> withAmountCol;
    @FXML
    private TableColumn<BankTransactionDB, String> withLpCol;

    @FXML
    private TableView<BankTransactionDB> BwTable;
    @FXML
    private TableView<BankTransactionDB> BdTable;
    @FXML
    private TableColumn<BankTransactionDB, String> withBankCashCol;

    @FXML
    private TableView<PurchaseOrderDB> PoTable;
    @FXML
    private TableColumn<PurchaseOrderDB, String> PoSupplierCol;
    @FXML
    private TableColumn<PurchaseOrderDB, String> PoLpCol;
    @FXML
    private TableColumn<PurchaseOrderDB, String> PoProductCol;
    @FXML
    private TableColumn<PurchaseOrderDB, String> PoQuantityCol;
    @FXML
    private TableColumn<PurchaseOrderDB, String> PoUnitCol;
    @FXML
    private TableColumn<PurchaseOrderDB, String> PoRateKgCol;
    @FXML
    private TableColumn<PurchaseOrderDB, String> PoRateMonCol;
    @FXML
    private TableColumn<PurchaseOrderDB, String> PoPriceCol;
    @FXML
    private TableColumn<PurchaseOrderDB, String> PoStorageCol;
    @FXML
    private TableColumn<PurchaseOrderDB, String> PoOrderNoCol;
    @FXML
    private TableColumn<PurchaseOrderDB, String> PoInstituteNameCol;

    @FXML
    private TableView<SaleOrderDB> SoTable;
    //SALES ORDER TABLE
    @FXML
    private TableColumn<SaleOrderDB, String> SoProductCol;
    @FXML
    private TableColumn<SaleOrderDB, String> SoMarkaCol;
    @FXML
    private TableColumn<SaleOrderDB, String> SoQuantityCol;
    @FXML
    private TableColumn<SaleOrderDB, String> SoRateKgSubCol;
    @FXML
    private TableColumn<SaleOrderDB, String> SoRateMonSubCol;
    @FXML
    private TableColumn<SaleOrderDB, String> SoPriceCol;
    @FXML
    private TableColumn<SaleOrderDB, String> SoUnitCol;
    @FXML
    private TableColumn<SaleOrderDB, String> SoOrderNoCol;
    @FXML
    private TableColumn<SaleOrderDB, String> SoCustomerNameCol;
    @FXML
    private TableColumn<SaleOrderDB, String> SoInstituteNameCol;

    @FXML
    private HBox clockDV;
    @FXML
    private HBox clockMV;
    @FXML
    private HBox clockTV;
    @FXML
    private Button BdEntryButt;
    @FXML
    private Button BwEntryButt;
    @FXML
    private Button SoEnterProductInfoButt;
    @FXML
    private Button PdEnterProductInfo;
    @FXML
    private Button SdEnterDeliveryInfo;

    @FXML
    private TableView<PurchaseDeliveryDB> PdTable;
    @FXML
    private TableColumn<PurchaseDeliveryDB, String> PdSupplierCol;
    @FXML
    private TableColumn<PurchaseDeliveryDB, String> PdInstituteCol;
    @FXML
    private TableColumn<PurchaseDeliveryDB, String> PdOrderNoCol;
    @FXML
    private TableColumn<PurchaseDeliveryDB, String> PdProductCol;
    @FXML
    private TableColumn<PurchaseDeliveryDB, String> PdQuantityCol;
    @FXML
    private TableColumn<PurchaseDeliveryDB, String> PdRateKgCol;
    @FXML
    private TableColumn<PurchaseDeliveryDB, String> PdRateMonCol;
    @FXML
    private TableColumn<PurchaseDeliveryDB, String> PdPriceCol;

    @FXML
    private TableView<SaleDeliveryDB> SdTable;
    @FXML
    private TableColumn<SaleDeliveryDB, String> SdCustomerCol;
    @FXML
    private TableColumn<SaleDeliveryDB, String> SdInstituteCol;
    @FXML
    private TableColumn<SaleDeliveryDB, String> SdBillNoCol;
    @FXML
    private TableColumn<SaleDeliveryDB, String> SdTruckNoCol;
    @FXML
    private TableColumn<SaleDeliveryDB, String> SdProductCol;
    @FXML
    private TableColumn<SaleDeliveryDB, String> SdQuantityCol;
    @FXML
    private TableColumn<SaleDeliveryDB, String> SdRateKgCol;
    @FXML
    private TableColumn<SaleDeliveryDB, String> SdRateMonCol;
    @FXML
    private TableColumn<SaleDeliveryDB, String> SdPriceCol;

    @FXML
    private VBox EeExpenseV;
    @FXML
    private TextField EeRemarks;
    @FXML
    private VBox EeBankCashV;
    @FXML
    private VBox EeAmountV;
    @FXML
    private TextField EeLp;
    @FXML
    private TableView<ExpenseDB> EeTable;
    @FXML
    private TableColumn<ExpenseDB, String> EeExpenseCol;
    @FXML
    private TableColumn<ExpenseDB, String> EeRemarksCol;
    @FXML
    private TableColumn<ExpenseDB, String> EeBankCashCol;
    @FXML
    private TableColumn<ExpenseDB, String> EeAmountCol;
    @FXML
    private TableColumn<ExpenseDB, String> EeLpCol;
    @FXML
    private VBox ReRevenueV;
    @FXML
    private TextField ReRemarks;
    @FXML
    private VBox ReBankCashV;
    @FXML
    private VBox ReAmountV;
    @FXML
    private TextField ReLp;
    @FXML
    private TableColumn<RevenueDB, String> ReRevenueCol;
    @FXML
    private TableColumn<RevenueDB, String> ReRemarksCol;
    @FXML
    private TableColumn<RevenueDB, String> ReBankCashCol;
    @FXML
    private TableColumn<RevenueDB, String> ReAmountCol;
    @FXML
    private TableColumn<RevenueDB, String> ReLpCol;

    public static String partyFlag = null;
    @FXML
    private Button BdDeleteButt;
    @FXML
    private Button BdEditButt;
    @FXML
    private Button BwDeleteButt;
    @FXML
    private Button BwEditButt;
    @FXML
    private Button EeDeleteButt;
    @FXML
    private Button EeEditButt;
    @FXML
    private Button ReDeleteButt;
    @FXML
    private Button ReEditButt;

    java.sql.Timestamp timeStamp = new Timestamp(System.currentTimeMillis());
    java.sql.Date date = new java.sql.Date(timeStamp.getTime());

    BankTransactionDB EditBtOb;
    ExpenseDB EditEeOb;
    RevenueDB EditReOb;

    @FXML
    private TableView<RevenueDB> ReTable;
    @FXML
    private Button EeEntryButt;
    @FXML
    private Button ReEntryButt;

    @FXML
    private Button PoEnterProductInfoButt1;

    public static String PoOrderNo = null;
    public static String SoOrderNo = null;
    public static String SdBillNo = null;
    public static String PdDeliveryNo = null;

    public static String user = null;
    @FXML
    private Button settingsButt;
    @FXML
    private TableColumn<PurchaseDeliveryDB, String> PdUnitCol;
    @FXML
    private TableColumn<SaleDeliveryDB, String> SdUnitCol;
    @FXML
    private Button PoViewButt;
    @FXML
    private Button SoViewButt;
    @FXML
    private Button PdViewButt;
    @FXML
    private Button SdViewButt;
    @FXML
    private Button BalanceSheet;
    @FXML
    private TableColumn<SaleOrderDB, String> SoLpCol;
    @FXML
    private TableColumn<PurchaseDeliveryDB, String> PdDeliveryNoCol;
    @FXML
    private TableColumn<PurchaseDeliveryDB, String> PdStorageCol;
    @FXML
    private TableColumn<PurchaseDeliveryDB, String> PdLpCol;
    @FXML
    private TableColumn<SaleDeliveryDB, String> SdOrderNoCol;
    @FXML
    private TableColumn<SaleDeliveryDB, String> SdLpCol;
    @FXML
    private TableColumn<SaleDeliveryDB, String> SdStorageCol;

    /**
     * Initializes the controller class.
     *
     * @param url
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        DontDeleteDB dontDeleteDB = new DontDeleteDB();
        dontDeleteDB.connectDatabase();

        //connecting the database
        Database database = new Database();
        database.connect_database();
        clockDisplayer();

        BdParty = new AutoCompleteTextField();
        BdParty.setEntries(database.getSuggestionList("SELECT * FROM CUSTOMER_LIST;", "CUSTOMER"));
        BdParty.setEntries(database.getSuggestionList("SELECT * FROM SUPPLIER_LIST;", "SUPPLIER"));
        BdParty.setMaxSize(150, 25);
        BdParty.setPromptText("PARTY");

        BdInstitute = new AutoCompleteTextField();
        BdInstitute.setEntries(database.getSuggestionList("SELECT * FROM CUSTOMER_LIST;", "INSTITUTE"));
        BdInstitute.setEntries(database.getSuggestionList("SELECT * FROM SUPPLIER_LIST;", "INSTITUTE"));
        BdInstitute.setMaxSize(150, 25);
        BdInstitute.setPromptText("INSTITUTE");

        BdBankCash = new AutoCompleteTextField();
        BdBankCash.setEntries(database.getSuggestionList("SELECT * FROM BANK_LIST;", "BANK_NAME"));
        BdBankCash.setMaxSize(150, 25);
        BdBankCash.setPromptText("BANK/CASH");

        //bank withdrawl 
        BwParty = new AutoCompleteTextField();
        BwParty.setEntries(database.getSuggestionList("SELECT * FROM SUPPLIER_LIST;", "SUPPLIER"));
        BwParty.setEntries(database.getSuggestionList("SELECT * FROM CUSTOMER_LIST;", "CUSTOMER"));
        BwParty.setMaxSize(150, 25);
        BwParty.setPromptText("PARTY");

        BwInstitute = new AutoCompleteTextField();
        BwInstitute.setEntries(database.getSuggestionList("SELECT * FROM SUPPLIER_LIST;", "INSTITUTE"));
        BwInstitute.setEntries(database.getSuggestionList("SELECT * FROM CUSTOMER_LIST;", "INSTITUTE"));
        BwInstitute.setMaxSize(150, 25);
        BwInstitute.setPromptText("INSTITUTE");

        BwBankCash = new AutoCompleteTextField();
        BwBankCash.setEntries(database.getSuggestionList("SELECT * FROM BANK_LIST;", "BANK_NAME"));
        BwBankCash.setMaxSize(150, 25);
        BwBankCash.setPromptText("BANK/CASH");

        BdPartyV.getChildren().add(BdParty);
        BdInstituteV.getChildren().add(BdInstitute);
        BdBankV.getChildren().add(BdBankCash);

        BwPartyV.getChildren().add(BwParty);
        BwInstituteV.getChildren().add(BwInstitute);
        BwBankV.getChildren().add(BwBankCash);

        EeBankCash = new AutoCompleteTextField();
        EeBankCash.setEntries(database.getSuggestionList("SELECT * FROM BANK_LIST;", "BANK_NAME"));
        EeBankCash.setMaxSize(150, 25);
        EeBankCash.setPromptText("BANK/CASH");

        ReBankCash = new AutoCompleteTextField();
        ReBankCash.setEntries(database.getSuggestionList("SELECT * FROM BANK_LIST;", "BANK_NAME"));
        ReBankCash.setMaxSize(150, 25);
        ReBankCash.setPromptText("BANK/CASH");

        EeExpense = new AutoCompleteTextField();
        EeExpense.setEntries(database.getSuggestionList("SELECT * FROM EXPENSE;", "EXPENSE"));
        EeExpense.setMaxSize(150, 25);
        EeExpense.setPromptText("EXPENSES");

        ReRevenue = new AutoCompleteTextField();
        ReRevenue.setEntries(database.getSuggestionList("SELECT * FROM REVENUE;", "REVENUE"));
        ReRevenue.setMaxSize(150, 25);
        ReRevenue.setPromptText("REVENUE");

        EeExpenseV.getChildren().add(EeExpense);
        ReRevenueV.getChildren().add(ReRevenue);

        EeBankCashV.getChildren().add(EeBankCash);
        ReBankCashV.getChildren().add(ReBankCash);

        ////////////////////////////////////////////TANMOY KAAJ///////////////////////////////////////////////////////////////
        BdAmount = new IntegerTextField();
        BwAmount = new IntegerTextField();
        BdAmount.setPromptText("AMOUNT");
        BwAmount.setPromptText("AMOUNT");

        BdAmountV.getChildren().add(BdAmount);
        BwAmountV.getChildren().add(BwAmount);

        EeAmount = new IntegerTextField();
        ReAmount = new IntegerTextField();
        EeAmount.setPromptText("AMOUNT");
        ReAmount.setPromptText("AMOUNT");

        EeAmountV.getChildren().add(EeAmount);
        ReAmountV.getChildren().add(ReAmount);

        //TABLE SHOW STARTS FROM HERE
        //BANK DEPOSIT TABLE
        depPartyCol.setCellValueFactory(new PropertyValueFactory<>("entity"));
        depInstituteCol.setCellValueFactory(new PropertyValueFactory<>("remarks"));
        depBankCashCol.setCellValueFactory(new PropertyValueFactory<>("bankOrCash"));
        depAmountCol.setCellValueFactory(new PropertyValueFactory<>("amount2"));
        depLpCol.setCellValueFactory(new PropertyValueFactory<>("lp"));
        BdTable.setItems(dontDeleteDB.getBankTransaction("SELECT * FROM BANK_TRANSACTION WHERE (TYPE=1.0 OR TYPE=2.0) AND date(DATE) = '" + date + "' ;"));

        //BANK WITHDRAWAL TABLE
        withPartyCol.setCellValueFactory(new PropertyValueFactory<>("entity"));
        withInstituteCol.setCellValueFactory(new PropertyValueFactory<>("remarks"));
        withBankCashCol.setCellValueFactory(new PropertyValueFactory<>("bankOrCash"));
        withAmountCol.setCellValueFactory(new PropertyValueFactory<>("amount2"));
        withLpCol.setCellValueFactory(new PropertyValueFactory<>("lp"));

        BwTable.setItems(dontDeleteDB.getBankTransaction("SELECT * FROM BANK_TRANSACTION WHERE (TYPE=3.0 OR TYPE=4.0) AND date(DATE) = '" + date + "' ;"));

        PoSupplierCol.setCellValueFactory(new PropertyValueFactory<>("supplier"));
        PoInstituteNameCol.setCellValueFactory(new PropertyValueFactory<>("institute"));
        PoOrderNoCol.setCellValueFactory(new PropertyValueFactory<>("orderNo"));
        PoProductCol.setCellValueFactory(new PropertyValueFactory<>("product"));
        PoStorageCol.setCellValueFactory(new PropertyValueFactory<>("storage"));
        PoQuantityCol.setCellValueFactory(new PropertyValueFactory<>("quantity2"));
        PoUnitCol.setCellValueFactory(new PropertyValueFactory<>("unit2"));
        PoRateKgCol.setCellValueFactory(new PropertyValueFactory<>("rateKg2"));
        PoRateMonCol.setCellValueFactory(new PropertyValueFactory<>("rateMon2"));
        PoPriceCol.setCellValueFactory(new PropertyValueFactory<>("price2"));
        PoLpCol.setCellValueFactory(new PropertyValueFactory<>("lp"));

        PoTable.setItems(dontDeleteDB.getPurchaseOrder("SELECT * FROM PURCHASE_ORDER WHERE date(DATE) = '" + date + "' ;"));

        //SALE ORDER TABLE
        SoOrderNoCol.setCellValueFactory(new PropertyValueFactory<>("invoiceNo"));
        SoCustomerNameCol.setCellValueFactory(new PropertyValueFactory<>("customer"));
        SoInstituteNameCol.setCellValueFactory(new PropertyValueFactory<>("institute"));
        SoProductCol.setCellValueFactory(new PropertyValueFactory<>("product"));
        SoMarkaCol.setCellValueFactory(new PropertyValueFactory<>("marka"));
        SoQuantityCol.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        SoUnitCol.setCellValueFactory(new PropertyValueFactory<>("unit2"));
        SoRateKgSubCol.setCellValueFactory(new PropertyValueFactory<>("rateKg2"));
        SoRateMonSubCol.setCellValueFactory(new PropertyValueFactory<>("rateMon2"));
        SoPriceCol.setCellValueFactory(new PropertyValueFactory<>("price2"));
        SoLpCol.setCellValueFactory(new PropertyValueFactory<>("lp"));

        SoTable.setItems(dontDeleteDB.getSaleOrder("SELECT * FROM SALE_ORDER WHERE date(DATE) = '" + date + "' ; "));
        //PURCHASE DELIVERY TABLE
        PdProductCol.setCellValueFactory(new PropertyValueFactory<>("product"));
        PdSupplierCol.setCellValueFactory(new PropertyValueFactory<>("supplier"));
        PdQuantityCol.setCellValueFactory(new PropertyValueFactory<>("quantity2"));
        PdRateKgCol.setCellValueFactory(new PropertyValueFactory<>("rateKg2"));
        PdRateMonCol.setCellValueFactory(new PropertyValueFactory<>("rateMon2"));
        PdPriceCol.setCellValueFactory(new PropertyValueFactory<>("price2"));
        PdOrderNoCol.setCellValueFactory(new PropertyValueFactory<>("orderNo"));
        PdUnitCol.setCellValueFactory(new PropertyValueFactory<>("unit2"));
        PdDeliveryNoCol.setCellValueFactory(new PropertyValueFactory<>("deliveryNo"));
        PdStorageCol.setCellValueFactory(new PropertyValueFactory<>("storage"));
        PdLpCol.setCellValueFactory(new PropertyValueFactory<>("lp"));

        PdInstituteCol.setCellValueFactory(new PropertyValueFactory<>("institute"));

        PdTable.setItems(dontDeleteDB.getPurchaseDelivery("SELECT * FROM PURCHASE_DELIVERY WHERE date(DATE) = '" + date + "' ;"));
        //SALE DELIVERY TABLE SHOW
        SdProductCol.setCellValueFactory(new PropertyValueFactory<>("product"));
        SdTruckNoCol.setCellValueFactory(new PropertyValueFactory<>("truckNo"));
        SdBillNoCol.setCellValueFactory(new PropertyValueFactory<>("billNo"));
        SdRateKgCol.setCellValueFactory(new PropertyValueFactory<>("rateKg2"));
        SdRateMonCol.setCellValueFactory(new PropertyValueFactory<>("rateMon2"));
        SdPriceCol.setCellValueFactory(new PropertyValueFactory<>("price2"));
        SdCustomerCol.setCellValueFactory(new PropertyValueFactory<>("customer"));
        SdInstituteCol.setCellValueFactory(new PropertyValueFactory<>("institute"));
        SdUnitCol.setCellValueFactory(new PropertyValueFactory<>("unit2"));
        SdQuantityCol.setCellValueFactory(new PropertyValueFactory<>("quantity2"));
        SdOrderNoCol.setCellValueFactory(new PropertyValueFactory<>("invoiceNo"));
        SdStorageCol.setCellValueFactory(new PropertyValueFactory<>("storage"));
        SdLpCol.setCellValueFactory(new PropertyValueFactory<>("lp"));

        SdTable.setItems(dontDeleteDB.getSaleDelivery("SELECT * FROM SALE_DELIVERY WHERE date(DATE) = '" + date + "' ;"));

        //TABLE SHOW STARTS FROM HERE
        //Expense TABLE
        EeExpenseCol.setCellValueFactory(new PropertyValueFactory<>("expense"));
        EeRemarksCol.setCellValueFactory(new PropertyValueFactory<>("remarks"));
        EeBankCashCol.setCellValueFactory(new PropertyValueFactory<>("bankOrCash"));
        EeAmountCol.setCellValueFactory(new PropertyValueFactory<>("amount"));
        EeLpCol.setCellValueFactory(new PropertyValueFactory<>("lp"));

        EeTable.setItems(dontDeleteDB.getExpense("SELECT * FROM EXPENSE WHERE date(DATE) = '" + date + "' ;"));

        //REVENUE TABLE
        ReRevenueCol.setCellValueFactory(new PropertyValueFactory<>("revenue"));
        ReRemarksCol.setCellValueFactory(new PropertyValueFactory<>("remarks"));
        ReBankCashCol.setCellValueFactory(new PropertyValueFactory<>("bankOrCash"));
        ReAmountCol.setCellValueFactory(new PropertyValueFactory<>("amount"));
        ReLpCol.setCellValueFactory(new PropertyValueFactory<>("lp"));

        ReTable.setItems(dontDeleteDB.getRevenue("SELECT * FROM REVENUE WHERE date(DATE) = '" + date + "' ;"));

        //TABLE SHOW ENDS HERE
        BdParty.setOnAction((ActionEvent evt) -> {
            BdInstitute.setText(getInstitute(BdParty.getText()));
            BdInstitute.requestFocus();
        });

        BwParty.setOnAction((ActionEvent evt) -> {
            BwInstitute.setText(getInstitute(BwParty.getText()));
            BwInstitute.requestFocus();
        });

        setLevel();

        dontDeleteDB.closeDatabase();
        database.close_database();
        errorhandling();

    }

    private void setLevel() {
        if (LoginController.level == false) {
            settingsButt.setDisable(true);
        } else {
            LoginController.level = false;
        }
    }

    private String getInstitute(String party) {

        DontDeleteDB dontDeleteDB = new DontDeleteDB();
        dontDeleteDB.connectDatabase();

        String mySql = "SELECT INSTITUTE FROM CUSTOMER_LIST WHERE CUSTOMER= '"
                + party + "'";
        String Ins = dontDeleteDB.getAStringDB(mySql, "INSTITUTE");
        if ("".equals(Ins) || Ins == null) {
            mySql = "SELECT INSTITUTE FROM SUPPLIER_LIST WHERE SUPPLIER = '"
                    + party + "'";
            Ins = dontDeleteDB.getAStringDB(mySql, "INSTITUTE");
            dontDeleteDB.closeDatabase();
            return Ins;

        }
        dontDeleteDB.closeDatabase();
        return Ins;

    }

    private void errorhandling() {

        BdEntryButt.disableProperty().bind(
                Bindings.isEmpty(BdAmount.textProperty())
                .or(Bindings.isEmpty(BdBankCash.textProperty()))
                .or(Bindings.isEmpty(BdParty.textProperty()))
                .or(Bindings.isEmpty(BdInstitute.textProperty()))
        );
        BwEntryButt.disableProperty().bind(
                Bindings.isEmpty(BwAmount.textProperty())
                .or(Bindings.isEmpty(BwBankCash.textProperty()))
                .or(Bindings.isEmpty(BwParty.textProperty()))
                .or(Bindings.isEmpty(BwInstitute.textProperty()))
        );
        EeEntryButt.disableProperty().bind(
                Bindings.isEmpty(EeAmount.textProperty())
                .or(Bindings.isEmpty(EeBankCash.textProperty()))
                .or(Bindings.isEmpty(EeExpense.textProperty()))
                .or(Bindings.isEmpty(EeRemarks.textProperty()))
        );
        ReEntryButt.disableProperty().bind(
                Bindings.isEmpty(ReAmount.textProperty())
                .or(Bindings.isEmpty(ReBankCash.textProperty()))
                .or(Bindings.isEmpty(ReRevenue.textProperty()))
                .or(Bindings.isEmpty(ReRemarks.textProperty()))
        );

    }

    private void clockDisplayer() {

        final Label clockd = new Label();
        final Label clockm = new Label();
        final Label clockt = new Label();

        final DateFormat format = new SimpleDateFormat("dd");
        final DateFormat formatm = new SimpleDateFormat("MM");
        final DateFormat formaty = new SimpleDateFormat("hh:mm:ss");
        final Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), (ActionEvent event) -> {
            final Calendar cal = Calendar.getInstance();
            clockd.setText(format.format(cal.getTime()) + "/");
            clockd.setFont(Font.font(70));
            clockm.setText(formatm.format(cal.getTime()));
            clockm.setFont(Font.font(52));
            clockt.setText(formaty.format(cal.getTime()));
            clockt.setFont(Font.font(25));
        }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
        clockDV.getChildren().add(clockd);
        clockMV.getChildren().add(clockm);
        clockTV.getChildren().add(clockt);
    }

    @FXML
    private void BankButtClick(ActionEvent event) throws IOException {

        Parent p = FXMLLoader.load(getClass().getResource("EmployeePass.fxml"));
        Stage Banks = new Stage();
        Scene scene = new Scene(p);
        Banks.initModality(Modality.WINDOW_MODAL);
        Banks.initOwner(
                ((Node) event.getSource()).getScene().getWindow());
        Banks.setScene(scene);
        Banks.showAndWait();
        if (LoginController.level == true) {
            LoginController.level = false;
            p = FXMLLoader.load(getClass().getResource("BankList.fxml"));
            Banks = new Stage();
            scene = new Scene(p);
            Banks.initModality(Modality.WINDOW_MODAL);
            Banks.initOwner(
                    ((Node) event.getSource()).getScene().getWindow());
            Banks.setScene(scene);
            Banks.showAndWait();
            Database database = new Database();
            database.connect_database();
            DontDeleteDB dontDeleteDB = new DontDeleteDB();
            dontDeleteDB.connectDatabase();

            BdBankCash.setEntries(database.getSuggestionList("SELECT * FROM BANK_LIST;", "BANK_NAME"));
            BwBankCash.setEntries(database.getSuggestionList("SELECT * FROM BANK_LIST;", "BANK_NAME"));

            BdTable.setItems(dontDeleteDB.getBankTransaction("SELECT * FROM BANK_TRANSACTION WHERE (TYPE=1.0 OR TYPE=2.0) AND date(DATE) = '" + date + "' ;"));

            BwTable.setItems(dontDeleteDB.getBankTransaction("SELECT * FROM BANK_TRANSACTION WHERE (TYPE=3.0 OR TYPE=4.0) AND date(DATE) = '" + date + "' ;"));

            dontDeleteDB.closeDatabase();
            database.close_database();

        }

    }

    @FXML
    private void CustomerButtClick(ActionEvent event) throws IOException {
        Parent p = FXMLLoader.load(getClass().getResource("CustomerList.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(p);
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(
                ((Node) event.getSource()).getScene().getWindow());
        stage.setScene(scene);
        stage.showAndWait();

        Database database = new Database();
        database.connect_database();

        BdParty.setEntries(database.getSuggestionList("SELECT * FROM CUSTOMER_LIST;", "NAME"));

        database.close_database();

    }

    @FXML
    private void SupplierButtClick(ActionEvent event) throws IOException {
        Parent p = FXMLLoader.load(getClass().getResource("SupplierList.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(p);
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(
                ((Node) event.getSource()).getScene().getWindow());
        stage.setScene(scene);
        stage.showAndWait();

        Database database = new Database();
        database.connect_database();
        BwParty.setEntries(database.getSuggestionList("SELECT * FROM SUPPLIER_LIST;", "SUPPLIER"));
        database.close_database();

    }

    @FXML
    private void StockButtClick(ActionEvent event) throws IOException {
        Parent p = FXMLLoader.load(getClass().getResource("StockList.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(p);
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(
                ((Node) event.getSource()).getScene().getWindow());
        stage.setScene(scene);
        stage.showAndWait();

    }

    @FXML
    private void ExpensesButtClick(ActionEvent event) throws IOException {
        Parent p = FXMLLoader.load(getClass().getResource("ExpensesList.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(p);
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(
                ((Node) event.getSource()).getScene().getWindow());
        stage.setScene(scene);
        stage.showAndWait();
    }

    @FXML
    private void BalanceSheetButtClick(ActionEvent event) throws IOException {
        Parent p = FXMLLoader.load(getClass().getResource("AdminPass.fxml"));
        Stage Banks = new Stage();
        Scene scene = new Scene(p);
        Banks.initModality(Modality.WINDOW_MODAL);
        Banks.initOwner(
                ((Node) event.getSource()).getScene().getWindow());
        Banks.setScene(scene);
        Banks.showAndWait();
        if (LoginController.level == true) {
            LoginController.level = false;
            p = FXMLLoader.load(getClass().getResource("BalanceSheet.fxml"));
            Stage stage = new Stage();
            scene = new Scene(p);
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(
                    ((Node) event.getSource()).getScene().getWindow());
            stage.setScene(scene);
            stage.showAndWait();
            if (BalanceSheetController.reini == true) {
                BalanceSheetController.reini = false;
                stage = (Stage) BalanceSheet.getScene().getWindow();
                stage.close();
                p = FXMLLoader.load(getClass().getResource("Front.fxml"));
                Stage front = new Stage();
                scene = new Scene(p);
                front.setScene(scene);
                front.show();
            }

        }
    }

    @FXML
    private void TruckButtClick(ActionEvent event) throws IOException {
        Parent p = FXMLLoader.load(getClass().getResource("TruckTrans.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(p);
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(
                ((Node) event.getSource()).getScene().getWindow());
        stage.setScene(scene);
        stage.showAndWait();

    }

    @FXML
    private void settingsButtclick(ActionEvent event) throws IOException {
        Parent p = FXMLLoader.load(getClass().getResource("Settings.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(p);
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(
                ((Node) event.getSource()).getScene().getWindow());
        stage.setScene(scene);
        stage.showAndWait();
    }

    @FXML
    private void BankDepositEntryButt(ActionEvent event) throws IOException, SQLException {
        if ("SAVE".equals(BdEntryButt.getText())) {
            BdDeleteEntry(EditBtOb);
            EditBtOb = null;
            BdEntryButt.setText("ENTRY");
            BdEditButt.setText("EDIT");
        }
        Timestamp dateTimeNow = new java.sql.Timestamp(new java.util.Date().getTime());

        DontDeleteDB dontDeleteDB = new DontDeleteDB();
        dontDeleteDB.connectDatabase();

        //BANK DEPOSIT NEW BANK CREATE
        String mySql = "SELECT BANK_LIST_ID FROM BANK_LIST WHERE BANK_NAME= '" + BdBankCash.getText() + "'";
        int createNew = dontDeleteDB.getAIntDB(mySql, "BANK_LIST_ID");
        if (createNew == 0) {

            newCreateEntitiySetText = "NEW BANK";
            bankName = BdBankCash.getText();
            //System.out.println(customer + "frontcontroller");

            newItem = bankName;
            Parent p = FXMLLoader.load(getClass().getResource("NewCreate.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(p);

            stage.setScene(scene);
            stage.setX(480);
            stage.setY(250);
            stage.showAndWait();
            bankName = null;
            if (newSet == false) {
                return;
            } else {
                newSet = false;
            }

        }
        createNew = dontDeleteDB.getAIntDB(mySql, "BANK_LIST_ID");

        //SHADMAN'S WORK
        //BANK DEPOSIT NEW CUSTOMER/NEW SUPPLIER CREATE
        mySql = "SELECT CUSTOMER_LIST_ID FROM CUSTOMER_LIST WHERE CUSTOMER= '" + BdParty.getText()
                + "'AND INSTITUTE= '" + BdInstitute.getText() + "'";
        int customerCheck = dontDeleteDB.getAIntDB(mySql, "CUSTOMER_LIST_ID");
        System.out.println(customerCheck + "customercheck");
        if (customerCheck == 0) {
            mySql = "SELECT SUPPLIER_LIST_ID FROM SUPPLIER_LIST WHERE SUPPLIER = '" + BdParty.getText()
                    + "' AND INSTITUTE = '" + BdInstitute.getText() + "'";
            int supplierCheck = dontDeleteDB.getAIntDB(mySql, "SUPPLIER_LIST_ID");
            System.out.println(supplierCheck + "customercheck");
            if (supplierCheck == 0) {

                newCreateEntitiySetText = "NEW PARTY";

                customer = BdParty.getText();
                supplier = BdParty.getText();
                institute = BdInstitute.getText();

                Parent p = FXMLLoader.load(getClass().getResource("BankNewPartyCreate.fxml"));
                Stage stage = new Stage();
                Scene scene = new Scene(p);

                stage.setScene(scene);
                stage.setX(480);
                stage.setY(250);
                stage.showAndWait();
                customer = null;
                supplier = null;
                institute = null;
                if (newSet == false) {
                    return;
                } else {
                    newSet = false;
                }

            }
        } //SHADMAN'S WORK

        mySql = "SELECT CUSTOMER_LIST_ID FROM CUSTOMER_LIST WHERE CUSTOMER= '" + BdParty.getText()
                + "'" + "AND INSTITUTE= '" + BdInstitute.getText() + "'";
        customerCheck = dontDeleteDB.getAIntDB(mySql, "CUSTOMER_LIST_ID");

        if (customerCheck == 0) {
            mySql = "SELECT SUPPLIER_LIST_ID FROM SUPPLIER_LIST WHERE SUPPLIER = '" + BdParty.getText()
                    + "' AND INSTITUTE = '" + BdInstitute.getText() + "'";
            int supplierCheck = dontDeleteDB.getAIntDB(mySql, "SUPPLIER_LIST_ID");
            if (supplierCheck != 0) {
                //supplier entry

                BankTransactionDB bankTransactionDB = new BankTransactionDB();

                bankTransactionDB.setBankOrCash(BdBankCash.getText());
                bankTransactionDB.setAmount(IntegerTextField.doubleConvert(BdAmount.getText()));
                bankTransactionDB.setEntity(BdParty.getText());
                bankTransactionDB.setDate(dateTimeNow);
                //bankTransactionDB.setIdBankDepoWith(idBankDepoWith);
                bankTransactionDB.setRemarks(BdInstitute.getText());
                bankTransactionDB.setLp(BdLp.getText());
                bankTransactionDB.setType(2.0);
                bankTransactionDB.setUser(user);

                dontDeleteDB.insertBankTransaction(bankTransactionDB);

                String command = "SELECT BANK_TRANSACTION_ID FROM BANK_TRANSACTION ORDER BY BANK_TRANSACTION_ID DESC LIMIT 1";
                int idBankTrans = dontDeleteDB.getAIntDB(command, "BANK_TRANSACTION_ID");

                SupplierTransactionDB supplierTransactionDB = new SupplierTransactionDB();

                supplierTransactionDB.setBankOrCash(BdBankCash.getText());
                supplierTransactionDB.setCredit(IntegerTextField.doubleConvert(BdAmount.getText()));
                supplierTransactionDB.setSupplier(BdParty.getText());
                supplierTransactionDB.setDate(dateTimeNow);
                supplierTransactionDB.setIdBankDepoWith(idBankTrans);
                supplierTransactionDB.setInstitute(BdInstitute.getText());
                supplierTransactionDB.setLp(BdLp.getText());
                supplierTransactionDB.setType(1.0);
                supplierTransactionDB.setUser(user);

                dontDeleteDB.insertSupplierTransaction(supplierTransactionDB);

                String sql = "SELECT BALANCE_ORDER FROM SUPPLIER_LIST WHERE SUPPLIER_LIST_ID = '"
                        + supplierCheck + "'";
                double balanceOrder = dontDeleteDB.getADoubleDB(sql, "BALANCE_ORDER");
                sql = "SELECT BALANCE_DELIVERY FROM SUPPLIER_LIST WHERE SUPPLIER_LIST_ID = '"
                        + supplierCheck + "'";
                double balanceDelivery = dontDeleteDB.getADoubleDB(sql, "BALANCE_DELIVERY");
                sql = "SELECT PAYMENT FROM SUPPLIER_LIST WHERE SUPPLIER_LIST_ID = '"
                        + supplierCheck + "'";
                double payment = dontDeleteDB.getADoubleDB(sql, "PAYMENT");
                balanceOrder -= IntegerTextField.doubleConvert(BdAmount.getText());
                balanceDelivery -= IntegerTextField.doubleConvert(BdAmount.getText());
                payment -= IntegerTextField.doubleConvert(BdAmount.getText());

                String query = "update SUPPLIER_LIST set BALANCE_ORDER = ? , BALANCE_DELIVERY = ? , PAYMENT = ? "
                        + "where SUPPLIER_LIST_ID = ?";
                dontDeleteDB.set3DoubleDB(query, balanceOrder, balanceDelivery, payment, supplierCheck);

                sql = "SELECT BALANCE FROM BANK_LIST WHERE BANK_LIST_ID = '"
                        + createNew + "'";
                double balance = dontDeleteDB.getADoubleDB(sql, "BALANCE");

                balance += IntegerTextField.doubleConvert(BdAmount.getText());

                query = "update BANK_LIST set BALANCE = ? "
                        + "where BANK_LIST_ID = ?";
                dontDeleteDB.setADoubleDB(query, balance, createNew);

            }
        } else {
            //customer entry
            BankTransactionDB bankTransactionDB = new BankTransactionDB();

            bankTransactionDB.setBankOrCash(BdBankCash.getText());
            bankTransactionDB.setAmount(IntegerTextField.doubleConvert(BdAmount.getText()));
            bankTransactionDB.setEntity(BdParty.getText());
            bankTransactionDB.setDate(dateTimeNow);
            //bankTransactionDB.setIdBankDepoWith(idBankDepoWith);
            bankTransactionDB.setRemarks(BdInstitute.getText());
            bankTransactionDB.setLp(BdLp.getText());
            bankTransactionDB.setType(1.0);
            bankTransactionDB.setUser(user);

            dontDeleteDB.insertBankTransaction(bankTransactionDB);

            String command = "SELECT BANK_TRANSACTION_ID FROM BANK_TRANSACTION ORDER BY BANK_TRANSACTION_ID DESC LIMIT 1";
            int idBankTrans = dontDeleteDB.getAIntDB(command, "BANK_TRANSACTION_ID");

            CustomerTransactionDB customerTransactionDB = new CustomerTransactionDB();

            customerTransactionDB.setBank(BdBankCash.getText());
            customerTransactionDB.setCredit(IntegerTextField.doubleConvert(BdAmount.getText()));
            customerTransactionDB.setCustomer(BdParty.getText());
            customerTransactionDB.setDate(dateTimeNow);
            customerTransactionDB.setIdBankDepoWith(idBankTrans);
            customerTransactionDB.setInstitute(BdInstitute.getText());
            customerTransactionDB.setLp(BdLp.getText());
            customerTransactionDB.setType(1.0);
            customerTransactionDB.setUser(user);

            dontDeleteDB.insertCustomerTransaction(customerTransactionDB);

            String sql = "SELECT BALANCE_ORDER FROM CUSTOMER_LIST WHERE CUSTOMER_LIST_ID = '"
                    + customerCheck + "'";
            double balanceOrder = dontDeleteDB.getADoubleDB(sql, "BALANCE_ORDER");
            sql = "SELECT BALANCE_DELIVERY FROM CUSTOMER_LIST WHERE CUSTOMER_LIST_ID = '"
                    + customerCheck + "'";
            double balanceDelivery = dontDeleteDB.getADoubleDB(sql, "BALANCE_DELIVERY");
            sql = "SELECT PAYMENT FROM CUSTOMER_LIST WHERE CUSTOMER_LIST_ID = '"
                    + customerCheck + "'";
            double payment = dontDeleteDB.getADoubleDB(sql, "PAYMENT");
            balanceOrder -= IntegerTextField.doubleConvert(BdAmount.getText());
            balanceDelivery -= IntegerTextField.doubleConvert(BdAmount.getText());
            payment += IntegerTextField.doubleConvert(BdAmount.getText());

            String query = "update CUSTOMER_LIST set BALANCE_ORDER = ? , BALANCE_DELIVERY = ? , PAYMENT = ? "
                    + "where CUSTOMER_LIST_ID = ?";
            dontDeleteDB.set3DoubleDB(query, balanceOrder, balanceDelivery, payment, customerCheck);

            sql = "SELECT BALANCE FROM BANK_LIST WHERE BANK_LIST_ID = '"
                    + createNew + "'";
            double balance = dontDeleteDB.getADoubleDB(sql, "BALANCE");

            balance += IntegerTextField.doubleConvert(BdAmount.getText());

            query = "update BANK_LIST set BALANCE = ? "
                    + "where BANK_LIST_ID = ?";
            dontDeleteDB.setADoubleDB(query, balance, createNew);

        }
        //SHADMAN'S WORK ENDS

        BdParty.clear();
        BdAmount.clear();
        BdInstitute.clear();
        BdBankCash.clear();
        BdLp.clear();
        Database database = new Database();
        database.connect_database();

        BdParty.setEntries(database.getSuggestionList("SELECT * FROM CUSTOMER_LIST;", "CUSTOMER"));
        BdParty.setEntries(database.getSuggestionList("SELECT * FROM SUPPLIER_LIST;", "SUPPLIER"));
        BdInstitute.setEntries(database.getSuggestionList("SELECT * FROM CUSTOMER_LIST;", "INSTITUTE"));
        BdInstitute.setEntries(database.getSuggestionList("SELECT * FROM SUPPLIER_LIST;", "INSTITUTE"));
        BdBankCash.setEntries(database.getSuggestionList("SELECT * FROM BANK_LIST;", "BANK_NAME"));
        BwParty.setEntries(database.getSuggestionList("SELECT * FROM SUPPLIER_LIST;", "SUPPLIER"));
        BwParty.setEntries(database.getSuggestionList("SELECT * FROM CUSTOMER_LIST;", "CUSTOMER"));
        BwInstitute.setEntries(database.getSuggestionList("SELECT * FROM SUPPLIER_LIST;", "INSTITUTE"));
        BwInstitute.setEntries(database.getSuggestionList("SELECT * FROM CUSTOMER_LIST;", "INSTITUTE"));
        BwBankCash.setEntries(database.getSuggestionList("SELECT * FROM BANK_LIST;", "BANK_NAME"));
        EeBankCash.setEntries(database.getSuggestionList("SELECT * FROM BANK_LIST;", "BANK_NAME"));
        ReBankCash.setEntries(database.getSuggestionList("SELECT * FROM BANK_LIST;", "BANK_NAME"));

        BdTable.setItems(dontDeleteDB.getBankTransaction("SELECT * FROM BANK_TRANSACTION WHERE (TYPE=1.0 OR TYPE=2.0) AND date(DATE) = '" + date + "' ;"));

        database.close_database();
        dontDeleteDB.closeDatabase();

    }

    @FXML
    private void BankWithdrawalEntry(ActionEvent event) throws IOException, SQLException {
        if ("SAVE".equals(BwEntryButt.getText())) {
            BwDelete(EditBtOb);
            EditBtOb = null;
            BwEntryButt.setText("ENTRY");
            BwEditButt.setText("EDIT");
        }
        Timestamp dateTimeNow = new java.sql.Timestamp(new java.util.Date().getTime());

        DontDeleteDB dontDeleteDB = new DontDeleteDB();
        dontDeleteDB.connectDatabase();

        //BANK DEPOSIT NEW BANK CREATE
        String mySql = "SELECT BANK_LIST_ID FROM BANK_LIST WHERE BANK_NAME= '" + BwBankCash.getText() + "'";
        int createNew = dontDeleteDB.getAIntDB(mySql, "BANK_LIST_ID");
        if (createNew == 0) {

            newCreateEntitiySetText = "NEW BANK";
            bankName = BwBankCash.getText();
            //System.out.println(customer + "frontcontroller");

            newItem = bankName;
            Parent p = FXMLLoader.load(getClass().getResource("NewCreate.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(p);

            stage.setScene(scene);
            stage.setX(480);
            stage.setY(250);
            stage.showAndWait();
            bankName = null;
            if (newSet == false) {
                return;
            } else {
                newSet = false;
            }

        }
        createNew = dontDeleteDB.getAIntDB(mySql, "BANK_LIST_ID");

        //SHADMAN'S WORK
        //BANK DEPOSIT NEW CUSTOMER/NEW SUPPLIER CREATE
        mySql = "SELECT CUSTOMER_LIST_ID FROM CUSTOMER_LIST WHERE CUSTOMER= '" + BwParty.getText()
                + "'AND INSTITUTE= '" + BwInstitute.getText() + "'";
        int customerCheck = dontDeleteDB.getAIntDB(mySql, "CUSTOMER_LIST_ID");
        if (customerCheck == 0) {
            mySql = "SELECT SUPPLIER_LIST_ID FROM SUPPLIER_LIST WHERE SUPPLIER = '" + BwParty.getText()
                    + "' AND INSTITUTE = '" + BwInstitute.getText() + "'";
            int supplierCheck = dontDeleteDB.getAIntDB(mySql, "SUPPLIER_LIST_ID");
            System.out.println(supplierCheck + "customercheck");
            if (supplierCheck == 0) {

                newCreateEntitiySetText = "NEW PARTY";

                customer = BwParty.getText();
                supplier = BwParty.getText();
                institute = BwInstitute.getText();

                Parent p = FXMLLoader.load(getClass().getResource("BankNewPartyCreate.fxml"));
                Stage stage = new Stage();
                Scene scene = new Scene(p);

                stage.setScene(scene);
                stage.setX(480);
                stage.setY(250);
                stage.showAndWait();
                customer = null;
                supplier = null;
                institute = null;
                if (newSet == false) {
                    return;
                } else {
                    newSet = false;
                }

            }
        } //SHADMAN'S WORK

        mySql = "SELECT CUSTOMER_LIST_ID FROM CUSTOMER_LIST WHERE CUSTOMER= '" + BwParty.getText()
                + "'" + "AND INSTITUTE= '" + BwInstitute.getText() + "'";
        customerCheck = dontDeleteDB.getAIntDB(mySql, "CUSTOMER_LIST_ID");

        if (customerCheck == 0) {
            mySql = "SELECT SUPPLIER_LIST_ID FROM SUPPLIER_LIST WHERE SUPPLIER = '" + BwParty.getText()
                    + "' AND INSTITUTE = '" + BwInstitute.getText() + "'";
            int supplierCheck = dontDeleteDB.getAIntDB(mySql, "SUPPLIER_LIST_ID");
            if (supplierCheck != 0) {
                //supplier entry
                BankTransactionDB bankTransactionDB = new BankTransactionDB();

                bankTransactionDB.setBankOrCash(BwBankCash.getText());
                bankTransactionDB.setAmount((-1) * IntegerTextField.doubleConvert(BwAmount.getText()));
                bankTransactionDB.setEntity(BwParty.getText());
                bankTransactionDB.setDate(dateTimeNow);
                //bankTransactionDB.setIdBankDepoWith(idBankDepoWith);
                bankTransactionDB.setRemarks(BwInstitute.getText());
                bankTransactionDB.setLp(BwLp.getText());
                bankTransactionDB.setType(4.0);
                bankTransactionDB.setUser(user);

                dontDeleteDB.insertBankTransaction(bankTransactionDB);

                String command = "SELECT BANK_TRANSACTION_ID FROM BANK_TRANSACTION ORDER BY BANK_TRANSACTION_ID DESC LIMIT 1";
                int idBankTrans = dontDeleteDB.getAIntDB(command, "BANK_TRANSACTION_ID");

                SupplierTransactionDB supplierTransactionDB = new SupplierTransactionDB();

                supplierTransactionDB.setBankOrCash(BwBankCash.getText());
                supplierTransactionDB.setDebit(IntegerTextField.doubleConvert(BwAmount.getText()));
                supplierTransactionDB.setSupplier(BwParty.getText());
                supplierTransactionDB.setDate(dateTimeNow);
                supplierTransactionDB.setIdBankDepoWith(idBankTrans);
                supplierTransactionDB.setInstitute(BwInstitute.getText());
                supplierTransactionDB.setLp(BwLp.getText());
                supplierTransactionDB.setType(4.0);
                supplierTransactionDB.setUser(user);

                dontDeleteDB.insertSupplierTransaction(supplierTransactionDB);

                String sql = "SELECT BALANCE_ORDER FROM SUPPLIER_LIST WHERE SUPPLIER_LIST_ID = '"
                        + supplierCheck + "'";
                double balanceOrder = dontDeleteDB.getADoubleDB(sql, "BALANCE_ORDER");
                sql = "SELECT BALANCE_DELIVERY FROM SUPPLIER_LIST WHERE SUPPLIER_LIST_ID = '"
                        + supplierCheck + "'";
                double balanceDelivery = dontDeleteDB.getADoubleDB(sql, "BALANCE_DELIVERY");
                sql = "SELECT PAYMENT FROM SUPPLIER_LIST WHERE SUPPLIER_LIST_ID = '"
                        + supplierCheck + "'";
                double payment = dontDeleteDB.getADoubleDB(sql, "PAYMENT");
                balanceOrder += IntegerTextField.doubleConvert(BwAmount.getText());
                balanceDelivery += IntegerTextField.doubleConvert(BwAmount.getText());
                payment += IntegerTextField.doubleConvert(BwAmount.getText());

                String query = "update SUPPLIER_LIST set BALANCE_ORDER = ? , BALANCE_DELIVERY = ? , "
                        + "PAYMENT = ? " + "where SUPPLIER_LIST_ID = ?";
                dontDeleteDB.set3DoubleDB(query, balanceOrder, balanceDelivery, payment, supplierCheck);

                sql = "SELECT BALANCE FROM BANK_LIST WHERE BANK_LIST_ID = '"
                        + createNew + "'";
                double balance = dontDeleteDB.getADoubleDB(sql, "BALANCE");

                balance -= IntegerTextField.doubleConvert(BwAmount.getText());

                query = "update BANK_LIST set BALANCE = ? "
                        + "where BANK_LIST_ID = ?";
                dontDeleteDB.setADoubleDB(query, balance, createNew);

            }
        } else {
            //customer entry
            BankTransactionDB bankTransactionDB = new BankTransactionDB();

            bankTransactionDB.setBankOrCash(BwBankCash.getText());
            bankTransactionDB.setAmount((-1) * IntegerTextField.doubleConvert(BwAmount.getText()));
            bankTransactionDB.setEntity(BwParty.getText());
            bankTransactionDB.setDate(dateTimeNow);
            //bankTransactionDB.setIdBankDepoWith(idBankDepoWith);
            bankTransactionDB.setRemarks(BwInstitute.getText());
            bankTransactionDB.setLp(BwLp.getText());
            bankTransactionDB.setType(3.0);
            bankTransactionDB.setUser(user);

            dontDeleteDB.insertBankTransaction(bankTransactionDB);

            String command = "SELECT BANK_TRANSACTION_ID FROM BANK_TRANSACTION ORDER BY BANK_TRANSACTION_ID DESC LIMIT 1";
            int idBankTrans = dontDeleteDB.getAIntDB(command, "BANK_TRANSACTION_ID");

            CustomerTransactionDB customerTransactionDB = new CustomerTransactionDB();

            customerTransactionDB.setBank(BwBankCash.getText());
            customerTransactionDB.setDebit(IntegerTextField.doubleConvert(BwAmount.getText()));
            customerTransactionDB.setCustomer(BwParty.getText());
            customerTransactionDB.setDate(dateTimeNow);
            customerTransactionDB.setIdBankDepoWith(idBankTrans);
            customerTransactionDB.setInstitute(BwInstitute.getText());
            customerTransactionDB.setLp(BwLp.getText());
            customerTransactionDB.setType(3.0);
            customerTransactionDB.setUser(user);

            dontDeleteDB.insertCustomerTransaction(customerTransactionDB);

            String sql = "SELECT BALANCE_ORDER FROM CUSTOMER_LIST WHERE CUSTOMER_LIST_ID = '"
                    + customerCheck + "'";
            double balanceOrder = dontDeleteDB.getADoubleDB(sql, "BALANCE_ORDER");
            sql = "SELECT BALANCE_DELIVERY FROM CUSTOMER_LIST WHERE CUSTOMER_LIST_ID = '"
                    + customerCheck + "'";
            double balanceDelivery = dontDeleteDB.getADoubleDB(sql, "BALANCE_DELIVERY");
            sql = "SELECT PAYMENT FROM CUSTOMER_LIST WHERE CUSTOMER_LIST_ID = '"
                    + customerCheck + "'";
            double payment = dontDeleteDB.getADoubleDB(sql, "PAYMENT");
            balanceOrder += IntegerTextField.doubleConvert(BwAmount.getText());
            balanceDelivery += IntegerTextField.doubleConvert(BwAmount.getText());
            payment -= IntegerTextField.doubleConvert(BwAmount.getText());

            String query = "update CUSTOMER_LIST set BALANCE_ORDER = ? , BALANCE_DELIVERY = ? , PAYMENT = ? "
                    + "where CUSTOMER_LIST_ID = ?";
            dontDeleteDB.set3DoubleDB(query, balanceOrder, balanceDelivery, payment, customerCheck);

            sql = "SELECT BALANCE FROM BANK_LIST WHERE BANK_LIST_ID = '"
                    + createNew + "'";
            double balance = dontDeleteDB.getADoubleDB(sql, "BALANCE");

            balance -= IntegerTextField.doubleConvert(BwAmount.getText());

            query = "update BANK_LIST set BALANCE = ? "
                    + "where BANK_LIST_ID = ?";
            dontDeleteDB.setADoubleDB(query, balance, createNew);

        }
        //SHADMAN'S WORK ENDS

        BwParty.clear();
        BwAmount.clear();
        BwInstitute.clear();
        BwBankCash.clear();
        BwLp.clear();
        Database database = new Database();
        database.connect_database();

        BdParty.setEntries(database.getSuggestionList("SELECT * FROM CUSTOMER_LIST;", "CUSTOMER"));
        BdParty.setEntries(database.getSuggestionList("SELECT * FROM SUPPLIER_LIST;", "SUPPLIER"));
        BdInstitute.setEntries(database.getSuggestionList("SELECT * FROM CUSTOMER_LIST;", "INSTITUTE"));
        BdInstitute.setEntries(database.getSuggestionList("SELECT * FROM SUPPLIER_LIST;", "INSTITUTE"));
        BdBankCash.setEntries(database.getSuggestionList("SELECT * FROM BANK_LIST;", "BANK_NAME"));
        BwParty.setEntries(database.getSuggestionList("SELECT * FROM SUPPLIER_LIST;", "SUPPLIER"));
        BwParty.setEntries(database.getSuggestionList("SELECT * FROM CUSTOMER_LIST;", "CUSTOMER"));
        BwInstitute.setEntries(database.getSuggestionList("SELECT * FROM SUPPLIER_LIST;", "INSTITUTE"));
        BwInstitute.setEntries(database.getSuggestionList("SELECT * FROM CUSTOMER_LIST;", "INSTITUTE"));
        BwBankCash.setEntries(database.getSuggestionList("SELECT * FROM BANK_LIST;", "BANK_NAME"));
        EeBankCash.setEntries(database.getSuggestionList("SELECT * FROM BANK_LIST;", "BANK_NAME"));
        ReBankCash.setEntries(database.getSuggestionList("SELECT * FROM BANK_LIST;", "BANK_NAME"));

        BwTable.setItems(dontDeleteDB.getBankTransaction("SELECT * FROM BANK_TRANSACTION WHERE (TYPE=3.0 OR TYPE=4.0) AND date(DATE) = '" + date + "' ;"));

        database.close_database();
        dontDeleteDB.closeDatabase();
    }

    @FXML
    private void PoEntryClick(ActionEvent event) throws IOException {
        Parent p = FXMLLoader.load(getClass().getResource("PurchaseOrder.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(p);
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(
                ((Node) event.getSource()).getScene().getWindow());
        stage.setScene(scene);
        stage.setResizable(false);
        stage.showAndWait();
        DontDeleteDB dontDeleteDB = new DontDeleteDB();
        dontDeleteDB.connectDatabase();
        PoTable.setItems(dontDeleteDB.getPurchaseOrder("SELECT * FROM PURCHASE_ORDER WHERE date(DATE) = '" + date + "' ;"));
        dontDeleteDB.closeDatabase();

    }

    @FXML
    private void SdEntryButtClick(ActionEvent event) throws IOException {
        Parent p = FXMLLoader.load(getClass().getResource("SaleDelivery.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(p);
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(
                ((Node) event.getSource()).getScene().getWindow());
        stage.setScene(scene);
        stage.setResizable(false);
        stage.showAndWait();

        DontDeleteDB dontDeleteDB = new DontDeleteDB();
        dontDeleteDB.connectDatabase();
        SdTable.setItems(dontDeleteDB.getSaleDelivery("SELECT * FROM SALE_DELIVERY WHERE date(DATE) = '" + date + "' ; "));
        dontDeleteDB.closeDatabase();

    }

    @FXML
    private void SoEntryButtClick(ActionEvent event) throws IOException {
        Parent p = FXMLLoader.load(getClass().getResource("SaleOrder.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(p);
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(
                ((Node) event.getSource()).getScene().getWindow());
        stage.setScene(scene);
        stage.setResizable(false);
        stage.showAndWait();
        DontDeleteDB dontDeleteDB = new DontDeleteDB();
        dontDeleteDB.connectDatabase();
        SoTable.setItems(dontDeleteDB.getSaleOrder("SELECT * FROM SALE_ORDER WHERE date(DATE) = '" + date + "' ; "));
        dontDeleteDB.closeDatabase();

    }

    @FXML
    private void PdEntryButtClick(ActionEvent event) throws IOException {
        Parent p = FXMLLoader.load(getClass().getResource("PurchaseDelivery.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(p);
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(
                ((Node) event.getSource()).getScene().getWindow());
        stage.setScene(scene);
        stage.setResizable(false);
        stage.showAndWait();
        DontDeleteDB dontDeleteDB = new DontDeleteDB();
        dontDeleteDB.connectDatabase();
        PdTable.setItems(dontDeleteDB.getPurchaseDelivery("SELECT * FROM PURCHASE_DELIVERY WHERE date(DATE) = '" + date + "' ;"));

        dontDeleteDB.closeDatabase();

    }

    @FXML
    private void EnterExpenseInfoButtClick(ActionEvent event) throws SQLException {
        if ("SAVE".equals(EeEntryButt.getText())) {
            EeDelete(EditEeOb);
            EditBtOb = null;
            EeEntryButt.setText("ENTRY");
            EeEditButt.setText("EDIT");
        }
        Timestamp dateTimeNow = new java.sql.Timestamp(new java.util.Date().getTime());

        DontDeleteDB dontDeleteDB = new DontDeleteDB();
        dontDeleteDB.connectDatabase();

        //insert into expenses table
        ExpenseDB expenseDB = new ExpenseDB();
        expenseDB.setAmount(IntegerTextField.doubleConvert(EeAmount.getText()));
        expenseDB.setBankOrCash(EeBankCash.getText());
        expenseDB.setDate(dateTimeNow);
        expenseDB.setExpense(EeExpense.getText());
        expenseDB.setLp(EeLp.getText());
        expenseDB.setRemarks(EeRemarks.getText());
        expenseDB.setUser(user);

        dontDeleteDB.insertExpense(expenseDB);
        //insert into expenses table ends here

        //updating banklist
        String sql = "SELECT BALANCE FROM BANK_LIST WHERE BANK_NAME= '" + EeBankCash.getText() + "'";
        double bankBalance = dontDeleteDB.getADoubleDB(sql, "BALANCE");
        bankBalance -= IntegerTextField.doubleConvert(EeAmount.getText());
        String query = "update BANK_LIST set BALANCE = ? where BANK_NAME = ?";
        dontDeleteDB.setADoubleDB(query, bankBalance, EeBankCash.getText());
        //updating banklist ends here

        // getting idExpense foreign key
        String command = "SELECT EXPENSE_ID FROM EXPENSE ORDER BY EXPENSE_ID DESC LIMIT 1";
        int idExpense = dontDeleteDB.getAIntDB(command, "EXPENSE_ID");

        //insering into bank transaction
        BankTransactionDB bankTransactionDB = new BankTransactionDB();
        bankTransactionDB.setAmount(-(IntegerTextField.doubleConvert(EeAmount.getText())));
        bankTransactionDB.setBankOrCash(EeBankCash.getText());
        bankTransactionDB.setDate(dateTimeNow);
        bankTransactionDB.setEntity(EeExpense.getText());
        bankTransactionDB.setIdExpense(idExpense);
        bankTransactionDB.setLp(EeLp.getText());
        bankTransactionDB.setRemarks(EeRemarks.getText());
        bankTransactionDB.setType(5.0);
        bankTransactionDB.setUser(user);

        dontDeleteDB.insertBankTransaction(bankTransactionDB);

        EeExpense.clear();
        EeRemarks.clear();
        EeAmount.clear();
        EeBankCash.clear();
        EeLp.clear();

        EeTable.setItems(dontDeleteDB.getExpense("SELECT * FROM EXPENSE WHERE date(DATE) = '" + date + "' ;"));

        //inserting banktransaction ends here
        dontDeleteDB.closeDatabase();
    }

    @FXML
    private void EnterRevenueInfoButtClick(ActionEvent event) throws SQLException {
        if ("SAVE".equals(ReEntryButt.getText())) {
            ReDelete(EditReOb);
            EditReOb = null;
            ReEntryButt.setText("ENTRY");
            ReEditButt.setText("EDIT");

        }
        DontDeleteDB dontDeleteDB = new DontDeleteDB();
        Timestamp dateTimeNow = new java.sql.Timestamp(new java.util.Date().getTime());

        dontDeleteDB.connectDatabase();

        //insert into revenue table
        RevenueDB revenueDB = new RevenueDB();
        revenueDB.setAmount(IntegerTextField.doubleConvert(ReAmount.getText()));
        revenueDB.setBankOrCash(ReBankCash.getText());
        revenueDB.setDate(dateTimeNow);
        revenueDB.setRevenue(ReRevenue.getText());
        revenueDB.setLp(ReLp.getText());
        revenueDB.setRemarks(ReRemarks.getText());
        revenueDB.setUser(user);

        dontDeleteDB.insertRevenue(revenueDB);
        //insert into revenue table ends here

        //updating banklist
        String sql = "SELECT BALANCE FROM BANK_LIST WHERE BANK_NAME= '" + ReBankCash.getText() + "'";
        double bankBalance = dontDeleteDB.getADoubleDB(sql, "BALANCE");
        bankBalance += IntegerTextField.doubleConvert(ReAmount.getText());
        String query = "update BANK_LIST set BALANCE = ? where BANK_NAME = ?";
        dontDeleteDB.setADoubleDB(query, bankBalance, ReBankCash.getText());
        //updating banklist ends here

        // getting idRevenue foreign key
        String command = "SELECT REVENUE_ID FROM REVENUE ORDER BY REVENUE_ID DESC LIMIT 1";
        int idRevenue = dontDeleteDB.getAIntDB(command, "REVENUE_ID");

        //insering into bank transaction
        BankTransactionDB bankTransactionDB = new BankTransactionDB();
        bankTransactionDB.setAmount(IntegerTextField.doubleConvert(ReAmount.getText()));
        bankTransactionDB.setBalance(bankBalance);

        bankTransactionDB.setBankOrCash(ReBankCash.getText());
        bankTransactionDB.setDate(dateTimeNow);
        bankTransactionDB.setEntity(ReRevenue.getText());
        bankTransactionDB.setIdRevenue(idRevenue);
        bankTransactionDB.setLp(ReLp.getText());
        bankTransactionDB.setRemarks(ReRemarks.getText());
        bankTransactionDB.setType(6.0);
        bankTransactionDB.setUser(user);

        dontDeleteDB.insertBankTransaction(bankTransactionDB);

        ReRevenue.clear();
        ReRemarks.clear();
        ReAmount.clear();
        ReBankCash.clear();
        ReLp.clear();

        ReTable.setItems(dontDeleteDB.getRevenue("SELECT * FROM REVENUE WHERE date(DATE) = '" + date + "' ;"));

        //inserting banktransaction ends here
        dontDeleteDB.closeDatabase();

    }

    @FXML
    private void BdDeleteButtClick(ActionEvent event) throws IOException, SQLException {

        BankTransactionDB BtOb = BdTable.getSelectionModel().getSelectedItem();
        if (BtOb == null) {
            return;
        }
        Parent p = FXMLLoader.load(getClass().getResource("DeleteSure.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(p);
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(
                ((Node) event.getSource()).getScene().getWindow());
        stage.setScene(scene);
        stage.showAndWait();

        if (newSet == true) {
            BdDeleteEntry(BtOb);
            newSet = false;
        }
        DontDeleteDB dontDeleteDB = new DontDeleteDB();

        dontDeleteDB.connectDatabase();

        BdTable.setItems(dontDeleteDB.getBankTransaction("SELECT * FROM BANK_TRANSACTION WHERE (TYPE=1.0 OR TYPE=2.0) AND date(DATE) = '" + date + "' ;"));

        dontDeleteDB.closeDatabase();

    }

    private void BdDeleteEntry(BankTransactionDB ob) throws SQLException {
        DontDeleteDB dontDeleteDB = new DontDeleteDB();
        dontDeleteDB.connectDatabase();

        Timestamp date = new java.sql.Timestamp(new java.util.Date().getTime());

        String query = "DELETE FROM BANK_TRANSACTION WHERE BANK_TRANSACTION_ID = '" + ob.getId() + "'";
        dontDeleteDB.delete(query);
        if (ob.getType() == 1.0) {
            query = "DELETE FROM CUSTOMER_TRANSACTION WHERE ID_BANK_DEPO_WITH = '" + ob.getId() + "'";
            dontDeleteDB.delete(query);

            String mySql = "SELECT CUSTOMER_LIST_ID FROM CUSTOMER_LIST WHERE CUSTOMER= '" + ob.getEntity()
                    + "'" + "AND INSTITUTE= '" + ob.getRemarks() + "'";
            int customerCheck = dontDeleteDB.getAIntDB(mySql, "CUSTOMER_LIST_ID");

            String sql = "SELECT BALANCE_ORDER FROM CUSTOMER_LIST WHERE CUSTOMER_LIST_ID = '"
                    + customerCheck + "'";
            double balanceOrder = dontDeleteDB.getADoubleDB(sql, "BALANCE_ORDER");
            sql = "SELECT BALANCE_DELIVERY FROM CUSTOMER_LIST WHERE CUSTOMER_LIST_ID = '"
                    + customerCheck + "'";
            double balanceDelivery = dontDeleteDB.getADoubleDB(sql, "BALANCE_DELIVERY");
            sql = "SELECT PAYMENT FROM CUSTOMER_LIST WHERE CUSTOMER_LIST_ID = '"
                    + customerCheck + "'";
            double payment = dontDeleteDB.getADoubleDB(sql, "PAYMENT");

            balanceOrder += ob.getAmount();
            balanceDelivery += ob.getAmount();
            payment -= ob.getAmount();

            query = "update CUSTOMER_LIST set BALANCE_ORDER = ? , BALANCE_DELIVERY = ? , PAYMENT = ? "
                    + "where CUSTOMER_LIST_ID = ?";
            dontDeleteDB.set3DoubleDB(query, balanceOrder, balanceDelivery, payment, customerCheck);

            mySql = "SELECT BANK_LIST_ID FROM BANK_LIST WHERE BANK_NAME= '" + ob.getBankOrCash() + "'";
            int createNew = dontDeleteDB.getAIntDB(mySql, "BANK_LIST_ID");

            sql = "SELECT BALANCE FROM BANK_LIST WHERE BANK_LIST_ID = '"
                    + createNew + "'";
            double balance = dontDeleteDB.getADoubleDB(sql, "BALANCE");

            balance -= ob.getAmount();

            query = "update BANK_LIST set BALANCE = ? "
                    + "where BANK_LIST_ID = ?";
            dontDeleteDB.setADoubleDB(query, balance, createNew);

        } else if (ob.getType() == 2.0) {
            query = "DELETE FROM SUPPLIER_TRANSACTION WHERE ID_BANK_DEPO_WITH = '" + ob.getId() + "'";
            dontDeleteDB.delete(query);

            String mySql = "SELECT SUPPLIER_LIST_ID FROM SUPPLIER_LIST WHERE SUPPLIER = '"
                    + ob.getEntity() + "' AND INSTITUTE = '" + ob.getRemarks() + "'";
            int supplierCheck = dontDeleteDB.getAIntDB(mySql, "SUPPLIER_LIST_ID");

            String sql = "SELECT BALANCE_ORDER FROM SUPPLIER_LIST WHERE SUPPLIER_LIST_ID = '"
                    + supplierCheck + "'";
            double balanceOrder = dontDeleteDB.getADoubleDB(sql, "BALANCE_ORDER");
            sql = "SELECT BALANCE_DELIVERY FROM SUPPLIER_LIST WHERE SUPPLIER_LIST_ID = '"
                    + supplierCheck + "'";
            double balanceDelivery = dontDeleteDB.getADoubleDB(sql, "BALANCE_DELIVERY");
            sql = "SELECT PAYMENT FROM SUPPLIER_LIST WHERE SUPPLIER_LIST_ID = '"
                    + supplierCheck + "'";
            double payment = dontDeleteDB.getADoubleDB(sql, "PAYMENT");

            balanceOrder += ob.getAmount();
            balanceDelivery += ob.getAmount();
            payment += ob.getAmount();

            query = "update SUPPLIER_LIST set BALANCE_ORDER = ? , BALANCE_DELIVERY = ? , PAYMENT = ? "
                    + "where SUPPLIER_LIST_ID = ?";
            dontDeleteDB.set3DoubleDB(query, balanceOrder, balanceDelivery, payment, supplierCheck);

            mySql = "SELECT BANK_LIST_ID FROM BANK_LIST WHERE BANK_NAME= '" + ob.getBankOrCash() + "'";
            int createNew = dontDeleteDB.getAIntDB(mySql, "BANK_LIST_ID");

            sql = "SELECT BALANCE FROM BANK_LIST WHERE BANK_LIST_ID = '"
                    + createNew + "'";
            double balance = dontDeleteDB.getADoubleDB(sql, "BALANCE");

            balance -= ob.getAmount();

            query = "update BANK_LIST set BALANCE = ? "
                    + "where BANK_LIST_ID = ?";
            dontDeleteDB.setADoubleDB(query, balance, createNew);
        }

    }

    @FXML
    private void BdEditButtClick(ActionEvent event) throws IOException {
        if ("CANCEL".equals(BdEditButt.getText())) {
            BdParty.clear();
            BdAmount.clear();
            BdInstitute.clear();
            BdBankCash.clear();
            BdLp.clear();
            BdEntryButt.setText("ENTRY");
            BdEditButt.setText("EDIT");

        } else if ("EDIT".equals(BdEditButt.getText())) {
            BankTransactionDB BtOb = BdTable.getSelectionModel().getSelectedItem();
            if (BtOb == null) {
                return;
            } else {
                EditBtOb = BtOb;
            }
            BdEditButt.setText("CANCEL");
            BdEntryButt.setText("SAVE");
            BdParty.setText(BtOb.getEntity());
            BdInstitute.setText(BtOb.getRemarks());
            BdBankCash.setText(BtOb.getBankOrCash());

            BdAmount.setText(IntegerTextField.numformat(BtOb.getAmount()));
            BdLp.setText(BtOb.getLp());

        }

    }

    @FXML
    private void BwDeleteButtClick(ActionEvent event) throws IOException, SQLException {

        BankTransactionDB BtOb = BwTable.getSelectionModel().getSelectedItem();
        if (BtOb == null) {
            return;
        }
        Parent p = FXMLLoader.load(getClass().getResource("DeleteSure.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(p);
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(
                ((Node) event.getSource()).getScene().getWindow());
        stage.setScene(scene);
        stage.showAndWait();

        if (newSet == true) {
            BwDelete(BtOb);
            newSet = false;
        }

        DontDeleteDB dontDeleteDB = new DontDeleteDB();

        dontDeleteDB.connectDatabase();

        BwTable.setItems(dontDeleteDB.getBankTransaction("SELECT * FROM BANK_TRANSACTION WHERE (TYPE=3.0 OR TYPE=4.0) AND date(DATE) = '" + date + "' ;"));

        dontDeleteDB.closeDatabase();

    }

    private void BwDelete(BankTransactionDB ob) throws SQLException {
        DontDeleteDB dontDeleteDB = new DontDeleteDB();
        dontDeleteDB.connectDatabase();

        Timestamp date = new java.sql.Timestamp(new java.util.Date().getTime());

        String query = "DELETE FROM BANK_TRANSACTION WHERE BANK_TRANSACTION_ID = '" + ob.getId() + "'";
        dontDeleteDB.delete(query);
        if (ob.getType() == 3.0) {
            query = "DELETE FROM CUSTOMER_TRANSACTION WHERE ID_BANK_DEPO_WITH = '" + ob.getId() + "'";
            dontDeleteDB.delete(query);

            String mySql = "SELECT CUSTOMER_LIST_ID FROM CUSTOMER_LIST WHERE CUSTOMER= '" + ob.getEntity()
                    + "'" + "AND INSTITUTE= '" + ob.getRemarks() + "'";
            int customerCheck = dontDeleteDB.getAIntDB(mySql, "CUSTOMER_LIST_ID");

            String sql = "SELECT BALANCE_ORDER FROM CUSTOMER_LIST WHERE CUSTOMER_LIST_ID = '"
                    + customerCheck + "'";
            double balanceOrder = dontDeleteDB.getADoubleDB(sql, "BALANCE_ORDER");
            sql = "SELECT BALANCE_DELIVERY FROM CUSTOMER_LIST WHERE CUSTOMER_LIST_ID = '"
                    + customerCheck + "'";
            double balanceDelivery = dontDeleteDB.getADoubleDB(sql, "BALANCE_DELIVERY");
            sql = "SELECT PAYMENT FROM CUSTOMER_LIST WHERE CUSTOMER_LIST_ID = '"
                    + customerCheck + "'";
            double payment = dontDeleteDB.getADoubleDB(sql, "PAYMENT");

            balanceOrder -= -(ob.getAmount());
            balanceDelivery -= -(ob.getAmount());
            payment += -(ob.getAmount());

            query = "update CUSTOMER_LIST set BALANCE_ORDER = ? , BALANCE_DELIVERY = ? , PAYMENT = ? "
                    + "where CUSTOMER_LIST_ID = ?";
            dontDeleteDB.set3DoubleDB(query, balanceOrder, balanceDelivery, payment, customerCheck);

            mySql = "SELECT BANK_LIST_ID FROM BANK_LIST WHERE BANK_NAME= '" + ob.getBankOrCash() + "'";
            int createNew = dontDeleteDB.getAIntDB(mySql, "BANK_LIST_ID");

            sql = "SELECT BALANCE FROM BANK_LIST WHERE BANK_LIST_ID = '"
                    + createNew + "'";
            double balance = dontDeleteDB.getADoubleDB(sql, "BALANCE");

            balance += -ob.getAmount();

            query = "update BANK_LIST set BALANCE = ? "
                    + "where BANK_LIST_ID = ?";
            dontDeleteDB.setADoubleDB(query, balance, createNew);

        } else if (ob.getType() == 4.0) {
            query = "DELETE FROM SUPPLIER_TRANSACTION WHERE ID_BANK_DEPO_WITH = '" + ob.getId() + "'";
            dontDeleteDB.delete(query);

            String mySql = "SELECT SUPPLIER_LIST_ID FROM SUPPLIER_LIST WHERE SUPPLIER = '"
                    + ob.getEntity() + "' AND INSTITUTE = '" + ob.getRemarks() + "'";
            int supplierCheck = dontDeleteDB.getAIntDB(mySql, "SUPPLIER_LIST_ID");

            String sql = "SELECT BALANCE_ORDER FROM SUPPLIER_LIST WHERE SUPPLIER_LIST_ID = '"
                    + supplierCheck + "'";
            double balanceOrder = dontDeleteDB.getADoubleDB(sql, "BALANCE_ORDER");
            sql = "SELECT BALANCE_DELIVERY FROM SUPPLIER_LIST WHERE SUPPLIER_LIST_ID = '"
                    + supplierCheck + "'";
            double balanceDelivery = dontDeleteDB.getADoubleDB(sql, "BALANCE_DELIVERY");
            sql = "SELECT PAYMENT FROM SUPPLIER_LIST WHERE SUPPLIER_LIST_ID = '"
                    + supplierCheck + "'";
            double payment = dontDeleteDB.getADoubleDB(sql, "PAYMENT");

            balanceOrder -= -ob.getAmount();
            balanceDelivery -= -ob.getAmount();
            payment -= -ob.getAmount();

            query = "update SUPPLIER_LIST set BALANCE_ORDER = ? , BALANCE_DELIVERY = ? , PAYMENT = ? "
                    + "where SUPPLIER_LIST_ID = ?";
            dontDeleteDB.set3DoubleDB(query, balanceOrder, balanceDelivery, payment, supplierCheck);

            mySql = "SELECT BANK_LIST_ID FROM BANK_LIST WHERE BANK_NAME= '" + ob.getBankOrCash() + "'";
            int createNew = dontDeleteDB.getAIntDB(mySql, "BANK_LIST_ID");

            sql = "SELECT BALANCE FROM BANK_LIST WHERE BANK_LIST_ID = '"
                    + createNew + "'";
            double balance = dontDeleteDB.getADoubleDB(sql, "BALANCE");

            balance += -ob.getAmount();

            query = "update BANK_LIST set BALANCE = ? "
                    + "where BANK_LIST_ID = ?";
            dontDeleteDB.setADoubleDB(query, balance, createNew);
        }
    }

    @FXML
    private void BwEditButtClick(ActionEvent event) {
        if ("CANCEL".equals(BwEditButt.getText())) {
            BwParty.clear();
            BwAmount.clear();
            BwInstitute.clear();
            BwBankCash.clear();
            BwLp.clear();
            BwEntryButt.setText("ENTRY");
            BwEditButt.setText("EDIT");

        } else if ("EDIT".equals(BwEditButt.getText())) {
            BankTransactionDB BtOb = BwTable.getSelectionModel().getSelectedItem();
            if (BtOb == null) {
                return;
            } else {
                EditBtOb = BtOb;
            }
            BwEditButt.setText("CANCEL");
            BwEntryButt.setText("SAVE");
            BwParty.setText(BtOb.getEntity());
            BwInstitute.setText(BtOb.getRemarks());
            BwBankCash.setText(BtOb.getBankOrCash());
            BwAmount.setText(IntegerTextField.numformat(-BtOb.getAmount()));
            BwLp.setText(BtOb.getLp());

        }
    }

    @FXML
    private void EeDeleteButtClick(ActionEvent event) throws IOException, SQLException {
        ExpenseDB EeOb = EeTable.getSelectionModel().getSelectedItem();
        if (EeOb == null) {
            return;
        }
        Parent p = FXMLLoader.load(getClass().getResource("DeleteSure.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(p);
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(
                ((Node) event.getSource()).getScene().getWindow());
        stage.setScene(scene);
        stage.showAndWait();

        if (newSet == true) {
            EeDelete(EeOb);
            newSet = false;
        }

        DontDeleteDB dontDeleteDB = new DontDeleteDB();

        dontDeleteDB.connectDatabase();

        EeTable.setItems(dontDeleteDB.getExpense("SELECT * FROM EXPENSE WHERE date(DATE) = '" + date + "' ;"));

        dontDeleteDB.closeDatabase();

    }

    private void EeDelete(ExpenseDB ob) throws SQLException {

        DontDeleteDB dontDeleteDB = new DontDeleteDB();
        dontDeleteDB.connectDatabase();

        String query = "DELETE FROM EXPENSE WHERE EXPENSE_ID = '" + ob.getId() + "'";
        dontDeleteDB.delete(query);

        //updating banklist
        String sql = "SELECT BALANCE FROM BANK_LIST WHERE BANK_NAME= '" + ob.getBankOrCash() + "'";
        double bankBalance = dontDeleteDB.getADoubleDB(sql, "BALANCE");
        bankBalance += ob.getAmount();
        query = "update BANK_LIST set BALANCE = ? where BANK_NAME = ?";
        dontDeleteDB.setADoubleDB(query, bankBalance, ob.getBankOrCash());
        //updating banklist ends here

        String command = "DELETE FROM BANK_TRANSACTION WHERE TYPE= '" + 5.0
                + "'AND ID_EXPENSE= '" + ob.getId() + "';";
        dontDeleteDB.delete(command);

        dontDeleteDB.closeDatabase();
    }

    @FXML
    private void EeEditButtClick(ActionEvent event) {
        if ("CANCEL".equals(EeEditButt.getText())) {
            EeExpense.clear();
            EeAmount.clear();
            EeRemarks.clear();
            EeBankCash.clear();
            EeLp.clear();
            EeEntryButt.setText("ENTRY");
            EeEditButt.setText("EDIT");

        } else if ("EDIT".equals(EeEditButt.getText())) {
            ExpenseDB EeOb = EeTable.getSelectionModel().getSelectedItem();
            if (EeOb == null) {
                return;
            } else {
                EditEeOb = EeOb;
            }
            EeEditButt.setText("CANCEL");
            EeEntryButt.setText("SAVE");
            EeExpense.setText(EeOb.getExpense());
            EeRemarks.setText(EeOb.getRemarks());
            EeBankCash.setText(EeOb.getBankOrCash());
            EeAmount.setText(EeOb.getAmount2());
            EeLp.setText(EeOb.getLp());

        }
    }

    @FXML
    private void ReDeleteButtClick(ActionEvent event) throws IOException, SQLException {
        RevenueDB ReOb = ReTable.getSelectionModel().getSelectedItem();
        if (ReOb == null) {
            return;
        }
        Parent p = FXMLLoader.load(getClass().getResource("DeleteSure.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(p);
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(
                ((Node) event.getSource()).getScene().getWindow());
        stage.setScene(scene);
        stage.showAndWait();

        if (newSet == true) {
            ReDelete(ReOb);
            newSet = false;
        }

        DontDeleteDB dontDeleteDB = new DontDeleteDB();

        dontDeleteDB.connectDatabase();

        ReTable.setItems(dontDeleteDB.getRevenue("SELECT * FROM REVENUE WHERE date(DATE) = '" + date + "' ;"));

        dontDeleteDB.closeDatabase();
    }

    private void ReDelete(RevenueDB ob) throws SQLException {

        DontDeleteDB dontDeleteDB = new DontDeleteDB();
        dontDeleteDB.connectDatabase();

        String query = "DELETE FROM REVENUE WHERE REVENUE_ID = '" + ob.getId() + "'";
        dontDeleteDB.delete(query);

        //updating banklist
        String sql = "SELECT BALANCE FROM BANK_LIST WHERE BANK_NAME= '" + ob.getBankOrCash() + "'";
        double bankBalance = dontDeleteDB.getADoubleDB(sql, "BALANCE");
        bankBalance -= ob.getAmount();
        query = "update BANK_LIST set BALANCE = ? where BANK_NAME = ?";
        dontDeleteDB.setADoubleDB(query, bankBalance, ob.getBankOrCash());
        //updating banklist ends here

        String command = "DELETE FROM BANK_TRANSACTION WHERE TYPE= '" + 6.0
                + "'AND ID_REVENUE= '" + ob.getId() + "';";
        dontDeleteDB.delete(command);

        dontDeleteDB.closeDatabase();

    }

    @FXML
    private void ReEditButtClick(ActionEvent event) {
        if ("CANCEL".equals(ReEditButt.getText())) {
            ReRevenue.clear();
            ReAmount.clear();
            ReRemarks.clear();
            ReBankCash.clear();
            ReLp.clear();
            ReEntryButt.setText("ENTRY");
            ReEditButt.setText("EDIT");

        }
        if ("EDIT".equals(ReEditButt.getText())) {
            RevenueDB ReOb = ReTable.getSelectionModel().getSelectedItem();
            if (ReOb == null) {
                return;
            } else {
                EditReOb = ReOb;
            }
            ReEditButt.setText("CANCEL");
            ReEntryButt.setText("SAVE");
            ReRevenue.setText(ReOb.getRevenue());
            ReRemarks.setText(ReOb.getRemarks());
            ReBankCash.setText(ReOb.getBankOrCash());
            ReAmount.setText(ReOb.getAmount2());
            ReLp.setText(ReOb.getLp());

        }
    }

    @FXML
    private void PoViewButtClick(ActionEvent event) throws IOException {
        PurchaseOrderDB PoOb = PoTable.getSelectionModel().getSelectedItem();
        if (PoOb == null) {
            return;
        } else {
            PoOrderNo = PoOb.getOrderNo();
            System.out.println(PoOrderNo);
            Parent p = FXMLLoader.load(getClass().getResource("PurchaseOrder.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(p);
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(
                    ((Node) event.getSource()).getScene().getWindow());
            stage.setScene(scene);
            stage.setResizable(false);
            stage.showAndWait();
            DontDeleteDB dontDeleteDB = new DontDeleteDB();
            dontDeleteDB.connectDatabase();
            PoTable.setItems(dontDeleteDB.getPurchaseOrder("SELECT * FROM PURCHASE_ORDER WHERE date(DATE) = '" + date + "' ;"));
            dontDeleteDB.closeDatabase();
        }
    }

    @FXML
    private void SoViewButtClick(ActionEvent event) throws IOException {
        SaleOrderDB SoOb = SoTable.getSelectionModel().getSelectedItem();
        if (SoOb == null) {
        } else {
            SoOrderNo = SoOb.getInvoiceNo();
            System.out.println(SoOrderNo);
            Parent p = FXMLLoader.load(getClass().getResource("SaleOrder.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(p);
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(
                    ((Node) event.getSource()).getScene().getWindow());
            stage.setScene(scene);
            stage.setResizable(false);
            stage.showAndWait();
            DontDeleteDB dontDeleteDB = new DontDeleteDB();
            dontDeleteDB.connectDatabase();
            SoTable.setItems(dontDeleteDB.getSaleOrder("SELECT * FROM SALE_ORDER WHERE date(DATE) = '" + date + "' ;"));
            dontDeleteDB.closeDatabase();
        }
        SoOrderNo = null;
    }

    @FXML
    private void PdViewButtClick(ActionEvent event) throws IOException {
        PurchaseDeliveryDB PdOb = PdTable.getSelectionModel().getSelectedItem();
        if (PdOb == null) {
            return;
        } else {
            PdDeliveryNo = PdOb.getDeliveryNo();
            //System.out.println(SoOrderNo);
            Parent p = FXMLLoader.load(getClass().getResource("PurchaseDelivery.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(p);
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(
                    ((Node) event.getSource()).getScene().getWindow());
            stage.setScene(scene);
            stage.setResizable(false);
            stage.showAndWait();
            DontDeleteDB dontDeleteDB = new DontDeleteDB();
            dontDeleteDB.connectDatabase();
            PdTable.setItems(dontDeleteDB.getPurchaseDelivery("SELECT * FROM PURCHASE_DELIVERY WHERE date(DATE) = '" + date + "' ;"));
            dontDeleteDB.closeDatabase();
        }
    }

    @FXML
    private void SdViewButtClick(ActionEvent event) throws IOException {
        SaleDeliveryDB SdOb = SdTable.getSelectionModel().getSelectedItem();
        if (SdOb == null) {
            return;
        } else {
            SdBillNo = SdOb.getBillNo();
            //System.out.println(SoOrderNo);
            Parent p = FXMLLoader.load(getClass().getResource("SaleDelivery.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(p);
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(
                    ((Node) event.getSource()).getScene().getWindow());
            stage.setScene(scene);
            stage.setResizable(false);
            stage.showAndWait();
            DontDeleteDB dontDeleteDB = new DontDeleteDB();
            dontDeleteDB.connectDatabase();
            SdTable.setItems(dontDeleteDB.getSaleDelivery("SELECT * FROM SALE_DELIVERY WHERE date(DATE) = '" + date + "' ;"));
            dontDeleteDB.closeDatabase();
        }
        SdBillNo = null;
    }

}

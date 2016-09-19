/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Tanmoy
 */
public class CustomerListController implements Initializable {

    //AutoSuggetionText CODE
    private AutoCompleteTextField ClCustomerName;
    private AutoCompleteTextField ClOrderNo;
    private AutoCompleteTextField ClBillNo;
    @FXML
    private TextField ClDateFrom;
    @FXML
    private TextField ClDateTo;

    //CUSTOMER LIST TABLE
    @FXML
    private TableColumn<CustomerListDB, String> ClCustomerNameCol;
    @FXML
    private TableColumn<CustomerListDB, String> ClInstitutionNameCol;

    //Sale Order Table
    @FXML
    private TableColumn<SaleOrderDB, String> SoDateCol;
    @FXML
    private TableColumn<SaleOrderDB, String> SoCustomerNameCol;
    @FXML
    private TableColumn<SaleOrderDB, String> SoInstituteNameCol;
    @FXML
    private TableColumn<SaleOrderDB, String> SoOrderNoCol;
    @FXML
    private TableColumn<SaleOrderDB, String> SoProductNameCol;
    @FXML
    private TableColumn<SaleOrderDB, String> SoMarkaCol;
    @FXML
    private TableColumn<SaleOrderDB, String> SoQuantityCol;
    @FXML
    private TableColumn<SaleOrderDB, String> SoUnitCol;
    @FXML
    private TableColumn<SaleOrderDB, String> SoRateKgCol;
    @FXML
    private TableColumn<SaleOrderDB, String> SoRateMonCol;

    @FXML
    private TableColumn<SaleOrderDB, String> SoLpCol;
    @FXML
    private TableColumn<SaleOrderDB, String> SoPriceCol;

    //SaleDelivery Table
    @FXML
    private TableColumn<SaleDeliveryDB, String> SdDateCol;
    @FXML
    private TableColumn<SaleDeliveryDB, String> SdTruckNoCol;
    @FXML
    private TableColumn<SaleDeliveryDB, String> SdBillNoCol;

    @FXML
    private TableColumn<SaleDeliveryDB, String> SdCustomerCol;
    @FXML
    private TableColumn<SaleDeliveryDB, String> SdInstituteCol;
    @FXML
    private TableColumn<SaleDeliveryDB, String> SdProductCol;

    @FXML
    private TableColumn<SaleDeliveryDB, String> SdUnitCol;

    @FXML
    private TableColumn<SaleDeliveryDB, String> SdRateKgCol;
    @FXML
    private TableColumn<SaleDeliveryDB, String> SdRateMonCol;

    @FXML
    private TableColumn<SaleDeliveryDB, String> SdLpCol;
    @FXML
    private TableColumn<SaleDeliveryDB, String> SdQuantityCol;
    @FXML
    private TableColumn<SaleDeliveryDB, String> SdPriceCol;

    //TABLE ID
    @FXML
    private TableView<CustomerListDB> ClTable;
    @FXML
    private TableColumn<CustomerListDB, String> ClPaymentCol;
    @FXML
    private TableColumn<CustomerListDB, String> ClTotalOrderCol;
    @FXML
    private TableColumn<CustomerListDB, String> ClBalanceOrderCol;
    @FXML
    private TableColumn<CustomerListDB, String> ClTotalDeliveriesCol;
    @FXML
    private TableColumn<CustomerListDB, String> ClBalanceDeliveriesCol;
    @FXML
    private TableColumn<CustomerListDB, String> ClContactsCol;

    @FXML
    private TableView<CustomerTransactionDB> CtTable;
    @FXML
    private TableColumn<CustomerTransactionDB, String> CtBillNoCol;
    
    @FXML
    private TableColumn<CustomerTransactionDB, String> CtLpCol;
    @FXML
    private TableColumn<CustomerTransactionDB, String> CtDateCol;
    @FXML
    private TableColumn<CustomerTransactionDB, String> CtCustomerCol;
    @FXML
    private TableColumn<CustomerTransactionDB, String> CtInstituteCol;
    @FXML
    private TableColumn<CustomerTransactionDB, String> CtBankCashCol;
    @FXML
    private TableColumn<CustomerTransactionDB, String> CtDebitCol;
    @FXML
    private TableColumn<CustomerTransactionDB, String> CtCreditCol;

    @FXML
    private TableView<SaleOrderDB> SoTable;

    @FXML
    private TableView<SaleDeliveryDB> SdTable;

    ObservableList<CustomerListDB> ClData = FXCollections.observableArrayList();

    ObservableList<CustomerTransactionDB> CtData = FXCollections.observableArrayList();

    ObservableList<SaleOrderDB> SoData = FXCollections.observableArrayList();

    ObservableList<SaleDeliveryDB> SdData = FXCollections.observableArrayList();

    @FXML
    private VBox ClCustomerNameV;

    @FXML
    private VBox ClInvoiceNoV;
    @FXML
    private VBox ClBillNoV;
    @FXML
    private Button ClViewButt;
    @FXML
    private Button ClSearchButt;
    @FXML
    private Button CtViewButt;
    @FXML
    private Button CtSearchButt;
    @FXML
    private Button SoViewButt;
    @FXML
    private Button SoSearchButt;
    @FXML
    private Button SdViewButt;
    @FXML
    private Button SdSearchButt;

    public static CustomerListDB sendClOb = null;
    public static String SoOrderNo = null;
    public static String SdBillNo = null;
    @FXML
    private TableColumn<SaleDeliveryDB, String> SdOrderNoCol;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //connecting the database
        Database database = new Database();
        DontDeleteDB dontDeleteDB = new DontDeleteDB();
        database.connect_database();
        dontDeleteDB.connectDatabase();

        //CUSTOMER NAME
        ClCustomerName = new AutoCompleteTextField();
        ClCustomerName.setEntries(database.getSuggestionList("SELECT * FROM CUSTOMER_LIST;", "CUSTOMER"));
        ClCustomerName.setPrefSize(150, 25);

        //ORDER NO
        ClOrderNo = new AutoCompleteTextField();
        ClOrderNo.setEntries(database.getSuggestionList("SELECT * FROM SALE_ORDER;", "INVOICE_NO"));
        ClOrderNo.setPrefSize(150, 25);

        ClBillNo = new AutoCompleteTextField();
        ClBillNo.setEntries(database.getSuggestionList("SELECT * FROM SALE_DELIVERY", "BILL_NO"));
        ClBillNo.setPrefSize(150, 25);

        ClCustomerNameV.getChildren().addAll(ClCustomerName);
        ClInvoiceNoV.getChildren().addAll(ClOrderNo);
        ClBillNoV.getChildren().addAll(ClBillNo);

        //CUSTOMER LIST TABLE SHOW
        ClCustomerNameCol.setCellValueFactory(new PropertyValueFactory<>("customer"));
        ClInstitutionNameCol.setCellValueFactory(new PropertyValueFactory<>("institute"));
        ClPaymentCol.setCellValueFactory(new PropertyValueFactory<>("payment2"));
        ClTotalOrderCol.setCellValueFactory(new PropertyValueFactory<>("totalOrder2"));
        ClBalanceOrderCol.setCellValueFactory(new PropertyValueFactory<>("balanceOrder2"));
        ClTotalDeliveriesCol.setCellValueFactory(new PropertyValueFactory<>("totalDelivery2"));
        ClBalanceDeliveriesCol.setCellValueFactory(new PropertyValueFactory<>("balanceDelivery2"));
        ClContactsCol.setCellValueFactory(new PropertyValueFactory<>("contacts"));

        ClData.setAll(dontDeleteDB.getCustomerList("SELECT * FROM CUSTOMER_LIST;"));
        ClTable.setItems(ClData);

        //Customer Transaction Table
        CtDateCol.setCellValueFactory(new PropertyValueFactory<>("date2"));
        CtCustomerCol.setCellValueFactory(new PropertyValueFactory<>("customer"));
        CtInstituteCol.setCellValueFactory(new PropertyValueFactory<>("institute"));
        CtBankCashCol.setCellValueFactory(new PropertyValueFactory<>("bank"));
        CtBillNoCol.setCellValueFactory(new PropertyValueFactory<>("billNo"));
        CtDebitCol.setCellValueFactory(new PropertyValueFactory<>("debit2"));
        CtCreditCol.setCellValueFactory(new PropertyValueFactory<>("credit2"));
        CtLpCol.setCellValueFactory(new PropertyValueFactory<>("lp"));
        CtData.setAll(dontDeleteDB.getCustomerTransaction("SELECT * FROM CUSTOMER_TRANSACTION order by DATE DESC ;"));
        CtTable.setItems(CtData);

        //Sale Order Table
        SoDateCol.setCellValueFactory(new PropertyValueFactory<>("date"));
        SoCustomerNameCol.setCellValueFactory(new PropertyValueFactory<>("customer"));
        SoInstituteNameCol.setCellValueFactory(new PropertyValueFactory<>("institute"));
        SoOrderNoCol.setCellValueFactory(new PropertyValueFactory<>("invoiceNo"));
        SoMarkaCol.setCellValueFactory(new PropertyValueFactory<>("marka"));
        SoProductNameCol.setCellValueFactory(new PropertyValueFactory<>("product"));
        SoQuantityCol.setCellValueFactory(new PropertyValueFactory<>("quantity2"));
        SoUnitCol.setCellValueFactory(new PropertyValueFactory<>("unit2"));
        SoRateKgCol.setCellValueFactory(new PropertyValueFactory<>("rateKg2"));
        SoRateMonCol.setCellValueFactory(new PropertyValueFactory<>("rateMon2"));
        SoPriceCol.setCellValueFactory(new PropertyValueFactory<>("price2"));
        SoLpCol.setCellValueFactory(new PropertyValueFactory<>("lp"));

        SoData.setAll(dontDeleteDB.getSaleOrder("SELECT * FROM SALE_ORDER order by DATE DESC ;"));
        SoTable.setItems(SoData);

        //SaleDelivery Table
        SdDateCol.setCellValueFactory(new PropertyValueFactory<>("date2"));
        SdTruckNoCol.setCellValueFactory(new PropertyValueFactory<>("truckNo"));
        SdBillNoCol.setCellValueFactory(new PropertyValueFactory<>("billNo"));
        SdCustomerCol.setCellValueFactory(new PropertyValueFactory<>("customer"));
        SdInstituteCol.setCellValueFactory(new PropertyValueFactory<>("institute"));
        SdProductCol.setCellValueFactory(new PropertyValueFactory<>("product"));
        SdQuantityCol.setCellValueFactory(new PropertyValueFactory<>("quantity2"));
        SdUnitCol.setCellValueFactory(new PropertyValueFactory<>("unit2"));
        SdPriceCol.setCellValueFactory(new PropertyValueFactory<>("price2"));
        SdRateKgCol.setCellValueFactory(new PropertyValueFactory<>("rateKg2"));
        SdRateMonCol.setCellValueFactory(new PropertyValueFactory<>("rateMon2"));
        SdLpCol.setCellValueFactory(new PropertyValueFactory<>("lp"));
        SdOrderNoCol.setCellValueFactory(new PropertyValueFactory<>("invoiceNo"));

        SdData.setAll(dontDeleteDB.getSaleDelivery("SELECT * FROM SALE_DELIVERY order by DATE DESC ;"));
        SdTable.setItems(SdData);

        dontDeleteDB.closeDatabase();
        database.close_database();

    }

    @FXML
    private void ClNewCustomerButtClick(ActionEvent event) throws IOException {
        Parent p = FXMLLoader.load(getClass().getResource("NewCustomer.fxml"));
        Stage Banks = new Stage();
        Scene scene = new Scene(p);
        Banks.initModality(Modality.WINDOW_MODAL);
        Banks.initOwner(
                ((Node) event.getSource()).getScene().getWindow());
        Banks.setScene(scene);
        Banks.setResizable(false);
        Banks.showAndWait();

        DontDeleteDB dontDeleteDB = new DontDeleteDB();
        dontDeleteDB.connectDatabase();
        ClData.setAll(dontDeleteDB.getCustomerList("SELECT * FROM CUSTOMER_LIST;"));
        ClTable.setItems(ClData);
        dontDeleteDB.closeDatabase();
    }

    @FXML
    private void ClViewButtClick(ActionEvent event) throws IOException {
        CustomerListDB ClOb = ClTable.getSelectionModel().getSelectedItem();
        if (ClOb == null) {
        } else {
            Parent p = FXMLLoader.load(getClass().getResource("AdminPass.fxml"));
            Stage Banks = new Stage();
            Scene scene = new Scene(p);
            Banks.initModality(Modality.WINDOW_MODAL);
            Banks.initOwner(
                    ((Node) event.getSource()).getScene().getWindow());
            Banks.setScene(scene);
            Banks.showAndWait();
            if (LoginController.level == true) {

                sendClOb = ClOb;
                //System.out.println(SoOrderNo);
                p = FXMLLoader.load(getClass().getResource("NewCustomer.fxml"));
                Stage stage = new Stage();
                scene = new Scene(p);
                stage.initModality(Modality.WINDOW_MODAL);
                stage.initOwner(
                        ((Node) event.getSource()).getScene().getWindow());
                stage.setScene(scene);
                stage.setResizable(false);
                stage.showAndWait();
                DontDeleteDB dontDeleteDB = new DontDeleteDB();
                dontDeleteDB.connectDatabase();
                ClTable.setItems(dontDeleteDB.getCustomerList("SELECT * FROM CUSTOMER_LIST;"));
                dontDeleteDB.closeDatabase();
                sendClOb = null;
                LoginController.level = false;
            }
        }
    }

    @FXML
    private void ClSearchButtClick(ActionEvent event
    ) {
        DontDeleteDB dontDeleteDB = new DontDeleteDB();
        dontDeleteDB.connectDatabase();
        int where = 0;
        String maker = "SELECT * FROM CUSTOMER_LIST ";
        if (ClCustomerName.getText() != null && !"".equals(ClCustomerName.getText())) {

            maker = maker + "WHERE CUSTOMER='" + ClCustomerName.getText() + "'";
            where++;
        }

        ClTable.setItems(dontDeleteDB.getCustomerList(maker + " order by DATE DESC  ;"));
        dontDeleteDB.closeDatabase();
    }

    @FXML
    private void CtViewButtClick(ActionEvent event) throws IOException, SQLException, ParseException {

        CustomerTransactionDB CtOb = CtTable.getSelectionModel().getSelectedItem();
        if (CtOb == null) {
        } else {
            Parent p = FXMLLoader.load(getClass().getResource("AdminPass.fxml"));
            Stage Banks = new Stage();
            Scene scene = new Scene(p);
            Banks.initModality(Modality.WINDOW_MODAL);
            Banks.initOwner(
                    ((Node) event.getSource()).getScene().getWindow());
            Banks.setScene(scene);
            Banks.showAndWait();
            if (LoginController.level == true) {
                LoginController.level=false;
                if ("".equals(CtOb.getBillNo()) || CtOb.getBillNo() == null) {
                    DontDeleteDB dontDeleteDB = new DontDeleteDB();
                    dontDeleteDB.connectDatabase();
                    BankTransactionDB ob = dontDeleteDB.getBt(CtOb.getIdBankDepoWith());
                    BankListController.sendBtOb = ob;
                    p = FXMLLoader.load(getClass().getResource("BankDepositDetails.fxml"));
                    Stage stage = new Stage();
                    scene = new Scene(p);
                    stage.initModality(Modality.WINDOW_MODAL);
                    stage.initOwner(
                            ((Node) event.getSource()).getScene().getWindow());
                    stage.setScene(scene);
                    stage.setResizable(false);
                    stage.showAndWait();
                    CtData.setAll(dontDeleteDB.getCustomerTransaction("SELECT * FROM CUSTOMER_TRANSACTION order by DATE DESC ;"));
                    CtTable.setItems(CtData);
                    dontDeleteDB.closeDatabase();
                    BankListController.sendBtOb = null;
                } else {
                    SdBillNo = CtOb.getBillNo();
                    //System.out.println(SoOrderNo);
                    p = FXMLLoader.load(getClass().getResource("SaleDelivery.fxml"));
                    Stage stage = new Stage();
                    scene = new Scene(p);
                    stage.initModality(Modality.WINDOW_MODAL);
                    stage.initOwner(
                            ((Node) event.getSource()).getScene().getWindow());
                    stage.setScene(scene);
                    stage.setResizable(false);
                    stage.showAndWait();
                    DontDeleteDB dontDeleteDB = new DontDeleteDB();
                    dontDeleteDB.connectDatabase();
                    SdTable.setItems(dontDeleteDB.getSaleDelivery("SELECT * FROM SALE_DELIVERY  order by DATE DESC ;"));
                    CtData.setAll(dontDeleteDB.getCustomerTransaction("SELECT * FROM CUSTOMER_TRANSACTION order by DATE DESC ;"));
                    CtTable.setItems(CtData);
                    dontDeleteDB.closeDatabase();
                }
                SdBillNo = null;
            }
        }
    }

    @FXML
    private void CtSearchButtClick(ActionEvent event) throws ParseException {
        DontDeleteDB dontDeleteDB = new DontDeleteDB();
        dontDeleteDB.connectDatabase();
        int where = 0;
        String maker = "SELECT * FROM CUSTOMER_TRANSACTION ";
        if (ClCustomerName.getText() != null && !"".equals(ClCustomerName.getText())) {

            maker = maker + "WHERE CUSTOMER='" + ClCustomerName.getText() + "'";
            where++;
        }
        if (ClBillNo.getText() != null && !"".equals(ClBillNo.getText())) {

            if (where > 0) {
                maker = maker + "AND BILL_NO= '" + ClBillNo.getText() + "'";
            } else {
                maker = maker + "WHERE BILL_NO= '" + ClBillNo.getText() + "'";
                where++;
            }

        }

        if (ClDateFrom.getText() != null && !"".equals(ClDateFrom.getText())) {
            DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            Date fromdate = format.parse(ClDateFrom.getText());
            java.sql.Date date = new java.sql.Date(fromdate.getTime());

            if (ClDateTo.getText() != null && !"".equals(ClDateTo.getText())) {

                Date todate = format.parse(ClDateTo.getText());
                java.sql.Date dateto = new java.sql.Date(todate.getTime());
                if (where > 0) {
                    maker = maker + "AND date(DATE)>= '" + date + "' AND date(DATE)<= '"
                            + dateto + "'";
                } else {
                    maker = maker + "WHERE date(DATE)>= '" + date + "' AND date(DATE)<= '"
                            + dateto + "'";
                }
            } else if (where > 0) {
                maker = maker + "AND date(DATE)= '" + date + "'";
            } else {
                maker = maker + "WHERE date(DATE)= '" + date + "'";
            }
        }

        CtTable.setItems(dontDeleteDB.getCustomerTransaction(maker + " order by DATE DESC  ;"));
        dontDeleteDB.closeDatabase();
    }

    @FXML
    private void SoViewButtClick(ActionEvent event) throws IOException {
        SaleOrderDB SoOb = SoTable.getSelectionModel().getSelectedItem();
        if (SoOb == null) {
        } else {

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
                SoOrderNo = SoOb.getInvoiceNo();
                p = FXMLLoader.load(getClass().getResource("SaleOrder.fxml"));
                Stage stage = new Stage();
                scene = new Scene(p);
                stage.initModality(Modality.WINDOW_MODAL);
                stage.initOwner(
                        ((Node) event.getSource()).getScene().getWindow());
                stage.setScene(scene);
                stage.setResizable(false);
                stage.showAndWait();
                DontDeleteDB dontDeleteDB = new DontDeleteDB();
                dontDeleteDB.connectDatabase();
                SoTable.setItems(dontDeleteDB.getSaleOrder("SELECT * FROM SALE_ORDER  order by DATE DESC ;"));
                dontDeleteDB.closeDatabase();

            }
        }
        SoOrderNo = null;
    }

    @FXML
    private void SoSearchButtClick(ActionEvent event) throws ParseException {
        DontDeleteDB dontDeleteDB = new DontDeleteDB();
        dontDeleteDB.connectDatabase();
        int where = 0;
        String maker = "SELECT * FROM SALE_ORDER ";
        if (ClCustomerName.getText() != null && !"".equals(ClCustomerName.getText())) {

            maker = maker + "WHERE CUSTOMER='" + ClCustomerName.getText() + "'";
            where++;
        }
        if (ClOrderNo.getText() != null && !"".equals(ClOrderNo.getText())) {

            if (where > 0) {
                maker = maker + "AND INVOICE_NO= '" + ClOrderNo.getText() + "'";
            } else {
                maker = maker + "WHERE INVOICE_NO= '" + ClOrderNo.getText() + "'";
                where++;
            }

        }

        if (ClDateFrom.getText() != null && !"".equals(ClDateFrom.getText())) {
            DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            Date fromdate = format.parse(ClDateFrom.getText());
            java.sql.Date date = new java.sql.Date(fromdate.getTime());

            if (ClDateTo.getText() != null && !"".equals(ClDateTo.getText())) {

                Date todate = format.parse(ClDateTo.getText());
                java.sql.Date dateto = new java.sql.Date(todate.getTime());
                if (where > 0) {
                    maker = maker + "AND date(DATE)>= '" + date + "' AND date(DATE)<= '"
                            + dateto + "'";
                } else {
                    maker = maker + "WHERE date(DATE)>= '" + date + "' AND date(DATE)<= '"
                            + dateto + "'";
                }
            } else if (where > 0) {
                maker = maker + "AND date(DATE)= '" + date + "'";
            } else {
                maker = maker + "WHERE date(DATE)= '" + date + "'";
            }
        }

        SoTable.setItems(dontDeleteDB.getSaleOrder(maker + " order by DATE DESC  ;"));
        dontDeleteDB.closeDatabase();
    }

    @FXML
    private void SdViewButtClick(ActionEvent event) throws IOException {
        SaleDeliveryDB SdOb = SdTable.getSelectionModel().getSelectedItem();
        if (SdOb == null) {
            return;
        } else {

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
                SdBillNo = SdOb.getBillNo();
                //System.out.println(SoOrderNo);
                p = FXMLLoader.load(getClass().getResource("SaleDelivery.fxml"));
                Stage stage = new Stage();
                scene = new Scene(p);
                stage.initModality(Modality.WINDOW_MODAL);
                stage.initOwner(
                        ((Node) event.getSource()).getScene().getWindow());
                stage.setScene(scene);
                stage.setResizable(false);
                stage.showAndWait();
                DontDeleteDB dontDeleteDB = new DontDeleteDB();
                dontDeleteDB.connectDatabase();
                SdTable.setItems(dontDeleteDB.getSaleDelivery("SELECT * FROM SALE_DELIVERY order by DATE DESC  ;"));
                dontDeleteDB.closeDatabase();
            }
        }
        SdBillNo = null;
    }

    @FXML
    private void SdSearchButtClick(ActionEvent event) throws ParseException {
        DontDeleteDB dontDeleteDB = new DontDeleteDB();
        dontDeleteDB.connectDatabase();
        int where = 0;
        String maker = "SELECT * FROM SALE_DELIVERY ";
        if (ClCustomerName.getText() != null && !"".equals(ClCustomerName.getText())) {

            maker = maker + "WHERE CUSTOMER='" + ClCustomerName.getText() + "'";
            where++;
        }
        if (ClBillNo.getText() != null && !"".equals(ClBillNo.getText())) {

            if (where > 0) {
                maker = maker + "AND BILL_NO= '" + ClBillNo.getText() + "'";
            } else {
                maker = maker + "WHERE BILL_NO= '" + ClBillNo.getText() + "'";
                where++;
            }
        }
        if (ClOrderNo.getText() != null && !"".equals(ClOrderNo.getText())) {

            if (where > 0) {
                maker = maker + "AND INVOICE_NO= '" + ClOrderNo.getText() + "'";
            } else {
                maker = maker + "WHERE INVOICE_NO= '" + ClOrderNo.getText() + "'";
                where++;
            }
        }

        if (ClDateFrom.getText() != null && !"".equals(ClDateFrom.getText())) {
            DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            Date fromdate = format.parse(ClDateFrom.getText());
            java.sql.Date date = new java.sql.Date(fromdate.getTime());

            if (ClDateTo.getText() != null && !"".equals(ClDateTo.getText())) {

                Date todate = format.parse(ClDateTo.getText());
                java.sql.Date dateto = new java.sql.Date(todate.getTime());
                if (where > 0) {
                    maker = maker + "AND date(DATE)>= '" + date + "' AND date(DATE)<= '"
                            + dateto + "'";
                } else {
                    maker = maker + "WHERE date(DATE)>= '" + date + "' AND date(DATE)<= '"
                            + dateto + "'";
                }
            } else if (where > 0) {
                maker = maker + "AND date(DATE)= '" + date + "'";
            } else {
                maker = maker + "WHERE date(DATE)= '" + date + "'";
            }
        }

        SdTable.setItems(dontDeleteDB.getSaleDelivery(maker + " order by DATE DESC  ;"));
        dontDeleteDB.closeDatabase();
    }

}

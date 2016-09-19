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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Tanmoy
 */
public class SupplierListController implements Initializable {

    //AutoSuggetionText CODE
    private AnchorPane SlAnchorPane;
    private AutoCompleteTextField SlSupplierName;
    private AutoCompleteTextField SlOrderNo;
    private AutoCompleteTextField SlDeliveryNo;
    @FXML
    private TextField SlDateFrom;
    @FXML
    private TextField SlDateTo;

    //SupplierList Table
    @FXML
    private TableColumn<SupplierListDB, String> SlSupplierNameCol;
    @FXML
    private TableColumn<SupplierListDB, String> SlInstituteNameCol;
    @FXML
    private TableColumn<SupplierListDB, String> SlPaymentCol;
    @FXML
    private TableColumn<SupplierListDB, String> SlTotalOrderCol;
    @FXML
    private TableColumn<SupplierListDB, String> SlBalanceOrderCol;
    @FXML
    private TableColumn<SupplierListDB, String> SlTotalDeliveriesCol;
    @FXML
    private TableColumn<SupplierListDB, String> SlBalanceDeliveriesCol;
    @FXML
    private TableColumn<SupplierListDB, String> SlContactsCol;

    //SUPPLIER TRANSACTION TABLE
    @FXML
    private TableColumn<SupplierTransactionDB, String> StDateCol;
    @FXML
    private TableColumn<SupplierTransactionDB, String> StSupplierNameCol;
    @FXML
    private TableColumn<SupplierTransactionDB, String> StInstituteNameCol;
    @FXML
    private TableColumn<SupplierTransactionDB, String> StBankCashCol;
    @FXML
    private TableColumn<SupplierTransactionDB, String> StProductNameCol;
    @FXML
    private TableColumn<SupplierTransactionDB, String> StDeliveryNoCol;
    @FXML
    private TableColumn<SupplierTransactionDB, String> StQuantityCol;
    @FXML
    private TableColumn<SupplierTransactionDB, String> StUnitCol;
    @FXML
    private TableColumn<SupplierTransactionDB, String> StLpCol;

    //PURCHASE DELIVERY TABLE
    @FXML
    private TableColumn<PurchaseDeliveryDB, String> PdSupplierCol;
    @FXML
    private TableColumn<PurchaseDeliveryDB, String> PdInstituteCol;
    @FXML
    private TableColumn<PurchaseDeliveryDB, String> PdProductCol;
    @FXML
    private TableColumn<PurchaseDeliveryDB, String> PdQuantityCol;
    @FXML
    private TableColumn<PurchaseDeliveryDB, String> PdUnitCol;
    @FXML
    private TableColumn<PurchaseDeliveryDB, String> PdRateKgCol;
    @FXML
    private TableColumn<PurchaseDeliveryDB, String> PdRateMonCol;
    @FXML
    private TableColumn<PurchaseDeliveryDB, String> PdLpCol;
    @FXML
    private TableColumn<PurchaseDeliveryDB, String> PdDateCol;
    @FXML
    private TableColumn<PurchaseDeliveryDB, String> PdPriceCol;

    //PURCHASE ORDER TABLE
    @FXML
    private TableColumn<PurchaseOrderDB, String> PoDateCol;
    @FXML
    private TableColumn<PurchaseOrderDB, String> PoInstituteNameCol;
    @FXML
    private TableColumn<PurchaseOrderDB, String> PoOrderNoCol;
    @FXML
    private TableColumn<PurchaseOrderDB, String> PoProductNameCol;
    @FXML
    private TableColumn<PurchaseOrderDB, String> PoQuantityCol;
    @FXML
    private TableColumn<PurchaseOrderDB, String> PoUnitCol;
    @FXML
    private TableColumn<PurchaseOrderDB, String> PoRateKgCol;
    @FXML
    private TableColumn<PurchaseOrderDB, String> PoRateMonCol;
    @FXML
    private TableColumn<PurchaseOrderDB, String> PoLpCol;
    @FXML
    private TableColumn<PurchaseOrderDB, String> PoSupplierCol;
    @FXML
    private TableColumn<PurchaseOrderDB, String> PoStorageCol;
    @FXML
    private TableColumn<PurchaseOrderDB, String> PoPriceCol;

    //TABLE ID
    @FXML
    private TableView<SupplierListDB> SlTable;

    @FXML
    private TableView<SupplierTransactionDB> StTable;

    @FXML
    private TableView<PurchaseOrderDB> PoTable;

    @FXML
    private TableView<PurchaseDeliveryDB> PdTable;

    ObservableList<SupplierListDB> SlData = FXCollections.observableArrayList();

    ObservableList<SupplierTransactionDB> StData = FXCollections.observableArrayList();

    ObservableList<PurchaseOrderDB> PoData = FXCollections.observableArrayList();

    ObservableList<PurchaseDeliveryDB> PdData = FXCollections.observableArrayList();
    @FXML
    private VBox SlSupplierV;

    @FXML
    private Button PdSearchButt;
    @FXML
    private TableColumn<SupplierTransactionDB, String> StDebitCol;
    @FXML
    private TableColumn<SupplierTransactionDB, String> StCreditCol;
    @FXML
    private TableColumn<SupplierTransactionDB, String> PdDeliveryNoCol;
    @FXML
    private VBox SlDeliveryNoV;
    @FXML
    private Button SlViewButt;
    @FXML
    private VBox SlOrderNoV;

    public static SupplierListDB sendSlOb = new SupplierListDB();
    @FXML
    private TableColumn<PurchaseDeliveryDB, String> PdOrderNoCol;
    @FXML
    private TableColumn<PurchaseDeliveryDB, String> PdStorageCol;
    @FXML
    private TableColumn<SupplierTransactionDB, String> StRateMon;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //connecting the database
        Database database = new Database();
        database.connect_database();

        DontDeleteDB dontDeleteDB = new DontDeleteDB();
        dontDeleteDB.connectDatabase();
        //SUPPLIER NAME
        SlSupplierName = new AutoCompleteTextField();
        SlSupplierName.setEntries(database.getSuggestionList("SELECT * FROM SUPPLIER_LIST;", "SUPPLIER"));
        SlSupplierName.setPrefSize(150, 25);
        SlSupplierName.setPromptText("SUPPLIER NAME");

        //ORDER NO
        SlOrderNo = new AutoCompleteTextField();
        SlOrderNo.setEntries(database.getSuggestionList("SELECT * FROM PURCHASE_ORDER;", "ORDER_NO"));
        SlOrderNo.setPrefSize(150, 25);
        SlOrderNo.setPromptText("ORDER NO");

        SlDeliveryNo = new AutoCompleteTextField();
        SlDeliveryNo.setEntries(database.getSuggestionList("SELECT * FROM PURCHASE_DELIVERY;", "DELIVERY_NO"));
        SlDeliveryNo.setPrefSize(150, 25);
        SlDeliveryNo.setPromptText("DELIVERY NO");

        SlSupplierV.getChildren().add(SlSupplierName);
        SlOrderNoV.getChildren().add(SlOrderNo);
        SlDeliveryNoV.getChildren().add(SlDeliveryNo);

        //SupplierList Table
        SlSupplierNameCol.setCellValueFactory(new PropertyValueFactory<>("supplier"));
        SlInstituteNameCol.setCellValueFactory(new PropertyValueFactory<>("institute"));
        SlPaymentCol.setCellValueFactory(new PropertyValueFactory<>("payment2"));
        SlTotalOrderCol.setCellValueFactory(new PropertyValueFactory<>("totalOrder2"));
        SlTotalDeliveriesCol.setCellValueFactory(new PropertyValueFactory<>("totalDelivery2"));
        SlBalanceOrderCol.setCellValueFactory(new PropertyValueFactory<>("balanceOrder2"));
        SlBalanceDeliveriesCol.setCellValueFactory(new PropertyValueFactory<>("balanceDelivery2"));
        SlContactsCol.setCellValueFactory(new PropertyValueFactory<>("contacts"));

        SlData.setAll(dontDeleteDB.getSupplierList("SELECT * FROM SUPPLIER_LIST;"));
        SlTable.setItems(SlData);

        //SUPPLIER TRANSACTION TABLE SHOW
        StDateCol.setCellValueFactory(new PropertyValueFactory<>("date2"));
        StSupplierNameCol.setCellValueFactory(new PropertyValueFactory<>("supplier"));
        StInstituteNameCol.setCellValueFactory(new PropertyValueFactory<>("institute"));
        StBankCashCol.setCellValueFactory(new PropertyValueFactory<>("bankOrCash"));
        StDeliveryNoCol.setCellValueFactory(new PropertyValueFactory<>("deliveryNo"));
        StProductNameCol.setCellValueFactory(new PropertyValueFactory<>("product"));
        StQuantityCol.setCellValueFactory(new PropertyValueFactory<>("quantity2"));
        StRateMon.setCellValueFactory(new PropertyValueFactory<>("rateMon2"));
        StUnitCol.setCellValueFactory(new PropertyValueFactory<>("unit2"));
        StDebitCol.setCellValueFactory(new PropertyValueFactory<>("debit2"));
        StCreditCol.setCellValueFactory(new PropertyValueFactory<>("credit2"));
        StUnitCol.setCellValueFactory(new PropertyValueFactory<>("unit2"));
        StLpCol.setCellValueFactory(new PropertyValueFactory<>("lp"));

        StData.setAll(dontDeleteDB.getSupplierTransaction("SELECT * FROM SUPPLIER_TRANSACTION order by DATE DESC ;"));
        StTable.setItems(StData);

        //PURCHASE ORDER TABLE SHOW
        PoDateCol.setCellValueFactory(new PropertyValueFactory<>("date2"));
        PoSupplierCol.setCellValueFactory(new PropertyValueFactory<>("supplier"));
        PoInstituteNameCol.setCellValueFactory(new PropertyValueFactory<>("institute"));
        PoOrderNoCol.setCellValueFactory(new PropertyValueFactory<>("orderNo"));
        PoProductNameCol.setCellValueFactory(new PropertyValueFactory<>("product"));
        PoStorageCol.setCellValueFactory(new PropertyValueFactory<>("storage"));
        PoQuantityCol.setCellValueFactory(new PropertyValueFactory<>("quantity2"));
        PoUnitCol.setCellValueFactory(new PropertyValueFactory<>("unit2"));
        PoRateKgCol.setCellValueFactory(new PropertyValueFactory<>("rateKg2"));
        PoRateMonCol.setCellValueFactory(new PropertyValueFactory<>("rateMon2"));
        PoPriceCol.setCellValueFactory(new PropertyValueFactory<>("price2"));
        PoLpCol.setCellValueFactory(new PropertyValueFactory<>("lp"));

        PoData.setAll(dontDeleteDB.getPurchaseOrder("SELECT * FROM PURCHASE_ORDER order by DATE DESC ;"));
        PoTable.setItems(PoData);

        //PURCHASE DELIVERY TABLE SHOW
        PdDateCol.setCellValueFactory(new PropertyValueFactory<>("date2"));
        PdSupplierCol.setCellValueFactory(new PropertyValueFactory<>("supplier"));
        PdInstituteCol.setCellValueFactory(new PropertyValueFactory<>("institute"));
        PdProductCol.setCellValueFactory(new PropertyValueFactory<>("product"));
        PdDeliveryNoCol.setCellValueFactory(new PropertyValueFactory<>("deliveryNo"));
        PdQuantityCol.setCellValueFactory(new PropertyValueFactory<>("quantity2"));
        PdUnitCol.setCellValueFactory(new PropertyValueFactory<>("unit2"));
        PdRateKgCol.setCellValueFactory(new PropertyValueFactory<>("rateKg2"));
        PdRateMonCol.setCellValueFactory(new PropertyValueFactory<>("rateMon2"));
        PdPriceCol.setCellValueFactory(new PropertyValueFactory<>("price2"));
        PdLpCol.setCellValueFactory(new PropertyValueFactory<>("lp"));
        PdOrderNoCol.setCellValueFactory(new PropertyValueFactory<>("orderNo"));
        PdStorageCol.setCellValueFactory(new PropertyValueFactory<>("storage"));

        PdData.setAll(dontDeleteDB.getPurchaseDelivery("SELECT * FROM PURCHASE_DELIVERY order by DATE DESC ;"));
        PdTable.setItems(PdData);

        dontDeleteDB.closeDatabase();
        database.close_database();
    }

    @FXML
    private void NewButtClick(ActionEvent event) throws IOException {
        sendSlOb=null;
        Parent p = FXMLLoader.load(getClass().getResource("NewSupplier.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(p);
        stage.initOwner(
                ((Node) event.getSource()).getScene().getWindow());
        stage.setScene(scene);
        stage.setResizable(false);
        stage.showAndWait();

        DontDeleteDB dontDeleteDB = new DontDeleteDB();
        dontDeleteDB.connectDatabase();

        SlData.setAll(dontDeleteDB.getSupplierList("SELECT * FROM SUPPLIER_LIST;"));
        SlTable.setItems(SlData);

        dontDeleteDB.closeDatabase();
    }

    @FXML
    private void SlViewButtClick(ActionEvent event) throws IOException {
        SupplierListDB SlOb = SlTable.getSelectionModel().getSelectedItem();
        if (SlOb == null) {
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
                sendSlOb = SlOb;
                //System.out.println(SoOrderNo);
                p = FXMLLoader.load(getClass().getResource("NewSupplier.fxml"));
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
                SlTable.setItems(dontDeleteDB.getSupplierList("SELECT * FROM SUPPLIER_LIST;"));
                dontDeleteDB.closeDatabase();
                sendSlOb = null;
            }
        }
    }

    @FXML
    private void SlSearchButtClick(ActionEvent event) {
        DontDeleteDB dontDeleteDB = new DontDeleteDB();
        dontDeleteDB.connectDatabase();
        int where = 0;
        String maker = "SELECT * FROM SUPPLIER_LIST ";
        if (SlSupplierName.getText() != null && !"".equals(SlSupplierName.getText())) {

            maker = maker + "WHERE SUPPLIER='" + SlSupplierName.getText() + "'";
            where++;
        }

        SlTable.setItems(dontDeleteDB.getSupplierList(maker + "  order by DATE DESC ;"));
        dontDeleteDB.closeDatabase();
    }

    @FXML
    private void StViewButtClick(ActionEvent event) throws SQLException, ParseException, IOException {

        SupplierTransactionDB StOb = StTable.getSelectionModel().getSelectedItem();
        if (StOb == null) {
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

                if ("".equals(StOb.getDeliveryNo()) || StOb.getDeliveryNo() == null) {
                    DontDeleteDB dontDeleteDB = new DontDeleteDB();
                    dontDeleteDB.connectDatabase();
                    BankTransactionDB ob = dontDeleteDB.getBt(StOb.getIdBankDepoWith());
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
                    StData.setAll(dontDeleteDB.getSupplierTransaction("SELECT * FROM SUPPLIER_TRANSACTION order by DATE DESC ;"));
                    StTable.setItems(StData);
                    dontDeleteDB.closeDatabase();
                    BankListController.sendBtOb = null;
                } else {
                    FrontController.PdDeliveryNo = StOb.getDeliveryNo();
                    //System.out.println(SoOrderNo);
                    p = FXMLLoader.load(getClass().getResource("PurchaseDelivery.fxml"));
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
                    PdTable.setItems(dontDeleteDB.getPurchaseDelivery("SELECT * FROM PURCHASE_DELIVERY order by DATE DESC  ;"));
                    StData.setAll(dontDeleteDB.getSupplierTransaction("SELECT * FROM SUPPLIER_TRANSACTION order by DATE DESC ;"));
                    StTable.setItems(StData);
                    dontDeleteDB.closeDatabase();
                }
                FrontController.PdDeliveryNo = null;

            }
        }
    }

    @FXML
    private void StSearchButtClick(ActionEvent event) throws ParseException {
        DontDeleteDB dontDeleteDB = new DontDeleteDB();
        dontDeleteDB.connectDatabase();
        int where = 0;
        String maker = "SELECT * FROM SUPPLIER_TRANSACTION ";
        if (SlSupplierName.getText() != null && !"".equals(SlSupplierName.getText())) {

            maker = maker + "WHERE SUPPLIER='" + SlSupplierName.getText() + "'";
            where++;
        }
        if (SlDeliveryNo.getText() != null && !"".equals(SlDeliveryNo.getText())) {

            if (where > 0) {
                maker = maker + "AND DELIVERY_NO= '" + SlDeliveryNo.getText() + "'";
            } else {
                maker = maker + "WHERE DELIVERY_NO= '" + SlDeliveryNo.getText() + "'";
                where++;
            }

        }

        if (SlDateFrom.getText() != null && !"".equals(SlDateFrom.getText())) {
            DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            Date fromdate = format.parse(SlDateFrom.getText());
            java.sql.Date date = new java.sql.Date(fromdate.getTime());

            if (SlDateTo.getText() != null && !"".equals(SlDateTo.getText())) {

                Date todate = format.parse(SlDateTo.getText());
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

        StTable.setItems(dontDeleteDB.getSupplierTransaction(maker + " order by DATE DESC  ;"));
        dontDeleteDB.closeDatabase();

    }

    @FXML
    private void PoViewButtClick(ActionEvent event) throws IOException {
        PurchaseOrderDB PoOb = PoTable.getSelectionModel().getSelectedItem();
        if (PoOb == null) {
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
                FrontController.PoOrderNo = PoOb.getOrderNo();

                p = FXMLLoader.load(getClass().getResource("PurchaseOrder.fxml"));
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
                PoTable.setItems(dontDeleteDB.getPurchaseOrder("SELECT * FROM PURCHASE_ORDER  order by DATE DESC ;"));
                dontDeleteDB.closeDatabase();
            }
            FrontController.PoOrderNo = null;
        }
    }

    @FXML
    private void PoSearchButtClick(ActionEvent event) throws ParseException {
        DontDeleteDB dontDeleteDB = new DontDeleteDB();
        dontDeleteDB.connectDatabase();
        int where = 0;
        String maker = "SELECT * FROM PURCHASE_ORDER ";
        if (SlSupplierName.getText() != null && !"".equals(SlSupplierName.getText())) {

            maker = maker + "WHERE SUPPLIER='" + SlSupplierName.getText() + "'";
            where++;
        }
        if (SlOrderNo.getText() != null && !"".equals(SlOrderNo.getText())) {

            if (where > 0) {
                maker = maker + "AND ORDER_NO= '" + SlOrderNo.getText() + "'";
            } else {
                maker = maker + "WHERE ORDER_NO= '" + SlOrderNo.getText() + "'";
                where++;
            }

        }

        if (SlDateFrom.getText() != null && !"".equals(SlDateFrom.getText())) {
            DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            Date fromdate = format.parse(SlDateFrom.getText());
            java.sql.Date date = new java.sql.Date(fromdate.getTime());

            if (SlDateTo.getText() != null && !"".equals(SlDateTo.getText())) {

                Date todate = format.parse(SlDateTo.getText());
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

        PoTable.setItems(dontDeleteDB.getPurchaseOrder(maker + "  order by DATE DESC ;"));
        dontDeleteDB.closeDatabase();
    }

    @FXML
    private void PdViewButtClick(ActionEvent event) throws IOException {
        PurchaseDeliveryDB PdOb = PdTable.getSelectionModel().getSelectedItem();
        if (PdOb == null) {
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
                FrontController.PdDeliveryNo = PdOb.getDeliveryNo();
                p = FXMLLoader.load(getClass().getResource("PurchaseDelivery.fxml"));
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
                PdTable.setItems(dontDeleteDB.getPurchaseDelivery("SELECT * FROM PURCHASE_DELIVERY  order by DATE DESC ;"));
                StData.setAll(dontDeleteDB.getSupplierTransaction("SELECT * FROM SUPPLIER_TRANSACTION order by DATE DESC ;"));
                StTable.setItems(StData);
                dontDeleteDB.closeDatabase();

                FrontController.PdDeliveryNo = null;
            }
        }
    }

    @FXML
    private void PdSearchButtClick(ActionEvent event) throws ParseException {
        DontDeleteDB dontDeleteDB = new DontDeleteDB();
        dontDeleteDB.connectDatabase();
        int where = 0;
        String maker = "SELECT * FROM PURCHASE_DELIVERY ";
        if (SlSupplierName.getText() != null && !"".equals(SlSupplierName.getText())) {

            maker = maker + "WHERE SUPPLIER='" + SlSupplierName.getText() + "'";
            where++;
        }
        if (SlDeliveryNo.getText() != null && !"".equals(SlDeliveryNo.getText())) {

            if (where > 0) {
                maker = maker + "AND DELIVERY_NO= '" + SlDeliveryNo.getText() + "'";
            } else {
                maker = maker + "WHERE DELIVERY_NO= '" + SlDeliveryNo.getText() + "'";
                where++;
            }
        }
        if (SlOrderNo.getText() != null && !"".equals(SlOrderNo.getText())) {

            if (where > 0) {
                maker = maker + "AND ORDER_NO= '" + SlOrderNo.getText() + "'";
            } else {
                maker = maker + "WHERE ORDER_NO= '" + SlOrderNo.getText() + "'";
                where++;
            }
        }

        if (SlDateFrom.getText() != null && !"".equals(SlDateFrom.getText())) {
            DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            Date fromdate = format.parse(SlDateFrom.getText());
            java.sql.Date date = new java.sql.Date(fromdate.getTime());

            if (SlDateTo.getText() != null && !"".equals(SlDateTo.getText())) {

                Date todate = format.parse(SlDateTo.getText());
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

        PdTable.setItems(dontDeleteDB.getPurchaseDelivery(maker + " order by DATE DESC  ;"));
        dontDeleteDB.closeDatabase();
    }
}

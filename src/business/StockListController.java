/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import java.io.IOException;
import java.net.URL;
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
public class StockListController implements Initializable {

    //Shadman AutoSuggetion starts here
    private AnchorPane SlAnchorPane;

    private AutoCompleteTextField SlProductName;
    private AutoCompleteTextField SlStorageName;
    private AutoCompleteTextField SlSupplierName;
    @FXML
    private TableColumn<TotalStockDB, String> TsProductNameCol;
    @FXML
    private TableColumn<TotalStockDB, String> TsStorageCol;
    @FXML
    private TableColumn<TotalStockDB, String> TsQuantityCol;
    @FXML
    private TableColumn<TotalStockDB, String> TsUnitCol;
    @FXML
    private TableColumn<TotalStockDB, String> TsTotalKgCol;
    @FXML
    private TableColumn<TotalStockDB, String> TsToalMonCol;
    @FXML
    private TableColumn<UpcomingStockDB, String> UsSupplierNameCol;
    @FXML
    private TableColumn<UpcomingStockDB, String> UsOrderNoCol;
    @FXML
    private TableColumn<UpcomingStockDB, String> UsProductNameCol;
    @FXML
    private TableColumn<UpcomingStockDB, String> UsOrderQuantityCol;
    @FXML
    private TableColumn<UpcomingStockDB, String> UsDeliveryQuantityCol;
    @FXML
    private TableColumn<UpcomingStockDB, String> UsUpcomingStockCol;

    @FXML
    private TableColumn<PendingDeliveriesDB, String> PdOrderNoCol;
    @FXML
    private TableColumn<PendingDeliveriesDB, String> PdProductNameCol;
    @FXML
    private TableColumn<PendingDeliveriesDB, String> PdCustomerCol;
    @FXML
    private TableColumn<PendingDeliveriesDB, String> PdOrderQuantityCol;
    @FXML
    private TableColumn<PendingDeliveriesDB, String> PdDeliveryQuantityCol;
    @FXML
    private TableColumn<PendingDeliveriesDB, String> PdPendingQuantityCol;
    @FXML
    private TableView<TotalStockDB> TsTable;
    @FXML
    private TableView<UpcomingStockDB> UsTable;
    @FXML
    private TableView<PendingDeliveriesDB> PdTable;
    @FXML
    private Button SlSearchButt;

    ObservableList<TotalStockDB> TsData = FXCollections.observableArrayList();
    ObservableList<TotalStockDB> TsTemp = FXCollections.observableArrayList();
    ObservableList<TotalStockDB> TsSearchTemp = FXCollections.observableArrayList();

    ObservableList<UpcomingStockDB> UsData = FXCollections.observableArrayList();
    ObservableList<UpcomingStockDB> UsTemp = FXCollections.observableArrayList();
    ObservableList<UpcomingStockDB> UsSearchTemp = FXCollections.observableArrayList();

    ObservableList<PendingDeliveriesDB> PdData = FXCollections.observableArrayList();
    ObservableList<PendingDeliveriesDB> PdTemp = FXCollections.observableArrayList();
    ObservableList<PendingDeliveriesDB> PdSearchTemp = FXCollections.observableArrayList();
    @FXML
    private Button UsSearchButt;

    @FXML
    private VBox SlProductV;
    @FXML
    private VBox SlStorageV;
    @FXML
    private TableColumn<TotalStockDB, String> TsExtraCol;
    @FXML
    private Button SlViewButt;

    public static TotalStockDB sendTsOb = null;
    @FXML
    private Button SlTransferProduct;
    @FXML
    private Button SlNewProduct;
    @FXML
    private TextField SlDateFrom;
    @FXML
    private TextField SlDateTo;
    @FXML
    private TableColumn<UpcomingStockDB, String> UsStorageCol;
    @FXML
    private VBox SlSupplierV;
    @FXML
    private Button PdSearchButt;
    @FXML
    private Button UsDeleteButt;
    @FXML
    private Button PdDeleteButt;
    @FXML
    private TableColumn<UpcomingStockDB, String> UsRateMon;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //connecting the database
        Database database = new Database();
        database.connect_database();

        DontDeleteDB dontDeleteDB = new DontDeleteDB();

        //PRODUCT NAME
        SlProductName = new AutoCompleteTextField();
        SlProductName.setEntries(database.getSuggestionList("SELECT * FROM TOTAL_STOCK;", "PRODUCT"));
        SlProductName.setMaxSize(150, 25);
        SlProductName.setPromptText("PRODUCT");
        SlProductV.getChildren().add(SlProductName);

        //STORAGE NAME
        SlStorageName = new AutoCompleteTextField();
        SlStorageName.setEntries(database.getSuggestionList("SELECT * FROM TOTAL_STOCK;", "STORAGE"));
        SlStorageName.setMaxSize(150, 25);
        SlStorageName.setPromptText("STORAGE");
        SlStorageV.getChildren().add(SlStorageName);

        SlSupplierName = new AutoCompleteTextField();
        SlSupplierName.setEntries(database.getSuggestionList("SELECT * FROM SUPPLIER_LIST;", "SUPPLIER"));
        SlSupplierName.setMaxSize(150, 25);
        SlSupplierName.setPromptText("SUPPLIER");
        SlSupplierV.getChildren().add(SlSupplierName);

        dontDeleteDB.connectDatabase();

        //TOTAL STOCK TABLE
        TsProductNameCol.setCellValueFactory(new PropertyValueFactory<>("product"));
        TsStorageCol.setCellValueFactory(new PropertyValueFactory<>("storage"));
        TsQuantityCol.setCellValueFactory(new PropertyValueFactory<>("quantity2"));
        TsUnitCol.setCellValueFactory(new PropertyValueFactory<>("avgrate2"));
        TsTotalKgCol.setCellValueFactory(new PropertyValueFactory<>("totalKg2"));
        TsToalMonCol.setCellValueFactory(new PropertyValueFactory<>("totalMon2"));
        TsExtraCol.setCellValueFactory(new PropertyValueFactory<>("extra"));

        TsData.setAll(dontDeleteDB.getTotalStock("SELECT * FROM TOTAL_STOCK order by DATE DESC;"));

        TsTable.setItems(TsData);

        UsProductNameCol.setCellValueFactory(new PropertyValueFactory<>("product"));
        UsSupplierNameCol.setCellValueFactory(new PropertyValueFactory<>("supplier"));
        UsDeliveryQuantityCol.setCellValueFactory(new PropertyValueFactory<>("deliveryQuantity2"));
        UsOrderNoCol.setCellValueFactory(new PropertyValueFactory<>("orderNo"));
        UsOrderQuantityCol.setCellValueFactory(new PropertyValueFactory<>("orderQuantity2"));
        UsUpcomingStockCol.setCellValueFactory(new PropertyValueFactory<>("upcomingQuantity2"));
        UsStorageCol.setCellValueFactory(new PropertyValueFactory<>("storage"));
        UsRateMon.setCellValueFactory(new PropertyValueFactory<>("orderUnit2"));

        UsData.setAll(dontDeleteDB.getUpcomingStock("SELECT * FROM UPCOMING_STOCK order by DATE DESC;"));
        UsTable.setItems(UsData);

        PdProductNameCol.setCellValueFactory(new PropertyValueFactory<>("product"));
        PdCustomerCol.setCellValueFactory(new PropertyValueFactory<>("customer"));
        PdDeliveryQuantityCol.setCellValueFactory(new PropertyValueFactory<>("deliveryQuantity2"));
        PdOrderNoCol.setCellValueFactory(new PropertyValueFactory<>("orderNo"));
        PdOrderQuantityCol.setCellValueFactory(new PropertyValueFactory<>("orderQuantity2"));
        PdPendingQuantityCol.setCellValueFactory(new PropertyValueFactory<>("pendingQuantity2"));

        PdData.setAll(dontDeleteDB.getPendingDeliveries("SELECT * FROM PENDING_DELIVERIES order by DATE DESC;"));
        PdTable.setItems(PdData);

        database.close_database();
        dontDeleteDB.closeDatabase();

    }

    @FXML
    private void NewButtClick(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        Parent p = FXMLLoader.load(getClass().getResource("NewProduct.fxml"));
        stage = new Stage();
        Scene scene = new Scene(p);
        stage.initOwner(
                ((Node) event.getSource()).getScene().getWindow());
        stage.setScene(scene);
        stage.setResizable(false);
        stage.showAndWait();
        DontDeleteDB dontDeleteDB = new DontDeleteDB();
        dontDeleteDB.connectDatabase();
        TsData.setAll(dontDeleteDB.getTotalStock("SELECT * FROM TOTAL_STOCK order by DATE DESC;"));
        SlProductName.setEntries(dontDeleteDB.getSuggestionList("SELECT * FROM TOTAL_STOCK;", "PRODUCT"));
        SlStorageName.setEntries(dontDeleteDB.getSuggestionList("SELECT * FROM TOTAL_STOCK;", "STORAGE"));
        
        TsTable.setItems(TsData);
        dontDeleteDB.closeDatabase();
    }

    @FXML
    private void SlViewButtClick(ActionEvent event) throws IOException {
        TotalStockDB TsOb = TsTable.getSelectionModel().getSelectedItem();
        if (TsOb == null) {
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
                sendTsOb = TsOb;
                //System.out.println(SoOrderNo);
                p = FXMLLoader.load(getClass().getResource("NewProduct.fxml"));
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
                TsData.setAll(dontDeleteDB.getTotalStock("SELECT * FROM TOTAL_STOCK order by DATE DESC;"));
                dontDeleteDB.closeDatabase();
                sendTsOb = null;
            }
        }
    }

    @FXML
    private void SlTransferProductClick(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        Parent p = FXMLLoader.load(getClass().getResource("TransferProduct.fxml"));

        Scene scene = new Scene(p);
        stage.initOwner(
                ((Node) event.getSource()).getScene().getWindow());
        stage.setScene(scene);
        stage.setResizable(false);
        stage.showAndWait();
        DontDeleteDB dontDeleteDB = new DontDeleteDB();
        dontDeleteDB.connectDatabase();
        TsData.setAll(dontDeleteDB.getTotalStock("SELECT * FROM TOTAL_STOCK order by DATE DESC;"));

        TsTable.setItems(TsData);
        dontDeleteDB.closeDatabase();

    }

    @FXML
    private void SlSearchButtClick(ActionEvent event) throws ParseException {
        DontDeleteDB dontDeleteDB = new DontDeleteDB();
        dontDeleteDB.connectDatabase();
        int where = 0;
        String maker = "SELECT * FROM TOTAL_STOCK ";
        if (SlProductName.getText() != null && !"".equals(SlProductName.getText())) {

            maker = maker + "WHERE PRODUCT='" + SlProductName.getText() + "'";
            where++;
        }
        if (SlStorageName.getText() != null && !"".equals(SlStorageName.getText())) {

            if (where > 0) {
                maker = maker + "AND STORAGE= '" + SlStorageName.getText() + "'";
            } else {
                maker = maker + "WHERE STORAGE= '" + SlStorageName.getText() + "'";
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

        TsTable.setItems(dontDeleteDB.getTotalStock(maker + " order by DATE DESC  ;"));
        dontDeleteDB.closeDatabase();

    }

    @FXML
    private void UsSearchButtClick(ActionEvent event) throws ParseException {
        DontDeleteDB dontDeleteDB = new DontDeleteDB();
        dontDeleteDB.connectDatabase();
        int where = 0;
        String maker = "SELECT * FROM UPCOMING_STOCK ";
        if (SlProductName.getText() != null && !"".equals(SlProductName.getText())) {

            maker = maker + "WHERE PRODUCT='" + SlProductName.getText() + "'";
            where++;
        }
        if (SlStorageName.getText() != null && !"".equals(SlStorageName.getText())) {

            if (where > 0) {
                maker = maker + "AND STORAGE= '" + SlStorageName.getText() + "'";
            } else {
                maker = maker + "WHERE STORAGE= '" + SlStorageName.getText() + "'";
                where++;
            }
        }

        if (SlSupplierName.getText() != null && !"".equals(SlSupplierName.getText())) {

            if (where > 0) {
                maker = maker + "AND SUPPLIER= '" + SlSupplierName.getText() + "'";
            } else {
                maker = maker + "WHERE SUPPLIER= '" + SlSupplierName.getText() + "'";
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

        UsTable.setItems(dontDeleteDB.getUpcomingStock(maker + " order by DATE DESC  ;"));
        dontDeleteDB.closeDatabase();
    }

    @FXML
    private void PdSearchButtClick(ActionEvent event) throws ParseException {
        DontDeleteDB dontDeleteDB = new DontDeleteDB();
        dontDeleteDB.connectDatabase();
        int where = 0;
        String maker = "SELECT * FROM PENDING_DELIVERIES ";
        if (SlProductName.getText() != null && !"".equals(SlProductName.getText())) {

            maker = maker + "WHERE PRODUCT='" + SlProductName.getText() + "'";
            where++;
        }
        if (SlStorageName.getText() != null && !"".equals(SlStorageName.getText())) {

            if (where > 0) {
                maker = maker + "AND STORAGE= '" + SlStorageName.getText() + "'";
            } else {
                maker = maker + "WHERE STORAGE= '" + SlStorageName.getText() + "'";
                where++;
            }
        }

        if (SlSupplierName.getText() != null && !"".equals(SlSupplierName.getText())) {

            if (where > 0) {
                maker = maker + "AND SUPPLIER= '" + SlSupplierName.getText() + "'";
            } else {
                maker = maker + "WHERE SUPPLIER= '" + SlSupplierName.getText() + "'";
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

        UsTable.setItems(dontDeleteDB.getUpcomingStock(maker + " order by DATE DESC  ;"));
        dontDeleteDB.closeDatabase();
    }

    @FXML
    private void UsDeleteButtClick(ActionEvent event) throws IOException {
        UpcomingStockDB UsOb = UsTable.getSelectionModel().getSelectedItem();
        if (UsOb == null) {
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
                //UPCOMING QUANTITY
                DontDeleteDB dontDeleteDB = new DontDeleteDB();
                dontDeleteDB.connectDatabase();
                String query = "DELETE FROM UPCOMING_STOCK WHERE PRODUCT= '" + UsOb.getProduct()
                        + "' AND ORDER_NO= '" + UsOb.getOrderNo() + "'";
                dontDeleteDB.delete(query);

                UsData.setAll(dontDeleteDB.getUpcomingStock("SELECT * FROM UPCOMING_STOCK order by DATE DESC;"));
                UsTable.setItems(UsData);

                dontDeleteDB.closeDatabase();
            }
        }
    }

    @FXML
    private void PdDeleteButtClick(ActionEvent event
    ) {
    }

}

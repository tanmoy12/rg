/* * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import static business.FrontController.newSet;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
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
import javafx.scene.control.MenuButton;
import javafx.scene.control.RadioMenuItem;
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
public class PurchaseDeliveryController implements Initializable {

    @FXML
    private VBox PdSupplierV;
    @FXML
    private VBox PdInstituteV;
    @FXML
    private VBox PdOrderNoV;
    @FXML
    private TextField PdLp;
    @FXML
    private VBox PdQuantityV;
    @FXML
    private VBox PdStorageV;
    @FXML
    private VBox PdProductV;
    @FXML
    private TextField PdUnit;
    @FXML
    private MenuButton PdTypeDrop;
    @FXML
    private RadioMenuItem PdTypeKg;
    @FXML
    private RadioMenuItem PdTypeMon;
    @FXML
    private RadioMenuItem PdTypePiece;
    @FXML
    private VBox PdRateKgV;
    @FXML
    private VBox PdRateMonV;

    ///purchase delivery
    private AutoCompleteTextField PdSupplier;
    private AutoCompleteTextField PdOrderNo;
    private AutoCompleteTextField PdInstituteName;
    private AutoCompleteTextField PdProduct;
    private AutoCompleteTextField PdStorage;

    private IntegerTextField PdQuantity;
    private IntegerTextField PdRateKg;
    private IntegerTextField PdRateMon;
    private IntegerTextField PdPrice;
    @FXML
    private TableColumn<PurchaseDeliveryDB, String> PdProductCol;
    @FXML
    private TableColumn<PurchaseDeliveryDB, String> PdStorageCol;
    @FXML
    private TableColumn<PurchaseDeliveryDB, String> PdQuantityCol;
    @FXML
    private TableColumn<PurchaseDeliveryDB, String> PdUnitCol;
    @FXML
    private TableColumn<PurchaseDeliveryDB, String> PdRateKgCol;
    @FXML
    private TableColumn<PurchaseDeliveryDB, String> PdRateMonCol;
    @FXML
    private TableColumn<PurchaseDeliveryDB, String> PdPriceCol;
    @FXML
    private VBox PdPriceV;
    @FXML
    private Button PdEnterProductInfoButt;
    @FXML
    private TableView<PurchaseDeliveryDB> PdTable;

    ObservableList<PurchaseDeliveryDB> PdData = FXCollections.observableArrayList();
    ObservableList<PurchaseDeliveryDB> PdTableData = FXCollections.observableArrayList();

    public static boolean flagDelete = false;

    @FXML
    private TextField PdDeliveryNo;
    @FXML
    private Button PdDeleteButt;
    @FXML
    private Button PdEditButt;

    PurchaseDeliveryDB editOb = new PurchaseDeliveryDB();

    private int totalAmount = 0;
    @FXML
    private TextField TotalAmount;
    @FXML
    private Button PdDeleteOrderButt;
    private String setdelivery = null;
    @FXML
    private Button PdSaveDeliveryButt;

    Timestamp dateTimeNow = new java.sql.Timestamp(new java.util.Date().getTime());

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        DontDeleteDB dontDeleteDB = new DontDeleteDB();
        dontDeleteDB.connectDatabase();
        //connecting the database
        Database database = new Database();
        database.connect_database();

        PdProduct = new AutoCompleteTextField();
        PdProduct.setEntries(database.getSuggestionList("SELECT * FROM TOTAL_STOCK;", "PRODUCT"));
        PdProduct.setMaxSize(150, 25);
        PdProduct.setPromptText("PRODUCT");
        PdProductV.getChildren().add(PdProduct);

        PdOrderNo = new AutoCompleteTextField();
        PdOrderNo.setEntries(database.getSuggestionList("SELECT * FROM PURCHASE_ORDER;", "ORDER_NO"));
        PdOrderNo.setMaxSize(150, 25);
        PdOrderNo.setPromptText("ORDER NO");
        PdOrderNoV.getChildren().add(PdOrderNo);

        PdStorage = new AutoCompleteTextField();
        PdStorage.setEntries(database.getSuggestionList("SELECT * FROM TOTAL_STOCK;", "STORAGE"));
        PdStorage.setMaxSize(150, 25);
        PdStorage.setPromptText("STORAGE");
        PdStorageV.getChildren().add(PdStorage);

        PdSupplier = new AutoCompleteTextField();
        PdSupplier.setEntries(database.getSuggestionList("SELECT * FROM SUPPLIER_LIST;", "SUPPLIER"));
        PdSupplier.setMaxSize(150, 25);
        PdSupplier.setPromptText("SUPPLIER");
        PdSupplierV.getChildren().add(PdSupplier);

        PdInstituteName = new AutoCompleteTextField();
        PdInstituteName.setEntries(database.getSuggestionList("SELECT * FROM SUPPLIER_LIST;", "INSTITUTE"));
        PdInstituteName.setMaxSize(150, 25);
        PdInstituteName.setPromptText("INSTITUTE");
        PdInstituteV.getChildren().add(PdInstituteName);

        PdProductCol.setCellValueFactory(new PropertyValueFactory<>("product"));
        PdQuantityCol.setCellValueFactory(new PropertyValueFactory<>("quantity2"));
        PdRateKgCol.setCellValueFactory(new PropertyValueFactory<>("rateKg2"));
        PdRateMonCol.setCellValueFactory(new PropertyValueFactory<>("rateMon2"));
        PdPriceCol.setCellValueFactory(new PropertyValueFactory<>("price2"));
        PdStorageCol.setCellValueFactory(new PropertyValueFactory<>("storage"));
        PdUnitCol.setCellValueFactory(new PropertyValueFactory<>("unit2"));

        PdQuantity = new IntegerTextField();
        PdRateKg = new IntegerTextField();
        PdRateMon = new IntegerTextField();
        PdPrice = new IntegerTextField();

        PdQuantity.setPromptText("QUANTITY");
        PdQuantity.setMaxWidth(150);
        PdRateKg.setPromptText("/KG");
        PdRateKg.setMaxWidth(150);
        PdRateMon.setPromptText("/MON");
        PdRateMon.setMaxWidth(150);
        PdPrice.setPromptText("/PRICE");
        PdPrice.setMaxWidth(150);

        PdQuantityV.getChildren().add(PdQuantity);
        PdPriceV.getChildren().add(PdPrice);
        PdRateKgV.getChildren().add(PdRateKg);
        PdRateMonV.getChildren().add(PdRateMon);
        
        PdDeliveryNo.setEditable(false);

        dontDeleteDB.closeDatabase();
        database.close_database();

        PdRateKg.setOnAction((ActionEvent evt) -> {
            double rateKg = IntegerTextField.doubleConvert(PdRateKg.getText());
            double rateMon = rateKg * 37.324;
            double totalKg = 0;
            if ("KG".equals(PdTypeDrop.getText())) {
                totalKg = IntegerTextField.doubleConvert(PdQuantity.getText()) * IntegerTextField.doubleConvert(PdUnit.getText());
                PdRateMon.setText(IntegerTextField.numformat(rateMon));
                PdPrice.setText(IntegerTextField.numformat(totalKg * rateKg));
            } else if ("MON".equals(PdTypeDrop.getText())) {
                totalKg = IntegerTextField.doubleConvert(PdQuantity.getText()) * IntegerTextField.doubleConvert(PdUnit.getText()) * 37.324;
                PdRateMon.setText(IntegerTextField.numformat(rateMon));
                PdPrice.setText(IntegerTextField.numformat(totalKg * rateKg));
            } else if ("PIECE".equals(PdTypeDrop.getText())) {
                totalKg = IntegerTextField.doubleConvert(PdQuantity.getText()) * IntegerTextField.doubleConvert(PdUnit.getText());
                PdPrice.setText(IntegerTextField.numformat(totalKg * rateKg));
                PdRateMon.setText(null);
            }

        });

        PdRateMon.setOnAction((ActionEvent evt) -> {
            double rateMon = IntegerTextField.doubleConvert(PdRateMon.getText());
            double rateKg = rateMon / 37.324;
            double totalKg = 0;
            if ("KG".equals(PdTypeDrop.getText())) {
                totalKg = IntegerTextField.doubleConvert(PdQuantity.getText()) * IntegerTextField.doubleConvert(PdUnit.getText());
            } else if ("MON".equals(PdTypeDrop.getText())) {
                totalKg = IntegerTextField.doubleConvert(PdQuantity.getText()) * IntegerTextField.doubleConvert(PdUnit.getText()) * 37.324;
            }
            PdRateKg.setText(IntegerTextField.doubleformat(rateKg));
            PdPrice.setText(IntegerTextField.numformat(totalKg * rateKg));
        });

        PdSupplier.setOnAction((ActionEvent evt) -> {
            PdInstituteName.setText(getInstitute(PdSupplier.getText()));
            PdInstituteName.requestFocus();
            generateDelivery();
        });

        PdOrderNo.setOnAction((ActionEvent evt) -> {
            setOrder(PdOrderNo.getText());
            generateDelivery();
        });

        PdEnterProductInfoButt.disableProperty().bind(
                Bindings.isEmpty(PdInstituteName.textProperty())
                .or(Bindings.isEmpty(PdOrderNo.textProperty()))
                .or(Bindings.isEmpty(PdProduct.textProperty()))
                .or(Bindings.isEmpty(PdStorage.textProperty()))
                .or(Bindings.isEmpty(PdSupplier.textProperty()))
                .or(Bindings.isEmpty(PdUnit.textProperty()))
                .or(Bindings.isEmpty(PdQuantity.textProperty()))
                .or(Bindings.isEmpty(PdPrice.textProperty()))
        );

        if (FrontController.PdDeliveryNo != null) {
            setPurchaseDelivery(FrontController.PdDeliveryNo);
        }

    }

    private void setPurchaseDelivery(String deliveryno) {
        DontDeleteDB dontDeleteDB = new DontDeleteDB();
        dontDeleteDB.connectDatabase();

        String sql = "SELECT * FROM PURCHASE_DELIVERY WHERE DELIVERY_NO='" + deliveryno + "'";
        PdTableData.addAll(dontDeleteDB.getPurchaseDelivery(sql));
        PdTable.setItems(PdTableData);

        for (PurchaseDeliveryDB Po : PdTableData) {
            PdSupplier.setText(Po.getSupplier());
            PdInstituteName.setText(Po.getInstitute());
            PdOrderNo.setText(Po.getOrderNo());
            PdDeliveryNo.setText(deliveryno);
            setdelivery = deliveryno;
            PdLp.setText(Po.getLp());
            dateTimeNow = Po.getDate();
            break;
        }
        setTotal();
        PdDeleteOrderButt.setDisable(false);

        PdSaveDeliveryButt.setText("SAVE");
        dontDeleteDB.closeDatabase();
    }

    private void generateDelivery() {
        DontDeleteDB dontDeleteDB = new DontDeleteDB();
        dontDeleteDB.connectDatabase();

        String query = "SELECT MAX(DELIVERY_NO) AS ID FROM PURCHASE_DELIVERY;";
        int serial = dontDeleteDB.getAIntDB(query, "ID");
        serial++;
        String sl;
        if (serial < 1000) {
            sl = String.format("%03d", serial);
        } else {
            sl = String.valueOf(serial);
        }
        PdDeliveryNo.setText(sl);

        dontDeleteDB.closeDatabase();
    }

    private void setTotal() {

        totalAmount = 0;
        for (PurchaseDeliveryDB Pd : PdTableData) {
            totalAmount += Pd.getPrice();
        }
        TotalAmount.setText(IntegerTextField.numformat(totalAmount));
    }

    private String getInstitute(String party) {

        DontDeleteDB dontDeleteDB = new DontDeleteDB();
        dontDeleteDB.connectDatabase();

        String mySql = "SELECT INSTITUTE FROM SUPPLIER_LIST WHERE SUPPLIER= '"
                + party + "'";
        String Ins = dontDeleteDB.getAStringDB(mySql, "INSTITUTE");

        dontDeleteDB.closeDatabase();
        return Ins;

    }

    private void setOrder(String order) {
        DontDeleteDB dontDeleteDB = new DontDeleteDB();
        dontDeleteDB.connectDatabase();

        String sql = "SELECT SUPPLIER from PURCHASE_ORDER where ORDER_NO='" + PdOrderNo.getText() + "'limit 1";
        String sup = dontDeleteDB.getAStringDB(sql, "SUPPLIER");
        PdSupplier.setText(sup);
        PdSupplier.requestFocus();
        sql = "SELECT INSTITUTE from PURCHASE_ORDER where ORDER_NO='" + PdOrderNo.getText() + "'limit 1";
        String ins = dontDeleteDB.getAStringDB(sql, "INSTITUTE");
        PdInstituteName.setText(ins);
        PdInstituteName.requestFocus();
        dontDeleteDB.closeDatabase();
    }

    @FXML
    private void PdEnterProductInfo(ActionEvent event) throws IOException, SQLException {
        
        DontDeleteDB dontDeleteDB = new DontDeleteDB();
        dontDeleteDB.connectDatabase();

        String mySql = "SELECT PURCHASE_ORDER_ID FROM PURCHASE_ORDER WHERE ORDER_NO= '" + PdOrderNo.getText() + "'";
        int id = dontDeleteDB.getAIntDB(mySql, "PURCHASE_ORDER_ID");
        if (id == 0) {
            FrontController.error = "ORDER NO. not Found!!";
            Parent p = FXMLLoader.load(getClass().getResource("Error.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(p);

            stage.setScene(scene);
            stage.setX(480);
            stage.setY(250);
            stage.showAndWait();

            return;

        }

        String stockSql = "SELECT TOTAL_STOCK_ID FROM TOTAL_STOCK WHERE PRODUCT= '"
                + PdProduct.getText()
                + "' AND STORAGE= '" + PdStorage.getText() + "';";
        int stockid = dontDeleteDB.getAIntDB(stockSql, "TOTAL_STOCK_ID");

        if (stockid == 0) {
            FrontController.error = "Stock details not Found!!";
            Parent p = FXMLLoader.load(getClass().getResource("Error.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(p);

            stage.setScene(scene);
            stage.setX(480);
            stage.setY(250);
            stage.showAndWait();

            return;

        }

        dontDeleteDB.closeDatabase();
        
        if ("SAVE".equals(PdEnterProductInfoButt.getText())) {
            PdTableData.remove(editOb);
            editOb = null;
            PdEnterProductInfoButt.setText("ENTRY");
            PdEditButt.setText("EDIT");
        }

        PurchaseDeliveryDB ob = new PurchaseDeliveryDB();

        ob.setDate(dateTimeNow);
        ob.setInstitute(PdInstituteName.getText());
        ob.setRateKg(IntegerTextField.doubleConvert(PdRateKg.getText()));
        ob.setRateKg2(PdRateKg.getText());
        ob.setLp(PdLp.getText());
        ob.setStorage(PdStorage.getText());
        ob.setRateMon(IntegerTextField.doubleConvert(PdRateMon.getText()));
        ob.setRateMon2(PdRateMon.getText());
        ob.setOrderNo(PdOrderNo.getText());
        ob.setPrice(IntegerTextField.doubleConvert(PdPrice.getText()));
        ob.setPrice2(PdPrice.getText());
        ob.setProduct(PdProduct.getText());
        ob.setQuantity(IntegerTextField.doubleConvert(PdQuantity.getText()));
        ob.setQuantity2(PdQuantity.getText());
        ob.setSupplier(PdSupplier.getText());
        ob.setUnit(IntegerTextField.doubleConvert(PdUnit.getText()));
        ob.setUnit2(PdUnit.getText() + PdTypeDrop.getText());
        ob.setUnitType(PdTypeDrop.getText());
        ob.setDeliveryNo(PdDeliveryNo.getText());
        ob.setUser(FrontController.user);

        PdTableData.add(ob);

        PdTable.setItems(PdTableData);
        setTotal();

        PdPrice.clear();
        PdProduct.clear();
        PdQuantity.clear();
        PdRateKg.clear();
        PdRateMon.clear();
        PdStorage.clear();

        PdUnit.clear();

    }

    private void setDeleteAll(String deliveryno) throws IOException, SQLException {
        DontDeleteDB dontDeleteDB = new DontDeleteDB();
        dontDeleteDB.connectDatabase();
        PdData.addAll(dontDeleteDB.getPurchaseDelivery("SELECT * from PURCHASE_DELIVERY WHERE DELIVERY_NO='"
                + deliveryno + "'"));

        for (PurchaseDeliveryDB po : PdData) {
            deletePurchaseDelivery(po);
        }
        dontDeleteDB.closeDatabase();
    }

    @FXML
    void insertPurchaseDelivery() throws SQLException, IOException {

        DontDeleteDB dontDeleteDB = new DontDeleteDB();
        dontDeleteDB.connectDatabase();

        if ("SAVE".equals(PdSaveDeliveryButt.getText())) {
            setDeleteAll(setdelivery);
        }

        for (PurchaseDeliveryDB ob : PdTableData) {
            ob.setSupplier(PdSupplier.getText());
            ob.setInstitute(PdInstituteName.getText());
            ob.setOrderNo(PdOrderNo.getText());
            ob.setDeliveryNo(PdDeliveryNo.getText());
            insertDelivery(ob);
            //inserting purchasedelivery table

        }
        dontDeleteDB.closeDatabase();

        Stage stage = (Stage) PdEditButt.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void PdDeleteButtClick(ActionEvent event) throws IOException, SQLException {
        PurchaseDeliveryDB PdOb = PdTable.getSelectionModel().getSelectedItem();
        if (PdOb == null) {
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
            PdTableData.remove(PdOb);
            newSet = false;
        }
        DontDeleteDB dontDeleteDB = new DontDeleteDB();

        dontDeleteDB.connectDatabase();

        PdTable.setItems(PdTableData);
        setTotal();

        dontDeleteDB.closeDatabase();

    }

    @FXML
    private void PdEditButtClick(ActionEvent event) throws IOException, SQLException {

        if ("CANCEL".equals(PdEditButt.getText())) {
            PdProduct.clear();
            PdStorage.clear();
            PdQuantity.clear();
            PdUnit.clear();
            PdRateKg.clear();
            PdRateKg.clear();
            PdRateMon.clear();
            PdEnterProductInfoButt.setText("ENTRY");
            PdEditButt.setText("EDIT");

        } else if ("EDIT".equals(PdEditButt.getText())) {
            PurchaseDeliveryDB PdOb = PdTable.getSelectionModel().getSelectedItem();
            if (PdOb == null) {
                return;
            } else {
                editOb = PdOb;
            }
            PdEditButt.setText("CANCEL");
            PdEnterProductInfoButt.setText("SAVE");
            PdSupplier.setText(PdOb.getSupplier());
            PdInstituteName.setText(PdOb.getInstitute());
            PdOrderNo.setText(PdOb.getOrderNo());
            PdLp.setText(PdOb.getLp());
            PdProduct.setText(PdOb.getProduct());
            PdStorage.setText(PdOb.getStorage());
            PdQuantity.setText(PdOb.getQuantity2());
            PdUnit.setText(String.valueOf(PdOb.getUnit()));
            PdTypeDrop.setText(PdOb.getUnitType());
            PdRateKg.setText(PdOb.getRateKg2());
            PdRateMon.setText(PdOb.getRateMon2());
            PdPrice.setText(PdOb.getPrice2());

        }

    }

    private void insertDelivery(PurchaseDeliveryDB ob) throws SQLException {

        DontDeleteDB dontDeleteDB = new DontDeleteDB();
        dontDeleteDB.connectDatabase();

        //inserting purchase delivery ends here
        dontDeleteDB.insertPurchaseDelivery(ob);
        String query = "Select PURCHASE_DELIVERY_ID FROM PURCHASE_DELIVERY ORDER BY PURCHASE_DELIVERY_ID DESC LIMIT 1";
        int purchaseDeliveryID = dontDeleteDB.getAIntDB(query, "PURCHASE_DELIVERY_ID");
        //inserting into supplier transaction

        SupplierTransactionDB obs = new SupplierTransactionDB();

        obs.setDate(ob.getDate());
        obs.setType(2);
        obs.setInstitute(PdInstituteName.getText());
        obs.setRateKg(ob.getRateKg());
        obs.setLp(ob.getLp());
        obs.setStorage(ob.getStorage());
        obs.setRateMon(ob.getRateMon());
        obs.setOrderNo(PdOrderNo.getText());
        obs.setCredit(ob.getPrice());
        obs.setProduct(ob.getProduct());
        obs.setQuantity(ob.getQuantity());
        obs.setSupplier(PdSupplier.getText());
        obs.setUnit(ob.getUnit());
        obs.setUnitType(ob.getUnitType());
        obs.setDeliveryNo(ob.getDeliveryNo());
        obs.setUser(ob.getUser());
        obs.setIdPurchaseDelivery(purchaseDeliveryID);

        dontDeleteDB.insertSupplierTransaction(obs);

        //starting supplier list
        String sqlslID = "SELECT SUPPLIER_LIST_ID FROM SUPPLIER_LIST WHERE SUPPLIER= '" + PdSupplier.getText()
                + "' AND INSTITUTE= '" + PdInstituteName.getText() + "'";
        int slID = dontDeleteDB.getAIntDB(sqlslID, "SUPPLIER_LIST_ID");

        String sqltotalDeilivery = "SELECT TOTAL_DELIVERY FROM SUPPLIER_LIST WHERE SUPPLIER_LIST_ID= " + slID + "";

        Double totalDelivery = dontDeleteDB.getADoubleDB(sqltotalDeilivery, "TOTAL_DELIVERY");
        String sqlbalanceDeilivery = "SELECT BALANCE_DELIVERY FROM SUPPLIER_LIST WHERE SUPPLIER_LIST_ID= " + slID + "";

        Double balanceDelivery = dontDeleteDB.getADoubleDB(sqlbalanceDeilivery, "BALANCE_DELIVERY");

        Double totalDeliveryNew = totalDelivery - ob.getPrice();
        Double balanceDeliveryNew = balanceDelivery - ob.getPrice();

        query = "update SUPPLIER_LIST set TOTAL_DELIVERY = ?"
                + "where SUPPLIER = ? AND INSTITUTE = ?";
        dontDeleteDB.setADoubleDB(query, (totalDeliveryNew),
                PdSupplier.getText(), PdInstituteName.getText());
        query = "update SUPPLIER_LIST set BALANCE_DELIVERY = ?"
                + "where SUPPLIER = ? AND INSTITUTE = ?";
        dontDeleteDB.setADoubleDB(query, (balanceDeliveryNew),
                PdSupplier.getText(), PdInstituteName.getText());

        //stock calculations
        String stockquery = "SELECT TOTAL_KG FROM TOTAL_STOCK WHERE PRODUCT= '" + ob.getProduct()
                + "' AND STORAGE= '" + ob.getStorage() + "'";
        double TKg = dontDeleteDB.getAIntDB(stockquery, "TOTAL_KG");
        double Tmon = TKg / 37.324;

        double TotalKg = 0, TotalMon = 0;

        if ("KG".equals(ob.getUnitType())) {
            TotalKg = ob.getQuantity() * ob.getUnit();
            TotalMon = TotalKg / 37.324;
        } else if ("MON".equals(ob.getUnitType())) {
            TotalMon = ob.getQuantity() * ob.getUnit();
            TotalKg = TotalMon * 37.324;
        } else if ("PIECE".equals(ob.getUnitType())) {
            TotalKg = ob.getQuantity() * ob.getUnit();
        }

        double TotalOrderKg=TotalKg;
        //plus plus in total stock
        TotalKg += TKg;

        query = "update TOTAL_STOCK set TOTAL_KG = ?"
                + "where PRODUCT = ? AND STORAGE = ?";
        dontDeleteDB.setADoubleDB(query, (TotalKg),
                ob.getProduct(), ob.getStorage());
        query = "update TOTAL_STOCK set TOTAL_MON = ?"
                + "where PRODUCT = ? AND STORAGE = ?";
        dontDeleteDB.setADoubleDB(query, (TotalKg / 37.324),
                ob.getProduct(), ob.getStorage());

        stockquery = "SELECT AVG_RATE FROM TOTAL_STOCK WHERE PRODUCT= '" + ob.getProduct()
                + "' AND STORAGE= '" + ob.getStorage() + "'";
        double avgdata = dontDeleteDB.getADoubleDB(stockquery, "AVG_RATE");

        double newrate;
        if (avgdata == 0) {
            newrate = ob.getRateMon();
        } else {
            double taka = (Tmon * avgdata) + (ob.getRateMon() * TotalMon);
            newrate = taka / ((Tmon) + TotalMon);
        }

        query = "update TOTAL_STOCK set AVG_RATE = ?"
                + "where PRODUCT = ? AND STORAGE = ?";
        dontDeleteDB.setADoubleDB(query, (newrate),
                ob.getProduct(), ob.getStorage());

        //UPCOMING QUANTITY
        stockquery = "SELECT UPCOMING_QUANTITY FROM UPCOMING_STOCK WHERE ORDER_NO= '" + ob.getOrderNo()
                + "' AND PRODUCT= '" + ob.getProduct() + "'";
        double UpQu = dontDeleteDB.getADoubleDB(stockquery, "UPCOMING_QUANTITY");

        if (UpQu > 0) {

            double newUpQu = UpQu - TotalOrderKg;
            //System.out.println(newUpQu);
            if (newUpQu < 20) {
                query = "DELETE FROM UPCOMING_STOCK WHERE PRODUCT= '" + ob.getProduct()
                        + "' AND ORDER_NO= '" + ob.getOrderNo() + "'";
                dontDeleteDB.delete(query);
            } else {
                query = "update UPCOMING_STOCK set UPCOMING_QUANTITY = ?"
                        + "where ORDER_NO = ? AND PRODUCT = ?";
                dontDeleteDB.setADoubleDB(query, (newUpQu),
                        ob.getOrderNo(), ob.getProduct());
            }
        }
        dontDeleteDB.closeDatabase();
        //}
    }

    private void deletePurchaseDelivery(PurchaseDeliveryDB ob) throws IOException, SQLException {
        DontDeleteDB dontDeleteDB = new DontDeleteDB();
        dontDeleteDB.connectDatabase();

        String query = "DELETE FROM PURCHASE_DELIVERY WHERE PURCHASE_DELIVERY_ID = '" + ob.getId() + "'";
        dontDeleteDB.delete(query);

        query = "DELETE FROM SUPPLIER_TRANSACTION WHERE ID_PURCHASE_DELIVERY = '" + ob.getId() + "'";
        dontDeleteDB.delete(query);

        //starting supplier list
        String sqlslID = "SELECT SUPPLIER_LIST_ID FROM SUPPLIER_LIST WHERE SUPPLIER= '" + ob.getSupplier()
                + "' AND INSTITUTE= '" + ob.getInstitute() + "'";
        int slID = dontDeleteDB.getAIntDB(sqlslID, "SUPPLIER_LIST_ID");

        String sqltotalDeilivery = "SELECT TOTAL_DELIVERY FROM SUPPLIER_LIST WHERE SUPPLIER_LIST_ID= " + slID + "";

        Double totalDelivery = dontDeleteDB.getADoubleDB(sqltotalDeilivery, "TOTAL_DELIVERY");
        String sqlbalanceDeilivery = "SELECT BALANCE_DELIVERY FROM SUPPLIER_LIST WHERE SUPPLIER_LIST_ID= " + slID + "";

        Double balanceDelivery = dontDeleteDB.getADoubleDB(sqlbalanceDeilivery, "BALANCE_DELIVERY");

        Double totalDeliveryNew = totalDelivery + ob.getPrice();
        Double balanceDeliveryNew = balanceDelivery + ob.getPrice();

        query = "update SUPPLIER_LIST set TOTAL_DELIVERY = ?"
                + "where SUPPLIER = ? AND INSTITUTE = ?";
        dontDeleteDB.setADoubleDB(query, (totalDeliveryNew),
                ob.getSupplier(), ob.getInstitute());
        query = "update SUPPLIER_LIST set BALANCE_DELIVERY = ?"
                + "where SUPPLIER = ? AND INSTITUTE = ?";
        dontDeleteDB.setADoubleDB(query, (balanceDeliveryNew),
                ob.getSupplier(), ob.getInstitute());

        //stocksssss
        String stockquery = "SELECT TOTAL_KG FROM TOTAL_STOCK WHERE PRODUCT= '" + ob.getProduct()
                + "' AND STORAGE= '" + ob.getStorage() + "'";
        double TKg = dontDeleteDB.getAIntDB(stockquery, "TOTAL_KG");
        double Tmon = TKg / 37.324;

        double TotalKg = 0, TotalMon = 0;

        if ("KG".equals(ob.getUnitType())) {
            TotalKg = ob.getQuantity() * ob.getUnit();
            TotalMon = TotalKg / 37.324;
        } else if ("MON".equals(ob.getUnitType())) {
            TotalMon = ob.getQuantity() * ob.getUnit();
            TotalKg = TotalMon * 37.324;
        } else if ("PIECE".equals(ob.getUnitType())) {
            TotalKg = ob.getQuantity() * ob.getUnit();
        }

        //plus plus in total stock
        TKg -= TotalKg;
        TotalKg = TKg;

        query = "update TOTAL_STOCK set TOTAL_KG = ?"
                + "where PRODUCT = ? AND STORAGE = ?";
        dontDeleteDB.setADoubleDB(query, (TotalKg),
                ob.getProduct(), ob.getStorage());
        query = "update TOTAL_STOCK set TOTAL_MON = ?"
                + "where PRODUCT = ? AND STORAGE = ?";
        dontDeleteDB.setADoubleDB(query, (TotalKg / 37.324),
                ob.getProduct(), ob.getStorage());

        stockquery = "SELECT AVG_RATE FROM TOTAL_STOCK WHERE PRODUCT= '" + ob.getProduct()
                + "' AND STORAGE= '" + ob.getStorage() + "'";
        double avgdata = dontDeleteDB.getADoubleDB(stockquery, "AVG_RATE");

        double taka = (Tmon * avgdata) - (ob.getRateMon() * TotalMon);

        double newrate = taka / ((Tmon) - TotalMon);

        query = "update TOTAL_STOCK set AVG_RATE = ?"
                + "where PRODUCT = ? AND STORAGE = ?";
        dontDeleteDB.setADoubleDB(query, (newrate),
                ob.getProduct(), ob.getStorage());

        //UPCOMING QUANTITY
        stockquery = "SELECT UPCOMING_QUANTITY FROM UPCOMING_STOCK WHERE ORDER_NO= '" + ob.getOrderNo()
                + "' AND PRODUCT= '" + ob.getProduct() + "'";
        double UpQu = dontDeleteDB.getADoubleDB(stockquery, "UPCOMING_QUANTITY");

        if (UpQu > 0) {

            double newUpQu = UpQu + TotalKg;

            query = "update UPCOMING_STOCK set UPCOMING_QUANTITY = ?"
                    + "where ORDER_NO = ? AND PRODUCT = ?";
            dontDeleteDB.setADoubleDB(query, (newUpQu),
                    ob.getOrderNo(), ob.getProduct());

        }

        dontDeleteDB.closeDatabase();

    }

    @FXML
    private void SetTextForSaleOrderTypeKg(ActionEvent event) {
        PdTypeDrop.setText("KG");
    }

    @FXML
    private void SetTextForSaleOrderTypeMon(ActionEvent event) {
        PdTypeDrop.setText("MON");
    }

    @FXML
    private void SetTextForSaleOrderTypePiece(ActionEvent event) {
        PdTypeDrop.setText("PIECE");
    }

    @FXML
    private void PdDeleteOrderButtClick(ActionEvent event) throws IOException, SQLException {
        DontDeleteDB dontDeleteDB = new DontDeleteDB();
        dontDeleteDB.connectDatabase();

        Parent p = FXMLLoader.load(getClass().getResource("DeleteSure.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(p);
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(
                ((Node) event.getSource()).getScene().getWindow());
        stage.setScene(scene);
        stage.showAndWait();

        if (newSet == true) {
            setDeleteAll(setdelivery);
            newSet = false;
        }

        dontDeleteDB.closeDatabase();
        stage = (Stage) PdEditButt.getScene().getWindow();
        stage.close();
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import static business.FrontController.institute;
import static business.FrontController.newCreateEntitiySetText;
import static business.FrontController.newItem;
import static business.FrontController.newSet;
import static business.FrontController.supplier;
import java.io.IOException;
import java.net.URL;
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
public class PurchaseOrderController implements Initializable {

    @FXML
    private VBox PoSupplierV;
    @FXML
    private TextField PoOrderNo;
    @FXML
    private VBox PoProductV;
    @FXML
    private VBox PoStorageV;
    @FXML
    private VBox PoQuantityV;
    @FXML
    private TextField PoUnit;
    @FXML
    private MenuButton PoTypeDrop;
    @FXML
    private RadioMenuItem PoTypeKg;
    @FXML
    private RadioMenuItem PoTypeMon;
    @FXML
    private RadioMenuItem PoTypePiece;
    @FXML
    private VBox PoRateKgV;
    @FXML
    private VBox PoRateMonV;
    @FXML
    private VBox PoPriceV;
    @FXML
    private TableView<PurchaseOrderDB> PoTable;

    private IntegerTextField PoQuantity;
    private IntegerTextField PoPrice;
    private IntegerTextField PoRateKg;
    private IntegerTextField PoRateMon;

    private AutoCompleteTextField PoSupplierName;
    private AutoCompleteTextField PoInstitute;
    private AutoCompleteTextField PoProduct;
    private AutoCompleteTextField PoStorage;
    @FXML
    private VBox PoInstituteV;
    @FXML
    private Button PoEnterProductInfoButt;
    @FXML
    private TextField PoLp;
    @FXML
    private TableColumn<PurchaseOrderDB, String> PoProductCol;
    @FXML
    private TableColumn<PurchaseOrderDB, String> PoStorageCol;
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

    ObservableList<PurchaseOrderDB> PoData = FXCollections.observableArrayList();
    ObservableList<PurchaseOrderDB> PoTableData = FXCollections.observableArrayList();
    @FXML
    private Button PoDeleteButt;
    @FXML
    private Button PoEditButt;

    private String order = null;
    private String setorder = null;

    PurchaseOrderDB editOb = null;

    private boolean flagset = false;
    private int supplierSerial = 0;
    private int purchaseOrderSerial = 0;

    int TotalAmount = 0;
    @FXML
    private TextField PoTotal;
    @FXML
    private Button PoDeleteOrderButt;
    private Button PoSaveOrder;
    @FXML
    private Button PoSaveOrderButt;

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

        PoSupplierName = new AutoCompleteTextField();
        PoSupplierName.setEntries(database.getSuggestionList("SELECT * FROM SUPPLIER_LIST;", "SUPPLIER"));
        PoSupplierName.setPromptText("SUPPLIER NAME");
        PoSupplierV.getChildren().add(PoSupplierName);

        PoInstitute = new AutoCompleteTextField();
        PoInstitute.setEntries(database.getSuggestionList("SELECT * FROM SUPPLIER_LIST;", "INSTITUTE"));
        PoInstitute.setPromptText("INSTITUTE");
        PoInstituteV.getChildren().add(PoInstitute);

        PoProduct = new AutoCompleteTextField();
        PoProduct.setEntries(database.getSuggestionList("SELECT * FROM TOTAL_STOCK;", "PRODUCT"));
        PoProduct.setPromptText("PRODUCT NAME");
        PoProductV.getChildren().add(PoProduct);

        PoStorage = new AutoCompleteTextField();
        PoStorage.setEntries(database.getSuggestionList("SELECT * FROM TOTAL_STOCK;", "STORAGE"));
        PoStorage.setPromptText("STORAGE");
        PoStorageV.getChildren().add(PoStorage);

        PoQuantity = new IntegerTextField();
        PoPrice = new IntegerTextField();
        PoRateKg = new IntegerTextField();
        PoRateMon = new IntegerTextField();

        PoQuantity.setPromptText("QUANTITY");
        PoPrice.setPromptText("PRICE");
        PoRateKg.setPromptText("/KG");
        PoRateMon.setPromptText("/MON");

        PoQuantityV.getChildren().add(PoQuantity);
        PoPriceV.getChildren().add(PoPrice);
        PoRateKgV.getChildren().add(PoRateKg);
        PoRateMonV.getChildren().add(PoRateMon);

        PoOrderNo.setEditable(false);

        dontDeleteDB.closeDatabase();
        database.close_database();
        PoSupplierName.setOnAction((ActionEvent evt) -> {
            PoInstitute.setText(getInstitute(PoSupplierName.getText()));
            PoInstitute.requestFocus();
            PoOrderNo.setText(generateOrder());
        });

        PoInstitute.setOnAction((ActionEvent evt) -> {
            PoInstitute.setText(getInstitute(PoSupplierName.getText()));
            PoInstitute.requestFocus();
            PoOrderNo.setText(generateOrder());
        });

        PoProductCol.setCellValueFactory(new PropertyValueFactory<>("product"));
        PoQuantityCol.setCellValueFactory(new PropertyValueFactory<>("quantity2"));
        PoRateKgCol.setCellValueFactory(new PropertyValueFactory<>("rateKg2"));
        PoRateMonCol.setCellValueFactory(new PropertyValueFactory<>("rateMon2"));
        PoPriceCol.setCellValueFactory(new PropertyValueFactory<>("price2"));
        PoStorageCol.setCellValueFactory(new PropertyValueFactory<>("storage"));
        PoUnitCol.setCellValueFactory(new PropertyValueFactory<>("unit2"));

        PoRateKg.setOnAction((ActionEvent evt) -> {
            double rateKg = IntegerTextField.doubleConvert(PoRateKg.getText());
            double rateMon = rateKg * 37.324;
            double totalKg;
            if ("KG".equals(PoTypeDrop.getText())) {
                totalKg = IntegerTextField.doubleConvert(PoQuantity.getText()) * IntegerTextField.doubleConvert(PoUnit.getText());
                PoRateMon.setText(IntegerTextField.numformat(rateMon));
                PoPrice.setText(IntegerTextField.numformat(totalKg * rateKg));
            } else if ("MON".equals(PoTypeDrop.getText())) {
                totalKg = IntegerTextField.doubleConvert(PoQuantity.getText()) * IntegerTextField.doubleConvert(PoUnit.getText()) * 37.324;
                PoRateMon.setText(IntegerTextField.numformat(rateMon));
                PoPrice.setText(IntegerTextField.numformat(totalKg * rateKg));
            } else if ("PIECE".equals(PoTypeDrop.getText())) {
                totalKg = IntegerTextField.doubleConvert(PoQuantity.getText()) * IntegerTextField.doubleConvert(PoUnit.getText());
                PoPrice.setText(IntegerTextField.numformat(totalKg * rateKg));
                PoRateMon.setText(null);
            }

        });

        PoRateMon.setOnAction((ActionEvent evt) -> {
            double rateMon = IntegerTextField.doubleConvert(PoRateMon.getText());
            double rateKg = rateMon / 37.324;
            double totalKg = 0;
            if ("KG".equals(PoTypeDrop.getText())) {
                totalKg = IntegerTextField.doubleConvert(PoQuantity.getText()) * IntegerTextField.doubleConvert(PoUnit.getText());
            } else if ("MON".equals(PoTypeDrop.getText())) {
                totalKg = IntegerTextField.doubleConvert(PoQuantity.getText()) * IntegerTextField.doubleConvert(PoUnit.getText()) * 37.324;
            }
            PoRateKg.setText(IntegerTextField.doubleformat(rateKg));
            PoPrice.setText(IntegerTextField.numformat(totalKg * rateKg));
        });

        PoEnterProductInfoButt.disableProperty().bind(
                Bindings.isEmpty(PoInstitute.textProperty())
                .or(Bindings.isEmpty(PoOrderNo.textProperty()))
                .or(Bindings.isEmpty(PoQuantity.textProperty()))
                .or(Bindings.isEmpty(PoProduct.textProperty()))
                .or(Bindings.isEmpty(PoSupplierName.textProperty()))
                .or(Bindings.isEmpty(PoUnit.textProperty()))
                .or(Bindings.isEmpty(PoPrice.textProperty()))
                .or(Bindings.isEmpty(PoTypeDrop.textProperty()))
        );

        if (FrontController.PoOrderNo != null) {
            setPurchaseOrder(FrontController.PoOrderNo);
        }

    }

    private void setPurchaseOrder(String orderno) {
        DontDeleteDB dontDeleteDB = new DontDeleteDB();
        dontDeleteDB.connectDatabase();

        String sql = "SELECT * FROM PURCHASE_ORDER WHERE ORDER_NO='" + orderno + "'";
        PoTableData.addAll(dontDeleteDB.getPurchaseOrder(sql));
        PoTable.setItems(PoTableData);

        for (PurchaseOrderDB Po : PoTableData) {
            PoSupplierName.setText(Po.getSupplier());
            PoInstitute.setText(Po.getInstitute());
            PoOrderNo.setText(orderno);
            setorder = orderno;
            PoLp.setText(Po.getLp());
            dateTimeNow = Po.getDate();
            purchaseOrderSerial = Po.getSerial();
            break;
        }
        TotalAmount = 0;
        for (PurchaseOrderDB Pd : PoTableData) {
            TotalAmount += Pd.getPrice();
        }
        PoTotal.setText(IntegerTextField.numformat(TotalAmount));

        PoDeleteOrderButt.setDisable(false);

        PoSaveOrderButt.setText("SAVE");
        dontDeleteDB.closeDatabase();
    }

    private String generateOrder() {
        DontDeleteDB dontDeleteDB = new DontDeleteDB();
        dontDeleteDB.connectDatabase();

        String sqlslID = "SELECT SUPPLIER_SERIAL as orderno FROM SUPPLIER_LIST"
                + " WHERE SUPPLIER= '"
                + PoSupplierName.getText()
                + "' AND INSTITUTE= '" + PoInstitute.getText() + "'";
        int slPoID = dontDeleteDB.getAIntDB(sqlslID, "orderno");
        sqlslID = "SELECT SUPPLIER_LIST_ID as ID FROM SUPPLIER_LIST"
                + " WHERE SUPPLIER= '"
                + PoSupplierName.getText()
                + "' AND INSTITUTE= '" + PoInstitute.getText() + "'";
        int slID = dontDeleteDB.getAIntDB(sqlslID, "ID");
        String query = "SELECT MAX(PURCHASE_ORDER_SERIAL) as orderno FROM PURCHASE_ORDER";
        int poID = dontDeleteDB.getAIntDB(query, "orderno");

        poID++;
        purchaseOrderSerial = poID;
        String ID, Ssl, Tsl;

        if (slID < 100) {
            ID = String.format("%02d", slID);
        } else {
            ID = String.valueOf(slID);
        }

        if (slPoID < 1000) {
            Ssl = String.format("%03d", slPoID);
        } else {
            Ssl = String.valueOf(slPoID);
        }

        if (poID < 1000) {
            Tsl = String.format("%03d", poID);
        } else {
            Tsl = String.valueOf(poID);
        }

        String orderno = ID + "-" + Ssl + "-" + Tsl;
        order = orderno;
        slPoID++;
        supplierSerial = slPoID;

        dontDeleteDB.closeDatabase();
        return orderno;
    }

    private String getInstitute(String party) {

        DontDeleteDB dontDeleteDB = new DontDeleteDB();
        dontDeleteDB.connectDatabase();

        String mySql = "SELECT INSTITUTE FROM SUPPLIER_LIST WHERE SUPPLIER = '"
                + party + "'";
        String Ins = dontDeleteDB.getAStringDB(mySql, "INSTITUTE");

        dontDeleteDB.closeDatabase();

        return Ins;

    }

    private void setTotal() {

        TotalAmount = 0;
        for (PurchaseOrderDB Pd : PoTableData) {
            TotalAmount += Pd.getPrice();
        }
        PoTotal.setText(IntegerTextField.numformat(TotalAmount));
    }

    @FXML
    private void PoEnterProductInfoButtClick(ActionEvent event) throws IOException {

        PurchaseOrderDB ob = new PurchaseOrderDB();

        DontDeleteDB dontDeleteDB = new DontDeleteDB();

        dontDeleteDB.connectDatabase();

        String sqlslID = "SELECT SUPPLIER_LIST_ID FROM SUPPLIER_LIST WHERE SUPPLIER= '"
                + PoSupplierName.getText()
                + "' AND INSTITUTE= '" + PoInstitute.getText() + "'";
        int slID = dontDeleteDB.getAIntDB(sqlslID, "SUPPLIER_LIST_ID");
        if (slID == 0) {
            newCreateEntitiySetText = "NEW SUPPLIER";
            supplier = PoSupplierName.getText();
            institute = PoInstitute.getText();
            newItem = supplier;
            Parent p = FXMLLoader.load(getClass().getResource("NewCreate.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(p);

            stage.setScene(scene);
            stage.setX(480);
            stage.setY(250);
            stage.showAndWait();
            if (newSet == false) {
                return;
            } else {
                newSet = false;
            }

        }

        String stockSql = "SELECT TOTAL_STOCK_ID FROM TOTAL_STOCK WHERE PRODUCT= '"
                + PoProduct.getText()
                + "' AND STORAGE= '" + PoStorage.getText() + "';";
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
        if ("SAVE".equals(PoEnterProductInfoButt.getText())) {
            PoTableData.remove(editOb);

            editOb = null;
            PoEnterProductInfoButt.setText("ENTRY");
            PoEditButt.setText("EDIT");
        }

        ob.setDate(dateTimeNow);
        ob.setPrice2(PoPrice.getText());
        ob.setPrice(IntegerTextField.doubleConvert(PoPrice.getText()));
        ob.setProduct(PoProduct.getText());
        ob.setQuantity(IntegerTextField.doubleConvert(PoQuantity.getText()));
        ob.setQuantity2(PoQuantity.getText());
        ob.setRateKg(IntegerTextField.doubleConvert(PoRateKg.getText()));
        ob.setRateKg2(PoRateKg.getText());
        ob.setRateMon(IntegerTextField.doubleConvert(PoRateMon.getText()));
        ob.setRateMon2(PoRateMon.getText());
        ob.setStorage(PoStorage.getText());
        ob.setUnit(IntegerTextField.doubleConvert(PoUnit.getText()));
        ob.setUnit2(PoUnit.getText());
        ob.setUnitType(PoTypeDrop.getText());
        ob.setUser(FrontController.user);
        ob.setSerial(purchaseOrderSerial);

        PoTableData.add(ob);

        PoTable.setItems(PoTableData);

        setTotal();

        PoPrice.clear();
        PoProduct.clear();
        PoQuantity.clear();
        PoRateKg.clear();
        PoRateMon.clear();
        PoStorage.clear();

        PoUnit.clear();

        dontDeleteDB.closeDatabase();

    }

    private void insertPurchaseOrder(PurchaseOrderDB ob) throws IOException {

        DontDeleteDB dontDeleteDB = new DontDeleteDB();

        dontDeleteDB.connectDatabase();

        dontDeleteDB.insertPurchaseOrder(ob);
        //insertion in purchase table ends here 

        String sqlslID = "SELECT SUPPLIER_LIST_ID FROM SUPPLIER_LIST WHERE SUPPLIER= '" + ob.getSupplier()
                + "' AND INSTITUTE= '" + ob.getInstitute() + "'";
        int slID = dontDeleteDB.getAIntDB(sqlslID, "SUPPLIER_LIST_ID");

        String sqlbalanceOrder = "SELECT BALANCE_ORDER FROM SUPPLIER_LIST WHERE SUPPLIER_LIST_ID= '" + slID + "'";

        double balanceOrder = dontDeleteDB.getADoubleDB(sqlbalanceOrder, "BALANCE_ORDER");

        String sqltotalOrder = "SELECT TOTAL_ORDER FROM SUPPLIER_LIST WHERE SUPPLIER_LIST_ID= '" + slID + "'";

        double totalOrder = dontDeleteDB.getADoubleDB(sqltotalOrder, "TOTAL_ORDER");

        balanceOrder -= ob.getPrice();
        totalOrder -= ob.getPrice();

        String query = "update SUPPLIER_LIST set TOTAL_ORDER = ?"
                + "where SUPPLIER = ? AND INSTITUTE = ?";
        dontDeleteDB.setADoubleDB(query, (totalOrder),
                ob.getSupplier(), ob.getInstitute());
        query = "update SUPPLIER_LIST set BALANCE_ORDER = ?"
                + "where SUPPLIER = ? AND INSTITUTE = ?";
        dontDeleteDB.setADoubleDB(query, (balanceOrder),
                ob.getSupplier(), ob.getInstitute());

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

        UpcomingStockDB upOb = new UpcomingStockDB();

        upOb.setSupplier(ob.getSupplier());
        upOb.setDate(ob.getDate());
        upOb.setOrderNo(ob.getOrderNo());
        upOb.setOrderQuantity(TotalKg);

        upOb.setProduct(ob.getProduct());
        upOb.setStorage(ob.getStorage());
        upOb.setOrderUnit(ob.getRateMon());
        upOb.setUpcomingQuantity(TotalKg);

        dontDeleteDB.insertUpcomingStock(upOb);
        dontDeleteDB.closeDatabase();

    }

    @FXML
    private void PoDeleteButtClick(ActionEvent event) throws IOException {
        PurchaseOrderDB PoOb = PoTable.getSelectionModel().getSelectedItem();
        if (PoOb == null) {
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
            PoTableData.remove(PoOb);

            newSet = false;
        }
        DontDeleteDB dontDeleteDB = new DontDeleteDB();

        dontDeleteDB.connectDatabase();

        PoTable.setItems(PoTableData);
        setTotal();

        dontDeleteDB.closeDatabase();

    }

    private void deletePurchaseOrder(PurchaseOrderDB ob) {
        DontDeleteDB dontDeleteDB = new DontDeleteDB();
        dontDeleteDB.connectDatabase();

        String query = "DELETE FROM PURCHASE_ORDER WHERE PURCHASE_ORDER_ID = '" + ob.getId() + "'";
        dontDeleteDB.delete(query);
        //insertion in purchase table ends here 

        String sqlslID = "SELECT SUPPLIER_LIST_ID FROM SUPPLIER_LIST WHERE SUPPLIER= '"
                + ob.getSupplier()
                + "' AND INSTITUTE= '" + ob.getInstitute() + "'";
        int slID = dontDeleteDB.getAIntDB(sqlslID, "SUPPLIER_LIST_ID");

        String sqlbalanceOrder = "SELECT BALANCE_ORDER FROM SUPPLIER_LIST WHERE SUPPLIER_LIST_ID= '" + slID + "'";

        double balanceOrder = dontDeleteDB.getADoubleDB(sqlbalanceOrder, "BALANCE_ORDER");

        String sqltotalOrder = "SELECT TOTAL_ORDER FROM SUPPLIER_LIST WHERE SUPPLIER_LIST_ID= '" + slID + "'";

        double totalOrder = dontDeleteDB.getADoubleDB(sqltotalOrder, "TOTAL_ORDER");

        balanceOrder += ob.getPrice();
        totalOrder += ob.getPrice();

        query = "update SUPPLIER_LIST set TOTAL_ORDER = ?"
                + "where SUPPLIER = ? AND INSTITUTE = ?";
        dontDeleteDB.setADoubleDB(query, (totalOrder),
                ob.getSupplier(), ob.getInstitute());
        query = "update SUPPLIER_LIST set BALANCE_ORDER = ?"
                + "where SUPPLIER = ? AND INSTITUTE = ?";
        dontDeleteDB.setADoubleDB(query, (balanceOrder),
                ob.getSupplier(), ob.getInstitute());

        //UPCOMING QUANTITY
        query = "DELETE FROM UPCOMING_STOCK WHERE PRODUCT= '" + ob.getProduct()
                + "' AND ORDER_NO= '" + ob.getOrderNo() + "'";
        dontDeleteDB.delete(query);

    }

    @FXML
    private void PoEditButtClick(ActionEvent event) {
        if ("CANCEL".equals(PoEditButt.getText())) {
            PoProduct.clear();
            PoStorage.clear();
            PoQuantity.clear();
            PoUnit.clear();
            PoRateKg.clear();
            PoRateKg.clear();
            PoRateMon.clear();
            PoEnterProductInfoButt.setText("ENTRY");
            PoEditButt.setText("EDIT");

        } else if ("EDIT".equals(PoEditButt.getText())) {
            PurchaseOrderDB PoOb = PoTable.getSelectionModel().getSelectedItem();
            if (PoOb == null) {
                return;
            } else {
                editOb = PoOb;
            }
            PoEditButt.setText("CANCEL");
            PoEnterProductInfoButt.setText("SAVE");
            PoSupplierName.setText(PoOb.getSupplier());
            PoInstitute.setText(PoOb.getInstitute());
            PoOrderNo.setText(PoOb.getOrderNo());
            PoLp.setText(PoOb.getLp());
            PoProduct.setText(PoOb.getProduct());
            PoStorage.setText(PoOb.getStorage());
            PoQuantity.setText(PoOb.getQuantity2());
            PoUnit.setText(String.valueOf(PoOb.getUnit()));
            PoTypeDrop.setText(PoOb.getUnitType());
            PoRateKg.setText(PoOb.getRateKg2());
            PoRateMon.setText(PoOb.getRateMon2());
            PoPrice.setText(PoOb.getPrice2());

        }

    }

    private void setDeleteAll(String orderno) {
        DontDeleteDB dontDeleteDB = new DontDeleteDB();
        dontDeleteDB.connectDatabase();
        PoData.addAll(dontDeleteDB.getPurchaseOrder("SELECT * from PURCHASE_ORDER WHERE ORDER_NO='"
                + setorder + "'"));

        for (PurchaseOrderDB po : PoData) {
            deletePurchaseOrder(po);
        }
        dontDeleteDB.closeDatabase();
    }

    @FXML
    private void PoSaveOrderButtClick(ActionEvent event) throws IOException {
        DontDeleteDB dontDeleteDB = new DontDeleteDB();
        dontDeleteDB.connectDatabase();
        if (supplierSerial != 0 && !"SAVE".equals(PoSaveOrderButt.getText())) {
            String query = "update SUPPLIER_LIST set SUPPLIER_SERIAL = ?"
                    + "where SUPPLIER = ? AND INSTITUTE = ?";
            dontDeleteDB.setADoubleDB(query, (supplierSerial),
                    PoSupplierName.getText(), PoInstitute.getText());

        }

        if ("SAVE".equals(PoSaveOrderButt.getText())) {
            setDeleteAll(setorder);
        }
        for (PurchaseOrderDB ob : PoTableData) {
            ob.setSupplier(PoSupplierName.getText());
            ob.setInstitute(PoInstitute.getText());
            ob.setLp(PoLp.getText());
            ob.setOrderNo(PoOrderNo.getText());
            insertPurchaseOrder(ob);
        }

        dontDeleteDB.closeDatabase();
        Stage stage = (Stage) PoEditButt.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void SetTextForPurchaseOrderTypeKg(ActionEvent event) {
        PoTypeDrop.setText("KG");
    }

    @FXML
    private void SetTextForPurchaseOrderTypeMon(ActionEvent event) {
        PoTypeDrop.setText("MON");
    }

    @FXML
    private void SetTextForPurchaseOrderTypePiece(ActionEvent event) {
        PoTypeDrop.setText("PIECE");
    }

    @FXML
    private void PoDeleteOrderButtClick(ActionEvent event) throws IOException {
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
            setDeleteAll(order);
            newSet = false;
        }

        dontDeleteDB.closeDatabase();
        stage = (Stage) PoEditButt.getScene().getWindow();
        stage.close();
    }

}

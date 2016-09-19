/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import static business.FrontController.customer;
import static business.FrontController.institute;
import static business.FrontController.newCreateEntitiySetText;
import static business.FrontController.newItem;
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
public class SaleOrderController implements Initializable {

    @FXML
    private VBox SoCustomerV;
    @FXML
    private VBox SoInstituteV;
    @FXML
    private TextField SoOrderNo;
    @FXML
    private TextField SoLp;
    @FXML
    private VBox SoProductV;
    @FXML
    private VBox SoMarkaV;
    @FXML
    private VBox SoQuantityV;
    @FXML
    private TextField SoUnit;
    @FXML
    private MenuButton SoTypeDrop;
    @FXML
    private RadioMenuItem SoTypeKg;
    @FXML
    private RadioMenuItem SoTypeMon;
    @FXML
    private RadioMenuItem SoTypePiece;
    @FXML
    private VBox SoRateKgV;
    @FXML
    private VBox SoRateMonV;
    @FXML
    private VBox SoPriceV;
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

    private AutoCompleteTextField SoProduct;
    private AutoCompleteTextField SoMarka;
    private AutoCompleteTextField SoInstitute;
    private AutoCompleteTextField SoCustomerName;
    private IntegerTextField SoRateKg;
    private IntegerTextField SoRateMon;
    private IntegerTextField SoQuantity;
    private IntegerTextField SoPrice;
    @FXML
    private Button SoEnterProductInfoButt;

    ObservableList<SaleOrderDB> SoData = FXCollections.observableArrayList();
    ObservableList<SaleOrderDB> SoTableData = FXCollections.observableArrayList();

    SaleOrderDB editOb = null;

    private boolean flagset = false;

    //private String order = null;
    @FXML
    private VBox SoLpV;
    @FXML
    private Button SoDeleteButt;
    @FXML
    private Button SoEditButt;

    private int totalAmount = 0;
    @FXML
    private TextField TotalAmount;

    private String setorder = null;
    @FXML
    private Button SoDeleteOrderButt;
    @FXML
    private Button SoSaveOrderButt;

    private int saleOrderSerial = 0;
    private int customerSerial = 0;

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

        SoProduct = new AutoCompleteTextField();
        SoProduct.setEntries(database.getSuggestionList("SELECT * FROM TOTAL_STOCK;", "PRODUCT"));
        SoProduct.setPromptText("PRODUCT");
        SoProductV.getChildren().add(SoProduct);

        SoMarka = new AutoCompleteTextField();
        SoMarka.setEntries(database.getSuggestionList("SELECT * FROM SALE_ORDER;", "MARKA"));
        SoMarka.setPromptText("MARKA");
        SoMarkaV.getChildren().add(SoMarka);

        SoCustomerName = new AutoCompleteTextField();
        SoCustomerName.setEntries(database.getSuggestionList("SELECT * FROM CUSTOMER_LIST;", "CUSTOMER"));
        SoCustomerName.setPromptText("CUSTOMER NAME");
        SoCustomerV.getChildren().add(SoCustomerName);

        SoInstitute = new AutoCompleteTextField();
        SoInstitute.setEntries(database.getSuggestionList("SELECT * FROM CUSTOMER_LIST;", "INSTITUTE"));
        SoInstitute.setPromptText("INSTITUTE");
        SoInstituteV.getChildren().add(SoInstitute);

        SoQuantity = new IntegerTextField();
        SoPrice = new IntegerTextField();
        SoRateKg = new IntegerTextField();
        SoRateMon = new IntegerTextField();
        SoQuantity.setPromptText("QUANTITY");
        SoQuantity.setMaxWidth(150);
        SoPrice.setPromptText("PRICE");
        SoPrice.setMaxWidth(150);
        SoRateKg.setPromptText("/KG");
        SoRateKg.setMaxWidth(150);
        SoRateMon.setPromptText("/MON");
        SoRateMon.setMaxWidth(100);

        SoQuantityV.getChildren().add(SoQuantity);
        SoPriceV.getChildren().add(SoPrice);
        SoRateKgV.getChildren().add(SoRateKg);
        SoRateMonV.getChildren().add(SoRateMon);

        SoProductCol.setCellValueFactory(new PropertyValueFactory<>("product"));
        SoMarkaCol.setCellValueFactory(new PropertyValueFactory<>("marka"));
        SoQuantityCol.setCellValueFactory(new PropertyValueFactory<>("quantity2"));
        SoUnitCol.setCellValueFactory(new PropertyValueFactory<>("unit2"));
        SoRateKgSubCol.setCellValueFactory(new PropertyValueFactory<>("rateKg2"));
        SoRateMonSubCol.setCellValueFactory(new PropertyValueFactory<>("rateMon2"));
        SoPriceCol.setCellValueFactory(new PropertyValueFactory<>("price2"));

        SoOrderNo.setEditable(false);

        dontDeleteDB.closeDatabase();
        database.close_database();

        SoCustomerName.setOnAction((ActionEvent evt) -> {
            SoInstitute.setText(getInstitute(SoCustomerName.getText()));
            SoInstitute.requestFocus();
            SoOrderNo.setText(generateOrder());
        });

        SoInstitute.setOnAction((ActionEvent evt) -> {
            SoInstitute.setText(getInstitute(SoCustomerName.getText()));
            SoInstitute.requestFocus();
            SoOrderNo.setText(generateOrder());
        });
        
        

        SoRateKg.setOnAction((ActionEvent evt) -> {
            double rateKg = IntegerTextField.doubleConvert(SoRateKg.getText());
            double rateMon = rateKg * 37.324;
            double totalKg = 0;
            if ("KG".equals(SoTypeDrop.getText())) {
                totalKg = IntegerTextField.doubleConvert(SoQuantity.getText()) * IntegerTextField.doubleConvert(SoUnit.getText());
                SoRateMon.setText(IntegerTextField.numformat(rateMon));
                SoPrice.setText(IntegerTextField.numformat(totalKg * rateKg));
            } else if ("MON".equals(SoTypeDrop.getText())) {
                totalKg = IntegerTextField.doubleConvert(SoQuantity.getText()) * IntegerTextField.doubleConvert(SoUnit.getText()) * 37.324;
                SoRateMon.setText(IntegerTextField.numformat(rateMon));
                SoPrice.setText(IntegerTextField.numformat(totalKg * rateKg));
            } else if ("PIECE".equals(SoTypeDrop.getText())) {
                totalKg = IntegerTextField.doubleConvert(SoQuantity.getText()) * IntegerTextField.doubleConvert(SoUnit.getText());
                SoPrice.setText(IntegerTextField.numformat(totalKg * rateKg));
                SoRateMon.setText(null);
            }

        });

        SoRateMon.setOnAction((ActionEvent evt) -> {
            double rateMon = IntegerTextField.doubleConvert(SoRateMon.getText());
            double rateKg = rateMon / 37.324;
            double totalKg = 0;
            if ("KG".equals(SoTypeDrop.getText())) {
                totalKg = IntegerTextField.doubleConvert(SoQuantity.getText()) * IntegerTextField.doubleConvert(SoUnit.getText());
            } else if ("MON".equals(SoTypeDrop.getText())) {
                totalKg = IntegerTextField.doubleConvert(SoQuantity.getText()) * IntegerTextField.doubleConvert(SoUnit.getText()) * 37.324;
            }
            SoRateKg.setText(IntegerTextField.doubleformat(rateKg));
            SoPrice.setText(IntegerTextField.numformat(totalKg * rateKg));
        });

        SoEnterProductInfoButt.disableProperty().bind(
                Bindings.isEmpty(SoCustomerName.textProperty())
                .or(Bindings.isEmpty(SoInstitute.textProperty()))
                .or(Bindings.isEmpty(SoOrderNo.textProperty()))
                .or(Bindings.isEmpty(SoProduct.textProperty()))
                .or(Bindings.isEmpty(SoQuantity.textProperty()))
                .or(Bindings.isEmpty(SoUnit.textProperty()))
                .or(Bindings.isEmpty(SoPrice.textProperty()))
        );

        if (FrontController.SoOrderNo != null) {
            setSaleOrder(FrontController.SoOrderNo);
        } else if (CustomerListController.SoOrderNo != null) {
            setSaleOrder(CustomerListController.SoOrderNo);
        }

    }

    private void setSaleOrder(String orderno) {
        DontDeleteDB dontDeleteDB = new DontDeleteDB();
        dontDeleteDB.connectDatabase();

        String sql = "SELECT * FROM SALE_ORDER WHERE INVOICE_NO='" + orderno + "'";
        SoTableData.addAll(dontDeleteDB.getSaleOrder(sql));
        SoTable.setItems(SoTableData);

        for (SaleOrderDB Po : SoTableData) {
            SoCustomerName.setText(Po.getCustomer());
            SoInstitute.setText(Po.getInstitute());
            SoOrderNo.setText(orderno);
            setorder = orderno;
            saleOrderSerial= Integer.valueOf(orderno);
            System.out.println(saleOrderSerial);
            SoLp.setText(Po.getLp());
            dateTimeNow = Po.getDate();
            break;
        }
        setTotal();

        SoDeleteOrderButt.setDisable(false);

        SoSaveOrderButt.setText("SAVE");
        dontDeleteDB.closeDatabase();
    }

    private void setTotal() {

        totalAmount = 0;
        for (SaleOrderDB Pd : SoTableData) {
            totalAmount += Pd.getPrice();
        }
        TotalAmount.setText(IntegerTextField.numformat(totalAmount));
    }

    private String getInstitute(String party) {

        DontDeleteDB dontDeleteDB = new DontDeleteDB();
        dontDeleteDB.connectDatabase();

        String mySql = "SELECT INSTITUTE FROM CUSTOMER_LIST WHERE CUSTOMER= '"
                + party + "'";
        String Ins = dontDeleteDB.getAStringDB(mySql, "INSTITUTE");

        dontDeleteDB.closeDatabase();
        return Ins;

    }

    private String generateOrder() {
        DontDeleteDB dontDeleteDB = new DontDeleteDB();
        dontDeleteDB.connectDatabase();

        String query = "SELECT MAX(SERIAL) as ID  FROM SALE_ORDER;";
        int serial = dontDeleteDB.getAIntDB(query, "ID");
        serial++;
        saleOrderSerial = serial;
        String sl;
        if (serial < 1000) {
            sl = String.format("%03d", serial);
        } else {
            sl = String.valueOf(serial);
        }

        return sl;
    }

    @FXML
    private void SoEnterProductInfoButtClick(ActionEvent event) throws IOException, SQLException {
        

        SaleOrderDB ob = new SaleOrderDB();

        DontDeleteDB dontDeleteDB = new DontDeleteDB();

        dontDeleteDB.connectDatabase();

        String sqlclID = "SELECT CUSTOMER_LIST_ID FROM CUSTOMER_LIST WHERE CUSTOMER= '"
                + SoCustomerName.getText()
                + "' AND INSTITUTE= '" + SoInstitute.getText() + "'";
        int clID = dontDeleteDB.getAIntDB(sqlclID, "CUSTOMER_LIST_ID");
        if (clID == 0) {
            newCreateEntitiySetText = "NEW CUSTOMER";
            customer = SoCustomerName.getText();
            institute = SoInstitute.getText();
            newItem = customer;
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
        
        if ("SAVE".equals(SoEnterProductInfoButt.getText())) {
            SoTableData.remove(editOb);
            editOb = null;
            SoEnterProductInfoButt.setText("ENTRY");
            SoEditButt.setText("EDIT");
        }

        ob.setDate(dateTimeNow);
        ob.setPrice2(SoPrice.getText());
        ob.setPrice(IntegerTextField.doubleConvert(SoPrice.getText()));
        ob.setProduct(SoProduct.getText());
        ob.setQuantity(IntegerTextField.doubleConvert(SoQuantity.getText()));
        ob.setQuantity2(SoQuantity.getText());
        ob.setRateKg(IntegerTextField.doubleConvert(SoRateKg.getText()));
        ob.setRateKg2(SoRateKg.getText());
        ob.setRateMon(IntegerTextField.doubleConvert(SoRateMon.getText()));
        ob.setRateMon2(SoRateMon.getText());
        ob.setMarka(SoMarka.getText());
        ob.setUnitType(SoTypeDrop.getText());
        ob.setUnit(IntegerTextField.doubleConvert(SoUnit.getText()));
        //ob.setUnit2(SoUnit.getText());

        ob.setUser(FrontController.user);
        ob.setSerial(saleOrderSerial);

        SoTableData.add(ob);

        SoTable.setItems(SoTableData);
        setTotal();

        SoPrice.clear();
        SoProduct.clear();
        SoQuantity.clear();
        SoRateKg.clear();
        SoRateMon.clear();
        SoMarka.clear();

        SoUnit.clear();

    }

    private void insertSaleOrder(SaleOrderDB ob) throws IOException {

        DontDeleteDB dontDeleteDB = new DontDeleteDB();

        dontDeleteDB.connectDatabase();

        dontDeleteDB.insertSaleOrder(ob);
        //insertion in purchase table ends here 
        String sqlslID = "SELECT CUSTOMER_LIST_ID FROM CUSTOMER_LIST WHERE CUSTOMER= '" + ob.getCustomer()
                + "' AND INSTITUTE= '" + ob.getInstitute() + "'";
        int clID = dontDeleteDB.getAIntDB(sqlslID, "CUSTOMER_LIST_ID");

        String sqlbalanceOrder = "SELECT BALANCE_ORDER FROM CUSTOMER_LIST WHERE CUSTOMER_LIST_ID= '" + clID + "'";

        Double balanceOrder = dontDeleteDB.getADoubleDB(sqlbalanceOrder, "BALANCE_ORDER");

        String sqltotalOrder = "SELECT TOTAL_ORDER FROM CUSTOMER_LIST WHERE CUSTOMER_LIST_ID= '" + clID + "'";

        Double totalOrder = dontDeleteDB.getADoubleDB(sqltotalOrder, "TOTAL_ORDER");

        balanceOrder += ob.getPrice();
        totalOrder += ob.getPrice();

        String query = "update CUSTOMER_LIST set TOTAL_ORDER = ?"
                + "where CUSTOMER = ? AND INSTITUTE = ?";
        dontDeleteDB.setADoubleDB(query, (totalOrder),
                ob.getCustomer(), ob.getInstitute());
        query = "update CUSTOMER_LIST set BALANCE_ORDER = ?"
                + "where CUSTOMER = ? AND INSTITUTE = ?";
        dontDeleteDB.setADoubleDB(query, (balanceOrder),
                ob.getCustomer(), ob.getInstitute());

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

        PendingDeliveriesDB pdOb = new PendingDeliveriesDB();

        pdOb.setCustomer(ob.getCustomer());
        pdOb.setDate(ob.getDate());
        pdOb.setInvoiceNo(ob.getInvoiceNo());
        pdOb.setOrderQuantity(TotalKg);

        pdOb.setProduct(ob.getProduct());
       
        pdOb.setPendingQuantity(TotalKg);

        dontDeleteDB.insertPendingDeliveries(pdOb);

        dontDeleteDB.closeDatabase();

    }

    @FXML
    private void SoDeleteButtClick(ActionEvent event) throws IOException {
        SaleOrderDB SoOb = SoTable.getSelectionModel().getSelectedItem();
        if (SoOb == null) {
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
            SoTableData.remove(SoOb);
            newSet = false;
        }
        DontDeleteDB dontDeleteDB = new DontDeleteDB();

        dontDeleteDB.connectDatabase();

        SoTable.setItems(SoTableData);
        setTotal();

        dontDeleteDB.closeDatabase();

    }

    @FXML
    private void SoEditButtClick(ActionEvent event) {
        if ("CANCEL".equals(SoEditButt.getText())) {
            SoProduct.clear();
            SoMarka.clear();
            SoQuantity.clear();
            SoUnit.clear();
            SoRateKg.clear();
            SoRateKg.clear();
            SoRateMon.clear();
            SoEnterProductInfoButt.setText("ENTRY");
            SoEditButt.setText("EDIT");

        } else if ("EDIT".equals(SoEditButt.getText())) {
            SaleOrderDB SoOb = SoTable.getSelectionModel().getSelectedItem();
            if (SoOb == null) {
                return;
            } else {
                editOb = SoOb;
            }
            SoEditButt.setText("CANCEL");
            SoEnterProductInfoButt.setText("SAVE");
            SoProduct.setText(SoOb.getProduct());
            SoMarka.setText(SoOb.getMarka());
            SoQuantity.setText(SoOb.getQuantity2());
            SoUnit.setText(String.valueOf(SoOb.getUnit()));
            SoTypeDrop.setText(SoOb.getUnitType());
            SoRateKg.setText(SoOb.getRateKg2());
            SoRateMon.setText(SoOb.getRateMon2());
            SoPrice.setText(SoOb.getPrice2());

        }

    }

    private void setDeleteAll(String orderno) {
        DontDeleteDB dontDeleteDB = new DontDeleteDB();
        dontDeleteDB.connectDatabase();
        SoData.addAll(dontDeleteDB.getSaleOrder("SELECT * from SALE_ORDER WHERE INVOICE_NO='"
                + orderno + "'"));

        for (SaleOrderDB so : SoData) {
            deleteSaleOrder(so);
        }
        dontDeleteDB.closeDatabase();
    }

    @FXML
    private void SaveOrderButtClick(ActionEvent event) throws IOException {
        DontDeleteDB dontDeleteDB = new DontDeleteDB();
        dontDeleteDB.connectDatabase();

        if ("SAVE".equals(SoSaveOrderButt.getText())) {
            setDeleteAll(setorder);
        }

        for (SaleOrderDB ob : SoTableData) {

            ob.setCustomer(SoCustomerName.getText());

            ob.setInstitute(SoInstitute.getText());
            ob.setLp(SoLp.getText());
            ob.setInvoiceNo(SoOrderNo.getText());
            ob.setSerial(saleOrderSerial);
            insertSaleOrder(ob);
            //inserting purchasedelivery table

        }
        dontDeleteDB.closeDatabase();

        Stage stage = (Stage) SoEditButt.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void SetTextForSaleOrderTypeKg(ActionEvent event) {
        SoTypeDrop.setText("KG");
    }

    @FXML
    private void SetTextForSaleOrderTypeMon(ActionEvent event) {
        SoTypeDrop.setText("MON");
    }

    @FXML
    private void SetTextForSaleOrderTypePiece(ActionEvent event) {
        SoTypeDrop.setText("PIECE");
    }

    @FXML
    private void SoDeleteOrderButtClick(ActionEvent event) throws IOException {
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
            setDeleteAll(setorder);
            newSet = false;
        }

        dontDeleteDB.closeDatabase();
        stage = (Stage) SoEditButt.getScene().getWindow();
        stage.close();
    }

    private void deleteSaleOrder(SaleOrderDB ob) {
        DontDeleteDB dontDeleteDB = new DontDeleteDB();
        dontDeleteDB.connectDatabase();

        String query = "DELETE FROM SALE_ORDER WHERE SALE_ORDER_ID = '" + ob.getId() + "'";
        dontDeleteDB.delete(query);
        //insertion in purchase table ends here 

        String sqlslID = "SELECT CUSTOMER_LIST_ID FROM CUSTOMER_LIST WHERE CUSTOMER= '"
                + ob.getCustomer()
                + "' AND INSTITUTE= '" + ob.getInstitute() + "'";
        int slID = dontDeleteDB.getAIntDB(sqlslID, "CUSTOMER_LIST_ID");

        String sqlbalanceOrder = "SELECT BALANCE_ORDER FROM CUSTOMER_LIST WHERE CUSTOMER_LIST_ID= '" + slID + "'";

        double balanceOrder = dontDeleteDB.getADoubleDB(sqlbalanceOrder, "BALANCE_ORDER");

        String sqltotalOrder = "SELECT TOTAL_ORDER FROM CUSTOMER_LIST WHERE CUSTOMER_LIST_ID= '" + slID + "'";

        double totalOrder = dontDeleteDB.getADoubleDB(sqltotalOrder, "TOTAL_ORDER");

        balanceOrder -= ob.getPrice();
        totalOrder -= ob.getPrice();

        query = "update CUSTOMER_LIST set TOTAL_ORDER = ?"
                + "where CUSTOMER = ? AND INSTITUTE = ?";
        dontDeleteDB.setADoubleDB(query, (totalOrder),
                ob.getCustomer(), ob.getInstitute());
        query = "update CUSTOMER_LIST set BALANCE_ORDER = ?"
                + "where CUSTOMER = ? AND INSTITUTE = ?";
        dontDeleteDB.setADoubleDB(query, (balanceOrder),
                ob.getCustomer(), ob.getInstitute());
        
        //UPCOMING QUANTITY
        query = "DELETE FROM PENDING_DELIVERIES WHERE PRODUCT= '" + ob.getProduct()
                + "' AND INVOICE_NO= '" + ob.getInvoiceNo()+ "'";
        dontDeleteDB.delete(query);

        dontDeleteDB.closeDatabase();
    }

}

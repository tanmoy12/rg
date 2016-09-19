/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Tanmoy
 */
public class TruckTransController implements Initializable {

    @FXML
    private TableView<SaleDeliveryDB> truckTable;
    @FXML
    private TableColumn<SaleDeliveryDB, String> truckDateCol;
    @FXML
    private TableColumn<SaleDeliveryDB, String> truckCustomerCol;
    @FXML
    private TableColumn<SaleDeliveryDB, String> truckTruckNoCol;
    @FXML
    private TableColumn<SaleDeliveryDB, String> truckBillNoCol;
    @FXML
    private TableColumn<SaleDeliveryDB, String> truckMobileNoCol;
    @FXML
    private TableColumn<SaleDeliveryDB, Integer> truckStateCol;
    @FXML
    private Button truckSearchButt;

    private AutoCompleteTextField TtTruckNo;
    private AutoCompleteTextField TtBillNo;
    private AutoCompleteTextField TtSupplier;
    @FXML
    private AnchorPane truckAnchor;
    
    public static String SendTtBillNo=null; 

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        DontDeleteDB dontDeleteDB = new DontDeleteDB();
        dontDeleteDB.connectDatabase();
        TtTruckNo = new AutoCompleteTextField();
        TtTruckNo.setEntries(dontDeleteDB.getSuggestionList("SELECT * FROM SALE_DELIVERY;", "TRUCK_NO"));
        TtTruckNo.setMaxSize(160, 25);
        TtTruckNo.setLayoutX(40);
        TtTruckNo.setLayoutY(90);
        TtTruckNo.setPromptText("TRUCK NO");

        truckAnchor.getChildren().add(TtTruckNo);

        //PRODUCT NAME
        TtBillNo = new AutoCompleteTextField();
        TtBillNo.setEntries(dontDeleteDB.getSuggestionList("SELECT * FROM SALE_DELIVERY;", "BILL_NO"));
        TtBillNo.setMaxSize(160, 25);
        TtBillNo.setLayoutX(280);
        TtBillNo.setLayoutY(90);
        TtBillNo.setPromptText("BILL NO.");

        truckAnchor.getChildren().add(TtBillNo);

        //STORAGE NAME
        TtSupplier = new AutoCompleteTextField();
        TtSupplier.setEntries(dontDeleteDB.getSuggestionList("SELECT * FROM CUSTOMER_LIST;", "CUSTOMER"));
        TtSupplier.setMaxSize(160, 25);
        TtSupplier.setLayoutX(520);
        TtSupplier.setLayoutY(90);
        TtSupplier.setPromptText("CUSTOMER");

        truckAnchor.getChildren().add(TtSupplier);

        truckDateCol.setCellValueFactory(new PropertyValueFactory<>("date2"));
        truckCustomerCol.setCellValueFactory(new PropertyValueFactory<>("customer"));
        truckBillNoCol.setCellValueFactory(new PropertyValueFactory<>("billNo"));
        truckMobileNoCol.setCellValueFactory(new PropertyValueFactory<>("truckMobileNo"));
        truckTruckNoCol.setCellValueFactory(new PropertyValueFactory<>("truckNo"));
        truckStateCol.setCellValueFactory(new PropertyValueFactory<>("user"));

        truckTable.setItems(dontDeleteDB.getSaleDelivery("SELECT * FROM SALE_DELIVERY order by DATE DESC;"));

        dontDeleteDB.closeDatabase();
    }

    @FXML
    private void TtViewButtClick(ActionEvent event) throws IOException {
        SaleDeliveryDB SdOb = truckTable.getSelectionModel().getSelectedItem();
        if (SdOb == null) {
        } else {

            SendTtBillNo = SdOb.getBillNo();
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
            truckTable.setItems(dontDeleteDB.getSaleDelivery("SELECT * FROM SALE_DELIVERY order by DATE DESC  ;"));
            dontDeleteDB.closeDatabase();
            SendTtBillNo=null;
        }
    }

    @FXML
    private void TtSearchClick(ActionEvent event) {

        DontDeleteDB dontDeleteDB = new DontDeleteDB();
        dontDeleteDB.connectDatabase();
        int where = 0;
        String maker = "SELECT * FROM SALE_DELIVERY ";
        if (TtTruckNo.getText() != null && !"".equals(TtTruckNo.getText())) {

            maker = maker + "WHERE TRUCK_NO='" + TtTruckNo.getText() + "'";
            where++;
        }

        if (TtBillNo.getText() != null && !"".equals(TtBillNo.getText())) {

            if (where > 0) {
                maker = maker + "AND BILL_NO= '" + TtBillNo.getText() + "'";
            } else {
                maker = maker + "WHERE BILL_NO= '" + TtBillNo.getText() + "'";
                where++;
            }

        }

        if (TtSupplier.getText() != null && !"".equals(TtSupplier.getText())) {

            if (where > 0) {
                maker = maker + "AND CUSTOMER= '" + TtSupplier.getText() + "'";
            } else {
                maker = maker + "WHERE CUSTOMER= '" + TtSupplier.getText() + "'";
                where++;
            }

        }
        
        System.out.println(maker);

        truckTable.setItems(dontDeleteDB.getSaleDelivery(maker + " order by DATE DESC  ;"));
        dontDeleteDB.closeDatabase();

    }

    @FXML
    private void TtChangeButtClick(ActionEvent event) throws SQLException {
        DontDeleteDB dontDeleteDB=new DontDeleteDB();
        dontDeleteDB.connectDatabase();
        SaleDeliveryDB SdOb = truckTable.getSelectionModel().getSelectedItem();
        if (SdOb == null) {
            
        } else if("LEFT".equals(SdOb.getUser())) {
            String query = "update SALE_DELIVERY set USER = ?"
                + "where BILL_NO = ?";
            dontDeleteDB.setAStringDB(query, "ARRIVED",
                SdOb.getBillNo());
        
        }else if("ARRIVED".equals(SdOb.getUser())){
             String query = "update SALE_DELIVERY set USER = ?"
                + "where BILL_NO = ?";
            dontDeleteDB.setAStringDB(query, "LEFT",
                SdOb.getBillNo());
        }
        truckTable.setItems(dontDeleteDB.getSaleDelivery("SELECT * FROM SALE_DELIVERY order by DATE DESC  ;"));
            
        dontDeleteDB.closeDatabase();
        
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Mithu
 */
public class TransferProductController implements Initializable {

    @FXML
    private HBox TpProductV;
    @FXML
    private HBox TpStorageV;
    @FXML
    private HBox TpTotalKgV;
    private HBox TpAvgRateV;
    @FXML
    private Button ApDoneButt;

    private IntegerTextField TpTotalKg;
    private AutoCompleteTextField TpProduct;
    private AutoCompleteTextField TpStorage;
    private AutoCompleteTextField TpStorage1;
    @FXML
    private HBox TpStorageV1;

    java.sql.Timestamp date = new java.sql.Timestamp(new java.util.Date().getTime());

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Database database = new Database();
        database.connect_database();

        TpTotalKg = new IntegerTextField();
        TpTotalKgV.getChildren().add(TpTotalKg);

        TpProduct = new AutoCompleteTextField();
        TpProduct.setEntries(database.getSuggestionList("SELECT * FROM TOTAL_STOCK;", "PRODUCT"));
        TpProduct.setMaxSize(150, 25);
        TpProduct.setPromptText("PRODUCT");
        TpProductV.getChildren().add(TpProduct);

        TpStorage = new AutoCompleteTextField();
        TpStorage.setEntries(database.getSuggestionList("SELECT * FROM TOTAL_STOCK;", "STORAGE"));
        TpStorage.setMaxSize(150, 25);
        TpStorage.setPromptText("STORAGE");
        TpStorageV.getChildren().add(TpStorage);

        TpStorage1 = new AutoCompleteTextField();
        TpStorage1.setEntries(database.getSuggestionList("SELECT * FROM TOTAL_STOCK;", "STORAGE"));
        TpStorage1.setMaxSize(150, 25);
        TpStorage1.setPromptText("STORAGE");
        TpStorageV1.getChildren().add(TpStorage1);

        database.close_database();
        errorhandling();

    }

    private void errorhandling() {

        ApDoneButt.disableProperty().bind(
                Bindings.isEmpty(TpProduct.textProperty())
                .or(Bindings.isEmpty(TpStorage.textProperty()))
                .or(Bindings.isEmpty(TpStorage1.textProperty()))
                .or(Bindings.isEmpty(TpTotalKg.textProperty()))
        );

    }

    @FXML
    private void ApDoneButtClick(ActionEvent event) throws IOException {
        if (TpProduct.getText() != null && TpStorage.getText() != null
                && TpTotalKg.getText() != null && TpStorage1.getText() != null) {
            DontDeleteDB dontDeleteDB = new DontDeleteDB();
            dontDeleteDB.connectDatabase();

            String stockSql = "SELECT TOTAL_STOCK_ID FROM TOTAL_STOCK WHERE PRODUCT= '"
                    + TpProduct.getText()
                    + "' AND STORAGE= '" + TpStorage.getText() + "';";
            int stockidfrom = dontDeleteDB.getAIntDB(stockSql, "TOTAL_STOCK_ID");

            stockSql = "SELECT TOTAL_STOCK_ID FROM TOTAL_STOCK WHERE PRODUCT= '"
                    + TpProduct.getText()
                    + "' AND STORAGE= '" + TpStorage1.getText() + "';";
            int stockidto = dontDeleteDB.getAIntDB(stockSql, "TOTAL_STOCK_ID");

            if (stockidfrom == 0 || stockidto == 0) {
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

            stockSql = "SELECT TOTAL_KG FROM TOTAL_STOCK WHERE PRODUCT= '"
                    + TpProduct.getText()
                    + "' AND STORAGE= '" + TpStorage.getText() + "';";
            int TKgfrom = dontDeleteDB.getAIntDB(stockSql, "TOTAL_KG");
            
            
            
            //plus plus in total stock
            double TotalKg = IntegerTextField.doubleConvert(TpTotalKg.getText());
            TKgfrom -= TotalKg;
            
            String query = "update TOTAL_STOCK set TOTAL_KG = ?"
                    + "where PRODUCT = ? AND STORAGE = ?";
            dontDeleteDB.setADoubleDB(query, (TKgfrom),
                    TpProduct.getText(), TpStorage.getText());
            
            stockSql = "SELECT TOTAL_KG FROM TOTAL_STOCK WHERE PRODUCT= '"
                    + TpProduct.getText()
                    + "' AND STORAGE= '" + TpStorage1.getText() + "';";
            int TKgto = dontDeleteDB.getAIntDB(stockSql, "TOTAL_KG");
            TKgto += TotalKg;
            
            query = "update TOTAL_STOCK set TOTAL_KG = ?"
                    + "where PRODUCT = ? AND STORAGE = ?";
            dontDeleteDB.setADoubleDB(query, (TKgto),
                    TpProduct.getText(), TpStorage1.getText());
            
            Stage stage = (Stage) ApDoneButt.getScene().getWindow();
            stage.close();
            dontDeleteDB.closeDatabase();

        } else {
            FrontController.error = "FILL in ALL DETAILS";
            Parent p = FXMLLoader.load(getClass().getResource("Error.fxml"));
            Stage sstage = new Stage();
            Scene scene = new Scene(p);
            sstage.initOwner(
                    ((Node) event.getSource()).getScene().getWindow());
            sstage.setScene(scene);
            sstage.setResizable(false);
            sstage.showAndWait();
        }
    }

}

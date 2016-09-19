/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import static business.FrontController.newSet;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author Mithu
 */
public class NewProductController implements Initializable {

    private AutoCompleteTextField ApProduct;
    private AutoCompleteTextField ApStorage;
    @FXML
    private Button ApDoneButt;
    
    TotalStockDB glob = new TotalStockDB();
    
    java.sql.Timestamp date = new java.sql.Timestamp(new java.util.Date().getTime());
    @FXML
    private Button ApDeleteButt;
    private IntegerTextField ApTotalKg;
    private IntegerTextField ApAvgRate;
    @FXML
    private HBox ApTotalKgV;
    @FXML
    private HBox ApAvgRateV;
    @FXML
    private HBox ApProductV;
    @FXML
    private HBox ApStorageV;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        DontDeleteDB dontDeleteDB= new DontDeleteDB();
        dontDeleteDB.connectDatabase();
        ApTotalKg = new IntegerTextField();
        ApTotalKgV.getChildren().add(ApTotalKg);
        //balanceByDelivery
        ApAvgRate = new IntegerTextField();
        ApAvgRateV.getChildren().add(ApAvgRate);
        
        ApProduct = new AutoCompleteTextField();
        ApProduct.setEntries(dontDeleteDB.getSuggestionList("SELECT * FROM TOTAL_STOCK;", "PRODUCT"));
        ApProduct.setPromptText("PRODUCT");
        ApProductV.getChildren().add(ApProduct);
        
        ApStorage = new AutoCompleteTextField();
        ApStorage.setEntries(dontDeleteDB.getSuggestionList("SELECT * FROM TOTAL_STOCK;", "STORAGE"));
        ApStorage.setPromptText("STORAGE");
        ApStorageV.getChildren().add(ApStorage);
        
        dontDeleteDB.closeDatabase();
        
        if (StockListController.sendTsOb != null) {
            
            glob = StockListController.sendTsOb;
            
            ApProduct.setText(glob.getProduct());
            ApStorage.setText(glob.getStorage());
            ApTotalKg.setText(glob.getTotalKg2());
            ApAvgRate.setText(glob.getAvgrate2());
            
            ApDoneButt.setText("SAVE");
            ApDeleteButt.setDisable(false);
            
            date=glob.getDate();

        }

    }    

    @FXML
    private void ApDoneButtClick(ActionEvent event) throws IOException {
        if (ApProduct.getText() != null && ApStorage.getText() != null
                && ApTotalKg.getText() != null && ApAvgRate.getText() != null
                && IntegerTextField.doubleConvert(ApTotalKg.getText())>=0) {
            DontDeleteDB dontDeleteDB = new DontDeleteDB();
            dontDeleteDB.connectDatabase();

            TotalStockDB ob = new TotalStockDB();

            if ("SAVE".equals(ApDoneButt.getText())) {

                String query = "DELETE FROM TOTAL_STOCK WHERE TOTAL_STOCK_ID = '" + glob.getId() + "'";
                dontDeleteDB.delete(query);

            }

            //SHADMAN STARTS HERE
            
            ob.setDate(date);
            ob.setProduct(ApProduct.getText());
            ob.setStorage(ApStorage.getText());
            ob.setTotalKg(IntegerTextField.doubleConvert(ApTotalKg.getText()));
            ob.setAvgrate(IntegerTextField.doubleConvert(ApAvgRate.getText()));

            ////CHECK IF THIS SUPPLIER IS NEW OR NOT WITH RESPECT TO SUPPLIER NAME AND INSTITUTE NAME
            String mySql = "SELECT PRODUCT FROM TOTAL_STOCK WHERE PRODUCT= '" + ApProduct.getText() 
                    + "'AND STORAGE= '" + ApStorage.getText() + "'";
            String createNew = dontDeleteDB.getAStringDB(mySql, "PRODUCT");

            if (createNew != null && !"".equals(createNew) && !"null".equals(createNew)) {

                //NOT A NEW SUPPLIER
                Parent p = FXMLLoader.load(getClass().getResource("RepeatError.fxml"));
                Stage stage = new Stage();
                Scene scene = new Scene(p);
                stage.initOwner(
                        ((Node) event.getSource()).getScene().getWindow());
                stage.setScene(scene);
                stage.setResizable(false);
                stage.show();

            } else {
                dontDeleteDB.insertTotalStock(ob);

                // front end
                Stage stage = (Stage) ApDoneButt.getScene().getWindow();
                Parent p = FXMLLoader.load(getClass().getResource("Successful.fxml"));
                FadeTransition f = new FadeTransition(Duration.millis(1700), p);
                f.setFromValue(0.0);
                f.setToValue(1.0);
                f.play();
                Scene scene = new Scene(p);

                stage.setScene(scene);
                stage.setX(480);
                stage.setY(250);
                stage.setResizable(false);
                stage.show();
                dontDeleteDB.closeDatabase();
            }

        } else {
            Parent p = FXMLLoader.load(getClass().getResource("Error.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(p);
            stage.initOwner(
                    ((Node) event.getSource()).getScene().getWindow());
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        }
    }

    @FXML
    private void ApDeleteButtClick(ActionEvent event) throws IOException {
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

            String query = "DELETE FROM TOTAL_STOCK WHERE TOTAL_STOCK_ID = '" + glob.getId() + "'";
            dontDeleteDB.delete(query);
            newSet = false;
        }
        dontDeleteDB.closeDatabase();
        stage=(Stage) ApDoneButt.getScene().getWindow();
        stage.close();
    }
    
}

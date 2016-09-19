/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author shadman264
 */
public class BankNewPartyCreateController implements Initializable {

    @FXML
    private Button BnpcCancelButt;
    
    @FXML
    private Button BnpcCustomerButt;
    @FXML
    private Button BnpcSupplierButt;

    public static boolean newset = false;
    @FXML
    private Label NcNewEntityLabel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        NcNewEntityLabel.setText(FrontController.newCreateEntitiySetText);
       
        newset = false;

    }

    @FXML
    private void BnpcCancelClick(ActionEvent event) {
        FrontController.newSet = false;
        Stage stage = (Stage) BnpcCancelButt.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void BnpcCustomerClick(ActionEvent event) throws IOException {

        Parent p = FXMLLoader.load(getClass().getResource("NewCustomer.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(p);

        //FOR MAKING BACKGROUND STAGE INACTIVE
        Stage Cstage = new Stage();
        Cstage.initModality(Modality.WINDOW_MODAL);
        Cstage.initOwner(
                ((Node) event.getSource()).getScene().getWindow());

        Cstage.setScene(scene);

        Cstage.setResizable(false);
        //Cstage.hide();
        Cstage.showAndWait();
        FrontController.newSet = newset;

        stage = (Stage) BnpcCustomerButt.getScene().getWindow();
        stage.close();

        //PARTY WAS NEW CUSTOMER
        FrontController.partyFlag = "customer";
    }

    @FXML
    private void BnpcSupplierButtClick(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        Parent p = FXMLLoader.load(getClass().getResource("NewSupplier.fxml"));
        stage = new Stage();
        Scene scene = new Scene(p);
        stage.initOwner(
                ((Node) event.getSource()).getScene().getWindow());
        stage.setScene(scene);
        stage.setResizable(false);
        stage.showAndWait();

        FrontController.newSet = newset;

        stage = (Stage) BnpcSupplierButt.getScene().getWindow();
        stage.close();

        //PARTY WAS NEW SUPPLIER
        FrontController.partyFlag = "supplier";
    }

}

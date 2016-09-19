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
public class NewCreateController implements Initializable {

    @FXML
    private Label newItem;
    @FXML
    private Button NcCancelButt;
    public static boolean newset=false;

    public NewCreateController() {
    }

    @FXML
    private Label NcNewEntityLabel;

    private Button NcLaterButt;
    @FXML
    private Button NcYesButt;

    public static String bankName = null;
    public static String institute = null;
    public static String customer = null;
    public static String supplier = null;
    public static String product = null;
    public static String storage = null;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        NcNewEntityLabel.setText(FrontController.newCreateEntitiySetText);
        newItem.setText(FrontController.newItem);
        newset=false;

    }

    public Label getNcNewEntityLabel() {
        return NcNewEntityLabel;
    }

    

    @FXML
    private void NewCreateYesClick(ActionEvent event) throws IOException {

        if (FrontController.newCreateEntitiySetText.equals("NEW BANK")) {

            bankName = FrontController.bankName;
            Parent p = FXMLLoader.load(getClass().getResource("NewBank.fxml"));
            Stage stage = new Stage();
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(
                    ((Node) event.getSource()).getScene().getWindow());
            Scene scene = new Scene(p);

            stage.setScene(scene);

            stage.setResizable(false);
            stage.hide();
            stage.showAndWait();

        }

        else if (FrontController.newCreateEntitiySetText.equals("NEW CUSTOMER")) {

            customer = FrontController.customer;
            System.out.println(customer + "newCreate");
            institute = FrontController.institute;
            Parent p = FXMLLoader.load(getClass().getResource("NewCustomer.fxml"));
            Stage stage = new Stage();
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(
                    ((Node) event.getSource()).getScene().getWindow());
            Scene scene = new Scene(p);

            stage.setScene(scene);

            stage.setResizable(false);
            stage.hide();
            stage.showAndWait();
        }

        else if (FrontController.newCreateEntitiySetText.equals("NEW SUPPLIER")) {

            supplier = FrontController.supplier;
            institute = FrontController.institute;
            Parent p = FXMLLoader.load(getClass().getResource("NewSupplier.fxml"));
            Stage stage = new Stage();
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(
                    ((Node) event.getSource()).getScene().getWindow());
            Scene scene = new Scene(p);

            stage.setScene(scene);

            stage.setResizable(false);
            stage.hide();
            stage.showAndWait();
        }

        else if (FrontController.newCreateEntitiySetText.equals("NEW STOCK")) {

            Stage nstage = new Stage();
            nstage.initModality(Modality.WINDOW_MODAL);
            nstage.initOwner(
                    ((Node) event.getSource()).getScene().getWindow());

            Parent p = FXMLLoader.load(getClass().getResource("NewStock.fxml"));
            Scene scene = new Scene(p);

            nstage.setScene(scene);
            nstage.hide();
            nstage.showAndWait();
        }
        bankName = null;
        customer = null;
        supplier = null;
        institute = null;
        FrontController.newSet = newset;

        Stage stage = (Stage) NcYesButt.getScene().getWindow();
        stage.close();

    }

    @FXML
    private void NewCreateCancelClick(ActionEvent event) {

          
        FrontController.newSet = false;
        Stage stage = (Stage) NcCancelButt.getScene().getWindow();
        stage.close();
    }

}


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
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author shadman264
 */
public class NewSupplierController implements Initializable {

    @FXML
    private Button NsDoneButt;

    //AutoSuggetion
    @FXML
    private AnchorPane NsAnchorPane;
    //private AutoCompleteTextField NewSupplierSupplierName;
    private AutoCompleteTextField NsInstituteName;
    private IntegerTextField NsBalanceByOrder;
    private IntegerTextField NsBalanceByDelivery;
    @FXML
    private TextArea NsAddress;
    @FXML
    private TextField NsMobileNo;
    @FXML
    private TextArea NsRemarks;
    @FXML
    private TextField NsSupplierName;

    @FXML
    private RadioButton NsBalanceDeliveryDebitRadio;
    @FXML
    private ToggleGroup Balance;
    @FXML
    private RadioButton NsBalanceDeliveryCreditRadio;
    @FXML
    private RadioButton NsBalanceOrderCreditRadio;
    @FXML
    private RadioButton NsBalanceOrderDebitRadio;
    @FXML
    private ToggleGroup Balance1;
    @FXML
    private TextField NsSupplierSerial;
    @FXML
    private Label NcPhoneNo;

    SupplierListDB glob = new SupplierListDB();

    public static String supplier = null;
    public static String customer = null;
    @FXML
    private Button NsDeleteButt;
    
    java.sql.Timestamp date = new java.sql.Timestamp(new java.util.Date().getTime());

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //connecting the database
        Database database = new Database();
        database.connect_database();

        //INSTITUTE
        NsInstituteName = new AutoCompleteTextField();
        NsInstituteName.setEntries(database.getSuggestionList("SELECT * FROM SUPPLIER_LIST;", "INSTITUTE"));
        NsInstituteName.setPrefSize(167, 25);
        NsInstituteName.setLayoutX(219);
        NsInstituteName.setLayoutY(189);
        NsInstituteName.setPromptText("INSTITUTE NAME");

        //balanceByOrder
        NsBalanceByOrder = new IntegerTextField();
        NsBalanceByOrder.setPrefSize(167, 25);
        NsBalanceByOrder.setLayoutX(219);
        NsBalanceByOrder.setLayoutY(246);

        //balanceByDelivery
        NsBalanceByDelivery = new IntegerTextField();
        NsBalanceByDelivery.setPrefSize(167, 25);
        NsBalanceByDelivery.setLayoutX(219);
        NsBalanceByDelivery.setLayoutY(292);

        NsAnchorPane.getChildren().addAll(NsInstituteName, NsBalanceByOrder, NsBalanceByDelivery);

        if (FrontController.supplier != null) {
            NsSupplierName.setText(FrontController.supplier);
            NsInstituteName.setText(FrontController.institute);
            NsSupplierName.setEditable(false);
            NsInstituteName.setEditable(false);
        } else if (SupplierListController.sendSlOb != null) {
            
            glob = SupplierListController.sendSlOb;
            NsAddress.setText(glob.getAddress());
            if (glob.getBalanceDelivery() < 0) {
                NsBalanceDeliveryCreditRadio.setSelected(true);
                NsBalanceByDelivery.setText(IntegerTextField.numformat(-glob.getBalanceDelivery()));
            } else {
                NsBalanceDeliveryDebitRadio.setSelected(true);
                NsBalanceByDelivery.setText(glob.getBalanceDelivery2());
            }
            if (glob.getBalanceOrder() < 0) {
                NsBalanceOrderCreditRadio.setSelected(true);
                NsBalanceByOrder.setText(IntegerTextField.numformat(-glob.getBalanceOrder()));
            } else {
                NsBalanceOrderDebitRadio.setSelected(true);
                NsBalanceByOrder.setText(glob.getBalanceOrder2());
            }
            NsSupplierName.setText(glob.getSupplier());
            NsSupplierSerial.setText(glob.getSupplierSerial2());
            NsInstituteName.setText(glob.getInstitute());
            NsMobileNo.setText(glob.getContacts());
            NsRemarks.setText(glob.getRemark());

            NsDoneButt.setText("SAVE");
            NsDeleteButt.setVisible(true);
            
            date=glob.getDate();

        }

        database.close_database();
    }

    @FXML
    private void NewSupplierInput(ActionEvent event) throws IOException {
        // database insert
        if (NsSupplierName.getText() != null && NsSupplierSerial.getText() != null
                && NsBalanceByOrder.getText() != null && NsBalanceByDelivery.getText() != null
                && NsInstituteName.getText() != null && !"".equals(NsSupplierName.getText())
                && !"".equals(NsBalanceByOrder.getText())
                && !"".equals(NsBalanceByDelivery.getText())
                && !"".equals(NsInstituteName.getText())
                && (NsBalanceOrderDebitRadio.isSelected() == true || true == NsBalanceOrderCreditRadio.isSelected())
                && (NsBalanceDeliveryDebitRadio.isSelected() == true || true == NsBalanceDeliveryCreditRadio.isSelected())
                && NsSupplierSerial.getText() != null && !"".equals(NsSupplierSerial.getText())) {
            DontDeleteDB dontDeleteDB = new DontDeleteDB();
            dontDeleteDB.connectDatabase();

            SupplierListDB ob = new SupplierListDB();

            if ("SAVE".equals(NsDoneButt.getText())) {

                String query = "DELETE FROM SUPPLIER_LIST WHERE SUPPLIER_LIST_ID = '" + glob.getId() + "'";
                dontDeleteDB.delete(query);

            }

            //SHADMAN STARTS HERE
            
            ob.setDate(date);

            ob.setUser(FrontController.user);
            ob.setSupplier(NsSupplierName.getText());
            ob.setInstitute(NsInstituteName.getText());

            ////CHECK IF THIS SUPPLIER IS NEW OR NOT WITH RESPECT TO SUPPLIER NAME AND INSTITUTE NAME
            String mySql = "SELECT SUPPLIER FROM SUPPLIERLIST WHERE SUPPIER= '" + NsSupplierName.getText() + "'AND INSTITUTE= '" + NsInstituteName.getText() + "'";
            String createNew = dontDeleteDB.getAStringDB(mySql, "SUPPLIER");

            if (createNew != null && !"".equals(createNew) && !"null".equals(createNew)) {

                //NOT A NEW SUPPLIER
                Parent p = FXMLLoader.load(getClass().getResource("RepeatError.fxml"));
                Stage stage = new Stage();
                Scene scene = new Scene(p);
                stage.initOwner(
                        ((Node) event.getSource()).getScene().getWindow());
                stage.setScene(scene);
                stage.setResizable(false);
                stage.showAndWait();

            } else {
                ob.setRemark(NsRemarks.getText());
                ob.setPayment(0);
                ob.setTotalOrder(0);
                ob.setTotalDelivery(0);

                if (NsBalanceOrderDebitRadio.isSelected() == true) {
                    double d = IntegerTextField.doubleConvert(NsBalanceByOrder.getText());
                    ob.setBalanceOrder(d);
                } else if (NsBalanceOrderCreditRadio.isSelected() == true) {
                    double d = IntegerTextField.doubleConvert(NsBalanceByOrder.getText());
                    d *= -1;
                    ob.setBalanceOrder(d);
                }

                if (NsBalanceDeliveryDebitRadio.isSelected() == true) {
                    double d = IntegerTextField.doubleConvert(NsBalanceByDelivery.getText());
                    ob.setBalanceDelivery(d);

                } else if (NsBalanceDeliveryCreditRadio.isSelected() == true) {
                    double d = IntegerTextField.doubleConvert(NsBalanceByDelivery.getText());
                    d *= -1;
                    ob.setBalanceDelivery(d);
                }

                ob.setAddress(NsAddress.getText());
                ob.setContacts(NsMobileNo.getText());
                String temp = NsSupplierSerial.getText();
                double d = Double.parseDouble(temp);
                ob.setSupplierSerial(d);
                
                if ("SAVE".equals(NsDoneButt.getText())) {
                    ob.setId(glob.getId());
                }

                //SHADMAN ENDS HERE
                dontDeleteDB.insertSupplierList(ob);

                // front end
                Stage stage = (Stage) NsDoneButt.getScene().getWindow();
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
                BankNewPartyCreateController.newset = true;
                FrontController.partyFlag = "supplier";
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
            stage.showAndWait();
        }
    }

    @FXML
    private void DeleteSupplierInput(ActionEvent event) throws IOException {
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

            String query = "DELETE FROM SUPPLIER_LIST WHERE SUPPLIER_LIST_ID = '" + glob.getId() + "'";
            dontDeleteDB.delete(query);
            newSet = false;
        }
        dontDeleteDB.closeDatabase();
        stage=(Stage) NsAddress.getScene().getWindow();
        stage.close();
    }

}

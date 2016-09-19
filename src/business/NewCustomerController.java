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
public class NewCustomerController implements Initializable {

    @FXML
    private Button NcDoneButt;
    //AutoSuggetion
    @FXML
    private AnchorPane NcAnchorPane;
    //private AutoCompleteTextField NewCustomerCustomerName;
    private AutoCompleteTextField NcInstituteName;
    private IntegerTextField NcBalanceByOrder;
    private IntegerTextField NcBalanceByDelivery;
    @FXML
    private TextArea NcAddress;

    @FXML
    private TextField NcMobileNo;
    @FXML
    private TextArea NcRemarks;
    @FXML
    private TextField NcCustomerName;
    @FXML
    private RadioButton NcBalanceDeliveryDebitRadio;
    @FXML
    private RadioButton NcBalanceDeliveryCreditRadio;
    @FXML
    private RadioButton NcBalanceOrderCreditRadio;
    @FXML
    private RadioButton NcBalanceOrderDebitRadio;
    @FXML
    private ToggleGroup Balance;
    @FXML
    private ToggleGroup Balance1;
    @FXML
    private TextField NcCustomerSerial;

    CustomerListDB glob = new CustomerListDB();
    @FXML
    private Label NcPhoneNo;
    @FXML
    private Button NcDeleteButt;
    
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
        NcInstituteName = new AutoCompleteTextField();
        NcInstituteName.setEntries(database.getSuggestionList("SELECT * FROM CUSTOMER_LIST;", "INSTITUTE"));
        NcInstituteName.setPrefSize(167, 25);
        NcInstituteName.setLayoutX(221);
        NcInstituteName.setLayoutY(189);
        NcInstituteName.setPromptText("INSTITUTE NAME");

        //balanceByOrder
        NcBalanceByOrder = new IntegerTextField();
        NcBalanceByOrder.setPrefSize(167, 25);
        NcBalanceByOrder.setLayoutX(219);
        NcBalanceByOrder.setLayoutY(246);

        //balanceByDelivery
        NcBalanceByDelivery = new IntegerTextField();
        NcBalanceByDelivery.setPrefSize(167, 25);
        NcBalanceByDelivery.setLayoutX(219);
        NcBalanceByDelivery.setLayoutY(292);
        
        NcDeleteButt.setVisible(false);

        NcAnchorPane.getChildren().addAll(NcInstituteName, NcBalanceByOrder, NcBalanceByDelivery);

        if (FrontController.customer != null) {
            NcCustomerName.setText(FrontController.customer);
            NcInstituteName.setText(FrontController.institute);
            NcCustomerName.setEditable(false);
            NcInstituteName.setEditable(false);
        } else if (CustomerListController.sendClOb != null) {

            glob = CustomerListController.sendClOb;
            NcAddress.setText(glob.getAddress());
            if (glob.getBalanceDelivery() < 0) {
                NcBalanceDeliveryCreditRadio.setSelected(true);
                NcBalanceByDelivery.setText(IntegerTextField.numformat(-glob.getBalanceDelivery()));
            } else {
                NcBalanceDeliveryDebitRadio.setSelected(true);
                NcBalanceByDelivery.setText(glob.getBalanceDelivery2());
            }
            if (glob.getBalanceOrder() < 0) {
                NcBalanceOrderCreditRadio.setSelected(true);
                NcBalanceByOrder.setText(IntegerTextField.numformat(-glob.getBalanceOrder()));
            } else {
                NcBalanceOrderDebitRadio.setSelected(true);
                NcBalanceByOrder.setText(glob.getBalanceOrder2());
            }
            NcCustomerName.setText(glob.getCustomer());
            NcCustomerSerial.setText(glob.getCustomerSerial2());
            NcInstituteName.setText(glob.getInstitute());
            NcMobileNo.setText(glob.getContacts());
            NcRemarks.setText(glob.getRemark());

            NcDoneButt.setText("SAVE");
            NcDeleteButt.setVisible(true);
            
            date=glob.getDate();

        }

        database.close_database();
    }

    @FXML
    private void NewCustomerInput(ActionEvent event) throws IOException {

        if (NcCustomerName.getText() != null && NcCustomerSerial.getText() != null
                && NcBalanceByOrder.getText() != null && NcBalanceByDelivery.getText() != null
                && NcInstituteName.getText() != null && !"".equals(NcCustomerName.getText())
                && !"".equals(NcBalanceByOrder.getText()) && !"".equals(NcBalanceByDelivery.getText())
                && !"".equals(NcInstituteName.getText())
                && (NcBalanceOrderDebitRadio.isSelected() == true || true == NcBalanceOrderCreditRadio.isSelected())
                && (NcBalanceDeliveryDebitRadio.isSelected() == true || true == NcBalanceDeliveryCreditRadio.isSelected())
                && NcCustomerSerial.getText() != null && !"".equals(NcCustomerSerial.getText())) {

            DontDeleteDB dontDeleteDB = new DontDeleteDB();

            dontDeleteDB.connectDatabase();

            CustomerListDB ob = new CustomerListDB();

            //SHADMAN STARTS HERE
            
            ob.setDate(date);

            ob.setUser(FrontController.user);

            if (NcDoneButt.getText() == "SAVE") {

                String query = "DELETE FROM CUSTOMER_LIST WHERE CUSTOMER_LIST_ID = '" + glob.getId() + "'";
                dontDeleteDB.delete(query);

            }

            ob.setCustomer(NcCustomerName.getText());
            ob.setInstitute(NcInstituteName.getText());

            ////CHECK IF THIS CUSTOMER IS NEW OR NOT WITH RESPECT TO CUSTOMER NAME AND INSTITUTE NAME
            String mySql = "SELECT CUSTOMER FROM CUSTOMER_LIST WHERE CUSTOMER= '"
                    + NcCustomerName.getText() + "'AND INSTITUTE= '" + NcInstituteName.getText() + "'";
            String createNew = dontDeleteDB.getAStringDB(mySql, "CUSTOMER");

            if (createNew != null && !"".equals(createNew) && !"null".equals(createNew)) {

                //NOT A NEW CUSTOMER
                Parent p = FXMLLoader.load(getClass().getResource("RepeatError.fxml"));
                Stage stage = new Stage();
                Scene scene = new Scene(p);
                stage.initOwner(
                        ((Node) event.getSource()).getScene().getWindow());
                stage.setScene(scene);
                stage.setResizable(false);
                stage.showAndWait();

            } else {
                ob.setRemark(NcRemarks.getText());
                ob.setAddress(NcAddress.getText());
                ob.setContacts(NcMobileNo.getText());

                if (NcBalanceOrderDebitRadio.isSelected() == true) {
                    double d = IntegerTextField.doubleConvert(NcBalanceByOrder.getText());
                    ob.setBalanceOrder(d);

                } else if (NcBalanceOrderCreditRadio.isSelected() == true) {
                    double d = IntegerTextField.doubleConvert(NcBalanceByOrder.getText());
                    d *= -1;
                    ob.setBalanceOrder(d);
                }

                if (NcBalanceDeliveryDebitRadio.isSelected() == true) {
                    double d = IntegerTextField.doubleConvert(NcBalanceByDelivery.getText());
                    ob.setBalanceDelivery(d);

                } else if (NcBalanceDeliveryCreditRadio.isSelected() == true) {
                    double d = IntegerTextField.doubleConvert(NcBalanceByDelivery.getText());
                    d *= -1;
                    ob.setBalanceDelivery(d);
                }

                ob.setPayment(0);
                ob.setTotalOrder(0);
                ob.setTotalDelivery(0);

                String temp = NcCustomerSerial.getText();
                double d = Double.parseDouble(temp);
                ob.setCustomerSerial(d);
                
                if (NcDoneButt.getText() == "SAVE") {
                    ob.setId(glob.getId());
                }

                //SHADMAN ENDS HERE
                dontDeleteDB.insertCustomerList(ob);

                ////front end
                Stage stage = (Stage) NcDoneButt.getScene().getWindow();

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
                FrontController.partyFlag = "customer";
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
    private void DeleteCustomerInput(ActionEvent event) throws IOException {
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

            String query = "DELETE FROM CUSTOMER_LIST WHERE CUSTOMER_LIST_ID = '" + glob.getId() + "'";
            dontDeleteDB.delete(query);
            newSet = false;
        }
        dontDeleteDB.closeDatabase();
        stage=(Stage) NcAddress.getScene().getWindow();
        stage.close();
    }

}

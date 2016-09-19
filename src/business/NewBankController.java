/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import static business.FrontController.newSet;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
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
import javafx.scene.control.MenuButton;
import javafx.scene.control.RadioButton;
import javafx.scene.control.RadioMenuItem;
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
 * @author Tanmoy
 */
public class NewBankController implements Initializable {

    @FXML
    private Button NbDoneButt;
    //AutoSuggetion
    @FXML
    private AnchorPane NbAnchorPane;

    @FXML
    private TextField NbAccountNumber;
    //private TextField NbBalance;
    @FXML
    private TextArea NbAddress;
    @FXML
    private TextField NbPhoneNo;
    @FXML
    private MenuButton NbTypeDrop;
    @FXML
    private TextField NbBankName;

    @FXML
    private TextField NbAccountName;
    @FXML
    private TextField NbBranch;
    @FXML
    private ToggleGroup balance;
    @FXML
    private RadioButton NbDebitRadio;
    @FXML
    private RadioButton NbCreditRadio;

    private IntegerTextField NbBalance;
    @FXML
    private RadioMenuItem NbTypeSavingsDrop;
    @FXML
    private ToggleGroup TypeOfAccount;
    @FXML
    private RadioMenuItem NbTypeFixedDepositDrop;
    @FXML
    private RadioMenuItem NbTypeCreditAccountDrop;

    BankListDB glob;
    @FXML
    private Button NbDeleteButt;
    
    java.sql.Timestamp date = new java.sql.Timestamp(new java.util.Date().getTime());

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        NbBalance = new IntegerTextField();
        NbBalance.setLayoutX(208);
        NbBalance.setLayoutY(270);
        NbBalance.setPrefWidth(199);
        NbAnchorPane.getChildren().add(NbBalance);

        NbDeleteButt.setVisible(false);
        if (NewCreateController.bankName != null) {
            NbBankName.setText(NewCreateController.bankName);
            NbBankName.setEditable(false);
        }
        if (BankListController.sendBlOb != null) {

            glob = BankListController.sendBlOb;
            NbAccountName.setText(glob.getAccountName());
            NbAccountNumber.setText(glob.getAccountNumber());
            NbAddress.setText(glob.getAddress());

            NbBankName.setText(glob.getBankName());
            NbBranch.setText(glob.getBranch());
            if (glob.getBalance() < 0) {
                NbCreditRadio.setSelected(true);
                NbBalance.setText(IntegerTextField.numformat(-glob.getBalance()));
            } else {
                NbDebitRadio.setSelected(true);
                NbBalance.setText(glob.getBalance2());
            }
            NbPhoneNo.setText(glob.getContacts());
            NbTypeDrop.setText(glob.getTypeOfAccount());

            NbDoneButt.setText("SAVE");
            NbDeleteButt.setVisible(true);
            NbDeleteButt.setDisable(false);
            
            date=glob.getDate();

        }

    }

    @FXML
    private void NewBankInput(ActionEvent event) throws IOException, SQLException {
        DontDeleteDB dontDeleteDB = new DontDeleteDB();

        dontDeleteDB.connectDatabase();

        if (NbBankName.getText() != null && NbBalance.getText() != null
                && NbAccountName.getText() != null && NbAccountNumber.getText() != null
                && !"".equals(NbBankName.getText()) && !"".equals(NbBalance.getText())
                && !"".equals(NbAccountName.getText()) && !"".equals(NbAccountNumber.getText())
                && (NbDebitRadio.isSelected() == true || true == NbCreditRadio.isSelected())) {
            // DATABASE INSERT

            BankListDB ob = new BankListDB();

            //SHADMAN STARTS HERE
            
            ob.setDate(date);

            ob.setUser(FrontController.user);

            ob.setBankName(NbBankName.getText());

            if ("SAVE".equals(NbDoneButt.getText())) {
                String query = "DELETE FROM BANK_LIST WHERE BANK_LIST_ID = '" + glob.getId() + "'";
                dontDeleteDB.delete(query);

            }

            //CHECK IF IT IS A NEW BANK OR NOT
            String mySql = "SELECT BANK_NAME FROM BANK_LIST WHERE BANK_NAME= '"
                    + NbBankName.getText() + "'";
            String createNew = dontDeleteDB.getAStringDB(mySql, "BANK_NAME");
            if (createNew != null && !"".equals(createNew) && !"null".equals(createNew)) {
                //NOT A NEW BANK

                Parent p = FXMLLoader.load(getClass().getResource("RepeatError.fxml"));
                Stage stage = new Stage();
                Scene scene = new Scene(p);
                stage.initOwner(
                        ((Node) event.getSource()).getScene().getWindow());
                stage.setScene(scene);
                stage.setResizable(false);
                stage.show();

            } //REALLY IT IS A NEW BANK
            else {
                ob.setBranch(NbBranch.getText());
                ob.setAccountNumber(NbAccountNumber.getText());
                if (NbTypeSavingsDrop.isSelected()) {
                    ob.setTypeOfAccount(NbTypeSavingsDrop.getText());
                } else if (NbTypeFixedDepositDrop.isSelected()) {
                    ob.setTypeOfAccount(NbTypeFixedDepositDrop.getText());
                } else if (NbTypeCreditAccountDrop.isSelected()) {
                    ob.setTypeOfAccount(NbTypeCreditAccountDrop.getText());
                }
                ob.setAccountName(NbAccountName.getText());
                if (NbDebitRadio.isSelected() == true) {
                    double d = IntegerTextField.doubleConvert(NbBalance.getText());
                    ob.setBalance(d);

                } else if (NbCreditRadio.isSelected() == true) {
                    double d = IntegerTextField.doubleConvert(NbBalance.getText());
                    d *= -1;
                    ob.setBalance(d);
                }
                ob.setAddress(NbAddress.getText());
                ob.setContacts(NbPhoneNo.getText());
                
                if ("SAVE".equals(NbDoneButt.getText())) {
                    ob.setId(glob.getId());
                }

                ////SHADMAN ENDS HERE
                dontDeleteDB.insertBankList(ob);

                Stage stage = (Stage) NbDoneButt.getScene().getWindow();

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

                NewCreateController.newset = true;
            }

        } else {
            FrontController.error = "DETAILS NOT COMPLETED";
            Parent p = FXMLLoader.load(getClass().getResource("Error.fxml"));

            Stage stage = new Stage();
            Scene scene = new Scene(p);
            stage.initOwner(
                    ((Node) event.getSource()).getScene().getWindow());
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();

        }
        dontDeleteDB.closeDatabase();

        // FRONT       END
    }

    @FXML
    private void SetTextForTypeOfAccount(ActionEvent event) {
        if (NbTypeSavingsDrop.isSelected()) {
            NbTypeDrop.setText(NbTypeSavingsDrop.getText());
        } else if (NbTypeFixedDepositDrop.isSelected()) {
            NbTypeDrop.setText(NbTypeFixedDepositDrop.getText());
        }
        if (NbTypeCreditAccountDrop.isSelected()) {
            NbTypeDrop.setText(NbTypeCreditAccountDrop.getText());
        }
    }

    @FXML
    private void DeleteBankInput(ActionEvent event) throws IOException {
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

            String query = "DELETE FROM BANK_LIST WHERE BANK_LIST_ID = '" + glob.getId() + "'";
            dontDeleteDB.delete(query);

            newSet = false;
            stage = (Stage) NbDeleteButt.getScene().getWindow();
            stage.close();
        }
        dontDeleteDB.closeDatabase();
    }

}

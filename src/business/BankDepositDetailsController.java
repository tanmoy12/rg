/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Tanmoy
 */
public class BankDepositDetailsController implements Initializable {

    @FXML
    private Label HeaderLab;
    @FXML
    private Button EditButt;
    @FXML
    private Button DeleteButt;

    private TextField bank;
    @FXML
    private VBox BdPartyV;
    @FXML
    private VBox BdInstituteV;
    @FXML
    private VBox BdBankV;
    @FXML
    private VBox BdAmountV;
    @FXML
    private TextField BdLp;

    private AutoCompleteTextField BdParty;

    private AutoCompleteTextField BdInstitute;

    private AutoCompleteTextField BdBankCash;

    private IntegerTextField BdAmount;
    @FXML
    private RadioButton BtDepositRadio;
    @FXML
    private ToggleGroup transtype;
    @FXML
    private RadioButton BtWithdrawalRadio;

    BankTransactionDB glob;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        //ob=FrontController.ob;
        //bank.setText(ob.getBankOrCash());

        DontDeleteDB dontDeleteDB = new DontDeleteDB();
        dontDeleteDB.connectDatabase();

        //connecting the database
        Database database = new Database();
        database.connect_database();

        BdParty = new AutoCompleteTextField();
        BdParty.setEntries(database.getSuggestionList("SELECT * FROM CUSTOMER_LIST;", "CUSTOMER"));
        BdParty.setEntries(database.getSuggestionList("SELECT * FROM SUPPLIER_LIST;", "SUPPLIER"));
        BdParty.setMaxSize(150, 25);
        BdParty.setPromptText("PARTY");

        BdInstitute = new AutoCompleteTextField();
        BdInstitute.setEntries(database.getSuggestionList("SELECT * FROM CUSTOMER_LIST;", "INSTITUTE"));
        BdInstitute.setEntries(database.getSuggestionList("SELECT * FROM SUPPLIER_LIST;", "INSTITUTE"));
        BdInstitute.setMaxSize(150, 25);
        BdInstitute.setPromptText("INSTITUTE");

        BdBankCash = new AutoCompleteTextField();
        BdBankCash.setEntries(database.getSuggestionList("SELECT * FROM BANK_LIST;", "BANK_NAME"));
        BdBankCash.setMaxSize(150, 25);
        BdBankCash.setPromptText("BANK/CASH");

        BdAmount = new IntegerTextField();
        BdAmount.setPrefSize(150, 25);
        BdAmount.setPromptText("AMOUNT");
        BdAmountV.getChildren().add(BdAmount);

        BdPartyV.getChildren().add(BdParty);
        BdInstituteV.getChildren().add(BdInstitute);
        BdBankV.getChildren().add(BdBankCash);

        if (BankListController.sendBtOb != null) {
            glob = BankListController.sendBtOb;
        }

        BdParty.setText(glob.getEntity());
        BdInstitute.setText(glob.getRemarks());
        BdBankCash.setText(glob.getBankOrCash());
        BdLp.setText(glob.getLp());

        if (glob.getAmount() < 0) {
            BtWithdrawalRadio.setSelected(true);
            BdAmount.setText(IntegerTextField.numformat(-glob.getAmount()));
        } else {
            BtDepositRadio.setSelected(true);
            BdAmount.setText(glob.getAmount2());
        }

        BdParty.setOnAction((ActionEvent evt) -> {
            BdInstitute.setText(getInstitute(BdParty.getText()));
            BdInstitute.requestFocus();
        });

    }

    @FXML
    private void EditButtClick(ActionEvent event) throws SQLException, IOException {

        if (glob.getAmount() < 0) {
            BwDelete(glob);

        } else {
            BdDeleteEntry(glob);

        }

        if (BtWithdrawalRadio.isSelected()) {
            BankWithdrawalEntry();

        } else {
            BankDepositEntry();

        }

        Stage stage = (Stage) DeleteButt.getScene().getWindow();
        stage.close();

    }

    @FXML
    private void DeleteButtClick(ActionEvent event) throws SQLException {

        if (glob.getAmount() < 0) {
            BwDelete(glob);
        } else {
            BdDeleteEntry(glob);
        }

        Stage stage = (Stage) DeleteButt.getScene().getWindow();
        stage.close();

    }

    private String getInstitute(String party) {

        DontDeleteDB dontDeleteDB = new DontDeleteDB();
        dontDeleteDB.connectDatabase();

        String mySql = "SELECT INSTITUTE FROM CUSTOMER_LIST WHERE CUSTOMER= '"
                + party + "'";
        String Ins = dontDeleteDB.getAStringDB(mySql, "INSTITUTE");
        if ("".equals(Ins) || Ins == null) {
            mySql = "SELECT INSTITUTE FROM SUPPLIER_LIST WHERE SUPPLIER = '"
                    + party + "'";
            Ins = dontDeleteDB.getAStringDB(mySql, "INSTITUTE");
            dontDeleteDB.closeDatabase();
            return Ins;

        }
        dontDeleteDB.closeDatabase();
        return Ins;

    }

    private void BankDepositEntry() throws IOException, SQLException {

        Timestamp dateTimeNow = glob.getDate();

        DontDeleteDB dontDeleteDB = new DontDeleteDB();
        dontDeleteDB.connectDatabase();

        //BANK DEPOSIT NEW BANK CREATE
        String mySql = "SELECT BANK_LIST_ID FROM BANK_LIST WHERE BANK_NAME= '" + BdBankCash.getText()
                + "'";
        int createNew = dontDeleteDB.getAIntDB(mySql, "BANK_LIST_ID");
        if (createNew == 0) {

            FrontController.error = "DETAILS NOT IN DATABASE";
            Parent p = FXMLLoader.load(getClass().getResource("Error.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(p);

            stage.setScene(scene);
            stage.setX(480);
            stage.setY(250);
            stage.showAndWait();
            return;

        }

        mySql = "SELECT CUSTOMER_LIST_ID FROM CUSTOMER_LIST WHERE CUSTOMER= '" + BdParty.getText()
                + "'AND INSTITUTE= '" + BdInstitute.getText() + "'";
        int customerCheck = dontDeleteDB.getAIntDB(mySql, "CUSTOMER_LIST_ID");

        if (customerCheck == 0) {

            mySql = "SELECT SUPPLIER_LIST_ID FROM SUPPLIER_LIST WHERE SUPPLIER = '" + BdParty.getText()
                    + "' AND INSTITUTE = '" + BdInstitute.getText() + "'";
            int supplierCheck = dontDeleteDB.getAIntDB(mySql, "SUPPLIER_LIST_ID");

            if (supplierCheck == 0) {

                FrontController.error = "DETAILS NOT IN DATABASE";
                Parent p = FXMLLoader.load(getClass().getResource("Error.fxml"));
                Stage stage = new Stage();
                Scene scene = new Scene(p);

                stage.setScene(scene);
                stage.setX(480);
                stage.setY(250);
                stage.showAndWait();
                return;
            }
        } //SHADMAN'S WORK

        mySql = "SELECT CUSTOMER_LIST_ID FROM CUSTOMER_LIST WHERE CUSTOMER= '" + BdParty.getText()
                + "'" + "AND INSTITUTE= '" + BdInstitute.getText() + "'";
        customerCheck = dontDeleteDB.getAIntDB(mySql, "CUSTOMER_LIST_ID");

        if (customerCheck == 0) {
            mySql = "SELECT SUPPLIER_LIST_ID FROM SUPPLIER_LIST WHERE SUPPLIER = '" + BdParty.getText()
                    + "' AND INSTITUTE = '" + BdInstitute.getText() + "'";
            int supplierCheck = dontDeleteDB.getAIntDB(mySql, "SUPPLIER_LIST_ID");
            if (supplierCheck != 0) {
                //supplier entry

                BankTransactionDB bankTransactionDB = new BankTransactionDB();

                bankTransactionDB.setBankOrCash(BdBankCash.getText());
                bankTransactionDB.setAmount(IntegerTextField.doubleConvert(BdAmount.getText()));
                bankTransactionDB.setEntity(BdParty.getText());
                bankTransactionDB.setDate(dateTimeNow);

                bankTransactionDB.setRemarks(BdInstitute.getText());
                bankTransactionDB.setLp(BdLp.getText());
                bankTransactionDB.setType(2.0);

                dontDeleteDB.insertBankTransaction(bankTransactionDB);

                String command = "SELECT BANK_TRANSACTION_ID FROM BANK_TRANSACTION ORDER BY BANK_TRANSACTION_ID DESC LIMIT 1";
                int idBankTrans = dontDeleteDB.getAIntDB(command, "BANK_TRANSACTION_ID");

                SupplierTransactionDB supplierTransactionDB = new SupplierTransactionDB();

                supplierTransactionDB.setBankOrCash(BdBankCash.getText());
                supplierTransactionDB.setCredit(IntegerTextField.doubleConvert(BdAmount.getText()));
                supplierTransactionDB.setSupplier(BdParty.getText());
                supplierTransactionDB.setDate(dateTimeNow);
                supplierTransactionDB.setIdBankDepoWith(idBankTrans);
                supplierTransactionDB.setInstitute(BdInstitute.getText());
                supplierTransactionDB.setLp(BdLp.getText());
                supplierTransactionDB.setType(1.0);

                dontDeleteDB.insertSupplierTransaction(supplierTransactionDB);

                String sql = "SELECT BALANCE_ORDER FROM SUPPLIER_LIST WHERE SUPPLIER_LIST_ID = '"
                        + supplierCheck + "'";
                double balanceOrder = dontDeleteDB.getADoubleDB(sql, "BALANCE_ORDER");
                sql = "SELECT BALANCE_DELIVERY FROM SUPPLIER_LIST WHERE SUPPLIER_LIST_ID = '"
                        + supplierCheck + "'";
                double balanceDelivery = dontDeleteDB.getADoubleDB(sql, "BALANCE_DELIVERY");
                sql = "SELECT PAYMENT FROM SUPPLIER_LIST WHERE SUPPLIER_LIST_ID = '"
                        + supplierCheck + "'";
                double payment = dontDeleteDB.getADoubleDB(sql, "PAYMENT");
                balanceOrder -= IntegerTextField.doubleConvert(BdAmount.getText());
                balanceDelivery -= IntegerTextField.doubleConvert(BdAmount.getText());
                payment -= IntegerTextField.doubleConvert(BdAmount.getText());

                String query = "update SUPPLIER_LIST set BALANCE_ORDER = ? , BALANCE_DELIVERY = ? , PAYMENT = ? "
                        + "where SUPPLIER_LIST_ID = ?";
                dontDeleteDB.set3DoubleDB(query, balanceOrder, balanceDelivery, payment, supplierCheck);

                sql = "SELECT BALANCE FROM BANK_LIST WHERE BANK_LIST_ID = '"
                        + createNew + "'";
                double balance = dontDeleteDB.getADoubleDB(sql, "BALANCE");

                balance += IntegerTextField.doubleConvert(BdAmount.getText());

                query = "update BANK_LIST set BALANCE = ? "
                        + "where BANK_LIST_ID = ?";
                dontDeleteDB.setADoubleDB(query, balance, createNew);

            }
        } else {
            //customer entry
            BankTransactionDB bankTransactionDB = new BankTransactionDB();

            bankTransactionDB.setBankOrCash(BdBankCash.getText());
            bankTransactionDB.setAmount(IntegerTextField.doubleConvert(BdAmount.getText()));
            bankTransactionDB.setEntity(BdParty.getText());
            bankTransactionDB.setDate(dateTimeNow);
            //bankTransactionDB.setIdBankDepoWith(idBankDepoWith);
            bankTransactionDB.setRemarks(BdInstitute.getText());
            bankTransactionDB.setLp(BdLp.getText());
            bankTransactionDB.setType(1.0);

            dontDeleteDB.insertBankTransaction(bankTransactionDB);

            String command = "SELECT BANK_TRANSACTION_ID FROM BANK_TRANSACTION ORDER BY BANK_TRANSACTION_ID DESC LIMIT 1";
            int idBankTrans = dontDeleteDB.getAIntDB(command, "BANK_TRANSACTION_ID");

            CustomerTransactionDB customerTransactionDB = new CustomerTransactionDB();

            customerTransactionDB.setBank(BdBankCash.getText());
            customerTransactionDB.setCredit(IntegerTextField.doubleConvert(BdAmount.getText()));
            customerTransactionDB.setCustomer(BdParty.getText());
            customerTransactionDB.setDate(dateTimeNow);
            customerTransactionDB.setIdBankDepoWith(idBankTrans);
            customerTransactionDB.setInstitute(BdInstitute.getText());
            customerTransactionDB.setLp(BdLp.getText());
            customerTransactionDB.setType(1.0);

            dontDeleteDB.insertCustomerTransaction(customerTransactionDB);

            String sql = "SELECT BALANCE_ORDER FROM CUSTOMER_LIST WHERE CUSTOMER_LIST_ID = '"
                    + customerCheck + "'";
            double balanceOrder = dontDeleteDB.getADoubleDB(sql, "BALANCE_ORDER");
            sql = "SELECT BALANCE_DELIVERY FROM CUSTOMER_LIST WHERE CUSTOMER_LIST_ID = '"
                    + customerCheck + "'";
            double balanceDelivery = dontDeleteDB.getADoubleDB(sql, "BALANCE_DELIVERY");
            sql = "SELECT PAYMENT FROM CUSTOMER_LIST WHERE CUSTOMER_LIST_ID = '"
                    + customerCheck + "'";
            double payment = dontDeleteDB.getADoubleDB(sql, "PAYMENT");
            balanceOrder -= IntegerTextField.doubleConvert(BdAmount.getText());
            balanceDelivery -= IntegerTextField.doubleConvert(BdAmount.getText());
            payment += IntegerTextField.doubleConvert(BdAmount.getText());

            String query = "update CUSTOMER_LIST set BALANCE_ORDER = ? , BALANCE_DELIVERY = ? , PAYMENT = ? "
                    + "where CUSTOMER_LIST_ID = ?";
            dontDeleteDB.set3DoubleDB(query, balanceOrder, balanceDelivery, payment, customerCheck);

            sql = "SELECT BALANCE FROM BANK_LIST WHERE BANK_LIST_ID = '"
                    + createNew + "'";
            double balance = dontDeleteDB.getADoubleDB(sql, "BALANCE");

            balance += IntegerTextField.doubleConvert(BdAmount.getText());

            query = "update BANK_LIST set BALANCE = ? "
                    + "where BANK_LIST_ID = ?";
            dontDeleteDB.setADoubleDB(query, balance, createNew);

        }

        dontDeleteDB.closeDatabase();

    }

    private void BankWithdrawalEntry() throws IOException, SQLException {

        Timestamp dateTimeNow = glob.getDate();

        DontDeleteDB dontDeleteDB = new DontDeleteDB();
        dontDeleteDB.connectDatabase();

        //BANK DEPOSIT NEW BANK CREATE
        String mySql = "SELECT BANK_LIST_ID FROM BANK_LIST WHERE BANK_NAME= '" + BdBankCash.getText() + "'";
        int createNew = dontDeleteDB.getAIntDB(mySql, "BANK_LIST_ID");
        if (createNew == 0) {

            FrontController.error = "DETAILS NOT IN DATABASE";
            Parent p = FXMLLoader.load(getClass().getResource("Error.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(p);

            stage.setScene(scene);
            stage.setX(480);
            stage.setY(250);
            stage.showAndWait();
            return;

        }

        mySql = "SELECT CUSTOMER_LIST_ID FROM CUSTOMER_LIST WHERE CUSTOMER= '" + BdParty.getText()
                + "'AND INSTITUTE= '" + BdInstitute.getText() + "'";
        int customerCheck = dontDeleteDB.getAIntDB(mySql, "CUSTOMER_LIST_ID");

        if (customerCheck == 0) {
            mySql = "SELECT SUPPLIER_LIST_ID FROM SUPPLIER_LIST WHERE SUPPLIER = '" + BdParty.getText()
                    + "' AND INSTITUTE = '" + BdInstitute.getText() + "'";
            int supplierCheck = dontDeleteDB.getAIntDB(mySql, "SUPPLIER_LIST_ID");

            if (supplierCheck == 0) {

                FrontController.error = "DETAILS NOT IN DATABASE";
                Parent p = FXMLLoader.load(getClass().getResource("Error.fxml"));
                Stage stage = new Stage();
                Scene scene = new Scene(p);

                stage.setScene(scene);
                stage.setX(480);
                stage.setY(250);
                stage.showAndWait();
                return;

            }
        } //SHADMAN'S WORK

        mySql = "SELECT CUSTOMER_LIST_ID FROM CUSTOMER_LIST WHERE CUSTOMER= '" + BdParty.getText()
                + "'" + "AND INSTITUTE= '" + BdInstitute.getText() + "'";
        customerCheck = dontDeleteDB.getAIntDB(mySql, "CUSTOMER_LIST_ID");

        if (customerCheck == 0) {
            mySql = "SELECT SUPPLIER_LIST_ID FROM SUPPLIER_LIST WHERE SUPPLIER = '" + BdParty.getText()
                    + "' AND INSTITUTE = '" + BdInstitute.getText() + "'";
            int supplierCheck = dontDeleteDB.getAIntDB(mySql, "SUPPLIER_LIST_ID");
            if (supplierCheck != 0) {
                //supplier entry
                BankTransactionDB bankTransactionDB = new BankTransactionDB();

                bankTransactionDB.setBankOrCash(BdBankCash.getText());
                bankTransactionDB.setAmount(-IntegerTextField.doubleConvert(BdAmount.getText()));
                bankTransactionDB.setEntity(BdParty.getText());
                bankTransactionDB.setDate(dateTimeNow);
                //bankTransactionDB.setIdBankDepoWith(idBankDepoWith);
                bankTransactionDB.setRemarks(BdInstitute.getText());
                bankTransactionDB.setLp(BdLp.getText());
                bankTransactionDB.setType(4.0);

                dontDeleteDB.insertBankTransaction(bankTransactionDB);

                String command = "SELECT BANK_TRANSACTION_ID FROM BANK_TRANSACTION ORDER BY BANK_TRANSACTION_ID DESC LIMIT 1";
                int idBankTrans = dontDeleteDB.getAIntDB(command, "BANK_TRANSACTION_ID");

                SupplierTransactionDB supplierTransactionDB = new SupplierTransactionDB();

                supplierTransactionDB.setBankOrCash(BdBankCash.getText());
                supplierTransactionDB.setDebit(IntegerTextField.doubleConvert(BdAmount.getText()));
                supplierTransactionDB.setSupplier(BdParty.getText());
                supplierTransactionDB.setDate(dateTimeNow);
                supplierTransactionDB.setIdBankDepoWith(idBankTrans);
                supplierTransactionDB.setInstitute(BdInstitute.getText());
                supplierTransactionDB.setLp(BdLp.getText());
                supplierTransactionDB.setType(4.0);

                dontDeleteDB.insertSupplierTransaction(supplierTransactionDB);

                String sql = "SELECT BALANCE_ORDER FROM SUPPLIER_LIST WHERE SUPPLIER_LIST_ID = '"
                        + supplierCheck + "'";
                double balanceOrder = dontDeleteDB.getADoubleDB(sql, "BALANCE_ORDER");
                sql = "SELECT BALANCE_DELIVERY FROM SUPPLIER_LIST WHERE SUPPLIER_LIST_ID = '"
                        + supplierCheck + "'";
                double balanceDelivery = dontDeleteDB.getADoubleDB(sql, "BALANCE_DELIVERY");
                sql = "SELECT PAYMENT FROM SUPPLIER_LIST WHERE SUPPLIER_LIST_ID = '"
                        + supplierCheck + "'";
                double payment = dontDeleteDB.getADoubleDB(sql, "PAYMENT");
                balanceOrder += IntegerTextField.doubleConvert(BdAmount.getText());
                balanceDelivery += IntegerTextField.doubleConvert(BdAmount.getText());
                payment += IntegerTextField.doubleConvert(BdAmount.getText());

                String query = "update SUPPLIER_LIST set BALANCE_ORDER = ? , BALANCE_DELIVERY = ? , "
                        + "PAYMENT = ? " + "where SUPPLIER_LIST_ID = ?";
                dontDeleteDB.set3DoubleDB(query, balanceOrder, balanceDelivery, payment, supplierCheck);

                sql = "SELECT BALANCE FROM BANK_LIST WHERE BANK_LIST_ID = '"
                        + createNew + "'";
                double balance = dontDeleteDB.getADoubleDB(sql, "BALANCE");

                balance -= IntegerTextField.doubleConvert(BdAmount.getText());

                query = "update BANK_LIST set BALANCE = ? "
                        + "where BANK_LIST_ID = ?";
                dontDeleteDB.setADoubleDB(query, balance, createNew);

            }
        } else {
            //customer entry
            BankTransactionDB bankTransactionDB = new BankTransactionDB();

            bankTransactionDB.setBankOrCash(BdBankCash.getText());
            bankTransactionDB.setAmount((-1) * IntegerTextField.doubleConvert(BdAmount.getText()));
            bankTransactionDB.setEntity(BdParty.getText());
            bankTransactionDB.setDate(dateTimeNow);
            //bankTransactionDB.setIdBankDepoWith(idBankDepoWith);
            bankTransactionDB.setRemarks(BdInstitute.getText());
            bankTransactionDB.setLp(BdLp.getText());
            bankTransactionDB.setType(3.0);

            dontDeleteDB.insertBankTransaction(bankTransactionDB);

            String command = "SELECT BANK_TRANSACTION_ID FROM BANK_TRANSACTION ORDER BY BANK_TRANSACTION_ID DESC LIMIT 1";
            int idBankTrans = dontDeleteDB.getAIntDB(command, "BANK_TRANSACTION_ID");

            CustomerTransactionDB customerTransactionDB = new CustomerTransactionDB();

            customerTransactionDB.setBank(BdBankCash.getText());
            customerTransactionDB.setDebit(IntegerTextField.doubleConvert(BdAmount.getText()));
            customerTransactionDB.setCustomer(BdParty.getText());
            customerTransactionDB.setDate(dateTimeNow);
            customerTransactionDB.setIdBankDepoWith(idBankTrans);
            customerTransactionDB.setInstitute(BdInstitute.getText());
            customerTransactionDB.setLp(BdLp.getText());
            customerTransactionDB.setType(3.0);

            dontDeleteDB.insertCustomerTransaction(customerTransactionDB);

            String sql = "SELECT BALANCE_ORDER FROM CUSTOMER_LIST WHERE CUSTOMER_LIST_ID = '"
                    + customerCheck + "'";
            double balanceOrder = dontDeleteDB.getADoubleDB(sql, "BALANCE_ORDER");
            sql = "SELECT BALANCE_DELIVERY FROM CUSTOMER_LIST WHERE CUSTOMER_LIST_ID = '"
                    + customerCheck + "'";
            double balanceDelivery = dontDeleteDB.getADoubleDB(sql, "BALANCE_DELIVERY");
            sql = "SELECT PAYMENT FROM CUSTOMER_LIST WHERE CUSTOMER_LIST_ID = '"
                    + customerCheck + "'";
            double payment = dontDeleteDB.getADoubleDB(sql, "PAYMENT");
            balanceOrder += IntegerTextField.doubleConvert(BdAmount.getText());
            balanceDelivery += IntegerTextField.doubleConvert(BdAmount.getText());
            payment -= IntegerTextField.doubleConvert(BdAmount.getText());

            String query = "update CUSTOMER_LIST set BALANCE_ORDER = ? , BALANCE_DELIVERY = ? , PAYMENT = ? "
                    + "where CUSTOMER_LIST_ID = ?";
            dontDeleteDB.set3DoubleDB(query, balanceOrder, balanceDelivery, payment, customerCheck);

            sql = "SELECT BALANCE FROM BANK_LIST WHERE BANK_LIST_ID = '"
                    + createNew + "'";
            double balance = dontDeleteDB.getADoubleDB(sql, "BALANCE");

            balance -= IntegerTextField.doubleConvert(BdAmount.getText());

            query = "update BANK_LIST set BALANCE = ? "
                    + "where BANK_LIST_ID = ?";
            dontDeleteDB.setADoubleDB(query, balance, createNew);

        }

        dontDeleteDB.closeDatabase();
    }

    private void BdDeleteEntry(BankTransactionDB ob) throws SQLException {
        DontDeleteDB dontDeleteDB = new DontDeleteDB();
        dontDeleteDB.connectDatabase();

        Timestamp date = new java.sql.Timestamp(new java.util.Date().getTime());

        String query = "DELETE FROM BANK_TRANSACTION WHERE BANK_TRANSACTION_ID = '" + ob.getId() + "'";
        dontDeleteDB.delete(query);
        if (ob.getType() == 1.0) {
            query = "DELETE FROM CUSTOMER_TRANSACTION WHERE ID_BANK_DEPO_WITH = '" + ob.getId() + "'";
            dontDeleteDB.delete(query);

            String mySql = "SELECT CUSTOMER_LIST_ID FROM CUSTOMER_LIST WHERE CUSTOMER= '" + ob.getEntity()
                    + "'" + "AND INSTITUTE= '" + ob.getRemarks() + "'";
            int customerCheck = dontDeleteDB.getAIntDB(mySql, "CUSTOMER_LIST_ID");

            String sql = "SELECT BALANCE_ORDER FROM CUSTOMER_LIST WHERE CUSTOMER_LIST_ID = '"
                    + customerCheck + "'";
            double balanceOrder = dontDeleteDB.getADoubleDB(sql, "BALANCE_ORDER");
            sql = "SELECT BALANCE_DELIVERY FROM CUSTOMER_LIST WHERE CUSTOMER_LIST_ID = '"
                    + customerCheck + "'";
            double balanceDelivery = dontDeleteDB.getADoubleDB(sql, "BALANCE_DELIVERY");
            sql = "SELECT PAYMENT FROM CUSTOMER_LIST WHERE CUSTOMER_LIST_ID = '"
                    + customerCheck + "'";
            double payment = dontDeleteDB.getADoubleDB(sql, "PAYMENT");

            balanceOrder += ob.getAmount();
            balanceDelivery += ob.getAmount();
            payment -= ob.getAmount();

            query = "update CUSTOMER_LIST set BALANCE_ORDER = ? , BALANCE_DELIVERY = ? , PAYMENT = ? "
                    + "where CUSTOMER_LIST_ID = ?";
            dontDeleteDB.set3DoubleDB(query, balanceOrder, balanceDelivery, payment, customerCheck);

            mySql = "SELECT BANK_LIST_ID FROM BANK_LIST WHERE BANK_NAME= '" + ob.getBankOrCash() + "'";
            int createNew = dontDeleteDB.getAIntDB(mySql, "BANK_LIST_ID");

            sql = "SELECT BALANCE FROM BANK_LIST WHERE BANK_LIST_ID = '"
                    + createNew + "'";
            double balance = dontDeleteDB.getADoubleDB(sql, "BALANCE");

            balance -= ob.getAmount();

            query = "update BANK_LIST set BALANCE = ? "
                    + "where BANK_LIST_ID = ?";
            dontDeleteDB.setADoubleDB(query, balance, createNew);

        } else if (ob.getType() == 2.0) {
            query = "DELETE FROM SUPPLIER_TRANSACTION WHERE ID_BANK_DEPO_WITH = '" + ob.getId() + "'";
            dontDeleteDB.delete(query);

            String mySql = "SELECT SUPPLIER_LIST_ID FROM SUPPLIER_LIST WHERE SUPPLIER = '"
                    + ob.getEntity() + "' AND INSTITUTE = '" + ob.getRemarks() + "'";
            int supplierCheck = dontDeleteDB.getAIntDB(mySql, "SUPPLIER_LIST_ID");

            String sql = "SELECT BALANCE_ORDER FROM SUPPLIER_LIST WHERE SUPPLIER_LIST_ID = '"
                    + supplierCheck + "'";
            double balanceOrder = dontDeleteDB.getADoubleDB(sql, "BALANCE_ORDER");
            sql = "SELECT BALANCE_DELIVERY FROM SUPPLIER_LIST WHERE SUPPLIER_LIST_ID = '"
                    + supplierCheck + "'";
            double balanceDelivery = dontDeleteDB.getADoubleDB(sql, "BALANCE_DELIVERY");
            sql = "SELECT PAYMENT FROM SUPPLIER_LIST WHERE SUPPLIER_LIST_ID = '"
                    + supplierCheck + "'";
            double payment = dontDeleteDB.getADoubleDB(sql, "PAYMENT");

            balanceOrder += ob.getAmount();
            balanceDelivery += ob.getAmount();
            payment += ob.getAmount();

            query = "update SUPPLIER_LIST set BALANCE_ORDER = ? , BALANCE_DELIVERY = ? , PAYMENT = ? "
                    + "where SUPPLIER_LIST_ID = ?";
            dontDeleteDB.set3DoubleDB(query, balanceOrder, balanceDelivery, payment, supplierCheck);

            mySql = "SELECT BANK_LIST_ID FROM BANK_LIST WHERE BANK_NAME= '" + ob.getBankOrCash() + "'";
            int createNew = dontDeleteDB.getAIntDB(mySql, "BANK_LIST_ID");

            sql = "SELECT BALANCE FROM BANK_LIST WHERE BANK_LIST_ID = '"
                    + createNew + "'";
            double balance = dontDeleteDB.getADoubleDB(sql, "BALANCE");

            balance -= ob.getAmount();

            query = "update BANK_LIST set BALANCE = ? "
                    + "where BANK_LIST_ID = ?";
            dontDeleteDB.setADoubleDB(query, balance, createNew);
        }

    }

    private void BwDelete(BankTransactionDB ob) throws SQLException {
        DontDeleteDB dontDeleteDB = new DontDeleteDB();
        dontDeleteDB.connectDatabase();

        Timestamp date = new java.sql.Timestamp(new java.util.Date().getTime());

        String query = "DELETE FROM BANK_TRANSACTION WHERE BANK_TRANSACTION_ID = '" + ob.getId() + "'";
        dontDeleteDB.delete(query);
        if (ob.getType() == 3.0) {
            query = "DELETE FROM CUSTOMER_TRANSACTION WHERE ID_BANK_DEPO_WITH = '" + ob.getId() + "'";
            dontDeleteDB.delete(query);

            String mySql = "SELECT CUSTOMER_LIST_ID FROM CUSTOMER_LIST WHERE CUSTOMER= '" + ob.getEntity()
                    + "'" + "AND INSTITUTE= '" + ob.getRemarks() + "'";
            int customerCheck = dontDeleteDB.getAIntDB(mySql, "CUSTOMER_LIST_ID");

            String sql = "SELECT BALANCE_ORDER FROM CUSTOMER_LIST WHERE CUSTOMER_LIST_ID = '"
                    + customerCheck + "'";
            double balanceOrder = dontDeleteDB.getADoubleDB(sql, "BALANCE_ORDER");
            sql = "SELECT BALANCE_DELIVERY FROM CUSTOMER_LIST WHERE CUSTOMER_LIST_ID = '"
                    + customerCheck + "'";
            double balanceDelivery = dontDeleteDB.getADoubleDB(sql, "BALANCE_DELIVERY");
            sql = "SELECT PAYMENT FROM CUSTOMER_LIST WHERE CUSTOMER_LIST_ID = '"
                    + customerCheck + "'";
            double payment = dontDeleteDB.getADoubleDB(sql, "PAYMENT");

            balanceOrder -= -(ob.getAmount());
            balanceDelivery -= -(ob.getAmount());
            payment += -(ob.getAmount());

            query = "update CUSTOMER_LIST set BALANCE_ORDER = ? , BALANCE_DELIVERY = ? , PAYMENT = ? "
                    + "where CUSTOMER_LIST_ID = ?";
            dontDeleteDB.set3DoubleDB(query, balanceOrder, balanceDelivery, payment, customerCheck);

            mySql = "SELECT BANK_LIST_ID FROM BANK_LIST WHERE BANK_NAME= '" + ob.getBankOrCash() + "'";
            int createNew = dontDeleteDB.getAIntDB(mySql, "BANK_LIST_ID");

            sql = "SELECT BALANCE FROM BANK_LIST WHERE BANK_LIST_ID = '"
                    + createNew + "'";
            double balance = dontDeleteDB.getADoubleDB(sql, "BALANCE");

            balance += -ob.getAmount();

            query = "update BANK_LIST set BALANCE = ? "
                    + "where BANK_LIST_ID = ?";
            dontDeleteDB.setADoubleDB(query, balance, createNew);

        } else if (ob.getType() == 4.0) {
            query = "DELETE FROM SUPPLIER_TRANSACTION WHERE ID_BANK_DEPO_WITH = '" + ob.getId() + "'";
            dontDeleteDB.delete(query);

            String mySql = "SELECT SUPPLIER_LIST_ID FROM SUPPLIER_LIST WHERE SUPPLIER = '"
                    + ob.getEntity() + "' AND INSTITUTE = '" + ob.getRemarks() + "'";
            int supplierCheck = dontDeleteDB.getAIntDB(mySql, "SUPPLIER_LIST_ID");

            String sql = "SELECT BALANCE_ORDER FROM SUPPLIER_LIST WHERE SUPPLIER_LIST_ID = '"
                    + supplierCheck + "'";
            double balanceOrder = dontDeleteDB.getADoubleDB(sql, "BALANCE_ORDER");
            sql = "SELECT BALANCE_DELIVERY FROM SUPPLIER_LIST WHERE SUPPLIER_LIST_ID = '"
                    + supplierCheck + "'";
            double balanceDelivery = dontDeleteDB.getADoubleDB(sql, "BALANCE_DELIVERY");
            sql = "SELECT PAYMENT FROM SUPPLIER_LIST WHERE SUPPLIER_LIST_ID = '"
                    + supplierCheck + "'";
            double payment = dontDeleteDB.getADoubleDB(sql, "PAYMENT");

            balanceOrder -= -ob.getAmount();
            balanceDelivery -= -ob.getAmount();
            payment -= -ob.getAmount();

            query = "update SUPPLIER_LIST set BALANCE_ORDER = ? , BALANCE_DELIVERY = ? , PAYMENT = ? "
                    + "where SUPPLIER_LIST_ID = ?";
            dontDeleteDB.set3DoubleDB(query, balanceOrder, balanceDelivery, payment, supplierCheck);

            mySql = "SELECT BANK_LIST_ID FROM BANK_LIST WHERE BANK_NAME= '" + ob.getBankOrCash() + "'";
            int createNew = dontDeleteDB.getAIntDB(mySql, "BANK_LIST_ID");

            sql = "SELECT BALANCE FROM BANK_LIST WHERE BANK_LIST_ID = '"
                    + createNew + "'";
            double balance = dontDeleteDB.getADoubleDB(sql, "BALANCE");

            balance += -ob.getAmount();

            query = "update BANK_LIST set BALANCE = ? "
                    + "where BANK_LIST_ID = ?";
            dontDeleteDB.setADoubleDB(query, balance, createNew);
        }
    }

}

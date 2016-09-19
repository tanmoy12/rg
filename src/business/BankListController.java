/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
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
import javafx.scene.control.Tab;
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
public class BankListController implements Initializable {

    ObservableList<BankTransactionDB> data = FXCollections.observableArrayList();
    ObservableList<BankTransactionDB> temp = FXCollections.observableArrayList();
    ObservableList<BankTransactionDB> searchtemp = FXCollections.observableArrayList();
    @FXML
    private VBox BiBankCashV;
    @FXML
    private TextField BiDateTo;
    @FXML
    private TextField BiDateFrom;

    @FXML
    private TableColumn<BankListDB, String> BlBankCol;
    @FXML
    private TableColumn<BankListDB, String> BlBranchCol;
    @FXML
    private TableColumn<BankListDB, String> BlBalanceCol;
    @FXML
    private TableColumn<BankListDB, String> BlContactsCol;
    @FXML
    private TableColumn<BankTransactionDB, String> BtDateCol;
    @FXML
    private TableColumn<BankTransactionDB, String> BtBankCol;
    @FXML
    private TableColumn<BankTransactionDB, String> BtEntityCol;
    @FXML
    private TableColumn<BankTransactionDB, String> BtRemarksCol;
    @FXML
    private TableColumn<BankTransactionDB, String> BtLpCol;

    AutoCompleteTextField BlBankName;
    @FXML
    private TableView<BankListDB> BlTable;
    @FXML
    private TableView<BankTransactionDB> BtTable;
    @FXML
    private Tab RSearchButt;
    @FXML
    private TableColumn<BankTransactionDB, String> BtDebitCol;
    @FXML
    private TableColumn<BankTransactionDB, String> BtCreditCol;
    @FXML
    private Button BtViewButt;

    public static BankListDB sendBlOb = null;
    public static BankTransactionDB sendBtOb = null;

    @FXML
    private Button BlViewButt;
    @FXML
    private Button BtSearchButt;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        //connecting the database
        Database database = new Database();
        database.connect_database();

        DontDeleteDB dontDeleteDB = new DontDeleteDB();
        dontDeleteDB.connectDatabase();

        //CUSTOMER NAME
        BlBankName = new AutoCompleteTextField();
        BlBankName.setEntries(database.getSuggestionList("SELECT * FROM BANK_LIST;", "BANK_NAME"));
        BlBankName.setPromptText("BANK NAME");
        BlBankName.setMaxSize(200, 25);
        BiBankCashV.getChildren().add(BlBankName);

        //BANK/CASH LIST TABLE SHOW
        BlBankCol.setCellValueFactory(new PropertyValueFactory<>("bankName"));
        BlBranchCol.setCellValueFactory(new PropertyValueFactory<>("branch"));
        BlBalanceCol.setCellValueFactory(new PropertyValueFactory<>("balance2"));
        BlContactsCol.setCellValueFactory(new PropertyValueFactory<>("contacts"));

        BlTable.setItems(dontDeleteDB.getBankList("SELECT * FROM BANK_LIST;"));

        //BANK TRANSACTION TABLE SHOW
        BtDateCol.setCellValueFactory(new PropertyValueFactory<>("date2"));
        BtBankCol.setCellValueFactory(new PropertyValueFactory<>("bankOrCash"));
        BtEntityCol.setCellValueFactory(new PropertyValueFactory<>("entity"));
        BtRemarksCol.setCellValueFactory(new PropertyValueFactory<>("remarks"));
        BtDebitCol.setCellValueFactory(new PropertyValueFactory<>("debit2"));
        BtCreditCol.setCellValueFactory(new PropertyValueFactory<>("credit2"));

        BtLpCol.setCellValueFactory(new PropertyValueFactory<>("lp"));
        data = dontDeleteDB.getBankTransaction("SELECT * FROM BANK_TRANSACTION order by DATE DESC;");

        BtTable.setItems(data);

        dontDeleteDB.closeDatabase();
        database.close_database();
    }

    @FXML
    private void BiNewBankButtClick(ActionEvent event) throws IOException {
        Parent p = FXMLLoader.load(getClass().getResource("NewBank.fxml"));
        Stage Banks = new Stage();
        Scene scene = new Scene(p);
        Banks.initModality(Modality.WINDOW_MODAL);
        Banks.initOwner(
                ((Node) event.getSource()).getScene().getWindow());
        Banks.setScene(scene);
        Banks.setResizable(false);
        Banks.showAndWait();

        DontDeleteDB dontDeleteDB = new DontDeleteDB();
        dontDeleteDB.connectDatabase();
        BlTable.setItems(dontDeleteDB.getBankList("SELECT * FROM BANK_LIST;"));
        dontDeleteDB.closeDatabase();
    }

    @FXML
    private void BtSearchButtClick(ActionEvent event) throws ParseException {

        DontDeleteDB dontDeleteDB = new DontDeleteDB();
        dontDeleteDB.connectDatabase();
        int where = 0;
        String maker = "SELECT * FROM BANK_TRANSACTION ";
        if (BlBankName.getText() != null && !"".equals(BlBankName.getText())) {

            maker = maker + "WHERE BANK_NAME='" + BlBankName.getText() + "'";
            where++;
        }

        if (BiDateFrom.getText() != null && !"".equals(BiDateFrom.getText())) {
            DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            Date fromdate = format.parse(BiDateFrom.getText());
            java.sql.Date date = new java.sql.Date(fromdate.getTime());

            if (BiDateTo.getText() != null && !"".equals(BiDateTo.getText())) {

                Date todate = format.parse(BiDateTo.getText());
                java.sql.Date dateto = new java.sql.Date(todate.getTime());
                if (where > 0) {
                    maker = maker + "AND date(DATE)>= '" + date + "' AND date(DATE)<= '"
                            + dateto + "'";
                } else {
                    maker = maker + "WHERE date(DATE)>= '" + date + "' AND date(DATE)<= '"
                            + dateto + "'";
                }
            } else if (where > 0) {
                maker = maker + "AND date(DATE)= '" + date + "'";
            } else {
                maker = maker + "WHERE date(DATE)= '" + date + "'";
            }
        }

        BtTable.setItems(dontDeleteDB.getBankTransaction(maker + " order by DATE DESC ;"));
        dontDeleteDB.closeDatabase();

    }

    @FXML
    private void BtViewButtClick(ActionEvent event) throws IOException {
        BankTransactionDB BtOb = BtTable.getSelectionModel().getSelectedItem();
        if (BtOb == null || BtOb.getIdExpense()!=0 || BtOb.getIdRevenue()!=0) {
            
        }
        else {
            
            Parent p = FXMLLoader.load(getClass().getResource("AdminPass.fxml"));
            Stage Banks = new Stage();
            Scene scene = new Scene(p);
            Banks.initModality(Modality.WINDOW_MODAL);
            Banks.initOwner(
                    ((Node) event.getSource()).getScene().getWindow());
            Banks.setScene(scene);
            Banks.showAndWait();
            if (LoginController.level == true) {
                LoginController.level = false;
                sendBtOb = BtOb;
                //System.out.println(SoOrderNo);
                p = FXMLLoader.load(getClass().getResource("BankDepositDetails.fxml"));
                Stage stage = new Stage();
                scene = new Scene(p);
                stage.initModality(Modality.WINDOW_MODAL);
                stage.initOwner(
                        ((Node) event.getSource()).getScene().getWindow());
                stage.setScene(scene);
                stage.setResizable(false);
                stage.showAndWait();
                DontDeleteDB dontDeleteDB = new DontDeleteDB();
                dontDeleteDB.connectDatabase();
                BtTable.setItems(dontDeleteDB.getBankTransaction("SELECT * FROM BANK_TRANSACTION order by DATE DESC ;"));
                BlTable.setItems(dontDeleteDB.getBankList("SELECT * FROM BANK_LIST;"));
                dontDeleteDB.closeDatabase();
                sendBtOb = null;
                
            }
        }
        
    }

    @FXML
    private void BlViewButtClick(ActionEvent event) throws IOException {
        BankListDB BlOb = BlTable.getSelectionModel().getSelectedItem();
        if (BlOb == null) {
        } else {
            
            Parent p = FXMLLoader.load(getClass().getResource("AdminPass.fxml"));
            Stage Banks = new Stage();
            Scene scene = new Scene(p);
            Banks.initModality(Modality.WINDOW_MODAL);
            Banks.initOwner(
                    ((Node) event.getSource()).getScene().getWindow());
            Banks.setScene(scene);
            Banks.showAndWait();
          
            if (LoginController.level == true) {
                LoginController.level = false;
                sendBlOb = BlOb;
                //System.out.println(SoOrderNo);
                p = FXMLLoader.load(getClass().getResource("NewBank.fxml"));
                Stage stage = new Stage();
                scene = new Scene(p);
                stage.initModality(Modality.WINDOW_MODAL);
                stage.initOwner(
                        ((Node) event.getSource()).getScene().getWindow());
                stage.setScene(scene);
                stage.setResizable(false);
                stage.showAndWait();
                DontDeleteDB dontDeleteDB = new DontDeleteDB();
                dontDeleteDB.connectDatabase();
                BlTable.setItems(dontDeleteDB.getBankList("SELECT * FROM BANK_LIST;"));
                dontDeleteDB.closeDatabase();
                sendBlOb = null;
                LoginController.level = false;
            }
        }
    }
}

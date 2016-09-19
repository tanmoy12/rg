/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
public class ExpensesListController implements Initializable {

    @FXML
    private Button ESearchButt;
    @FXML
    private Button RSearchButt;
    @FXML
    private TableView<ExpenseDB> EeTable;
    @FXML
    private TableColumn<ExpenseDB, String> EeDateCol;
    @FXML
    private TableColumn<ExpenseDB, String> EeExpensesCol;
    @FXML
    private TableColumn<ExpenseDB, String> EeRemarksCol;
    @FXML
    private TableColumn<ExpenseDB, String> EeBankCol;
    @FXML
    private TableColumn<ExpenseDB, String> EeAmountCol;
    @FXML
    private TableColumn<ExpenseDB, String> EeLpCol;
    @FXML
    private TableView<RevenueDB> ReTable;
    @FXML
    private TableColumn<RevenueDB, String> ReDateCol;
    @FXML
    private TableColumn<RevenueDB, String> ReRevenueCol;
    @FXML
    private TableColumn<RevenueDB, String> ReRemarksCol;
    @FXML
    private TableColumn<RevenueDB, String> ReBankCol;
    @FXML
    private TableColumn<RevenueDB, String> ReAmountCol;
    @FXML
    private TableColumn<RevenueDB, String> ReLpCol;

    java.sql.Timestamp timeStamp = new Timestamp(System.currentTimeMillis());
    java.sql.Date date = new java.sql.Date(timeStamp.getTime());
    @FXML
    private Button EeViewButt;
    @FXML
    private Button ReViewButt;

    AutoCompleteTextField EeExpenses;
    AutoCompleteTextField EeBank;
    @FXML
    private VBox EeBankV;
    @FXML
    private VBox EeExpensesV;
    @FXML
    private TextField EeDateFrom;
    @FXML
    private TextField EeDateTo;
    
    public static ExpenseDB sendEeOb= new ExpenseDB();
    public static RevenueDB sendReOb= new RevenueDB();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        DontDeleteDB dontDeleteDB = new DontDeleteDB();
        dontDeleteDB.connectDatabase();

        Database database = new Database();
        database.connect_database();

        EeExpensesCol.setCellValueFactory(new PropertyValueFactory<>("expense"));
        EeRemarksCol.setCellValueFactory(new PropertyValueFactory<>("remarks"));
        EeBankCol.setCellValueFactory(new PropertyValueFactory<>("bankOrCash"));
        EeAmountCol.setCellValueFactory(new PropertyValueFactory<>("amount"));
        EeLpCol.setCellValueFactory(new PropertyValueFactory<>("lp"));
        EeDateCol.setCellValueFactory(new PropertyValueFactory<>("date2"));

        EeTable.setItems(dontDeleteDB.getExpense("SELECT * FROM EXPENSE order by DATE DESC;"));

        //REVENUE TABLE
        ReRevenueCol.setCellValueFactory(new PropertyValueFactory<>("revenue"));
        ReRemarksCol.setCellValueFactory(new PropertyValueFactory<>("remarks"));
        ReBankCol.setCellValueFactory(new PropertyValueFactory<>("bankOrCash"));
        ReAmountCol.setCellValueFactory(new PropertyValueFactory<>("amount"));
        ReLpCol.setCellValueFactory(new PropertyValueFactory<>("lp"));
        ReDateCol.setCellValueFactory(new PropertyValueFactory<>("date2"));

        ReTable.setItems(dontDeleteDB.getRevenue("SELECT * FROM REVENUE order by DATE DESC;"));

        EeExpenses = new AutoCompleteTextField();
        EeExpenses.setEntries(database.getSuggestionList("SELECT * FROM EXPENSE;", "EXPENSE"));
        EeExpenses.setEntries(database.getSuggestionList("SELECT * FROM REVENUE;", "REVENUE"));
        EeExpenses.setPromptText("EXPENSE/REVENUE");
        EeExpenses.setMaxSize(200, 25);
        EeExpensesV.getChildren().add(EeExpenses);

        EeBank = new AutoCompleteTextField();
        EeBank.setEntries(database.getSuggestionList("SELECT * FROM BANK_LIST;", "BANK_NAME"));
        EeBank.setPromptText("BANK NAME");
        EeBank.setMaxSize(200, 25);
        EeBankV.getChildren().add(EeBank);

        dontDeleteDB.closeDatabase();
        database.close_database();
    }

    @FXML
    private void EeViewButtClick(ActionEvent event) throws IOException {
        ExpenseDB EeOb = EeTable.getSelectionModel().getSelectedItem();
        if (EeOb == null) {
            
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
                sendEeOb = EeOb;
                //System.out.println(SoOrderNo);
                p = FXMLLoader.load(getClass().getResource("ExpenseDetails.fxml"));
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
                EeTable.setItems(dontDeleteDB.getExpense("SELECT * FROM EXPENSE order by DATE DESC;"));
                dontDeleteDB.closeDatabase();
                sendEeOb = null;
                
            }
        }
        
    }

    @FXML
    private void EeSearchButtClick(ActionEvent event) throws ParseException {
        DontDeleteDB dontDeleteDB = new DontDeleteDB();
        dontDeleteDB.connectDatabase();
        int where = 0;
        String maker = "SELECT * FROM EXPENSE ";
        if (EeExpenses.getText() != null && !"".equals(EeExpenses.getText())) {

            maker = maker + "WHERE EXPENSE='" + EeExpenses.getText() + "'";
            where++;
        }
        if (EeBank.getText() != null && !"".equals(EeBank.getText())) {

            if(where==0) maker = maker + " WHERE BANK_OR_CASH='" + EeBank.getText() + "'";
            else maker = maker + " AND BANK_OR_CASH='" + EeBank.getText() + "'";
            where++;
        }

        if (EeDateFrom.getText() != null && !"".equals(EeDateFrom.getText())) {
            DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            Date fromdate = format.parse(EeDateFrom.getText());
            java.sql.Date date = new java.sql.Date(fromdate.getTime());

            if (EeDateTo.getText() != null && !"".equals(EeDateTo.getText())) {

                Date todate = format.parse(EeDateTo.getText());
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

        EeTable.setItems(dontDeleteDB.getExpense(maker + " order by DATE DESC ;"));
        dontDeleteDB.closeDatabase();

    }

    @FXML
    private void ReViewButtClick(ActionEvent event) throws IOException {
    RevenueDB ReOb = ReTable.getSelectionModel().getSelectedItem();
        if (ReOb == null) {
            
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
                sendReOb = ReOb;
                //System.out.println(SoOrderNo);
                p = FXMLLoader.load(getClass().getResource("RevenueDetails.fxml"));
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
                ReTable.setItems(dontDeleteDB.getRevenue("SELECT * FROM REVENUE order by DATE DESC;"));
                dontDeleteDB.closeDatabase();
                sendReOb = null;
                
            }
        }
        
    }

    @FXML
    private void ReSearchButtClick(ActionEvent event) throws ParseException {
        DontDeleteDB dontDeleteDB = new DontDeleteDB();
        dontDeleteDB.connectDatabase();
        int where = 0;
        String maker = "SELECT * FROM REVENUE ";
        if (EeExpenses.getText() != null && !"".equals(EeExpenses.getText())) {

            maker = maker + "WHERE REVENUE='" + EeExpenses.getText() + "'";
            where++;
        }

        if (EeBank.getText() != null && !"".equals(EeBank.getText())) {

            if(where==0) maker = maker + " WHERE BANK_OR_CASH='" + EeBank.getText() + "'";
            else maker = maker + " AND BANK_OR_CASH='" + EeBank.getText() + "'";
            where++;
        }

        if (EeDateFrom.getText() != null && !"".equals(EeDateFrom.getText())) {
            DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            Date fromdate = format.parse(EeDateFrom.getText());
            java.sql.Date date = new java.sql.Date(fromdate.getTime());

            if (EeDateTo.getText() != null && !"".equals(EeDateTo.getText())) {

                Date todate = format.parse(EeDateTo.getText());
                java.sql.Date dateto = new java.sql.Date(todate.getTime());
                if (where > 0) {
                    maker = maker + " AND date(DATE)>= '" + date + "' AND date(DATE)<= '"
                            + dateto + "'";
                } else {
                    maker = maker + " WHERE date(DATE)>= '" + date + "' AND date(DATE)<= '"
                            + dateto + "'";
                }
            } else if (where > 0) {
                maker = maker + " AND date(DATE)= '" + date + "'";
            } else {
                maker = maker + " WHERE date(DATE)= '" + date + "'";
            }
        }

        EeTable.setItems(dontDeleteDB.getExpense(maker + " order by DATE DESC ;"));
        dontDeleteDB.closeDatabase();
    }

}

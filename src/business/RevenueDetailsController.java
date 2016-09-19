/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import static business.FrontController.user;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Anjan
 */
public class RevenueDetailsController implements Initializable {

    @FXML
    private VBox ReRevenueV;
    @FXML
    private TextField ReRemarks;
    @FXML
    private VBox ReBankCashV;
    @FXML
    private VBox ReAmountV;
    @FXML
    private TextField ReLp;
    private Button EditButt;
    @FXML
    private Button DeleteButt;

    private AutoCompleteTextField ReBankCash;
    private AutoCompleteTextField ReRevenue;
    private IntegerTextField ReAmount;
    @FXML
    private Button SaveButt;

    RevenueDB glob;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        DontDeleteDB dontDeleteDB = new DontDeleteDB();
        dontDeleteDB.connectDatabase();

        //connecting the database
        Database database = new Database();
        database.connect_database();

        ReBankCash = new AutoCompleteTextField();
        ReBankCash.setEntries(database.getSuggestionList("SELECT * FROM BANK_LIST;", "BANK_NAME"));
        ReBankCash.setMaxSize(150, 25);
        ReBankCash.setPromptText("BANK/CASH");

        ReRevenue = new AutoCompleteTextField();
        ReRevenue.setEntries(database.getSuggestionList("SELECT * FROM REVENUE;", "REVENUE"));
        ReRevenue.setMaxSize(150, 25);
        ReRevenue.setPromptText("REVENUE");

        ReRevenueV.getChildren().add(ReRevenue);
        ReBankCashV.getChildren().add(ReBankCash);
        ReAmount = new IntegerTextField();
        ReAmount.setPromptText("AMOUNT");
        ReAmountV.getChildren().add(ReAmount);

        if (ExpensesListController.sendReOb != null) {
            glob = ExpensesListController.sendReOb;
        }

        ReRevenue.setText(glob.getRevenue());
        ReRemarks.setText(glob.getRemarks());
        ReBankCash.setText(glob.getBankOrCash());
        ReAmount.setText(glob.getAmount2());
        ReLp.setText(glob.getLp());

    }

    private void ReDelete(RevenueDB ob) throws SQLException {

        DontDeleteDB dontDeleteDB = new DontDeleteDB();
        dontDeleteDB.connectDatabase();

        String query = "DELETE FROM REVENUE WHERE REVENUE_ID = '" + ob.getId() + "'";
        dontDeleteDB.delete(query);

        //updating banklist
        String sql = "SELECT BALANCE FROM BANK_LIST WHERE BANK_NAME= '" + ob.getBankOrCash() + "'";
        double bankBalance = dontDeleteDB.getADoubleDB(sql, "BALANCE");
        bankBalance -= ob.getAmount();
        query = "update BANK_LIST set BALANCE = ? where BANK_NAME = ?";
        dontDeleteDB.setADoubleDB(query, bankBalance, ob.getBankOrCash());
        //updating banklist ends here

        String command = "DELETE FROM BANK_TRANSACTION WHERE TYPE= '" + 6.0
                + "'AND ID_REVENUE= '" + ob.getId() + "';";
        dontDeleteDB.delete(command);

        dontDeleteDB.closeDatabase();

    }

    @FXML
    private void DeleteButtClick(ActionEvent event) throws SQLException {
        ReDelete(glob);
        
        
        Stage stage = (Stage) DeleteButt.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void SaveButtClick(ActionEvent event) throws SQLException {
        ReDelete(glob);

        DontDeleteDB dontDeleteDB = new DontDeleteDB();
        Timestamp dateTimeNow = glob.getDate();

        dontDeleteDB.connectDatabase();

        //insert into revenue table
        RevenueDB revenueDB = new RevenueDB();
        revenueDB.setAmount(IntegerTextField.doubleConvert(ReAmount.getText()));
        revenueDB.setBankOrCash(ReBankCash.getText());
        revenueDB.setDate(dateTimeNow);
        revenueDB.setRevenue(ReRevenue.getText());
        revenueDB.setLp(ReLp.getText());
        revenueDB.setRemarks(ReRemarks.getText());
        revenueDB.setUser(user);

        dontDeleteDB.insertRevenue(revenueDB);
        //insert into revenue table ends here

        //updating banklist
        String sql = "SELECT BALANCE FROM BANK_LIST WHERE BANK_NAME= '" + ReBankCash.getText() + "'";
        double bankBalance = dontDeleteDB.getADoubleDB(sql, "BALANCE");
        bankBalance += IntegerTextField.doubleConvert(ReAmount.getText());
        String query = "update BANK_LIST set BALANCE = ? where BANK_NAME = ?";
        dontDeleteDB.setADoubleDB(query, bankBalance, ReBankCash.getText());
        //updating banklist ends here

        // getting idRevenue foreign key
        String command = "SELECT REVENUE_ID FROM REVENUE ORDER BY REVENUE_ID DESC LIMIT 1";
        int idRevenue = dontDeleteDB.getAIntDB(command, "REVENUE_ID");

        //insering into bank transaction
        BankTransactionDB bankTransactionDB = new BankTransactionDB();
        bankTransactionDB.setAmount(IntegerTextField.doubleConvert(ReAmount.getText()));
        bankTransactionDB.setBalance(bankBalance);

        bankTransactionDB.setBankOrCash(ReBankCash.getText());
        bankTransactionDB.setDate(dateTimeNow);
        bankTransactionDB.setEntity(ReRevenue.getText());
        bankTransactionDB.setIdRevenue(idRevenue);
        bankTransactionDB.setLp(ReLp.getText());
        bankTransactionDB.setRemarks(ReRemarks.getText());
        bankTransactionDB.setType(6.0);
        bankTransactionDB.setUser(user);

        dontDeleteDB.insertBankTransaction(bankTransactionDB);

        ReRevenue.clear();
        ReRemarks.clear();
        ReAmount.clear();
        ReBankCash.clear();
        ReLp.clear();
        //inserting banktransaction ends here
        dontDeleteDB.closeDatabase();
        
        
        Stage stage = (Stage) DeleteButt.getScene().getWindow();
        stage.close();

    }

}

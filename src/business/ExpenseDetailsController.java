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
public class ExpenseDetailsController implements Initializable {

    @FXML
    private VBox EeExpenseV;
    @FXML
    private TextField EeRemarks;
    @FXML
    private VBox EeBankCashV;
    @FXML
    private VBox EeAmountV;
    @FXML
    private TextField EeLp;
    private Button EditButt;
    @FXML
    private Button DeleteButt;
    private AutoCompleteTextField EeBankCash;

    private AutoCompleteTextField EeExpense;

    private IntegerTextField EeAmount;
    @FXML
    private Button SaveButt;
    
    ExpenseDB glob;

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
        EeBankCash = new AutoCompleteTextField();
        EeBankCash.setEntries(database.getSuggestionList("SELECT * FROM BANK_LIST;", "BANK_NAME"));
        EeBankCash.setMaxSize(150, 25);
        EeBankCash.setPromptText("BANK/CASH");

        EeExpense = new AutoCompleteTextField();
        EeExpense.setEntries(database.getSuggestionList("SELECT * FROM EXPENSE;", "EXPENSE"));
        EeExpense.setMaxSize(150, 25);
        EeExpense.setPromptText("EXPENSES");

        EeExpenseV.getChildren().add(EeExpense);

        EeBankCashV.getChildren().add(EeBankCash);

        EeAmount = new IntegerTextField();
        EeAmount.setPromptText("AMOUNT");

        EeAmountV.getChildren().add(EeAmount);
        
        if(ExpensesListController.sendEeOb != null){
            glob= ExpensesListController.sendEeOb;
        }
        
        EeExpense.setText(glob.getExpense());
        EeRemarks.setText(glob.getRemarks());
        EeBankCash.setText(glob.getBankOrCash());
        EeAmount.setText(glob.getAmount2());
        EeLp.setText(glob.getLp());

    }
    
    private void EeDelete(ExpenseDB ob) throws SQLException {

        DontDeleteDB dontDeleteDB = new DontDeleteDB();
        dontDeleteDB.connectDatabase();

        String query = "DELETE FROM EXPENSE WHERE EXPENSE_ID = '" + ob.getId() + "'";
        dontDeleteDB.delete(query);

        //updating banklist
        String sql = "SELECT BALANCE FROM BANK_LIST WHERE BANK_NAME= '" + ob.getBankOrCash() + "'";
        double bankBalance = dontDeleteDB.getADoubleDB(sql, "BALANCE");
        bankBalance += ob.getAmount();
        query = "update BANK_LIST set BALANCE = ? where BANK_NAME = ?";
        dontDeleteDB.setADoubleDB(query, bankBalance, ob.getBankOrCash());
        //updating banklist ends here

        String command = "DELETE FROM BANK_TRANSACTION WHERE TYPE= '" + 5.0
                + "'AND ID_EXPENSE= '" + ob.getId() + "';";
        dontDeleteDB.delete(command);

        dontDeleteDB.closeDatabase();
    }


    @FXML
    private void SaveButtClick(ActionEvent event) throws SQLException {
        EeDelete(glob);
        
        Timestamp dateTimeNow = glob.getDate();

        DontDeleteDB dontDeleteDB = new DontDeleteDB();
        dontDeleteDB.connectDatabase();

        //insert into expenses table
        ExpenseDB expenseDB = new ExpenseDB();
        expenseDB.setAmount(IntegerTextField.doubleConvert(EeAmount.getText()));
        expenseDB.setBankOrCash(EeBankCash.getText());
        expenseDB.setDate(dateTimeNow);
        expenseDB.setExpense(EeExpense.getText());
        expenseDB.setLp(EeLp.getText());
        expenseDB.setRemarks(EeRemarks.getText());
        expenseDB.setUser(user);

        dontDeleteDB.insertExpense(expenseDB);
        //insert into expenses table ends here

        //updating banklist
        String sql = "SELECT BALANCE FROM BANK_LIST WHERE BANK_NAME= '" + EeBankCash.getText() + "'";
        double bankBalance = dontDeleteDB.getADoubleDB(sql, "BALANCE");
        bankBalance -= IntegerTextField.doubleConvert(EeAmount.getText());
        String query = "update BANK_LIST set BALANCE = ? where BANK_NAME = ?";
        dontDeleteDB.setADoubleDB(query, bankBalance, EeBankCash.getText());
        //updating banklist ends here

        // getting idExpense foreign key
        String command = "SELECT EXPENSE_ID FROM EXPENSE ORDER BY EXPENSE_ID DESC LIMIT 1";
        int idExpense = dontDeleteDB.getAIntDB(command, "EXPENSE_ID");

        //insering into bank transaction
        BankTransactionDB bankTransactionDB = new BankTransactionDB();
        bankTransactionDB.setAmount(-(IntegerTextField.doubleConvert(EeAmount.getText())));
        bankTransactionDB.setBankOrCash(EeBankCash.getText());
        bankTransactionDB.setDate(dateTimeNow);
        bankTransactionDB.setEntity(EeExpense.getText());
        bankTransactionDB.setIdExpense(idExpense);
        bankTransactionDB.setLp(EeLp.getText());
        bankTransactionDB.setRemarks(EeRemarks.getText());
        bankTransactionDB.setType(5.0);
        bankTransactionDB.setUser(user);

        dontDeleteDB.insertBankTransaction(bankTransactionDB);

        EeExpense.clear();
        EeRemarks.clear();
        EeAmount.clear();
        EeBankCash.clear();
        EeLp.clear();

        
        //inserting banktransaction ends here
        dontDeleteDB.closeDatabase();
        
        
        Stage stage = (Stage) DeleteButt.getScene().getWindow();
        stage.close();
    }

 
    @FXML
    private void DeleteButtClick(ActionEvent event) throws SQLException {
        EeDelete(glob);
        
        
        Stage stage = (Stage) DeleteButt.getScene().getWindow();
        stage.close();
    }

}

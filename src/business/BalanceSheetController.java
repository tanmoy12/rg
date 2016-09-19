/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author strings
 */
public class BalanceSheetController implements Initializable {

    @FXML
    private HBox clockDV;
    @FXML
    private HBox clockMV;
    @FXML
    private Button CloseForTheDate;
    @FXML
    private TableView<BalanceSheetDB> DebitTable;
    @FXML
    private TableColumn<BalanceSheetDB, String> DebitEntityCol;
    @FXML
    private TableColumn<BalanceSheetDB, String> DebitRemarksCol;
    @FXML
    private TableColumn<BalanceSheetDB, String> DebitAmountCol;
    @FXML
    private TableView<BalanceSheetDB> CreditTable;
    @FXML
    private TableColumn<BalanceSheetDB, String> CreditEntityCol;
    @FXML
    private TableColumn<BalanceSheetDB, String> CreditRemarksCol;
    @FXML
    private TableColumn<BalanceSheetDB, String> CreditAmountCol;
    @FXML
    private VBox exportV;
    @FXML
    private Button exportButt;
    @FXML
    private TextField exportText;
    @FXML
    private VBox exportV1;
    @FXML
    private TextField importText;
    @FXML
    private Button ImportButt;
    @FXML
    private ProgressIndicator prog;
    @FXML
    private Label importwaitLabel;
    @FXML
    private Label exportLabel;

    public static boolean reini = false;
    @FXML
    private TextField BsFromDate;
    @FXML
    private TextField BsToDate;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        clockDisplayer();

    }

    private void clockDisplayer() {

        final Label clockd = new Label();
        final Label clockm = new Label();
        final Label clockt = new Label();

        final DateFormat format = new SimpleDateFormat("dd");
        final DateFormat formatm = new SimpleDateFormat("MM");

        final Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), (ActionEvent event) -> {
            final Calendar cal = Calendar.getInstance();
            clockd.setText(format.format(cal.getTime()) + "/");
            clockd.setFont(Font.font(70));
            clockm.setText(formatm.format(cal.getTime()));
            clockm.setFont(Font.font(70));
        }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
        clockDV.getChildren().add(clockd);
        clockMV.getChildren().add(clockm);

    }

    @FXML
    private void CloseForTheDateButtClick(ActionEvent event) throws IOException {
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

        if (FrontController.newSet == true) {
            FrontController.newSet = false;
            java.sql.Timestamp timeStamp = new Timestamp(System.currentTimeMillis());
            String query = "Select USER_ID FROM USERS where USERNAME='stairsdate'";
            int user = dontDeleteDB.getAIntDB(query, "USER_ID");
            if (user == 0) {
                UsersDB ob = new UsersDB();
                ob.setUsername("stairsdate");
                ob.setDate(timeStamp);
                dontDeleteDB.insertUsers(ob);
            } else {
                query = "delete from USERS where USER_ID='" + user + "'";
                dontDeleteDB.delete(query);
                UsersDB ob = new UsersDB();
                ob.setUsername("stairsdate");
                ob.setDate(timeStamp);
                dontDeleteDB.insertUsers(ob);

            }
        }
        dontDeleteDB.closeDatabase();

    }

    @FXML
    private void exportdataclick(ActionEvent event) {
        SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd");//dd/MM/yyyy
        Date now = new Date();
        String strDate = sdfDate.format(now);
        String path = exportText.getText() + "RGtrading_" + strDate + ".sql";
        exportData(path);
    }

    public void exportData(String PATH) {
        String dbName = "stairsdatabase";
        String dbUser = "root";
        String dbPass = DontDeleteDB.Pass;
        String executeCmd = "";
        String path = PATH;
        executeCmd = "C:\\wamp\\bin\\mysql\\mysql5.6.17\\bin\\mysqldump -u " + dbUser + " -p" + dbPass
                + " --add-drop-database -B " + dbName + " -r " + path;

        try {
            Process runtimeProcess = Runtime.getRuntime().exec(executeCmd);

            int processComplete = runtimeProcess.waitFor();
            if (processComplete == 0) {
                System.out.println("Backup taken successfully");
                exportLabel.setText("SUCCESS");
            } else {
                System.out.println("Could not take mysql backup");
                exportLabel.setText("FAILED, export in Desktop");
            }
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public void restoreDB(String path) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/stairsdatabase?zeroDateTimeBehavior=convertToNull", "root", DontDeleteDB.Pass);
            //connection= DriverManager.getConnection("jdbc:mysql://host.evatixcloud.com:2083/cpsess7182539747/:3306/bslduorg_stairsdatabase","bslduorg_anik","anikbsl");
            ScriptRunner runner = new ScriptRunner(con, true, true);
            runner.runScript(new BufferedReader(new FileReader(path)));

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @FXML
    private void exportBrowseClick(ActionEvent event) {
        Stage stage = new Stage();

        DirectoryChooser chooser = new DirectoryChooser();
        chooser.setTitle("EXPORT TO");
        File selectedDirectory = chooser.showDialog(stage);

        if (selectedDirectory != null) {
            exportText.setText(selectedDirectory.getAbsolutePath() + "\\");
        }
    }

    @FXML
    private void importBrowseClick(ActionEvent event) {
        Stage stage = new Stage();

        FileChooser chooser = new FileChooser();
        chooser.setTitle("IMPORT FROM");
        File selectedFile = chooser.showOpenDialog(stage);

        if (selectedFile != null) {
            importText.setText(selectedFile.getAbsolutePath());
        }
    }

    @FXML
    private void importButtclick(ActionEvent event) {

        prog.setProgress(0);
        restoreDB(importText.getText());
        reini = true;
        Stage stage = (Stage) CloseForTheDate.getScene().getWindow();
        stage.close();
        prog.setProgress(100);

    }

    @FXML
    private void ViewBalanceSheet(ActionEvent event) throws ParseException {
        DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        Date fdate = format.parse(BsFromDate.getText());
        java.sql.Date fromdate = new java.sql.Date(fdate.getTime());
        fdate = format.parse(BsToDate.getText());
        java.sql.Date todate = new java.sql.Date(fdate.getTime());
        
        DontDeleteDB dontDeleteDB= new DontDeleteDB();
        dontDeleteDB.connectDatabase();
        
        String sql= "SELECT sum(AMOUNT) as total from BANK_TRANSACTION" + "WHERE date(DATE)>= '" + fromdate 
                + "' AND date(DATE)<= '" + todate + "'";
        int totalinperiod= dontDeleteDB.getAIntDB(sql, "total");
        System.out.println(totalinperiod);
        
        //SELECT sum(AMOUNT) as total from BANK_TRANSACTION br WHERE br.DATE>'01/09/2016'
        
        dontDeleteDB.closeDatabase();

    }

}

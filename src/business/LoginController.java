/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Timestamp;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Tanmoy
 */
public class LoginController implements Initializable {

    @FXML
    private Button DoneButt;
    @FXML
    private TextField LgUsername;

    @FXML
    private PasswordField LgPassword;

    public static boolean level = false;
    @FXML
    private MenuButton StAccountTypeDrop;

    java.sql.Timestamp timeStamp = new Timestamp(System.currentTimeMillis());
    java.sql.Date date = new java.sql.Date(timeStamp.getTime());

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO\
        level = false;
    }

   

    @FXML
    private void DoneButtClick(ActionEvent event) throws IOException, InterruptedException {
        
        DontDeleteDB dontDeleteDB = new DontDeleteDB();

        dontDeleteDB.connectDatabase();
        double lgType;

        String sqllgID = "SELECT TYPE FROM USERS WHERE USERNAME= '"
                + LgUsername.getText()
                + "' AND PASSWORD= '" + LgPassword.getText() + "'";
        lgType = dontDeleteDB.getADoubleDB(sqllgID, "TYPE");

        if (lgType == 1 && "ADMIN".equals(StAccountTypeDrop.getText())) {
            level = true;
            if (checksum()) {

                Stage stage = (Stage) DoneButt.getScene().getWindow();
                stage.close();
                Parent p = FXMLLoader.load(getClass().getResource("Front.fxml"));
                Stage front = new Stage();
                Scene scene = new Scene(p);

                front.setScene(scene);
                front.show();
            } else {
                FrontController.error = "THIS DATE IS CLOSED";
                Stage stage = (Stage) DoneButt.getScene().getWindow();
                stage.close();
                Parent p = FXMLLoader.load(getClass().getResource("Error.fxml"));
                Stage front = new Stage();
                Scene scene = new Scene(p);

                front.setScene(scene);
                front.show();
            }
        } else if (lgType == 2 && "EMPLOYEE".equals(StAccountTypeDrop.getText())) {
            level = false;
            if (checksum()) {
                Stage stage = (Stage) DoneButt.getScene().getWindow();
                stage.close();
                Parent p = FXMLLoader.load(getClass().getResource("Front.fxml"));
                Stage front = new Stage();
                Scene scene = new Scene(p);

                front.setScene(scene);
                front.show();
            } else {

                FrontController.error = "THIS DATE IS CLOSED";
                Stage stage = (Stage) DoneButt.getScene().getWindow();
                stage.close();
                Parent p = FXMLLoader.load(getClass().getResource("Error.fxml"));
                Stage front = new Stage();
                Scene scene = new Scene(p);

                front.setScene(scene);
                front.show();
            }
        } else {
            FrontController.error = "USERNAME AND PASSWORD NOT RECOGNIZED";
            Parent p = FXMLLoader.load(getClass().getResource("Error.fxml"));
            Stage front = new Stage();
            Scene scene = new Scene(p);

            front.setScene(scene);
            front.show();
        }
    }

    private boolean checksum() throws IOException {
        DontDeleteDB dontDeleteDB = new DontDeleteDB();
        dontDeleteDB.connectDatabase();

        String Query = "SELECT USER_ID from USERS where USERNAME='stairsdate' ";
        int b = dontDeleteDB.getAIntDB(Query, "USER_ID");

        Query = "SELECT USER_ID from USERS where USERNAME='stairsdate' "
                + "AND date(DATE)<'" + date + "'";
        int a = dontDeleteDB.getAIntDB(Query, "USER_ID");

        dontDeleteDB.closeDatabase();
        if (a == 0) {

            return b == 0;

        } else {

            return true;
        }

    }

    @FXML
    private void StAdminClick(ActionEvent event) throws IOException, InterruptedException {
        StAccountTypeDrop.setText("ADMIN");

    }

    @FXML
    private void StEmployeeClick(ActionEvent event) {
        StAccountTypeDrop.setText("EMPLOYEE");
    }

}

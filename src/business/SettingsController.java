/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author strings
 */
public class SettingsController implements Initializable {

    @FXML
    private Button StAddAccount;
    @FXML
    private TableView<UsersDB> StAccounts;
    @FXML
    private TableColumn<UsersDB, String> StUsernameCol;
    @FXML
    private TableColumn<UsersDB, String> StTypeCol;

    public static UsersDB sendUsOb = null;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        DontDeleteDB dontDeleteDB = new DontDeleteDB();
        dontDeleteDB.connectDatabase();

        StUsernameCol.setCellValueFactory(new PropertyValueFactory<>("username"));
        StTypeCol.setCellValueFactory(new PropertyValueFactory<>("type2"));
        ObservableList<UsersDB> UsData
                = (dontDeleteDB.getUsers("SELECT * FROM USERS where USERNAME<>'stairsdate';"));

        StAccounts.setItems(UsData);

        dontDeleteDB.closeDatabase();
    }

    @FXML
    private void StAddAccountClick(ActionEvent event) throws IOException {
        Parent p = FXMLLoader.load(getClass().getResource("Users.fxml"));
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
        StAccounts.setItems(dontDeleteDB.getUsers("SELECT * FROM USERS where USERNAME<>'stairsdate';"));
        dontDeleteDB.closeDatabase();

    }

    @FXML
    private void ViewButtClick(ActionEvent event) throws IOException {
        UsersDB UsOb = StAccounts.getSelectionModel().getSelectedItem();
        if (UsOb == null) {
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
                sendUsOb = UsOb;
                //System.out.println(SoOrderNo);
                p = FXMLLoader.load(getClass().getResource("Users.fxml"));
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
                StAccounts.setItems(dontDeleteDB.getUsers("SELECT * FROM USERS where USERNAME<>'stairsdate';"));
                dontDeleteDB.closeDatabase();
                sendUsOb = null;
            }
        }
    }

}

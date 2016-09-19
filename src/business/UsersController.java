package business;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author strings
 */
public class UsersController implements Initializable {

    @FXML
    private TextField StUsername;
    @FXML
    private TextField StPassword;
    @FXML
    private MenuButton StAccountTypeDrop;
    @FXML
    private Button UsDoneButt;
    @FXML
    private Button UsDeleteButt;

    private double type = 0;
    UsersDB glob = null;

    java.sql.Timestamp timeStamp = new Timestamp(System.currentTimeMillis());
    java.sql.Date date = new java.sql.Date(timeStamp.getTime());

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        if (SettingsController.sendUsOb != null) {
            UsDoneButt.setText("SAVE");
            glob = SettingsController.sendUsOb;
            StUsername.setText(glob.getUsername());
            StPassword.setText(glob.getPassword());
            if (glob.getType() == 1) {
                StAccountTypeDrop.setText("ADMIN");
            } else {
                StAccountTypeDrop.setText("EMPLOYEE");
            }
            UsDeleteButt.setDisable(false);
        }
    }

    @FXML
    private void StAdminClick(ActionEvent event) {
        StAccountTypeDrop.setText("ADMIN");
        type = 1;
    }

    @FXML
    private void StEmployeeClick(ActionEvent event) {
        StAccountTypeDrop.setText("EMPLOYEE");
        type = 2;
    }

    @FXML
    private void UsDoneButtClick(ActionEvent event) throws IOException {
        insertUser();

    }

    private void insertUser() throws IOException {
        if (StUsername.getText() != null && !"".equals(StUsername.getText())
                && StPassword.getText() != null && !"".equals(StPassword.getText())
                && type != 0) {

            UsersDB usersDB = new UsersDB();
            usersDB.setDate(timeStamp);
            usersDB.setUsername(StUsername.getText());
            usersDB.setPassword(StPassword.getText());
            usersDB.setType(type);

            DontDeleteDB dontDeleteDB = new DontDeleteDB();
            dontDeleteDB.connectDatabase();

            dontDeleteDB.insertUsers(usersDB);
            if ("SAVE".equals(UsDoneButt.getText())) {

                String sql = "Delete from USERS WHERE USER_ID = '" + glob.getId() + "';";
                dontDeleteDB.delete(sql);

            }
            dontDeleteDB.closeDatabase();
            Stage stage = (Stage) UsDoneButt.getScene().getWindow();
            stage.close();
        } else {
            FrontController.error = "DETAILS NOT COMPLETED";
            Parent p = FXMLLoader.load(getClass().getResource("Error.fxml"));

            Stage stage = new Stage();
            Scene scene = new Scene(p);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.showAndWait();
        }
    }

    @FXML
    private void UsDeleteButtClick(ActionEvent event) {
        DontDeleteDB dontDeleteDB = new DontDeleteDB();
        dontDeleteDB.connectDatabase();
        String sql = "Delete from USERS WHERE USER_ID = '" + glob.getId() + "';";
        dontDeleteDB.delete(sql);
          Stage stage=(Stage) UsDeleteButt.getScene().getWindow();
        stage.close();
    }

}

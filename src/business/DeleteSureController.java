/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Nazrul
 */
public class DeleteSureController implements Initializable {

    @FXML
    private Button DsCancelButt;
    @FXML
    private Button DsYesButt;
    @FXML
    private Label newItem;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void DsCancelClick(ActionEvent event) {
        FrontController.newSet = false;
        Stage stage = (Stage) DsCancelButt.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void DsYesClick(ActionEvent event) {
        FrontController.newSet=true;
        Stage stage = (Stage) DsYesButt.getScene().getWindow();
        stage.close();
    }
    
}

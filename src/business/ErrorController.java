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
 * @author shadman264
 */
public class ErrorController implements Initializable {
    @FXML
    private Button EOkButt;
    @FXML
    private Label ErrorLabel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ErrorLabel.setText(FrontController.error);
    }    

    @FXML
    private void ErrorOkClick(ActionEvent event) {
        Stage stage=(Stage) EOkButt.getScene().getWindow();
        stage.close();
    }
    
}

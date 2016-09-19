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
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author strings
 */
public class AdminPassController implements Initializable {

    @FXML
    private TextField Pass;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void PassClick(ActionEvent event) {
        DontDeleteDB dontDeleteDB=new DontDeleteDB();
        dontDeleteDB.connectDatabase();
        
        String query ="Select USERNAME from USERS WHERE PASSWORD='" + Pass.getText() 
                +"' AND TYPE= 1;"; 
        String user= dontDeleteDB.getAStringDB(query, "USERNAME");
       
        if(user==null) LoginController.level=false;
        else LoginController.level=true;
        Stage stage=(Stage) Pass.getScene().getWindow();
        stage.close();
    }
    
}

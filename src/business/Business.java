/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Tanmoy
 */
public class Business extends Application {
    

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
        
        
        Scene scene = new Scene(root);
 
        stage.setScene(scene);

        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        DontDeleteDB dontDeleteDB = new DontDeleteDB();

        dontDeleteDB.createDatabase();
        dontDeleteDB.connectDatabase();
        dontDeleteDB.createBankList();
        dontDeleteDB.createBankTransaction();
        dontDeleteDB.createCustomerList();
        dontDeleteDB.createCustomerTransaction();
        dontDeleteDB.createPendingDeliveries();
        dontDeleteDB.createPurchaseDelivery();
        dontDeleteDB.createPurchaseOrder();
        dontDeleteDB.createSaleDelivery();
        dontDeleteDB.createSaleOrder();
        dontDeleteDB.createSupplierList();
        dontDeleteDB.createSupplierTransaction();
        dontDeleteDB.createTotalStock();
        dontDeleteDB.createUpcomingStock();
        dontDeleteDB.createBankDepoWith();
        dontDeleteDB.createExpense();
        dontDeleteDB.createRevenue();
        dontDeleteDB.createUsers();
        dontDeleteDB.closeDatabase();
        
        
        launch(args);
    }

}


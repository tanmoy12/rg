/*
   * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.Date;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author User
 */
public class DontDeleteDB {

    /**
     * @param args the command line arguments
     */
    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;
    private ResultSetMetaData metaData;
    private PreparedStatement preparedStatement;
    public static String Pass= "tomtom";

    public DontDeleteDB() {

    }

    public void createDatabase() {
        String sql = "CREATE DATABASE IF NOT EXISTS stairsdatabase ; ";

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/stairsdatabase?zeroDateTimeBehavior=convertToNull", "root", Pass);
            //connection= DriverManager.getConnection("jdbc:mysql://host.evatixcloud.com:2083/cpsess7182539747/:3306/bslduorg_stairsdatabase","bslduorg_anik","anikbsl");
            statement = connection.createStatement();
            statement.executeUpdate(sql);

        } catch (Exception e) {
            e.printStackTrace();
        }
        closeDatabase();
    }

    public void createDatabaseOnline() {
        String sql = "CREATE DATABASE IF NOT EXISTS stairsdatabaseonline ; ";

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/stairsdatabase?zeroDateTimeBehavior=convertToNull", "root", Pass);
            //connection= DriverManager.getConnection("jdbc:mysql://host.evatixcloud.com:2083/cpsess7182539747/:3306/bslduorg_stairsdatabase","bslduorg_anik","anikbsl");
            statement = connection.createStatement();
            statement.executeUpdate(sql);

        } catch (ClassNotFoundException | SQLException e) {
        }
        closeDatabase();
    }

    // #1.01
    public void createBankList() {
        String sql = "CREATE TABLE IF NOT EXISTS BANK_LIST "
                + "(BANK_LIST_ID INT PRIMARY KEY     NOT NULL AUTO_INCREMENT," //101
                + " DATE           DATETIME   , "
                + " USER           VARCHAR(50), "
                + " BANK_NAME           VARCHAR(50) , "
                + " ACCOUNT_NUMBER           VARCHAR(50) , "
                + " ACCOUNT_NAME              VARCHAR(50) , "
                + " BRANCH           VARCHAR(50) , "
                + " TYPE_OF_ACCOUNT           VARCHAR(150) , "
                + " BALANCE          DOUBLE , "
                + " ADDRESS          VARCHAR(100) , "
                + " CONTACTS          VARCHAR(50) ) ";
        update(sql);

    }

    // #1.02 
    public void insertBankList(BankListDB ob) {
        String sql = "INSERT INTO BANK_LIST ("
                + "BANK_LIST_ID, "
                + "DATE, "
                + "USER, "
                + "BANK_NAME, "
                + "ACCOUNT_NUMBER, "
                + "ACCOUNT_NAME, "
                + "BRANCH, "
                + "TYPE_OF_ACCOUNT, "
                + "BALANCE, "
                + "ADDRESS, "
                + "CONTACTS)"
                + " VALUES(" + ob.getId()+ ",'" + ob.getDate() + "','" + ob.getUser() + "','" + ob.getBankName() + "','" + ob.getAccountNumber() + "','"
                + ob.getAccountName() + "','" + ob.getBranch() + "','" + ob.getTypeOfAccount() + "'," + ob.getBalance() + ",'" + ob.getAddress() + "','" + ob.getContacts() + "');";
        update(sql);
    }

    // #1.03
    public ObservableList<BankListDB> getBankList(String sql) {                //103
        ObservableList<BankListDB> row = FXCollections.observableArrayList();
        try {
            resultSet = statement.executeQuery(sql);
            metaData = resultSet.getMetaData();

            while (resultSet.next()) {
                BankListDB ob = new BankListDB(
                        resultSet.getInt("BANK_LIST_ID"),
                        resultSet.getTimestamp("DATE"),
                        resultSet.getString("USER"),
                        resultSet.getString("BANK_NAME"),
                        resultSet.getString("ACCOUNT_NUMBER"),
                        resultSet.getString("ACCOUNT_NAME"),
                        resultSet.getString("BRANCH"),
                        resultSet.getString("TYPE_OF_ACCOUNT"),
                        resultSet.getDouble("BALANCE"),
                        resultSet.getString("ADDRESS"),
                        resultSet.getString("CONTACTS")
                );
                row.addAll(ob);
            }

        } catch (Exception e) {

        }
        return row;

    }

    // #2.01
    public void createPurchaseOrder() {
        String sql = "CREATE TABLE  IF NOT EXISTS PURCHASE_ORDER "
                + "(PURCHASE_ORDER_ID INT PRIMARY KEY     NOT NULL AUTO_INCREMENT," //201
                + " DATE           DATETIME   , "
                + " SUPPLIER           VARCHAR(50), "
                + " INSTITUTE           VARCHAR(50) , "
                + " ORDER_NO           VARCHAR(50) , "
                + " STORAGE           VARCHAR(30) , "
                + " PRODUCT           VARCHAR(50) , "
                + " QUANTITY           DOUBLE , "
                + " UNIT          DOUBLE , "
                + " UNIT_TYPE          VARCHAR(20) , "
                + " RATE_KG          DOUBLE , "
                + " RATE_MON          DOUBLE , "
                + " PURCHASE_ORDER_SERIAL          INT , "
                + " PRICE          DOUBLE , "
                + " LP          VARCHAR(50)  , "
                + " USER    VARCHAR(30) ) ";
        update(sql);
    }

    // #2.02
    public void insertPurchaseOrder(PurchaseOrderDB ob) {   //202

        String sql = "INSERT INTO PURCHASE_ORDER (DATE, SUPPLIER, INSTITUTE, ORDER_NO, STORAGE , PRODUCT , QUANTITY ,"
                + " UNIT, UNIT_TYPE , RATE_KG , RATE_MON , PRICE , LP ,PURCHASE_ORDER_SERIAL, USER )"
                + " VALUES('" + ob.getDate() + "','" + ob.getSupplier() + "','" + ob.getInstitute() + "','" + ob.getOrderNo() + "','" + ob.getStorage() + "','"
                + ob.getProduct() + "'," + ob.getQuantity() + "," + ob.getUnit() + ",'" + ob.getUnitType()
                + "'," + ob.getRateKg() + "," + ob.getRateMon() + "," + ob.getPrice()
                + ",'" + ob.getLp() + "' " + ",'" + ob.getSerial() + "' "
                + ",'" + ob.getUser() + "');";
        update(sql);
    }

    // #2.03
    public ObservableList<PurchaseOrderDB> getPurchaseOrder(String sql) {                //203
        ObservableList<PurchaseOrderDB> row = FXCollections.observableArrayList();
        try {
            resultSet = statement.executeQuery(sql);
            metaData = resultSet.getMetaData();

            while (resultSet.next()) {
                PurchaseOrderDB ob = new PurchaseOrderDB(
                        resultSet.getInt("PURCHASE_ORDER_ID"),
                        resultSet.getTimestamp("DATE"),
                        resultSet.getString("SUPPLIER"),
                        resultSet.getString("INSTITUTE"),
                        resultSet.getString("ORDER_NO"),
                        resultSet.getString("STORAGE"),
                        resultSet.getString("PRODUCT"),
                        resultSet.getDouble("QUANTITY"),
                        resultSet.getDouble("UNIT"),
                        resultSet.getString("UNIT_TYPE"),
                        resultSet.getDouble("RATE_KG"),
                        resultSet.getDouble("RATE_MON"),
                        resultSet.getDouble("PRICE"),
                        resultSet.getString("LP"),
                        resultSet.getString("USER"),
                        resultSet.getInt("PURCHASE_ORDER_SERIAL")
                );

                row.addAll(ob);

            }
        } catch (Exception e) {

        }

        return row;

    }

    // #3.01
    public void createPurchaseDelivery() {
        String sql = "CREATE TABLE  IF NOT EXISTS  PURCHASE_DELIVERY "
                + "(PURCHASE_DELIVERY_ID INT PRIMARY KEY     NOT NULL AUTO_INCREMENT," //301
                + " DATE           DATETIME   , "
                + " SUPPLIER           VARCHAR(50), "
                + " INSTITUTE           VARCHAR(50) , "
                + " ORDER_NO           VARCHAR(50) , "
                + " DELIVERY_NO           VARCHAR(50) , "
                + " STORAGE           VARCHAR(50) , "
                + " PRODUCT           VARCHAR(50) , "
                + " QUANTITY           DOUBLE , "
                + " UNIT          DOUBLE , "
                + " UNIT_TYPE          VARCHAR(20) , "
                + " RATE_KG          DOUBLE , "
                + " RATE_MON          DOUBLE , "
                + " PRICE          DOUBLE , "
                + " LP          VARCHAR(50) , "
                + " USER VARCHAR(30) ) ";
        update(sql);

    }

    // #3.02
    public void insertPurchaseDelivery(PurchaseDeliveryDB ob) {           //302

        String sql = "INSERT INTO PURCHASE_DELIVERY (DATE , SUPPLIER, INSTITUTE, ORDER_NO, DELIVERY_NO , STORAGE, PRODUCT , QUANTITY ,"
                + " UNIT, UNIT_TYPE , RATE_KG , RATE_MON , PRICE , LP , USER )"
                + " VALUES('" + ob.getDate() + "','" + ob.getSupplier() + "','" + ob.getInstitute() + "','" + ob.getOrderNo() + "','" + ob.getDeliveryNo() + "','"
                + ob.getStorage() + "','" + ob.getProduct() + "'," + ob.getQuantity() + "," + ob.getUnit() + ",'" + ob.getUnitType()
                + "'," + ob.getRateKg() + "," + ob.getRateMon() + "," + ob.getPrice() + ",'" + ob.getLp() + "' ,'" + ob.getUser() + "');";
        update(sql);
    }

    // #3.03
    public ObservableList<PurchaseDeliveryDB> getPurchaseDelivery(String sql) {                //303
        ObservableList<PurchaseDeliveryDB> row = FXCollections.observableArrayList();
        try {
            resultSet = statement.executeQuery(sql);
            metaData = resultSet.getMetaData();

            while (resultSet.next()) {
                PurchaseDeliveryDB ob = new PurchaseDeliveryDB(
                        resultSet.getInt("PURCHASE_DELIVERY_ID"),
                        resultSet.getTimestamp("DATE"),
                        resultSet.getString("USER"),
                        resultSet.getString("SUPPLIER"),
                        resultSet.getString("INSTITUTE"),
                        resultSet.getString("ORDER_NO"),
                        resultSet.getString("DELIVERY_NO"),
                        resultSet.getString("PRODUCT"),
                        resultSet.getString("STORAGE"),
                        resultSet.getDouble("QUANTITY"),
                        resultSet.getDouble("UNIT"),
                        resultSet.getString("UNIT_TYPE"),
                        resultSet.getDouble("RATE_KG"),
                        resultSet.getDouble("RATE_MON"),
                        resultSet.getDouble("PRICE"),
                        resultSet.getString("LP")
                );
                row.addAll(ob);

            }
        } catch (Exception e) {

        }

        return row;

    }

    //login system
    public void createUsers() {
        String sql = "CREATE TABLE  IF NOT EXISTS  USERS "
                + "(USER_ID INT PRIMARY KEY     NOT NULL AUTO_INCREMENT," //301
                + " DATE           DATETIME   , "
                + " USERNAME           VARCHAR(50), "
                + " PASSWORD           VARCHAR(50) , "
                + " TYPE          DOUBLE , "
                + " IDENTIFIED_BY  VARCHAR(50) ) ";
        update(sql);

    }

    // #3.02
    public void insertUsers(UsersDB ob) {           //302

        String sql = "INSERT INTO USERS (DATE , USERNAME, PASSWORD, TYPE, IDENTIFIED_BY )"
                + " VALUES('" + ob.getDate() + "','" + ob.getUsername() + "','"
                + ob.getPassword() + "','" + ob.getType() + "','"
                + ob.getIdentifiedby() + "');";
        update(sql);
    }

    // #3.03
    public ObservableList<UsersDB> getUsers(String sql) {                //303
        ObservableList<UsersDB> row = FXCollections.observableArrayList();
        try {
            resultSet = statement.executeQuery(sql);
            metaData = resultSet.getMetaData();

            while (resultSet.next()) {
                UsersDB ob = new UsersDB();
                ob.setDate(resultSet.getTimestamp("DATE"));
                ob.setId(resultSet.getInt("USER_ID"));
                ob.setIdentifiedby(resultSet.getString("IDENTIFIED_BY"));
                ob.setPassword(resultSet.getString("PASSWORD"));
                ob.setUsername(resultSet.getString("USERNAME"));
                ob.setType(resultSet.getDouble("TYPE"));
                if (resultSet.getDouble("TYPE") == 1) {
                    ob.setType2("ADMIN");
                } else if (resultSet.getDouble("TYPE") == 2) {
                    ob.setType2("EMPLOYEE");
                }
                row.add(ob);

            }
        } catch (Exception e) {
        }

        return row;

    }

    // #4.01
    public void createSaleOrder() {
        String sql = "CREATE TABLE  IF NOT EXISTS  SALE_ORDER "
                + "(SALE_ORDER_ID INT PRIMARY KEY     NOT NULL AUTO_INCREMENT," //401
                + " DATE           DATETIME  , "
                + " CUSTOMER           VARCHAR(50), "
                + " INSTITUTE           VARCHAR(50) , "
                + " INVOICE_NO           VARCHAR(50) , "
                + " MARKA           VARCHAR(50) , "
                + " PRODUCT           VARCHAR(50) , "
                + " QUANTITY           DOUBLE , "
                + " UNIT          DOUBLE , "
                + " UNIT_TYPE          VARCHAR(20) , "
                + " RATE_KG         DOUBLE , "
                + " RATE_MON          DOUBLE , "
                + " PRICE          DOUBLE , "
                + " LP          VARCHAR(50) , "
                + " SERIAL          INT , "
                + " USER VARCHAR(30) ) ";
        update(sql);

    }

    // #4.02
    public void insertSaleOrder(SaleOrderDB ob) {   //402
        String sql = "INSERT INTO SALE_ORDER (DATE ,  CUSTOMER, INSTITUTE, INVOICE_NO, MARKA, PRODUCT , QUANTITY ,"
                + " UNIT, UNIT_TYPE , RATE_KG , RATE_MON , PRICE , LP ,SERIAL, USER )"
                + " VALUES('" + ob.getDate() + "','" + ob.getCustomer() + "','" + ob.getInstitute() + "','" + ob.getInvoiceNo() + "','"
                + ob.getMarka() + "','" + ob.getProduct() + "'," + ob.getQuantity() + "," + ob.getUnit() + ",'" + ob.getUnitType()
                + "'," + ob.getRateKg() + "," + ob.getRateMon() + "," + ob.getPrice() + ",'"
                + ob.getLp() + "','" + ob.getSerial() + "','" + ob.getUser() + "');";
        update(sql);
    }

    // #4.03
    public ObservableList<SaleOrderDB> getSaleOrder(String sql) {                //403
        ObservableList<SaleOrderDB> row = FXCollections.observableArrayList();
        try {
            resultSet = statement.executeQuery(sql);
            metaData = resultSet.getMetaData();

            while (resultSet.next()) {
                SaleOrderDB ob = new SaleOrderDB(
                        resultSet.getInt("SALE_ORDER_ID"),
                        resultSet.getTimestamp("DATE"),
                        resultSet.getString("USER"),
                        resultSet.getString("CUSTOMER"),
                        resultSet.getString("INSTITUTE"),
                        resultSet.getString("INVOICE_NO"),
                        resultSet.getString("PRODUCT"),
                        resultSet.getString("MARKA"),
                        resultSet.getDouble("QUANTITY"),
                        resultSet.getDouble("UNIT"),
                        resultSet.getString("UNIT_TYPE"),
                        resultSet.getDouble("RATE_KG"),
                        resultSet.getDouble("RATE_MON"),
                        resultSet.getDouble("PRICE"),
                        resultSet.getString("LP"),
                        resultSet.getInt("SERIAL")
                );
                row.addAll(ob);

            }
        } catch (Exception e) {

        }

        return row;

    }

    // #5.01
    public void createSaleDelivery() {
        String sql = "CREATE TABLE  IF NOT EXISTS  SALE_DELIVERY "
                + "(SALE_DELIVERY_ID INT PRIMARY KEY     NOT NULL AUTO_INCREMENT," //501
                + " DATE           DATETIME   , "
                + " DELIVERY_SERIAL DOUBLE   , "
                + " CUSTOMER           VARCHAR(50), "
                + " INSTITUTE           VARCHAR(50) , "
                + " INVOICE_NO           VARCHAR(50) , "
                + " TRUCK_NO           VARCHAR(50)   , "
                + " TRUCK_MOB_NO           VARCHAR(50)   , "
                + " BILL_NO           VARCHAR(50)   , "
                + " MARKA           VARCHAR(50) , "
                + " STORAGE           VARCHAR(50) , "
                + " PRODUCT           VARCHAR(50) , "
                + " QUANTITY           DOUBLE , "
                + " UNIT          DOUBLE , "
                + " UNIT_TYPE          VARCHAR(50) , "
                + " RATE_KG          DOUBLE , "
                + " RATE_MON          DOUBLE , "
                + " COMMISSION          DOUBLE  , "
                + " FREIGHT           DOUBLE   , "
                + " ADVANCE           DOUBLE   , "
                + " PRICE          DOUBLE , "
                + " LP          VARCHAR(50) , "
                + " USER VARCHAR(30)) ";
        update(sql);

    }

    public void insertSaleDelivery(SaleDeliveryDB ob) {                     //502

        String sql = "INSERT INTO SALE_DELIVERY "
                + "(DATE ,DELIVERY_SERIAL, CUSTOMER, INSTITUTE, INVOICE_NO,"
                + " TRUCK_NO , TRUCK_MOB_NO , BILL_NO ,MARKA,STORAGE ,  PRODUCT , QUANTITY ,"
                + " UNIT, UNIT_TYPE , RATE_KG , RATE_MON , COMMISSION , FREIGHT , PRICE , LP , USER, ADVANCE )"
                + " VALUES('" + ob.getDate() + "'," + ob.getDeliverySerial() + ",'" + ob.getCustomer() + "','" + ob.getInstitute() + "','"
                + ob.getInvoiceNo() + "','" + ob.getTruckNo() + "','" + ob.getTruckMobileNo() + "','" + ob.getBillNo() + "','" + ob.getMarka() + "','" + ob.getStorage()
                + "','" + ob.getProduct() + "'," + ob.getQuantity() + "," + ob.getUnit() + ",'" + ob.getUnitType() + "'," + ob.getRateKg() + ","
                + ob.getRateMon() + "," + ob.getCommission() + "," + ob.getFreight() + "," + ob.getPrice() + ",'" + ob.getLp() + "','" + ob.getUser() + "'," + ob.getAdvance() + ");";

        update(sql);
    }

    public ObservableList<SaleDeliveryDB> getSaleDelivery(String sql) {                //503
        ObservableList<SaleDeliveryDB> row = FXCollections.observableArrayList();
        try {
            resultSet = statement.executeQuery(sql);
            metaData = resultSet.getMetaData();

            while (resultSet.next()) {
                SaleDeliveryDB ob = new SaleDeliveryDB(
                        resultSet.getInt("SALE_DELIVERY_ID"),
                        resultSet.getTimestamp("DATE"),
                        resultSet.getString("USER"),
                        resultSet.getDouble("DELIVERY_SERIAL"),
                        resultSet.getString("CUSTOMER"),
                        resultSet.getString("INSTITUTE"),
                        resultSet.getString("INVOICE_NO"),
                        resultSet.getString("BILL_NO"),
                        resultSet.getString("TRUCK_NO"),
                        resultSet.getString("TRUCK_MOB_NO"),
                        resultSet.getString("PRODUCT"),
                        resultSet.getString("MARKA"),
                        resultSet.getString("STORAGE"),
                        resultSet.getDouble("QUANTITY"),
                        resultSet.getDouble("UNIT"),
                        resultSet.getString("UNIT_TYPE"),
                        resultSet.getDouble("RATE_KG"),
                        resultSet.getDouble("RATE_MON"),
                        resultSet.getDouble("FREIGHT"),
                        resultSet.getDouble("ADVANCE"),
                        resultSet.getDouble("COMMISSION"),
                        resultSet.getDouble("PRICE"),
                        resultSet.getString("LP")
                );
                row.addAll(ob);

            }
        } catch (Exception e) {

        }

        return row;

    }


    // #6.01
    public void createSupplierList() {
        String sql = "CREATE TABLE  IF NOT EXISTS  SUPPLIER_LIST "
                + "(SUPPLIER_LIST_ID INT PRIMARY KEY     NOT NULL AUTO_INCREMENT," //601
                + " DATE DATETIME   , "
                + " USER VARCHAR(50)   , "
                + " SUPPLIER VARCHAR(50)   , "
                + " INSTITUTE VARCHAR(50), "
                + " REMARK VARCHAR(500) , "
                + " PAYMENT DOUBLE , "
                + " TOTAL_ORDER DOUBLE , "
                + " TOTAL_DELIVERY DOUBLE , "
                + " BALANCE_ORDER DOUBLE , "
                + " BALANCE_DELIVERY DOUBLE , "
                + " ADDRESS VARCHAR(150) , "
                + " CONTACTS VARCHAR(20) , "
                + " SUPPLIER_SERIAL DOUBLE ) ";
        update(sql);

    }

    public void insertSupplierList(SupplierListDB ob) {                        //602        
        String sql = "INSERT INTO SUPPLIER_LIST (SUPPLIER_LIST_ID, DATE , USER, SUPPLIER , INSTITUTE, REMARK ,PAYMENT, TOTAL_ORDER, TOTAL_DELIVERY, BALANCE_ORDER, BALANCE_DELIVERY, ADDRESS , CONTACTS , SUPPLIER_SERIAL )"
                + " VALUES(" + ob.getId()+ ",'" + ob.getDate() + "','" + ob.getUser() + "','" + ob.getSupplier() + "','" + ob.getInstitute() + "','" + ob.getRemark() + "',"
                + ob.getPayment() + "," + ob.getTotalOrder() + "," + ob.getTotalDelivery() + "," + ob.getBalanceOrder()
                + "," + ob.getBalanceDelivery() + ",'" + ob.getAddress() + "','" + ob.getContacts() + "'," + ob.getSupplierSerial() + ");";
        System.out.println(sql);
        update(sql);
    }

    public ObservableList<SupplierListDB> getSupplierList(String sql) {                //603
        ObservableList<SupplierListDB> row = FXCollections.observableArrayList();
        try {
            resultSet = statement.executeQuery(sql);
            metaData = resultSet.getMetaData();

            while (resultSet.next()) {
                SupplierListDB ob = new SupplierListDB(
                        resultSet.getInt("SUPPLIER_LIST_ID"),
                        resultSet.getTimestamp("DATE"),
                        resultSet.getString("USER"),
                        resultSet.getString("SUPPLIER"),
                        resultSet.getString("INSTITUTE"),
                        resultSet.getString("REMARK"),
                        resultSet.getDouble("PAYMENT"),
                        resultSet.getDouble("TOTAL_ORDER"),
                        resultSet.getDouble("TOTAL_DELIVERY"),
                        resultSet.getDouble("BALANCE_ORDER"),
                        resultSet.getDouble("BALANCE_DELIVERY"),
                        resultSet.getString("ADDRESS"),
                        resultSet.getString("CONTACTS"),
                        resultSet.getDouble("SUPPLIER_SERIAL"));
                row.addAll(ob);

            }
        } catch (Exception e) {

        }

        return row;

    }

    // # 7.01
    public void createCustomerList() {
        String sql = "CREATE TABLE  IF NOT EXISTS  CUSTOMER_LIST " //701
                + "(CUSTOMER_LIST_ID INT PRIMARY KEY     NOT NULL AUTO_INCREMENT,"
                + " DATE DATETIME   , "
                + " USER VARCHAR(50)   , "
                + " CUSTOMER VARCHAR(50)   , "
                + " INSTITUTE VARCHAR(50), "
                + " REMARK VARCHAR(100)   , "
                + " PAYMENT DOUBLE   , "
                + " TOTAL_ORDER DOUBLE   , "
                + " TOTAL_DELIVERY DOUBLE   , "
                + " BALANCE_ORDER DOUBLE   , "
                + " BALANCE_DELIVERY DOUBLE   , "
                + " ADDRESS VARCHAR(150) , "
                + " CONTACTS VARCHAR(20) , "
                + " CUSTOMER_SERIAL DOUBLE) ";
        update(sql);

    }

    public void insertCustomerList(CustomerListDB ob) {                        //702        
        String sql = "INSERT INTO CUSTOMER_LIST (CUSTOMER_LIST_ID, DATE , USER , CUSTOMER , INSTITUTE, REMARK , PAYMENT , TOTAL_ORDER, "
                + "TOTAL_DELIVERY, BALANCE_ORDER, BALANCE_DELIVERY ,ADDRESS, CONTACTS , CUSTOMER_SERIAL )"
                + " VALUES(" + ob.getId()+ ",'" + ob.getDate() + "','" + ob.getUser() + "','" + ob.getCustomer() + "','" + ob.getInstitute() + "','"
                + ob.getRemark() + "',"
                + ob.getPayment() + "," + ob.getTotalOrder() + "," + ob.getTotalDelivery() + ","
                + ob.getBalanceOrder() + "," + ob.getBalanceDelivery() + ",'" + ob.getAddress() + "','" + ob.getContacts() + "','" + ob.getCustomerSerial() + "');";
        update(sql);
    }

    public ObservableList<CustomerListDB> getCustomerList(String sql) {                //703
        ObservableList<CustomerListDB> row = FXCollections.observableArrayList();
        try {
            resultSet = statement.executeQuery(sql);
            metaData = resultSet.getMetaData();

            while (resultSet.next()) {
                CustomerListDB ob = new CustomerListDB(
                        resultSet.getInt("CUSTOMER_LIST_ID"),
                        resultSet.getTimestamp("DATE"),
                        resultSet.getString("USER"),
                        resultSet.getString("CUSTOMER"),
                        resultSet.getString("INSTITUTE"),
                        resultSet.getString("REMARK"),
                        resultSet.getDouble("PAYMENT"),
                        resultSet.getDouble("TOTAL_ORDER"),
                        resultSet.getDouble("TOTAL_DELIVERY"),
                        resultSet.getDouble("BALANCE_ORDER"),
                        resultSet.getDouble("BALANCE_DELIVERY"),
                        resultSet.getString("ADDRESS"),
                        resultSet.getString("CONTACTS"),
                        resultSet.getDouble("CUSTOMER_SERIAL"));
                row.addAll(ob);

            }
        } catch (Exception e) {

        }

        return row;

    }

    // # 8.01
    public void createCustomerTransaction() {
        String sql = "CREATE TABLE  IF NOT EXISTS  CUSTOMER_TRANSACTION "
                + "(CUSTOMER_TRANSACTION_ID INT PRIMARY KEY     NOT NULL AUTO_INCREMENT," //801
                + " DATE           DATETIME   , "
                + " USER           VARCHAR(30)   , "
                + " TYPE           DOUBLE   , "
                + " CUSTOMER           VARCHAR(50), "
                + " INSTITUTE           VARCHAR(50) , "
                + " STORAGE           VARCHAR(50) , "
                + " TRUCK_NO          VARCHAR(50), "
                + " BILL_NO          VARCHAR(50) , "
                + " PRODUCT          VARCHAR(50), "
                + " MARKA           VARCHAR(50) , "
                + " QUANTITY        DOUBLE, "
                + " UNIT           DOUBLE , "
                + " UNIT_TYPE           VARCHAR(50) , "
                + " RATE_KG           DOUBLE , "
                + " RATE_MON          DOUBLE , "
                + " DEBIT           DOUBLE , "
                + " CREDIT          DOUBLE , "
                + " BANK           VARCHAR(50) , "
                + " BALANCE        DOUBLE , "
                + " LP           VARCHAR(50) , "
                + " ID_BANK_DEPO_WITH           INT , "
                + " ID_SALE_DELIVERY          INT ) ";
        update(sql);

    }

    public void insertCustomerTransaction(CustomerTransactionDB ob) {                        //802      
        String sql = "INSERT INTO CUSTOMER_TRANSACTION (DATE , USER , TYPE ,CUSTOMER , INSTITUTE, STORAGE ,"
                + "TRUCK_NO, BILL_NO , PRODUCT , MARKA , QUANTITY , UNIT , UNIT_TYPE , RATE_KG , RATE_MON , "
                + "DEBIT , CREDIT , BANK , BALANCE, LP, ID_BANK_DEPO_WITH , ID_SALE_DELIVERY )"
                + " VALUES('" + ob.getDate() + "','" + ob.getUser() + "'," + ob.getType() + ",'" + ob.getCustomer() + "','"
                + ob.getInstitute() + "','" + ob.getStorage() + "','" + ob.getTruckNo() + "','" + ob.getBillNo() + "','"
                + ob.getProduct() + "','" + ob.getMarka() + "'," + ob.getQuantity() + "," + ob.getUnit() + ",'"
                + ob.getUnitType() + "'," + ob.getRateKg() + "," + ob.getRateMon() + ","
                + ob.getDebit() + "," + ob.getCredit() + ",'" + ob.getBank() + "',"
                + ob.getBalance() + ",'" + ob.getLp() + "'," + ob.getIdBankDepoWith() + "," + ob.getIdSaleDelivery() + ");";
        update(sql);
    }

    public ObservableList<CustomerTransactionDB> getCustomerTransaction(String sql) {                //803
        ObservableList<CustomerTransactionDB> row = FXCollections.observableArrayList();
        try {
            resultSet = statement.executeQuery(sql);
            metaData = resultSet.getMetaData();

            while (resultSet.next()) {
                CustomerTransactionDB ob = new CustomerTransactionDB(
                        resultSet.getInt("CUSTOMER_TRANSACTION_ID"),
                        resultSet.getTimestamp("DATE"),
                        resultSet.getString("USER"),
                        resultSet.getDouble("TYPE"),
                        resultSet.getString("CUSTOMER"),
                        resultSet.getString("INSTITUTE"),
                        resultSet.getString("STORAGE"),
                        resultSet.getString("TRUCK_NO"),
                        resultSet.getString("BILL_NO"),
                        resultSet.getString("PRODUCT"),
                        resultSet.getString("MARKA"),
                        resultSet.getDouble("QUANTITY"),
                        resultSet.getDouble("UNIT"),
                        resultSet.getString("UNIT_TYPE"),
                        resultSet.getDouble("RATE_KG"),
                        resultSet.getDouble("RATE_MON"),
                        resultSet.getDouble("DEBIT"),
                        resultSet.getDouble("CREDIT"),
                        resultSet.getString("BANK"),
                        resultSet.getDouble("BALANCE"),
                        resultSet.getString("LP"),
                        resultSet.getInt("ID_BANK_DEPO_WITH"),
                        resultSet.getInt("ID_SALE_DELIVERY")
                );
                row.addAll(ob);

            }
        } catch (Exception e) {

        }

        return row;

    }

    // # 9.01
    /*
    
     */
    public void createSupplierTransaction() {
        String sql = "CREATE TABLE  IF NOT EXISTS  SUPPLIER_TRANSACTION "
                + "(SUPPLIER_TRANSACTION_ID INT PRIMARY KEY     NOT NULL AUTO_INCREMENT," //901
                + " DATE DATETIME   , "
                + " USER VARCHAR(50), "
                + " TYPE DOUBLE , "
                + " SUPPLIER VARCHAR(50) , "
                + " INSTITUTE VARCHAR(50) , "
                + " STORAGE VARCHAR(50) , "
                + " ORDER_NO VARCHAR(50), "
                + " DELIVERY_NO VARCHAR(50) , "
                + " PRODUCT VARCHAR(50) , "
                + " QUANTITY DOUBLE , "
                + " UNIT DOUBLE , "
                + " UNIT_TYPE VARCHAR(50) , "
                + " RATE_KG DOUBLE , "
                + " RATE_MON DOUBLE , "
                + " DEBIT DOUBLE , "
                + " CREDIT DOUBLE , "
                + " BALANCE DOUBLE , "
                + " BANK_OR_CASH VARCHAR(50) , "
                + " LP VARCHAR(50) , "
                + " ID_BANK_DEPO_WITH           INT , "
                + " ID_PURCHASE_DELIVERY          INT ) ";
        update(sql);

    }

    public void insertSupplierTransaction(SupplierTransactionDB ob) {              //902
        String sql = "INSERT INTO SUPPLIER_TRANSACTION (DATE,USER , TYPE , SUPPLIER , INSTITUTE, STORAGE , ORDER_NO , DELIVERY_NO, "
                + "PRODUCT , QUANTITY , UNIT , UNIT_TYPE , RATE_KG , RATE_MON , DEBIT , CREDIT , BALANCE , "
                + "BANK_OR_CASH , LP , ID_BANK_DEPO_WITH , ID_PURCHASE_DELIVERY )"
                + " VALUES('" + ob.getDate() + "','" + ob.getUser() + "'," + ob.getType() + ",'" + ob.getSupplier() + "','" + ob.getInstitute() + "','"
                + ob.getStorage() + "','" + ob.getOrderNo() + "','" + ob.getDeliveryNo() + "','" + ob.getProduct() + "'," + ob.getQuantity() + ","
                + ob.getUnit() + ",'" + ob.getUnitType() + "'," + ob.getRateKg() + "," + ob.getRateMon()
                + "," + ob.getDebit() + "," + ob.getCredit() + "," + ob.getBalance() + ",'" + ob.getBankOrCash()
                + "','" + ob.getLp() + "'," + ob.getIdBankDepoWith() + "," + ob.getIdPurchaseDelivery() + ");";
        update(sql);

    }

    public ObservableList<SupplierTransactionDB> getSupplierTransaction(String sql) {                //903
        ObservableList<SupplierTransactionDB> row = FXCollections.observableArrayList();
        try {
            resultSet = statement.executeQuery(sql);
            metaData = resultSet.getMetaData();

            while (resultSet.next()) {
                SupplierTransactionDB ob = new SupplierTransactionDB(
                        resultSet.getInt("SUPPLIER_TRANSACTION_ID"),
                        resultSet.getTimestamp("DATE"),
                        resultSet.getString("USER"),
                        resultSet.getDouble("TYPE"),
                        resultSet.getString("SUPPLIER"),
                        resultSet.getString("INSTITUTE"),
                        resultSet.getString("STORAGE"),
                        resultSet.getString("ORDER_NO"),
                        resultSet.getString("DELIVERY_NO"),
                        resultSet.getString("PRODUCT"),
                        resultSet.getDouble("QUANTITY"),
                        resultSet.getDouble("UNIT"),
                        resultSet.getString("UNIT_TYPE"),
                        resultSet.getDouble("RATE_KG"),
                        resultSet.getDouble("RATE_MON"),
                        resultSet.getDouble("DEBIT"),
                        resultSet.getDouble("CREDIT"),
                        resultSet.getDouble("BALANCE"),
                        resultSet.getString("BANK_OR_CASH"),
                        resultSet.getString("LP"),
                        resultSet.getInt("ID_BANK_DEPO_WITH"),
                        resultSet.getInt("ID_PURCHASE_DELIVERY")
                );
                row.addAll(ob);

            }
        } catch (Exception e) {

        }

        return row;

    }

    // 9.01
    public void createBankTransaction() {
        String sql = "CREATE TABLE   IF NOT EXISTS BANK_TRANSACTION "
                + "(BANK_TRANSACTION_ID INT PRIMARY KEY     NOT NULL AUTO_INCREMENT," //1001
                + " TYPE DOUBLE   , "
                + " DATE DATETIME   , "
                + " USER VARCHAR(50)   , "
                + " BANK_NAME VARCHAR(50), "
                + " BANK_OR_CASH VARCHAR(50) , "
                + " ENTITY VARCHAR(50) , "
                + " REMARKS VARCHAR(200) , "
                + " AMOUNT DOUBLE , "
                + " BALANCE DOUBLE , "
                + " LP VARCHAR(50) , "
                + " ID_BANK_DEPO_WITH INT , "
                + " ID_EXPENSE INT , "
                + " ID_REVENUE INT ) ";
        update(sql);

    }

    public BankTransactionDB getBt(int Bt_ID) throws SQLException, ParseException {
        String query = "SELECT * FROM BANK_TRANSACTION WHERE BANK_TRANSACTION_ID=" + Bt_ID;
        BankTransactionDB ob = null;
        try {
            resultSet = statement.executeQuery(query);

            if (resultSet.next()) {
                ob = new BankTransactionDB(
                        resultSet.getInt("BANK_TRANSACTION_ID"),
                        resultSet.getDouble("TYPE"),
                        resultSet.getTimestamp("DATE"),
                        resultSet.getString("USER"),
                        resultSet.getString("BANK_NAME"),
                        resultSet.getString("BANK_OR_CASH"),
                        resultSet.getString("ENTITY"),
                        resultSet.getString("REMARKS"),
                        resultSet.getDouble("AMOUNT"),
                        resultSet.getDouble("BALANCE"),
                        resultSet.getString("LP"),
                        resultSet.getInt("ID_BANK_DEPO_WITH"),
                        resultSet.getInt("ID_EXPENSE"),
                        resultSet.getInt("ID_REVENUE")
                );
            }
        } catch (SQLException | ParseException e) {

        }
        return ob;
    }

    public void insertBankTransaction(BankTransactionDB ob) {                     //1002
        String sql = "INSERT INTO BANK_TRANSACTION (TYPE ,DATE, USER , BANK_NAME , BANK_OR_CASH , ENTITY, REMARKS, AMOUNT,BALANCE,  LP , ID_BANK_DEPO_WITH , ID_EXPENSE , ID_REVENUE)"
                + " VALUES(" + ob.getType() + ",'" + ob.getDate() + "','" + ob.getUser() + "','" + ob.getBankOrCash() + "','" + ob.getBankOrCash() + "','"
                + ob.getEntity() + "','" + ob.getRemarks() + "'," + ob.getAmount() + "," + ob.getBalance() + ",'" + ob.getLp() + "'," + ob.getIdBankDepoWith() + "," + ob.getIdExpense() + "," + ob.getIdRevenue() + ");";

        update(sql);

    }

    public ObservableList<BankTransactionDB> getBankTransaction(String sql) {                //1003
        ObservableList<BankTransactionDB> row = FXCollections.observableArrayList();
        try {
            resultSet = statement.executeQuery(sql);
            metaData = resultSet.getMetaData();

            while (resultSet.next()) {
                BankTransactionDB ob = new BankTransactionDB(
                        resultSet.getInt("BANK_TRANSACTION_ID"),
                        resultSet.getDouble("TYPE"),
                        resultSet.getTimestamp("DATE"),
                        resultSet.getString("USER"),
                        resultSet.getString("BANK_NAME"),
                        resultSet.getString("BANK_OR_CASH"),
                        resultSet.getString("ENTITY"),
                        resultSet.getString("REMARKS"),
                        resultSet.getDouble("AMOUNT"),
                        resultSet.getDouble("BALANCE"),
                        resultSet.getString("LP"),
                        resultSet.getInt("ID_BANK_DEPO_WITH"),
                        resultSet.getInt("ID_EXPENSE"),
                        resultSet.getInt("ID_REVENUE")
                );
                row.addAll(ob);

            }
        } catch (SQLException | ParseException e) {

        }

        return row;

    }

    //10.01 
    public void createTotalStock() {
        String sql = "CREATE TABLE  IF NOT EXISTS  TOTAL_STOCK "
                + "(TOTAL_STOCK_ID INT PRIMARY KEY     NOT NULL AUTO_INCREMENT,"
                + " DATE DATETIME   , " //1101
                + " PRODUCT VARCHAR(50)   , "
                + " STORAGE VARCHAR(90), "
                + " QUANTITY DOUBLE , "
                + " UNIT DOUBLE , "
                + " UNIT_TYPE VARCHAR(50) , "
                + " TOTAL_KG DOUBLE , "
                + " TOTAL_MON DOUBLE , "
                + " EXTRA DOUBLE , "
                + " AVG_RATE DOUBLE )";
        update(sql);

    }

    public void insertTotalStock(TotalStockDB ob) {                                           //1102
        String sql = "INSERT INTO TOTAL_STOCK (DATE, PRODUCT , STORAGE, TOTAL_KG , TOTAL_MON, AVG_RATE )"
                + " VALUES('" + ob.getDate() + "','" + ob.getProduct() + "','" + ob.getStorage() + "',"
                + ob.getTotalKg() + "," + ob.getTotalMon() + "," + ob.getAvgrate() + ");";
        //System.out.println(sql);
        update(sql);

    }

    public ObservableList<TotalStockDB> getTotalStock(String sql) {                //1103
        ObservableList<TotalStockDB> row = FXCollections.observableArrayList();
        try {
            resultSet = statement.executeQuery(sql);
            metaData = resultSet.getMetaData();

            while (resultSet.next()) {
                TotalStockDB ob = new TotalStockDB(
                        resultSet.getInt("TOTAL_STOCK_ID"),
                        resultSet.getTimestamp("DATE"),
                        resultSet.getString("PRODUCT"),
                        resultSet.getString("STORAGE"),
                        resultSet.getDouble("QUANTITY"),
                        resultSet.getDouble("UNIT"),
                        resultSet.getString("UNIT_TYPE"),
                        resultSet.getDouble("TOTAL_KG"),
                        resultSet.getDouble("TOTAL_MON"),
                        resultSet.getDouble("AVG_RATE")
                );
                row.addAll(ob);

            }
        } catch (Exception e) {

        }

        return row;

    }

// 11.01
    public void createUpcomingStock() {
        String sql = "CREATE TABLE  IF NOT EXISTS  UPCOMING_STOCK "
                + "(UPCOMING_STOCK_ID INT PRIMARY KEY     NOT NULL AUTO_INCREMENT," //1301
                + " DATE           DATETIME, "
                + " SUPPLIER           VARCHAR(50), "
                + " STORAGE           VARCHAR(50) , "
                + " PRODUCT          VARCHAR(50) , "
                + " ORDER_NO          VARCHAR(50) , "
                + " ORDER_QUANTITY DOUBLE , "
                + " ORDER_UNIT DOUBLE , "
                + " ORDER_TYPE VARCHAR(50) , "
                + " DELIVERY_QUANTITY DOUBLE , "
                + " DELIVERY_UNIT DOUBLE , "
                + " DELIVERY_TYPE VARCHAR(50) , "
                + " UPCOMING_QUANTITY DOUBLE , "
                + " UPCOMING_UNIT DOUBLE , "
                + " UPCOMING_TYPE VARCHAR(50) , "
                + " ID_PURCHASE_ORDER          INT , "
                + " ID_PURCHASE_DELIVERY          INT ) ";
        update(sql);
    }

    public void insertUpcomingStock(UpcomingStockDB ob) {   //1302        
        String sql = "INSERT INTO UPCOMING_STOCK (DATE, SUPPLIER ,STORAGE , PRODUCT , ORDER_NO, ORDER_QUANTITY , ORDER_UNIT ,"
                + " ORDER_TYPE , DELIVERY_QUANTITY , DELIVERY_UNIT , DELIVERY_TYPE ,  UPCOMING_QUANTITY , UPCOMING_UNIT , UPCOMING_TYPE , ID_PURCHASE_ORDER , "
                + "ID_PURCHASE_DELIVERY)"
                + " VALUES('" + ob.getDate() + "','" + ob.getSupplier() + "','" + ob.getStorage() + "','" + ob.getProduct() + "','"
                + ob.getOrderNo() + "'," + ob.getOrderQuantity() + "," + ob.getOrderUnit() + ",'"
                + ob.getOrderUnitType() + "'," + ob.getDeliveryQuantity() + "," + ob.getDeliveryUnit() + ",'"
                + ob.getDeliveryUnitType() + "'," + ob.getUpcomingQuantity() + "," + ob.getUpcomingUnit() + ",'"
                + ob.getUpcomingUnitType() + "'," + ob.getIdPurchaseOrder() + "," + ob.getIdPurchaseDelivery() + ");";

        update(sql);

    }

    public ObservableList<UpcomingStockDB> getUpcomingStock(String sql) {                //1303
        ObservableList<UpcomingStockDB> row = FXCollections.observableArrayList();
        try {
            resultSet = statement.executeQuery(sql);
            metaData = resultSet.getMetaData();

            while (resultSet.next()) {
                UpcomingStockDB ob = new UpcomingStockDB(
                        resultSet.getInt("UPCOMING_STOCK_ID"),
                        resultSet.getTimestamp("DATE"),
                        resultSet.getString("SUPPLIER"),
                        resultSet.getString("STORAGE"),
                        resultSet.getString("PRODUCT"),
                        resultSet.getString("ORDER_NO"),
                        resultSet.getDouble("ORDER_QUANTITY"),
                        resultSet.getDouble("ORDER_UNIT"),
                        resultSet.getString("ORDER_TYPE"),
                        resultSet.getDouble("DELIVERY_QUANTITY"),
                        resultSet.getDouble("DELIVERY_UNIT"),
                        resultSet.getString("DELIVERY_TYPE"),
                        resultSet.getDouble("UPCOMING_QUANTITY"),
                        resultSet.getDouble("UPCOMING_UNIT"),
                        resultSet.getString("UPCOMING_TYPE"),
                        resultSet.getInt("ID_PURCHASE_ORDER"),
                        resultSet.getInt("ID_PURCHASE_DELIVERY")
                );
                row.addAll(ob);

            }
        } catch (Exception e) {

        }

        return row;

    }

    //12.01
    public void createPendingDeliveries() {
        String sql = "CREATE TABLE  IF NOT EXISTS  PENDING_DELIVERIES "
                + "(PENDING_DELIVERIES_ID INT PRIMARY KEY     NOT NULL AUTO_INCREMENT," //1401
                + " DATE DATETIME, "
                + " CUSTOMER VARCHAR(50), "
                + " PRODUCT VARCHAR(50) , "
                + " INVOICE_NO VARCHAR(50) , "
                + " ORDER_QUANTITY DOUBLE , "
                + " ORDER_UNIT DOUBLE , "
                + " ORDER_TYPE VARCHAR(50) , "
                + " DELIVERY_QUANTITY DOUBLE , "
                + " DELIVERY_UNIT DOUBLE , "
                + " DELIVERY_TYPE VARCHAR(50) , "
                + " PENDING_QUANTITY DOUBLE , "
                + " PENDING_UNIT DOUBLE , "
                + " PENDING_TYPE VARCHAR(50) , "
                + " ID_BANK_DEPO_WITH           INT , "
                + " ID_SALE_ORDER    INT ) ";
        update(sql);

    }

    public void insertPendingDeliveries(PendingDeliveriesDB ob) {            //1402
        String sql = "INSERT INTO PENDING_DELIVERIES(DATE, CUSTOMER , PRODUCT , INVOICE_NO, ORDER_QUANTITY , ORDER_UNIT ,"
                + " ORDER_TYPE , DELIVERY_QUANTITY , DELIVERY_UNIT , DELIVERY_TYPE ,  PENDING_QUANTITY , PENDING_UNIT , PENDING_TYPE , ID_BANK_DEPO_WITH , "
                + "ID_SALE_ORDER)"
                + " VALUES('" + ob.getDate() + "','" + ob.getCustomer() + "','" + ob.getProduct() + "','"
                + ob.getInvoiceNo() + "'," + ob.getOrderQuantity() + "," + ob.getOrderUnit() + ",'"
                + ob.getOrderUnitType() + "'," + ob.getDeliveryQuantity() + "," + ob.getDeliveryUnit() + ",'"
                + ob.getDeliveryUnitType() + "'," + ob.getPendingQuantity() + "," + ob.getPendingUnit() + ",'"
                + ob.getPendingUnitType() + "'," + ob.getIdBankDepoWith() + "," + ob.getIdSaleOrder() + ");";

        update(sql);

    }

    public ObservableList<PendingDeliveriesDB> getPendingDeliveries(String sql) {                //1403
        ObservableList<PendingDeliveriesDB> row = FXCollections.observableArrayList();
        try {
            resultSet = statement.executeQuery(sql);
            metaData = resultSet.getMetaData();

            while (resultSet.next()) {
                PendingDeliveriesDB ob = new PendingDeliveriesDB(
                        resultSet.getInt("UPCOMING_STOCK_ID"),
                        resultSet.getTimestamp("DATE"),
                        resultSet.getString("CUSTOMER"),
                        resultSet.getString("PRODUCT"),
                        resultSet.getString("INVOICE_NO"),
                        resultSet.getDouble("ORDER_QUANTITY"),
                        resultSet.getDouble("ORDER_UNIT"),
                        resultSet.getString("ORDER_TYPE"),
                        resultSet.getDouble("DELIVERY_QUANTITY"),
                        resultSet.getDouble("DELIVERY_UNIT"),
                        resultSet.getString("DELIVERY_TYPE"),
                        resultSet.getDouble("PENDING_QUANTITY"),
                        resultSet.getDouble("PENDING_UNIT"),
                        resultSet.getString("PENDING_TYPE"),
                        resultSet.getInt("ID_BANK_DEPO_WITH"),
                        resultSet.getInt("ID_SALE_ORDER")
                );
                row.addAll(ob);

            }
        } catch (Exception e) {

        }

        return row;

    }

    //13.01
    public void createBankDepoWith() {
        String sql = "CREATE TABLE  IF NOT EXISTS  BANK_DEPO_WITH "
                + "(BANK_DEPO_WITH_ID INT PRIMARY KEY     NOT NULL AUTO_INCREMENT," //1501
                + " DATE DATETIME, "
                + " PARTY VARCHAR(50), "
                + " INSTITUTE VARCHAR(50) , "
                + " BANK_OR_CASH VARCHAR(50) , "
                + " AMOUNT DOUBLE , "
                + " LP VARCHAR(50) , "
                + " USER VARCHAR(30) ) ";
        update(sql);

    }

    // Table Truck Transport
    // expense table
    public void createExpense() {
        String sql = "CREATE TABLE  IF NOT EXISTS  EXPENSE "
                + "(EXPENSE_ID INT PRIMARY KEY     NOT NULL AUTO_INCREMENT," //1501
                + " DATE DATETIME, "
                + " USER VARCHAR(50), "
                + " EXPENSE VARCHAR(50) , "
                + " REMARKS VARCHAR(100) , "
                + " BANK_OR_CASH VARCHAR(50) , "
                + " AMOUNT DOUBLE , "
                + " LP VARCHAR(50) ) ";
        update(sql);

    }

    public void insertExpense(ExpenseDB ob) {            //1502
        String sql = "INSERT INTO EXPENSE (DATE, USER , EXPENSE, REMARKS, BANK_OR_CASH , AMOUNT , LP)"
                + " VALUES('" + ob.getDate() + "','" + ob.getUser() + "','" + ob.getExpense() + "','" + ob.getRemarks() + "','"
                + ob.getBankOrCash() + "'," + ob.getAmount() + ",'" + ob.getLp() + "');";

        update(sql);

    }

    public ObservableList<ExpenseDB> getExpense(String sql) {                //1503
        ObservableList<ExpenseDB> row = FXCollections.observableArrayList();
        try {
            resultSet = statement.executeQuery(sql);
            metaData = resultSet.getMetaData();

            while (resultSet.next()) {
                ExpenseDB ob = new ExpenseDB(
                        resultSet.getInt("EXPENSE_ID"),
                        resultSet.getTimestamp("DATE"),
                        resultSet.getString("USER"),
                        resultSet.getString("EXPENSE"),
                        resultSet.getString("REMARKS"),
                        resultSet.getString("BANK_OR_CASH"),
                        resultSet.getDouble("AMOUNT"),
                        resultSet.getString("LP")
                );
                row.addAll(ob);

            }
        } catch (Exception e) {

        }

        return row;

    }

    // REVENUE TABLE
    public void createRevenue() {
        String sql = "CREATE TABLE  IF NOT EXISTS  REVENUE "
                + "(REVENUE_ID INT PRIMARY KEY     NOT NULL AUTO_INCREMENT," //1501
                + " DATE DATETIME, "
                + " USER VARCHAR(50), "
                + " REVENUE VARCHAR(50) , "
                + " REMARKS VARCHAR(100) , "
                + " BANK_OR_CASH VARCHAR(50) , "
                + " AMOUNT DOUBLE , "
                + " LP VARCHAR(50) ) ";
        update(sql);

    }

    public void insertRevenue(RevenueDB ob) {            //1502
        String sql = "INSERT INTO REVENUE (DATE, USER , REVENUE, REMARKS, BANK_OR_CASH , AMOUNT , LP)"
                + " VALUES('" + ob.getDate() + "','" + ob.getUser() + "','" + ob.getRevenue() + "','" + ob.getRemarks() + "','"
                + ob.getBankOrCash() + "'," + ob.getAmount() + ",'" + ob.getLp() + "');";

        update(sql);

    }

    public ObservableList<RevenueDB> getRevenue(String sql) {                //1503
        ObservableList<RevenueDB> row = FXCollections.observableArrayList();
        try {
            resultSet = statement.executeQuery(sql);
            metaData = resultSet.getMetaData();

            while (resultSet.next()) {
                RevenueDB ob = new RevenueDB(
                        resultSet.getInt("REVENUE_ID"),
                        resultSet.getTimestamp("DATE"),
                        resultSet.getString("USER"),
                        resultSet.getString("REVENUE"),
                        resultSet.getString("REMARKS"),
                        resultSet.getString("BANK_OR_CASH"),
                        resultSet.getDouble("AMOUNT"),
                        resultSet.getString("LP")
                );
                row.addAll(ob);

            }
        } catch (Exception e) {

        }

        return row;

    }

    public String getAStringDB(String sql, String col) {

        String str = null;
        try {
            resultSet = statement.executeQuery(sql);
            metaData = resultSet.getMetaData();

            while (resultSet.next()) {
                //str = resultSet.getString("\" "+ col + "\"");// "\"hello\""
                str = resultSet.getString(col);   //"''hello''"
            }
        } catch (Exception e) {

        }

        return str;

    }

    public Date getADateDB(String sql, String col) {

        Date str = null;
        try {
            resultSet = statement.executeQuery(sql);
            metaData = resultSet.getMetaData();

            while (resultSet.next()) {
                //str = resultSet.getString("\" "+ col + "\"");// "\"hello\""
                str = resultSet.getDate(col);   //"''hello''"
            }
        } catch (Exception e) {

        }

        return str;

    }

    public String getADateTimeDB(String sql, String col) {

        String str = null;
        try {
            resultSet = statement.executeQuery(sql);
            metaData = resultSet.getMetaData();

            while (resultSet.next()) {
                //str = resultSet.getString("\" "+ col + "\"");// "\"hello\""
                str = resultSet.getTimestamp(col).toString();   //"''hello''"
            }
        } catch (Exception e) {

        }

        return str;

    }

    public int getAIntDB(String sql, String col) {

        int num = 0;
        try {
            resultSet = statement.executeQuery(sql);
            metaData = resultSet.getMetaData();

            while (resultSet.next()) {
                //str = resultSet.getString("\" "+ col + "\"");// "\"hello\""
                num = resultSet.getInt(col);   //"''hello''"
            }
        } catch (Exception e) {

        }

        return num;

    }

    public double getADoubleDB(String sql, String col) {

        double num = 0.0;
        try {
            resultSet = statement.executeQuery(sql);
            metaData = resultSet.getMetaData();

            while (resultSet.next()) {
                //str = resultSet.getString("\" "+ col + "\"");// "\"hello\""
                num = resultSet.getDouble(col);   //"''hello''"
            }
        } catch (Exception e) {

        }

        return num;

    }

    public void setAStringDB(String sql, String input, String key) throws SQLException {

        try {
            PreparedStatement preparedStmt = connection.prepareStatement(sql);
            preparedStmt.setString(1, input);
            preparedStmt.setString(2, key);

            // execute the java preparedstatement
            preparedStmt.executeUpdate();
        } catch (Exception e) {
        }

    }

    public void setADoubleDB(String sql, double input, String key) throws SQLException {

        try {
            PreparedStatement preparedStmt = connection.prepareStatement(sql);
            preparedStmt.setDouble(1, input);
            preparedStmt.setString(2, key);

            // execute the java preparedstatement
            preparedStmt.executeUpdate();
        } catch (Exception e) {
        }

    }

    public void setAStringDB(String sql, String input1, String key1, String key2) {

        try {
            PreparedStatement preparedStmt = connection.prepareStatement(sql);
            preparedStmt.setString(1, input1);

            preparedStmt.setString(2, key1);
            preparedStmt.setString(3, key2);

            // execute the java preparedstatement
            preparedStmt.executeUpdate();
        } catch (Exception e) {
        }

    }

    public void setADoubleDB(String sql, double input1, String key1, String key2) {

        try {
            PreparedStatement preparedStmt = connection.prepareStatement(sql);
            preparedStmt.setDouble(1, input1);

            preparedStmt.setString(2, key1);
            preparedStmt.setString(3, key2);

            // execute the java preparedstatement
            preparedStmt.executeUpdate();
        } catch (Exception e) {
        }

    }

    public void set2DoubleDB(String sql, double input1, String key1, String key2) {

        try {
            PreparedStatement preparedStmt = connection.prepareStatement(sql);
            preparedStmt.setDouble(1, input1);
            preparedStmt.setDouble(2, input1);
            preparedStmt.setString(3, key1);
            preparedStmt.setString(4, key2);

            // execute the java preparedstatement
            preparedStmt.executeUpdate();
        } catch (Exception e) {
        }

    }

    public void setAStringDB(String sql, String input1, String key1, String key2, String key3) {

        try {
            PreparedStatement preparedStmt = connection.prepareStatement(sql);
            preparedStmt.setString(1, input1);

            preparedStmt.setString(2, key1);
            preparedStmt.setString(3, key2);
            preparedStmt.setString(4, key3);

            // execute the java preparedstatement
            preparedStmt.executeUpdate();
        } catch (Exception e) {
        }

    }

    public void set3StringDB(String sql, String input1, String input2, String input3, String key1, String key2, String key3) throws SQLException {

        try {
            PreparedStatement preparedStmt = connection.prepareStatement(sql);
            preparedStmt.setString(1, input1);
            preparedStmt.setString(2, input2);
            preparedStmt.setString(3, input3);
            preparedStmt.setString(4, key1);
            preparedStmt.setString(5, key2);
            preparedStmt.setString(6, key3);

            // execute the java preparedstatement
            preparedStmt.executeUpdate();
        } catch (Exception e) {
        }

    }

    //Tanmoy's Addition
    public void set3DoubleDB(String sql, Double input1, Double input2, Double input3, String key1, String key2, String key3) throws SQLException {

        try {
            PreparedStatement preparedStmt = connection.prepareStatement(sql);
            preparedStmt.setDouble(1, input1);
            preparedStmt.setDouble(2, input2);
            preparedStmt.setDouble(3, input3);
            preparedStmt.setString(4, key1);
            preparedStmt.setString(5, key2);
            preparedStmt.setString(6, key3);

            // execute the java preparedstatement
            preparedStmt.executeUpdate();
        } catch (Exception e) {
        }

    }

    public void setAStringDB(String sql, String input1, int key1) {

        try {
            PreparedStatement preparedStmt = connection.prepareStatement(sql);
            preparedStmt.setString(1, input1);

            preparedStmt.setDouble(2, key1);

            // execute the java preparedstatement
            preparedStmt.executeUpdate();
        } catch (Exception e) {
        }

    }

    public void set3DoubleDB(String sql, Double input1, Double input2, Double input3, int key1) throws SQLException {

        try {
            PreparedStatement preparedStmt = connection.prepareStatement(sql);
            preparedStmt.setDouble(1, input1);
            preparedStmt.setDouble(2, input2);
            preparedStmt.setDouble(3, input3);
            preparedStmt.setInt(4, key1);

            // execute the java preparedstatement
            preparedStmt.executeUpdate();
        } catch (Exception e) {
        }

    }

    public void setADoubleDB(String sql, double input, int key) throws SQLException {

        try {
            PreparedStatement preparedStmt = connection.prepareStatement(sql);
            preparedStmt.setDouble(1, input);
            preparedStmt.setDouble(2, key);

            // execute the java preparedstatement
            preparedStmt.executeUpdate();
        } catch (Exception e) {
        }

    }

    public void set6DoubleDB(String sql, Double input1, Double input2, String input3, Double input4, Double input5, String input6, int key) throws SQLException {

        try {
            PreparedStatement preparedStmt = connection.prepareStatement(sql);
            preparedStmt.setDouble(1, input1);
            preparedStmt.setDouble(2, input2);
            preparedStmt.setString(3, input3);
            preparedStmt.setDouble(4, input4);
            preparedStmt.setDouble(5, input5);
            preparedStmt.setString(6, input6);

            preparedStmt.setInt(7, key);

            // execute the java preparedstatement
            preparedStmt.executeUpdate();
        } catch (Exception e) {
        }

    }

    //Tanmoy's addition ends
    public void update(String sql) {
        //connectDatabase();

        try {
            statement.executeUpdate(sql);

        } catch (SQLException ex) {
            Logger.getLogger(DontDeleteDB.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        //closeDatabase();

    }

    public void connectDatabase() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/stairsdatabase?zeroDateTimeBehavior=convertToNull", "root", Pass);
            //connection= DriverManager.getConnection("jdbc:mysql:https://bsldu.org.bd:2083/3rdparty/phpMyAdmin/index.php:3306/bslduorg_stairsdatabase","bslduorg_anik","anikbsl");
            statement = connection.createStatement();

        } catch (ClassNotFoundException | SQLException e) {
        }
        System.out.println("database connected_razon");
    }

    public void connectDatabaseOnline() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/stairsdatabase?zeroDateTimeBehavior=convertToNull", "root", Pass);
            //connection= DriverManager.getConnection("jdbc:mysql://host.evatixcloud.com:2083/cpsess7182539747/:3306/bslduorg_stairsdatabase","bslduorg_anik","anikbsl");
            statement = connection.createStatement();

        } catch (ClassNotFoundException | SQLException e) {
        }
        System.out.println("database connected_razon");
    }

    public void closeDatabase() {

        try {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }

            System.out.println("database closed_razon");
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public SortedSet<String> getSuggestionList(String query, String column_name) {

        SortedSet<String> sortedSet = new TreeSet<>();
        try {
            resultSet = statement.executeQuery(query);
            metaData = resultSet.getMetaData();

            while (resultSet.next()) {
                String name = resultSet.getString(column_name);

                sortedSet.add(name);

            }
        } catch (Exception e) {

        }

        return sortedSet;
    }

    public String getAStringSql(String targetCol, String tableName, String searchCol, String customerName) {
        String sql = "SELECT " + targetCol + " FROM " + tableName + " WHERE " + searchCol + "= '" + customerName + "'";
        return sql;

    }

    public void delete(String sql) {
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.execute();
        } catch (SQLException ex) {
            Logger.getLogger(DontDeleteDB.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}


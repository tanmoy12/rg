/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 *
 * @author User
 */
public class SaleOrderDB {

    private int id;
    private Timestamp date;
    private String user;
    private String customer;
    private String institute;
    private String invoiceNo;
    private String product;
    private String marka;
    private double quantity;
    private double unit;
    private String unitType;
    private double rateKg;
    private double rateMon;
    private double price;
    private String lp;

    public String getDate2() {
        return date2;
    }

    public void setDate2(String date2) {
        this.date2 = date2;
    }
    private String date2;
    
        
    private String quantity2;
    private String unit2;
    private String rateKg2;
    private String rateMon2;
    private String price2;

    public int getSerial() {
        return serial;
    }

    public void setSerial(int serial) {
        this.serial = serial;
    }
    private int serial;


    public String getQuantity2() {
        return quantity2;
    }

    public void setQuantity2(String quantity2) {
        this.quantity2 = quantity2;
    }

    public String getUnit2() {
        return unit2;
    }

    public void setUnit2(String unit2) {
        this.unit2 = unit2;
     
    }

    public String getRateKg2() {
        return rateKg2;
    }

    public void setRateKg2(String rateKg2) {
        this.rateKg2 = rateKg2;
    }

    public String getRateMon2() {
        return rateMon2;
    }

    public void setRateMon2(String rateMon2) {
        this.rateMon2 = rateMon2;
    }

    public String getPrice2() {
        return price2;
    }

    public void setPrice2(String price2) {
        this.price2 = price2;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getInstitute() {
        return institute;
    }

    public void setInstitute(String institute) {
        this.institute = institute;
    }

    public String getInvoiceNo() {
        return invoiceNo;
    }

    public void setInvoiceNo(String invoiceNo) {
        this.invoiceNo = invoiceNo;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getMarka() {
        return marka;
    }

    public void setMarka(String marka) {
        this.marka = marka;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public double getUnit() {
        return unit;
    }

    public void setUnit(double unit) {
        this.unit = unit;
        this.unit2 = String.valueOf((int)unit)+" "+unitType;
    }

    public String getUnitType() {
        return unitType;
    }

    public void setUnitType(String unitType) {
        this.unitType = unitType;
    }

    public double getRateKg() {
        return rateKg;
    }

    public void setRateKg(double rateKg) {
        this.rateKg = rateKg;
    }

    public double getRateMon() {
        return rateMon;
    }

    public void setRateMon(double rateMon) {
        this.rateMon = rateMon;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getLp() {
        return lp;
    }

    public void setLp(String lp) {
        this.lp = lp;
    }

    public SaleOrderDB() {
    }

    public SaleOrderDB(int id, Timestamp date, String user, String customer, String institute, 
            String invoiceNo, String product, String marka, double quantity, double unit, 
            String unitType, double rateKg, double rateMon, double price, String lp, int serial) {
        this.id = id;
        this.date = date;
        this.user = user;
        this.customer = customer;
        this.institute = institute;
        this.invoiceNo = invoiceNo;
        this.product = product;
        this.marka = marka;
        this.quantity = quantity;
        this.unit = unit;
        this.unitType = unitType;
        this.rateKg = rateKg;
        this.rateMon = rateMon;
        this.price = price;
        this.lp = lp;
        this.serial=serial;
        
        this.price2 = IntegerTextField.numformat(price);
        this.quantity2 = IntegerTextField.numformat(quantity);
        this.rateKg2 = IntegerTextField.doubleformat(rateKg);
        this.rateMon2 = IntegerTextField.numformat(rateMon);
        this.unit2 = String.valueOf((int)unit)+" "+unitType;
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        this.date2= formatter.format(date);
        
    }


}

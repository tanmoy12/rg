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
public class SaleDeliveryDB {

    private int id;
    private Timestamp date;
    private String user;
    private double deliverySerial;
    private String customer;
    private String institute;
    private String invoiceNo;
    private String billNo;
    private String truckNo;
    private String truckMobileNo;
    private String product;
    private String marka;
    private String storage;
    private double quantity;
    private double unit;
    private String unitType;
    private double rateKg;
    private double rateMon;
    private double freight;
    private double advance;
    private double commission;
    private double price;
    private String lp;
    private String date2;

    public String getDeliverySerial2() {
        return deliverySerial2;
    }

    public void setDeliverySerial2(String deliverySerial2) {
        this.deliverySerial2 = deliverySerial2;
    }

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

    public String getFreight2() {
        return freight2;
    }

    public void setFreight2(String freight2) {
        this.freight2 = freight2;
    }

    public String getCommission2() {
        return commission2;
    }

    public void setCommission2(String commission2) {
        this.commission2 = commission2;
    }

    public String getPrice2() {
        return price2;
    }

    public void setPrice2(String price2) {
        this.price2 = price2;
    }
    
    private String deliverySerial2;
    private String quantity2;
    private String unit2;
    private String rateKg2;
    private String rateMon2;
    private String freight2;
    private String commission2;

    public double getAdvance() {
        return advance;
    }

    public void setAdvance(double advance) {
        this.advance = advance;
    }

    public String getAdvance2() {
        return advance2;
    }

    public void setAdvance2(String advance2) {
        this.advance2 = advance2;
    }
    private String advance2;
    private String price2;
    
    
    
    public SaleDeliveryDB(int id, Timestamp date, String user, double deliverySerial, String customer, String institute, String invoiceNo, String billNo, String truckNo, String truckMobileNo, String product, String marka, String storage, double quantity, double unit, String unitType, double rateKg, double rateMon, double freight, double advance, double commission, double price, String lp) {
        this.id = id;
        this.date = date;
        this.user = user;
        this.deliverySerial = deliverySerial;
        this.customer = customer;
        this.institute = institute;
        this.invoiceNo = invoiceNo;
        this.billNo = billNo;
        this.truckNo = truckNo;
        this.truckMobileNo = truckMobileNo;
        this.product = product;
        this.marka = marka;
        this.storage = storage;
        this.quantity = quantity;
        this.unit = unit;
        this.unitType = unitType;
        this.rateKg = rateKg;
        this.rateMon = rateMon;
        this.freight = freight;
        this.advance = advance;
        this.commission = commission;
        this.price = price;
        this.lp = lp;
        
        this.commission2 = IntegerTextField.numformat(commission);
        this.deliverySerial2 = String.valueOf(deliverySerial);
        this.freight2 = IntegerTextField.numformat(freight);
        this.price2 = IntegerTextField.numformat(price);
        this.quantity2 = IntegerTextField.numformat(quantity);
        this.rateKg2 = IntegerTextField.doubleformat(rateKg);
        this.rateMon2 = IntegerTextField.numformat(rateMon);
        this.advance2 = IntegerTextField.numformat(advance);
        this.unit2 = this.unit2 = String.valueOf((int)unit)+" "+unitType;
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        this.date2= formatter.format(date);
        
        
    }

    public SaleDeliveryDB() {
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

    public double getDeliverySerial() {
        return deliverySerial;
    }

    public void setDeliverySerial(double deliverySerial) {
        this.deliverySerial = deliverySerial;
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

    public String getBillNo() {
        return billNo;
    }

    public void setBillNo(String billNo) {
        this.billNo = billNo;
    }

    public String getTruckNo() {
        return truckNo;
    }

    public void setTruckNo(String truckNo) {
        this.truckNo = truckNo;
    }

    public String getTruckMobileNo() {
        return truckMobileNo;
    }

    public void setTruckMobileNo(String truckMobileNo) {
        this.truckMobileNo = truckMobileNo;
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

    public String getStorage() {
        return storage;
    }

    public void setStorage(String storage) {
        this.storage = storage;
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

    public double getFreight() {
        return freight;
    }

    public void setFreight(double freight) {
        this.freight = freight;
    }

    public double getCommission() {
        return commission;
    }

    public void setCommission(double commission) {
        this.commission = commission;
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

    public String getDate2() {
        return date2;
    }

    public void setDate2(String date2) {
        this.date2 = date2;
    }

    public void setLp(String lp) {
        this.lp = lp;
    }

 
}

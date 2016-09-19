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
public class CustomerTransactionDB {
    private int id ; 
    private Timestamp date;
    private String user;
    private double type;
    private String customer;
    private String institute;
    private String storage;
    private String truckNo;
    private String billNo;
    private String product;
    private String marka;
    private double quantity;
    private double unit;
    private String unitType;
    private double rateKg;
    private double rateMon;
    private double debit;
    private double credit;
    private String bank;
    private double balance;
    private String lp;
    private int idBankDepoWith ;

    public String getType2() {
        return type2;
    }

    public void setType2(String type2) {
        this.type2 = type2;
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

    public String getDebit2() {
        return debit2;
    }

    public void setDebit2(String debit2) {
        this.debit2 = debit2;
    }

    public String getCredit2() {
        return credit2;
    }

    public void setCredit2(String credit2) {
        this.credit2 = credit2;
    }

    public String getBalance2() {
        return balance2;
    }

    public void setBalance2(String balance2) {
        this.balance2 = balance2;
    }
    
    
    private String type2;
    private String quantity2;
    private String unit2;
    private String rateKg2;
    private String rateMon2;
    private String debit2;
    private String credit2;
    private String balance2;

    public String getDate2() {
        return date2;
    }

    public void setDate2(String date2) {
        this.date2 = date2;
    }
    private String date2;
    
    private int idSaleDelivery;

    public int getIdSaleDelivery() {
        return idSaleDelivery;
    }

    public void setIdSaleDelivery(int idSaleDelivery) {
        this.idSaleDelivery = idSaleDelivery;
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

    public double getType() {
        return type;
    }

    public void setType(double type) {
        this.type = type;
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

    public String getStorage() {
        return storage;
    }

    public void setStorage(String storage) {
        this.storage = storage;
    }

    public String getTruckNo() {
        return truckNo;
    }

    public void setTruckNo(String truckNo) {
        this.truckNo = truckNo;
    }

    public String getBillNo() {
        return billNo;
    }

    public void setBillNo(String billNo) {
        this.billNo = billNo;
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

    public double getDebit() {
        return debit;
    }

    public void setDebit(double debit) {
        this.debit = debit;
    }

    public double getCredit() {
        return credit;
    }

    public void setCredit(double credit) {
        this.credit = credit;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getLp() {
        return lp;
    }

    public void setLp(String lp) {
        this.lp = lp;
    }

    public int getIdBankDepoWith() {
        return idBankDepoWith;
    }

    public void setIdBankDepoWith(int idBankDepoWith) {
        this.idBankDepoWith = idBankDepoWith;
    }

    public CustomerTransactionDB() {
    }

    public CustomerTransactionDB(int id, Timestamp date, String user, double type, String customer, String institute, String storage, String truckNo, String billNo, String product, String marka, double quantity, double unit, String unitType, double rateKg, double rateMon, double debit, double credit, String bank, double balance, String lp, int idBankDepoWith, int idSaleDelivery) {
        this.id = id;
        this.date = date;
        this.user = user;
        this.type = type;
        this.customer = customer;
        this.institute = institute;
        this.storage = storage;
        this.truckNo = truckNo;
        this.billNo = billNo;
        if("null".equals(billNo)) this.billNo="";
        this.product = product;
        if("null".equals(product)) this.product="";
        this.marka = marka;
        this.quantity = quantity;
        this.unit = unit;
        this.unitType = unitType;
        if("null".equals(unitType)) this.unitType="";
        this.rateKg = rateKg;
        this.rateMon = rateMon;
        this.debit = debit;
        this.credit = credit;
        this.bank = bank;
        if("null".equals(bank)) this.bank="";
        this.balance = balance;
        this.lp = lp;
        this.idBankDepoWith = idBankDepoWith;
        
        this.balance2 = IntegerTextField.numformat(balance);
        this.credit2 = IntegerTextField.numformat(credit);
        if(credit==0) this.credit2="";
        this.debit2 = IntegerTextField.numformat(debit);
        if(debit==0) this.debit2="";
        this.quantity2 = IntegerTextField.numformat(quantity);
        if(quantity==0) this.quantity2="";
        this.rateKg2 = IntegerTextField.numformat(rateKg);
        this.rateMon2 = IntegerTextField.numformat(rateMon);
        this.type2 = String.valueOf(type);
        this.unit2 = String.valueOf((int)unit)+" "+this.unitType;
        if(unit==0)this.unit2="";
        this.idSaleDelivery = idSaleDelivery;
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        this.date2= formatter.format(date);
        
    }

}

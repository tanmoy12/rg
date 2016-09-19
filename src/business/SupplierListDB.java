/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import java.sql.Timestamp;

/**
 *
 * @author User
 */
public class SupplierListDB {

    private int id ;
    private Timestamp date;
    private String user;
    private String supplier;
    private String institute;
    private String remark;
    private double payment;
    private double totalOrder;
    private double totalDelivery;
    private double balanceOrder;
    private double balanceDelivery;    
    private String address ;
    private String contacts;
    private double supplierSerial;

    public String getPayment2() {
        return payment2;
    }

    public void setPayment2(String payment2) {
        this.payment2 = payment2;
    }

    public String getTotalOrder2() {
        return totalOrder2;
    }

    public void setTotalOrder2(String totalOrder2) {
        this.totalOrder2 = totalOrder2;
    }

    public String getTotalDelivery2() {
        return totalDelivery2;
    }

    public void setTotalDelivery2(String totalDelivery2) {
        this.totalDelivery2 = totalDelivery2;
    }

    public String getBalanceOrder2() {
        return balanceOrder2;
    }

    public void setBalanceOrder2(String balanceOrder2) {
        this.balanceOrder2 = balanceOrder2;
    }

    public String getBalanceDelivery2() {
        return balanceDelivery2;
    }

    public void setBalanceDelivery2(String balanceDelivery2) {
        this.balanceDelivery2 = balanceDelivery2;
    }

    public String getSupplierSerial2() {
        return supplierSerial2;
    }

    public void setSupplierSerial2(String supplierSerial2) {
        this.supplierSerial2 = supplierSerial2;
    }
    
    private String payment2;
    private String totalOrder2;
    private String totalDelivery2;
    private String balanceOrder2;
    private String balanceDelivery2;
    private String supplierSerial2;
    

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

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public String getInstitute() {
        return institute;
    }

    public void setInstitute(String institute) {
        this.institute = institute;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public double getPayment() {
        return payment;
    }

    public void setPayment(double payment) {
        this.payment = payment;
    }

    public double getTotalOrder() {
        return totalOrder;
    }

    public void setTotalOrder(double totalOrder) {
        this.totalOrder = totalOrder;
    }

    public double getTotalDelivery() {
        return totalDelivery;
    }

    public void setTotalDelivery(double totalDelivery) {
        this.totalDelivery = totalDelivery;
    }

    public double getBalanceOrder() {
        return balanceOrder;
    }

    public void setBalanceOrder(double balanceOrder) {
        this.balanceOrder = balanceOrder;
    }

    public double getBalanceDelivery() {
        return balanceDelivery;
    }

    public void setBalanceDelivery(double balanceDelivery) {
        this.balanceDelivery = balanceDelivery;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContacts() {
        return contacts;
    }

    public void setContacts(String contacts) {
        this.contacts = contacts;
    }

    public double getSupplierSerial() {
        return supplierSerial;
    }

    public void setSupplierSerial(double supplierSerial) {
        this.supplierSerial = supplierSerial;
    }

    public SupplierListDB(int id, Timestamp date, String user, String supplier, String institute, String remark, double payment, double totalOrder, double totalDelivery, double balanceOrder, double balanceDelivery, String address, String contacts, double supplierSerial) {
        this.id = id;
        this.date = date;
        this.user = user;
        this.supplier = supplier;
        this.institute = institute;
        this.remark = remark;
        this.payment = payment;
        this.totalOrder = totalOrder;
        this.totalDelivery = totalDelivery;
        this.balanceOrder = balanceOrder;
        this.balanceDelivery = balanceDelivery;
        this.address = address;
        this.contacts = contacts;
        this.supplierSerial = supplierSerial;
        
        this.balanceDelivery2 =IntegerTextField.numformat(balanceDelivery);
        this.balanceOrder2 = IntegerTextField.numformat(balanceOrder);
        this.supplierSerial2 = IntegerTextField.numformat(supplierSerial);
        this.totalDelivery2 = IntegerTextField.numformat(totalDelivery);
        this.payment2 = IntegerTextField.numformat(payment);
        this.totalOrder2 = IntegerTextField.numformat(totalOrder);
    }

    public SupplierListDB() {
    }

}
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
 *
 */
public class PendingDeliveriesDB {

    private int id;
    private Timestamp date;
    private String customer;
    private String product;
    private String invoiceNo;
    private double orderQuantity;
    private double orderUnit;
    private String orderUnitType;
    private double deliveryQuantity;
    private double deliveryUnit;
    private String deliveryUnitType;
    private double pendingQuantity;
    private double pendingUnit;
    private String pendingUnitType;
    private int idBankDepoWith;
    private int idSaleOrder;

    public String getOrderQuantity2() {
        return orderQuantity2;
    }

    public void setOrderQuantity2(String orderQuantity2) {
        this.orderQuantity2 = orderQuantity2;
    }

    public String getOrderUnit2() {
        return orderUnit2;
    }

    public void setOrderUnit2(String orderUnit2) {
        this.orderUnit2 = orderUnit2;
    }

    public String getDeliveryQuantity2() {
        return deliveryQuantity2;
    }

    public void setDeliveryQuantity2(String deliveryQuantity2) {
        this.deliveryQuantity2 = deliveryQuantity2;
    }

    public String getDeliveryUnit2() {
        return deliveryUnit2;
    }

    public void setDeliveryUnit2(String deliveryUnit2) {
        this.deliveryUnit2 = deliveryUnit2;
    }

    public String getPendingQuantity2() {
        return pendingQuantity2;
    }

    public void setPendingQuantity2(String pendingQuantity2) {
        this.pendingQuantity2 = pendingQuantity2;
    }

    public String getPendingUnit2() {
        return pendingUnit2;
    }

    public void setPendingUnit2(String pendingUnit2) {
        this.pendingUnit2 = pendingUnit2;
    }
    
    private String orderQuantity2;
    private String orderUnit2;
    private String deliveryQuantity2;
    private String deliveryUnit2;
    private String pendingQuantity2;
    private String pendingUnit2;
    

    public PendingDeliveriesDB(int id, Timestamp date, String customer, String product, String invoiceNo, double orderQuantity, double orderUnit, String orderUnitType, double deliveryQuantity, double deliveryUnit, String deliveryUnitType, double pendingQuantity, double pendingUnit, String pendingUnitType, int idBankDepoWith, int idSaleOrder) {
        this.id = id;
        this.date = date;
        this.customer = customer;
        
        this.product = product;
        this.invoiceNo = invoiceNo;
        this.orderQuantity = orderQuantity/50;
        this.orderUnit = orderUnit;
        this.orderUnitType = orderUnitType;
        this.deliveryQuantity = (orderQuantity-pendingQuantity)/50;
        this.deliveryUnit = deliveryUnit;
        this.deliveryUnitType = deliveryUnitType;
        this.pendingQuantity = pendingQuantity/50;
        
        this.deliveryQuantity2 = IntegerTextField.doubleformat((orderQuantity-pendingQuantity)/50);
        this.deliveryUnit2 = String.valueOf(deliveryUnit);
        this.orderQuantity2 = IntegerTextField.doubleformat(orderQuantity/50);
        this.orderUnit2 = String.valueOf(orderUnit);
        this.pendingQuantity2 = IntegerTextField.doubleformat(pendingQuantity/50);
        
    }

    public PendingDeliveriesDB() {
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

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getInvoiceNo() {
        return invoiceNo;
    }

    public void setInvoiceNo(String invoiceNo) {
        this.invoiceNo = invoiceNo;
    }

    public double getOrderQuantity() {
        return orderQuantity;
    }

    public void setOrderQuantity(double orderQuantity) {
        this.orderQuantity = orderQuantity;
    }

    public double getOrderUnit() {
        return orderUnit;
    }

    public void setOrderUnit(double orderUnit) {
        this.orderUnit = orderUnit;
    }

    public String getOrderUnitType() {
        return orderUnitType;
    }

    public void setOrderUnitType(String orderUnitType) {
        this.orderUnitType = orderUnitType;
    }

    public double getDeliveryQuantity() {
        return deliveryQuantity;
    }

    public void setDeliveryQuantity(double deliveryQuantity) {
        this.deliveryQuantity = deliveryQuantity;
    }

    public double getDeliveryUnit() {
        return deliveryUnit;
    }

    public void setDeliveryUnit(double deliveryUnit) {
        this.deliveryUnit = deliveryUnit;
    }

    public String getDeliveryUnitType() {
        return deliveryUnitType;
    }

    public void setDeliveryUnitType(String deliveryUnitType) {
        this.deliveryUnitType = deliveryUnitType;
    }

    public double getPendingQuantity() {
        return pendingQuantity;
    }

    public void setPendingQuantity(double pendingQuantity) {
        this.pendingQuantity = pendingQuantity;
    }

    public double getPendingUnit() {
        return pendingUnit;
    }

    public void setPendingUnit(double pendingUnit) {
        this.pendingUnit = pendingUnit;
    }

    public String getPendingUnitType() {
        return pendingUnitType;
    }

    public void setPendingUnitType(String pendingUnitType) {
        this.pendingUnitType = pendingUnitType;
    }

    public int getIdBankDepoWith() {
        return idBankDepoWith;
    }

    public void setIdBankDepoWith(int idBankDepoWith) {
        this.idBankDepoWith = idBankDepoWith;
    }

    public int getIdSaleOrder() {
        return idSaleOrder;
    }

    public void setIdSaleOrder(int idSaleOrder) {
        this.idSaleOrder = idSaleOrder;
    }

}

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
public class UpcomingStockDB {

    
    
    private int id ; 
    private Timestamp date;
    private String supplier;
    private String storage;
    private String product;
    private String orderNo;
    private double orderQuantity;
    private double orderUnit;
    private String orderUnitType;
    private double deliveryQuantity;
    private double deliveryUnit;
    private String deliveryUnitType;
    private double upcomingQuantity;
    private double upcomingUnit;
    private String upcomingUnitType;
    private int idPurchaseOrder;
    private int idPurchaseDelivery ;

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

    public String getUpcomingQuantity2() {
        return upcomingQuantity2;
    }

    public void setUpcomingQuantity2(String upcomingQuantity2) {
        this.upcomingQuantity2 = upcomingQuantity2;
    }

    public String getUpcomingUnit2() {
        return upcomingUnit2;
    }

    public void setUpcomingUnit2(String upcomingUnit2) {
        this.upcomingUnit2 = upcomingUnit2;
    }
    
    private String orderQuantity2;
    private String orderUnit2;
    private String deliveryQuantity2;
    private String deliveryUnit2;
    private String upcomingQuantity2;
    private String upcomingUnit2;
    

    public UpcomingStockDB() {
    }

    public UpcomingStockDB(int id, Timestamp date, String supplier, String storage, String product, String orderNo, double orderQuantity, double orderUnit, String orderUnitType, double deliveryQuantity, double deliveryUnit, String deliveryUnitType, double upcomingQuantity, double upcomingUnit, String upcomingUnitType, int idPurchaseOrder, int idPurchaseDelivery) {
        this.id = id;
        this.date = date;
        this.supplier = supplier;
        this.storage = storage;
        this.product = product;
        this.orderNo = orderNo;
        this.orderQuantity = orderQuantity/50;
        this.orderUnit = orderUnit;
        this.orderUnitType = orderUnitType;
        this.deliveryQuantity = (orderQuantity-upcomingQuantity)/50;
        this.deliveryUnit = deliveryUnit;
        this.deliveryUnitType = deliveryUnitType;
        this.upcomingQuantity = upcomingQuantity/50;
        this.upcomingUnit = upcomingUnit;
        this.upcomingUnitType = upcomingUnitType;
        this.idPurchaseOrder = idPurchaseOrder;
        this.idPurchaseDelivery = idPurchaseDelivery;
        
        this.deliveryQuantity2 = IntegerTextField.doubleformat((orderQuantity-upcomingQuantity)/50);
        this.deliveryUnit2 = String.valueOf(deliveryUnit);
        this.orderQuantity2 = IntegerTextField.doubleformat(orderQuantity/50);
        this.orderUnit2 = String.valueOf(orderUnit);
        this.upcomingQuantity2 = IntegerTextField.doubleformat(upcomingQuantity/50);
        this.upcomingUnit2 = String.valueOf(upcomingUnit);
        
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

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public String getStorage() {
        return storage;
    }

    public void setStorage(String storage) {
        this.storage = storage;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
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

    public double getUpcomingQuantity() {
        return upcomingQuantity;
    }

    public void setUpcomingQuantity(double upcomingQuantity) {
        this.upcomingQuantity = upcomingQuantity;
    }

    public double getUpcomingUnit() {
        return upcomingUnit;
    }

    public void setUpcomingUnit(double upcomingUnit) {
        this.upcomingUnit = upcomingUnit;
    }

    public String getUpcomingUnitType() {
        return upcomingUnitType;
    }

    public void setUpcomingUnitType(String upcomingUnitType) {
        this.upcomingUnitType = upcomingUnitType;
    }

    public int getIdPurchaseOrder() {
        return idPurchaseOrder;
    }

    public void setIdPurchaseOrder(int idPurchaseOrder) {
        this.idPurchaseOrder = idPurchaseOrder;
    }

    public int getIdPurchaseDelivery() {
        return idPurchaseDelivery;
    }

    public void setIdPurchaseDelivery(int idPurchaseDelivery) {
        this.idPurchaseDelivery = idPurchaseDelivery;
    }



}
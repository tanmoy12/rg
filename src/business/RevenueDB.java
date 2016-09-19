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
 * @author razon
 */
public class RevenueDB {

    private int id;
    private Timestamp date;
    private String user;
    private String revenue;
    private String remarks;
    private String bankOrCash;
    private double amount;
    private String lp;

    public String getDate2() {
        return date2;
    }

    public void setDate2(String date2) {
        this.date2 = date2;
    }
    private String date2;

    public String getAmount2() {
        return amount2;
    }
    
    private String amount2;

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

    public String getRevenue() {
        return revenue;
    }

    public void setRevenue(String revenue) {
        this.revenue = revenue;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getBankOrCash() {
        return bankOrCash;
    }

    public void setBankOrCash(String bankOrCash) {
        this.bankOrCash = bankOrCash;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getLp() {
        return lp;
    }

    public void setLp(String lp) {
        this.lp = lp;
    }

    public RevenueDB() {
    }

    public RevenueDB(int id, Timestamp date, String user, String revenue, String remarks, String bankOrCash, double amount, String lp) {
        this.id = id;
        this.date = date;
        this.user = user;
        this.revenue = revenue;
        this.remarks = remarks;
        this.bankOrCash = bankOrCash;
        this.amount = amount;
        this.lp = lp;
        
        this.amount2 = IntegerTextField.doubleformat(amount);
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        this.date2= formatter.format(date);
        
    }
    

}

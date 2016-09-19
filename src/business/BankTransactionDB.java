/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author User
 */
public class BankTransactionDB {

    private int id;
    private double type;
    private Timestamp date;
    private String date2;
    private String user;
    private String bankOrCash;

    public String getDate2() {
        return date2;
    }

    public void setDate2(String date2) {
        this.date2 = date2;
    }
    private String entity;
    private String remarks;
    private double amount;
    private double debit;
    private double credit;
    private String debit2;
    private String credit2;

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
    private double balance;
    private String lp;
    private int idBankDepoWith;
    private String type2;
    private String amount2;
    private String balance2;
    private int idExpense;
    private int idRevenue;

    public String getType2() {
        return type2;
    }

    public void setType2(String type2) {
        this.type2 = type2;
    }

    public String getAmount2() {
        return amount2;
    }

    public void setAmount2(String amount2) {
        this.amount2 = amount2;
    }

    public String getBalance2() {
        return balance2;
    }

    public void setBalance2(String balance2) {
        this.balance2 = balance2;
    }

    public int getIdExpense() {
        return idExpense;
    }

    public void setIdExpense(int idExpense) {
        this.idExpense = idExpense;
    }

    public int getIdRevenue() {
        return idRevenue;
    }

    public void setIdRevenue(int idRevenue) {
        this.idRevenue = idRevenue;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getType() {
        return type;
    }

    public void setType(double type) {
        this.type = type;
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

    public String getBankOrCash() {
        return bankOrCash;
    }

    public void setBankOrCash(String bankOrCash) {
        this.bankOrCash = bankOrCash;
    }

    public String getEntity() {
        return entity;
    }

    public void setEntity(String entity) {
        this.entity = entity;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
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

    public BankTransactionDB() {
    }

    public BankTransactionDB(int id, double type, Timestamp date, String user, String bankName, String bankOrCash, String entity, String remarks, double amount, double balance, String lp, int idBankDepoWith, int idExpense, int idRevenue) throws ParseException {
        this.id = id;
        this.type = type;
        this.date = date;
        this.user = user;

        this.bankOrCash = bankOrCash;
        this.entity = entity;
        this.remarks = remarks;
        this.amount = amount;
        this.balance = balance;
        this.lp = lp;
        this.idBankDepoWith = idBankDepoWith;
        this.idExpense = idExpense;
        this.idRevenue = idRevenue;

        if (this.amount >= 0) {
            this.debit = this.amount;
            this.debit2 = IntegerTextField.numformat(this.debit);
        } else {
            this.credit = -(this.amount);
            this.credit2 = IntegerTextField.numformat(this.credit);
        }

        this.amount2 = IntegerTextField.numformat(amount);
        this.balance2 = String.valueOf(balance);
        this.type2 = String.valueOf(type);
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        this.date2= formatter.format(date);
       

    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

/**
 *
 * @author Mithu
 */
public class BalanceSheetDB {

    private String entity;
    private String remark;
    private int amount;
    private String amount2;

    public String getEntity() {
        return entity;
    }

    public void setEntity(String entity) {
        this.entity = entity;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getAmount2() {
        return amount2;
    }

    public void setAmount2(String amount2) {
        this.amount2 = amount2;
    }

    public BalanceSheetDB() {
    }

    public BalanceSheetDB(String entity, String remark, int amount, String amount2) {
        this.entity = entity;
        this.remark = remark;
        this.amount = amount;
        this.amount2 = amount2;
    }
}

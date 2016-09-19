/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import java.sql.Timestamp;

/**
 *
 * @author strings
 */
public class UsersDB {
    
    private int id;
    private Timestamp date;
    private String username;
    private String password;
    private String identifiedby;
    private String type2;
    private double type;
    
    public UsersDB
        (int id, Timestamp date, String username, String password, String identifiedby, double type) {
        this.id = id;
        this.date = date;
        this.username = username;
        this.password = password;
        this.identifiedby = identifiedby;
        this.type = type;
        
        System.out.println("OB CREATED"+ this.username);
        //if (type==1.0) this.type2="ADMIN";
        //else if (type==2.0) this.type2="EMPLOYEE";
    }

    public UsersDB() {
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getIdentifiedby() {
        return identifiedby;
    }

    public void setIdentifiedby(String identifiedby) {
        this.identifiedby = identifiedby;
    }

    public double getType() {
        return type;
    }

    public void setType(double type) {
        this.type = type;
    }
    
    

    public String getType2() {
        return type2;
    }

    public void setType2(String type2) {
        this.type2 = type2;
    }
    
}

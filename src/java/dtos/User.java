/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dtos;

/**
 *
 * @author takah
 */
public class User {
    private String uid;
    private String username;
    private String password;
    private String fullname;

    public User(String uid, String username, String password, String fullname) {
        this.uid = uid;
        this.username = username;
        this.password = password;
        this.fullname = fullname;
    }

    public User(String uid, String username, String fullname) {
        this.uid = uid;
        this.username = username;
        this.fullname = fullname;
    }
    
    public User() {
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
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

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public User(String username, String fullname) {
        this.username = username;
        this.fullname = fullname;
    }
    
    
}
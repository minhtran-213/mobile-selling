/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dtos;

import java.io.Serializable;

/**
 *
 * @author takah
 */
public class Phone implements Serializable{
    private String id;
    private String name;
    private String descript;
    private Brand brand;
    private String imgURL;
    private double price;
    private int quantity;

    public Phone(String id, String name, String descript, Brand brand, String imgURL, double price) {
        this.id = id;
        this.name = name;
        this.descript = descript;
        this.brand = brand;
        this.imgURL = imgURL;
        this.price = price;
    }

    public Phone() {
    }

    public String getId() {
        return id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescript() {
        return descript;
    }

    public void setDescript(String descript) {
        this.descript = descript;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrandID(Brand brand) {
        this.brand = brand;
    }

    public String getImgURL() {
        return imgURL;
    }

    public void setImgURL(String imgURL) {
        this.imgURL = imgURL;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Phone(String name, double price) {
        this.name = name;
        this.price = price;
    }
    
}

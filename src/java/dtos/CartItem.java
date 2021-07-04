/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtos;

import java.io.Serializable;

/**
 *
 * @author ihtng
 */
public class CartItem implements Serializable{

    private Phone phone;
    public int quantity;
    public double totalCost;

    public CartItem() {
    }

    public CartItem(Phone phone, int quantity) {
        this.phone = phone;
        this.quantity = quantity;
    }

    public CartItem(Phone phone, int quantity, double totalCost) {
        this.phone = phone;
        this.quantity = quantity;
        this.totalCost = totalCost;
    }

    public CartItem(Phone phone) {
        this.phone = phone;
    }

    public Phone getPhone() {
        return phone;
    }

    public void setPhone(Phone phone) {
        this.phone = phone;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }
    
    

}

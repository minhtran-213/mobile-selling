/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtos;

import daos.PhoneDAO;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author takah
 */
public class Cart implements Serializable {

//    private Map<String, CartItem> items;
//    double price = 0;
//
//    public Map<String, CartItem> getItems() {
//        return items;
//    }
//
//    public double getPrice() {
//        double price = 0;
//        for (CartItem item : items.values()) {
//            price += item.getPhone().getPrice();
//        }
//        return price;
//    }
//
//    public void addPhone(String name){
//        if (this.items == null){
//            items = new HashMap();
//        }
//        
//        int quantity = 1;
//        if (this.items.containsKey(name)){
//            quantity = this.items.get(name) + 1;
//        }
//        
////        price = quantity * price;
//        this.items.put(name, quantity);
//    }
//
//    public void removePhone(String name) {
//        if (this.items == null) {
//            return;
//        }
//
//        if (this.items.containsKey(name)) {
//            this.items.remove(name);
//
//            if (this.items.isEmpty()) {
//                this.items = null;
//            }
//        }
//    }
    
    ArrayList<CartItem> itemList;
    
    public ArrayList<CartItem> getItems() {
        return itemList;
    }
    
    public void addPhone (Phone phone) throws Exception {
        try {
//        PhoneDAO dao = new PhoneDAO();
//        Phone phone = dao.getPhonebyName(name);
        CartItem item = new CartItem(phone, 1, phone.getPrice());
        if (itemList == null) {
            itemList = new ArrayList<CartItem>();
        }
        if (itemList.contains(item)) {
            item.setQuantity(item.getQuantity() + 1);
            item.setTotalCost(phone.getPrice() * item.getQuantity());
            return;
        }
        
        item.setTotalCost(phone.getPrice() * item.getQuantity());
        itemList.add(item);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void removePhone(String name) throws Exception{
              PhoneDAO dao = new PhoneDAO();
        Phone phone = dao.getPhonebyName(name);
        CartItem item = new CartItem(phone);
        if (itemList == null) {
            return;
        }
        
        if (itemList.contains(item)) {
            itemList.remove(item);
            if (itemList.isEmpty())
                itemList = null;
        }
    }
}

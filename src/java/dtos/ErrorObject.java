/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtos;

import java.io.Serializable;

/**
 *
 * @author Admin
 */
public class ErrorObject implements Serializable{
    private String idError, nameError, descriptError, imgURLError, priceError;

    public ErrorObject() {
    }

    public String getIdError() {
        return idError;
    }

    public void setIdError(String idError) {
        this.idError = idError;
    }

    public String getNameError() {
        return nameError;
    }

    public void setNameError(String nameError) {
        this.nameError = nameError;
    }

    public String getDescriptError() {
        return descriptError;
    }

    public void setDescriptError(String descriptError) {
        this.descriptError = descriptError;
    }

    public String getImgURLError() {
        return imgURLError;
    }

    public void setImgURLError(String imgURLError) {
        this.imgURLError = imgURLError;
    }

    public String getPriceError() {
        return priceError;
    }

    public void setPriceError(String priceError) {
        this.priceError = priceError;
    }
    
    
}

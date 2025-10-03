/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jenbumapps.core.model;


import com.jenbumapps.core.model.codec.FormType;

import org.parceler.Parcel;

/**
 *
 * @author Mainong
 */
@Parcel
public class Terms {
    
    private int id;
    private FormType formType;
    private String condition;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public FormType getFormType() {
        return formType;
    }

    public void setFormType(FormType formType) {
        this.formType = formType;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jenbumapps.core.model;

import org.parceler.Parcel;

/**
 *
 * @author Mainong
 */
@Parcel
public class EPassTerm {
    
    private int id;
    private int ePassId;
    private Terms term;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getePassId() {
        return ePassId;
    }

    public void setePassId(int ePassId) {
        this.ePassId = ePassId;
    }

    public Terms getTerm() {
        return term;
    }

    public void setTerm(Terms term) {
        this.term = term;
    }
    
}

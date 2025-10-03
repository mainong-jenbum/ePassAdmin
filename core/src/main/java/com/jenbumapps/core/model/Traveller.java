package com.jenbumapps.core.model;

import com.jenbumapps.core.model.codec.TravellerType;

import org.parceler.Parcel;

@Parcel
public class Traveller {
    private int id;
    private int formId;
    private String name;
    private String idProof;
    private TravellerType type;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFormId() {
        return formId;
    }

    public void setFormId(int formId) {
        this.formId = formId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdProof() {
        return idProof;
    }

    public void setIdProof(String idProof) {
        this.idProof = idProof;
    }
    
    

    public TravellerType getType() {
        return type;
    }

    public void setType(TravellerType type) {
        this.type = type;
    }
}

package com.jenbumapps.core.model;

import org.parceler.Parcel;

@Parcel
public class ApplicantDetail {
    private String name;
    private long contact;
    private String address;
    private File idProof;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getContact() {
        return contact;
    }

    public void setContact(long contact) {
        this.contact = contact;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public File getIdProof() {
        return idProof;
    }

    public void setIdProof(File idProof) {
        this.idProof = idProof;
    }

    
    
    
}

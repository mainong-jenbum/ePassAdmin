package com.jenbumapps.core.model;

import com.jenbumapps.core.model.codec.UserStatus;

import org.parceler.Parcel;

@Parcel
public class User {
    private int id;
    private String name;
    private long phone;
    private String password;
    private String designationAbbr;
    private String designation;
    private String signatureUrl;
    private String address;
    private City city;

    private UserStatus status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getPhone() {
        return phone;
    }

    public void setPhone(long phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDesignationAbbr() {
        return designationAbbr;
    }

    public void setDesignationAbbr(String designationAbbr) {
        this.designationAbbr = designationAbbr;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getSignatureUrl() {
        return signatureUrl;
    }

    public void setSignatureUrl(String signatureUrl) {
        this.signatureUrl = signatureUrl;
    }



    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }
}

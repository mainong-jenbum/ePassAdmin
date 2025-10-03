package com.jenbumapps.core.model;

import org.parceler.Parcel;

@Parcel
public class AuthorityDetail {
    private String authorityName;
    private String authorityDesignationAbbr;
    private String authorityDesignation;
    private String authorityAddress;
    private String authoritySign;

    public String getAuthorityName() {
        return authorityName;
    }

    public void setAuthorityName(String authorityName) {
        this.authorityName = authorityName;
    }

    public String getAuthorityDesignationAbbr() {
        return authorityDesignationAbbr;
    }

    public void setAuthorityDesignationAbbr(String authorityDesignationAbbr) {
        this.authorityDesignationAbbr = authorityDesignationAbbr;
    }

    public String getAuthorityDesignation() {
        return authorityDesignation;
    }

    public void setAuthorityDesignation(String authorityDesignation) {
        this.authorityDesignation = authorityDesignation;
    }

    public String getAuthorityAddress() {
        return authorityAddress;
    }

    public void setAuthorityAddress(String authorityAddress) {
        this.authorityAddress = authorityAddress;
    }

    public String getAuthoritySign() {
        return authoritySign;
    }

    public void setAuthoritySign(String authoritySign) {
        this.authoritySign = authoritySign;
    }
}

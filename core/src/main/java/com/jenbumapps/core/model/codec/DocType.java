/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jenbumapps.core.model.codec;

/**
 *
 * @author Mainong
 */
public enum DocType {
    APPLICANT_ID_PROOF(0), APPLICATION(1), MEDICAL_DOCUMENT(2), VEHICLE_RC(3), OTHER(4);
    
    private int code;
    DocType(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
    
    public static DocType valueOf(int code) {
        switch(code) {
            case 0: return APPLICANT_ID_PROOF;
            case 1: return APPLICATION;
            case 2: return MEDICAL_DOCUMENT;
            case 3: return VEHICLE_RC;
            case 4: 
            default: return OTHER;
            
        }
    }

    @Override
    public String toString() {
        switch(this) {
            case APPLICANT_ID_PROOF: return "APPLICANT ID PROOF";
            case APPLICATION: return "APPLICATION";
            case MEDICAL_DOCUMENT: return "MEDICAL DOCUMENT";
            case VEHICLE_RC: return "VEHICLE RC";
            case OTHER: 
            default: return "OTHER";
            
        }
    }
    
    
}

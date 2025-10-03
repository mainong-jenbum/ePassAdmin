/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jenbumapps.core.model;

import org.parceler.Parcel;

import java.util.List;

/**
 *
 * @author Mainong
 */
@Parcel
public class DocumentDetail {
    private File application;
    private File medicalDocument;
    private File vehicleRc;
    private List<File> otherSupportingDocuments;

    public File getApplication() {
        return application;
    }

    public void setApplication(File application) {
        this.application = application;
    }

    public File getMedicalDocument() {
        return medicalDocument;
    }

    public void setMedicalDocument(File medicalDocument) {
        this.medicalDocument = medicalDocument;
    }
    
    public File getVehicleRc() {
        return vehicleRc;
    }

    public void setVehicleRc(File vehicleRc) {
        this.vehicleRc = vehicleRc;
    }

    public List<File> getOtherSupportingDocuments() {
        return otherSupportingDocuments;
    }

    public void setOtherSupportingDocuments(List<File> otherSupportingDocuments) {
        this.otherSupportingDocuments = otherSupportingDocuments;
    }
    
    
    
}

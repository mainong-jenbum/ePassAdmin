/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jenbumapps.core.model;


import com.jenbumapps.core.model.codec.FileType;
import com.jenbumapps.core.model.codec.DocType;

import org.parceler.Parcel;

/**
 *
 * @author Mainong
 */
@Parcel
public class File {
    private int id;
    private FileType type;
    private DocType docType;
    private String url;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public FileType getType() {
        return type;
    }

    public void setType(FileType type) {
        this.type = type;
    }

    public DocType getDocType() {
        return docType;
    }

    public void setDocType(DocType docType) {
        this.docType = docType;
    }
    

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    
}

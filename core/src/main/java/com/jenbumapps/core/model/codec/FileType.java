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
public enum FileType {
    IMAGE(0), PDF(1), WORD(2), EXCEL(3);

    private int code;
    FileType(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
    
    public static FileType valueOf(int code) {
        switch(code) {
            default:
            case 0: return IMAGE;
            case 1: return PDF;
            case 2: return WORD;
            case 3: return EXCEL;
        }
    }
    
    
}

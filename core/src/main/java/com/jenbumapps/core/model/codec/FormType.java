package com.jenbumapps.core.model.codec;

import androidx.annotation.NonNull;

public enum  FormType {
    SELECT_FORM_TYPE(0), MEDICAL(1), ESSENTIAL_GOODS(2), TEA_ARECA_NUT(3), CONSTRUCTION_MATERIAL(4), LABOUR_STUDENT(5), INTRA_ARUNACHAL_PASS(6);

    private int code;
    FormType(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    @NonNull
    @Override
    public String toString() {
        switch (this) {
            case MEDICAL: return "MEDICAL";
            case ESSENTIAL_GOODS: return "ESSENTIAL GOODS";
            case TEA_ARECA_NUT: return "TEA or ARECA NUT";
            case CONSTRUCTION_MATERIAL: return "CONSTRUCTION MATERIAL";
            case LABOUR_STUDENT: return "LABOUR or STUDENT";
            case INTRA_ARUNACHAL_PASS: return "INTRA ARUNACHAL PASS";

            default: return "Select a form type";
        }
    }
}

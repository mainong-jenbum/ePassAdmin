package com.jenbumapps.core.model.codec;

import androidx.annotation.NonNull;

public enum UserStatus {
    INACTIVE(0), ACTIVE(1), SELF_DEACTIVATE(2);

    private int code;
    UserStatus(int code){
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static UserStatus valueOf(int code) {
        switch (code) {
            default:
            case 0: return INACTIVE;

            case 1: return ACTIVE;

            case 2: return SELF_DEACTIVATE;
        }
    }

    @NonNull
    @Override
    public String toString() {
        switch (this) {
            default:
            case ACTIVE: return "ACTIVE";

            case INACTIVE: return "INACTIVE";

            case SELF_DEACTIVATE: return "SELF DEACTIVATE";
        }
    }
}

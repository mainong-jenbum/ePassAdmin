package com.jenbumapps.core.model.codec;

public enum ApproveStatus {
    PENDING(0), APPROVED(1), REJECTED(2);

    private int code;
    ApproveStatus(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}

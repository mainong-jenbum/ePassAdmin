package com.jenbumapps.core.model.codec;

public enum TravellerType {

    SELECT_TRAVELLER_TYPE(0),PATIENT(1), ATTENDANT(2), DRIVER(3), PASSENGER(4), HANDYMAN(5);

    private int code;

    TravellerType(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static TravellerType valueOf(int code){
        switch (code){
            default:
            case 0 : return SELECT_TRAVELLER_TYPE;
            case 1 : return PATIENT;
            case 2 : return ATTENDANT;
            case 3 : return DRIVER;
            case 4 : return PASSENGER;
            case 5 : return HANDYMAN;

        }
    }

    @Override
    public String toString() {
        switch (this){
            default:
            case SELECT_TRAVELLER_TYPE: return "Select Traveller Type";
            case PATIENT: return "PATIENT";
            case ATTENDANT: return "ATTENDANT";
            case DRIVER:return "DRIVER";
            case PASSENGER:return "PASSENGER";
            case HANDYMAN:return "HANDYMAN";
        }
    }
}

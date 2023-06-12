package com.example.onlineshopphicen.model;

public enum DeliveryMethod {

    CDEK("CDEK"),
    POST_RUSSIAN("ПОЧТА РОССИИ");

    private final String displayValue;

    private DeliveryMethod(String displayValue) {
        this.displayValue = displayValue;
    }

    public String getDisplayValue(){
        return displayValue;
    }

}

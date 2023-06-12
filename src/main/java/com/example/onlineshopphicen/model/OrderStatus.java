package com.example.onlineshopphicen.model;

public enum OrderStatus {

    NEW("НОВЫЙ"),
    APPROVED("ПРИНЯТ"),
    CANCEL("ОТМЕНЁН"),
    PAID("ОПЛАЧЕН"),
    SENT("ОТПРАВЛЕН"),
    CLOSED("ЗАКРЫТ");

    private final String displayValue;

    private OrderStatus(String displayValue) {
        this.displayValue = displayValue;
    }

    public String getDisplayValue(){
        return displayValue;
    }

}

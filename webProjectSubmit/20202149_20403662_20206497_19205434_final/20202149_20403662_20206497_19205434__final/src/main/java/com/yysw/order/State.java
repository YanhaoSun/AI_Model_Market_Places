package com.yysw.order;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

public enum State {
    @Enumerated(EnumType.STRING)
    NEW("New"),
    @Enumerated(EnumType.STRING)
    DELIVERED("Delivered"),
    @Enumerated(EnumType.STRING)
    CANCELLED("Cancelled");

    private final String displayValue;

    State(String displayValue) { this.displayValue = displayValue; }

    public String getDisplayValue() { return displayValue; }
}



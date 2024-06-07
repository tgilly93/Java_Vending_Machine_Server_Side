package com.techelevator.model;

import java.math.BigDecimal;

public class Money {
    private BigDecimal value;

    public Money(BigDecimal value) {
        this.value = value;
    }

    public BigDecimal getValue() {
        return value;
    }

    public BigDecimal updateMoney(BigDecimal value) {
    return this.value.add(value);
    }
}

package com.techelevator.model;


import java.math.BigDecimal;

public class Bank {
    private BigDecimal totalMoney;
    private BigDecimal totalAmountInserted;
    public Bank() {
        totalMoney = BigDecimal.valueOf(0.00);
        totalAmountInserted = BigDecimal.valueOf(0.00);
    }
    public void addCoin(BigDecimal money) {
        Money money1 = new Money(money);
        totalMoney = totalMoney.add(money1.getValue());
        totalAmountInserted = totalAmountInserted.add(money1.getValue());
    }
    public BigDecimal getTotalMoney() {
        return totalMoney;
    }
    public BigDecimal getTotalAmountInserted() {
        return totalAmountInserted;
    }
}
package com.techelevator.model;


import java.math.BigDecimal;

public class Bank {
    private BigDecimal totalMoney;
    private BigDecimal totalAmountInserted;
    public Bank() {
        totalMoney = BigDecimal.valueOf(0.00);
        totalAmountInserted = BigDecimal.valueOf(0.00);
    }
    public void addCoin(Money money) {
        totalMoney = totalMoney.add(money.getValue());
        totalAmountInserted = totalAmountInserted.add(money.getValue());
    }
    public BigDecimal getTotalMoney() {
        return totalMoney;
    }
    public BigDecimal getTotalAmountInserted() {
        return totalAmountInserted;
    }
}

//Take in money method
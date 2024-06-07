package com.techelevator.model;


import java.math.BigDecimal;

public class Bank {
    private BigDecimal totalMoney;

    private Money moneyInserted;
    private Money bankTotal;    private BigDecimal totalAmountInserted;
    public Bank() {
        totalMoney = BigDecimal.valueOf(0.00);
        totalAmountInserted = BigDecimal.valueOf(0.00);

    }
    public void addCoin(Money money) {
        totalMoney = totalMoney.add(money.getValue());
        totalAmountInserted = totalAmountInserted.add(money.getValue());
    }
    public Money getTotalMoney() {
        this.bankTotal = new Money(totalMoney);
        return bankTotal;
    }
    public Money getTotalAmountInserted() {
        this.moneyInserted = new Money(totalAmountInserted);
        return moneyInserted;
    }
}
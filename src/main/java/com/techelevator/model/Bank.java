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
    public void addCoin(BigDecimal money) {
        Money money1 = new Money(money);
        totalMoney = totalMoney.add(money1.getValue());
        totalAmountInserted = totalAmountInserted.add(money1.getValue());
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
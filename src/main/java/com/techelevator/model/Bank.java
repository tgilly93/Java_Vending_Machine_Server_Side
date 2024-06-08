package com.techelevator.model;


import java.math.BigDecimal;

public class Bank {
    private BigDecimal totalMoney;

    private Money moneyInserted;
    private BigDecimal bankTotal;
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
        return bankTotal;
    }
    public Money getTotalAmountInserted() {
        this.moneyInserted = new Money(totalAmountInserted);
        return moneyInserted;
    }

    public void setBankTotal(BigDecimal bankTotal) {
        this.bankTotal = bankTotal;
    }
    public void clearInserted(){
        this.moneyInserted.setValue(BigDecimal.ZERO);
    }
}

//Take in money method
package com.techelevator;

import com.techelevator.model.Bank;
import com.techelevator.model.Money;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

public class BankTest {

    private Bank bank;

    @Before
    public void setUp() {
        bank = new Bank();
    }

    @Test
    public void testInitialState() {
        // Arrange
        // Act
        BigDecimal totalMoney = bank.getTotalMoney();
        Money totalAmountInserted = bank.getTotalAmountInserted();

        // Assert
        Assert.assertEquals(BigDecimal.valueOf(0.00), totalMoney);
        Assert.assertEquals(BigDecimal.valueOf(0.00), totalAmountInserted.getValue());
    }

    @Test
    public void testAddCoin() {
        // Arrange
        BigDecimal coin = BigDecimal.valueOf(1.00);

        // Act
        bank.addCoin(coin);
        BigDecimal totalMoney = bank.getTotalMoney();
        Money totalAmountInserted = bank.getTotalAmountInserted();

        // Assert
        Assert.assertEquals(coin, totalMoney);
        Assert.assertEquals(coin, totalAmountInserted.getValue());
    }

    @Test
    public void testGetTotalAmountInserted() {
        // Arrange
        BigDecimal coin1 = BigDecimal.valueOf(1.00);
        BigDecimal coin2 = BigDecimal.valueOf(0.50);

        // Act
        bank.addCoin(coin1);
        bank.addCoin(coin2);
        Money totalAmountInserted = bank.getTotalAmountInserted();

        // Assert
        Assert.assertEquals(BigDecimal.valueOf(1.50), totalAmountInserted.getValue());
    }

    @Test
    public void testSetBankTotal() {
        // Arrange
        BigDecimal newBankTotal = BigDecimal.valueOf(100.00);

        // Act
        bank.setBankTotal(newBankTotal);
        BigDecimal totalMoney = bank.getTotalMoney();

        // Assert
        Assert.assertEquals(newBankTotal, totalMoney);
    }

    @Test
    public void testClearInserted() {
        // Arrange
        BigDecimal coin1 = BigDecimal.valueOf(1.00);
        BigDecimal coin2 = BigDecimal.valueOf(0.50);
        bank.addCoin(coin1);
        bank.addCoin(coin2);

        // Act
        bank.clearInserted();
        Money totalAmountInserted = bank.getTotalAmountInserted();

        // Assert
        Assert.assertEquals(BigDecimal.valueOf(0.00), totalAmountInserted.getValue());
    }
}

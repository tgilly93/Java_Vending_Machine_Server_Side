import com.techelevator.model.Money;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.math.BigDecimal;

public class MoneyTest {

    private Money money;

    @Before
    public void setUp() {
        // Arrange
        money = new Money(new BigDecimal("100.00"));
    }

    @Test
    public void testConstructor() {
        // Arrange
        BigDecimal initialAmount = new BigDecimal("100.00");

        // Act
        Money money = new Money(initialAmount);

        // Assert
        Assert.assertNotNull("Money object should not be null", money);
        Assert.assertEquals("Initial value should be 100.00", initialAmount, money.getValue());
    }

    @Test
    public void testGetValue() {
        // Arrange is handled in setUp method

        // Act
        BigDecimal value = money.getValue();

        // Assert
        Assert.assertEquals("Value should be 100.00", new BigDecimal("100.00"), value);
    }

    @Test
    public void testUpdateMoney() {
        // Arrange
        BigDecimal updateAmount = new BigDecimal("30.00");
        BigDecimal expectedValue = new BigDecimal("70.00");

        // Act
        BigDecimal newValue = money.updateMoney(updateAmount);

        // Assert
        Assert.assertEquals("Updated value should be 70.00", expectedValue, newValue);
        Assert.assertEquals("Value should be 70.00 after update", expectedValue, money.getValue());
    }

    @Test
    public void testToString() {
        // Arrange is handled in setUp method

        // Act
        String stringValue = money.toString();

        // Assert
        Assert.assertEquals("String representation should be 100.00", "100.00", stringValue);
    }

    @Test
    public void testSetValue() {
        // Arrange
        BigDecimal newValue = new BigDecimal("200.00");

        // Act
        money.setValue(newValue);

        // Assert
        Assert.assertEquals("Value should be 200.00 after setting new value", newValue, money.getValue());
    }
}

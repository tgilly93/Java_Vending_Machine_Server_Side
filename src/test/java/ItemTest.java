import com.techelevator.model.Item;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

public class ItemTest {

    private Item item;

    @Before
    public void setUp() {
        // Arrange: Initialize the item object
        item = new Item();
    }

    @Test
    public void testSetAndGetLocation() {
        // Arrange
        String expectedLocation = "A1";

        // Act
        item.setLocation(expectedLocation);
        String actualLocation = item.getLocation();

        // Assert
        Assert.assertEquals(expectedLocation, actualLocation);
    }

    @Test
    public void testSetAndGetName() {
        // Arrange
        String expectedName = "Soda";

        // Act
        item.setName(expectedName);
        String actualName = item.getName();

        // Assert
        Assert.assertEquals(expectedName, actualName);
    }

    @Test
    public void testSetAndGetType() {
        // Arrange
        String expectedType = "Drink";

        // Act
        item.setType(expectedType);
        String actualType = item.getType();

        // Assert
        Assert.assertEquals(expectedType, actualType);
    }

    @Test
    public void testSetAndGetCost() {
        // Arrange
        BigDecimal expectedCost = new BigDecimal("1.50");

        // Act
        item.setCost(expectedCost);
        BigDecimal actualCost = item.getCost();

        // Assert
        Assert.assertEquals(expectedCost, actualCost);
    }

    @Test
    public void testSetAndGetInventoryCount() {
        // Arrange
        int expectedInventoryCount = 10;

        // Act
        item.setInventoryCount(expectedInventoryCount);
        int actualInventoryCount = item.getInventoryCount();

        // Assert
        Assert.assertEquals(expectedInventoryCount, actualInventoryCount);
    }

    @Test
    public void testToString() {
        // Arrange
        String expectedLocation = "A1";
        String expectedName = "Soda";
        BigDecimal expectedCost = new BigDecimal("1.50");
        int expectedInventoryCount = 10;

        item.setLocation(expectedLocation);
        item.setName(expectedName);
        item.setCost(expectedCost);
        item.setInventoryCount(expectedInventoryCount);

        String expectedString = "Location: A1 - Item: Soda - Price: $1.5 - x10";

        // Act
        String actualString = item.toString();

        // Assert
        Assert.assertEquals(expectedString, actualString);
    }
}

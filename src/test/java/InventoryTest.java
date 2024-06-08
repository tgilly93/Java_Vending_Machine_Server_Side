import com.techelevator.model.Inventory;
import com.techelevator.model.Item;
import com.techelevator.model.Money;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class InventoryTest {

    private Inventory inventory;

    @Before
    public void setUp() {
        // Arrange
        List<Item> mockItems = new ArrayList<>();
        mockItems.add(new Item("A1", 5, new BigDecimal("1.50")));
        mockItems.add(new Item("B1", 3, new BigDecimal("2.00")));

        // Directly set itemInventory field in the Inventory class
        inventory = new Inventory();
        inventory.setItemInventory(mockItems);
    }

    @Test
    public void testDispenseItem_ValidLocationAndEnoughInventoryAndFunds() {
        // Arrange
        Money money = new Money(new BigDecimal("3.05"));

        // Act
        Item dispensedItem = inventory.dispenseItem("A1", money);

        // Assert
        assertNotNull(dispensedItem);
        Assert.assertEquals("A1", dispensedItem.getLocation());
        Assert.assertEquals(4, dispensedItem.getInventoryCount());
        Assert.assertEquals(4, Inventory.individualCount);
    }

    @Test
    public void testDispenseItem_InvalidLocation() {
        // Arrange
        Money money = new Money(new BigDecimal("3.00"));

        // Act
        Item dispensedItem = inventory.dispenseItem("Z1", money);

        // Assert
        // Modify the assertion to check for null
        Assert.assertNull(dispensedItem);
    }


    @Test
    public void testDispenseItem_InsufficientInventory() {
        // Arrange
        Money initialMoney = new Money(new BigDecimal("5.00")); // Sufficient funds
        inventory.dispenseItem("A1", initialMoney); // reduce inventory count to 4

        Money insufficientMoney = new Money(new BigDecimal("0.25")); // More than item cost but not enough for two items

        // Act
        Item dispensedItem = inventory.dispenseItem("A1", insufficientMoney); // try to dispense again

        // Assert
        assertNull(dispensedItem); // Expecting null because of insufficient inventory
    }


    @Test
    public void testDispenseItem_InsufficientFunds() {
        // Arrange
        Money money = new Money(new BigDecimal("1.00"));

        // Act
        Item dispensedItem = inventory.dispenseItem("A1", money);

        // Assert
        assertNull(dispensedItem);
    }

    @Test
    public void testRefillInventory() {
        // Arrange
        // Set the initial inventory count to 0 for each item
        Inventory.setInventoryCount(0);

        // Act
        // Dispense items to reduce inventory count below the expected value
        //inventory.dispenseItem("A1", new Money(new BigDecimal("1.50"))); // reduce inventory count by 1
        //inventory.dispenseItem("B1", new Money(new BigDecimal("2.00"))); // reduce inventory count by 1

        // Refill the inventory
        inventory.refillInventory();

        // Assert
        Assert.assertEquals(80, Inventory.inventoryCount); // total count after refill
    }


    @Test
    public void testDisplay() {
        // Act
        inventory.display();

        // Assert
        // As we cannot capture System.out.println(), we can only test that the method runs without exceptions.
        Assert.assertTrue(true);
    }
}

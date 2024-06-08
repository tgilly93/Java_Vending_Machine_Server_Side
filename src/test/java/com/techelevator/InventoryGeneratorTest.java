package com.techelevator;

import com.techelevator.generators.InventoryGenerator;
import com.techelevator.model.Item;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

public class InventoryGeneratorTest {

    @Test
    public void testGetListOfItemsFromFile_Success() throws IOException {
        // Arrange
        File tempFile = File.createTempFile("vendingmachine", ".csv");
        try (FileWriter writer = new FileWriter(tempFile)) {
            writer.write("A1|Chips|1.50|Snack\n");
            writer.write("B1|Soda|2.00|Drink\n");
        }

        // Act
        List<Item> items = InventoryGenerator.getListOfItemsFromFile(tempFile.getAbsolutePath());

        // Assert
        Assert.assertNotNull(items);
        Assert.assertEquals(2, items.size());

        Item firstItem = items.get(0);
        Assert.assertEquals("A1", firstItem.getLocation());
        Assert.assertEquals("Chips", firstItem.getName());
        Assert.assertEquals(new BigDecimal("1.50"), firstItem.getCost());
        Assert.assertEquals("Snack", firstItem.getType());

        Item secondItem = items.get(1);
        Assert.assertEquals("B1", secondItem.getLocation());
        Assert.assertEquals("Soda", secondItem.getName());
        Assert.assertEquals(new BigDecimal("2.00"), secondItem.getCost());
        Assert.assertEquals("Drink", secondItem.getType());

        // Clean up
        tempFile.deleteOnExit();
    }

    @Test
    public void testGetListOfItemsFromFile_FileNotFound() {
        // Arrange
        String nonexistentFilePath = "nonexistent.csv";

        // Act
        List<Item> items = InventoryGenerator.getListOfItemsFromFile(nonexistentFilePath);

        // Assert
        Assert.assertNotNull(items);
        Assert.assertTrue(items.isEmpty());
    }

    @Test
    public void testGetListOfItemsFromFile_FileNotReadable() throws IOException {
        // Arrange
        File tempFile = File.createTempFile("vendingmachine", ".csv");
        tempFile.setReadable(false);

        try {
            // Act
            List<Item> items = InventoryGenerator.getListOfItemsFromFile(tempFile.getAbsolutePath());
            Assert.fail("Expected RuntimeException not thrown");
        } catch (RuntimeException e) {
            // Assert
            Assert.assertTrue(e.getCause() instanceof FileNotFoundException);
        } finally {
            // Clean up
            tempFile.setReadable(true);
            tempFile.deleteOnExit();
        }
    }

    @Test
    public void testGetInventoryFile_NonExistent() {
        // Arrange
        String nonexistentFilePath = "nonexistent.csv";

        // Act
        File result = InventoryGenerator.getInventoryFile(nonexistentFilePath);

        // Assert
        Assert.assertNull(result);
    }

    @Test
    public void testGetInventoryFile_NotAFile() {
        // Arrange
        File tempDirectory = new File("tempDirectory");
        if (tempDirectory.mkdir()) {
            tempDirectory.deleteOnExit();
        }

        // Act
        File result = InventoryGenerator.getInventoryFile(tempDirectory.getAbsolutePath());

        // Assert
        Assert.assertNull(result);
    }

    @Test
    public void testGetInventoryFile_ValidFile() throws IOException {
        // Arrange
        File tempFile = File.createTempFile("vendingmachine", ".csv");

        // Act
        File result = InventoryGenerator.getInventoryFile(tempFile.getAbsolutePath());

        // Assert
        Assert.assertNotNull(result);
        Assert.assertEquals(tempFile.getAbsolutePath(), result.getAbsolutePath());

        // Clean up
        tempFile.deleteOnExit();
    }
}



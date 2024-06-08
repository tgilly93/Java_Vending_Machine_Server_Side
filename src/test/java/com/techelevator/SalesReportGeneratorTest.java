package com.techelevator;

import com.techelevator.generators.SalesReportGenerator;
import org.junit.Assert;
import org.junit.Test;
import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.util.Map;

public class SalesReportGeneratorTest {

    @Test
    public void testGetReportFromFile_Success() throws IOException {
        // Arrange
        File tempFile = File.createTempFile("salesreport", ".csv");
        try (FileWriter writer = new FileWriter(tempFile)) {
            writer.write("2021-01|1000\n");
            writer.write("2021-02|2000\n");
        }

        // Act
        SalesReportGenerator SalesReportGenerator = new SalesReportGenerator();
        Map<String, String> report = SalesReportGenerator.getReportFromFile(tempFile.getAbsolutePath());

        // Assert
        Assert.assertNotNull(report);
        Assert.assertEquals(2, report.size());
        Assert.assertEquals("1000", report.get("2021-01"));
        Assert.assertEquals("2000", report.get("2021-02"));

        // Clean up
        tempFile.deleteOnExit();
    }

    @Test
    public void testGetReportFromFile_FileNotFound() {
        // Arrange
        String nonexistentFilePath = "nonexistent.csv";

        // Act
        Map<String, String> report = SalesReportGenerator.getReportFromFile(nonexistentFilePath);

        // Assert
        Assert.assertNotNull(report);
        Assert.assertTrue(report.isEmpty());
    }

    @Test(expected = RuntimeException.class)
    public void testGetReportFromFile_FileNotReadable() throws IOException {
        // Arrange
        File tempFile = File.createTempFile("salesreport", ".csv");
        tempFile.setReadable(false);

        try {
            // Act
            SalesReportGenerator.getReportFromFile(tempFile.getAbsolutePath());
        } finally {
            // Clean up
            tempFile.setReadable(true);
            tempFile.deleteOnExit();
        }
    }

    @Test
    public void testGetSalesFile_NonExistent() {
        // Arrange
        String nonexistentFilePath = "nonexistent.csv";

        // Act
        File result = SalesReportGenerator.getSalesFile(nonexistentFilePath);

        // Assert
        Assert.assertNull(result);
    }

    @Test
    public void testGetSalesFile_NotAFile() {
        // Arrange
        File tempDirectory = new File("tempDirectory");
        if (tempDirectory.mkdir()) {
            tempDirectory.deleteOnExit();
        }

        // Act
        File result = SalesReportGenerator.getSalesFile(tempDirectory.getAbsolutePath());

        // Assert
        Assert.assertNull(result);
    }

    @Test
    public void testGetSalesFile_ValidFile() throws IOException {
        // Arrange
        File tempFile = File.createTempFile("salesreport", ".csv");

        // Act
        File result = SalesReportGenerator.getSalesFile(tempFile.getAbsolutePath());

        // Assert
        Assert.assertNotNull(result);
        Assert.assertEquals(tempFile.getAbsolutePath(), result.getAbsolutePath());

        // Clean up
        tempFile.deleteOnExit();
    }
}
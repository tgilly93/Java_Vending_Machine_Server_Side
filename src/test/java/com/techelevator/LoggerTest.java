package com.techelevator;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;

public class LoggerTest {

    private Logger logger;
    private String logFileName;
    private String logFilePath;

    @Before
    public void setUp() throws IOException {
        // Arrange
        logFileName = LocalDate.now() + "-test.log";
        logger = new Logger("-test.log");
        logFilePath = logger.getFilePath();
    }

    @After
    public void tearDown() {
        // Clean up the created log file after each test
        File file = new File(logFilePath);
        if (file.exists()) {
            file.delete();
        }
    }

    @Test
    public void testLoggerConstructor() throws IOException {
        // Arrange is handled in setUp

        // Act
        File logFile = new File(logFilePath);

        // Assert
        Assert.assertNotNull("Logger object should not be null", logger);
        Assert.assertTrue("Log file should exist", logFile.exists());
        Assert.assertTrue("Log file should be a file", logFile.isFile());
    }

    @Test
    public void testWriteLogEntry() throws IOException {
        // Arrange
        String message = "This is a test log entry";

        // Act
        logger.writeLogEntry(message);

        // Assert
        try (BufferedReader reader = new BufferedReader(new FileReader(logFilePath))) {
            String line;
            boolean found = false;
            while ((line = reader.readLine()) != null) {
                if (line.contains(message)) {
                    found = true;
                    break;
                }
            }
            Assert.assertTrue("Log file should contain the log entry", found);
        }
    }

    @Test
    public void testGetLogFile() throws IOException {
        // Arrange
        String newLogFileName = LocalDate.now() + "-newtest.log";
        Logger newLogger = new Logger("-newtest.log");

        // Act
        File newLogFile = newLogger.getLogFile(newLogFileName);

        // Assert
        Assert.assertNotNull("Log file should not be null", newLogFile);
        Assert.assertTrue("Log file should exist", newLogFile.exists());
        Assert.assertTrue("Log file should be a file", newLogFile.isFile());

        // Clean up
        newLogFile.delete();
    }

    @Test
    public void testGetFilePath() {
        // Arrange is handled in setUp

        // Act
        String filePath = logger.getFilePath();

        // Assert
        Assert.assertEquals("File paths should match", logFilePath, filePath);
    }
}


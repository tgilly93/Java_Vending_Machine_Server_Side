package com.techelevator.generators;

import com.techelevator.model.Item;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class SalesReportGenerator {
    public static Map<String, String> getReportFromFile(String path){
        Map<String, String> report = new LinkedHashMap<>();
        File inputFile = getSalesFile(path);
        if (inputFile != null) {
            try (Scanner fileScanner = new Scanner(inputFile)) {
                while (fileScanner.hasNextLine()) {
                    String[] reportLine = fileScanner.nextLine().split("\\|");
                    report.put(reportLine[0],reportLine[1]);
                }
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }

        return report;
    }

    public static File getSalesFile(String s) {
        File inputFile = new File(s);
        if (inputFile.exists() == false) { // checks for the existence of a file
            System.out.println(s + " does not exist");
            return null;
        } else if (inputFile.isFile() == false) {
            System.out.println(s + " is not a file");
            return null;
        }
        return inputFile;
    }
}

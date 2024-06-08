package com.techelevator.generators;

import com.techelevator.model.Item;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InventoryGenerator {


    public static List<Item> getListOfItemsFromFile(){
        List<Item> items = new ArrayList<>();
        File inputFile = getInventoryFile("vendingmachine.csv");

        if (inputFile != null) {
            try (Scanner fileScanner = new Scanner(inputFile)) {
                while (fileScanner.hasNextLine()) {
                    String[] itemAsString  = fileScanner.nextLine().split("\\|");
                   // String[] itemAsString  = itemLine.split("|");
                    Item item = new Item();
                    item.setLocation(itemAsString[0]);
                    item.setName(itemAsString[1]);
                    item.setCost(new BigDecimal(itemAsString[2]));
                    item.setType(itemAsString[3]);

                    items.add(item);
                }
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        return items;
    }

    public static File getInventoryFile(String path) {
        File inputFile = new File(path);
        if (inputFile.exists() == false) { // checks for the existence of a file
            System.out.println(path + " does not exist");
            return null;
        } else if (inputFile.isFile() == false) {
            System.out.println(path + " is not a file");
            return null;
        }
        return inputFile;
    }
}

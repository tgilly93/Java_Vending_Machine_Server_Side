package com.techelevator.model;

import com.techelevator.generators.InventoryGenerator;

import java.io.File;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Inventory {
    private List<Item> itemInventory;
    public static int inventoryCount;
    public static int individualCount;
    private final Map<String, Item> inventory = new HashMap<>();
    private final  Map<String, BigDecimal> salesReport = new LinkedHashMap<>();

    public Inventory() {
        File tempFile = null;
        this.itemInventory = InventoryGenerator.getListOfItemsFromFile(tempFile.getAbsolutePath());
        for (Item item : this.itemInventory) {
            inventoryCount += item.getInventoryCount();
            inventory.put(item.getLocation(),item);
            salesReport.put(item.getName(), BigDecimal.valueOf(0));
        }
        salesReport.put("Total Sales", BigDecimal.valueOf(0));

    }

    public static void setInventoryCount(int i) {
    }


    public Item dispenseItem(String location, Money money){

       if(inventory.containsKey(location.toUpperCase())){
        int index = itemInventory.indexOf(inventory.get(location.toUpperCase()));
        Item item = itemInventory.get(index);
        if(inventoryCount > 0){
           if (item.getInventoryCount() > 0 && money.getValue().compareTo(item.getCost()) >= 0) {
               itemInventory.get(index).setInventoryCount(itemInventory.get(index).getInventoryCount() -1);
               inventoryCount --;
               getIndividualItemCount(location);


               return itemInventory.get(index);

           } else {
                   System.out.println("SOLD OUT!");
               return null;
           }

        }
        else {
            System.out.println("Need to Restock!");
            return null;
        }
       }else {
        System.out.println("location is invalid");
        return null;}


    }
public int getIndividualItemCount (String ItemCount){
    if(inventory.containsKey(ItemCount.toUpperCase())){
        int index = itemInventory.indexOf(inventory.get(ItemCount.toUpperCase()));
        Item item = itemInventory.get(index);
        individualCount = item.getInventoryCount();
            return individualCount;
        }
    return 0;
}

    public void refillInventory(){
        if (inventoryCount == 0){
            File tempFile = null;
            this.itemInventory = InventoryGenerator.getListOfItemsFromFile(tempFile.getAbsolutePath());
            for (Item item : this.itemInventory) {
                inventoryCount += item.getInventoryCount();
            }
        }
    }

    public void display(){
        for(Item item : itemInventory){
            System.out.println(item.toString());
        }
    }




    public Map<String, BigDecimal> getSalesReport() {
        return salesReport;
    }
}



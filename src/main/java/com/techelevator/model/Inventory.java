package com.techelevator.model;

import com.techelevator.generators.InventoryGenerator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Inventory {
    private List<Item> itemInventory;
    static int inventoryCount;
    private final Map<String, Item> inventory = new HashMap<>();

    public Inventory() {
        this.itemInventory = InventoryGenerator.getListOfItemsFromFile();
        for (Item item : this.itemInventory) {
            inventoryCount += item.getInventoryCount();
            inventory.put(item.getLocation(),item);
        }

    }


    public Item dispenseItem(String location){

       if(inventory.containsKey(location.toUpperCase())){
        int index = itemInventory.indexOf(inventory.get(location.toUpperCase()));
        if(inventoryCount > 0){
           if (itemInventory.get(index).getInventoryCount() > 0) {
               itemInventory.get(index).setInventoryCount(itemInventory.get(index).getInventoryCount() -1);
               inventoryCount --;


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

    public void refillInventory(){
        if (inventoryCount == 0){
            this.itemInventory = InventoryGenerator.getListOfItemsFromFile();
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


}



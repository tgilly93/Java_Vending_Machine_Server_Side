package com.techelevator.model;

import com.techelevator.generators.InventoryGenerator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Inventory {
    private List<Item> ItemInventory;
    static int InventoryCount;
    private  Map<String, Item> inventory = new HashMap<>();

    public Inventory(List<Item> itemInventory) {
        this.ItemInventory = itemInventory;
        for (Item item : ItemInventory) {
            InventoryCount += item.getInventoryCount();
        }
        for(Item item : ItemInventory){
            inventory.put(item.getLocation(),item);
        }
    }

    public Item dispenseItem(String location){

       if(inventory.containsKey(location)){
           Item item = new Item();
           item = inventory.get(location);

           if (InventoryCount > 0){
               if (item.getInventoryCount() > 0){
                   return item;

               } else {
                   System.out.println("SOLD OUT!");
               }
           } else {
               System.out.println("Please Restock!");
           }

       }else {
           System.out.println("location is invalid");
       }


       return null;

    }

    public void refillInventory(){
        if (InventoryCount == 0){
            this.ItemInventory = InventoryGenerator.getListOfItemsFromFile();
        }
    }


}


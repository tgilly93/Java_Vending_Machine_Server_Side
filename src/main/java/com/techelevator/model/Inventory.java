package com.techelevator.model;

import com.techelevator.generators.InventoryGenerator;

import java.util.List;

public class Inventory {
    private List<Item> ItemInventory;
    static int InventoryCount;

    public Inventory(List<Item> itemInventory) {
        this.ItemInventory = itemInventory;
        for (Item item : ItemInventory) {
            InventoryCount += item.getInventoryCount();
        }
    }

    public void dispenseItem(Item item){
        if (InventoryCount > 0){
            if (item.getInventoryCount() > 0){
                item.setInventoryCount(item.getInventoryCount() - 1);
                InventoryCount-=1;
            } else {
                System.out.println("SOLD OUT!");
            }
        } else {
            System.out.println("Please Restock!");
        }

    }

    public void refillInventory(){
        if (InventoryCount == 0){
            this.ItemInventory = InventoryGenerator.getListOfItemsFromFile();
        }
    }


}


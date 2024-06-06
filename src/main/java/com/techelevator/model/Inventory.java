package com.techelevator.model;

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
}


package com.techelevator.model;

<<<<<<< HEAD
public class Inventory {
}
=======
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

>>>>>>> 3afa2aafb781d2de47f1ad4d31fd8d142822e601

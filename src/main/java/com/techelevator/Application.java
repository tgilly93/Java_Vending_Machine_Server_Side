package com.techelevator;

import com.techelevator.generators.InventoryGenerator;
import com.techelevator.model.Item;

import java.util.List;

public class Application {

	public static void main(String[] args) {
		List<Item> items = InventoryGenerator.getListOfItemsFromFile();

		for(Item item : items){
			System.out.println(item.getName());
		}
	}
}

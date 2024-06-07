package com.techelevator;
import com.techelevator.generators.InventoryGenerator;
import com.techelevator.model.*;
import  java.lang.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ConsoleService {
   private  boolean valid = false;
   List<Item> inventory;

   public  void printStartUp(){
      System.out.println("*********************************************");
      System.out.println("Welcome to  Umbrella Corp's Vendo-Matic 800");
      System.out.println("\"Where Our business is life itself!\"");
      System.out.println("*********************************************");
   }

   public void printMenu() {
      System.out.println("(1) Display Vending Machine Items");
      System.out.println("(2) Purchase");
      System.out.println("(3) Exit");
      Scanner userInput = new Scanner(System.in);
      int selected = 0;
      while (!valid) {
         System.out.println("Please make selection based on number...");
         try {
            selected = Integer.parseInt(userInput.nextLine());
            valid = true;
           choiceSelected(selected);
         } catch (NumberFormatException e) {
            valid = false;
            System.out.println("Invalid Option");
         } catch (Exception e) {
            valid = false;
            System.out.println("Invalid Option");
         }

      }
   }

   public void choiceSelected(int choice){
      switch (choice){
         case 1:
            System.out.println("Displaying items...");
            displayInventory();
            break;
         case 2:
            System.out.println("You selected 2");
            break;

         case 3:
            System.out.println("You selected 3, Goodbye!");
            System.exit(0);
            break;

         case 4:
            System.out.println("You selected 4");
            break;
         default:
            System.out.println("Invalid selection");
            valid = false;
            printMenu();
      }
   }
   public  void displayInventory(){
      inventory = InventoryGenerator.getListOfItemsFromFile();
      for(Item item : inventory){
         System.out.println(item.toString());
      }

   }

}

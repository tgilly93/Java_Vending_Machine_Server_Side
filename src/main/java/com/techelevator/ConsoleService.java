package com.techelevator;
import com.techelevator.generators.InventoryGenerator;
import com.techelevator.model.*;
import  java.lang.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ConsoleService {
   private  boolean valid = false;
   Inventory inventory = new Inventory();
   int menu = 1;

   public  void printStartUp(){
      System.out.println("*********************************************");
      System.out.println("Welcome to  Umbrella Corp's Vendo-Matic 800");
      System.out.println("\"Where Our business is life itself!\"");
      System.out.println("*********************************************");
   }

   public void printMenu(int menu) {
      if(menu == 1) {
         System.out.println("(1) Display Vending Machine Items");
         System.out.println("(2) Purchase");
         System.out.println("(3) Exit");
      }else{
         System.out.println("(1) Feed Money");
         System.out.println("(2) Select Product");
         System.out.println("(3) Finish Transaction");
      }
      Scanner userInput = new Scanner(System.in);
      int selected = 0;
      while (!valid) {
         System.out.println("Please make selection based on number...");
         try {
            selected = Integer.parseInt(userInput.nextLine());
            valid = true;
           choiceSelected(selected, menu);
         } catch (Exception e) {
            valid = false;
            System.out.println("Invalid Option");
         }

      }
   }

   public void choiceSelected(int choice, int menu){
      if(menu == 1){
         switch (choice) {
            case 1:
               System.out.println("Displaying items...");
               displayInventory();
               printMenu(menu);
                break;
            case 2:
               System.out.println("You selected 2");
               menu = 2;
               valid = false;
               printMenu(menu);
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
               menu = 1;
               printMenu(menu);
         }
      }else {
         switch (choice){
            case 1:
               break;
            case 2:
               dispense();
               break;
            case 3:
               break;
            default:
               System.out.println("Invalid selection");
               valid = false;
               menu = 2;
               printMenu(menu);
         }
      }
   }
   public  void displayInventory(){
     inventory.display();
     valid = false;

   }

   public void dispense() {
      displayInventory();
      Scanner userInput = new Scanner(System.in);


         System.out.println("Make a selection");
         String choice = userInput.nextLine();
         Item item = inventory.dispenseItem(choice);
         if (item != null) {
            System.out.println(item.toString());
         }else{
            menu = 2;
            printMenu(menu);
         }
   }



}

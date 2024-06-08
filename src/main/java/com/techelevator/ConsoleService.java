package com.techelevator;

import com.techelevator.generators.SalesReportGenerator;
import com.techelevator.model.Bank;
import com.techelevator.model.Inventory;
import com.techelevator.model.Item;
import com.techelevator.model.Money;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.MessageFormat;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class ConsoleService {
    private boolean valid = false;
    private final String Log_Txt = "-log.log";
    private final String Sales_Report_TXT = "salesreport.txt";
    private BigDecimal cartTotal = BigDecimal.valueOf(0.00);
    private Inventory inventory = new Inventory();
    private Bank bank = new Bank();
    private Logger logger;
    private Money moneyInserted = new Money(BigDecimal.valueOf(0.00));
    private int menu = 1;

    private Map<String, BigDecimal> salesReport = new LinkedHashMap();


    public void printStartUp() {
        System.out.println("*********************************************");
        System.out.println("Welcome to  Umbrella Corp's Vendo-Matic 800");
        System.out.println("\"Where Our business is life itself!\"");
        System.out.println("*********************************************");
        salesReport = inventory.getSalesReport();
    }

    public void printMenu(int menu) {
        lookForSalesReportToLoad();
        if (menu == 1) {
            System.out.println("\n(1) Display Vending Machine Items");
            System.out.println("(2) Purchase");
            System.out.println("(3) Exit");
        } else {
            String message = MessageFormat.format("Current Money Provided: {0}", moneyInserted);
            System.out.println("\n" + message + "\n");
            System.out.println("(1) Feed Money");
            System.out.println("(2) Select Product");
            System.out.println("(3) Finish Transaction");
        }
        Scanner userInput = new Scanner(System.in);
        int selected;
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

    public void choiceSelected(int choice, int menu) {
        if (menu == 1) {
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
                    makeSalesReport();
                    valid = false;
                    printMenu(menu);
                    break;
                default:
                    System.out.println("Invalid selection");
                    valid = false;
                    printMenu(menu);
                    break;
            }
        } else {
            switch (choice) {
                case 1:
                    feedMoney();
                    break;
                case 2:
                    dispense();
                    break;
                case 3:
                    finishTransaction();
                    break;
                default:
                    System.out.println("Invalid selection");
                    valid = false;
                    menu = 2;
                    printMenu(menu);
            }
        }
    }

    public void displayInventory() {
        inventory.display();
        valid = false;

    }

    public void dispense() {
        displayInventory();
        Scanner userInput = new Scanner(System.in);


        System.out.println("Make a selection");
        String choice = userInput.nextLine();
        Item item = inventory.dispenseItem(choice, moneyInserted);
        if (item != null) {
            updateTracked(item);
            try {
                logger = new Logger(Log_Txt);
                String logMessage = "Purchased " + item.getName() + " - " + item.getCost();
                logger.writeLogEntry(logMessage);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

        }
        menu = 2;
        printMenu(menu);

    }

    private void updateTracked(Item item) {
        System.out.println(item);
        moneyInserted.updateMoney(item.getCost());
        cartTotal = cartTotal.add(item.getCost());
        BigDecimal count = salesReport.get(item.getName());
        BigDecimal sales = salesReport.get("Total Sales");
        salesReport.put(item.getName(), count.add(BigDecimal.ONE));
        salesReport.put("Total Sales", sales.add(item.getCost()));
        switch (item.getType()) {
            case "Drink":
                System.out.println("Glug Glug, Yum!");
                break;
            case "Candy":
                System.out.println("Munch Munch, Yum!");
                break;
            case "Gum":
                System.out.println("Chew Chew, Yum!");
                break;
            default:
                System.out.println("Crunch Crunch, Yum!");
                break;

        }
    }

    public void feedMoney() {

        Scanner moneyInput = new Scanner(System.in);

        valid = false;
        BigDecimal selected;
        while (!valid) {
            System.out.println("Add Money");
            try {
                selected = BigDecimal.valueOf(Double.parseDouble(moneyInput.nextLine())).setScale(2, RoundingMode.HALF_EVEN);
                valid = true;
                bank.addCoin(selected);
                moneyInserted = bank.getTotalAmountInserted();
                logger = new Logger(Log_Txt);
                String logMessage = "Inserted " + moneyInserted.getValue();
                logger.writeLogEntry(logMessage);
            } catch (Exception e) {
                valid = false;
                System.out.println("Invalid Option");
            }

        }
        valid = false;
        menu = 2;
        printMenu(menu);

    }

    public void finishTransaction() {
        //return change
        getChange();
        //add amount of money spent to Bank Class
        bank.setBankTotal(cartTotal);
        //reset moneyInserted to 0
        moneyInserted.setValue(BigDecimal.ZERO);
        bank.clearInserted();
        try {
            logger = new Logger(Log_Txt);
            String logMessage = "Inserted " + moneyInserted.getValue();
            logger.writeLogEntry(logMessage);
        } catch (Exception e) {
            valid = false;
            System.out.println("Invalid Option");
        }
        //return user back to main menu
        menu = 1;
        valid = false;
        printMenu(menu);
    }

    public void getChange() {
        int quarters;
        int dimes;
        int nickels;

        double totalCents = moneyInserted.getValue().doubleValue();
        int totalCentsInt = (int) (totalCents * 100);


        quarters = totalCentsInt / 25;
        totalCentsInt %= 25;

        dimes = totalCentsInt / 10;
        totalCentsInt %= 10;

        nickels = totalCentsInt / 5;

        System.out.println("Your change is " + quarters + " quarters, " + dimes + " dimes, and " + nickels + " nickels.");
    }

    private void makeSalesReport() {
        try {
            logger = new Logger(Sales_Report_TXT);

        } catch (IOException e) {
            System.out.println("Error on sales report:" + e.getMessage());
        }
        try {
            for (Map.Entry<String, BigDecimal> entry : salesReport.entrySet()) {

                    String logMessage = entry.getKey() + "|" + entry.getValue();
                    logger.writeLogEntry(logMessage);
            }
        } catch (IOException e) {
            System.out.println("Error on sales report:" + e.getMessage());
        } finally {
           lookForSalesReportToLoad();
           for(Map.Entry<String, BigDecimal> report: salesReport.entrySet()){
               System.out.println(report.getKey() + '|' + report.getValue());
           }
        }

    }

    private  void lookForSalesReportToLoad(){
        Map<String, String> reportDisplay = new LinkedHashMap<>();

        try {
            logger = new Logger(Sales_Report_TXT);
            reportDisplay = SalesReportGenerator.getReportFromFile(logger.getFilePath());

        } catch (IOException e) {
            System.out.println("Error on sales report:" + e.getMessage());
        }finally {
            if(!reportDisplay.isEmpty()) {
                for (Map.Entry<String, String> display : reportDisplay.entrySet()) {
                    salesReport.put(display.getKey(), BigDecimal.valueOf(Double.parseDouble(display.getValue())));

                }
            }
        }
    }
}

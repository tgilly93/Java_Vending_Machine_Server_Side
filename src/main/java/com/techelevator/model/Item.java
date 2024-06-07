package com.techelevator.model;


import java.math.BigDecimal;
import java.text.MessageFormat;

public class Item {
    private String location;
    private String name;

    private String type;

    private BigDecimal cost;

    private int inventoryCount = 5;

    public Item() {

    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }


    public int getInventoryCount() {
        return inventoryCount;
    }

    public void setInventoryCount(int inventoryCount) {
        this.inventoryCount = inventoryCount;
    }

    @Override
    public String toString(){
        return MessageFormat.format("Location: {0} - Item: {1} - Price: ${2} - x{3}",getLocation(),getName(),getCost(),getInventoryCount());
    }


}

package org.example;

public class Product {
    private int id = 1;
    private String name;
    private Double priceBought;
    private Double priceSold;
    private int stock;

    public Product(String name, double priceBought, int stock) {
        this.id++;
        this.name = name;
        this.priceBought = priceBought;
        this.stock = stock;
    }

    public Product(String name, double priceBought, double priceSold, int stock) {
        this.id++;
        this.name = name;
        this.priceBought = priceBought;
        this.priceSold = priceSold;
        this.stock = stock;
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPriceBought() {
        return priceBought;
    }

    public void setPriceBought(Double priceBought) {
        this.priceBought = priceBought;
    }

    public Double getPriceSold() {
        return this.priceSold;
    }

    public Double setPriceSold() {
        return this.priceSold;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String productDetailsOnCart() {
        double totalPriceItems = this.priceSold * this.stock;
        return String.format("Item Name: %s -*- Price Each: $%.2f -*- Quantity on Cart: %d for a total of %.2f", this.name, this.priceSold, this.stock, totalPriceItems);
    }

    public String toString() {
        return String.format("Item Name: %s --- Price Each: $%.2f --- Qty Available: %d", this.name, this.priceSold, this.stock);
    }
}

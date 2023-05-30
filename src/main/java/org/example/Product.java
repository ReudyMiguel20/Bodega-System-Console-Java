package org.example;

public class Product {
    private int id = 1;
    private String name;
    private Double price;
    private int stock;

    public Product(String name, double price, int stock) {
        this.id++;
        this.name = name;
        this.price = price;
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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String productDetailsOnCart() {
        double totalPriceItems = this.price * this.stock;
        return String.format("Item Name: %s -*- Price Each: $%.2f -*- Quantity on Cart: %d for a total of %.2f", this.name, this.price, this.stock, totalPriceItems);
    }

    public String toString() {
        return String.format("Item Name: %s --- Price Each: $%.2f --- Qty Available: %d", this.name, this.price, this.stock);
    }
}

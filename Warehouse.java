package org.example;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;

public class Warehouse {
    private ArrayList<Product> productInventory;
    private Scanner scanner;

    public Warehouse() {
        this.productInventory = new ArrayList<>();
        this.scanner = new Scanner(System.in);
    }

    public void itemManagementList() {
        System.out.println("""
                
                ===Manage Items===
                1 - Add Item
                2 - Remove Item
                3 - Update Item
                4 - Delete Item""");

        switch (scanner.nextLine()) {
            case "1" -> addProduct();
            case "2" -> removeProduct();
//            case "3" ->
//            case "4" ->
        }
    }

    public void addProduct() {
        boolean userWantToExit = false;
        DecimalFormat df = new DecimalFormat("0.00");

        while (!userWantToExit) {
            System.out.println("\nEnter the name of the product:");
            String nameProduct = scanner.nextLine();
            System.out.println("Enter the price of the product:");
            double priceProduct = Double.parseDouble(scanner.nextLine());
            System.out.println("What is the stock for the item?");
            int stockProduct = Integer.parseInt(scanner.nextLine());

            this.productInventory.add(new Product(nameProduct, priceProduct, stockProduct));
            System.out.printf("\nThe product %s has been added with the price of %.2f and stock %d.\n", nameProduct, priceProduct, stockProduct);


            boolean userWantToAddItem = false;
            while (!userWantToAddItem) {
                System.out.println("You want to add a new item? (Y/N)");
                switch (scanner.nextLine()) {
                    case "Y" -> {
                        userWantToAddItem = true;
                    }
                    case "N" -> {
                        userWantToAddItem = true;
                        userWantToExit = true;
                    }
                    default -> System.out.println("\nPlease put (Y/N)");
                }
            }

        }

    }

    public void addProduct(Product product) {
        this.productInventory.add(product);
    }

    //Want to remove the product by giving the name, then its going to give me the ID so I can confirm to remove the item
    //by putting the ID !!!! -- This is to complex. maybe in the future

    public void removeProduct() {
        System.out.println("Give the ID number of the item");
        int ID = Integer.parseInt(scanner.nextLine());

        for (Product x : this.productInventory) {
            if (x.getId() == ID) {
                this.productInventory.remove(x);
                System.out.printf("Item: %s removed successfully.", x.getName());
                break;
            }
        }
    }

    public int getSize() {
        return this.productInventory.size();
    }
}

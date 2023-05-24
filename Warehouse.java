package org.example;

import java.io.FileWriter;
import java.io.IOException;
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
                3 - Update Item""");

        switch (scanner.nextLine()) {
            case "1" -> addProduct();
            case "2" -> removeProduct();
            case "3" -> updateProduct();
        }
    }

    /**
     * Method for adding products, asks for name, price and stock of the item before adding it to the list
     * <p>
     * If item doesn't exist on the list, it will be added as normal and then the user need to decide if it's still
     * to add more items
     * <p>
     * If the item exists on the list it won't be added, and it returns the user back to the menu
     */
    public void addProduct() {
        boolean userWantToExit = false;

        while (!userWantToExit) {
            System.out.println("\nEnter the name of the product:");
            String nameProduct = scanner.nextLine().trim().toLowerCase();
            System.out.println("Enter the price of the product:");
            double priceProduct = Double.parseDouble(scanner.nextLine());
            System.out.println("What is the stock for the item?");
            int stockProduct = Integer.parseInt(scanner.nextLine());

            if (isItemOnTheList(nameProduct)) {
                System.out.println("\nSorry, item exists on the list, going back to the menu...");
                break;
            }

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

    public boolean isItemOnTheList(String nameProduct) {
        for (Product x : this.productInventory) {
            if (x.getName().equals(nameProduct)) {
                return true;
            }
        }
        return false;
    }

    public void addProduct(Product product) {
        this.productInventory.add(product);
    }

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

    public void updateProduct() {
        System.out.println("Input the name of the item you want to update: ");
        String itemName = scanner.nextLine();

        int index = 0;
        for (Product x : this.productInventory) {
            if (x.getName().equals(itemName)) {
                this.productInventory.set(index, updateProductInfo());
            }
            index++;
        }
    }

    public Product updateProductInfo() {
        System.out.println("\nEnter the name of the product:");
        String nameProduct = scanner.nextLine().trim().toLowerCase();
        System.out.println("Enter the price of the product:");
        double priceProduct = Double.parseDouble(scanner.nextLine());
        System.out.println("What is the stock for the item?");
        int stockProduct = Integer.parseInt(scanner.nextLine());

        return new Product(nameProduct, priceProduct, stockProduct);
    }

    public int getSize() {
        return this.productInventory.size();
    }


    /**
     * The export file saves the value into a file in the following format:
     *  Item:Price:StockQty
     */
    public void exportItems() throws IOException {
        FileWriter fw = new FileWriter("list.txt");
        fw.write(csvValues());
        fw.close();
    }

    public String csvValues() {
        StringBuilder sb = new StringBuilder();

        for (Product x : this.productInventory) {
            sb.append(x.getName()).append(":").append(x.getPrice()).append(":").append(x.getStock()).append("\n");
        }

        //Removing last newline from the StringBuilder
        sb.deleteCharAt(sb.length() - 1);

        return sb.toString();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();

        this.productInventory.forEach(item -> sb.append(item).append("\n"));

        //Removing last newline from the StringBuilder
        sb.deleteCharAt(sb.length() - 1);

        return sb.toString();
    }
}

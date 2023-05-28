package org.example;

import java.util.ArrayList;
import java.util.Scanner;

public class BodegaCheckout {
    private Warehouse warehouse;
    private double totalMoney;
    private Scanner scanner;
    private ArrayList<Product> cart;

    public BodegaCheckout(Warehouse warehouse) {
        this.totalMoney = 0;
        this.warehouse = warehouse;
        this.scanner = new Scanner(System.in);
        this.cart = new ArrayList<>();
    }

    public void sellItems() {
        while (true) {
            System.out.println("Enter the name of the item or 'back' if you want to return.");
            String productName = scanner.nextLine();

            if (productName.equals("back")) {
                return;
            }

            System.out.printf("How many %s do you want? (Input quantity):\n", productName);
            int productQuantity = Integer.parseInt(scanner.nextLine());

            Product itemToSell = this.warehouse.getProductToSell(productName);

            if (itemToSell == null) {
                System.out.println("This product doesn't exist");
                return;
            } else {
                //handle if the stock is one or less than one
                itemToSell.setStock(itemToSell.getStock() - productQuantity);
                this.totalMoney += itemToSell.getPrice() * productQuantity;
                this.cart.add(new Product(itemToSell.getName(), itemToSell.getPrice(), productQuantity));
            }

            System.out.printf("You bought %d of %s for a total price of %.2f", productQuantity, itemToSell.getName(), this.totalMoney);
        }


        //Create a while loop and sum the total like a cart and checkout
        //handle if the stock is one or less than one
        //Exception when loading file empty
    }
}

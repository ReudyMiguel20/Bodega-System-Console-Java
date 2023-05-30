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

    /**
     * This method is for selling items
     */
    public void sellItems() {
        boolean userWantToExit = false;
        double totalPriceOfItem = 0.00;
        double checkoutTotalCart = 0.00;

        while (!userWantToExit) {
            System.out.println("Input product name:");
            String productName = scanner.nextLine();
            Product itemToSell = this.warehouse.getProductToSell(productName);


            if (itemToSell == null) {
                System.out.println("This product doesn't exist. Please check the item list in the 'Inventory Management'.");
                return;
            } else {
                System.out.printf("How many %s do you want? (Input quantity):\n", productName);
                int productQuantity = Integer.parseInt(scanner.nextLine());
                //handle if the stock is one or less than one
                itemToSell.setStock(itemToSell.getStock() - productQuantity);
                totalPriceOfItem = itemToSell.getPrice() * productQuantity;

                //Adding total price to the variables;
                checkoutTotalCart += totalPriceOfItem;
                this.totalMoney += totalPriceOfItem;

                this.cart.add(new Product(itemToSell.getName(), itemToSell.getPrice(), productQuantity));


                System.out.printf("You added %d of %s to the cart.", productQuantity, itemToSell.getName());

                userWantToExit = controlSellItems(checkoutTotalCart);
            }

        }

        //Create a while loop and sum the total like a cart and checkout
        //handle if the stock is one or less than one
        //Exception when loading file empty
    }

    public boolean controlSellItems(double cartPrice) {
        boolean wrongInput = true;
        System.out.println("\n\nEnter 'add' for adding products or 'checkout' to proceed to checkout.");

        while (wrongInput) {
            String userInput = scanner.nextLine();
            switch (userInput) {
                case "back" -> {
                    wrongInput = false;
                    return true;
                }
                case "checkout" -> {
                    wrongInput = false;
                    checkoutItems(cartPrice);
                    return true;
                }
                case "add" -> {
                    wrongInput = false;
                    return false;
                }
                default -> {
                    System.out.println("Wrong input. Try Again.");
                }
            }
        }
        return false;
    }

    public void checkoutItems(double cartPrice) {
        System.out.println("\n===Items on cart===");
        for (Product x : this.cart) {
            System.out.println(x.productDetailsOnCart());
        }
        System.out.printf("\nFor a total of: $%.2f\n", cartPrice);
        System.out.println("Thanks for your purchase, come back soon!");
    }

    public void checkMoneyOnCashRegister() {
        System.out.printf("\nTotal cash on the cash register: $%.2f\n", this.totalMoney);
    }
}

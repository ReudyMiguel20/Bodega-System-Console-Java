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
     * This method is for selling items, in order for items to be sold the item must exist in the item inventory first,
     * if said item is not on the 'Item Inventory' then is going to return back. If the asked amount of items to sell
     * and the stock of said item is less than 0, then it's not going to be possible to sell said item.
     *
     * After a product has been added to be sold, is going to call the method controlSellItems(), which action is to
     * control the userInput and ask the user if it wants to keep adding items, or proceed to checkout.
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

                //Handling if the stock is less than zero.
                if (itemToSell.getStock() - productQuantity < 0) {
                    System.out.printf("\nSorry, there's not enough %s for this required amount.", productName);
                    return;
                }

                itemToSell.setStock(itemToSell.getStock() - productQuantity);
                totalPriceOfItem = itemToSell.getPrice() * productQuantity;

                //Adding total price to the variables to keep track of the total money on cart and on the cash register.
                checkoutTotalCart += totalPriceOfItem;
                this.totalMoney += totalPriceOfItem;

                this.cart.add(new Product(itemToSell.getName(), itemToSell.getPrice(), productQuantity));

                System.out.printf("You added %d of %s to the cart.", productQuantity, itemToSell.getName());

                userWantToExit = controlSellItems(checkoutTotalCart);
            }

        }
        //Exception when loading file empty
    }

    /**
     * Method to control the flow of the program after successfully adding an item for checkout. For more information
     * check the method sellItems().
     * @param cartPrice
     * @return
     */
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

    //I need to move the export/import methods from Warehouse to this class.
}

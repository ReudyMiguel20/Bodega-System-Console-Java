package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
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

                //Handling if the stock is less than zero, if it is then returning back to the menu.
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

    //Method for printing all the items on cart on checkout.
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

    public double totalMoneyOnCashRegister() {
        return this.totalMoney;
    }

    /**
     * The export file saves the value into a file in the following format:
     * Item:Price:StockQty
     * Item:Price:StockQty
     * totalMoneyOnCashRegister - it's the total amount of money on cash register, it's always the last value on list.
     *
     * If there's no items added/loaded to the system then it's impossible to export a file, which action is going
     * to throw an exception.
     */
    public void exportItems() {
        try {
            if (this.warehouse.getSize() == 0) {
                throw new StringIndexOutOfBoundsException();
            }

            FileWriter fw = new FileWriter("list.txt");
            fw.write(this.warehouse.csvValues() + "\n");
            fw.write(String.valueOf(totalMoneyOnCashRegister()));
            fw.close();
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("There's no item on the inventory to export.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method imports all items from the list.txt file, the data should be in this format:
     * Item:Price:StockQty
     * Item:Price:StockQty
     * totalMoneyOnCashRegister - it's the total amount of money on cash register, it's always the last value on list.
     *
     */
    public void importFile() throws IOException {
        String line = "";
        BufferedReader reader = null;
        int counterItems = 0;

        try {
            reader = new BufferedReader(new FileReader("list.txt"));

            while ((line = reader.readLine()) != null) {
                String[] splitterInfo = line.split(":");

                //This is for the last number on the file, this should be the money on the cash register.
                if (splitterInfo.length == 1) {
                    this.totalMoney = Double.parseDouble(splitterInfo[0]);
                    break;
                }

                String itemName = splitterInfo[0];
                double itemPrice = Double.parseDouble(splitterInfo[1]);
                int itemQuantity = Integer.parseInt(splitterInfo[2]);

                this.warehouse.addProduct(new Product(itemName, itemPrice, itemQuantity));
                counterItems++;
            }

            //Printing status condition if there's or there's no items on the file.
            if (counterItems == 0) {
                System.out.println("""
                        
                        Import action uncompleted:
                        The file "list.txt" is empty.""");
            } else {
                System.out.printf("\nLoaded %d items to the program.\n", counterItems);
            }

            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

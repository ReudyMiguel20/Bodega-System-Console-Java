package org.example;

import java.io.IOException;
import java.util.Scanner;

public class UserInterface {
    private Scanner scanner;
    private Warehouse warehouse;
    private BodegaCheckout bodegaCheckout;

    public UserInterface(Warehouse warehouse) {
        this.warehouse = warehouse;
        this.bodegaCheckout = new BodegaCheckout(this.warehouse);
        this.scanner = new Scanner(System.in);
    }

    public void start() throws IOException {
        boolean userWantToExit = false;

        System.out.println("Welcome to the Bodega System v0.2");
        while (!userWantToExit) {
            commandList();
            switch (scanner.nextLine()) {
                case "1" -> {
                    this.warehouse.itemManagementList();
                }
                case "2" -> {
                    this.bodegaCheckout.sellItems();
                }
                case "3" -> {
                    this.bodegaCheckout.printStatistics();
                }
                case "6" -> {
                    this.bodegaCheckout.importFile();
                }
                case "7" -> {
                    this.bodegaCheckout.exportItems();
                }
                case "0" -> {
                    System.out.println("Closing the program and saving all stats.");
                    this.bodegaCheckout.exportItems();
                    userWantToExit = true;
                }
            }
        }
    }

    public static void commandList() {
        System.out.println("""
                                
                ===Command List===
                1 - Inventory Management
                2 - Sell Items (Point of Sale)
                3 - Statistics (Report)
                6 - Import Items
                7 - Export Items
                0 - Save and Exit""");
    }

}



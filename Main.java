package org.example;

public class Main {
    public static void main(String[] args) {
        Warehouse bodegaPopular = new Warehouse();
        UserInterface UI = new UserInterface(bodegaPopular);

        UI.start();
    }
}
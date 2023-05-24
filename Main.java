package org.example;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Warehouse bodegaPopular = new Warehouse();
        UserInterface UI = new UserInterface(bodegaPopular);

        UI.start();
    }
}
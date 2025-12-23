package com.example.initierespringbd.cli;

public class MenuPrinter {

    public void print() {
        System.out.println("\nAlegeti optiunea:");
        for (MenuOption option : MenuOption.values()) {
            System.out.printf("%s - %s%n", option.code(), option.description());
        }
    }
}

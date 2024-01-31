package com.adatech;

import java.util.Scanner;

public class ContactBookApplication {
    public static void main(String[] args) {
        ContactBook contactBook = ContactBookFileHandler.loadContactBook();
        Scanner scanner = new Scanner(System.in);
        ContactBookController contactBookController = new ContactBookController(contactBook, scanner);
        contactBookController.start();
        scanner.close();
    }
}

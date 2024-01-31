package com.adatech;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ContactBookFileHandler {
    private static final String FILE_PATH = "agenda.txt";

    public static void saveContactBook(ContactBook contactBook) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Contact contact : contactBook.getContacts()) {
                writeContact(writer, contact);
            }
        } catch (IOException e) {
            handleIOException(e);
        }
    }

    private static void writeContact(BufferedWriter writer, Contact contact) throws IOException {
        writer.write(String.format("%d,%s,%s", contact.getId(), contact.getFirstName(), contact.getLastName()));

        List<Phone> phones = contact.getPhones();
        if (phones != null) {
            for (Phone phone : phones) {
                writer.write(String.format(",%s,%d", phone.getDdd(), phone.getNumber()));
            }
        }

        writer.newLine();
    }

    public static ContactBook loadContactBook() {
        ContactBook contactBook = new ContactBook();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                process(contactBook, line);
            }
        } catch (IOException e) {
            handleIOException(e);
        }
        return contactBook;
    }

    private static void handleIOException(IOException e) {
        System.err.println("I/O Error: " + e.getMessage());
    }

    private static void process(ContactBook contactBook, String line) {
        if (line == null || line.trim().isEmpty()) {
            return;
        }
        String[] parts = line.split(",");
        if (parts.length >= 3) {
            try {
                Long id = Long.parseLong(parts[0].trim());
                String firstName = parts[1].trim();
                String lastName = parts[2].trim();
                List<Phone> phones = createPhonesAndAddToContact(parts);
                Contact contact = new Contact(firstName, lastName, phones);
                contactBook.addContact(contact);
            } catch (NumberFormatException e) {
                System.err.println("Erro ao converter número: " + e.getMessage());
            }
        } else {
            System.err.println("Linha inválida no arquivo: " + line);
        }
    }

    private static List<Phone> createPhonesAndAddToContact(String[] parts) {
        List<Phone> phones = new ArrayList<>();
        Long phoneId = 1L;
        for (int i = 3; i < parts.length; i += 2) {
            try {
                String ddd = parts[i].trim();
                Long number = Long.parseLong(parts[i + 1].trim());
                phones.add(new Phone(phoneId++, ddd, number));
            } catch (NumberFormatException e) {
                System.err.println("Erro ao converter número: " + e.getMessage());
            }
        }
        return phones;
    }
}

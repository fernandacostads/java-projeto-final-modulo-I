package com.adatech;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ContactBookController {
    private final ContactBook contactBook;
    private final Scanner scanner;

    public ContactBookController(ContactBook contactBook, Scanner scanner) {
        this.contactBook = contactBook;
        this.scanner = scanner;
    }

    public void start() {
        int option;
        boolean exit = false;
        do {
            showMenu();

            while (!scanner.hasNextInt()) {
                System.out.println("Opção inválida. Tente novamente.");
                scanner.next();
            }
            option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    addContact();
                    break;
                case 2:
                    removeContact();
                    break;
                case 3:
                    editContact();
                    break;
                case 4:
                    saveContactBook();
                    System.out.print("\nFechando programa... \n Fechar e Salvar? (S) \n Continuar operações? (Enter): ");
                    String continueOption = scanner.nextLine();
                    if (continueOption.equalsIgnoreCase("S")) {
                        exit = true;
                    }
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (!exit);

        scanner.close();
    }

    private void showMenu() {
        System.out.println("##################");
        System.out.println("##### AGENDA #####");
        System.out.println("##################\n");
        System.out.println(">>>> Contatos <<<<");
        System.out.println("Id | Name");
        for (Contact contact : contactBook.getContacts()) {
            System.out.println(contact);
        }
        System.out.println("\n>>>> Menu <<<<");
        System.out.println("1 - Adicionar Contato");
        System.out.println("2 - Remover Contato");
        System.out.println("3 - Editar Contato");
        System.out.println("4 - Sair");
        System.out.print("Escolha uma opção: ");
    }

    private void addContact() {
        try {
            System.out.print("Digite o nome do novo contato: ");
            String newFirstName = scanner.nextLine();

            System.out.print("Digite o sobrenome do novo contato: ");
            String newLastName = scanner.nextLine();

            Contact newContact = new Contact(newFirstName, newLastName, new ArrayList<>());
            contactBook.addContact(newContact);

            addPhones(scanner, contactBook, newContact);
        } catch (InputMismatchException e) {
            System.out.println("Erro: Entrada inválida. ");

        } catch (Exception e) {
            System.out.println("Erro inesperado ao adicionar um contato: " + e.getMessage());
        }
    }

    private static void addPhones(Scanner scanner, ContactBook contactBook, Contact contact) {
        System.out.println("Adicione telefones ao novo contato (Digite '0' para parar):");

        while (true) {
            try {
                System.out.print("Digite o DDD do telefone (ou '0' para parar): ");
                String ddd = scanner.nextLine();
                if (ddd.equals("0")) {
                    break;
                }

                System.out.print("Digite o número do telefone:");
                Long number = scanner.nextLong();
                scanner.nextLine();

                Phone newPhone = new Phone(null, ddd, number);

                if (contactBook.phoneAlreadyRegistered(newPhone.getNumber())) {
                    System.out.println("Erro: Este telefone já está cadastrado.\"");
                    continue;
                }

                contact.addPhone(newPhone);

            } catch (InputMismatchException e) {
                System.out.println("Erro: Entrada inválida.");
                scanner.nextLine();
            } catch (Exception e) {
                System.out.println("Erro inesperado ao adicionar telefone: " + e.getMessage());
                scanner.nextLine();
            }
        }
    }

    private void removeContact() {
        try {
            System.out.print("Digite o ID do contato que deseja remover: ");
            Long idToRemove = scanner.nextLong();
            scanner.nextLine();

            contactBook.removeContact(idToRemove);
        } catch (InputMismatchException e) {
            System.out.println("Erro: Entrada inválida.");
            scanner.nextLine();
        } catch (Exception e) {
            System.out.println("Erro inesperado ao remover contato: " + e.getMessage());
            scanner.nextLine();
        }
    }

    private void editContact() {
        try {
            System.out.print("Digite o ID do contato que deseja editar:");
            Long idToEdit = scanner.nextLong();
            scanner.nextLine();

            Contact contactToEdit = contactBook.getContactById(idToEdit);
            if (contactToEdit != null) {
                System.out.print("Digite o novo nome: ");
                String newFirstName = scanner.nextLine();

                System.out.print("Digite o novo sobrenome: ");
                String newLastName = scanner.nextLine();

                contactBook.editContact(idToEdit, newFirstName, newLastName);

                System.out.print("Deseja editar telefones? (S/N):");
                String editPhonesOption = scanner.nextLine();
                if (editPhonesOption.equalsIgnoreCase("S")) {
                    editPhones(scanner, contactBook, idToEdit);
                }
            } else {
                System.out.println("Contato não encontrado.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Erro: Entrada inválida. .");
            scanner.nextLine();
        } catch (Exception e) {
            System.out.println("Erro inesperado ao editar contato: " + e.getMessage());
            scanner.nextLine();
        }
    }

//    private void editPhones(Scanner scanner, ContactBook contactBook, Long contactId) {
//        Contact contact = contactBook.getContactById(contactId);
//        if (contact != null) {
//            System.out.println("Telefones do contato:");
//            for (Phone phone : contact.getPhones()) {
//                System.out.println(phone.getId() + " | " + phone.getDdd() + " " + phone.getNumber());
//            }
//
//            System.out.print("Digite o ID do telefone que deseja editar:  ");
//            Long idPhoneToEdit = scanner.nextLong();
//            scanner.nextLine();
//            editPhoneOfContact(scanner, contact, idPhoneToEdit);
//
//
//        } else {
//            System.out.println("Contato não encontrado.");
//        }
//    }
private void editPhones(Scanner scanner, ContactBook contactBook, Long contactId) {
    Contact contact = contactBook.getContactById(contactId);
    if (contact != null) {
        while (true) {
            System.out.println("Telefones do contato:");
            for (Phone phone : contact.getPhones()) {
                System.out.println(phone.getId() + " | " + phone.getDdd() + " " + phone.getNumber());
            }

            System.out.println("Escolha uma opção:");
            System.out.println("1 - Adicionar Telefone");
            System.out.println("2 - Editar Telefone");
            System.out.println("3 - Remover Telefone");
            System.out.println("0 - Voltar");
            System.out.print("Opção: ");

            int option;
            while (!scanner.hasNextInt()) {
                System.out.println("Opção inválida. Tente novamente.");
                scanner.next();
            }
            option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    addPhones(scanner, contactBook, contact);
                    break;
                case 2:
                    editPhone(scanner, contact);
                    break;
                case 3:
                    removePhone(scanner, contact);
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    } else {
        System.out.println("Contato não encontrado.");
    }
}

    private void editPhone(Scanner scanner, Contact contact) {
        System.out.print("Digite o ID do telefone que deseja editar: ");
        Long idPhoneToEdit = scanner.nextLong();
        scanner.nextLine();
        editPhoneOfContact(scanner, contact, idPhoneToEdit);
    }

    private void removePhone(Scanner scanner, Contact contact) {
        System.out.print("Digite o ID do telefone que deseja remover: ");
        Long idPhoneToRemove = scanner.nextLong();
        scanner.nextLine();

        Phone phoneToRemove = contact.getPhoneById(idPhoneToRemove);
        if (phoneToRemove != null) {
            contact.getPhones().remove(phoneToRemove);
            System.out.println("SUCESSO: Telefone removido!");
        } else {
            System.out.println("Erro: Telefone não encontrado.");
        }
    }


    private void editPhoneOfContact(Scanner scanner, Contact contact, Long idPhone) {
        System.out.print("Digite o novo DDD:");
        String newDdd = scanner.nextLine();

        System.out.print("Digite o novo número do telefone: ");
        Long newNumber = scanner.nextLong();
        scanner.nextLine();

        contact.editPhone(idPhone, newDdd, newNumber);
    }

    private void saveContactBook() {
        ContactBookFileHandler.saveContactBook(contactBook);
    }
}

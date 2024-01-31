package com.adatech;

import java.util.ArrayList;
import java.util.List;

public class ContactBook {
    private List<Contact> contacts;

    public ContactBook() {
        contacts = new ArrayList<>();
    }

    public void addContact(Contact contact) {
        if (getContactById(contact.getId()) == null) {
            contacts.add(contact);
        } else {
            System.out.println("Erro: Já existe um contato com este ID.");
        }
    }

    public void removeContact(Long id) {
        Contact contactToRemove = null;
        for (Contact contact : contacts) {
            if (contact.getId().equals(id)) {
                contactToRemove = contact;
                break;
            }
        }

        if (contactToRemove != null) {
            contacts.remove(contactToRemove);
            System.out.println("SUCESSO: Contato removido!");
        } else {
            System.out.println("Erro: Não existe um contato com esse ID.");
        }
    }

    public void editContact(Long id, String newFirstName, String newLastName) {
        for (Contact contact : contacts) {
            if (contact.getId().equals(id)) {
                contact.setFirstName(newFirstName);
                contact.setLastName(newLastName);
                System.out.println("SUCESSO: Contato editado!");
                return;
            }
        }
        System.out.println("Erro: Não existe um contato com esse ID.");
    }

    public List<Contact> getContacts() {
        return contacts;
    }

    public Contact getContactById(Long id) {
        for (Contact contact : contacts) {
            if (contact.getId().equals(id)) {
                return contact;
            }
        }
        return null;
    }

    public boolean phoneAlreadyRegistered(Long number) {
        for (Contact contact : contacts) {
            if (contact.phoneAlreadyRegistered(number)) {
                return true;
            }
        }
        return false;
    }
}

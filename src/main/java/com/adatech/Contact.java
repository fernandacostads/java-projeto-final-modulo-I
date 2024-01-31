package com.adatech;

import java.util.ArrayList;
import java.util.List;

public class Contact {
    private Long id;
    private String firstName;
    private String lastName;
    private List<Phone> phones;

    private static Long lastContactId = 0L;
    private Long lastPhoneId;

    public Contact(String firstName, String lastName, List<Phone> phones) {
        this.id = generateNewContactId();
        this.firstName = firstName;
        this.lastName = lastName;
        this.phones = (phones != null) ? phones : new ArrayList<>();
        this.lastPhoneId = 0L;
    }

    private Long generateNewContactId() {
        return ++lastContactId;
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    @Override
    public String toString() {
        return id + " | " + firstName + " " + lastName;
    }

    public void setFirstName(String newFirstName) {
        this.firstName = newFirstName;
    }

    public void setLastName(String newLastName) {
        this.lastName = newLastName;
    }

    public List<Phone> getPhones() {
        return phones;
    }

    public void addPhone(Phone phone) {
        if (!phoneAlreadyRegistered(phone.getNumber())) {
            phone.setId(generateNewPhoneId());
            phones.add(phone);
        } else {
            System.out.println("Erro: Telefone já registrado na agenda!");
        }
    }

    private Long generateNewPhoneId() {
        return ++lastPhoneId;
    }

    public boolean phoneAlreadyRegistered(Long number) {
        return phones.stream().anyMatch(phone -> phone.getNumber().equals(number));
    }

    public Phone getPhoneById(Long id) {
        for (Phone phone : phones) {
            if (phone.getId().equals(id)) {
                return phone;
            }
        }
        return null;
    }

    public void editPhone(Long phoneId, String newDdd, Long newNumber) {
        Phone phone = getPhoneById(phoneId);
        if (phone != null) {
            phone.setDdd(newDdd);
            phone.setNumber(newNumber);
            System.out.println("SUCESSO: Telefone editado!");
        } else {
            System.out.println("Telefone não encontrado.");
        }
    }
}

package com.adatech;

class Phone {
    private Long id;
    private String ddd;
    private Long number;

    public Phone(Long id, String ddd, Long number) {
        this.id = id;
        this.ddd = ddd;
        this.number = number;
    }

    public Long getId() {
        return id;
    }

    public String getDdd() {
        return ddd;
    }

    public Long getNumber() {
        return number;
    }

    public void setDdd(String newDdd) {
        this.ddd = newDdd;
    }

    public void setNumber(Long newNumber) {
        this.number = newNumber;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
package com.adatech;


import java.util.List;

public class Contato {
    private Long id;
    private String nome;
    private String sobreNome;
    private List<Telefone> telefones;

    public Contato(Long id, String nome, String sobreNome, List<Telefone> telefones) {
        this.id = id;
        this.nome = nome;
        this.sobreNome = sobreNome;
        this.telefones = telefones;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getSobreNome() {
        return sobreNome;
    }

    @Override
    public String toString() {
        return id + " | " + nome + " " + sobreNome;
    }

    public void setNome(String novoNome) {
        this.nome = novoNome;
    }

    public void setSobreNome(String novoSobreNome) {
        this.sobreNome = novoSobreNome;
    }

    public List<Telefone> getTelefones() {
        return telefones;
    }
}
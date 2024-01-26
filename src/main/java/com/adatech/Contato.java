package com.adatech;

import java.util.ArrayList;
import java.util.List;

public class Contato {
    private Long id;
    private String nome;
    private String sobreNome;
    private List<Telefone> telefones;

    public Contato(Long id, String nome, String sobreNome) {
        this.id = id;
        this.nome = nome;
        this.sobreNome = sobreNome;
    }
    private void inicializarTelefones() {
        if (telefones == null) {
            telefones = new ArrayList<>();
        }
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

    public List<Telefone> getTelefones() {
        return telefones;
    }

    public void setNome(String novoNome) {
        this.nome = novoNome;
    }

    public void setSobreNome(String novoSobreNome) {
        this.sobreNome = novoSobreNome;
    }

    public void adicionarTelefone(Telefone telefone) {
        inicializarTelefones();
        telefones.add(telefone);
    }

    public void removerTelefone(Telefone telefone) {
        inicializarTelefones();
        telefones.remove(telefone);
    }

    public boolean telefoneJaCadastrado(Long numero) {
        inicializarTelefones();
        return telefones.stream().anyMatch(telefone -> telefone.getNumero().equals(numero));
    }

    public boolean telefoneJaCadastradoNoContato(Long numero) {
        inicializarTelefones();
        return telefones.stream().anyMatch(telefone -> telefone.getNumero().equals(numero));
    }

    @Override
    public String toString() {
        return id + "  | " + nome + " " + sobreNome;
    }
}

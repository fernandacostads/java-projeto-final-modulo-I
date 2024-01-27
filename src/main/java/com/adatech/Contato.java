
package com.adatech;

import java.util.ArrayList;
import java.util.List;

public class Contato {
    private Long id;
    private String nome;
    private String sobreNome;
    private List<Telefone> telefones;

    private Long ultimoIdTelefone;

    public Contato(Long id, String nome, String sobreNome, List<Telefone> telefones) {
        this.id = id;
        this.nome = nome;
        this.sobreNome = sobreNome;
        this.telefones = (telefones != null) ? telefones : new ArrayList<>();
        this.ultimoIdTelefone = 0L;
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

    public void adicionarTelefone(Telefone telefone) {
        if (!telefoneJaCadastrado(telefone.getNumero())) {
            telefone.setId(gerarNovoIdTelefone());
            telefones.add(telefone);
        } else {
            System.out.println("Erro: Este telefone já está cadastrado.");
        }
    }

    private Long gerarNovoIdTelefone() {
        return ++ultimoIdTelefone;
    }
    public void removerTelefone(Telefone telefone) {
        telefones.remove(telefone);
    }

    public boolean telefoneJaCadastrado(Long numero) {
        return telefones.stream().anyMatch(telefone -> telefone.getNumero().equals(numero));
    }

    public Telefone getTelefoneById(Long id) {
        for (Telefone telefone : telefones) {
            if (telefone.getId().equals(id)) {
                return telefone;
            }
        }
        return null;
    }

    public void editarTelefone(Long idTelefone, String novoDdd, Long novoNumero) {
        Telefone telefone = getTelefoneById(idTelefone);
        if (telefone != null) {
            telefone.setDdd(novoDdd);
            telefone.setNumero(novoNumero);
            System.out.println("Telefone editado com sucesso!");
        } else {
            System.out.println("Telefone não encontrado com o ID fornecido.");
        }
    }

    public Telefone getTelefoneByDddAndNumero(String ddd, Long numero) {
        for (Telefone telefone : telefones) {
            if (telefone.getDdd().equals(ddd) && telefone.getNumero().equals(numero)) {
                return telefone;
            }
        }
        return null;
    }
}

//package com.adatech;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class Contato {
//    private Long id;
//    private String nome;
//    private String sobreNome;
//    private List<Telefone> telefones;
//
//    public Contato(Long id, String nome, String sobreNome, List<Telefone> telefones) {
//        this.id = id;
//        this.nome = nome;
//        this.sobreNome = sobreNome;
//        this.telefones = (telefones != null) ? telefones : new ArrayList<>();
//    }
//    private void inicializarTelefones() {
//        if (telefones == null) {
//            telefones = new ArrayList<>();
//        }
//    }
//    public Long getId() {
//        return id;
//    }
//
//    public String getNome() {
//        return nome;
//    }
//
//    public String getSobreNome() {
//        return sobreNome;
//    }
////
////    public void setNome(String novoNome) {
////        this.nome = novoNome;
////    }
////
////    public void setSobreNome(String novoSobreNome) {
////        this.sobreNome = novoSobreNome;
//    @Override
//    public String toString() {
//        return id + " | " + nome + " " + sobreNome;
//    }
//
//    public void setNome(String novoNome) {
//        this.nome = novoNome;
//    }
//
//    public void setSobreNome(String novoSobreNome) {
//        this.sobreNome = novoSobreNome;
//    }
//
//    public List<Telefone> getTelefones() {
//        return telefones;
//    }
//    public void adicionarTelefone(Telefone telefone) {
//        if (!telefoneJaCadastrado(telefone.getNumero())) {
////            inicializarTelefones();
//            telefones.add(telefone);
//        }else {
//            System.out.println("Erro: Este telefone já está cadastrado Contato clss.");
//        }
//    }
//
//    public void removerTelefone(Telefone telefone) {
//        inicializarTelefones();
//        telefones.remove(telefone);
//    }
//
//    public boolean telefoneJaCadastrado(Long numero) {
////        inicializarTelefones();
//        return telefones.stream().anyMatch(telefone -> telefone.getNumero().equals(numero));
//    }
//
//    public boolean telefoneJaCadastradoNoContato(Long telefone) {
////        inicializarTelefones();
//        return telefones.contains(telefone);
//    }}

package com.adatech;

import java.util.ArrayList;
import java.util.List;

public class Agenda {
    private List<Contato> contatos;

    public Agenda() {
        contatos = new ArrayList<>();
    }

    public void adicionarContato(Contato contato) {
        if (contatoNaoExiste(contato.getId())) {
            contatos.add(contato);
            System.out.println("Contato adicionado. 4- Sair e salvar | 1 ao 3 - Continuar operações!");
        } else {
            System.out.println("Erro: Já existe um contato com o mesmo ID.");
        }
    }

    public void removerContato(Long id) {
        Contato contatoRemover = getContatoById(id);
        if (contatoRemover != null) {
            contatos.remove(contatoRemover);
            System.out.println("Contato removido com sucesso!");
        } else {
            System.out.println("Erro: Contato não encontrado com o ID fornecido.");
        }
    }

    public void editarContato(Long id, String novoNome, String novoSobreNome) {
        Contato contato = getContatoById(id);
        if (contato != null) {
            contato.setNome(novoNome);
            contato.setSobreNome(novoSobreNome);
            System.out.println("Contato editado com sucesso!");
        } else {
            System.out.println("Erro: Contato não encontrado com o ID fornecido.");
        }
    }

    public List<Contato> getContatos() {
        return contatos;
    }

    public Contato getContatoById(Long id) {
        return contatos.stream()
                .filter(contato -> contato.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public void adicionarTelefoneAoContato(Long contatoId, Telefone novoTelefone) {
        Contato contato = getContatoById(contatoId);
        if (contato != null) {
            List<Telefone> telefones = contato.getTelefones();
            if (!telefones.contains(novoTelefone)) {
                telefones.add(novoTelefone);
                System.out.println("Telefone adicionado ao contato com sucesso!");
            } else {
                System.out.println("Erro: Este telefone já está cadastrado para este contato.");
            }
        } else {
            System.out.println("Erro: Contato não encontrado com o ID fornecido.");
        }
    }

    public boolean telefoneJaCadastrado(Long numero) {
        return contatos.stream()
                .anyMatch(contato -> contato.telefoneJaCadastradoNoContato(numero));
    }

    public boolean telefoneJaCadastradoEmOutroContato(Long contatoId, Long numero) {
        return contatos.stream()
                .anyMatch(contato -> !contato.getId().equals(contatoId) && contato.telefoneJaCadastradoNoContato(numero));
    }

    private boolean contatoNaoExiste(Long contatoId) {
        return getContatoById(contatoId) == null;
    }
}

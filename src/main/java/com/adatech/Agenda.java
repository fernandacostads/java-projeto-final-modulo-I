package com.adatech;

import java.util.ArrayList;
import java.util.List;
 public class Agenda {
      private List<Contato> contatos;

      public Agenda() {
          contatos = new ArrayList<>();
      }

//      public void adicionarContato(Contato contato) {
//          if (!contatos.contains(contato)) {
//              contatos.add(contato);
//              System.out.println("Contato adicionado com sucesso!");
//          } else {
//              System.out.println("Erro: Já existe um contato com o mesmo ID.");
//          }
//      }

     public void adicionarContato(Contato contato) {
         if (getContatoById(contato.getId()) == null) {
             contatos.add(contato);
             System.out.println("Contato adicionado. 4- Sair e salvar | 1 ao 3 - Continuar operações!");

         } else {
             System.out.println("Erro: Já existe um contato com o mesmo ID.");
         }
     }
      public void removerContato(Long id) {
          Contato contatoRemover = null;
          for (Contato contato : contatos) {
              if (contato.getId().equals(id)) {
                  contatoRemover = contato;
                  break;
              }
          }

          if (contatoRemover != null) {
              contatos.remove(contatoRemover);
              System.out.println("Contato removido com sucesso!");
          } else {
              System.out.println("Erro: Contato não encontrado com o ID fornecido.");
          }
      }

      public void editarContato(Long id, String novoNome, String novoSobreNome) {
          for (Contato contato : contatos) {
              if (contato.getId().equals(id)) {
                  contato.setNome(novoNome);
                  contato.setSobreNome(novoSobreNome);
                  System.out.println("Contato editado com sucesso!");
                  return;
              }
          }
          System.out.println("Erro: Contato não encontrado com o ID fornecido.");
      }

      public List<Contato> getContatos() {
          return contatos;
      }

      public Contato getContatoById(Long id) {
          for (Contato contato : contatos) {
              if (contato.getId().equals(id)) {
                  return contato;
              }
          }
          return null;
      }

      public void adicionarTelefoneAoContato(Long novoId, Telefone novoTelefone) {
          Contato contato = getContatoById(novoId);
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

    }


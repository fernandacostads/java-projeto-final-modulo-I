package com.adatech;

import java.util.ArrayList;
import java.util.List;

public class Agenda {
    private List<Contato> contatos;

    public Agenda() {
        contatos = new ArrayList<>();
    }

    public void adicionarContato(Contato contato) {
        if (getContatoById(contato.getId()) == null) {
            contatos.add(contato);
//            System.out.println("Contato adicionado. 4- Sair e salvar | 1 ao 3 - Continuar operações!");
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

    public void editarTelefone(Long contatoId, Long telefoneId, String novoDdd, Long novoNumero) {
        Contato contato = getContatoById(contatoId);
        if (contato != null) {
            Telefone telefone = contato.getTelefoneById(telefoneId);
            if (telefone != null) {
                telefone.setDdd(novoDdd);
                telefone.setNumero(novoNumero);
                System.out.println("Telefone editado com sucesso!");
            } else {
                System.out.println("Erro: Telefone não encontrado no contato.");
            }
        } else {
            System.out.println("Erro: Contato não encontrado com o ID fornecido.");
        }
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
            contato.adicionarTelefone(novoTelefone);
        } else {
            System.out.println("Erro: Contato não encontrado com o ID fornecido.");
        }
    }

    public boolean telefoneJaCadastrado(Long numero) {
        for (Contato contato : contatos) {
            if (contato.telefoneJaCadastrado(numero)) {
                return true;  // Número de telefone já existe em algum contato
            }
        }
        return false;  // Número de telefone não encontrado em nenhum contato
    }

    public boolean telefoneJaCadastradoEmOutroContato(Long contatoId, Long numero) {
        for (Contato contato : contatos) {
            if (!contato.getId().equals(contatoId) && contato.telefoneJaCadastrado(numero)) {
                return true;  // Número de telefone já existe em outro contato
            }
        }
        return false;  // Número de telefone não encontrado em outros contatos
    }
}


//package com.adatech;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class Agenda {
//    private List<Contato> contatos;
//
//    public Agenda() {
//        contatos = new ArrayList<>();
//    }
//
//    public void adicionarContato(Contato contato) {
//        if (getContatoById(contato.getId()) == null) {
//            contatos.add(contato);
//            System.out.println("Contato adicionado. 4- Sair e salvar | 1 ao 3 - Continuar operações!");
//        } else {
//            System.out.println("Erro: Já existe um contato com o mesmo ID.");
//        }
//    }
//
//    public void removerContato(Long id) {
//        Contato contatoRemover = null;
//        for (Contato contato : contatos) {
//            if (contato.getId().equals(id)) {
//                contatoRemover = contato;
//                break;
//            }
//        }
//
//        if (contatoRemover != null) {
//            contatos.remove(contatoRemover);
//            System.out.println("Contato removido com sucesso!");
//        } else {
//            System.out.println("Erro: Contato não encontrado com o ID fornecido.");
//        }
//    }
//
//    public void editarContato(Long id, String novoNome, String novoSobreNome) {
//        for (Contato contato : contatos) {
//            if (contato.getId().equals(id)) {
//                contato.setNome(novoNome);
//                contato.setSobreNome(novoSobreNome);
//                System.out.println("Contato editado com sucesso!");
//                return;
//            }
//        }
//        System.out.println("Erro: Contato não encontrado com o ID fornecido.");
//    }
//
//    public List<Contato> getContatos() {
//        return contatos;
//    }
//
//    public Contato getContatoById(Long id) {
//        for (Contato contato : contatos) {
//            if (contato.getId().equals(id)) {
//                return contato;
//            }
//        }
//        return null;
//    }
//
////    public void adicionarTelefoneAoContato(Long novoId, Telefone novoTelefone) {
////        Contato contato = getContatoById(novoId);
////        if (contato != null) {
////            List<Telefone> telefones = contato.getTelefones();
////            if (!telefoneJaCadastradoEmOutroContato(contato.getId(), novoTelefone.getNumero())) {
////                telefones.add(novoTelefone);
////                System.out.println("Telefone adicionado ao contato com sucesso!");
////            } else {
////                System.out.println("Erro: Este telefone já está cadastrado em outro contato.");
////            }
////        } else {
////            System.out.println("Erro: Contato não encontrado com o ID fornecido.");
////        }
////    }
//
//    public void adicionarTelefoneAoContato(Long novoId, Telefone novoTelefone) {
//        Contato contato = getContatoById(novoId);
//        if (contato != null) {
//            contato.adicionarTelefone(novoTelefone);
//        } else {
//            System.out.println("Erro: Contato não encontrado com o ID fornecido.");
//        }
//    }
//    public boolean telefoneJaCadastrado(Long numero) {
//        for (Contato contato : contatos) {
//            if (contato.telefoneJaCadastrado(numero)) {
//                return true;  // Phone number already exists in some contact
//            }
//        }
//        return false;  // Phone number not found in any contact
//    }
//
//    public boolean telefoneJaCadastradoEmOutroContato(Long contatoId, Long numero) {
//        for (Contato contato : contatos) {
//            if (!contato.getId().equals(contatoId) && contato.telefoneJaCadastrado(numero)) {
//                return true;  // Número de telefone já existe em outro contato
//            }
//        }
//        return false;  // Número de telefone não encontrado em outros contatos
//    }
//}

package com.adatech;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Agenda agenda = AgendaFileHandler.loadAgenda();
        Scanner scanner = new Scanner(System.in);

        int opcao;
        do {
            exibirMenu(agenda);

            while (!scanner.hasNextInt()) {
                System.out.println("Opção inválida. Tente novamente.");
                scanner.next(); // Consume invalid input
            }
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    adicionarContato(scanner, agenda);
                    break;
                case 2:
                    removerContato(scanner, agenda);
                    break;
                case 3:
                    editarContato(scanner, agenda);
                    break;
                case 4:
                    salvarAgenda(agenda);
                    System.out.println("Mudanças detectadas. Pressione Enter para salvar...");
                    scanner.nextLine(); // Wait for user input
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 4);

        scanner.close();
    }

    private static void exibirMenu(Agenda agenda) {
        System.out.println("##################");
        System.out.println("##### AGENDA #####");
        System.out.println("##################\n");
        System.out.println(">>>> Contatos <<<<");
        System.out.println("Id | Nome");
        for (Contato contato : agenda.getContatos()) {
            System.out.println(contato);
        }
        System.out.println(">>>> Menu <<<<");
        System.out.println("1 - Adicionar Contato");
        System.out.println("2 - Remover Contato");
        System.out.println("3 - Editar Contato");
        System.out.println("4 - Sair");
        System.out.print("Escolha uma opção: ");
    }

    private static void adicionarContato(Scanner scanner, Agenda agenda) {
        System.out.print("Digite o ID do novo contato: ");
        Long novoId = scanner.nextLong();
        scanner.nextLine();
        if (agenda.getContatoById(novoId) != null) {
            System.out.println("Erro: Já existe um contato com o mesmo ID.");
            System.out.println("Pressione Enter para reiniciar...");
            scanner.nextLine();
            return;
        }
        System.out.print("Digite o nome do novo contato: ");
        String novoNome = scanner.nextLine();

        System.out.print("Digite o sobrenome do novo contato: ");
        String novoSobreNome = scanner.nextLine();

        Contato novoContato = new Contato(novoId, novoNome, novoSobreNome, new ArrayList<>());
        agenda.adicionarContato(novoContato);

        adicionarTelefones(scanner, agenda, novoContato);
    }

    private static void adicionarTelefones(Scanner scanner, Agenda agenda, Contato contato) {
        System.out.println("Adicione telefones ao novo contato (Digite '0' para parar): ");

//        Contato contato = agenda.getContatoById(contatoId);
//        if (contato != null) {
            while (true) {
                System.out.print("Digite o DDD do telefone (ou '0' para parar): ");
                String ddd = scanner.nextLine();
                if (ddd.equals("0")) {
                    break;
                }

                System.out.print("Digite o número do telefone: ");
                Long numero = scanner.nextLong();
                scanner.nextLine(); // Limpar o buffer do teclado

                Telefone novoTelefone = new Telefone(null, ddd, numero);

                if (agenda.telefoneJaCadastrado(novoTelefone.getNumero())) {
                    System.out.println("Erro: Este telefone já está cadastrado.");
                    continue;
                }

                contato.adicionarTelefone(novoTelefone);
            }
//        } else {
//            System.out.println("Contato não encontrado");
//        }
    }

    private static void removerContato(Scanner scanner, Agenda agenda) {
        System.out.print("Digite o ID do contato que deseja remover: ");
        Long idRemover = scanner.nextLong();
        scanner.nextLine();

        agenda.removerContato(idRemover);
    }

    private static void editarContato(Scanner scanner, Agenda agenda) {
        System.out.print("Digite o ID do contato que deseja editar: ");
        Long idEditar = scanner.nextLong();
        scanner.nextLine();

        Contato contatoEditar = agenda.getContatoById(idEditar);
        if (contatoEditar != null) {
            System.out.print("Digite o novo nome: ");
            String novoNome = scanner.nextLine();

            System.out.print("Digite o novo sobrenome: ");
            String novoSobreNome = scanner.nextLine();

            agenda.editarContato(idEditar, novoNome, novoSobreNome);
        } else {
            System.out.println("Contato não encontrado com o ID fornecido.");
        }
    }

    private static void salvarAgenda(Agenda agenda) {
        AgendaFileHandler.saveAgenda(agenda);
    }
}

//package com.adatech;
//
//import java.util.ArrayList;
//import java.util.Scanner;
//
//public class Main {
//    public static void main(String[] args) {
//        Agenda agenda = AgendaFileHandler.loadAgenda();
//        Scanner scanner = new Scanner(System.in);
//
//        int opcao;
//        do {
//            exibirMenu(agenda);
//
//            while (!scanner.hasNextInt()) {
//                System.out.println("Opção inválida. Tente novamente.");
//                scanner.next(); // Consume invalid input
//            }
//            opcao = scanner.nextInt();
//            scanner.nextLine();
//
//            switch (opcao) {
//                case 1:
//                    adicionarContato(scanner, agenda);
//                    break;
//                case 2:
//                    removerContato(scanner, agenda);
//                    break;
//                case 3:
//                    editarContato(scanner, agenda);
//                    break;
//                case 4:
//                    salvarAgenda(agenda);
//                    System.out.println("Mudanças detectadas. Pressione Enter para salvar...");
//                    scanner.nextLine(); // Wait for user input
//                    break;
//                default:
//                    System.out.println("Opção inválida. Tente novamente.");
//            }
//        } while (opcao != 4);
//
//        scanner.close();
//    }
//
//    private static void exibirMenu(Agenda agenda) {
//        System.out.println("##################");
//        System.out.println("##### AGENDA #####");
//        System.out.println("##################\n");
//        System.out.println(">>>> Contatos <<<<");
//        System.out.println("Id | Nome");
//        for (Contato contato : agenda.getContatos()) {
//            System.out.println(contato);
//        }
//        System.out.println(">>>> Menu <<<<");
//        System.out.println("1 - Adicionar Contato");
//        System.out.println("2 - Remover Contato");
//        System.out.println("3 - Editar Contato");
//        System.out.println("4 - Sair");
//        System.out.print("Escolha uma opção: ");
//    }
//
//    private static void adicionarContato(Scanner scanner, Agenda agenda) {
//        System.out.print("Digite o ID do novo contato: ");
//        Long novoId = scanner.nextLong();
//        scanner.nextLine();
//        if (agenda.getContatoById(novoId) != null) {
//            System.out.println("Erro: Já existe um contato com o mesmo ID.");
//            System.out.println("Pressione Enter para reiniciar...");
//            scanner.nextLine();
//            return;
//        }
//        System.out.print("Digite o nome do novo contato: ");
//        String novoNome = scanner.nextLine();
//
//        System.out.print("Digite o sobrenome do novo contato: ");
//        String novoSobreNome = scanner.nextLine();
//
//        Contato novoContato = new Contato(novoId, novoNome, novoSobreNome, new ArrayList<>());
//        agenda.adicionarContato(novoContato);
//
//        adicionarTelefones(scanner, agenda, novoId);
//    }
//
//    private static void adicionarTelefones(Scanner scanner, Agenda agenda, Long contatoId) {
//        System.out.println("Adicione telefones ao novo contato (Digite '0' para parar): ");
//
//        Contato contato = agenda.getContatoById(contatoId);
//        if (contato != null) {
//            while (true) {
//                System.out.print("Digite o DDD do telefone (ou '0' para parar): ");
//                String ddd = scanner.nextLine();
//                if (ddd.equals("0")) {
//                    break;
//                }
//
//                System.out.print("Digite o número do telefone: ");
//                Long numero = scanner.nextLong();
//                scanner.nextLine(); // Limpar o buffer do teclado
//
//                Telefone novoTelefone = new Telefone(null, ddd, numero);
//                contato.adicionarTelefone(novoTelefone);
//
//                if (agenda.telefoneJaCadastrado(numero)) {
//                    System.out.println("Erro: Este telefone já está cadastrado no mesmo contato.");
//                    continue;
//                }
//
//                if (agenda.telefoneJaCadastradoEmOutroContato(contatoId, numero)) {
//                    System.out.println("Erro: Este telefone já está cadastrado em outro contato.");
//                    continue;
//                }
//            }
//
//
//        } else {
//            System.out.println("Contato não encontrado");
//        }
//    }
//
//    private static void removerContato(Scanner scanner, Agenda agenda) {
//        System.out.print("Digite o ID do contato que deseja remover: ");
//        Long idRemover = scanner.nextLong();
//        scanner.nextLine();
//
//        agenda.removerContato(idRemover);
//    }
//
//    private static void editarContato(Scanner scanner, Agenda agenda) {
//        System.out.print("Digite o ID do contato que deseja editar: ");
//        Long idEditar = scanner.nextLong();
//        scanner.nextLine();
//
//        Contato contatoEditar = agenda.getContatoById(idEditar);
//        if (contatoEditar != null) {
//            System.out.print("Digite o novo nome: ");
//            String novoNome = scanner.nextLine();
//
//            System.out.print("Digite o novo sobrenome: ");
//            String novoSobreNome = scanner.nextLine();
//
//            agenda.editarContato(idEditar, novoNome, novoSobreNome);
//        } else {
//            System.out.println("Contato não encontrado com o ID fornecido.");
//        }
//    }
//
//    private static void salvarAgenda(Agenda agenda) {
//        AgendaFileHandler.saveAgenda(agenda);
//    }
//}

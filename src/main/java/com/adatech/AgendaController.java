package com.adatech;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class AgendaController {
    private final Agenda agenda;
    private final Scanner scanner;

    public AgendaController(Agenda agenda, Scanner scanner) {
        this.agenda = agenda;
        this.scanner = scanner;
    }

    public void iniciar() {
        int opcao;
        do {
            exibirMenu();

            while (!scanner.hasNextInt()) {
                System.out.println("Opção inválida. Tente novamente.");
                scanner.next();
            }
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    adicionarContato();
                    break;
                case 2:
                    removerContato();
                    break;
                case 3:
                    editarContato();
                    break;
                case 4:
                    salvarAgenda();
                    System.out.println("Mudanças detectadas. Pressione Enter para salvar...");
                    scanner.nextLine();
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente: ");
            }
        } while (opcao != 4);

        scanner.close();
    }

    private void exibirMenu() {
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

    private void adicionarContato() {
        try {
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

            Contato novoContato = new Contato( novoId, novoNome, novoSobreNome, new ArrayList<>());
            agenda.adicionarContato(novoContato);

            adicionarTelefones(scanner, agenda, novoContato);
        } catch (InputMismatchException e) {
            System.out.println("Erro: Entrada inválida. Certifique-se de digitar um número para o ID.");

        } catch (Exception e) {
            System.out.println("Erro desconhecido ao adicionar contato: " + e.getMessage());
        }
    }

    private static void adicionarTelefones(Scanner scanner, Agenda agenda, Contato contato) {
        System.out.println("Adicione telefones ao novo contato (Digite '0' para parar): ");

        while (true) {
            try {
                System.out.print("Digite o DDD do telefone (ou '0' para parar): ");
                String ddd = scanner.nextLine();
                if (ddd.equals("0")) {
                    break;
                }

                System.out.print("Digite o número do telefone: ");
                Long numero = scanner.nextLong();
                scanner.nextLine();

                Telefone novoTelefone = new Telefone(null, ddd, numero);

                if (agenda.telefoneJaCadastrado(novoTelefone.getNumero())) {
                    System.out.println("Erro: Este telefone já está cadastrado.");
                    continue;
                }

                contato.adicionarTelefone(novoTelefone);

            } catch (InputMismatchException e) {
                System.out.println("Erro: Entrada inválida. Certifique-se de digitar um número para o telefone.");
                scanner.nextLine();
            } catch (Exception e) {
                System.out.println("Erro inesperado ao adicionar telefone: " + e.getMessage());
                scanner.nextLine();
            }
        }
    }

    private void removerContato() {
        try {
            System.out.print("Digite o ID do contato que deseja remover: ");
            Long idRemover = scanner.nextLong();
            scanner.nextLine();

            agenda.removerContato(idRemover);
        } catch (InputMismatchException e) {
            System.out.println("Erro: Entrada inválida. Certifique-se de digitar um número para o ID do contato.");
            scanner.nextLine();
        } catch (Exception e) {
            System.out.println("Erro inesperado ao remover contato: " + e.getMessage());
            scanner.nextLine();
        }
    }


    private void editarContato() {
        try {
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

                System.out.print("Deseja editar os telefones? (S/N): ");
                String editarTelefonesOpcao = scanner.nextLine();
                if (editarTelefonesOpcao.equalsIgnoreCase("S")) {
                    editarTelefones(scanner, agenda, idEditar);
                }
            } else {
                System.out.println("Contato não encontrado com o ID fornecido.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Erro: Entrada inválida. Certifique-se de digitar um número para o ID do contato.");
            scanner.nextLine();
        } catch (Exception e) {
            System.out.println("Erro inesperado ao editar contato: " + e.getMessage());
            scanner.nextLine();
        }
    }


    private void editarTelefones(Scanner scanner, Agenda agenda, Long contatoId) {
        Contato contato = agenda.getContatoById(contatoId);
        if (contato != null) {
            System.out.println("Telefones do Contato:");
            for (Telefone telefone : contato.getTelefones()) {
                System.out.println(telefone.getId() + " | " + telefone.getDdd() + " " + telefone.getNumero());
            }

            System.out.print("Digite o ID do telefone que deseja editar: ");
            Long idTelefoneEditar = scanner.nextLong();
            scanner.nextLine();
            editarTelefoneDoContato(scanner, contato, idTelefoneEditar);


        } else {
            System.out.println("Contato não encontrado.");
        }
    }

    private void editarTelefoneDoContato(Scanner scanner, Contato contato, Long idTelefone) {
        System.out.print("Digite o novo DDD: ");
        String novoDdd = scanner.nextLine();

        System.out.print("Digite o novo número do telefone: ");
        Long novoNumero = scanner.nextLong();
        scanner.nextLine();

        contato.editarTelefone(idTelefone, novoDdd, novoNumero);
    }

    private void salvarAgenda() {
        AgendaFileHandler.salvaAgenda(agenda);
    }
}

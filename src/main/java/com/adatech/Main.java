package com.adatech;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.

import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Agenda agenda = AgendaFileHandler.loadAgenda();
        Scanner scanner = new Scanner(System.in);

        int opcao;
        do {
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

            while (!scanner.hasNextInt()) {
                System.out.println("Opção inválida. Tente novamente.");
                scanner.next(); // Consume invalid input
            }
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    System.out.print("Digite o ID do novo contato: ");
                    Long novoId = scanner.nextLong();
                    scanner.nextLine();
                    if (agenda.getContatoById(novoId) != null) {
                        System.out.println("Erro: Já existe um contato com o mesmo ID.");
                        System.out.println("Pressione Enter para reiniciar...");
                        scanner.nextLine();
                        break;
                    }
                    System.out.print("Digite o nome do novo contato: ");
                    String novoNome = scanner.nextLine();

                    System.out.print("Digite o sobrenome do novo contato: ");
                    String novoSobreNome = scanner.nextLine();

                    Contato novoContato = new Contato(novoId, novoNome, novoSobreNome, new ArrayList<>());
                    agenda.adicionarContato(novoContato);

                    System.out.println("Adicione telefones ao novo contato (Digite '0' para parar): ");
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
                        agenda.adicionarTelefoneAoContato(novoId, novoTelefone);
                    }
                    break;
                case 2:
                    // TODO:Implementar a lógica para remover contato
                    break;
                case 3:
                    // TO DO: Implementar a lógica para editar contato
                    break;
                case 4:

                    AgendaFileHandler.saveAgenda(agenda);
                    System.out.println("Mudanças detectadas. Pressione Enter para salvar...");
                    scanner.nextLine(); // Wait for user input
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 4);

        scanner.close();
    }
}






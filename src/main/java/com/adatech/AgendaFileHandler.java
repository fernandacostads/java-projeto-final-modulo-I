package com.adatech;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class AgendaFileHandler {
    private static final String FILE_PATH = "agenda.txt";

    public static void saveAgenda(Agenda agenda) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Contato contato : agenda.getContatos()) {
                writer.write(contato.getId() + "," + contato.getNome() + "," + contato.getSobreNome());
                List<Telefone> telefones = contato.getTelefones();
                for (Telefone telefone : telefones) {
                    writer.write("," + telefone.getDdd() + "," + telefone.getNumero());
                }
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Agenda loadAgenda() {
        Agenda agenda = new Agenda();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 3) {
                    try {
                        Long id = Long.parseLong(parts[0].trim());
                        String nome = parts[1].trim();
                        String sobreNome = parts[2].trim();
                        List<Telefone> telefones = new ArrayList<>();  // Adapte conforme necessário
                        Contato contato = new Contato(id, nome, sobreNome, telefones);
                        agenda.adicionarContato(contato);
                    } catch (NumberFormatException e) {
                        System.err.println("Erro ao converter número: " + e.getMessage());
                    }
                } else {
                    System.err.println("Linha inválida no arquivo: " + line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return agenda;
    }
}

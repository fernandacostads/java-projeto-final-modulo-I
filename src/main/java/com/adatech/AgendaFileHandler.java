package com.adatech;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class AgendaFileHandler {
    private static final String FILE_PATH = "agenda.txt";

    public static void saveAgenda(Agenda agenda) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Contato contato : agenda.getContatos()) {
                writeContato(writer, contato);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void writeContato(BufferedWriter writer, Contato contato) throws IOException {
        writer.write(String.format("%d,%s,%s", contato.getId(), contato.getNome(), contato.getSobreNome()));

        List<Telefone> telefones = contato.getTelefones();
        if (telefones != null) {
            for (Telefone telefone : telefones) {
                writer.write(String.format(",%s,%d", telefone.getDdd(), telefone.getNumero()));
            }
        }

        writer.newLine();
    }

    public static Agenda loadAgenda() {
        Agenda agenda = new Agenda();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                processLine(agenda, line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return agenda;
    }

    private static void processLine(Agenda agenda, String line) {
        String[] parts = line.split(",");
        if (parts.length >= 3) {
            try {
                Long id = Long.parseLong(parts[0].trim());
                String nome = parts[1].trim();
                String sobreNome = parts[2].trim();
                List<Telefone> telefones = createTelefonesAndAddToContato(parts);
                Contato contato = new Contato(id, nome, sobreNome, telefones);
                agenda.adicionarContato(contato);
            } catch (NumberFormatException e) {
                System.err.println("Erro ao converter número: " + e.getMessage());
            }
        } else {
            System.err.println("Linha inválida no arquivo: " + line);
        }
    }

    private static List<Telefone> createTelefonesAndAddToContato(String[] parts) {
        List<Telefone> telefones = new ArrayList<>();
        Long idTelefone = 1L; // Começando a partir do ID 1
        for (int i = 3; i < parts.length; i += 2) {
            try {
                String ddd = parts[i].trim();
                Long numero = Long.parseLong(parts[i + 1].trim());
                telefones.add(new Telefone(idTelefone++, ddd, numero));
            } catch (NumberFormatException e) {
                System.err.println("Erro ao converter número: " + e.getMessage());
            }
        }
        return telefones;
    }

}


//package com.adatech;
//
//import java.io.*;
//import java.util.ArrayList;
//import java.util.List;
//
//public class AgendaFileHandler {
//    private static final String FILE_PATH = "agenda.txt";
//
//    public static void saveAgenda(Agenda agenda) {
//        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
//            for (Contato contato : agenda.getContatos()) {
//                writeContato(writer, contato);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    private static void writeContato(BufferedWriter writer, Contato contato) throws IOException {
//        writer.write(String.format("%d,%s,%s", contato.getId(), contato.getNome(), contato.getSobreNome()));
//
//        List<Telefone> telefones = contato.getTelefones();
//        if (telefones != null) {
//            for (Telefone telefone : telefones) {
//                writer.write(String.format(",%s,%d", telefone.getDdd(), telefone.getNumero()));
//            }
//        }
//
//        writer.newLine();
//    }
//
//    public static Agenda loadAgenda() {
//        Agenda agenda = new Agenda();
//        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
//            String line;
//            while ((line = reader.readLine()) != null) {
//                processLine(agenda, line);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return agenda;
//    }
//
////    private static void processLine(Agenda agenda, String line) {
////        String[] parts = line.split(",");
////        if (parts.length >= 3) {
////            try {
////                Long id = Long.parseLong(parts[0].trim());
////                String nome = parts[1].trim();
////                String sobreNome = parts[2].trim();
////                Contato contato = new Contato(id, nome, sobreNome, new ArrayList<>());
//////                createTelefonesAndAddToContato(contato, parts);
////                agenda.adicionarContato(contato);
////            } catch (NumberFormatException e) {
////                System.err.println("Erro ao converter número: " + e.getMessage());
////            }
////        } else {
////            System.err.println("Linha inválida no arquivo: " + line);
////        }
////    }
//private static void processLine(Agenda agenda, String line) {
//    String[] parts = line.split(",");
//    if (parts.length >= 3) {
//        try {
//            Long id = Long.parseLong(parts[0].trim());
//            String nome = parts[1].trim();
//            String sobreNome = parts[2].trim();
//            List<Telefone> telefones = createTelefonesAndAddToContato(parts);
//            Contato contato = new Contato(id, nome, sobreNome, telefones);
//            agenda.adicionarContato(contato);
//        } catch (NumberFormatException e) {
//            System.err.println("Erro ao converter número: " + e.getMessage());
//        }
//    } else {
//        System.err.println("Linha inválida no arquivo: " + line);
//    }
//}
//
////    private static void createTelefonesAndAddToContato(Contato contato, String[] parts) {
////        for (int i = 3; i < parts.length; i += 2) {
////            try {
////                String ddd = parts[i].trim();
////                Long numero = Long.parseLong(parts[i + 1].trim());
////                Telefone telefone = new Telefone(null, ddd, numero);
////                contato.adicionarTelefone(telefone);
////            } catch (NumberFormatException e) {
////                System.err.println("Erro ao converter número: " + e.getMessage());
////            }
////        }
////    }
//private static List<Telefone> createTelefonesAndAddToContato(String[] parts) {
//    List<Telefone> telefones = new ArrayList<>();
//    for (int i = 3; i < parts.length; i += 2) {
//        try {
//            String ddd = parts[i].trim();
//            Long numero = Long.parseLong(parts[i + 1].trim());
//            telefones.add(new Telefone(null, ddd, numero));
//        } catch (NumberFormatException e) {
//            System.err.println("Erro ao converter número: " + e.getMessage());
//        }
//    }
//    return telefones;
//}
//}

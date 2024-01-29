package com.adatech;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Agenda agenda = AgendaFileHandler.carregaAgenda();
        Scanner scanner = new Scanner(System.in);
        AgendaController agendaController = new AgendaController(agenda, scanner);
        agendaController.iniciar();

    }
}

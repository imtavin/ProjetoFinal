package br.edu.up.CinemaManager.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Util {

    public static String contatenaAssentos(List<String> assentosDisponiveis){
        StringBuilder assentosStr = new StringBuilder();

        for (int i = 0; i < assentosDisponiveis.size(); i++) {
            assentosStr.append(assentosDisponiveis.get(i));
            if (i != assentosDisponiveis.size() - 1) {
                assentosStr.append("-");
            }
        }
        return assentosStr.toString();
    }

    public static void consumirQuebraDeLinha(Scanner scanner) {
        scanner.nextLine();
    }

    public static boolean validateCPF(String cpf){
        final String CPF_REGEX = "\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}";
        return Pattern.matches(CPF_REGEX, cpf);
    }

    public static String obterHorarioAtual(){
        LocalDateTime agora = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        return agora.format(formatter);
    }
}

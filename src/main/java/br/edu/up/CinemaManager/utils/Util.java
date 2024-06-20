package br.edu.up.CinemaManager.utils;

import java.util.List;
import java.util.Scanner;

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

    public static void consumirQuebraDeLinha(){
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
    }
}

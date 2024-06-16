package br.edu.up.CinemaManager.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class IdUtils {
    private static final Logger logger = LogManager.getLogger(IdUtils.class);

    private static Integer idSessao = 0;
    private static Integer idFilme = 0;

    public static Integer newIdSessao(){
        idSessao = idSessao + 1;
        return idSessao;
    }

    public static Integer newIdFilme (){
        idFilme = idFilme + 1;
        return idFilme;
    }

    public static Integer getIdSessao() {
        return idSessao;
    }

    public static void setIdSessao(Integer idSessao) {
        IdUtils.idSessao = idSessao;
    }

    public static Integer getIdFilme() {
        return idFilme;
    }

    public static void setIdFilme(Integer idFilme) {
        IdUtils.idFilme = idFilme;
    }
}

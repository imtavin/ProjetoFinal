package br.edu.up.CinemaManager.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class IdUtils {
    private static final Logger logger = LogManager.getLogger(IdUtils.class);

    private static Integer idSessao = 0;
    private static Integer idFilme = 0;

    public static void setIdSessao(Integer id) {
        idSessao = id;
    }

    public static void setIdFilme(Integer id) {
        idFilme = id;
    }

    public static Integer newIdSessao(){
        idSessao = idSessao + 1;
        return idSessao;
    }

    public static Integer newIdFilme (){
        idFilme = idFilme + 1;
        return idFilme;
    }

}

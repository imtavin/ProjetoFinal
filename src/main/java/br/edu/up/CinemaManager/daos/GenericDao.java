package br.edu.up.CinemaManager.daos;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public interface GenericDao<T> {
    void salvar(List<T> entities);
    List<T> carregar();
}
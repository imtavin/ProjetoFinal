package br.edu.up.CinemaManager.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class AbstractCRUD<T> {
    protected List<T> lista;

    public AbstractCRUD() {
        this.lista = new ArrayList<>();
    }

    public void create(T item) {
        lista.add(item);
    }

    public List<T> readAll() {
        return lista;
    }

    public Optional<T> readById(int id) {
        return lista.stream()
                .filter(item -> getId(item) == id)
                .findFirst();
    }

    public boolean update(int id, T newItem) {
        Optional<T> optionalItem = readById(id);
        if (optionalItem.isPresent()) {
            int index = lista.indexOf(optionalItem.get());
            lista.set(index, newItem);
            return true;
        }
        return false;
    }

    public boolean delete(int id) {
        Optional<T> optionalItem = readById(id);
        if (optionalItem.isPresent()) {
            lista.remove(optionalItem.get());
            return true;
        }
        return false;
    }

    protected abstract int getId(T item);
}

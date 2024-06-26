package br.edu.up.CinemaManager.controllers;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractCRUD<T> {
    protected List<T> items = new ArrayList<>();

    public void create(T item) {
        items.add(item);
    }

    public void delete(T item) {
        items.remove(item);
    }
}

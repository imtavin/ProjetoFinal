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

    public T find(T item) {
        int index = items.indexOf(item);
        if (index != -1) {
            return items.get(index);
        }
        return null;
    }

    public List<T> findAll() {
        return new ArrayList<>(items);
    }

    public void update(T oldItem, T newItem) {
        int index = items.indexOf(oldItem);
        if (index != -1) {
            items.set(index, newItem);
        }
    }
}

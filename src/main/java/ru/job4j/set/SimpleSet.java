package ru.job4j.set;

import ru.job4j.list.SimpleArrayList;
import java.util.Iterator;
import java.util.Objects;

public class SimpleSet<T> implements Set<T> {

    private SimpleArrayList<T> container = new SimpleArrayList<>(16);

    @Override
    public boolean add(T value) {
        if (contains(value)) {
            return false;
        }
        container.add(value);
        return true;
    }

    @Override
    public boolean contains(T value) {
        for (T t : this) {
            if (Objects.equals(t, value)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Iterator<T> iterator() {
        return container.iterator();
    }
}
package ru.job4j.set;

import ru.job4j.list.SimpleArrayList;
import java.util.Iterator;

public class SimpleSet<T> implements Set<T> {

    private SimpleArrayList<T> set = new SimpleArrayList<>(16);

    @Override
    public boolean add(T value) {
        if (contains(value)) {
            return false;
        }
        set.add(value);
        return true;
    }

    @Override
    public boolean contains(T value) {
        for (T t : this) {
            if (t != null && t.equals(value)) {
                return true;
            } else if (t == value) {
                return true;
            }

        }
        return false;
    }

    @Override
    public Iterator<T> iterator() {
        return set.iterator();
    }
}
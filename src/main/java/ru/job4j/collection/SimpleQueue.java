package ru.job4j.collection;

import java.util.EmptyStackException;

public class SimpleQueue<T> {
    private final SimpleStack<T> in = new SimpleStack<>();
    private final SimpleStack<T> out = new SimpleStack<>();

    public T poll() {
        if (in.getSize() == 0 && out.getSize() == 0) {
            throw new EmptyStackException();
        }
        if (out.getSize() == 0) {
            while (in.getSize() != 0) {
                out.push(in.pop());
            }
        }
        return out.pop();
    }

    public void push(T value) {
        in.push(value);
    }
}

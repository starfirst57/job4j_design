package ru.job4j.map;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleMap<K, V> implements Map<K, V> {

    private static final float LOAD_FACTOR = 0.75f;

    private int capacity = 8;

    private int count = 0;

    private int modCount = 0;

    private MapEntry<K, V>[] table = new MapEntry[capacity];

    private int hash(int hashCode) {
        return hashCode & (capacity - 1);
    }

    private int indexFor(int hash) {
        return hash % capacity;
    }

    private void expand() {
        capacity *= 2;
        MapEntry<K, V>[] temp = new MapEntry[capacity];
        for (MapEntry<K, V> element : table) {
            if (element != null) {
                temp[indexFor(hash(element.getKey().hashCode()))] = element;
            }
        }
        table = temp;
    }

    @Override
    public boolean put(K key, V value) {
        if (count / capacity >= LOAD_FACTOR) {
            expand();
        }
        var rsl = false;
        var index = indexFor(hash(key.hashCode()));
        if (table[index] == null) {
            table[index] = new MapEntry<>(key, value);
            count++;
            modCount++;
            rsl = true;
        }
        return rsl;
    }

    @Override
    public V get(K key) {
        var index = indexFor(hash(key.hashCode()));
        return table[index] != null && table[index].getKey().equals(key) ? table[index].getValue() : null;
    }

    @Override
    public boolean remove(K key) {
        var rsl = false;
        var index = indexFor(hash(key.hashCode()));
        if (table[index] != null && table[index].getKey().equals(key)) {
            table[index] = null;
            count--;
            modCount++;
            rsl = true;
        }
        return rsl;
    }

    @Override
    public Iterator<K> iterator() {
        return new Iterator<>() {
            private final int expectedModCount = modCount;
            int point = 0;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return point < capacity && count > 0;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                MapEntry<K, V> element = table[point];
                point++;

                return element == null ? next() : element.getKey();
            }
        };
    }

    private static class MapEntry<K, V> {

        K key;
        V value;

        public V getValue() {
            return value;
        }

        public K getKey() {
            return key;
        }

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }

    }
}

package ru.job4j.map;

import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.Assert.*;

public class SimpleMapTest {
        @Test
        public void putAndGet() {
            SimpleMap<Integer, String> map = new SimpleMap<>();
            assertTrue(map.put(1, "lock"));
            assertEquals(map.get(1), "lock");
        }

        @Test
        public void getUnexistedElement() {
            SimpleMap<Integer, String> map = new SimpleMap<>();
            assertNull(map.get(1));
        }

        @Test
        public void putTwice() {
            SimpleMap<Integer, String> map = new SimpleMap<>();
            map.put(1, "lock");
            assertFalse(map.put(1, "lock"));
        }

        @Test
        public void putAndRemove() {
            SimpleMap<Integer, String> map = new SimpleMap<>();
            assertTrue(map.put(1, "lock"));
            assertTrue(map.remove(1));
        }

        @Test
        public void removeUnexistedElement() {
            SimpleMap<Integer, String> map = new SimpleMap<>();
            assertFalse(map.remove(1));
        }

        @Test
        public void putWithExpand() {
            SimpleMap<Integer, String> map = new SimpleMap<>();
            map.put(1, "lock");
            map.put(2, "lock");
            map.put(3, "lock");
            map.put(4, "lock");
            map.put(5, "lock");
            map.put(6, "lock");
            map.put(7, "lock");
            map.put(8, "lock");
            assertTrue(map.put(9, "lock"));
        }

        @Test
        public void getElementAfterExpand() {
            SimpleMap<Integer, String> map = new SimpleMap<>();
            map.put(1, "Ivan");
            map.put(2, "Boris");
            map.put(3, "Ilia");
            map.put(4, "Nikolay");
            map.put(5, "Max");
            map.put(6, "Nikita");
            map.put(7, "Sasha");
            map.put(8, "Aleksey");
            assertEquals(map.get(2), "Boris");
        }

        @Test
        public void iteratorEmpty() {
            SimpleMap<Integer, String> map = new SimpleMap<>();
            assertFalse(map.iterator().hasNext());
        }

        @Test
        public void iteratorWithTwoElements() {
            SimpleMap<Integer, String> map = new SimpleMap<>();
            map.put(1, "Ivan");
            map.put(2, "Boris");
            assertTrue(map.iterator().hasNext());
        }

        @Test(expected = ConcurrentModificationException.class)
        public void whenModificate() {
            SimpleMap<Integer, String> map = new SimpleMap<>();
            map.put(1, "Ivan");
            map.put(2, "Boris");
            Iterator<Integer> iterator = map.iterator();
            iterator.next();
            map.put(3, "Boris");
            iterator.next();

        }

        @Test(expected = NoSuchElementException.class)
        public void whenNoElements() {
            SimpleMap map = new SimpleMap();
            map.iterator().next();
        }
}
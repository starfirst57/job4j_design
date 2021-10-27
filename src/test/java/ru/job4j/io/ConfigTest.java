package ru.job4j.io;

import org.junit.Test;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class ConfigTest {

    @Test
    public void whenPairWithoutComment() {
        String path = "./data/pair_without_comment.properties";
        Config config = new Config(path);
        config.load();
        assertEquals("Petr Arsentev", config.value("name"));
    }

    @Test
    public void whenPairWithComment() {
        String path = "./data/pair_with_comment.properties";
        Config config = new Config(path);
        config.load();
        assertEquals("Petr Arsentev", config.value("name"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenNoPairs() {
        String path = "./data/no_pairs.properties";
        Config config = new Config(path);
        config.load();
    }

    @Test
    public void whenTwoPairsWithComment() {
        String path = "./data/two_pairs_with_comment.properties";
        Config config = new Config(path);
        config.load();
        assertEquals(config.value("name"), "Petr Arsentev");
        assertEquals(config.value("login"), "test");
    }
}
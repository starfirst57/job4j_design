package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Config {

    private final String path;
    private final Map<String, String> values = new HashMap<>();

    public Config(final String path) {
        this.path = path;
    }

    public void load() {
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines().filter(line -> !line.startsWith("#")).peek(line -> {
                        if (!line.matches("^[a-zA-Z+. ]+[^ =][=][^ =][^ ][a-zA-Z+. ]+$")) {
                            throw new IllegalArgumentException("");
                        }
                    })
                    .map(line -> line.split("="))
                    .forEach(line -> values.put(line[0], line[1]));

        } catch (IllegalArgumentException ill) {
          throw ill;
        } catch (Exception e) {
             e.printStackTrace();
        }
    }

    public String value(String key) {
        return this.values.get(key);
    }

    @Override
    public String toString() {
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines().forEach(out::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return out.toString();
    }

    public static void main(String[] args) {
        Config conf = new Config("./data/no_pairs.properties");
        conf.load();
        System.out.println(conf.values);

    }

}

package ru.job4j.io;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ArgsName {

    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        return values.get(key);
    }
    public int getSize() {
        return values.size();
    }

    private void parse(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("empty arguments");
        }
        Arrays.stream(args).filter(line -> !line.startsWith("#")).peek(line -> {
                        if (!line.matches("^[-][a-zA-Z+. ]*[=]\\S")) {
                            throw new IllegalArgumentException("incorrect argument " + line);
                        }
                    })
                    .map(line -> line.split("="))
                    .forEach(line -> values.put(line[0].substring(1), line[1]));
    }

    public static ArgsName of(String[] args) {
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }
}
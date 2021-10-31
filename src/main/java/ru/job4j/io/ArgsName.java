package ru.job4j.io;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ArgsName {

    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        return values.get(key);
    }

    private void parse(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("empty arguments");
        }
        Arrays.stream(args).filter(line -> !line.startsWith("#")).peek(line -> {
                        if (!line.matches("^[-][a-zA-Z+. ]+[^ =][=][^ =][^ ][a-zA-Z0-9-+. ]+$")) {
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

    public static void main(String[] args) {
        ArgsName jvm = ArgsName.of(new String[] {"-Xmx=512", "-encoding=UTF-8"});
        System.out.println(jvm.get("Xmx"));

        ArgsName zip = ArgsName.of(new String[] {"-out=project.zip", "-encoding=UTF-8"});
        System.out.println(zip.get("out"));
    }
}
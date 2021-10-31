package ru.job4j.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;

public class Search {
    public static void main(String[] args) throws IOException {
        if (args.length != 2) {
            throw new IllegalArgumentException("Wrong number of parameters. Usage java -jar dir.jar ROOT_FOLDER FILE_TYPE");
        }
        System.out.println(args[0]);
        System.out.println(args[1]);
        if (!args[0].matches("^[A-Z][:].+")) {
            throw new IllegalArgumentException("ROOT_FOLDER example C:example/");
        }
        if (!args[1].matches("^[a-zA-Z0-9]+$")) {
            throw new IllegalArgumentException("FILE_TYPE example txt");
        }
        Path start = Paths.get(args[0]);
        search(start, p -> p.toFile().getName().endsWith(args[1])).forEach(System.out::println);
    }

    public static List<Path> search(Path root, Predicate<Path> condition) throws IOException {
        SearchFiles searcher = new SearchFiles(condition);
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }
}
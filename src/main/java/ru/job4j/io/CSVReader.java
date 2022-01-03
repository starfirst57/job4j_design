package ru.job4j.io;

import java.io.*;
import java.nio.file.Path;
import java.util.*;

public class CSVReader {
    public static void handle(ArgsName argsName) {
        String[] columns = argsName.get("filter").split(",");
        try (Scanner scanner = new Scanner(new File(argsName.get("path")))) {
            String[] header = scanner.nextLine().split(argsName.get("delimiter"));
            int[] headerIndexes = new int[columns.length];
            for (int i = 0; i < columns.length; i++) {
                for (int j = 0; j < header.length; j++) {
                    if (header[j].equals(columns[i])) {
                        headerIndexes[i] = j;
                    }
                }
                System.out.print(columns[i] + " ");
            }
            System.out.println();
            while (scanner.hasNext()) {
                String[] line = scanner.nextLine().split(argsName.get("delimiter"));
                for (int i = 0; i < headerIndexes.length; i++) {
                    System.out.print(line[headerIndexes[i]] + " ");
                }
                System.out.println();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static ArgsName validate(String[] args) {
        ArgsName arguments = ArgsName.of(args);
        if (arguments.getSize() != 4) {
            throw new IllegalArgumentException("Some arguments not exist");
        }

        if (!Path.of(arguments.get("path")).toFile().exists()) {
            throw new IllegalArgumentException("File not exist");
        }

        if (!Path.of(arguments.get("path")).toFile().isFile()) {
            throw new IllegalArgumentException(arguments.get("path") + " is not a file");
        }

        if (!Path.of(arguments.get("path")).toFile().getName().endsWith("csv")) {
            throw new IllegalArgumentException("File not a csv");
        }

        /*if (!Objects.equals(arguments.get("out"), "stdout") || !Path.of(arguments.get("out")).toFile().getName().endsWith("csv")) {
            System.out.println(arguments.get("out"));
            throw new IllegalArgumentException("argument -out must contain 'stdout' or csv file");
        }*/
        return arguments;
    }

    public static void main(String[] args) throws Exception {
        handle(validate(args));
    }
}
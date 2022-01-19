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

            if (!argsName.get("out").equals("stdout")) {
                try (FileOutputStream out = new FileOutputStream(argsName.get("out"))) {
                    for (int i = 0; i < columns.length; i++) {
                        for (int j = 0; j < header.length; j++) {
                            if (header[j].equals(columns[i])) {
                                headerIndexes[i] = j;
                            }
                        }
                            out.write((columns[i] + (columns.length - i > 1 ? argsName.get("delimiter")  : "")).getBytes());
                    }
                        out.write(System.lineSeparator().getBytes());
                    while (scanner.hasNext()) {
                        String[] line = scanner.nextLine().split(argsName.get("delimiter"));
                        for (int i = 0; i < headerIndexes.length; i++) {
                            out.write((line[headerIndexes[i]] + (headerIndexes.length - i > 1 ? argsName.get("delimiter") : "")).getBytes());
                        }
                        out.write(System.lineSeparator().getBytes());
                    }
                }
            } else {
                for (int i = 0; i < columns.length; i++) {
                    for (int j = 0; j < header.length; j++) {
                        if (header[j].equals(columns[i])) {
                            headerIndexes[i] = j;
                        }
                    }
                    System.out.print(columns[i] + argsName.get("delimiter"));
                }

                System.out.println();
                while (scanner.hasNext()) {
                    String[] line = scanner.nextLine().split(argsName.get("delimiter"));
                    for (int i = 0; i < headerIndexes.length; i++) {
                        System.out.print(line[headerIndexes[i]] + argsName.get("delimiter"));
                    }
                    System.out.println();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static ArgsName validate(String[] args) {
        ArgsName  arguments = ArgsName.of(args);
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

        if (!arguments.get("out").endsWith("csv") && !arguments.get("out").equals("stdout"))  {
            throw new IllegalArgumentException("argument -out must contain 'stdout' or txt file");
        }
        return arguments;
    }

    public static void main(String[] args) throws Exception {
        handle(validate(args));
    }
}
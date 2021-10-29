package ru.job4j.io.duplicates;

import ru.job4j.set.Set;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashSet;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    private HashSet<FileProperty> files = new HashSet<>();

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {

        if (file.toFile().isFile()) {
            FileProperty temp = new FileProperty(file.toFile().getUsableSpace(), file.toFile().getName());
            if (!files.add(temp)) {
                System.out.printf(" %s\n------------------\n", file);
            }
        }
        return super.visitFile(file, attrs);
    }
}
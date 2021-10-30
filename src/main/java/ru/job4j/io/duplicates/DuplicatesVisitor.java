package ru.job4j.io.duplicates;

import ru.job4j.set.Set;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    private HashMap<FileProperty, List<Path>> files = new HashMap<>();

    public HashMap<FileProperty, List<Path>> getFiles() {
        return files;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {

        if (file.toFile().isFile()) {
            FileProperty temp = new FileProperty(file.toFile().getUsableSpace(), file.toFile().getName());
            if (!files.containsKey(temp)) {
                files.put(temp, new ArrayList<>());
            }
            files.get(temp).add(file);
        }
        return super.visitFile(file, attrs);
    }
}
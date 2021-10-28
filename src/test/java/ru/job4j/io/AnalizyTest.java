package ru.job4j.io;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.*;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

public class AnalizyTest {
    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Test
    public void withoutErrors() throws IOException {
        File source = folder.newFile("source.txt");
        File target = folder.newFile("target.txt");
        Analizy analizy = new Analizy();
        try (PrintWriter out = new PrintWriter(source.getAbsolutePath())) {
            out.println("200 10:58:01");
            out.println("200 11:01:02");
        }
        analizy.unavailable(source.getAbsolutePath(), target.getAbsolutePath());
        StringBuilder rsl = new StringBuilder();
        try (BufferedReader in = new BufferedReader(new FileReader(target))) {
            in.lines().forEach(rsl::append);
        }
        assertEquals(rsl.toString(), "");

    }

    @Test
    public void withError() throws IOException {
        File source = folder.newFile("source.txt");
        File target = folder.newFile("target.txt");
        Analizy analizy = new Analizy();
        try (PrintWriter out = new PrintWriter(source.getAbsolutePath())) {
            out.println("400 10:56:01");
            out.println("200 10:57:01");
        }
        analizy.unavailable(source.getAbsolutePath(), target.getAbsolutePath());
        StringBuilder rsl = new StringBuilder();
        try (BufferedReader in = new BufferedReader(new FileReader(target))) {
            in.lines().forEach(rsl::append);
        }
        assertEquals(rsl.toString(), "10:56:01;10:57:01");

    }

    @Test
    public void withTwoErrors() throws IOException {
        File source = folder.newFile("source.txt");
        File target = folder.newFile("target.txt");
        Analizy analizy = new Analizy();
        try (PrintWriter out = new PrintWriter(source.getAbsolutePath())) {
            out.println("400 10:56:01");
            out.println("200 10:57:01");
            out.println("400 11:56:01");
            out.println("200 12:57:01");
        }
        analizy.unavailable(source.getAbsolutePath(), target.getAbsolutePath());
        List<String> actual;
        List<String> expected = List.of("10:56:01;10:57:01", "11:56:01;12:57:01");
        try (BufferedReader in = new BufferedReader(new FileReader(target))) {
            actual = in.lines().collect(Collectors.toList());
        }
        assertEquals(actual, expected);

    }
}
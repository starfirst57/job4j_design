package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Analizy {
    private List<String[]> status = new ArrayList<>();
    public void unavailable(String source, String target) {
        try (BufferedReader read = new BufferedReader(new FileReader(source))) {
            status = read.lines().map(line -> line.split(" ")).collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
        }

        try (PrintWriter out = new PrintWriter(new BufferedOutputStream(new FileOutputStream(target)))) {
            boolean isError = true;
            for (String[] arr : status) {
                if (isError && (Objects.equals(arr[0], "500") || Objects.equals(arr[0], "400"))) {
                    isError = false;
                    out.printf("%s;", arr[1]);
                }
                if (!isError && Objects.equals(arr[0], "200")) {
                    out.println(arr[1]);
                    isError = true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Analizy analizy = new Analizy();
        analizy.unavailable("server.log", "unavailable.txt");
    }
}

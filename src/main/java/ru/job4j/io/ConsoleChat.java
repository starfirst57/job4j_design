package ru.job4j.io;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ConsoleChat {

    private final String path;
    private final String botAnswers;
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    public void run() throws IOException {
        List<String> phrases = readPhrases(botAnswers);
        boolean end = false;
        boolean pause = false;
        try (FileWriter out = new FileWriter(new File(path))) {
            while (!end) {
                Scanner in = new Scanner(System.in);
                String temp = in.nextLine();
                if (temp.equals(OUT)) {
                    end = true;
                }
                if ()
                System.out.println(temp);
                out.write(temp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private List<String> readPhrases(String botAnswers) {
        List<String> phrases = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(botAnswers, StandardCharsets.UTF_8))) {
             in.lines().forEach(phrases::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return phrases;
    }

    private void saveLog(List<String> log) {

    }

    public static void main(String[] args) {
        ConsoleChat cc = new ConsoleChat("", "");
        cc.run();
    }
}
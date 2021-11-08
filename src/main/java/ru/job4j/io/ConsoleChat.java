package ru.job4j.io;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
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

    public void run() {
        List<String> phrases = readPhrases(botAnswers);
        List<String> chat = new ArrayList<>();

        boolean end = false;
        boolean pause = false;

        while (!end) {
            Scanner console = new Scanner(System.in);
            String temp = console.nextLine();
            chat.add(temp);
            if (temp.contains(OUT)) {
                end = true;
                continue;
            }
            if (temp.contains(STOP)) {
                pause = true;
                continue;
            }

            if (temp.contains(CONTINUE)) {
                pause = false;
                continue;
            }

            if (!pause) {
                int rand = (int) (Math.random() * phrases.size());
                chat.add(phrases.get(rand));
                System.out.println(phrases.get(rand));
            }
        }
        saveLog(chat);
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
        try (FileWriter out = new FileWriter(Path.of(path).toFile(), StandardCharsets.UTF_8)) {
            for (String line: log) {
                out.write(line + "\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }



    }

    public static void main(String[] args) {
        ConsoleChat cc = new ConsoleChat("./log.txt", "./duplicate.txt");
        cc.run();
    }
}
package ru.job4j.question;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class Analize {

    public static Info diff(Set<User> previous, Set<User> current) {
        int added = 0;
        int changed = 0;
        int deleted = 0;
        int currentSize = current.size();
        Map<Integer, String> userMap = new HashMap<>();
        for (User user : previous) {
            userMap.put(user.getId(), user.getName());
        }

        for (User user : current) {
            if (userMap.containsKey(user.getId())) {
                if (!Objects.equals(userMap.get(user.getId()), user.getName())) {
                    changed++;
                }
            } else {
                added++;
                currentSize--;
            }
        }
        if (previous.size() > currentSize) {
            deleted = previous.size() - currentSize;
        }
        return new Info(added, changed, deleted);
    }

}
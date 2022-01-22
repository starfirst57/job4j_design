package ru.job4j.serialization;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Main {
    public static void main(String[] args) {
        final Apartment apartment = new Apartment("Moscow, Pobedy st., 8, 12", 5,
                120.5f, true,
                new Contact(1234, "25-45-45"), new String[]{"facade.jpg", "room1.jpg", "room2.jpg"});

        final Gson gson = new GsonBuilder().create();
        System.out.println(gson.toJson(apartment));

        String apartJson = "{\"address\":\"Moscow, "
                + "Pobedy st., 8, 12\","
                + "\"flat\":5,\"square\":120.5,"
                + "\"hasBalcony\":true,"
                + "\"ownerContact\":{"
                + "\"zipCode\":1234,"
                + "\"phone\":\"25-45-45\"},"
                + "\"photos\":["
                + "\"facade.jpg\","
                + "\"room1.jpg\","
                + "\"room2.jpg\"]}";

        System.out.println(gson.fromJson(apartJson, Apartment.class));
    }
}

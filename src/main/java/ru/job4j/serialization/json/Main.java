package ru.job4j.serialization.json;

import org.json.JSONArray;
import org.json.JSONObject;
import ru.job4j.serialization.Apartment;
import ru.job4j.serialization.Contact;

public class Main {
    public static void main(String[] args) {
        final Apartment apartment = new Apartment("Moscow, Pobedy st., 8, 12", 5,
                120.5f, true,
                new Contact(1234, "25-45-45"), new String[]{"facade.jpg", "room1.jpg", "room2.jpg"});

        JSONObject jsonContact = new JSONObject();
        jsonContact.put("zipCode", apartment.getOwnerContact().getZipCode());
        jsonContact.put("phone", apartment.getOwnerContact().getPhone());

        JSONArray jsonArray = new JSONArray(apartment.getPhotos());

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("address", apartment.getAddress());
        jsonObject.put("flat", apartment.getFlat());
        jsonObject.put("square", apartment.getSquare());
        jsonObject.put("ownerContact", jsonContact);
        jsonObject.put("photos", jsonArray);

        System.out.println(jsonObject);



    }
}

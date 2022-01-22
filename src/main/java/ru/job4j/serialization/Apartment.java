package ru.job4j.serialization;

import java.io.Serializable;
import java.util.Arrays;

public class Apartment implements Serializable {
    private final String address;
    private final int flat;
    private final float square;
    private final boolean hasBalcony;
    private final Contact ownerContact;
    private final String[] photos;

    public Apartment (String address, int flat, float square,
                      boolean hasBalcony, Contact ownerContact, String[] photos) {
        this.address = address;
        this.flat = flat;
        this.square = square;
        this.hasBalcony = hasBalcony;
        this.ownerContact = ownerContact;
        this.photos = photos;

    }

    @Override
    public String toString() {
        return "Apartment{"
                + "address='" + address + '\''
                + ", flat=" + flat
                + ", square=" + square
                + ", hasBalcony=" + hasBalcony
                + ", ownerContact=" + ownerContact
                + ", photos=" + Arrays.toString(photos)
                + '}';
    }
}

package ru.job4j.serialization.xml;

import ru.job4j.serialization.xml.Contact;

import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.Arrays;

@XmlRootElement(name = "apartment")
@XmlAccessorType(XmlAccessType.FIELD)
public class Apartment implements Serializable {
    @XmlAttribute
    private String address;
    @XmlAttribute
    private int flat;
    @XmlAttribute
    private float square;
    @XmlAttribute
    private boolean hasBalcony;
    private Contact ownerContact;
    @XmlElementWrapper(name = "photos")
    @XmlElement(name = "photo")
    private String[] photos;

    public Apartment() { }

    public Apartment(String address, int flat, float square,
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

package ru.job4j.serialization.xml;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

public class Main {
    public static void main(String[] args) throws JAXBException {
        Apartment apartment = new Apartment("Moscow, Pobedy st., 8, 12", 5,
                120.5f, true,
                new Contact(1234, "25-45-45"), new String[]{"facade.jpg", "room1.jpg", "room2.jpg"});

        JAXBContext context = JAXBContext.newInstance(Apartment.class);

        Marshaller marshaller = context.createMarshaller();

        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        String xml = "";

        try (StringWriter writer = new StringWriter()) {
            marshaller.marshal(apartment, writer);
            xml = writer.getBuffer().toString();
            System.out.println(xml);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Unmarshaller unmarshaller = context.createUnmarshaller();
        try (StringReader reader = new StringReader(xml)) {
            ru.job4j.serialization.xml.Apartment fromXml =
                    (ru.job4j.serialization.xml.Apartment) unmarshaller.unmarshal(reader);
            System.out.println(fromXml);
        }
    }
}

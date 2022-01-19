package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        short i = 3;
        int price = 1265;
        double amount = 155.0;
        float item = 15.5f;
        char a = 'a';
        long number = 56465;
        boolean isClosed = true;
        byte j = 16;
        LOG.debug("i = {}, price = {}, amount = {}, item = {}, a = {}, number = {}, isClosed = {}, j = {}"
                 , i, price, amount, item, a, number, isClosed, j);

    }
}
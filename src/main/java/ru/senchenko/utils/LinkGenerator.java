package ru.senchenko.utils;

import org.apache.commons.text.RandomStringGenerator;

public class LinkGenerator {

    private final RandomStringGenerator randomStringGenerator;

    public LinkGenerator() {
        this.randomStringGenerator = new RandomStringGenerator.Builder()
                .filteredBy(LinkGenerator::isCorrectSymbol)
                .build();
    }

    public String generateLink(int length) {
        return randomStringGenerator.generate(length);
    }

    private static boolean isCorrectSymbol(int symbol) {
        return ('a' <= symbol && symbol <= 'z')
                || ('A' <= symbol && symbol <= 'Z')
                || ('0' <= symbol && symbol <= '9')
                || ('+' == symbol)
                || ('_' == symbol)
                || ('-' == symbol);
    }
}

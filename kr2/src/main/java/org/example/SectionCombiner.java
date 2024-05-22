package org.example;

import java.util.SortedMap;
import java.util.TreeMap;

public class SectionCombiner {

    private SortedMap<Integer, String> sections = new TreeMap<>();

    public synchronized void addSection(int number, String content) {
        sections.put(number, content);
    }

    public synchronized String combineSections() {
        StringBuilder builder = new StringBuilder();
        for (String section : sections.values()) {
            builder.append(section).append("\n");
        }
        return builder.toString();
    }
}
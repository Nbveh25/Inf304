package org.example;

import java.io.*;

public class SectionReader implements Runnable {

    private File file;
    private SectionCombiner combiner;

    public SectionReader(File file, SectionCombiner combiner) {
        this.file = file;
        this.combiner = combiner;
    }

    @Override
    public void run() {
        try (InputStream in = new FileInputStream(file);
             DataInputStream dataIn = new DataInputStream(in)) {
            int size = dataIn.readInt();
            byte[] content = new byte[size];
            dataIn.readFully(content);
            String text = new String(content, "UTF-8");
            int expectedLength = dataIn.readInt();
            int partNumber = dataIn.readInt();

            if (text.length() != expectedLength) {
                System.err.println("Mismatch in file " + file.getName() + ": expected " + expectedLength + " characters, found " + text.length());
            } else {
                System.out.println("Processed file " + file.getName() + ", bytes: " + size + ", characters: " + text.length() + ", check: " + expectedLength + ", part: " + partNumber);
            }

            combiner.addSection(partNumber, text);
        } catch (IOException e) {
            System.err.println("Failed to read " + file.getName() + ": " + e.getMessage());
        }
    }
}
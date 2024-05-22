package org.example;

import java.io.*;
import java.nio.charset.Charset;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DocumentAssembler {

    public static void main(String[] args) {
        File directory = new File("C:/javaproject/Inf304/blobs");
        SectionCombiner combiner = new SectionCombiner();
        File[] files = directory.listFiles();

        if (files == null) return;

        try (ExecutorService service = Executors.newCachedThreadPool();){
            for (File file : files) {
                service.execute(new SectionReader(file, combiner));
            }
        }


        File outputFile = new File("./v16.txt");
        try (Writer output = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outputFile), Charset.forName("UTF-8")))) {
            output.write(combiner.combineSections());
        } catch (IOException e) {
            System.err.println("Failed to write to file: " + e.getMessage());
        }
    }
}
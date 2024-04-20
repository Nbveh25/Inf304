package org.example;

import java.io.*;

public class FileMerger {
    public static void main(String[] args) {
        System.out.println(merge());
    }
    public static String merge() {
        String result = "";
        try (BufferedReader bf1 = new BufferedReader(new FileReader("file1.txt"));
             BufferedReader bf2 = new BufferedReader(new FileReader("file2.txt"));
             BufferedWriter bw  = new BufferedWriter(new FileWriter("file3.txt"))){
            result = bf1.readLine() + bf2.readLine();
            bw.write(result);

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return result;
    }
}

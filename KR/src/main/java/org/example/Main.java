package org.example;


public class Main {
    public static void main(String[] args) {
        DoubleLinkedList list = new DoubleLinkedList();

        Stroika[] stroikas = new Stroika[10];
        for (int i = 1; i < 5; i++) {
            stroikas[i] = new Stroika(new Roof(10), new Basement(11), new Walls(12), new Otdelka(13), new StroikaStatus(StatusEnum.выполнен), i);
        }

        System.out.println(stroikas[list.next()]);
        System.out.println(stroikas[list.next()]);
        System.out.println(stroikas[list.next()]);


    }
}
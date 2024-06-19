package org.example.V1;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Data data = new Data();
        data.readFile("schedule.txt");

        System.out.println("Все программы:");
        data.showAllPrograms();

        System.out.println();
        System.out.println("Программы сортированные по времени:");
        data.sortChanelByTime();
        data.showAllPrograms();

        System.out.println();
        System.out.println("Программы сортированные по названию:");
        data.sortChanelByName();
        data.showAllPrograms();

        System.out.println();
        System.out.println("Программы идущие сейчас:");
        data.showProgramNow();

        System.out.println();
        System.out.println("Поиск программы по названию: ");
        System.out.println(data.searchProgram("Время"));

        System.out.println();
        System.out.println("Все программы определенного канала:");
        System.out.println(data.programsOfChannel("НТВ"));

        System.out.println();
        System.out.println("Программа по определенному времени: ");
        BroadcastsTime time1 = new BroadcastsTime((byte) 18, (byte) 30);
        BroadcastsTime time2 = new BroadcastsTime((byte) 21, (byte) 30);
        System.out.println(data.programsOfInterval("Матч!", time1, time2));

    }
}

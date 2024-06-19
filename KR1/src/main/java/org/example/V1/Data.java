package org.example.V1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalTime;
import java.util.*;

public class Data {
    private Map<BroadcastsTime, List<Program>> channelsAndPrograms = new HashMap<>();
    private List<Program> allPrograms = new ArrayList<>();

    public void readFile(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            String channel = "";
            BroadcastsTime time = null;

            while ((line = br.readLine()) != null) {
                if (line.startsWith("#")) {
                    channel = line.substring(1);
                } else {
                    if (time == null) {
                        String[] timeParts = line.split(":");
                        time = new BroadcastsTime(Byte.parseByte(timeParts[0]), Byte.parseByte(timeParts[1]));
                    } else {
                        String name = line;
                        Program program = new Program(channel, time, name);
                        allPrograms.add(program);
                        channelsAndPrograms.computeIfAbsent(time, k -> new ArrayList<>()).add(program);
                        time = null;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sortChanelByTime() {
        Collections.sort(allPrograms, new TimeComparator());
    }

    public void sortChanelByName() {
        Collections.sort(allPrograms, new TimeComparator());
    }
    public void showAllPrograms() {
        allPrograms.sort(Comparator.comparing(Program::getTime));
        for (Program program : allPrograms) {
            System.out.println(program.getChannel() + " " + program.getTime() + " " + program.getName());
        }
    }

    public void showProgramNow() {
        LocalTime currentTime = LocalTime.now();
        for (Program program : allPrograms) {
            if (program.getTime().after(new BroadcastsTime((byte) currentTime.getHour(), (byte) currentTime.getMinute()))) {
                System.out.println("Сейчас идет: " + program.getChannel() + " " + program.getTime() + " " + program.getName());
                break;
            }
        }
    }

    public List<Program> searchProgram(String name) {
        List<Program> foundedPrograms = new ArrayList<>();
        for (Program program : allPrograms) {
            if (program.getName().equalsIgnoreCase(name)) {
                foundedPrograms.add(program);
            }
        }
        return foundedPrograms;
    }

    public List<Program> programsOfChannel(String name) {
        List<Program> targetProgram = new ArrayList<>();
        for (int i = 0; i < allPrograms.size(); i++) {
            if (Objects.equals(allPrograms.get(i).getChannel(), name)) {
                targetProgram.add(allPrograms.get(i));
            }
        }
        return targetProgram;
    }

    public List<Program> programsOfInterval(String name, BroadcastsTime timeBegin, BroadcastsTime timeEnd) {
        List<Program> targetProgram = new ArrayList<>();

        for (int i = 0; i < allPrograms.size(); i++) {
            if (Objects.equals(allPrograms.get(i).getChannel(), name)) {
                if (allPrograms.get(i).getTime().between(timeBegin, timeEnd)) {
                    targetProgram.add(allPrograms.get(i));
                }

            }
        }
        return targetProgram;
    }
}

class NameComparator implements Comparator<Program> {
    @Override
    public int compare(Program o1, Program o2) {
        return o1.getChannel().compareTo(o2.getChannel());
    }
}

class TimeComparator implements Comparator<Program> {
    @Override
    public int compare(Program o1, Program o2) {
        return o1.getTime().compareTo(o2.getTime());
    }
}
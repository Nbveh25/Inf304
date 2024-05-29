package org.example;

import lombok.Getter;
import lombok.Setter;

import java.util.Stack;

@Setter
@Getter
public class Car extends Thread {

    private static Stack<Float> resultsList = new Stack<>();

    private static double currentTime;
    private static boolean isCounting = true;

    private Car prevCar = null;
    private static final double MIN_DISTANCE = 5;
    private static final double CAR_LENGTH = 5;
    private static final double ROAD_LENGTH = 100;
    private static final double MAX_SPEED = 70000 / 3600d;
    private static final double ACCELERATION = 10000 / 3600d;
    private static final double DECELERATION = 100000 / 3600d * 100000 / 3600d / 100d;
    private double currentSpeed = 0;
    private double distanceTravelled = 0;

    @Override
    public void run() {

        while (distanceTravelled < ROAD_LENGTH) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            if (currentSpeed < MAX_SPEED) {
                accelerate();
            }

            if (distanceToNextCar() < MIN_DISTANCE) {
                decelerate();
            }

            move();
        }

        resultsList.add((float)currentTime);
        System.out.println("Автомобиль проехал заданное расстояние");
    }

    private void accelerate() {
        if (currentSpeed < MAX_SPEED) {
            currentSpeed += ACCELERATION * 0.1;
        }
    }

    private void decelerate() {
        if (currentSpeed > 0) {
            currentSpeed -= DECELERATION * 0.1;
        }
    }

    private void move() {
        double distance = currentSpeed * 0.1;
        distanceTravelled += distance;
    }

    private double distanceToNextCar() {
        if (prevCar == null) return MIN_DISTANCE;
        return prevCar.getDistanceTravelled() - distanceTravelled;
    }

    public static void startCounting() {
        Thread countingThread = new Thread(() -> {
            while (isCounting) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                currentTime+=0.1;
            }
        });
        countingThread.start();
    }

    public static void stopCounting() {
        isCounting = false;
    }

    public float intensiv(int countOfThreads) {
        float sum = 0;
        for (int i = 0; i < countOfThreads - 1; i++) {
            sum += resultsList.pop() - resultsList.peek();
        }
        resultsList.clear();
        return sum / (countOfThreads - 1);
    }
}

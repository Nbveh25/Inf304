package org.example;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ThreadPool {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(10); // Создание пула из 10 потоков

        Car[] cars = new Car[10];
        cars[0] = new Car();
        for (int i = 1; i < 10; i++) {
            cars[i] = new Car();
            cars[i].setPrevCar(cars[i-1]);
        }

        Car.startCounting();

        for (int i = 0; i < 10; i++) {
            executor.execute(cars[i]); // Запуск каждого автомобиля в отдельном потоке
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        executor.shutdown();
        try {
            executor.awaitTermination(1, TimeUnit.DAYS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        Car.stopCounting();

        System.out.println("Интенсив потока автомобилей: " + cars[9].intensiv(10));
    }
}

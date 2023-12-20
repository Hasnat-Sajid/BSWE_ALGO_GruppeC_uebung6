package org.algo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Hotel {

    private int availableRooms;
    private int guestCount;
    private ExecutorService executorService;


    public Hotel(int availableRooms, int guestCount) {
        this.availableRooms = availableRooms;
        this.guestCount = guestCount;
        this.executorService = Executors.newFixedThreadPool(availableRooms);
    }
    public void addGuest(Guest guest) {
        executorService.submit(() -> {
            guest.start();
        });

        Future<String> future = executorService.submit(() -> "Hello World");
//        String result = future.get();
    }
}

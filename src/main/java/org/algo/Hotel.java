package org.algo;

import java.util.ArrayDeque;
import java.util.Optional;
import java.util.Queue;
import java.util.concurrent.*;

public class Hotel {

    private ExecutorService executorService;
    private int guestCount;
    private int availableRooms;

    private Queue queue;


    public Hotel(int availableRooms) {
        this.executorService = Executors.newFixedThreadPool(availableRooms);
        this.availableRooms = availableRooms;
        this.guestCount = 0;
        this.queue = new ArrayDeque<Guest>();
    }

    public Future checkIn(Guest guest) {
        guestCount++;
        return executorService.submit(() -> {
            try {
                guest.start();
                guest.wait();
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });



//        String reslt = future.get();
    }

    public void checkOut() {
        guestCount--;
    }

    public void shutdown() {
        executorService.shutdown();
    }

}

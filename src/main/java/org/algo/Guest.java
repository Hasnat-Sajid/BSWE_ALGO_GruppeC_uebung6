package org.algo;

import java.time.LocalDateTime;

public class Guest extends Thread {

    private int guestId;


    public Guest(int guestId){
        this.guestId = guestId;
    }

    public void run() {
        System.out.println("Guest "+guestId+" has checked in at "+ LocalDateTime.now());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Guest "+guestId+" has checked out at "+ LocalDateTime.now());
    }

}

package org.algo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

public class Guest extends Thread {

    private final String guestId;
    private final Hotel hotel;
    private final int stayTime;
    private final int cleanUpTime;
    private boolean isWaiting = false;


    public Guest(String guestId, Hotel hotel, int stayTime, int cleanUpTime) {
        this.guestId = guestId;
        this.hotel = hotel;
        this.stayTime = stayTime;
        this.cleanUpTime = cleanUpTime;
        setName(guestId);
    }

    @Override
    public void run() {
        try {
            printStringWithTimestamp("tries to check into the Hotel at: ");
            boolean toContinue = true;

            while(toContinue) {
                if (hotel.isRoomFree()) {
                    hotel.checkInGuest(this);
                    printStringWithTimestamp("checked into the Hotel at: ");
                    toContinue = false;
                } else {
                    if (!isWaiting) {
                        printStringWithTimestamp("is waiting to check into the hotel at: ");
                        isWaiting = true;
                    }
                    TimeUnit.MILLISECONDS.sleep(500);
                }
            }

            TimeUnit.SECONDS.sleep(stayTime);
            printStringWithTimestamp("has checked out of the hotel at: ");
            TimeUnit.SECONDS.sleep(cleanUpTime);
            hotel.checkOutGuest(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getGuestId() {
        return this.guestId;
    }

    private void printStringWithTimestamp(String text) {
        String time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
        System.out.printf("Guest with GuestId %s %s %s%n", this.guestId, text, time);
    }

}

package org.algo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

/**
 * Represents a guest in a hotel simulation.
 * Each guest is a thread that checks into and out of a hotel.
 */
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
    }

    /**
     * The main execution method for the guest thread.
     * Handles the process of checking into the hotel, staying, and checking out.
     */
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

    /**
     * Gets the guest's ID.
     *
     * @return the guest ID
     */
    public String getGuestId() {
        return this.guestId;
    }


    /**
     * Prints a given string with a timestamp.
     *
     * @param text the text to print with the timestamp
     */
    private void printStringWithTimestamp(String text) {
        String time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
        System.out.printf("Guest with GuestId %s %s %s%n", this.guestId, text, time);
    }

}

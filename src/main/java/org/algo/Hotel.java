package org.algo;

import java.util.*;

/**
 * Represents a hotel with a fixed number of rooms.
 * Manages the check-in and check-out of guests.
 */
public class Hotel {

    private final int rooms;
    private int counter = 0;
    private final Set<Guest> checkedInGuests = new HashSet<>();

    public Hotel(int rooms) {
        this.rooms = rooms;
    }

    /**
     * Checks if there are free rooms available in the hotel.
     *
     * @return true if there is at least one free room, false otherwise
     */
    public boolean isRoomFree() {
        return counter < rooms;
    }


    /**
     * Checks in a guest if there's a free room.
     *
     * @param guest the guest to be checked in
     */
    public void checkInGuest(Guest guest) {
        if (isRoomFree()) {
            ++counter;
            checkedInGuests.add(guest);
        }
    }

    /**
     * Checks out a guest from the hotel.
     *
     * @param guest the guest to be checked out
     * @throws IllegalStateException if the guest is not in the hotel
     */
    public void checkOutGuest(Guest guest) {
        if (checkedInGuests.contains(guest)) {
            counter--;
            checkedInGuests.remove(guest);
        } else {
            throw new IllegalStateException("Guest not in the hotel: " + guest.getGuestId());
        }
    }

    /**
     * Gets a set of all guests currently checked in.
     *
     * @return a set of checked-in guests
     */
    public Set<Guest> getCheckedInGuests() {
        return checkedInGuests;
    }
}

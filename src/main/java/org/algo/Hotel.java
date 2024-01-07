package org.algo;

import java.util.*;

public class Hotel {

    private final int rooms;
    private int counter = 0;
    private final Set<Guest> checkedInGuests = new HashSet<>();

    public Hotel(int rooms) {
        this.rooms = rooms;
    }

    public boolean isRoomFree() {
        return counter < rooms;
    }


    public void checkInGuest(Guest guest) {
        if (isRoomFree()) {
            ++counter;
            checkedInGuests.add(guest);
        }
    }
    public void checkOutGuest(Guest guest) {
        if (checkedInGuests.contains(guest)) {
            counter--;
            checkedInGuests.remove(guest);
        } else {
            throw new IllegalStateException("Guest not in the hotel: " + guest.getGuestId());
        }
    }

    public Set<Guest> getCheckedInGuests() {
        return checkedInGuests;
    }
}

package org.algo;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class GuestTest {

    @Test
    void testGuestCheckInAndOut() throws InterruptedException {
        Hotel hotel = new Hotel(1);
        Guest guest = new Guest("Guest1", hotel, 1, 1);
        guest.start();
        Thread.sleep(1000); // Wait for the guest to check in
        assertTrue(hotel.getCheckedInGuests().contains(guest));

        guest.join(); // Wait for the guest to check out
        assertFalse(hotel.getCheckedInGuests().contains(guest));
    }
}

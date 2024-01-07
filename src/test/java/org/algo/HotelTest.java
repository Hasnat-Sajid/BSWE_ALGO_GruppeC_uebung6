package org.algo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class HotelTest {

    private Hotel hotel;

    @BeforeEach
    void setUp() {
        hotel = new Hotel(2);
    }

    @Test
    void testIsRoomFree_WhenHotelIsEmpty() {
        assertTrue(hotel.isRoomFree());
    }

    @Test
    void testIsRoomFree_WhenHotelIsFull() {
        hotel.checkInGuest(new Guest("Guest1", hotel, 1, 1));
        hotel.checkInGuest(new Guest("Guest2", hotel, 1, 1));
        assertFalse(hotel.isRoomFree());
    }

    @Test
    void testCheckInGuest_WhenRoomIsAvailable() {
        Guest guest = new Guest("Guest1", hotel, 1, 1);
        hotel.checkInGuest(guest);
        assertTrue(hotel.getCheckedInGuests().contains(guest));
    }

    @Test
    void testCheckInGuest_WhenRoomIsNotAvailable() {
        hotel.checkInGuest(new Guest("Guest1", hotel, 1, 1));
        hotel.checkInGuest(new Guest("Guest2", hotel, 1, 1));
        Guest newGuest = new Guest("Guest3", hotel, 1, 1);
        hotel.checkInGuest(newGuest);
        assertFalse(hotel.getCheckedInGuests().contains(newGuest));
    }

    @Test
    void testCheckOutGuest_ExistingGuest() {
        Guest guest = new Guest("Guest1", hotel, 1, 1);
        hotel.checkInGuest(guest);
        hotel.checkOutGuest(guest);
        assertFalse(hotel.getCheckedInGuests().contains(guest));
    }

    @Test
    void testCheckOutGuest_NonExistingGuest() {
        Guest guest = new Guest("Guest1", hotel, 1, 1);
        assertThrows(IllegalStateException.class, () -> hotel.checkOutGuest(guest));
    }
}

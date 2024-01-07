package org.algo;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class UserMenu {
    private final Scanner sc = new Scanner(System.in);
    private Hotel hotel;
    private int guestCount = 5;
    private int hotelRooms = 2;
    private int cleanUpTime = 2;
    private int stayTime = 3;

    /**
     * Displays a menu with options for starting the application, setting guest count,
     * hotel room space, clean up time, and average stay time. It processes user input and
     * executes the corresponding actions.
     */
    public void startMenu() {
        boolean isContinue = true;
        String menu = """
                    ┌───────────────────────────────────────────────────────────┐
                                      Welcome to our ADT solution!
                                         Group C - Exercise 6
                    └───────────────────────────────────────────────────────────┘
                                      Current guest count: %d
                                      Hotel room space: %d
                                      Clean up time: %d seconds
                                      Average stay time: %d seconds
                    ┌───────────────────────────────────────────────────────────┐
                        1 - 📜 Start
                        2 - 🔢 Set Guest Count
                        3 - 🏨 Set Hotel Room Space
                        4 - 🧹 Set Clean Up Time
                        5 - ⏳ Set Average Stay Time
                        0 - ❌ Exit
                    └───────────────────────────────────────────────────────────┘
                    \n
                """;


        while (isContinue) {
            System.out.printf(menu, guestCount, hotelRooms, cleanUpTime, stayTime);
            String userInput = sc.nextLine();
            int choice;

            if (isInteger(userInput)) {
                choice = Integer.parseInt(userInput);
            } else {
                System.out.println("Input not recognized");
                continue;
            }

            switch (choice) {
                case 1 -> {
                    System.out.println("1 - 📜 Start");
                    startThreads();
                }
                case 2 -> {
                    System.out.println("2 - Set guest count");
                    System.out.println("Enter the amount of guests that are going to check into the hotel:");
                    guestCount = getIntegerFromUser();
                } case 3 -> {
                    System.out.println("3 - Set Hotel room space");
                    System.out.println("Enter the amount of rooms that the hotel should have:");
                    hotelRooms = getIntegerFromUser();
                } case 4 -> {
                    System.out.println("4 - Set clean up time");
                    System.out.println("Enter the amount of seconds that the hotel room should still be occupied after the guest left:");
                    cleanUpTime = getIntegerFromUser();
                } case 5 -> {
                    System.out.println("5 - Set average stay time");
                    System.out.println("Enter the amount of seconds the guests should stay in their room:");
                    stayTime = getIntegerFromUser();
                }
                case 0 -> isContinue = false;
                default -> System.out.println("Input not recognized");
            }
        }
        System.out.println("Bye!");
    }

    private void startThreads() {
        this.hotel = new Hotel(hotelRooms);

        List<Guest> guestList = IntStream.range(1, guestCount + 1)
                .mapToObj((int i) -> new Guest("Guest-" + i, hotel, stayTime, cleanUpTime))
                .toList();

        List<Guest> allRunningGuests = guestList.stream().map(guest -> {
            guest.start();
            return guest;
        }).collect(Collectors.toList());

        waitForThreads(allRunningGuests);
    }


    private void waitForThreads(List<Guest> guests) {
        for (Thread thread : guests) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        System.out.println("No guests left in the hotel.");
    }

    private int getIntegerFromUser() {
        String input;
        while (true) {
            input = sc.nextLine();
            if (isInteger(input)) {
                return Integer.parseInt(input);
            } else {
                System.out.println("Invalid input. Please enter a valid integer:");
            }
        }
    }


    private boolean isInteger(String toCheck) {
        String regex = "^-?\\d+$";
        return toCheck.matches(regex);
    }
}

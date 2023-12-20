package org.algo;

import java.util.Scanner;

public class UserMenu {
    private final Scanner sc = new Scanner(System.in);
    private Hotel hotel;

    public void startMenu() {
        boolean isContinue = true;
        String menu = """
                    ┌───────────────────────────────────────────────────────────┐
                                      Welcome to our ADT solution!
                                         Group C - Exercise 6
                    └───────────────────────────────────────────────────────────┘
                                      Current guest count: %s
                                      Hotel room space: %s
                                      Clean up time: %s
                                      Average stay time: %s
                    ┌───────────────────────────────────────────────────────────┐
                      1 - 📜 Start
                      2 - 🔑 Set Guest Count
                      3 - 🐓 Set Hotel room space
                      4 - 🧽 Set clean up time
                      5 - 🍌 Set average stay time
                      0 - ❌ Exit
                    └───────────────────────────────────────────────────────────┘
                    \n
                """;


        while (isContinue) {
            System.out.printf(menu, "count", "space", "time", "stay time");
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

                }
                case 2 -> {
                    System.out.println("2 - 📜 Set guest count");

                } case 3 -> {
                    System.out.println("3 - 🐓 Set Hotel room space");

                } case 4 -> {
                    System.out.println("4 - 🧽 Set clean up time");

                } case 5 -> {
                    System.out.println("5 - 🍌 Set average stay time");

                }
                case 0 -> isContinue = false;
                default -> System.out.println("Input not recognized");
            }
        }
        System.out.println("Bye!");
    }

    public boolean isInteger(String toCheck) {
        String regex = "^-?\\d+$";
        return toCheck.matches(regex);
    }
}

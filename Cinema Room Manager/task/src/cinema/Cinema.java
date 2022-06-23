package cinema;

import java.util.Arrays;
import java.util.Scanner;

class Cinema {

    static Scanner scanner = new Scanner(System.in);
    static char[][] room;
    static int purchasedTickets = 0;
    static int currentIncome = 0;

    public static void main(String[] args) {
        menu();
    }

    static void menu() {
        createAndFillTheRoom();
        boolean flag = true;
        while (flag) {
            System.out.println("1. Show the seats");
            System.out.println("2. Buy a ticket");
            System.out.println("3. Statistics");
            System.out.println("0. Exit");
            int n = scanner.nextInt();
            switch (n) {
                case 1:
                    showTheSeats();
                    break;
                case 2:
                    buyATicket();
                    break;
                case 3:
                    showStatistics();
                    break;
                case 0:
                    flag = false;
                    break;
            }
        }
    }

    static void createAndFillTheRoom() {
        System.out.println("Enter the number of rows: ");
        int rows = scanner.nextInt() + 1;
        System.out.println("Enter the number of seats in each row: ");
        int seats = scanner.nextInt() + 1;
        room = new char[rows][seats];
        for (char[] chars : room) {
            Arrays.fill(chars, 'S');
        }
        room[0][0] = ' ';
        for (int i = 1; i < room[0].length; i++) {
            room[0][i] = (char) (i + '0');
        }
        for (int i = 1; i < room.length; i++) {
            room[i][0] = (char) (i + '0');
        }
    }

    static void showTheSeats() {
        System.out.println("Cinema:");
        for (char[] inner : room) {
            for (char c : inner) {
                System.out.print(c + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    static void buyATicket() {
        boolean wrongInput = false;
        while (!wrongInput) {
            System.out.println("Enter a row number:");
            int rowNumber = scanner.nextInt();
            System.out.println("Enter a seat number in that row:");
            int seatNumber = scanner.nextInt();
            if (rowNumber >= room.length || seatNumber >= room[1].length) {
                System.out.println("Wrong input!");
                continue;
            } else {
                wrongInput = true;
            }
            if (room[rowNumber][seatNumber] == 'B') {
                System.out.println("That ticket has already been purchased!");
                wrongInput = false;
            } else {
                int ticketPrice;
                int totalSeats = (room.length - 1) * (room[0].length - 1);
                if (totalSeats <= 60) {
                    ticketPrice = 10;
                    currentIncome += ticketPrice;

                } else {
                    int rows = room.length - 1;
                    if (rowNumber <= rows / 2) {
                        ticketPrice = 10;
                        currentIncome += ticketPrice;
                    } else {
                        ticketPrice = 8;
                        currentIncome += ticketPrice;
                    }
                }
                System.out.println("Ticket price: $" + ticketPrice);
                room[rowNumber][seatNumber] = 'B';
                purchasedTickets++;
            }
        }
    }

    static void showStatistics() {
        System.out.printf("Number of purchased tickets: %d\n", purchasedTickets);
        double totalSeats = ((double) room.length - 1) * ((double) room[0].length - 1);
        double percentage = ((double) purchasedTickets / totalSeats) * 100.0;
        System.out.printf("Percentage: %.2f%%\n", percentage);
        System.out.printf("Current income: $%d\n", currentIncome);
        int totalIncome = 0;
        int totalSeatsInt = (room.length - 1) * (room[0].length - 1);
        int seatsInRow = room[0].length - 1;
        if (totalSeats <= 60) {
            totalIncome = totalSeatsInt * 10;
        } else {
            int rows = room.length - 1;
            if ((double) rows % 2.0 == 0) {
                totalIncome = (rows/2) * seatsInRow * 10 + (rows/2) * seatsInRow * 8;
            } else {
                totalIncome = (rows/2) * seatsInRow * 10 + ((rows/2) + 1) * seatsInRow * 8;
            }
        }
        System.out.printf("Total income: $%d\n", totalIncome);
    }
}

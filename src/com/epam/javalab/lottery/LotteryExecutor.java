package com.epam.javalab.lottery;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class LotteryExecutor {
    private static final int MIN_NUMBERS_IN_LOTTERY = 25;
    //default configs
    private static int totalCountOfTicket = 10;
    private static int countOfNumberInTicket = 5;
    private static int totalNumberInLottery = 25;
    private static List<Ticket> winnerTickets = new ArrayList<>();

    public static void execude(){
        setUsersValues();
        TicketNumber.init(totalNumberInLottery);
        Ticket.init(totalCountOfTicket, countOfNumberInTicket);

        while (LotteryExecutor.IsNoWinner()) {
            LotteryExecutor.takeNumber();
        }
        System.out.println("Winner Winner Chicken Dinner!");
        System.out.println(LotteryExecutor.getWinnerTickets());
    }

    private static void takeNumber() {
        int index = new Random().nextInt(TicketNumber.getAllNumbers().size());

        if (!TicketNumber.getAllNumbers().get(index).isUsed()) {
            TicketNumber.getAllNumbers().get(index).setUsed(true);
            System.out.println("Picked number: " + TicketNumber.getAllNumbers().get(index).getNumber());
        }
    }

    private static boolean IsNoWinner() {
        for (Ticket ticket : Ticket.getAllTickets()) {
            boolean hasWinner = true;
            for (TicketNumber ticketNumber : ticket.getNumbers()) {
                if (!ticketNumber.isUsed()) {
                    hasWinner = false;
                }
            }
            if (hasWinner) {
                winnerTickets.add(ticket);
            }
        }
        return winnerTickets.isEmpty();
    }

    private static void setUsersValues() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            System.out.println("Enter total count of numbers in the lottery:");
            int textIn = Integer.parseInt(reader.readLine());
            if (textIn >= MIN_NUMBERS_IN_LOTTERY) {
                setTotalNumberInLottery(textIn);
            } else {
                System.out.println("Your number is not valid (<25)! Default number will be used " + getTotalNumberInLottery());
            }
            System.out.println("Enter total count of tickets:");
            textIn = Integer.parseInt(reader.readLine());
            if (textIn > 0) {
                setTotalCountOfTicket(textIn);
            } else {
                System.out.println("Your number is not valid (<0)! Default number will be used " + getTotalCountOfTicket());
            }
            System.out.println("Enter total count of numbers in one ticket:");
            textIn = Integer.parseInt(reader.readLine());
            if (textIn < getTotalNumberInLottery() && textIn > 1) {
                setCountOfNumberInTicket(textIn);
            } else {
                System.out.println("Your number is not valid (<1 and >total count of numbers in the lottery)! Default number will be used " + getCountOfNumberInTicket());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Ticket> getWinnerTickets() {
        return winnerTickets;
    }

    public static int getTotalCountOfTicket() {
        return totalCountOfTicket;
    }

    public static void setTotalCountOfTicket(int totalCountOfTicket) {
        LotteryExecutor.totalCountOfTicket = totalCountOfTicket;
    }

    public static int getCountOfNumberInTicket() {
        return countOfNumberInTicket;
    }

    public static void setCountOfNumberInTicket(int countOfNumberInTicket) {
        LotteryExecutor.countOfNumberInTicket = countOfNumberInTicket;
    }

    public static int getTotalNumberInLottery() {
        return totalNumberInLottery;
    }

    public static void setTotalNumberInLottery(int totalNumberInLottery) {
        LotteryExecutor.totalNumberInLottery = totalNumberInLottery;
    }
}

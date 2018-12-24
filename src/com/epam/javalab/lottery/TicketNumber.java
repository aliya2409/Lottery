package com.epam.javalab.lottery;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TicketNumber {
    private int number;
    private boolean isUsed;
    private static List<TicketNumber> allNumbers = new ArrayList<>();

    public static void init(int totalNumber){
        for (int i = 1; i <= totalNumber; i++) {
            allNumbers.add( new TicketNumber(i, false));
        }
    }

    public TicketNumber(int number, boolean isUsed) {
        this.number = number;
        this.isUsed = isUsed;
    }

    public void setUsed(boolean used) {
        isUsed = used;
    }

    public int getNumber() {
        return number;
    }

    public boolean isUsed() {
        return isUsed;
    }

    public static List<TicketNumber> getAllNumbers() {
        return allNumbers;
    }

    @Override
    public String toString() {
        return "TicketNumber{" +
                "number=" + number +
                ", isUsed=" + isUsed +
                '}';
    }
}

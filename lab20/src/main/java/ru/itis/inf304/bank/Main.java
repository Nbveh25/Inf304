package ru.itis.inf304.bank;

import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) {
        try {
            System.out.println(new TradeRequest(20, new BigDecimal(1000)));
        } catch (InvalidAmountException | InvalidTotalException | InvalidTotalScaleException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println("Unknown error");
        }
    }
}
package ru.itis.inf304.bank;

public class InvalidAmountException extends Exception{
    public InvalidAmountException(String s) {
        super(s);
    }
}

package ru.itis.inf304.bank;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class TradeRequest {
    private int amount;// кол-во товара
    private BigDecimal total; // полная стоимость

    public TradeRequest(int amount, BigDecimal total) throws InvalidAmountException, InvalidTotalException, InvalidTotalScaleException {
        setAmount(amount);
        setTotal(total);
    }

    public void setAmount(int amount) throws InvalidAmountException {
        if (amount <= 0) throw new InvalidAmountException("Amount should be greater than 0");
        this.amount = amount;
    }

    public void setTotal(BigDecimal total) throws InvalidTotalException, InvalidTotalScaleException {
        if (total.compareTo(new BigDecimal("0")) <= 0 ) throw new InvalidTotalException("Total should be greater than 0");
        if (total.scale() > 2) throw new InvalidTotalScaleException(total, total.setScale(2, RoundingMode.DOWN));
        this.total = total;
    }

    public int getAmount() {
        return amount;
    }
    public BigDecimal getTotal() {
        return total;
    }

    @Override
    public String toString() {
        return "TradeRequest{" +
                "amount = " + amount +
                ", total = " + total +
                '}';
    }
}

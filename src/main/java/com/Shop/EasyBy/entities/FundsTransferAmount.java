package com.Shop.EasyBy.entities;

public class FundsTransferAmount {
    private String amount;

    public FundsTransferAmount(String amount) {
        this.amount = amount;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }






    @Override
    public String toString() {
        return "FundsTransferAmount{" +
                "amount='" + amount + '\'' +
                '}';
    }
}

package com.Shop.EasyBy.entities;

// SectionRow.java
public class SectionRow {
    private String label;
    private String count;
    private String creditAmount;
    private String debitAmount;
    private Object totalAmount; // keep as String or Map depending on cas
    private String clearingAmount;

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getCreditAmount() {
        return creditAmount;
    }

    public void setCreditAmount(String creditAmount) {
        this.creditAmount = creditAmount;
    }

    public String getDebitAmount() {
        return debitAmount;
    }

    public void setDebitAmount(String debitAmount) {
        this.debitAmount = debitAmount;
    }

    public Object getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Object totalAmount) {
        this.totalAmount = totalAmount;
    }

    @Override
    public String toString() {
        return "SectionRow{" +
                "label='" + label + '\'' +
                ", count='" + count + '\'' +
                ", creditAmount='" + creditAmount + '\'' +
                ", debitAmount='" + debitAmount + '\'' +
                ", totalAmount=" + totalAmount +
                ", clearingAmount='" + clearingAmount + '\'' +
                '}';
    }

    public String getClearingAmount() {
        return clearingAmount;
    }

    public void setClearingAmount(String clearingAmount) {
        this.clearingAmount = clearingAmount;
    }
}




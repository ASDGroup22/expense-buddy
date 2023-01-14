package org.expense.tracker.models;

import java.util.Date;

public class Transaction {
    private int transactionId;
    private double amount;
    private boolean isRecurring;
    private String note;
    private Category category;
    private Date transactionDate;

    private boolean isExpense;

    public Transaction() {
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    public Transaction(float amount, boolean isRecurring, String note, Category category, boolean isExpense) {
        this.amount = amount;
        this.isRecurring = isRecurring;
        this.note = note;
        this.category = category;
        this.isExpense = isExpense;
    }

    public Transaction(int transactionId, float amount, boolean isRecurring, String note, Category category,
                       Date transactionDate, boolean isExpense) {
        this.transactionId = transactionId;
        this.amount = amount;
        this.isRecurring = isRecurring;
        this.note = note;
        this.category = category;
        this.transactionDate = transactionDate;
        this.isExpense = isExpense;
    }

    public Transaction(float amount, boolean isRecurring, String note, Category category, Date transactionDate, boolean isExpense) {
        this.amount = amount;
        this.isRecurring = isRecurring;
        this.note = note;
        this.category = category;
        this.transactionDate = transactionDate;
        this.isExpense = isExpense;
    }
    

    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public boolean isRecurring() {
        return isRecurring;
    }

    public void setRecurring(boolean recurring) {
        this.isRecurring = recurring;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "isExpense=" + isExpense +
                ", amount=" + amount +
                ", recurring=" + isRecurring +
                ", note='" + note + '\'' +
                ", category=" + category +
                '}';
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public boolean isExpense() {
        return isExpense;
    }

    public void setIsExpense(boolean expense) {
        isExpense = expense;
    }
}

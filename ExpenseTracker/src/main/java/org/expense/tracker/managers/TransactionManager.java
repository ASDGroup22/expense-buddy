package org.expense.tracker.managers;

import org.expense.tracker.models.Category;
import org.expense.tracker.models.Transaction;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TransactionManager {
    private List<Transaction> transactionList;

    public TransactionManager() {
        this.transactionList = new ArrayList<Transaction>();
    }
    

    public int createTransaction(float amount, boolean recurring, String note, Category category, Date date,
                                 boolean isExpense){
        int id = transactionList.size();
        Transaction transaction = new Transaction(id, amount, recurring, note, category, date, isExpense);
        transactionList.add(transaction);
        return id;
    }

    public void setTransactionList(List<Transaction> transactionList) {
        this.transactionList = transactionList;
    }

    public List<Transaction> getTransactionList() {
        return transactionList;
    }


    public void deleteTransaction(int transactionId) {
        transactionList.remove(transactionId);
    }

    public Transaction getTransaction(int transId){
        for (Transaction transactionObj: transactionList) {
            if (transId == transactionObj.getTransactionId()) {
                return transactionObj;
            }
        }
        return null;
    }
    
    public void updateTransaction(Transaction transaction) {
        transactionList.set(getTransactionPosition(transaction), transaction);
    }

    private int getTransactionPosition(Transaction transaction){
        for (Transaction item : transactionList) {
            if (item.getTransactionId() == transaction.getTransactionId()){
                return transactionList.indexOf(transaction);
            }
        }
        return -1;
    }

    public void updateTransaction(int transactionID, float amount, boolean recurring, String note, Category category) {
        Transaction transaction = getTransaction(transactionID);
        transaction.setAmount(amount);
        transaction.setRecurring(recurring);
        transaction.setNote(note);
        transaction.setCategory(category);
    }


    @Override
    public String toString() {
        return "TransactionManager{" +
                "transactionList=" + transactionList.toString() +
                '}';
    }
}

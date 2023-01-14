package org.expense.tracker;

import org.codehaus.plexus.util.FileUtils;
import org.expense.tracker.controllers.Controller;
import org.expense.tracker.models.Category;
import org.expense.tracker.models.User;
import org.expense.tracker.models.Transaction;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

public class TestController {

    @Test
    public void testSqlFlow() throws IOException {
        FileUtils.deleteDirectory(new File("./data"));
        executeTests();
    }

    @Test(dependsOnMethods = {"testSqlFlow"})
    public void testInMemFlow() {
        System.setProperty("config.file", "inmemory.properties");
        executeTests();
    }

    private void executeTests() {
        Controller controller = new Controller();
        int profId = testProfiles(controller);
        testCategories(controller, profId);
        testTransactions(controller, profId);
        testBudget(controller, profId);
    }

    private int testProfiles(Controller controller) {
        controller.createUser("user01");
        Assert.assertEquals(controller.getUsers().get(0).getUserName(), "user01");
        controller.deleteUser(0);
        Assert.assertEquals(controller.getUsers().size(), 0);

        int profId = controller.createUser("user02");
        User userDb = controller.getUsers().get(profId);
        Assert.assertEquals(userDb.getUserName(), "user02");
        userDb.setUserName("user03");
        controller.updateUser(userDb);
        Assert.assertEquals(controller.getUser(profId).getUserName(), "user03");
        return profId;
    }

    private void testCategories(Controller controller, int userId) {
        int categoryId = controller.createCategory(userId, "Investment", false);
        controller.createCategory(userId, "Education", true);

        Assert.assertEquals(controller.getCategories(userId).size(), 7);
        Assert.assertEquals(controller.getIncomeCategories(userId).size(), 3);
        Assert.assertEquals(controller.getIncomeCategories(userId).get(2).getName(), "Investment");
        Assert.assertEquals(controller.getExpenseCategories(userId).size(), 4);
        Assert.assertEquals(controller.getExpenseCategories(userId).get(3).getName(), "Education");

        Category category = controller.getCategories(userId).get(categoryId);
        category.setName("Dividend");
        controller.updateCategory(userId, category);
        Assert.assertEquals(controller.getIncomeCategories(userId).get(2).getName(), "Dividend");

        int tempCategory = controller.createCategory(userId, "temp category", false);
        Assert.assertEquals(controller.getIncomeCategories(userId).size(), 4);
        controller.deleteCategory(userId, controller.getCategories(userId).get(tempCategory));
        Assert.assertEquals(controller.getIncomeCategories(userId).size(), 3);
    }

    private void testTransactions(Controller controller, int profileId) {
        int transactionId = controller.createTransaction(profileId, 1000, false, "test transaction",
                controller.getIncomeCategories(profileId).get(0), new Date(), false);

        Transaction testTransaction = controller.getTransactions(profileId).get(transactionId);
        testTransaction.setAmount(1200);

        controller.updateTransaction(transactionId, testTransaction);
        Assert.assertEquals(controller.getTransactions(profileId).get(transactionId).getAmount(), 1200);

        controller.deleteTransaction(profileId, testTransaction);
        Assert.assertEquals(controller.getTransactions(profileId).size(), 0);

        controller.createTransaction(profileId, 1100, false, "give treat",
                controller.getIncomeCategories(profileId).get(0), new Date(), false);

        controller.createTransaction(profileId, 100, false, "buy breakfast",
                controller.getExpenseCategories(profileId).get(0), new Date(), true);

        Assert.assertEquals(controller.getTransactions(profileId).size(), 2);
        Assert.assertEquals(controller.getIncomeTransactions(profileId).size(), 1);
        Assert.assertEquals(controller.getExpenseTransactions(profileId).size(), 1);

        Assert.assertNotNull(controller.getIncomeTransactions(profileId).get(0).getTransactionDate());
        Transaction transaction = controller.getIncomeTransactions(profileId).get(0);
        Date newDate = new Date(2023, Calendar.JANUARY, 10);
        transaction.setTransactionDate(newDate);
        controller.updateTransaction(profileId, transaction);
        Assert.assertEquals(controller.getIncomeTransactions(profileId).get(0).getTransactionDate(), newDate);

        Assert.assertEquals(controller.getIncomeTransactions(profileId).get(0).getNote(), "give treat");
        Assert.assertEquals(controller.getExpenseTransactions(profileId).get(0).getNote(), "buy breakfast");

        Assert.assertEquals(controller.getExpenseSummary(profileId), 100.0);
        Assert.assertEquals(controller.getIncomeSummary(profileId), 1100.0);

        Assert.assertEquals(controller.getCategorySummary(profileId,
                controller.getIncomeCategories(profileId).get(0).getId()), 1100.0);
        Assert.assertEquals(controller.getCategorySummary(profileId,
                controller.getExpenseCategories(profileId).get(0).getId()), 100.0);
    }

    private void testBudget(Controller controller, int userId) {
        Assert.assertEquals(controller.getExpenseCategories(userId).size(), 4);
        controller.updateBudget(userId, controller.getExpenseCategories(userId).get(0).getId(), 10000.00);
        controller.updateBudget(userId, controller.getExpenseCategories(userId).get(1).getId(), 20000.00);
        controller.updateBudget(userId, controller.getExpenseCategories(userId).get(2).getId(), 30000.00);
        controller.updateBudget(userId, controller.getExpenseCategories(userId).get(3).getId(), 40000.00);
        Assert.assertEquals(controller.getTotalBudget(userId), 100000);
    }
}

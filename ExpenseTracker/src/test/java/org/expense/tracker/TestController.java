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
    }

    private int testProfiles(Controller controller) {
        Assert.assertEquals(controller.getProfiles().get(0).getUserName(), "Profile");
        controller.deleteProfile(0);
        Assert.assertEquals(controller.getProfiles().size(),0);

        int profId = controller.createProfile("Dilhasha",500);
        User userDb = controller.getProfiles().get(profId);
        Assert.assertEquals(userDb.getUserName(), "Dilhasha");
        userDb.setBudget(1000);

        controller.updateProfile(userDb);
        Assert.assertEquals(controller.getProfile(profId).getBudget(), 1000f);
        return profId;
    }

    private void testCategories(Controller controller, int profId) {
        int salaryCatId = controller.createIncomeCategory(profId, "Investment");
        controller.createCategory(profId, "Outings", true);

        Assert.assertEquals(controller.getCategories(profId).size(),7);
        Assert.assertEquals(controller.getIncomeCategories(profId).size(),3);
        Assert.assertEquals(controller.getIncomeCategories(profId).get(2).getName(),"Investment");
        Assert.assertEquals(controller.getExpenseCategories(profId).size(),4);
        Assert.assertEquals(controller.getExpenseCategories(profId).get(3).getName(),"Outings");

        Category saleryCat = controller.getCategories(profId).get(salaryCatId);
        saleryCat.setName("SalaryNew");
        controller.updateCategory(profId,saleryCat);
        Assert.assertEquals(controller.getIncomeCategories(profId).get(2).getName(),"SalaryNew");

        int tempCat = controller.createIncomeCategory(profId, "temp cat");
        Assert.assertEquals(controller.getIncomeCategories(profId).size(), 4);
        controller.deleteCategory(profId, controller.getCategories(profId).get(tempCat));
        Assert.assertEquals(controller.getIncomeCategories(profId).size(), 3);
    }

    private void testTransactions(Controller controller, int profId) {
        int testTrans = controller.createTransaction(profId, 1000, false, "test trans",
                controller.getIncomeCategories(profId).get(0), new Date(), false);
        Transaction testTransObj = controller.getTransactions(profId).get(testTrans);
        testTransObj.setAmount(1200);
        controller.updateTransaction(testTrans, testTransObj);
        Assert.assertEquals(controller.getTransactions(profId).get(testTrans).getAmount(), 1200f);

        controller.deleteTransaction(profId, testTransObj);
        Assert.assertEquals(controller.getTransactions(profId).size(), 0);

        controller.createTransaction(profId, 1100, false, "first month salary",
                controller.getIncomeCategories(profId).get(0), new Date(), false);

        controller.createTransaction(profId, 100, false, "kingsburry",
                controller.getExpenseCategories(profId).get(0), new Date(), true);

        Assert.assertEquals(controller.getTransactions(profId).size(),2);
        Assert.assertEquals(controller.getIncomeTransactions(profId).size(),1);
        Assert.assertEquals(controller.getExpenseTransactions(profId).size(),1);

        Assert.assertNotNull(controller.getIncomeTransactions(profId).get(0).getTransactionDate());
        Transaction transaction = controller.getIncomeTransactions(profId).get(0);
        Date newDate = new Date(2022, Calendar.FEBRUARY, 23);
        transaction.setTransactionDate(newDate);
        controller.updateTransaction(profId,transaction);
        Assert.assertEquals(controller.getIncomeTransactions(profId).get(0).getTransactionDate(),newDate);

        Assert.assertEquals(controller.getIncomeTransactions(profId).get(0).getNote(), "first month salary");
        Assert.assertEquals(controller.getExpenseTransactions(profId).get(0).getNote(), "kingsburry");

        Assert.assertEquals(controller.getExpenseSummary(profId), 100f);
        Assert.assertEquals(controller.getIncomeSummary(profId), 1100f);

        Assert.assertEquals(controller.getCategorySummary(profId,
                controller.getIncomeCategories(profId).get(0).getId()), 1100f);
        Assert.assertEquals(controller.getCategorySummary(profId,
                controller.getExpenseCategories(profId).get(0).getId()), 100f);
    }
}

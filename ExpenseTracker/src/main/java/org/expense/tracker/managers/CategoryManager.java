package org.expense.tracker.managers;

import org.expense.tracker.models.Category;

import java.util.ArrayList;
import java.util.List;

public class CategoryManager {
    private List<Category> categoryList;
    
    public CategoryManager() {
        this.categoryList = new ArrayList<>();
    }

    public List<Category> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<Category> categoryList) {
        this.categoryList = categoryList;
    }
    
    public int createCategory(String name, boolean isExpenseCategory) {
        int id = categoryList.size();
        // TODO: Add description
        Category expenseCategory = new Category(id, name, name, isExpenseCategory);
        categoryList.add(expenseCategory);
        return id;
    }

    public void deleteCategory(Category category) {
        categoryList.remove(category);
    }

    public void updateCategory(Category newCategory) {
        int index= -1;
        for (int i = 0; i < categoryList.size(); i++) {
            Category categoryObj = categoryList.get(i);
            if (categoryObj.getId() == newCategory.getId()) {
                index =i;
            }
        }
        if (index != -1) {
            categoryList.set(index, newCategory);
        }
    }
    

    public Category getCategory(int catId){
        for (Category categoryObj: categoryList) {
           if (catId == categoryObj.getId()) {
               return categoryObj;
           }
        }
        return null;
    }

    public List<Category> getIncomeCategories() {
        List<Category> incomeList = new ArrayList<>();
        for (Category category: categoryList) {
            if (!category.isExpenseCategory()) {
                incomeList.add(category);
            }
        }
         return incomeList;
    }

    public List<Category> getExpenseCategories() {
        List<Category> expenseList = new ArrayList<>();
        for (Category category: categoryList) {
            if (category.isExpenseCategory()) {
                expenseList.add(category);
            }
        }
        return expenseList;
    }

}

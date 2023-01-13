package org.expense.tracker.ui.components;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import org.expense.tracker.managers.CategoryManager;
import org.expense.tracker.models.Category;
import org.expense.tracker.models.CategoryBudget;
import org.expense.tracker.models.Transaction;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class BudgetListViewCell extends ListCell<CategoryBudget>{

    @FXML
    private Label category;

    @FXML
    private Label amount;

    @FXML
    private ImageView budgetItemEditIcon;


    @FXML
    private HBox listItem;

    FXMLLoader mLLoader;

    FileInputStream imageEdit ;


    BudgetListViewCell(){
        
    }


    @Override
    protected void updateItem(CategoryBudget object, boolean empty) {
        super.updateItem(object, empty);

        if(empty || object == null) {

            setText(null);
            setGraphic(null);

        } else {
            
            if (mLLoader == null) {

                mLLoader = new FXMLLoader(getClass().getResource("../../fxml/budget_list_item.fxml"));
                mLLoader.setController(this);

                try {
                    mLLoader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

            try {

                Category itemCategory = object.getCategory();
                imageEdit = new FileInputStream(new File(this.getClass().getResource("../../images/icon-edit.png").getFile()));


                
                budgetItemEditIcon.setImage(new Image(imageEdit));

                category.setText(object.getCategory().getName());
                amount.setText(String.valueOf(object.getBudgetVal()));

                category.getStyleClass().add("label-black");
            } catch (Exception e) {
                e.printStackTrace();
            }
            
            setText(null);
            setId(String.valueOf(object.getCategoryId()));
            setGraphic(listItem);

        }

    }
    
}

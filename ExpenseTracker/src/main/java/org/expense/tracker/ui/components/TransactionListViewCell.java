package org.expense.tracker.ui.components;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.expense.tracker.models.Category;
import org.expense.tracker.models.Transaction;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class TransactionListViewCell extends ListCell<Transaction>{

    @FXML
    private Label category;

    @FXML
    private Label date;

    @FXML
    private Label amount;

    @FXML
    private ImageView transactionItemIcon;

    @FXML
    private ImageView transactionItemEditIcon;


    @FXML 
    private HBox listItem;

    FXMLLoader mLLoader;

    FileInputStream imageExpense ;
    FileInputStream imageIncome ;
    FileInputStream imageEdit ;

    
    TransactionListViewCell(){
        
    }


    @Override
    protected void updateItem(Transaction object, boolean empty) {
        super.updateItem(object, empty);

        if(empty || object == null) {

            setText(null);
            setGraphic(null);

        } else {
            
            if (mLLoader == null) {
                mLLoader = new FXMLLoader(getClass().getResource("../../fxml/transaction_list_item.fxml"));
                mLLoader.setController(this);

                try {
                    mLLoader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

            try {

                Category itemCategory =  object.getCategory();
                imageExpense = new FileInputStream(new File(this.getClass().getResource("../../images/icon-expense.png").getFile()));
                imageIncome = new FileInputStream(new File(this.getClass().getResource("../../images/icon-income.png").getFile()));
                imageEdit = new FileInputStream(new File(this.getClass().getResource("../../images/icon-edit.png").getFile()));

                if (itemCategory.isExpenseCategory()) {
                    amount.getStyleClass().add("label-red");
                    transactionItemIcon.setImage(new Image(imageExpense));
                } else {
                    amount.getStyleClass().add("label-green");
                    transactionItemIcon.setImage(new Image(imageIncome));
                }
                
                transactionItemEditIcon.setImage(new Image(imageEdit));

                category.setText(object.getCategory().getName());
                date.setText(object.getTransactionDate().toString());
                amount.setText(String.valueOf(object.getAmount()));

                category.getStyleClass().add("label-black");
                date.getStyleClass().add("label-black");

            } catch (Exception e) {
                e.printStackTrace();
            }
            
            setText(null);
            setId(String.valueOf(object.getTransactionId()));
            setGraphic(listItem);

        }

    }
    
}

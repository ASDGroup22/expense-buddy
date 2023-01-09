package org.expense.tracker.ui.components;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.expense.tracker.models.Category;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class CategoryListViewCell extends ListCell<Category>{

    @FXML
    private Label category;

    @FXML 
    private HBox listItem;

    @FXML
    ImageView categoryItemIcon;

    @FXML
    ImageView categoryItemEditIcon;

    FXMLLoader mLLoader;

    FileInputStream imageCategory ;
    FileInputStream imageEdit;
    
    CategoryListViewCell(){
        
    }

    @Override
    protected void updateItem(Category object, boolean empty) {
        super.updateItem(object, empty);

        if(empty || object == null) {

            setText(null);
            setGraphic(null);

        } else {
            
            if (mLLoader == null) {
                mLLoader = new FXMLLoader(getClass().getResource("../../fxml/category_list_item.fxml"));
                mLLoader.setController(this);

                

                try {
                    mLLoader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

            try {

                imageCategory = new FileInputStream(new File(this.getClass().getResource("../../images/icon-category.png").getFile()));
                imageEdit = new FileInputStream(new File(this.getClass().getResource("../../images/icon-edit.png").getFile()));

                categoryItemIcon.setImage(new Image(imageCategory));
                categoryItemEditIcon.setImage(new Image(imageEdit));


                category.setText(object.getName());
                category.getStyleClass().add("label-black");

            } catch (Exception e) {

            }
            
            setId(String.valueOf(object.getId()));
            setGraphic(listItem);

        }

    }
    
}

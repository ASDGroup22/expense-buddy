package org.expense.tracker.ui.components;

import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;

public class HeadLabel {

    HBox hbox;

    Label label;

    Region region1;
    Region region2;


    public HeadLabel() {
        this.hbox = new HBox();
        this.region1 = new Region();
        this.region2 = new Region();
        this.label = new Label();

        HBox.setHgrow(region1, Priority.ALWAYS);
        HBox.setHgrow(region2, Priority.ALWAYS);

        label.getStyleClass().add("label-head");

        hbox.getChildren().addAll(region1, label, region2);

    }

    public HBox getHeadLabel(String title){
        label.setText(title);
        return hbox;

    }

    
}



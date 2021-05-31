package org.example;

import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class CycleOutputWindow {

    public Stage stage = new Stage();

    public CycleOutputWindow(){ }

    public void openCycleOutput(Stage ownerStage, String cycles){

        VBox root = new VBox();

        TextArea area = new TextArea(cycles);
        area.setEditable(false);
        area.setPrefHeight(10000);
        root.getChildren().add(area);



        Scene scene = new Scene(root, 1000, 1000);
        stage.setTitle("Cycles");
        stage.initOwner(ownerStage);
        stage.initModality(Modality.WINDOW_MODAL);
        stage.setScene(scene);
        stage.show();

    }

}

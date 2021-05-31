package org.example;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ResultWindow{

    public Stage stage = new Stage();


    public ResultWindow(){}

    public void openResultWindow(Stage ownerStage, int i, int d, int r, String s){
        VBox root = new VBox();
        root.setSpacing(10);
        root.setPadding(new Insets(10));

        HBox infectedBox = new HBox();
        infectedBox.setSpacing(10);

        HBox deadBox = new HBox();
        deadBox.setSpacing(10);

        HBox recoveredBox = new HBox();
        recoveredBox.setSpacing(10);

        Label infectedLabel =  new Label("Total number of infected: ");
        TextField infectedTextField = new TextField(String.valueOf(i));
        infectedTextField.setEditable(false);

        infectedBox.getChildren().addAll(infectedLabel, infectedTextField);

        Label deadLabel = new Label("Total number of dead: ");
        TextField deadTextField = new TextField(String.valueOf(d));
        deadTextField.setEditable(false);
        deadBox.getChildren().addAll(deadLabel, deadTextField);

        Label recoveredLabel= new Label("Total number of recovered");
        TextField recoveredTextField = new TextField(String.valueOf(r));
        recoveredTextField.setEditable(false);
        recoveredBox.getChildren().addAll(recoveredLabel, recoveredTextField);

        Button viewCycleOutput = new Button("Wyświetl wyniki poszczególnych cykli");

        viewCycleOutput.setOnAction(actionEvent -> {
            new CycleOutputWindow().openCycleOutput(stage, s);
        });

        root
                .getChildren()
                .addAll(infectedBox, deadBox, recoveredBox, viewCycleOutput);


        Scene scene = new Scene(root, 380, 180);
        stage.setTitle("Results");
        stage.setResizable(false);
        stage.initOwner(ownerStage);
        stage.initModality(Modality.WINDOW_MODAL);
        stage.setScene(scene);
        stage.show();
    }
}

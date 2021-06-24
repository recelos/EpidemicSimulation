package gui.windows;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * The type Cycle output window.
 */
public class CycleOutputWindow {

    /**
     * The Stage.
     */
    public Stage stage = new Stage();

    /**
     * Instantiates a new Cycle output window.
     */
    public CycleOutputWindow(){ }

    /**
     * Open cycle output.
     *
     * @param ownerStage the owner stage
     * @param cycles     the cycles
     */
    public void openCycleOutput(Stage ownerStage, String cycles){

        VBox root = new VBox();
        root.setSpacing(10);

        TextArea area = new TextArea(cycles);
        area.setEditable(false);
        area.setPrefHeight(550);
        area.setPadding(new Insets(2));

        root
                .getChildren()
                .addAll(area);


        Scene scene = new Scene(root, 480, 550);
        stage.setTitle("Cycles");
        stage.initOwner(ownerStage);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }
}

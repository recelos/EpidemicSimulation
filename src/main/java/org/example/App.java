package org.example;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


/**
 * JavaFX App
 */
public class App extends Application {

    @Override
    public void start(Stage stage) {

        VBox root = new VBox();
        HBox agentBox = new HBox();
        agentBox.setSpacing(10);
        HBox infectedBox = new HBox();
        infectedBox.setSpacing(10);

        root.setPadding(new Insets(10));
        root.setSpacing(10);
       // root.setAlignment(Pos.CENTER);

        Label agentNumberLabel = new Label("Podaj początkową liczbę agentów:");
        TextField agentNumberTextField = new TextField();
        agentNumberTextField.setPadding(new Insets(7));
        agentNumberTextField.setMaxWidth(100);
        agentBox.getChildren().addAll(agentNumberLabel, agentNumberTextField);
        agentBox.setAlignment(Pos.BASELINE_LEFT);


        Label infectedNumberLabel = new Label("Podaj początkową liczbę zarażonych:");
        TextField infectedNumberTextField = new TextField();
        infectedNumberTextField.setPadding(new Insets(7));
        infectedNumberTextField.setMaxWidth(100);
        infectedBox.getChildren().addAll(infectedNumberLabel,infectedNumberTextField);
        infectedBox.setAlignment(Pos.BASELINE_LEFT);


        Label contagiousnessIndicatorLabel = new Label("Zaraźliwość");
        contagiousnessIndicatorLabel.setAlignment(Pos.CENTER);
        Slider contagiousnessSlider = new Slider(0.01, 1, 0.01);
        Label contagiousnessSliderValue = new Label();

        contagiousnessSlider.setMajorTickUnit(0.05);
        contagiousnessSlider.setShowTickMarks(true);
        contagiousnessSlider.setShowTickLabels(true);

        contagiousnessSlider.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> ov,
                                Number old_val, Number new_val) {
                contagiousnessSliderValue.setText(String.format("%.2f", new_val));
            }
        });

        Label mortalityIndicatorLabel = new Label("Śmiertelność");
        Slider mortalitySlider = new Slider(0.01,0.5, 0.01);
        Label mortalitySliderValue = new Label();

        mortalitySlider.setShowTickMarks(true);
        mortalitySlider.setShowTickLabels(true);
        mortalitySlider.setMajorTickUnit(0.01);

        mortalitySlider.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> ov,
                                Number old_val, Number new_val) {
                mortalitySliderValue.setText(String.format("%.3f", new_val));
            }
        });


        Button startButton = new Button("Start");
        startButton.setPrefWidth(80);

        root.getChildren().addAll(agentBox, infectedBox, contagiousnessIndicatorLabel, contagiousnessSlider, contagiousnessSliderValue,
                mortalityIndicatorLabel, mortalitySlider, mortalitySliderValue, startButton);



        var scene = new Scene(root, 640, 360);
        stage.setTitle("Epidemic Simulation");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
        startButton.setOnAction(actionEvent -> {
            if(Integer.parseInt(agentNumberTextField.getText()) >= Integer.parseInt(infectedNumberTextField.getText())){
                Linker.passValueToSimulation(Integer.parseInt(agentNumberTextField.getText()), Integer.parseInt(infectedNumberTextField.getText()),
                        contagiousnessSlider.getValue(), mortalitySlider.getValue());
                new ResultWindow().openResultWindow(stage, Linker.getInfectedQuantity(), Linker.getDeadQuantity(), Linker.getRecoveredQuantity(), Linker.getOutputString());
            }
            else{
                Alert error = new Alert(Alert.AlertType.ERROR);
                error.setTitle("Błąd");
                error.setHeaderText("Podałeś błędne dane");
                error.setContentText("Spróbuj ponownie");
                error.showAndWait();
            }
        });
    }



    public static void main(String[] args) {
        launch();
    }

}
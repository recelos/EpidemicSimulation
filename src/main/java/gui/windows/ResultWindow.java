package gui.windows;

import simulation.handling.statistics.ChartDataHolder;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * The type Result window.
 */
public class ResultWindow{

    /**
     * The Stage.
     */
    public Stage stage = new Stage();


    public ResultWindow(){}

    /**
     * Open result window.
     *
     * @param ownerStage the owner stage
     * @param i          the
     * @param d          the d
     * @param r          the r
     * @param s          the s
     */
    public void openResultWindow(Stage ownerStage, int i, int d, int r, String s,
                                 ChartDataHolder singleCycleCharts, ChartDataHolder finalCharts){
        VBox root = new VBox();
        root.setSpacing(10);
        root.setPadding(new Insets(10));

        HBox infectedBox = new HBox();
        infectedBox.setSpacing(10);

        HBox deadBox = new HBox();
        deadBox.setSpacing(10);

        HBox recoveredBox = new HBox();
        recoveredBox.setSpacing(10);

        Label infectedLabel =  new Label("Łączna liczba zarażonych: ");
        TextField infectedTextField = new TextField(String.valueOf(i));
        infectedTextField.setEditable(false);

        infectedBox.getChildren().addAll(infectedLabel, infectedTextField);

        Label deadLabel = new Label("Łączna liczba martwych: ");
        TextField deadTextField = new TextField(String.valueOf(d));
        deadTextField.setEditable(false);
        deadBox.getChildren().addAll(deadLabel, deadTextField);

        Label recoveredLabel= new Label("Łączna liczba wyzdrowiałych");
        TextField recoveredTextField = new TextField(String.valueOf(r));
        recoveredTextField.setEditable(false);
        recoveredBox.getChildren().addAll(recoveredLabel, recoveredTextField);

        Button viewCycleOutput = new Button("Wyświetl wyniki poszczególnych cykli");

        Button viewCycleCharts = new Button("Nowe przypadki");


        viewCycleOutput.setOnAction(actionEvent -> new CycleOutputWindow()
                .openCycleOutput(stage, s));
        viewCycleCharts.setOnAction(actionEvent -> new ChartWindow()
                .openChartWindow(stage, singleCycleCharts, "Nowe przypadki"));


        Button viewFinalCharts = new Button("Łącznie");

        viewFinalCharts.setOnAction(actionEvent -> new ChartWindow()
                .openChartWindow(stage, finalCharts, "Łącznie"));


        HBox chartButtons = new HBox();
        chartButtons.setAlignment(Pos.CENTER);
        chartButtons.setSpacing(20);
        chartButtons.getChildren().addAll(viewCycleCharts, viewFinalCharts);

        Label chartLabel = new Label("Wykresy");
        HBox chartLabelBox = new HBox();
        chartLabelBox.setAlignment(Pos.CENTER);
        chartLabelBox.setSpacing(20);
        chartLabelBox.getChildren().add(chartLabel);


        Separator separator = new Separator();


        root
                .getChildren()
                .addAll(infectedBox, deadBox, recoveredBox, viewCycleOutput, separator, chartLabelBox, chartButtons);


        Scene scene = new Scene(root, 500, 230);
        setStage(ownerStage, scene);
        stage.show();
    }

    private void setStage(Stage ownerStage, Scene scene) {
        stage.setTitle("Rezultaty");
        stage.setResizable(false);
        stage.initOwner(ownerStage);
        stage.initModality(Modality.WINDOW_MODAL);
        stage.setScene(scene);
    }
}

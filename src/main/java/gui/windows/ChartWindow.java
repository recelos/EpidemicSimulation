package gui.windows;

import simulation.handling.statistics.ChartDataHolder;
import javafx.scene.chart.*;
import javafx.stage.Stage;
import javafx.scene.Scene;


public class ChartWindow {
    public ChartWindow(){}

    public void openChartWindow(Stage ownerStage, ChartDataHolder chart, String chartTitle) {

        Stage stage = new Stage();
        stage.setTitle("Wykres");

        final Axis<String> xAxis = new CategoryAxis();
        final Axis<Number> yAxis = new NumberAxis();

        xAxis.setLabel("cykl");
        yAxis.setLabel("l. agentów");


        final LineChart<String, Number> lineChart = new LineChart<>(xAxis, yAxis);
        lineChart.setCreateSymbols(false);


        lineChart.setTitle(chartTitle);
        lineChart.setStyle("CHART_COLOR_1: red; CHART_COLOR_2: green; CHART_COLOR_3: black;");


        XYChart.Series<String, Number> infections = new XYChart.Series<>();
        for (int i = 0; i < chart.getInfections().size(); i++) {
            infections.getData().add(new XYChart.Data<>(String.format("%d", i + 1), chart.getInfections().get(i)));
        }
        infections.setName("zarażenia");


        XYChart.Series<String, Number> deaths = new XYChart.Series<>();
        for (int i = 0; i < chart.getDeaths().size(); i++) {
            deaths.getData().add(new XYChart.Data<>(String.format("%d", i + 1), chart.getDeaths().get(i)));
        }
        deaths.setName("zgony");


        XYChart.Series<String, Number> recovers = new XYChart.Series<>();
        for (int i = 0; i < chart.getRecovers().size(); i++) {
            recovers.getData().add(new XYChart.Data<>(String.format("%d", i + 1), chart.getRecovers().get(i)));
        }
        recovers.setName("uzdrowienia");

        lineChart
                .getData()
                .addAll(infections, recovers, deaths);


            Scene scene = new Scene(lineChart, 800, 640);
            stage.setScene(scene);
            stage.initOwner(ownerStage);
            stage.show();
    }
}

package gui.windows;

import controller.Controller;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * The type User input reader.
 */
public class UserInputReader {

    /**
     * Instantiates a new User input reader.
     */
    public UserInputReader() {}

    /**
     * Open user input reader.
     *
     * @param stage the stage
     */
    public void OpenUserInputReader(Stage stage){
        VBox root = new VBox();

        setupRoot(root);

        HBox agentBox = new HBox();

        Label agentNumberLabel = new Label("Podaj początkową liczbę agentów:");
        TextField agentNumberTextField = new TextField();


        Label agentWarning = new Label("Uwaga! Długi czas przeprowadzania symulacji");
        agentWarning.setVisible(false);





        setupTextField(agentNumberTextField);
        setupHBox(agentBox,agentNumberLabel,agentNumberTextField);

        agentNumberTextField.textProperty().addListener(actionEvent -> {
            if (isStringInteger(agentNumberTextField.getText())) {
                if (Integer.parseInt(agentNumberTextField.getText()) >= 400000) {
                    agentWarning.setVisible(true);
                }
                else if(Integer.parseInt(agentNumberTextField.getText()) < 400000){
                    agentWarning.setVisible(false);
                }
            }
        });

        agentBox.getChildren()
                .add(agentWarning);

        HBox infectedBox = new HBox();

        Label infectedNumberLabel = new Label("Podaj początkową liczbę zarażonych:");
        TextField infectedNumberTextField = new TextField();

        setupTextField(infectedNumberTextField);
        setupHBox(infectedBox, infectedNumberLabel, infectedNumberTextField);





        Label contagiousnessIndicatorLabel = new Label("Zaraźliwość");
        Slider contagiousnessSlider = new Slider(0.01, 1, 0.01);

        Label contagiousnessSliderValue = new Label();
        setupSlider(contagiousnessSlider,contagiousnessSliderValue, 0.05, "%.2f");
        
        
        
        Label mortalityIndicatorLabel = new Label("Śmiertelność");
        Slider mortalitySlider = new Slider(0.01,1, 0.01);

        Label mortalitySliderValue = new Label();
        setupSlider(mortalitySlider, mortalitySliderValue, 0.05, "%.2f");


        HBox diseaseLengthBox = new HBox();
        Label diseaseLengthLabel = new Label("Długość choroby (w cyklach)");
        diseaseLengthBox.setSpacing(10);
        ChoiceBox<Integer> diseaseLengthCBox = new ChoiceBox<>();
        ObservableList<Integer> array = diseaseLengthCBox.getItems();

        array.addAll(3,4,5,6,7,8);



        diseaseLengthCBox.setValue(array.get(2));
        diseaseLengthCBox.getSelectionModel().selectedIndexProperty().addListener((observableValue, number, t1)
                -> diseaseLengthCBox
                .setValue(array.get(t1.intValue())));

        diseaseLengthBox.getChildren().addAll(diseaseLengthLabel,diseaseLengthCBox);

        HBox buttonBox = new HBox();
        Button startButton = setupStartButton();
        buttonBox.setAlignment(Pos.BOTTOM_CENTER);
        buttonBox.getChildren().add(startButton);



        root
                .getChildren()
                .addAll(agentBox, infectedBox, contagiousnessIndicatorLabel, contagiousnessSlider, contagiousnessSliderValue,
                mortalityIndicatorLabel, mortalitySlider, mortalitySliderValue, diseaseLengthBox, buttonBox);


        var scene = new Scene(root, 640, 370);

        setupStage(stage, scene);


        startButton.setOnAction(actionEvent -> {
            if(correctData(agentNumberTextField.getText(), infectedNumberTextField.getText())){


                Controller controller = new Controller(Integer.parseInt(agentNumberTextField.getText()), Integer.parseInt(infectedNumberTextField.getText()),
                        contagiousnessSlider.getValue(), mortalitySlider.getValue(), diseaseLengthCBox.getValue()).passValueToSimulation();




                new ResultWindow()
                        .openResultWindow(stage, controller.getInfected(), controller.getDead(),
                                controller.getRecovered(), controller.getCyclesOutput(),
                                controller.getSingleCycleCharts(), controller.getFinalCharts());
            }
            else{
                Alert error = setupError();
                error.showAndWait();
            }
        });
    }

    private void setupRoot(VBox root) {
        root.setPadding(new Insets(5));
        root.setSpacing(10);
    }


    private void setupTextField(TextField field){
        field.setPadding(new Insets(7));
        field.setMaxWidth(100);
    }

    private void setupHBox(HBox box, Label label, TextField textField){
        box.setAlignment(Pos.BASELINE_LEFT);
        box.setSpacing(10);
        box
                .getChildren()
                .addAll(label, textField);
    }


    private Button setupStartButton(){
        Button button = new Button("Start");
        button.setAlignment(Pos.BOTTOM_CENTER);
        button.setPrefWidth(80);
        return button;
    }

    private void setupSlider(Slider slider, Label sliderValue, double tick, String format){
        slider.setMajorTickUnit(tick);
        slider.setShowTickMarks(true);
        slider.setShowTickLabels(true);

        slider
                .valueProperty()
                .addListener((ov, old_val, new_val)
                        -> sliderValue.setText(String.format(format, new_val)));
    }



    private boolean correctData(String s1, String s2){
        if(isStringInteger(s1) && isStringInteger(s2)){
            return (Integer.parseInt(s1) >= Integer.parseInt(s2)) &&
                    Integer.parseInt(s1) >= 0 && Integer.parseInt(s2) >= 0;
        }
        return false;
    }
    private void setupStage(Stage stage, Scene scene){
        stage.setTitle("Epidemic Simulation");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    private boolean isStringInteger(String s){
        try{
            Integer.parseInt(s);
        }catch(Exception e){
            return false;
        }
        return true;
    }

    private Alert setupError(){
        Alert output = new Alert(Alert.AlertType.ERROR);
        output.setTitle("Błąd");
        output.setHeaderText("Podałeś błędne dane");
        output.setContentText("Spróbuj ponownie");
        return output;
    }
}

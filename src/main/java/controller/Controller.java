package controller;

import simulation.handling.Simulation;
import simulation.handling.statistics.ChartDataHolder;

/**
 * Klasa pośrednicząca pomiędzy symulacją a interfejsem graficznym.
 */
public class Controller {

    private final Simulation sim;

    private int infected;

    private int dead;

    private int recovered;

    private String cyclesOutput;

    private ChartDataHolder singleCycleCharts;

    private ChartDataHolder finalCharts;


    /**
     * Konstruktor
     *
     * @param initialAgents   Liczba wszystkich agentow
     * @param initialInfected Poczatkowa liczba zarazonych agentow
     * @param contagiousness  smiertelnosci
     * @param mortality       Wspolczynnik zarazliwosci
     * @param diseaseLength   Standardowa długość choroby
     */
    public Controller(int initialAgents, int initialInfected, double contagiousness, double mortality, int diseaseLength) {
        sim = new Simulation(initialAgents, initialInfected, contagiousness, mortality, diseaseLength);
    }

    /**
     * Odbiera dane od klienta i na ich bazie uruchamia symulacje
     *
     * @return Controller
     */
    public Controller passValueToSimulation(){
        sim.run();
        collectResults();
        return this;
    }

    /**
     * Pobiera dane od symulacji
     */
    private void collectResults() {
        infected = sim.getResults()[0];
        dead = sim.getResults()[1];
        recovered = sim.getResults()[2];
        cyclesOutput = sim.getCyclesOutput();
        singleCycleCharts = sim.singleCycleCharts();
        finalCharts = sim.getFinalCharts();
    }

    /**
     * Getter łącznej liczby wyzdrowiałych.
     * @return int
     */
    public int getRecovered() {
        return recovered;
    }

    /**
     * Getter łącznej liczby martwych.
     * @return int
     */
    public int getDead() {
        return dead;
    }

    /**
     * Getter łącznej liczby zarażonych.
     * @return int
     */
    public int getInfected() {
        return infected;
    }

    /**
     * Getter tekstu zawierającego dane o wszystkich cyklach
     *
     * @return String
     */
    public String getCyclesOutput(){ return cyclesOutput; }

    /**
     * Zwraca obiekt przechowujący dane o nowych przypadkach w trakcie cyklu.
     * @return ChartDataHolder
     */
    public ChartDataHolder getSingleCycleCharts() { return singleCycleCharts; }

    /**
     * Zwraca obiekt przechowujący dane o łącznej liczbie przypadków w trakcie cyklu.
     * @return ChartDataHolder
     */
    public ChartDataHolder getFinalCharts() { return finalCharts; }
}

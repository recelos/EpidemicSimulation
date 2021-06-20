package Simulation.Handling;

import Simulation.Handling.Family.FamilySegregator;
import Simulation.Handling.Infected.InfectedHandler;
import Simulation.Handling.Interaction.InteractionHandler;
import Simulation.Handling.Interfaces.Handler;
import Simulation.Handling.Statistics.ChartDataHolder;
import Simulation.Handling.Statistics.Counters;

import static Simulation.Handling.Statistics.Counters.*;

/**
 * Klasa przeprowadzająca symulację.
 */
public class Simulation {

    private final int numberOfAgents;
    private final int initialNumberOfInfected;

    private static double contagiousness;
    /**
     * Getter śmiertelnosci zaraźliwosci
     *
     * @return double
     */
    public static double getContagiousness() {
        return contagiousness;
    }

    private static double mortality;
    /**
     * Getter współczynnika śmiertelnosci
     *
     * @return double
     */
    public static double getMortality(){
        return mortality;
    }

    private static int diseaseLength;

    /**
     * Getter standardowej długości choroby
     * @return int
     */
    public static int getDiseaseLength() {
        return diseaseLength;
    }

    private final AgentHandler agents = new AgentHandler();
    private final Handler interactions = new InteractionHandler();
    private final Handler infected = new InfectedHandler();
    private final FamilySegregator families = new FamilySegregator();

    private final ChartDataHolder singleCycleCharts = new ChartDataHolder();

    /**
     * Zwraca obiekt przechowujący dane o nowych przypadkach w trakcie cyklu.
     * @return ChartDataHolder
     */
    public ChartDataHolder singleCycleCharts(){
        return singleCycleCharts;
    }

    private final ChartDataHolder finalCharts = new ChartDataHolder();

    /**
     * Zwraca obiekt przechowujący dane o łącznych przypadkach w trakcie cyklu.
     * @return ChartDataHolder
     */
    public ChartDataHolder getFinalCharts(){
        return finalCharts;
    }

    /**
     * Bazowy konstruktor symulacji.
     * @param numberOfAgents  początkowa liczba agentów
     * @param initialNumberOfInfected  początkowa liczba zarażonych agentów
     * @param contagiousness współczynnik zaraźliwości
     * @param mortality współczynnik śmiertelnosci
     * @param diseaseLength standardowa długość choroby
     */
    public Simulation(int numberOfAgents, int initialNumberOfInfected, double contagiousness, double mortality, int diseaseLength){
        this.numberOfAgents = numberOfAgents;
        this.initialNumberOfInfected = initialNumberOfInfected;
        Simulation.mortality = calculateMortality(mortality, diseaseLength);
        Simulation.contagiousness = contagiousness;
        Simulation.diseaseLength = diseaseLength;
    }

    /**
     * Oblicza szanse na śmierć w trakcie pojedynczego cyklu.
     * @param probability szansa na śmierć w trakcie całego przebiegu choroby
     * @param cycles długość choroby w cyklach
     * @return double
     */
    private double calculateMortality(double probability, int cycles){
        return (1-Math.pow(1-probability, (double)1/cycles));
    }
    /**
     * Główma pętla symulacji. Działa dopóki kolekcje agentów i zarażonych nie są puste.
     */
    public void run(){

        Counters.reset();
        int cycleCounter = 1;

        agents.initializeSimulation(numberOfAgents, initialNumberOfInfected);
        families.organize(agents.getAgents());

        Counters.setOutputString("Dane początkowe \n" + agents.displayCycle() + "\n");

        do{
            Counters.resetCycleData();

            interactions
                    .organize(agents.getAgents())
                    .iterate();

            families
                    .iterate();

            infected
                    .organize(agents.getAgents())
                    .iterate();


            agents.removeDead();
            families.removeDead();

            setStatistics(cycleCounter);
            cycleCounter++;

        }while(!infected.isEmpty() && !agents.getAgents().isEmpty());
        results = setResults();
        cyclesOutput = getOutputString();
    }

    private int[] results;
    /**
     * Zwraca liczbowe rezultaty symulacji w postaci tablicy.
     *
     * @return int array
     */
    public int[] getResults() {
        return results;
    }

    private int[] setResults(){
        int[] output = new int[3];
        output[0] = Counters.getInfectedCounter();
        output[1] = Counters.getDeadCounter();
        output[2] = Counters.getRecoveredCounter();
        return output;
    }

    private String cyclesOutput;

    /**
     * Zwraca tekst zawierającu dane o wszystkich cyklach.
     *
     * @return String
     */
    public String getCyclesOutput() { return cyclesOutput; }

    /**
     * Zwraca tekst zawierający informacje o nowych przypadkach w trakcie cyklu.
     * @return String
     */
    private String currentCycleData(){
        return(
                "zarażeni w tym cyklu: " + Counters.getInfectedInThisCycle() + "\n" +
                "zmarli w tym cyklu: " + Counters.getDeadInThisCycle() + "\n" +
                "uzdrowieni w tym cyklu: " + Counters.getRecoveredInThisCycle() +
                "\n\n"
        );
    }

    /**
     * Ustawia statystyki po każdym z cykli.
     * @param cycleCounter licznik cykli
     */
    private void setStatistics(int cycleCounter) {
        Counters.setOutputString(getOutputString() + "Cykl " + cycleCounter + "\n" + agents.displayCycle() + "\n" + currentCycleData());
        singleCycleCharts
                .addData(Counters.getInfectedInThisCycle(), Counters.getDeadInThisCycle(), Counters.getRecoveredInThisCycle());
        finalCharts
                .addData(Counters.getInfectedCounter(), Counters.getDeadCounter(), Counters.getRecoveredCounter());
    }

}

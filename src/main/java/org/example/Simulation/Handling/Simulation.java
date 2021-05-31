package org.example.Simulation.Handling;

import org.example.Simulation.Handling.Interaction.InteractionHandler;

public class Simulation {
    private final int numberOfAgents;
    private final int initialNumberOfInfected;


    private static double Contagiousness;

    /**
     * Getter wspolczynnika zarazliwosci
     * @return double
     */
    public static double  getContagiousness() {
        return Contagiousness;
    }

    private static double Mortality;

    /**
     * Getter wspolczynnika smiertelnosci
     * @return double
     */
    public static double getMortality(){
        return Mortality;
    }



    private final AgentHandler agents = new AgentHandler();
    private final InteractionHandler interactions = new InteractionHandler();
    private final InfectedHandler infected = new InfectedHandler();


    /**
     * Konstruktor
     * @param numberOfAgents poczatkowa liczba agentow
     * @param initialNumberOfInfected poczatkowa liczba zarazonych agentow
     * @param contagiousness wspolczynnik zarazliwosci
     * @param mortality wspolczynnik smiertelnosci
     */
    public Simulation(int numberOfAgents, int initialNumberOfInfected, double contagiousness, double mortality){
        this.numberOfAgents = numberOfAgents;
        this.initialNumberOfInfected = initialNumberOfInfected;
        Mortality = mortality;
        Contagiousness = contagiousness;
    }

    /**
     * Uruchomienie symulacji. Dziala dopoki kolekcje agentow i zarazonych nie sa puste.
     */
    public void Run(){

        Statistics.reset();
        int cycleCounter = 0;

        agents.initializeSimulation(numberOfAgents, initialNumberOfInfected); // inicjalizacja symulacji
        do{
            Statistics.setOutputString(Statistics.getOutputString() + "Cykl " + cycleCounter + "\n" + agents.displayCycle() + "\n");

            interactions
                    .arrangePairs(agents.getAgents())   // zebranie agentów w pary
                    .runInteractions();                 // przeprowadzenie interakcji
            infected
                    .gatherInfected(agents.getAgents()) // zebrabue zarażonych agentów
                    .simulateCoursesOfIllness();         // przeprowadzenie przebiegu chorób

            agents.removeDead();

            cycleCounter++;

        }while(infected.checkForInfected() && !agents.getAgents().isEmpty());
    }
}

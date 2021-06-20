package Simulation.Handling;

import Simulation.Agent.Abstractions.Agent;

import Simulation.Agent.Subclasses.Adult;
import Simulation.Agent.Subclasses.Child;
import Simulation.Agent.Subclasses.Elder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static Simulation.Agent.Enums.HealthStatus.*;


/**
 * Klasa przechowująca i obsługująca wszystkich agentów znajdujących się w symulacji.
 */
public class AgentHandler {

    /**
     * Lista przechowująca wszystkich agentów.
     */
    private List<Agent> agents;

    /**
     * Getter zbioru agentow
     *
     * @return Agent List
     */
    public List<Agent> getAgents() { return agents; }

    /**
     * Inicjalizacja agentów i pierwszych zarażonych.
     *
     * @param nOfAgents   początkowa liczba agentów
     * @param nOfInfected liczba agentów do zarażenia podczas inicjalizacji
     */
    public void initializeSimulation(int nOfAgents, int nOfInfected){
        this.agents = generateAgents(nOfAgents);
        infectFirstAgents(nOfInfected);
    }


    /**
     * Generuje agentów na początku symulacji.
     * @param input początkowa liczba agentów
     * @return List Agent
     */
    private List<Agent> generateAgents(int input){
        List<Agent> output = new ArrayList<>(input);

        int nOfAdults = (int) Math.floor((0.6 * input));
        int nOfChildren = (int) Math.floor(nOfAdults*0.5);
        int nOfElders = input - nOfAdults - nOfChildren;

        for(int i = 0; i < nOfAdults; i++) output.add(new Adult());
        for(int i = 0; i < nOfChildren; i++) output.add(new Child());
        for(int i = 0; i < nOfElders; i++) output.add(new Elder());
        return output;
    }


    /**
     * Losuje i zaraża agentów na początku symulacji.
     * @param input liczba agentów do zainfekowania
     */
    private void infectFirstAgents(int input){
        List<Agent> a = new ArrayList<>(agents);
        Collections.shuffle(a);

        for(int i=0; i<input; ++i)
            a
                    .get(i)
                    .catchDisease();
    }

    /**
     * Usunięcie martwych agenów po każdym cyklu.
     */
    public void removeDead(){
        agents.removeIf(a -> a.getStatus() == DEAD);
    }

    /**
     * Zlicza łączną liczbę zarażonych w trakcie cyklu.
     * @return String
     */
    private String displayNoOfInfected(){
        int x = (int) agents
                .stream()
                .filter(a -> a.getStatus() == INFECTED)
                .count();
        return ("zarażeni: " + x + "\n");
    }

    /**
     *Zlicza łączną liczbę żywych agentów w trakcie cyklu.
     * @return String
     */
    private String displayNoOfAllAgents(){
        return ("wszyscy: " + agents.size() + "\n");
    }

    /**
     * Zlicza łączną liczbę zdrowych w trakcie cyklu.
     * @return String
     */
    private String displayNoOfHealthy(){
        int x = (int) agents.
                stream()
                .filter(a -> a.getStatus() == HEALTHY)
                .count();
        return ("zdrowi: " + x + "\n");
    }

    /**
     * Zwraca statystyki każdego z poszczególnych cyklów
     * postaci tekstu.
     *
     * @return String
     */
    public String displayCycle() {
        return displayNoOfAllAgents() + displayNoOfHealthy() + displayNoOfInfected();
    }
}

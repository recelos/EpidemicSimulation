package org.example.Simulation.Handling;

import org.example.Simulation.Agent.Abstractions.Agent;
import org.example.Simulation.Agent.Enums.AgentStatus;
import org.example.Simulation.Agent.Interfaces.IAgent;
import org.example.Simulation.Agent.Subclasses.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class AgentHandler {

    private List<IAgent> agents;

    /**
     * Getter zbioru agentow
     * @return List{IAgent}
     */
    public List<IAgent> getAgents() { return agents; }

    /**
     * Inicjalizacja agentow i pierwszych zarazonych
     * @param nOfAgents poczatkowa liczba agentow
     * @param nOfInfected liczba agentow do zarazenia podczas inicjalizacji
     */
    public void initializeSimulation(int nOfAgents, int nOfInfected){
        this.agents = generateAgents(nOfAgents);
        infectFirstAgents(nOfInfected);
    }


    /**
     * @param input poczatkowa liczba agentow
     * @return List{IAgent}
     */
    private List<IAgent> generateAgents(int input){
        List<IAgent> output = new ArrayList<>(input);

        int nOfAdults = (int) Math.floor((0.6 * input));
        int nOfChildren = (int) Math.floor(nOfAdults*0.5);
        int nOfElders = input - nOfAdults - nOfChildren;

        for(int i = 0; i < nOfAdults; i++) output.add(new Adult());
        for(int i = 0; i < nOfChildren; i++) output.add(new Child());
        for(int i = 0; i < nOfElders; i++) output.add(new Elder());
        return output;
    }


    /**
     * Losuje i zaraza agentow na poczatku symulacji
     * @param input Liczba agentow do zainfekowania
     */
    private void infectFirstAgents(int input){
        List<IAgent> a = new ArrayList<>(agents);
        Collections.shuffle(a);
        for(int i=0; i<input; i++)
            a.get(i).CatchDisease();
    }

    /**
     * Usuniecie martwych agentow po kazdym cyklu
     */
    public void removeDead(){
        agents.removeIf(a -> a.getStatus() == AgentStatus.Dead);
    }

    /**
     * Zlicza laczna liczbe zarazonych w trakcie cykli
     * @return String
     */
    private String displayNoOfInfected(){
        int x = (int) agents.stream().filter(a -> a.getStatus() == AgentStatus.Infected).count();
        return ("zarażeni: " + x + "\n");
    }

    /**
     * Zlicza laczna liczbe agentow przy zyciu w trakcie cyklu
     * @return String
     */
    private String displayNoOfAllAgents(){
        return ("wszyscy: " + agents.size() + "\n");
    }

    /**
     * Zlicza wszystkich zdrowych agentow w trakcie cyklu
     * @return String
     */
    private String displayNoOfHealthy(){
        int x = (int) agents.stream().filter(a -> a.getStatus() == AgentStatus.Healthy).count();
        return ("zdrowi: " + x + "\n");
    }

    /**
     * Zwraca statystyki kazdego z poszczegolnych cyklow
     * postaci tekstu
     * @return String
     */
    public String displayCycle() {
        return displayNoOfAllAgents() + displayNoOfHealthy() + displayNoOfInfected();
    }
}

package Simulation.Handling.Infected;

import Simulation.Agent.Abstractions.Agent;
import Simulation.Agent.Interfaces.DiseaseSusceptible;
import Simulation.Handling.Interfaces.Handler;

import java.util.ArrayList;
import java.util.List;

import static Simulation.Agent.Enums.HealthStatus.*;

/**
 * Klasa obsługująca przebiegi chorób zarażonych agentów
 */
public class InfectedHandler implements Handler {

    /**
     * Lista przechowująca agentów chorych w trakcie cyklu.
     */
    private final List<DiseaseSusceptible> infected = new ArrayList<>();

    /**
     * Zbiera zarażonych agentów.
     *
     * @param agents główna kolekcja agentów.
     * @return Handler
     */
    public Handler organize(List<Agent> agents){

        infected.clear();

        agents
                .stream()
                .filter(a -> a.getStatus() == INFECTED)
                .forEach(infected::add);

        return this;
    }

    /**
     * Symuluje przebiegi chorób u zarażonych.
     */
    public void iterate(){
        for (DiseaseSusceptible ill : infected) {
            ill.courseOfIllness();
        }
    }

    /**
     * Sprawdza, czy zbiór zarażonych jest pusty.
     *
     * @return boolean
     */
    public boolean isEmpty(){ return infected.isEmpty(); }
}

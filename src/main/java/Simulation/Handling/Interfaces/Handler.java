package Simulation.Handling.Interfaces;

import Simulation.Agent.Abstractions.Agent;

import java.util.List;

/**
 * Definiuje zachowanie klasy obsługującej określoną część symulacji.
 */
public interface Handler{
    /**
     * Organizuje agentów.
     * @param agents organizowani agenci
     * @return Handler
     */
    Handler organize(List<Agent> agents);
    /**
     * Iteruje po własnej kolekcji.
     */
    void iterate();
    /**
     * Sprawdza, czy wewnętrzny zbiór jest pusty.
     *
     * @return boolean
     */
    boolean isEmpty();
}

package Simulation.Handling.Interaction;

import Simulation.Agent.Abstractions.Agent;

import Simulation.Agent.Enums.HealthStatus;
import Simulation.Agent.Interfaces.Interactive;
import Simulation.Handling.Interfaces.Handler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Klasa obsługująca interakcje w całej symulacji.
 */
public class InteractionHandler implements Handler {

    /**
     * Kolekcja przechowująca pary w trakcie cyklu.
     */
    private final List<Pair> pairs = new ArrayList<>();

    /**
     * Funkcja organizująca agentów w pary.
     *
     * @param agents kolekcja agentow
     * @return InteractionHandler
     */
    public Handler organize(List<Agent> agents){

        pairs.clear();
        
        int nOfPairs = countNumberOfPairs(agents);

        List<Interactive> firstHalf = new ArrayList<>();
        List<Interactive> secondHalf = new ArrayList<>();

        divideInHalf(agents, firstHalf, secondHalf, nOfPairs);

        for(int i=0; i<nOfPairs;++i)
            pairs.add(new Pair(firstHalf.get(i), secondHalf.get(i)));
        return this;
    }
    /** Wylicza ilość par do utworzenia. W przypadku nieparzystej liczby agentów ostatni zostanie pominięty w trakcie interakcji
     * @param agents kolekcja agentów
     * @return int
     */
    private int countNumberOfPairs(List<Agent> agents) {
        return (agents.size() % 2 == 0) ? (agents.size() / 2) : ((agents.size() - 1) / 2);
    }
    /**
     * Dzieli początkową listę na dwie losowe połówki.
     * @param origin Dzielona lista
     * @param firstHalf Pierwsza polowa listy
     * @param secondHalf Druga polowa listy
     * @param nOfPairs Liczba par do utworzenia
     */
    private void divideInHalf(List<Agent> origin, List<Interactive> firstHalf,
                              List<Interactive> secondHalf, int nOfPairs){

        List<Interactive> temporary = new ArrayList<>(origin);
        Collections.shuffle(temporary);

        for (int i = 0; i < nOfPairs; ++i) {
            firstHalf.add(temporary.get(i));
            secondHalf.add(temporary.get(i+nOfPairs));
        }
    }
    /**
     * Przeprowadza interakcje na wszystkich zebranych parach.
     */
    @Override
    public void iterate() {
        pairs.forEach(p -> p
                .getFirst()
                .interact(p.getSecond()));
    }
    @Override
    public boolean isEmpty() {
        return pairs.isEmpty();
    }
}

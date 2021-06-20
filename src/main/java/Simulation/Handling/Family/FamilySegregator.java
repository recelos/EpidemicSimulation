package Simulation.Handling.Family;

import Simulation.Agent.Abstractions.Agent;
import Simulation.Agent.Interfaces.Interactive;
import Simulation.Agent.Subclasses.*;
import Simulation.Handling.Interfaces.Handler;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Klasa segregującą i obsługująca model rodzin w symulacji.
 */
public class FamilySegregator implements Handler {
    private final List<Family> families = new ArrayList<>();

    /**
     * Segreguje agentów w rodziny na początku symulacji. Rodzina składa się z dwóch
     * dorosłych, jednego dziecka i co trzecia rodzina jednego starszego.
     * @param agents agenci segregowani
     */
    public Handler organize(List<Agent> agents){

        if (agents.size()<=7) return this;


        List<Adult> adults;
        List<Child> children;
        List<Elder> elders;

        adults = agents
                .stream()
                .filter(a -> a instanceof Adult)
                .map(a -> (Adult) a)
                .collect(Collectors.toList());

        children = agents
                .stream()
                .filter(a -> a instanceof Child)
                .map(a -> (Child) a)
                .collect(Collectors.toList());

        elders = agents
                .stream()
                .filter(a -> a instanceof Elder)
                .map(a -> (Elder) a)
                .collect(Collectors.toList());

        addAdults(adults);

        addChildren(children);

        addElders(elders);

        return this;
    }


    /**
     * Dodaje pary dorosłych do rodzin.
     * @param adults dodawani dorośli
     */
    private void addAdults(List<Adult> adults) {
        if (adults.size()==0) return;
        int iterations = adults.size()%2==0 ? adults.size()+1 : adults.size();
        for (int i = 1; i < iterations; i+=2) {
            List<Interactive> temporary = new ArrayList<>();
            temporary.add(adults.get(i-1));
            temporary.add(adults.get(i));
            families.add(new Family(temporary));
        }
    }

    /**
     * Dodaje dzieci do rodzin.
     * @param children dodawane dzieci
     */
    private void addChildren(List<Child> children) {
        if(children.size()==0) return;
        IntStream.range(0, children.size())
                .forEach(i -> families.get(i)
                        .add(children.get(i)));
    }

    /**
     * Dodaje starszych do rodzin.
     * @param elders dodawani starsi
     */
    private void addElders(List<Elder> elders) {
        if(elders.size()==0) return;
        IntStream.range(0, elders.size())
                .forEach(i -> families.get(i)
                        .add(elders.get(i)));
    }

    /**
     * Iteruje przez wszystkie rodziny. Jeśli w rodzinie znajduje się zarażony członek, ma 5 procent
     * szans na zarażenie pozostałych członków.
     */
    public void iterate(){
        families.forEach(Family::infectMembers);
    }

    /**
     * Usuwa martwych z rodzin pod koniec cyklu.
     */
    public void removeDead(){
        families.forEach(Family::removeDead);
    }


    /**
     * Zwraca wszystkie rodziny.
     *
     * @return Family List
     */
    public List<Family> getFamilies() {
        return families;
    }

    @Override
    public boolean isEmpty() {
        return families.isEmpty();
    }
}

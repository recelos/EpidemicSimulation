package org.example.Simulation.Handling.Interaction;

import org.example.Simulation.Agent.Interfaces.IAgent;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class InteractionHandler {

    private List<Pair> pairs = new ArrayList<>();

    /**
     * Funkcja aranzujaca agentow w pary
     * @param agents kolekcja agentow
     * @return InteractionHandler
     */
    public InteractionHandler arrangePairs(List<IAgent> agents){

        pairs.clear(); // usunięcie par z poprzedniego cyklu

        List<IAgent> temp = new ArrayList<>(agents); // utworzenie tymczasowej listy agentów i
        Collections.shuffle(temp);                   // przetasowanie jej umożliwia losowy dobór agentów do interakcji
                                                     // niezmieniając kolejności głównej listy przechowującej wszystkich
                                                     // agentów
        int nOfInteractions;

        if(agents.size()%2==0) nOfInteractions = agents.size(); // w przypadku nieparzystej liczby agentów
        else nOfInteractions = agents.size()-1;                 // ostatni agent z tymczasowej listy zostanie
                                                                // pominięty podczas przeprowadzania interakcji

        List<IAgent> firstHalf = new ArrayList<>(); //
        List<IAgent> secondHalf = new ArrayList<>();//

        for(int i=0; i<nOfInteractions/2;i++) {
            firstHalf.add(temp.get(i));                     // podzielenie tymczasowej listy
            secondHalf.add(temp.get(i+nOfInteractions/2));  // na dwie części
        }

        for(int i=0; i<nOfInteractions/2;i++) pairs.add(new Pair(firstHalf.get(i),secondHalf.get(i)));// utworzenie par

        return this;
    }

    /**
     * Przeprowadza interakcje na wszystkich parach
     * @return InteractionHandler
     */
    public InteractionHandler runInteractions() {
        for(Pair p:
                pairs) p.interaction();

        return this;
    }

}

package org.example.Simulation.Handling;

import org.example.Simulation.Agent.Enums.AgentStatus;
import org.example.Simulation.Agent.Interfaces.IAgent;

import java.util.ArrayList;
import java.util.List;

public class InfectedHandler {

    private List<IAgent> infected = new ArrayList<>();

    /**
     * Zbiera zarazonych agentow
     * @param agents kolekcja agentow
     * @return InfectedHandler
     */
    public InfectedHandler gatherInfected(List<IAgent> agents){

        infected.clear();

        for(IAgent a:
                agents){
            if(a.getStatus() == AgentStatus.Infected) infected.add(a);
        }
        return this;
    }

    /**
     * Symuluje przebiegi chorob
     * @return InfectedHandler
     */
    public InfectedHandler simulateCoursesOfIllness(){
        for(IAgent a
                :infected) a.courseOfIllness();

        return this;
    }

    /**
     * Sprawdza, czy zbior zarazonych jest pusty
     * @return boolean
     */
    public boolean checkForInfected(){
        return !infected.isEmpty();
    }
}

package org.example.Simulation.Handling.Family;

import java.util.ArrayList;
import java.util.List;

import org.example.Simulation.Agent.Abstractions.Agent;
import org.example.Simulation.Agent.Interfaces.IAgent;

import static org.example.Simulation.Agent.Enums.AgentStatus.Infected;
import static org.example.Simulation.Agent.Enums.AgentStatus.values;

public class Family {
    private List<IAgent> members = new ArrayList<>();
    public List<IAgent> getMembers(){ return members; }


    /**
     * Konstruktor
     * @param members agenci dodawani do rodziny
     */
    Family(List<IAgent> members) { this.members = members; }

    /**
     * Zaraza agentow w rodzinie
     */
    void infectMembers(){
        if(checkForInfected())
            members.forEach(IAgent::CatchDisease);      // zarażenie pozostałych członków rodziny
    }

    /**
     * Sprawdza, czy w rodzinie jest przynajmniej jeden zarazony
     * @return boolean
     */
    private boolean checkForInfected(){
        return members.stream().anyMatch(a -> a.getStatus()==Infected); // sprawdzenie czy w rodzinie znajduje sie zarażony członek
    }

}

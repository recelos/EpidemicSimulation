package org.example.Simulation.Handling.Interaction;

import org.example.Simulation.Agent.Abstractions.Agent;
import org.example.Simulation.Agent.Interfaces.IAgent;
import org.example.Simulation.Handling.Simulation;

import static org.example.Simulation.Agent.Enums.AgentStatus.*;

public class Pair {

    private final IAgent a1;
    private final IAgent a2;

    /**
     * Konstruktor
     * @param a1 pierwszy agent z pary
     * @param a2 drugi agent z pary
     */
    Pair(IAgent a1, IAgent a2) {
        this.a1 = a1;
        this.a2 = a2;
    }

    /**
     * Interakcja pomiedzy agentami. W wyniku interakcji jesli jeden z agentow jest zarazony,
     * to jesli drugi jest zdrowy to moze sie zarazic.
     */
     void interaction(){
        if(a1.getStatus() == Infected && a2.getStatus() == Healthy){
            double odds = Math.random();
            if(odds <= Simulation.getContagiousness()) a2.CatchDisease();
        }
        else if(a1.getStatus() == Healthy && a2.getStatus() == Infected){
            double odds = Math.random();
            if(odds <= Simulation.getContagiousness()) a1.CatchDisease();
        }
    }
}

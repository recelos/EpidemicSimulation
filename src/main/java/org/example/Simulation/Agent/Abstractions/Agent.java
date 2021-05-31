package org.example.Simulation.Agent.Abstractions;

import org.example.Simulation.Agent.Enums.AgentStatus;
import org.example.Simulation.Agent.Interfaces.IAgent;
import org.example.Simulation.Handling.Statistics;

import static org.example.Simulation.Agent.Enums.AgentStatus.Healthy;

/**
 * Klasa abstrakcyjna agentów
 */
public abstract class Agent implements IAgent {

    protected AgentStatus status;
    /**
     * Getter statusu zdrowia agenta
     * @return AgentStatus
     */
    @Override
    public AgentStatus getStatus() { return status; }

    protected boolean isImmune;

    protected int timeToRecover;

    public abstract void courseOfIllness();

    /**
     * Konstruktor
     */
    public Agent(){
        this.status = Healthy;
        this.timeToRecover = 0;
        this.isImmune = false;
    }

    /**
     * Uzdrawia agenta i uodparnia go od ponownego zarażenia
     */
    @Override
    public void Recover() {
        this.status = AgentStatus.Healthy;
        this.isImmune = true;
        Statistics.setRecoveredCounter(Statistics.getRecoveredCounter()+1);
    }

    /**
     * Zmienia status na agenta na martwy
     */
    @Override
    public void Decease() {
        this.status = AgentStatus.Dead;
        Statistics.setDeadCounter(Statistics.getDeadCounter()+1);
    }

}

package Simulation.Agent.Abstractions;

import Simulation.Agent.Interfaces.*;
import Simulation.Handling.Simulation;
import Simulation.Handling.Statistics.Counters;
import Simulation.Agent.Enums.HealthStatus;

import static Simulation.Agent.Enums.HealthStatus.*;
import static Simulation.Handling.Statistics.Counters.*;

/**
 * Klasa abstrakcyjna agentów.
 */
public abstract class Agent implements DiseaseSusceptible, Interactive  {
    /**
     * Status zdrowotny agenta.
     */
    protected HealthStatus status;
    /**
     * Informacja o odporności agenta.
     */
    protected boolean isImmune;
    /**
     * Czas agenta do uzdrowienia
     */
    protected int timeToRecover;

    public abstract void courseOfIllness();

    public abstract void catchDisease();

    /**
     * Konstruktor bazowy agenta.
     */
    public Agent(){
        this.status = HEALTHY;
        this.timeToRecover = 0;
        this.isImmune = false;
    }
    /**
     * Interakcja pomiedzy agentami. W jej wyniku zarażony agent może zarazić
     * zdrowego agenta.
     */
    @Override
    public void interact(Interactive input) {
        if(this.getStatus() == INFECTED && input.getStatus() == HEALTHY){
            double odds = Math.random();
            if(odds <= Simulation.getContagiousness()) input.catchDisease();
        }
        else if(this.getStatus() == HEALTHY && input.getStatus() == INFECTED){
            double odds = Math.random();
            if(odds <= Simulation.getContagiousness()) this.catchDisease();
        }
    }
    /**
     * Getter statusu zdrowia agenta.
     * @return HealthStatus
     */
    @Override
    public HealthStatus getStatus() { return status; }
    /**
     * Przywraca stan agenta na zdrowy i uodparnia go od ponownego zarażenia.
     */
    @Override
    public void recover() {
        this.status = HEALTHY;
        this.isImmune = true;
        setRecoveredCounter(Counters.getRecoveredCounter()+1);
        setRecoveredInThisCycle(Counters.getRecoveredInThisCycle()+1);
    }
    /**
     * Zmienia status na agenta na martwy.
     */
    @Override
    public void decease() {
        this.status = DEAD;
        setDeadCounter(Counters.getDeadCounter()+1);
        setDeadInThisCycle(Counters.getDeadInThisCycle()+1);
    }

}

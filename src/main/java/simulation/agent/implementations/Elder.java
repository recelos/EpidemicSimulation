package simulation.agent.implementations;

import simulation.agent.abstractions.Agent;
import simulation.handling.Simulation;

import static simulation.handling.statistics.Counters.*;
import static simulation.agent.abstractions.HealthStatus.*;
import static java.lang.Math.random;
/**
 * Rodzaj agenta typu Elder.
 */
public class Elder extends Agent{
    /**
     * Konstruktor
     */
    public Elder(){
        super();
        this.deathChanceModifier=1.25;
        this.diseaseLengthModifier=1;
    }
}

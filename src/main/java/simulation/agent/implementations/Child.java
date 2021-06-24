package simulation.agent.implementations;

import simulation.agent.abstractions.Agent;
/**
 * Rodzaj agenta typu Child.
 */
public class Child extends Agent {
    /**
     * Konstruktor
     */
    public Child(){
        super();
        this.deathChanceModifier=0.75;
        this.diseaseLengthModifier=-1;
    }
}

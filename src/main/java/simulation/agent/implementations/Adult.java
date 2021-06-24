package simulation.agent.implementations;

import simulation.agent.abstractions.Agent;
/**
 * Rodzaj agenta typu Adult.
 */
public class Adult extends Agent{
    /**
     * Konstruktor
     */
    public Adult(){
        super();
        this.deathChanceModifier=1;
        this.diseaseLengthModifier=0;
    }
}

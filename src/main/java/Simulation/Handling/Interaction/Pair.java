package Simulation.Handling.Interaction;


import Simulation.Agent.Interfaces.Interactive;

/**
 * Klasa przechowująca agentów będących parą.
 */
public class Pair {

    /**
     * Pierwszy agent z pary.
     */
    private final Interactive first;

    /**
     * Drugi agent z pary.
     */
    private final Interactive second;

    /**
     * Getter pierwszego agenta.
     *
     * @return Interactive
     */
    Interactive getFirst(){
        return first;
    }

    /**
     * Getter drugiego agenta.
     *
     * @return Interactive
     */
    Interactive getSecond(){
        return second;
    }

    /**
     * Konstruktor
     *
     * @param first  pierwszy agent
     * @param second drugi agent
     */
    public Pair(Interactive first, Interactive second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public boolean equals(Object o){
        if(!(o instanceof Pair))
            return false;

        Pair p = (Pair) o;

        return (this.first == p.first
                && this.second == p.second);
    }


}

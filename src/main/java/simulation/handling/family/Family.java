package simulation.handling.family;

import java.util.List;

import simulation.agent.abstractions.Interactive;

import static simulation.agent.abstractions.HealthStatus.*;

/**
 * Klasa przechowująca agentów w rodzinie.
 */
public class Family {

    /**
     * Przechowuje członków rodziny.
     */
    private final List<Interactive> members;

//    /**
//     * Zwraca listę przechowującą członków rodziny.
//     *
//     * @return Interactive List
//     */
//    public List<Interactive> getMembers(){ return members; }

    /**
     * Konstruktor.
     *
     * @param members agenci dodawani do rodziny
     */
    Family(List<Interactive> members) { this.members = members; }

    /**
     * Zaraża agentów w rodzinie.
     */
    void infectMembers(){
        if(checkForInfected()){
            double odds = Math.random();
            double chanceToContain = 0.05;
            if(odds < chanceToContain)
                members.forEach(Interactive::catchDisease);
        }
    }
    /**
     * Dodaje agenta do rodziny.
     *
     * @param agent dodawany agent
     */
    void add(Interactive agent){
        members.add(agent);
    }

    /**
     * Sprawdza, czy w rodzinie jest przynajmniej jeden zarażony.
     * @return boolean
     */
    private boolean checkForInfected(){
        return members
                .stream()
                .anyMatch(a -> a.getStatus()==INFECTED);
    }

    /**
     * Usuwa martwych członków z rodziny.
     */
    public void removeDead() {
        members.removeIf(a -> a.getStatus() == DEAD);
    }
}

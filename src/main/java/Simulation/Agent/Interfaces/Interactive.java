package Simulation.Agent.Interfaces;

import Simulation.Agent.Enums.HealthStatus;

/**
 * Definiuje zachowanie obiektu mogącego dokonywać interakcji.
 */
public interface Interactive {
    /**
     * Interakcja pomiędzy dwoma agentami.
     *
     * @param agent obiekt z którym dokonywana jest interakcja
     */
    void interact(Interactive agent);
    /**
     * Złapanie choroby przez agenta.
     */
    void catchDisease();
    /**
     * Zwraca status zdrowotny agenta.
     *
     * @return HealthStatus
     */
    HealthStatus getStatus();
}

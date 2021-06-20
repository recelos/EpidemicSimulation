package Simulation.Agent.Interfaces;

/**
 * Interfejs definiujący zachowanie obiektu podatnego na działanie choroby
 */
public interface DiseaseSusceptible {
    /**
     * Uzdrawia agenta.
     */
    void recover();
    /**
     * Zabija agenta.
     */
    void decease();
    /**
     * Definiuje przebieg choroby.
     */
    void courseOfIllness();
}

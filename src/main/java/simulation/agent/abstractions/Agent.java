package simulation.agent.abstractions;

import simulation.handling.Simulation;
import simulation.handling.statistics.Counters;

import static java.lang.Math.random;
import static simulation.agent.abstractions.HealthStatus.*;
import static simulation.handling.statistics.Counters.*;

/**
 * Klasa abstrakcyjna agentów.
 */
public abstract class Agent implements IAgent {
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

    protected int diseaseLengthModifier;

    protected double deathChanceModifier;

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
     * Zaraża agenta jeśli jest zdrowy i nie ma odporności. Ustawia czas do uzdrowienia.
     */
    @Override
    public void catchDisease(){
        if(!this.isImmune && this.status == HEALTHY){
            this.status = INFECTED;
            timeToRecover = Simulation.getDiseaseLength()+diseaseLengthModifier;
            setInfectedCounter(getInfectedCounter()+1);
            setInfectedInThisCycle(getInfectedInThisCycle()+1);
        }
    }

    /**
     * Symulacja przebiegu choroby. Współczynnik śmiertelnosci determinuje szansę
     * na przeżycie w trakcie jednego cyklu. Jeśli minie odpowiednia liczba cykli,
     * agent zostanie uzdrowiony.
     * */
    @Override
    public void courseOfIllness() {
        // Generuje losową liczbę z zakresu od 0 do 1
        double odds;
        odds = random();

        if(odds*deathChanceModifier <= Simulation.getMortality())
            this.decease();
        else
            --this.timeToRecover;

        if(this.timeToRecover==0) this.recover();
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

package Simulation.Agent.Subclasses;

import Simulation.Agent.Abstractions.Agent;
import Simulation.Handling.Simulation;

import static Simulation.Handling.Statistics.Counters.*;
import static Simulation.Agent.Enums.HealthStatus.*;
import static java.lang.Math.random;

/**
 * Rodzaj agenta typu Elder.
 */
public class Elder extends Agent {

    /**
     * Konstruktor
     */
    public Elder(){
        super();
    }

    /**
     * Symulacja przebiegu choroby. Współczynnik smiertelnosci determinuje szanse
     * na przeżycie w trakcie jednego cyklu. Jeśli minie odpowiednia liczba cykli,
     * agent zostanie uzdrowiony. Agent typu Elder ma mniejsze szanse na preżycie.
     */
    @Override
    public void courseOfIllness() {

        // Generuje losową liczbę z zakresu od 0 do 1
        double odds;
        odds = random();

        if(odds*1.25<= Simulation.getMortality())
            this.decease();
        else
            --this.timeToRecover;

        if(this.timeToRecover==0)
            this.recover();
    }

    /**
     * Zaraża agenta jeśli jest zdrowy i nie ma odporności. Ustawia czas do uzdrowienia.
     * W przypadku Agenta typu Elder czas do uzdrowienia jest dłuższy o jeden cykl.
     */
    @Override
    public void catchDisease(){
        if(!this.isImmune && this.status == HEALTHY){
            this.status = INFECTED;
            this.timeToRecover = Simulation.getDiseaseLength()+1;
            setInfectedCounter(getInfectedCounter()+1);
            setInfectedInThisCycle(getInfectedInThisCycle()+1);
        }
    }
}

package Simulation.Agent.Subclasses;

import Simulation.Agent.Abstractions.Agent;
import Simulation.Handling.Simulation;

import static Simulation.Agent.Enums.HealthStatus.*;
import static java.lang.Math.random;
import static Simulation.Handling.Statistics.Counters.*;

/**
 * Rodzaj agenta typu Child.
 */
public class Child extends Agent {

    /**
     * Konstruktor
     */
    public Child(){
        super();
    }

    /**
     * Symulacja przebiegu choroby. Współczynnik śmiertelnosci determinuje szansę
     * na przeżycie w trakcie jednego cyklu. Jeśli minie odpowiednia liczba cykli,
     * agent zostanie uzdrowiony. Agent klasy Child ma większe szanse na przeżycie.
     */
    @Override
    public void courseOfIllness() {
        // Generuje losową liczbę z zakresu od 0 do 1
        double odds;
        odds = random();


        if(odds*0.75 <= Simulation.getMortality())
            this.decease();
        else
            --this.timeToRecover;

        if(this.timeToRecover==0)
            this.recover();
    }


    /**
     * Zaraża agenta jeśli jest zdrowy i nie ma odporności. Ustawia czas do uzdrowienia.
     * W przypadku Agenta typu Child czas do uzdrowienia jest krótszy o jedne cykl.
     */
    @Override
    public void catchDisease(){
        if(!this.isImmune && this.status == HEALTHY){
            this.status = INFECTED;
            this.timeToRecover = Simulation.getDiseaseLength()-1;
            setInfectedCounter(getInfectedCounter()+1);
            setInfectedInThisCycle(getInfectedInThisCycle()+1);
        }
    }
}

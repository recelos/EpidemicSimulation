package Simulation.Agent.Subclasses;

import Simulation.Handling.Simulation;
import Simulation.Agent.Abstractions.Agent;

import static Simulation.Agent.Enums.HealthStatus.*;
import static java.lang.Math.random;
import static Simulation.Handling.Statistics.Counters.*;

/**
 * Rodzaj agenta typu Adult.
 */
public class Adult extends Agent{

    /**
     * Konstruktor
     */
    public Adult(){
        super();
    }

    /**
     * Symulacja przebiegu choroby. Współczynnik śmiertelnośći determinuje szansę
     * na przeżycie w trakcie jednego cyklu. Jeśli minie odpowiednia liczba cykli,
     * agent zostanie uzdrowiony. Obiekt klasy Adult ma standardową szansę na przeżycie.
     */
    @Override
    public void courseOfIllness() {

        // Generuje losową liczbę z zakresu od 0 do 1
        double odds = random();

        if(odds <= Simulation.getMortality())
            this.decease();
        else
            --this.timeToRecover;

        if(this.timeToRecover==0)
            this.recover();
    }

    /**
     * Zaraża agenta jeśli jest zdrowy i nie ma odporności. Ustawia czas do uzdrowienia.
     * W przypadku agenta klasy Adult czas do uzdrowienia wynosi standardową liczbę cykli.
     */
    @Override
    public void catchDisease(){
        if(!this.isImmune && this.status == HEALTHY){
            this.status = INFECTED;
            timeToRecover = Simulation.getDiseaseLength();
            setInfectedCounter(getInfectedCounter()+1);
            setInfectedInThisCycle(getInfectedInThisCycle()+1);
        }
    }

}

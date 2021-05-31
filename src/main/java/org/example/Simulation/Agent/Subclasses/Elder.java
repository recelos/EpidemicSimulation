package org.example.Simulation.Agent.Subclasses;

import org.example.Simulation.Agent.Abstractions.Agent;
import org.example.Simulation.Handling.Simulation;
import org.example.Simulation.Handling.Statistics;

import static org.example.Simulation.Agent.Enums.AgentStatus.*;
import static java.lang.Math.random;

public class Elder extends Agent {

    /**
     * Konstruktor
     */
    public Elder(){
        super();
    }

    /**
     * Symulacja przebiegu choroby. Wspolczynnik smiertelnosci determinuje szanse
     * na przezycie w trakcie jednego cyklu. Jesli minie odpowiednia liczba cykli,
     * agent zostanie uzdrowiony. Agent typu Elder ma mniejsze szanse na przezycie.
     */
    @Override
    public void courseOfIllness() {

        // Generuje losową liczbę z zakresu od 0 do 1
        double odds;
        odds = random();

        if(odds*1.25<= Simulation.getMortality())
            this.Decease();
        else
            --this.timeToRecover;

        if(this.timeToRecover==0)
            this.Recover();
    }

    /**
     * Zaraza agenta jesli jest zdrowy i nie ma odpornosci. Ustawia czas do uzdrowienia.
     * W przypadku Agenta type Elder czas do uzdrowienia wynosi 6 cykli.
     */
    @Override
    public void CatchDisease(){
        if(!this.isImmune && this.status == Healthy){
            this.status = Infected;
            timeToRecover = 6;
            Statistics.setInfectedCounter(Statistics.getInfectedCounter()+1);
        }
    }
}

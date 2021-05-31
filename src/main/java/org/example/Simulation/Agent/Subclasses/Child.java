package org.example.Simulation.Agent.Subclasses;

import org.example.Simulation.Agent.Abstractions.Agent;
import org.example.Simulation.Handling.Simulation;
import org.example.Simulation.Handling.Statistics;

import static org.example.Simulation.Agent.Enums.AgentStatus.*;
import static java.lang.Math.random;

public class Child extends Agent{

    /**
     * Konstruktor
     */
    public Child(){
        super();
    }

    /**
     * Symulacja przebiegu choroby. Wspolczynnik smiertelnosci determinuje szanse
     * na przezycie w trakcie jednego cyklu. Jesli minie odpowiednia liczba cykli,
     * agent zostanie uzdrowiony. Agent typu Child ma wieksze szanse na przezycie.
     */
    @Override
    public void courseOfIllness() {
        // Generuje losową liczbę z zakresu od 0 do 1
        double odds;
        odds = random();


        if(odds*0.75 <= Simulation.getMortality())
            this.Decease();
        else
            --this.timeToRecover;

        if(this.timeToRecover==0)
            this.Recover();
    }


    /**
     * Zaraza agenta jesli jest zdrowy i nie ma odpornosci. Ustawia czas do uzdrowienia.
     * W przypadku Agenta type Child czas do uzdrowienia wynosi 4 cykli.
     */
    @Override
    public void CatchDisease(){
        if(!this.isImmune && this.status == Healthy){
            this.status = Infected;
            this.timeToRecover = 4;
            Statistics.setInfectedCounter(Statistics.getInfectedCounter()+1);
        }
    }
}

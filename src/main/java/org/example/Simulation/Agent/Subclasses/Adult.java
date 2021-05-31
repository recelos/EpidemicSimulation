package org.example.Simulation.Agent.Subclasses;

import org.example.Simulation.Agent.Abstractions.Agent;
import org.example.Simulation.Handling.Simulation;
import org.example.Simulation.Handling.Statistics;

import static org.example.Simulation.Agent.Enums.AgentStatus.*;
import static java.lang.Math.random;

public class Adult extends Agent{

    /**
     * Konstruktor
     */
    public Adult(){
        super();
    }

    /**
     * Symulacja przebiegu choroby. Wspolczynnik smiertelnosci determinuje szanse
     * na przezycie w trakcie jednego cyklu. Jesli minie odpowiednia liczba cykli,
     * agent zostanie uzdrowiony. Dla klasy Adult szanse na przezycie nie sa modyfikowane.
     */
    @Override
    public void courseOfIllness() {

        // Generuje losową liczbę z zakresu od 0 do 1
        double odds;
        odds = random();


        if(odds<= Simulation.getMortality())
            this.Decease();
        else
            --this.timeToRecover;

        if(this.timeToRecover==0)
            this.Recover();
    }

    /**
     * Zaraza agenta jesli jest zdrowy i nie ma odpornosci. Ustawia czas do uzdrowienia.
     * W przypadku Agenta typu Adult czas do uzdrowienia wynosi 5 cykli.
     */
    @Override
    public void CatchDisease(){
        if(!this.isImmune && this.status == Healthy){
            this.status = Infected;
            timeToRecover = 5;
            Statistics.setInfectedCounter(Statistics.getInfectedCounter()+1);
        }
    }

}

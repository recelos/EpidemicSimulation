package org.example;

import org.example.Simulation.Handling.Simulation;
import org.example.Simulation.Handling.Statistics;

public class Linker {

    /**
     * Odbiera dane od klienta i na ich bazie uruchamia symulacje
     * @param a Liczba wszystkich agentow
     * @param i Poczatkowa liczba zarazonych agentow
     * @param c Wspolczynnik zarazliwosci
     * @param m Wspolczynnik smiertelnosci
     */
    public static void passValueToSimulation(int a, int i, double c, double m){
        new Simulation(a, i, c, m).Run();
    }

    /**
     * Wysyla interfejsowi dane ze wszystkich cykli
     * @return String
     */
    public static String getOutputString() { return Statistics.getOutputString(); }

    /**
     * Wysyla interfejsowi laczna liczbe zarazonych w trakcie symulacji
     * @return int
     */
    public static int getInfectedQuantity(){ return Statistics.getInfectedCounter(); }

    /**
     * Wysyla interfejsowi laczna liczbe martwych w trakcie symulacji
     * @return int
     */
    public static int getDeadQuantity(){ return Statistics.getDeadCounter(); }

    /**
     * Wysyla interfejsowi laczna liczbe uzdrowionych w trakcie symulacji
     * @return int
     */
    public static int getRecoveredQuantity(){ return Statistics.getRecoveredCounter(); }



}

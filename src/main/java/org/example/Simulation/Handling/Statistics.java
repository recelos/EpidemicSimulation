package org.example.Simulation.Handling;

public class Statistics {

    /**
     * Getter licznika zarazonych
     * @return int
     */
    public static int getInfectedCounter() {
        return infectedCounter;
    }

    /**
     * Setter licznika zarazonych
     * @param infectedCounter licznik zarazonych
     */
    public static void setInfectedCounter(int infectedCounter) {
        Statistics.infectedCounter = infectedCounter;
    }

    /**
     * Getter licznika martwych
     * @return int
     */
    public static int getDeadCounter() {
        return deadCounter;
    }

    /**
     * Setter licznika martwych
     * @param deadCounter licznik martwych
     */
    public static void setDeadCounter(int deadCounter) {
        Statistics.deadCounter = deadCounter;
    }

    /**
     * Getter licznika uzdrowionych
     * @return int
     */
    public static int getRecoveredCounter() {
        return recoveredCounter;
    }

    /**
     * Setter licznika uzdrowionych
     * @param recoveredCounter licznik uzdrowionych
     */
    public static void setRecoveredCounter(int recoveredCounter) {
        Statistics.recoveredCounter = recoveredCounter;
    }

    /**
     * Getter wynikow wszystkich cykli
     * @return String
     */
    public static String getOutputString() {
        return outputString;
    }

    /**
     * Setter wynikow wszystkich cykli
     * @param outputString wyniki wszystkich cykli
     */
    public static void setOutputString(String outputString) {
        Statistics.outputString = outputString;
    }


    /**
     * Resetuje wszystkie liczniki
     */
    public static void reset(){
        infectedCounter = 0;
        deadCounter = 0;
        recoveredCounter = 0;
        outputString = "";
    }

    private static int infectedCounter=0;
    private static int deadCounter=0;
    private static int recoveredCounter=0;
    private static String outputString = "";

}

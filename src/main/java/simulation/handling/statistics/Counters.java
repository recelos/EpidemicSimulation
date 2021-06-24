package simulation.handling.statistics;

/**
 * The type Statistics.
 */
public class Counters {

    /**
     * Getter licznika zarazonych
     *
     * @return int infected counter
     */
    public static int getInfectedCounter() {
        return infectedCounter;
    }

    /**
     * Setter licznika zarazonych
     *
     * @param value the value
     */
    public static void setInfectedCounter(int value) {
        Counters.infectedCounter = value;
    }

    /**
     * Getter licznika martwych
     *
     * @return int dead counter
     */
    public static int getDeadCounter() {
        return deadCounter;
    }

    /**
     * Setter licznika martwych
     *
     * @param value the value
     */
    public static void setDeadCounter(int value) {
        Counters.deadCounter = value;
    }

    /**
     * Getter licznika uzdrowionych
     *
     * @return int recovered counter
     */
    public static int getRecoveredCounter() {
        return recoveredCounter;
    }

    /**
     * Setter licznika uzdrowionych
     *
     * @param value the value
     */
    public static void setRecoveredCounter(int value) {
        Counters.recoveredCounter = value;
    }

    /**
     * Getter wynikow wszystkich cykli
     *
     * @return String output string
     */
    public static String getOutputString() {
        return outputString;
    }

    /**
     * Setter wynikow wszystkich cykli
     *
     * @param value the value
     */
    public static void setOutputString(String value) {
        Counters.outputString = value;
    }

    public static int getInfectedInThisCycle() {
        return infectedInThisCycle;
    }

    public static void setInfectedInThisCycle(int value) {
        Counters.infectedInThisCycle = value;
    }

    public static int getDeadInThisCycle() {
        return deadInThisCycle;
    }

    public static void setDeadInThisCycle(int value) {
        Counters.deadInThisCycle = value;
    }

    public static int getRecoveredInThisCycle() {
        return recoveredInThisCycle;
    }

    public static void setRecoveredInThisCycle(int value) {
        Counters.recoveredInThisCycle = value;
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

    public static void resetCycleData(){
        infectedInThisCycle=0;
        deadInThisCycle=0;
        recoveredInThisCycle=0;
    }


    private static int infectedCounter=0;
    private static int deadCounter=0;
    private static int recoveredCounter=0;
    private static String outputString = "";

    private static int infectedInThisCycle;
    private static int deadInThisCycle;
    private static int recoveredInThisCycle;


}

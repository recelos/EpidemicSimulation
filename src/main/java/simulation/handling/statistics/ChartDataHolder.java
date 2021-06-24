package simulation.handling.statistics;

import java.util.ArrayList;
import java.util.List;

public class ChartDataHolder {

    private final List<Integer> infections;
    private final List<Integer> deaths;
    private final List<Integer> recovers;

    public ChartDataHolder() {
        recovers = new ArrayList<>();
        deaths = new ArrayList<>();
        infections = new ArrayList<>();
    }

    public void addData(int infected, int dead, int recovered){
        infections.add(infected);
        deaths.add(dead);
        recovers.add(recovered);
    }

    public List<Integer> getInfections(){ return infections; }
    public List<Integer> getDeaths(){ return deaths; }
    public List<Integer> getRecovers() { return recovers; }
}

package test.SimulationTest.InteractionTest;

import simulation.agent.abstractions.Agent;
import simulation.agent.abstractions.IAgent;
import simulation.agent.implementations.*;
import simulation.handling.family.FamilySegregator;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FamilySegregatorTest {

    FamilySegregator famSegregator = new FamilySegregator();

    @Test
    void segregate() {
        List<IAgent> agents = new ArrayList<>();

        for(int i = 0; 101 > i; ++i){
            agents.add(new Adult());
        }
        for(int i = 0; 50 > i; ++i){
            agents.add(new Child());
        }
        for(int i = 0; 50 > i; ++i){
            agents.add(new Elder());
        }

        famSegregator.organize(agents);

        assertEquals(50, famSegregator.getFamilies().size());


    }
}
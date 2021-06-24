package test.SimulationTest.InteractionTest;

import simulation.agent.abstractions.Interactive;
import simulation.agent.implementations.Adult;
import simulation.agent.implementations.Child;
import simulation.agent.implementations.Elder;
import simulation.handling.interaction.Pair;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PairTest {

    @Test
    void getFirst() {
    }

    @Test
    void getSecond() {
    }

    @Test
    void testEquals() {
        Interactive[] agents = {
                new Adult(),
                new Adult(),
                new Child(),
                new Elder()
        };
        Pair[] pairs = {
                new Pair(agents[0],agents[1]),
                new Pair(agents[0],agents[1]),
                new Pair(agents[0],agents[3]),
                new Pair(agents[2],agents[0]),
        };
        assertEquals(pairs[1], pairs[0]);
    }
}
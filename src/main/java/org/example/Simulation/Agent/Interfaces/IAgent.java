package org.example.Simulation.Agent.Interfaces;

import org.example.Simulation.Agent.Enums.AgentStatus;

public interface IAgent {
    void CatchDisease();
    void Recover();
    void Decease();
    AgentStatus getStatus();
    void courseOfIllness();
}

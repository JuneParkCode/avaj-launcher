package avaj_launcher.simulator;

import java.util.LinkedList;
import java.util.List;
import avaj_launcher.flyable.Flyable;

public class SimulatorData {
    private int simulations;
    private List<Flyable> flyables;

    public SimulatorData() {
        this.simulations = 0;
        this.flyables = new LinkedList<Flyable>();
    }

    public SimulatorData(int simulations, List<Flyable> flyables) {
        this.simulations = simulations;
        this.flyables = flyables;
    }

    public SimulatorData(SimulatorData simulatorData) {
        this.simulations = simulatorData.simulations;
        this.flyables = simulatorData.flyables;
    }

    public void setSimulations(int simulations) {
        this.simulations = simulations;
    }

    public void setFlyables(List<Flyable> flyables) {
        this.flyables = flyables;
    }

    public int getSimulations() {
        return this.simulations;
    }

    public List<Flyable> getFlyables() {
        return this.flyables;
    }
}

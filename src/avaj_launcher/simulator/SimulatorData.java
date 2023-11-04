package avaj_launcher.simulator;

import java.util.LinkedList;
import java.util.List;
import avaj_launcher.flyable.Flyable;

public class SimulatorData {
    public int simulations;
    public List<Flyable> flyables;

    public SimulatorData() {
        this.flyables = new LinkedList<Flyable>();
    }
}

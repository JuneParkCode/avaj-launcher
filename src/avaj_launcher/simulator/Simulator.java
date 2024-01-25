package avaj_launcher.simulator;

import java.util.List;

import avaj_launcher.flyable.Flyable;
import avaj_launcher.tower.WeatherTower;
import avaj_launcher.utils.logger.Logger;

public class Simulator {
    private List<Flyable> flyables;
    private int totalSimulationCount;
    private int currentSimulationCount;
    private WeatherTower weatherTower;

    public Simulator(SimulatorData simulationData) {
        this.flyables = simulationData.flyables;
        this.totalSimulationCount = simulationData.simulations;
        this.currentSimulationCount = 0;
        this.weatherTower = new WeatherTower();
        flyables.forEach((flyables) -> flyables.registerTower(weatherTower));
    }

    public void simulate() {
        while (this.currentSimulationCount < this.totalSimulationCount) {
            Logger.log(
                    String.format("\n=== SIMULATION (%d/%d) ===", this.currentSimulationCount + 1,
                            this.totalSimulationCount));
            this.weatherTower.changeWeather();
            ++this.currentSimulationCount;
        }
    }
}

package avaj_launcher.simulator;

import java.util.List;

import avaj_launcher.flyable.Flyable;
import avaj_launcher.tower.WeatherTower;
import avaj_launcher.util.Logger;

public class Simulator {
    private List<Flyable> flyables;
    private int totalSimulationCount;
    private int currentSimulationCount;
    private WeatherTower weatherTower;

    public Simulator(SimulatorData p_data) {
        flyables = p_data.flyables;
        totalSimulationCount = p_data.simulations;
        currentSimulationCount = 0;
        weatherTower = new WeatherTower();
        for (Flyable flyable : flyables) {
            flyable.registerTower(weatherTower);
        }
    }

    public void simulate() {
        while (currentSimulationCount < totalSimulationCount) {
            Logger.log(
                    String.format("\n=== SIMULATION (%d/%d) ===", currentSimulationCount + 1, totalSimulationCount));
            weatherTower.changeWeather();
            ++currentSimulationCount;
        }
    }
}

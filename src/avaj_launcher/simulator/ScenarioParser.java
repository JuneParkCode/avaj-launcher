package avaj_launcher.simulator;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import avaj_launcher.flyable.Flyable;
import avaj_launcher.flyable.aircraft.AircraftParser;

/**
 * Avaj-launcher data format
 * LINE 0 : simulations (number)
 * LINE N : TYPE(STRING) NAME(STRING) LONGITUDE(NUMBER) LATITUDE(NUMBER)
 * HEIGHT(NUMBER)
 */
public class ScenarioParser {
    public ScenarioParser() {
    }

    public SimulatorData parse(final String filePath) throws ParseFailedException {
        SimulatorData simulationData = new SimulatorData();
        Path path = Paths.get(filePath);
        if (!Files.exists(path)) {
            throw new ParseFailedException(String.format("File '%s' is not exists", filePath));
        }
        try {
            List<String> lines = Files.readAllLines(path);
            simulationData.simulations = parseSimulations(lines.get(0));
            simulationData.flyables =  parseFlyables(lines.subList(1, lines.size()));
        } catch (Exception e) {
            throw new ParseFailedException(e.getMessage());
        }
        return simulationData;
    }

    private int parseSimulations(final String line) throws ParseFailedException {
        try {
            return Integer.parseInt(line);
        } catch (NumberFormatException e) {
            throw new ParseFailedException(String.format("Simulations is not a number : %s", line));
        }
    }

    private List<Flyable> parseFlyables(final List<String> lines) throws ParseFailedException {
        AircraftParser aircraftParser = new AircraftParser();
        List<Flyable> flyables = new ArrayList<Flyable>();

        try {
            for (String line : lines) {
                flyables.add(aircraftParser.parse(line));
            }
        } catch (Exception e) {
            throw new ParseFailedException(e.getMessage());
        } 
        return flyables;
    }

    public class ParseFailedException extends Exception {
        public static final String MESSAGE = "ParseFailed";

        public ParseFailedException() {
            super(MESSAGE);
        }

        public ParseFailedException(String cause) {
            super(String.format("%s : %s", MESSAGE, cause));
        }
    }
}

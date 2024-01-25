package avaj_launcher.simulator;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import avaj_launcher.flyable.Flyable;
import avaj_launcher.flyable.aircraft.AircraftParser;
import avaj_launcher.flyable.aircraft.AircraftParser.InvalidSyntaxException;

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
        try {
            SimulatorData simulationData = new SimulatorData();
            Path path = Paths.get(filePath);
            List<String> lines = Files.readAllLines(path);
    
            if (lines.size() < 2) {
                throw new ParseFailedException("Scenario file is too short");
            }
            simulationData.setSimulations(parseSimulations(lines.get(0)));
            simulationData.setFlyables(parseFlyables(lines.subList(1, lines.size())));
            return simulationData;
        } catch (IOException e) {
            throw new ParseFailedException(e.getMessage());
        } catch (InvalidSyntaxException e) {
            throw new ParseFailedException(e.getMessage());
        }
    }

    private int parseSimulations(final String line) throws ParseFailedException {
        try {
            return Integer.parseInt(line);
        } catch (NumberFormatException e) {
            throw new ParseFailedException(String.format("Simulations is not a number : %s", line));
        }
    }

    private List<Flyable> parseFlyables(final List<String> lines) throws InvalidSyntaxException{
        AircraftParser aircraftParser = new AircraftParser();
        List<Flyable> flyables = new ArrayList<Flyable>();

        for (String line : lines) {
            flyables.add(aircraftParser.parse(line));
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

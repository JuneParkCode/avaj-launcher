package avaj_launcher.simulator;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

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
        AircraftParser aircraftParser = new AircraftParser();
        SimulatorData simulationData = new SimulatorData();
        Scanner scanner = null;

        try {
            final File file = new File(filePath);
            scanner = new Scanner(file);

            simulationData.simulations = Integer.parseInt(scanner.nextLine());
            while (scanner.hasNextLine()) {
                Flyable fly = aircraftParser.parse(scanner.nextLine());
                simulationData.flyables.add(fly);
            }
        } catch (FileNotFoundException e) {
            throw new ParseFailedException(String.format("File '%s' is not exists", filePath));
        } catch (NumberFormatException e) {
            throw new ParseFailedException(String.format("number can not over %d", Integer.MAX_VALUE));
        } catch (Exception e) {
            throw new ParseFailedException(e.getMessage());
        } finally {
            if (scanner != null) {
                scanner.close();
            }
        }

        return simulationData;
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

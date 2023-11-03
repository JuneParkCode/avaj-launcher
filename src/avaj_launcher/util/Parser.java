package avaj_launcher.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import avaj_launcher.coordinates.Coordinates;
import avaj_launcher.coordinates.CoordinatesFactory;
import avaj_launcher.flyable.Flyable;
import avaj_launcher.flyable.aircraft.AircraftFactory;
import avaj_launcher.flyable.aircraft.AircraftFactory.InvalidAircraftTypeException;
import avaj_launcher.simulator.SimulatorData;

/**
 * Avaj-launcher data format
 * LINE 0 : simulations (number)
 * LINE N : TYPE(STRING) NAME(STRING) LONGITUDE(NUMBER) LATITUDE(NUMBER)
 * HEIGHT(NUMBER)
 */
public class Parser {
    public Parser() {
    }

    public SimulatorData parse(final String filePath) throws ParseFailedException {
        SimulatorData simulationData = new SimulatorData();
        Scanner scanner = null;

        try {
            final File file = new File(filePath);
            scanner = new Scanner(file);

            simulationData.simulations = Integer.parseInt(scanner.nextLine());
            while (scanner.hasNextLine()) {
                Flyable fly = parseFlyableLine(scanner.nextLine());
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

    /**
     * FLYABLE LINE PARSER
     * FORMAT : TYPE(STRING) NAME(STRING) LONGITUDE(NUMBER) LATITUDE(NUMBER)
     * HEIGHT(NUMBER)
     * 
     * @return Flyable if failed, it @return null
     */
    private Flyable parseFlyableLine(final String line)
            throws InvalidScenarioSyntaxException, InvalidAircraftTypeException {
        final AircraftFactory aircraftFactory = AircraftFactory.getInstance();
        final String regex = "^(\\w+) (\\w+) (\\d]) (\\d+) (\\d+)$";
        final Matcher matcher = Pattern.compile(regex).matcher(line);
        if (!matcher.find()) {
            throw new InvalidScenarioSyntaxException();
        }

        final String type = matcher.group(1);
        final String name = matcher.group(2);
        final int longitude = Integer.parseInt(matcher.group(3));
        final int latitude = Integer.parseInt(matcher.group(4));
        final int height = Integer.parseInt(matcher.group(5));
        final Coordinates coordinates = CoordinatesFactory.newCoordinates(longitude, latitude, height);
        return aircraftFactory.newAircraft(type, name, coordinates);
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

    public class InvalidScenarioSyntaxException extends Exception {
        public static final String MESSAGE = "Invalid scenario syntax";

        public InvalidScenarioSyntaxException() {
            super(MESSAGE);
        }

        public InvalidScenarioSyntaxException(String cause) {
            super(String.format("%s : %s", MESSAGE, cause));
        }
    }
}
